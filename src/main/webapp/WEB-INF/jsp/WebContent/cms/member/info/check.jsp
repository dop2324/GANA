<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="infoFrm" name="infoFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${siteVo.site_code }" />" />
<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />
<input type="hidden" id="step" 			name="step" 		value="<c:out value="${step }" />" />
<input type="hidden" id="substep" 		name="substep" 		value="<c:out value="${substep }" />" />

<div class="join_wrap">
	<ul class="join_box depth1">
		<li>비밀번호를 입력하세요.</li>
	</ul>

	<div class="join_form form_style">
		<fieldset>
		<dl>
			<dt><label for="check_pw"><span class="must" title="필수입력">*</span>비밀번호</label></dt>
			<dd><input type="password" id="check_pw" name="check_pw" maxlength="20" /></dd>
		</dl>
		</fieldset>
	</div>

	<div class="join_btn">
		<a href="#self" onclick="checkPass();">확인</a>
		<a href="<c:out value="?mnu_code=${mnu_code }" />">취소</a>
	</div>
</div>
</form>

<script>
function checkPass() {
	if($("#check_pw").val() == "") {
		alert("비밀번호를 입력하세요!");
		$("#check_pw").select();
		return false;
	}

	$("#infoFrm").submit();
}
</script>
