<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="infoFrm" name="infoFrm" method="post" action="<c:out value="${publicDir }/cms/member/update_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${siteVo.site_code }" />" />
<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />
<input type="hidden" id="step" 			name="step" 		value="<c:out value="${step }" />" />

<div class="join_wrap">
	<div class="join_agree">
		<div class="agree_wrap">
			<p class="title">회원 탈퇴 안내</p>
			<div class="content_wrap">
				<ul class="depth1">
					<li>회원탈퇴 하시면 같은 아이디로 재가입 불가능 합니다.</li>
					<li>회원 아이디를 제외한 모든 정보가 삭제 됩니다.</li>
				</ul>
			</div>
			<div class="radio_wrap">
				<input type="radio" id="mem_check2-1" name="mem_check2" /><label for="mem_check2-1">동의합니다</label>
				<input type="radio" id="mem_check2-2" name="mem_check2" /><label for="mem_check2-2">동의하지않습니다</label>
			</div>
		</div>
	</div>
	<div class="join_form form_style">
		<p class="title">회원 탈퇴</p>
		<fieldset>
			<dl>
				<dt>아이디</dt>
				<dd><c:out value="${memberVo.mem_id }" /></dd>
				<dt><label for="mem_pw"><span class="must" title="필수입력">*</span>비밀번호</label></dt>
				<dd>
					<input id="mem_pw" name="mem_pw" type="password" maxlength="20" value="">
				</dd>
			</dl>
		</fieldset>
	</div>

	<div class="join_btn">
		<a href="#self" onclick="secedeData();">확인</a>
		<a href="<c:out value="?mnu_code=${mnu_code }" />">취소</a>
	</div>
</div>
</form>

<script>
function secedeData() {
	if(!$('#mem_check2-1').is(':checked')) {
    	alert("회원 탈퇴 안내사항 확인 후 동의 하여 주십시요");
    	$('#mem_check2-1').select();
    	return false;
    }
	if($("#mem_pw").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#mem_pw").focus();
		return false;
	}
	if(confirm("회원탈퇴 하시겠습니까?")) {
		$("#infoFrm").submit();
	}
}
</script>
