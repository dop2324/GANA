<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- holdOn --%>
<script src="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.css" />

<div class="board_wrap">

	<div class="board_write form_style">
		<dl>
			<dt><label for="membNm">회원ID</label></dt>
			<dd>
				<c:out value="${resultVO.membId }" /> (<c:out value="${moa:getCodeNm(pageContext.request, '27', resultVO.membType)}" />)
			</dd>
			<dt><label for="membNm">회원명</label></dt>
			<dd><c:out value="${resultVO.membNm }" /></dd>
			<dt><label for="sexdstn">성별</label></dt>
			<dd><c:out value="${resultVO.sexdstn eq 'M'?'남자':'여자'  }" /></dd>
			<dt><label for="authMthd">인증정보</label></dt>
			<dd>
				<c:out value="${resultVO.authMthd}" />
				&nbsp;
				<div class="board_desc">
					<strong>인증일자</strong> <c:out value="${resultVO.authDt }" />
				</div>
			</dd>
			<dt><label for="mbtlnum">연락처</label></dt>
			<dd><c:out value="${resultVO.mbtlnum }" /></dd>
			<dt><label for="emlAddr">이메일 주소</label></dt>
			<dd><c:out value="${resultVO.emlAddr }" /></dd>
			<dt><label for="addr">거주지 주소</label></dt>
			<dd>
				<c:out value="${resultVO.zip }" /> <c:out value="${resultVO.addr }" /> <c:out value="${resultVO.dtlAddr }" />				
			</dd>
			<dt><label for="joinDt">회원가입일</label></dt>
			<dd><c:out value="${resultVO.joinDt }" /></dd>
			<dt><label for="lastLgnDt">마지막 로그인</label></dt>
			<dd>
				<c:out value="${resultVO.lastLgnDt }" />
				&nbsp;
				<div class="board_desc">
				<strong>로그인 실패횟수</strong> <c:out value="${resultVO.lgnFailCnt }" />
				</div>
			</dd>
			<dt><label for="lastLgnDt">비밀번호 관리</label></dt>
			<dd>
				<div>
					마지막 비밀번호 변경일자 <c:out value="${resultVO.pswdChgDt }" />
					<br />
					<div class="board_opt_frm">
						<button type="button" onclick="return pswdInit();" class="btn" style="width:120px;">비밀번호 초기화</button>
						<button type="button" onclick="return pswdRegen();" class="btn" style="width:140px;">임시비밀번호 발급</button>
					</div>
					<div class="board_desc">
						<ul>
							<li>비밀번호 초기화를 하게 될 경우 '사용자ID' + '1@'로 변경됩니다. 예시)admin의 비밀번호 초기화: admin1@</li>
							<li>임시비밀번호 발급은 임시로 랜덤한 비밀번호를 생성하여 사용자의 등록된 휴대폰번호의 문자로 발송합니다.</li>
							<li>비밀번호 5회 실패로 잠금처리된 계정의 경우 위 두가지방법으로 자동으로 잠금해제 및 로그인실패횟수가 초기화 됩니다.</li>
						</ul>
					</div>
				</div>
			</dd>
			<dt><label for="membStatCd">회원상태</label></dt>
			<dd>
				<c:out value="${moa:getCodeNm(pageContext.request, '22', resultVO.membStatCd)}" />
				<div class="board_opt_frm">
					<button type="button" onclick="membStatChg(event);" data-statcd="N" data-membid="<c:out value='${resultVO.membId }' />" class="btn red">사용중지</button>
					<button type="button" onclick="membStatChg(event);" data-statcd="P" data-membid="<c:out value='${resultVO.membId }' />" class="btn green">사용승인</button>
				</div>
				
				<div class="board_desc">
					<ul>
						<li><strong>가입승인 </strong>최초 가입한 상태. 정상적인 사용가능</li>
						<li><strong>가입탈퇴 </strong>사용자가 가입을 탈퇴한 상태, 정책에 따라 일정기간 후 개인정보가 완전삭제된다. 단 시스템정책에 따라 ID 재사용은 불가능</li>
						<li><strong>잠금상태 </strong>비밀번호를 5회 이상 실패시 로그인이 잠김(비밀번호 초기화 혹은 임시비밀번호 발급으로 자동으로 이용승인이 된다.)</li>
						<li><strong>이용중지 </strong>관리자가 내부 정책상 이용을 못하도록 중지한 상태 혹은 장기미사용자(휴면회원의 경우 휴면회원조회에서 조회가능)</li>
						<li><strong>장기미사용자 </strong>1년 이상 미접속 장기미사용자, 장기미사용자 메일 1차,2차 발송 후 3년뒤에는 자동 가입탈퇴가 진행됨</li>
					</ul>
				</div>
			</dd>
		</dl>
	</div>
	
