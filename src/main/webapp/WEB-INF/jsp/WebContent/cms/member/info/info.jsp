<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<form id="infoFrm" name="infoFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${siteVo.site_code }" />" />
<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />
<input type="hidden" id="step" 			name="step" 		value="<c:out value="${step }" />" />

</form>
<div class="join_wrap">

	<div class="join_form form_style">
		<p class="title">아이디 정보</p>
		<fieldset>
			<dl>
		    	<dt>아이디</dt>
		    	<dd><c:out value="${memberVo.mem_id }" /></dd>
			</dl>
		</fieldset>

		<p class="title">개인 정보</p>
		<fieldset>
			<legend class="hidden">개인 정보</legend>
			<dl>
				<dt>이름</dt>
				<dd><c:out value="${memberVo.mem_nm }" /></dd>
			    <dt>이메일</dt>
			   	<dd><c:out value="${memberVo.mem_mail }" /></dd>
			    <dt>생년월일</dt>
			    <dd><c:out value="${memberVo.mem_birth }" /></dd>
			    <dt>휴대전화</dt>
			    <dd><c:out value="${memberVo.mem_moblphone }" /></dd>
			    <dt>전화번호</dt>
			    <dd><c:out value="${memberVo.mem_telno }" /></dd>
			    <dt>성별</dt>
			    <dd>
			    	<c:choose>
					<c:when test="${memberVo.mem_gender == 1 }">남성</c:when>
					<c:when test="${memberVo.mem_gender == 2 }">여성</c:when>
					<c:otherwise>비공개</c:otherwise>
				</c:choose>
			    </dd>
			    <dt>주소</dt>
			    <dd>
			    	<c:out value="${memberVo.mem_zip }" />
				<p>
				<c:out value="${memberVo.mem_addr }" />
				<c:out value="${memberVo.mem_addrDtl }" />
			    	</p>
			    </dd>
			    <dt>직장정보</dt>
			    <dd>
			    	<c:out value="${memberVo.mem_company }" />
					<c:out value="${memberVo.mem_pos }" />
				</dd>
				<dt>이메일 수신동의</dt>
			    <dd>
					<c:choose>
						<c:when test="${memberVo.mem_emlYn == 1 }">수신</c:when>
						<c:when test="${memberVo.mem_emlYn == 0 }">수신거부</c:when>
					</c:choose>
			    </dd>
			</dl>
		</fieldset>
	</div>

	<div class="join_btn">
		<a href="#self" onclick="updateCheck();">회원정보 수정</a>
		<a href="#self" onclick="secede();">회원 탈퇴</a>
	</div>
</div>


<script>
function updateCheck() {
	$("#step").val("update");
	$("#infoFrm").submit();
}

function secede() {
	$("#step").val("secede");
	$("#infoFrm").submit();
}
</script>
