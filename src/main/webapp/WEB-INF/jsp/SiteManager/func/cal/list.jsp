<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<link rel="stylesheet" href="/y/common/component/tui/tui-grid.css">
<link rel="stylesheet" href="/y/common/Holdon/HoldOn.min.css">
<script src="/y/common/component/tui/tui-grid.js"></script>
<script src="/y/common/Holdon/HoldOn.min.js"></script>

<style>
.ui-datepicker {
  z-index: 9999 !important;
}
</style>

<div class="board_wrap">
  <form method="post" id="siteFrm" name="siteFrm" action="<c:out value='${actionMenuLink }' />">
    <input type="hidden" id="mnu_code" name="mnu_code" value="<c:out value='${mnu_code }' />" />
    <input type="hidden" id="orgUnqId" name="orgUnqId" value="<c:out value='${orgUnqId}'/>" />
    <input type="hidden" name="pageNo" id="pageNo" value="<c:out value='${pageNo}'/>" />

    <div class="board_search" style="justify-content:flex-start; margin-top:10px;">
      <input type="text" name="startDate" id="startDate" class="inp_txt datepicker" readonly placeholder="정산 시작일" value="<c:out value='${srchMap.startDate}'/>"/>
      ~ <input type="text" name="endDate" id="endDate" class="inp_txt datepicker" readonly placeholder="정산 종료일" value="<c:out value='${srchMap.endDate}'/>"/>
      <select name="dateType" id="dateType">
        <option value="paidOutDate" <c:if test="${dateType eq 'paidOutDate'}">selected</c:if>>정산일 기준</option>
        <option value="soldDate" <c:if test="${dateType eq 'soldDate'}">selected</c:if>>매출일 기준</option>
      </select>
      <select name="status" id="status">
        <option value="" <c:if test="${empty status}">selected</c:if>>전체</option>
        <option value="payment" <c:if test="${status eq 'payment'}">selected</c:if>>결제</option>
        <option value="refund" <c:if test="${status eq 'refund'}">selected</c:if>>환불</option>
      </select>
      <button type="button" class="bt3 srch" onclick="searchData()">검색</button>
      <button type="button" class="bt3 srch" onclick="excelDown()">엑셀</button>
      <button type="button" class="bt3 blue" onclick="syncCalData()">수동 동기화</button>
    </div>
  </form>

  <div id="grid" style="margin-top: 10px;"></div>
</div>

<script>
function addComma(value) {
	  if (value == null || value === '') return '0';
	  const number = Number(value);
	  if (isNaN(number)) return value;
	  return number.toLocaleString(); // 1234567 → 1,234,567
	}

function groupAndSummarize(data) {
	  const grouped = {};
	  
	  // 1. 그룹화 및 합계 계산
	  data.forEach(row => {
	    const orgNm = row.orgNm;
	    if (!grouped[orgNm]) {
	      grouped[orgNm] = {
	        rows: [],
	        totalPayment: 0,
	        totalRefund: 0
	      };
	    }
	    grouped[orgNm].rows.push(row);

	    const amt = Number(row.amount);
	    if (amt > 0) grouped[orgNm].totalPayment += amt;
	    if (amt < 0) grouped[orgNm].totalRefund += amt;
	  });

	  // 2. rowspan 대체 및 합계 row 추가
	  const finalRows = [];
	  for (const org in grouped) {
	    const group = grouped[org];
	    group.rows.forEach((row, idx) => {
	      row._orgNm = idx === 0 ? org : ''; // rowspan처럼
	      row._isSummary = false;
	      finalRows.push(row);
	    });
	    finalRows.push({
	      _orgNm: '▶ ' + org + ' 합계',
	      orderId: '',
	      method: '',
	      approvedAt: '',
	      soldDate: '',
	      payoutDate: '',
	      amount: '',
	      payStatus: '',
	      totalPayment: group.totalPayment,
	      totalRefund: group.totalRefund,
	      _isSummary: true
	    });
	  }

	  return finalRows;
	}

	