</div>

<div class="board_btn">
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

<script type="text/javascript">
//<![CDATA[
	function pswdInit(){
		if(confirm('정말로 변경하시겠습니까?') !== false){
			
			HoldOn.open({
				theme: 'sk-circle',
				message: '처리 중입니다. 잠시만 기다려주세요...'
			});
			
			$.ajax({
				type:"post"
				, url:globalConfigJs.contextPath + "/SiteManager/func/memb/mng/pswdInit.do"
				, dataType:"json"
				, data: {
					mnu_code : '${mnu_code}'
					, membUnqId : '${resultVO.membUnqId}'
				}
				, success:function(data)
				{
					HoldOn.close();
					if (data.result) {
						alert("비밀번호가 초기화 되었습니다.");
					} else {
						alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
					}
				}
				, error:function(jqXHR, textStatus, errorThrown)
				{
					alert('통신중에 장애가 발생했습니다.');
					HoldOn.close();
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}		
			});
			
		}
	}
	
	function pswdRegen(){
		if(confirm('임시비밀번호를 발급하시겠습니까?') !== false){
			
			HoldOn.open({
				theme: 'sk-circle',
				message: '처리 중입니다. 잠시만 기다려주세요...'
			});
			
			$.ajax({
				type:"post"
				, url:globalConfigJs.contextPath + "/SiteManager/func/memb/mng/pswdRegen.do"
				, dataType:"json"
				, data: {
					mnu_code : '${mnu_code}'
					, membUnqId : '${resultVO.membUnqId}'
				}
				, success:function(data)
				{
					HoldOn.close();
					if (data.result) {
						alert("임시비밀번호가 발급되었습니다. 사용자 휴대폰의 문자를 확인바랍ㄴ다.");
					} else {
						alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
					}
				}
				, error:function(jqXHR, textStatus, errorThrown)
				{
					alert('통신중에 장애가 발생했습니다.');
					HoldOn.close();
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}		
			});
		}
	}
	
	function membStatChg(event){
		event.preventDefault();
		
		var membStatCd = event.target.getAttribute('data-statcd');
		var membId = event.target.getAttribute('data-membid');
		
		if (!membStatCd || !membId) {
	        alert("회원상태 또는 회원ID가 유효하지 않습니다.");
	        return;
	    }
		
		let isConfirmed = true;
		if(membStatCd == 'N'){
			isConfirmed = confirm('사용중지 하시겠습니까? 추후 가입승인을 통해 이용가능하게 변경하실 수 있습니다.');
		}
		if(membStatCd == 'P'){
			isConfirmed = confirm('가입승인 하시겠습니까?');
		}
		
		if (!isConfirmed) return;
		
		HoldOn.open({
			theme: 'sk-circle',
			message: '처리 중입니다. 잠시만 기다려주세요...'
		});
		
		$.ajax({
			type:"post"
			, url:globalConfigJs.contextPath + "/SiteManager/func/memb/mng/membStatChg.do"
			, dataType:"json"
			, data: {
				mnu_code : '${mnu_code}'
				, membId : membId
				, membStatCd : membStatCd
			}
			, success:function(data)
			{
				HoldOn.close();
				if (data.result) {
					alert("회원 상태가 성공적으로 변경되었습니다.");
					location.reload();
				} else {
					alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
				}
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				HoldOn.close();
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
//]]>
</script>