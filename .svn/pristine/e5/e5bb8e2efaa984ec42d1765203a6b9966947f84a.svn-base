<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>비밀번호 기한 만료</title>
	<meta name="title" 			content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteName }" />">
	<meta name="subject" 		content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteName }" />">
	<meta name="keywords" 		content="<c:out value="${siteName }" />">
	<meta name="description" 	content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteName }" />">
	<meta name="author" 		content="<c:out value="${siteName }" />">
	<meta property="og:type" content="website">
	<meta property="og:description" content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteName }" />">
	<meta property="og:image" content="">
	<meta property="og:url" content="">

	<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_head" />

</head>
<body class="expirepw_wrap">
	<div class="login_wrap">
		<form id="expirePwFrm" name="expirePwFrm" method="post" action="<c:out value="/${path }/expirepw_process.do" />">
		<double-submit:preventer/>
		<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
		<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
		<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
		<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
		<input type="hidden" id="loginUrl" 		name="loginUrl" 	value="<c:out value="${loginUrl }" />" />
			<div class="login_form">
				<div>
					<input type="text" id="mem_id" name="mem_id" autocomplete="off" value="<c:out value="${USR_ID }" />" required="required"> 
					<label for="mem_id">아이디</label> 
				</div>
				<div>
					<input type="password" id="mem_pw" name="mem_pw" maxlength="20" autocomplete="off" required="required"> 
					<label for="mem_pw">비밀번호</label> 
				</div>
				<div> 
					<input type="password" id="chg_pw" name="chg_pw" maxlength="20" autocomplete="off" required="required"> 
					<label for="mem_pw">변경 비밀번호</label> 
				</div>
				<div> 
					<input type="password" id="chk_pw" name="chk_pw" maxlength="20" autocomplete="off" required="required"> 
					<label for="mem_pw">변경 비밀번호 확인</label> 
				</div>
			</div>

			<div class="checkbox"><input type="checkbox" id="chg_next" name="chg_next" value="1" /><label for="chg_next">다음에 변경하기</label></div>

			<div class="login_btn">
				<a href="#none" onclick="changePw();">확인</a>
			</div>
		</form>
	
	</div>

<script>
function changePw()
{
	var chg_next = $("#chg_next").is(":checked");
	
	if($("#mem_id").val() == "") {
		alert("아이디를 입력하여 주십시요");
		$("#mem_id").focus();
		return false;
	}
	
	if(!chg_next) {
		
		if($("#mem_pw").val() == "") {
			alert("비밀번호를 입력하여 주십시요");
			$("#mem_pw").focus();
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
	$("#expirePwFrm").submit();
}
</script>

</body>
</html>