const grid = new tui.Grid({
	  el: document.getElementById('grid'),
	  scrollX: false,
	  scrollY: true,
	  rowHeaders: ['rowNum'],
	  bodyHeight: 500,
	  columns: [
	    {
	      header: '기관명',
	      name: '_orgNm',
	      formatter({ row }) {
	        return row._isSummary ? `<strong>${row._orgNm}</strong>` : row._orgNm;
	      }
	    },
	    { header: '거래ID', name: 'orderId' },
	    { header: '결제수단', name: 'method' },
	    { header: '승인일자', name: 'approvedAt' },
	    { header: '매출일', name: 'soldDate' },
	    { header: '정산일', name: 'payoutDate' },
	    {
	      header: '결제금액',
	      name: 'amount',
	      align: 'right',
	      formatter({ value }) {
	        return value ? addComma(value) : '';
	      }
	    },
	    {
	    	  header: '총 결제금액',
	    	  name: 'totalPayment',
	    	  align: 'right',
	    	  formatter({ row }) {
	    	    if (row._isSummary) {
	    	      const total = row.totalPayment || 0;
	    	      return '<strong>' + addComma(total) + '</strong>';
	    	    }
	    	    return '';
	    	  }
	    	},
	    	{
	    	  header: '총 환불금액',
	    	  name: 'totalRefund',
	    	  align: 'right',
	    	  formatter({ row }) {
	    	    if (row._isSummary) {
	    	      const total = row.totalRefund || 0;
	    	      return '<strong>' + addComma(Math.abs(total)) + '</strong>';
	    	    }
	    	    return '';
	    	  }
	    	}
	  ]
	});



document.addEventListener("DOMContentLoaded", function () {
	  // ① 오늘 날짜 yyyy-mm-dd 형식 생성 (템플릿 리터럴 사용 안 함)
	  var today = new Date();
	  var yyyy = today.getFullYear();
	  var mm = ('0' + (today.getMonth() + 1)).slice(-2);
	  var dd = ('0' + today.getDate()).slice(-2);
	  var todayStr = yyyy + '-' + mm + '-' + dd;

	  // ② 날짜 input에 값이 없으면 오늘 날짜로 설정
	  var startDateEl = document.getElementById('startDate');
	  var endDateEl = document.getElementById('endDate');

	  if (!startDateEl.value) startDateEl.value = todayStr;
	  if (!endDateEl.value) endDateEl.value = todayStr;

	  // ③ TUI Grid 데이터 로드
	  var rawData = <c:out value='${gridJson}' escapeXml="false"/>;
	  var groupedData = groupAndSummarize(rawData);
	  grid.resetData(groupedData);
	});



function searchData() {
  document.siteFrm.submit();
}

function excelDown() {
	  const startDate = document.getElementById('startDate').value;
	  const endDate = document.getElementById('endDate').value;
	  const dateType = document.getElementById('dateType').value;
	  const mnuCode = document.getElementById('mnu_code').value;

	  if (!startDate || !endDate) {
	    alert("정산 시작일자와 종료일자를 모두 선택해 주세요.");
	    return;
	  }

	  // 동적 form 생성
	  const form = document.createElement('form');
	  form.method = 'POST';
	  form.action = globalConfigJs.contextPath + '/SiteManager/func/cal/excelDown.do';
	  form.target = '_blank'; // 새 탭에서 다운로드 처리

	  // 필요한 파라미터들 추가
	  const params = {
	    startDate,
	    endDate,
	    dateType,
	    mnu_code: mnuCode
	  };

	  for (const key in params) {
	    const input = document.createElement('input');
	    input.type = 'hidden';
	    input.name = key;
	    input.value = params[key];
	    form.appendChild(input);
	  }

	  document.body.appendChild(form);
	  form.submit();
	  form.remove(); // 제출 후 정리
	}



function syncCalData() {
	const startDate = document.getElementById('startDate').value;
	const endDate = document.getElementById('endDate').value;
	const mnuCode = document.getElementById('mnu_code').value;

	if (!startDate) {
		alert('정산시작 일자를 선택해주세요');
		return;
	}
	if (!endDate) {
		alert('정산종료 일자를 선택해주세요');
		return;
	}

	if (confirm(startDate + ' ~ ' + endDate+ ' 기준으로 수동 동기화하시겠습니까?')) {
		HoldOn.open({
			theme: 'sk-circle',
			message: '처리 중입니다. 잠시만 기다려주세요...'
		});

		$.ajax({
			type: "post",
			url: globalConfigJs.contextPath + "/SiteManager/func/cal/syncManual.do",
			dataType: "json",
			data: {
				startDate: startDate,
				endDate: endDate,
				mnu_code: mnuCode
			},
			success: function(data) {
				HoldOn.close();
				if (data.success) {
					alert(data.message);
					searchData(); // 다시 조회
				} else {
					alert("오류: " + (data.message || "동기화 중 오류가 발생했습니다."));
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert('통신 중 오류가 발생했습니다.');
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
				HoldOn.close();
			}
		});
	}
}




</script>
