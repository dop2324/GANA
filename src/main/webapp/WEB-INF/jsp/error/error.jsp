<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<c:import url="/EgovPageLink.do?link=error/include/inc_head" />
<title>접근오류</title>
</head>

<c:set var="errTitle" 	value="알 수 없는 오류"/>
<c:set var="errMessage" value="알 수 없는 오류가 발생하였습니다.<br/>관리자에게 문의 하시기 바랍니다."/>
<c:set var="btnBack" 	value="0"/>
<c:set var="btnLogin" 	value="0"/>

<c:choose>
	<c:when test="${errCode == '1000' }">
		<c:set var="errTitle" 	value="접근 허가된 IP주소가 아닙니다.<em>(Aeecss Denied IP : ${clientip})</em>"/>
		<c:set var="errMessage" value="입력하신 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다."/>
	</c:when>
	<c:when test="${errCode == '2000' }">
		<c:set var="errTitle" 	value="접근 차단 된 IP주소 (${clientip}) 입니다.<em>(Aeecss Denied IP)</em>"/>
		<c:set var="errMessage" value="관리자에게 문의 바랍니다"/>
	</c:when>
	<c:when test="${errCode == '3000' }">
		<c:set var="errTitle" 	value="세션이 종료 되었습니다."/>
		<c:set var="errMessage" value="다시 로그인 하여 주십시요"/>
		<c:set var="btnLogin" 	value="1"/>
	</c:when>
	<c:when test="${errCode == '4000' }">
		<c:set var="errTitle" 	value="접근 차단 된 ID 입니다.<em>(Aeecss Denied ID)</em>"/>
		<c:set var="errMessage" value="관리자에게 문의 바랍니다"/>
	</c:when>
	<c:when test="${errCode == '5000' }">
		<c:set var="errTitle" 	value="이용 권한이 없는 페이지 입니다."/>
		<c:set var="errMessage" value="이용 권한이 없거나  로그인이 필요한 서비스 입니다.<br>관리자에게 문의 하시기 바랍니다."/>
		<c:set var="btnLogin" 	value="1"/>
	</c:when>
</c:choose>

<body>
	<div class="error_wrap">
		<div class="error">
			<p>${errTitle}</p>
			<span>${errMessage}</span>
			<div class="btn_area">
				<a href="<c:url value='/' />">메인화면</a>
				
				<c:if test="${btnBack == 1 }">
					<a href="javascript:history.back()">이전페이지</a>
				</c:if>
				<c:if test="${btnLogin == 1 }">
					<a href="./login.do">로그인</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
