<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="login_wrap">
	<form id="loginFrm" name="loginFrm" method="post" action="<c:out value="${publicDir }/cms/member/login_process.do" />">
	<double-submit:preventer/>
	<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="loginUrl" 		name="loginUrl" 	value="<c:out value="${loginUrl }" />" />
		<div class="login_form">
			<div>
				<input type="text" id="mem_id" name="mem_id" maxlength="32"  autocomplete="off" required="required">
				<label for="mem_id">아이디</label> 
			</div>
			<div>
				<input type="password" id="mem_pw" name="mem_pw" maxlength="20" autocomplete="off" required="required"> 
				<label for="mem_pw">비밀번호</label> 
			</div>
        </div>
		<div class="login_btn">
			<a href="#none" onclick="login();return false;">로그인</a>
			<a href="?mnu_code=join">회원가입</a> 
		</div>
		<div class="text_btn">
			<a href="?mnu_code=findid">아이디 찾기</a> 
			<a href="?mnu_code=findpw">비밀번호 찾기</a> 
		</div>
		<p class="check_txt"><strong>비밀번호 5회 이상</strong> 입력 오류 시 접속이 차단됩니다.</p>
	</form>

</div>


<script>
function enterlogin() {
	if (window.event.keyCode == 13) {
		login();
    }
}
function login()
{
	if($("#mem_id").val() == "") {
		alert("아이디를 입력하여 주십시요");
		$("#mem_id").focus();
		return false;
	}
	if($("#mem_pw").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#mem_pw").focus();
		return false;
	}

	$("#loginFrm").submit();
}
</script>
