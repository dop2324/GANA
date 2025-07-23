<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div class="frm_wrap pr10">
	<form id="orgForm" name="orgForm" method="post" action="<c:url value="${managerDir }/func/lnkorg/info_process.do" />">
		<double-submit:preventer/>
		<input type="hidden" name="orgUnqId" value="<c:out value='${orgVo.orgUnqId}'/>">
		<input type="hidden" name="mnu_code" value="<c:out value='${mnu_code}'/>">
		<input type="hidden" name="cmd" value="<c:out value='${cmd}'/>">
		<input type="hidden" name="returnUrl" value="<c:out value='${returnUrl}'/>">
		<input type="hidden" name="queryString" value="<c:out value='${queryString}'/>">

		<div class="form_style">
			<dl>
				<dt><label for="orgNm">기관명 <span class="require">*</span></label></dt>
				<dd><input type="text" id="orgNm" name="orgNm" class="inp_txt" value="<c:out value='${orgVo.orgNm}'/>" required></dd>

				<dt><label for="zip">우편번호</label></dt>
				<dd>
					<input type="text" id="zip" name="zip" class="inp_txt" readonly value="<c:out value='${orgVo.zip}'/>">
					<a href="#self" class="btn" onclick="execDaumPostcode()">주소검색</a>
				</dd>
				
				<dt><label for="roadNmAddr">도로명 주소</label></dt>
				<dd><input type="text" id="roadNmAddr" name="roadNmAddr" class="inp_txt" readonly value="<c:out value='${orgVo.roadNmAddr}'/>"></dd>
				
				<dt><label for="dtlAddr">상세 주소</label></dt>
				<dd><input type="text" id="dtlAddr" name="dtlAddr" class="inp_txt" value="<c:out value='${orgVo.dtlAddr}'/>"></dd>

				<dt><label for="picNm">담당자명</label></dt>
				<dd><input type="text" id="picNm" name="picNm" class="inp_txt" value="<c:out value='${orgVo.picNm}'/>"></dd>

				<dt><label for="picEmlAddr">담당자 이메일</label></dt>
				<dd><input type="email" id="picEmlAddr" name="picEmlAddr" class="inp_txt" value="<c:out value='${orgVo.picEmlAddr}'/>"></dd>

				<dt><label for="picCnctTelno">담당자 연락처</label></dt>
				<dd><input type="text" id="picTelno" name="picTelno" class="inp_txt" value="<c:out value='${orgVo.picTelno}'/>"></dd>

				<dt><label for="mtncoNm">유지보수 업체명</label></dt>
				<dd><input type="text" id="mtncoNm" name="mtncoNm" class="inp_txt" value="<c:out value='${orgVo.mtncoNm}'/>"></dd>

				<dt><label for="mtncoPicNm">유지보수 업체 담당자</label></dt>
				<dd><input type="text" id="mtncoPicNm" name="mtncoPicNm" class="inp_txt" value="<c:out value='${orgVo.mtncoPicNm}'/>"></dd>

				<dt><label for="mtncoPicTelno">유지보수 연락처</label></dt>
				<dd><input type="text" id="mtncoPicTelno" name="mtncoPicTelno" class="inp_txt" value="<c:out value='${orgVo.mtncoPicTelno}'/>"></dd>

				<dt><label for="mtncoPicEmlAddr">유지보수 이메일</label></dt>
				<dd><input type="email" id="mtncoPicEmlAddr" name="mtncoPicEmlAddr" class="inp_txt" value="<c:out value='${orgVo.mtncoPicEmlAddr}'/>"></dd>

				<dt><label for="hmpgUrl">홈페이지 주소</label></dt>
				<dd><input type="text" id="hmpgUrl" name="hmpgUrl" class="inp_txt" value="<c:out value='${orgVo.hmpgUrl}'/>"></dd>

				<dt>사용 여부</dt>
				<dd>
					<input id="useYnY" name="useYn" type="radio" value="Y" <c:if test="${orgVo.useYn eq 'Y'}">checked</c:if> class="inp_rad">
					<label for="useYnY">사용</label>
					<input id="useYnN" name="useYn" type="radio" value="N" <c:if test="${orgVo.useYn eq 'N'}">checked</c:if> class="inp_rad">
					<label for="useYnN">중지</label>
				</dd>
			</dl>
		</div>

		<div class="board_btn">
			<a href="#self" class="btn blue" onclick="submitOrgForm()">저장</a>
			<c:if test="${cmd eq '16' }">
				<a href="javascript:void(0);" class="btn pink" onclick="confirmDelete('<c:out value="${resultVO.fsvUnqId}" />')">삭제</a>
			</c:if>
			<a href="<c:out value='${listLink}'/>" class="btn">목록</a>
		</div>
	</form>
</div>

<script>
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 주소 정보 세팅
			document.getElementById('zip').value = data.zonecode;
			document.getElementById('roadNmAddr').value = data.roadAddress;

			// 상세주소 포커스
			document.getElementById('dtlAddr').focus();
		}
	}).open();
}

function submitOrgForm() {
	if (!$("#orgNm").val()) {
		alert("기관명을 입력하세요.");
		$("#orgNm").focus();
		return false;
	}
	$("#orgForm").submit();
}
function confirmDelete(orgUnqId) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		const form = document.getElementById("orgForm");
		form.cmd.value = "32";  // cmd를 32(삭제)로 설정
		form.submit();
	}
}

</script>
