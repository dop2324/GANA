<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>본인확인</title>

</head>
<body style="padding:15px;">
	<h2>개발용 본인확인</h2>
	<form id="authFrm" name="authFrm" method="post" action="/auth/dev/auth_devProc.do">
	<input type="hidden" id="authUrl" 	name="authUrl" 	value="<c:out value="${authUrl }" />" />
	<input type="hidden" id="returnTy" 	name="returnTy" value="<c:out value="${returnTy }" />" />
	<table>
		<tr>
			<td>ID</td>
			<td>
				<input type="radio" id="diTy_1" name="diTy" value="1" /> <label for="diTy_1">고정</label>
				<input type="radio" id="diTy_0" name="diTy" value="0" /> <label for="diTy_0">랜덤</label>
				<input type="submit" value="확인" /> 
			</td>
		<td>
	</table>
	</form>

</body>
</html>