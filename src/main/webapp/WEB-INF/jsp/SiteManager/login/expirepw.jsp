<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>

<c:import url="/WEB-INF/jsp/SiteManager/include/inc_head.jsp" />

<title><c:out value="${siteName}" /></title>
</head>
<body class="login_body">

	<div class="login_wrap">
		<div class="title">
			<h1><c:out value="${siteName}" /></h1>
			<p class="dt">관리자페이지</p>
			<p class="dd b">비밀번호 사용기간이 만료 되었습니다.<br/>비밀번호를 변경하여 주십시요.</p>
			<p class="dd pt5 blue line">개인정보를 안전하게 보호하고, <br/>개인정보 도용으로 인한 피해를 예방하기 위해 <br/><fmt:message bundle="${globalsConfig}" key="Globals.expirePwDt" />일 이상 비밀번호를 변경하지 않은 경우<br/> 비밀번호를 변경을 권장하고 있습니다.</p>
		</div>
		<div>
			<c:set var="loginAction">${pageContext.request.contextPath}<fmt:message bundle="${globalsConfig}" key="Globals.AdminPath" /></c:set>
			<form id="frmChgpw" name="frmChgpw" method="post" action="<c:out value="${loginAction }/expirepw_process.do" />" onsubmit="return changePw()">
			<double-submit:preventer/>

				<div>
					<input type="text" id="user_id" name="user_id" title="id" class="inp_txt" readonly="readonly" value="<c:out value="${USR_ID }" />" placeholder="아이디" />
				</div>
				<div>
					<input type="password" id="usr_password" name="usr_password" title="password" class="inp_txt" autocomplete="new-password" placeholder="현재 비밀번호" />
					<span>현재 비밀번호</span>
				</div>
				<div>
					<input type="password" id="chg_pw" name="chg_pw" title="password" class="inp_txt" autocomplete="new-password" placeholder="변경 비밀번호" />
					<span>변경 비밀번호</span>
				</div>
				<div>
					<input type="password" id="chk_pw" name="chk_pw" title="password" class="inp_txt" autocomplete="new-password" placeholder="변경 비밀번호 확인" />
					<span>변경 비밀번호 확인</span>
				</div>
				<div class="pl5">
					<input type="checkbox" id="chg_next" name="chg_next" value="1" /> <label for="chg_next">다음에 변경하기</label>
				</div>

				<input type="submit" value="확인"/>

			</form>
		</div>
	</div>


<script>
//<![CDATA[
    /* **********************************************************************************
	 * 로그인
	 * **********************************************************************************/
	function changePw()
	{
		var chg_next = $("#chg_next").is(":checked");

		if(!chg_next) {

			if($("#user_id").val() == "") {
				alert("아이디를 입력하세요!");
				$("#user_id").focus();
				return false;
			}
			if($("#usr_password").val() == "") {
				alert("현재 비밀번호를 입력하세요!");
				$("#usr_password").focus();
				return false;
			}

			if($("#chg_pw").val() == "") {
				alert("변경 비밀번호를 입력하세요!");
				$("#chg_pw").focus();
				return false;
			}

			if(!regPw.test($("#chg_pw").val())) {
				alert("변경 비밀번호는 영문 숫자 특수기호 조합 (8자 이상 20자 이하) 사용해야 합니다.");
				$("#chg_pw").select();
				return false;
			}

			if($("#chk_pw").val() == "") {
				alert("변경 비밀번호 확인을 입력하여 주십시요");
				$("#chk_pw").select();
				return false;
			}

			if($('#chg_pw').val()!=$('#chk_pw').val()) {
				alert("변경 비밀번호 확인이 일치하지 않습니다.");
				$('#chk_pw').select();
				return false;
			}
		}

		return true;
	}
//]]>
</script>
</body>
</html>
