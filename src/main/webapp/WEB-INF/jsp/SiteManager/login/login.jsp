<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>

<%--
<c:import url="/EgovPageLink.do?link=SiteManager/include/inc_head" />
 --%>
<c:import url="/WEB-INF/jsp/SiteManager/include/inc_head.jsp" />

<title><c:out value="${siteName}" /></title>
</head>
<body class="login_body">

	<div class="login_wrap">
		<div class="title">
			<h1><c:out value="${siteName}" /></h1>
			<p class="dt">관리자페이지</p>
			<p class="dd">허가받지 않은 사용자의 로그인 시도시 기록이 남습니다.<br/>아이디와 비밀번호를 입력 후 로그인 버튼을 눌러주세요.</p>
		</div>
		<div>
			<c:set var="loginAction">${pageContext.request.contextPath}<fmt:message bundle="${globalsConfig}" key="Globals.AdminPath" /></c:set>
			<form id="frmLgin" name="frmLogin" method="post" action="<c:out value="${loginAction }/managerLoginProcess.do" />" onsubmit="return login()">
			<double-submit:preventer/>
			<c:set var="textLognBtn" value="manager.login.txtloginBtn"/>
				<div><input type="text" id="user_id" name="user_id" title="id" class="inp_txt" autocomplete="off" required="required" value="manager" /><span>아이디</span></div>
				<div><input type="password" id="user_password" name="user_password" title="password" class="inp_txt" required="required" value="dnftksrksk1@" /><span>비밀번호</span></div>
				<input type="submit" value="LOGIN"/>
			</form>
		</div>
	</div>


<script>
//<![CDATA[
    /* **********************************************************************************
	 * 로그인
	 * **********************************************************************************/
	function login()
	{
		var usr_id			= document.getElementById("user_id");
		var usr_password	= document.getElementById("user_password");

		if(usr_id.value.replace(/ /g, "").length == 0) {
			alert("아이디를 입력하세요!");
			usr_id.focus();
			return false;
		}
		if(usr_password.value.replace(/ /g, "").length == 0) {
			alert("비밀번호를 입력하세요!");
			usr_password.focus();
			return false;
		}

		return true;
	}
	setCookie("changeAfter",0,1);
//]]>
</script>
</body>
</html>
