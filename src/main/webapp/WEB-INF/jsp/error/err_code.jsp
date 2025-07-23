<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<c:import url="/EgovPageLink.do?link=error/include/inc_head" />
<title>500 서버 에러 발생</title>


<c:set var="errTitle" 	value="알 수 없는 오류"/>
<c:set var="errMessage" value="알 수 없는 오류가 발생하였습니다.<br/>관리자에게 문의 하시기 바랍니다."/>

<c:choose>
	<c:when test="${errCode == 1 }">
		<c:set var="errTitle" 	value="존재하지 않는 페이지 요청"/>
		<c:set var="errMessage" value="요청하신 페이지는 존재하지 않습니다."/>
	</c:when>
	<c:when test="${errCode == 2 }">
		<c:set var="errTitle" 	value="서버 에러 발생"/>
		<c:set var="errMessage" value="서비스 이용에 불편을 드려 대단히 죄송합니다.<br/>빠른 시간내에 문제를 처리하겠습니다."/>
	</c:when>
	<c:when test="${errCode == 3 }">
		<c:set var="errTitle" 	value="존재하지 않는 메뉴 요청"/>
		<c:set var="errMessage" value="요청하신 메뉴는 존재하지 않습니다."/>
	</c:when>
	<c:when test="${errCode == 4 }">
		<c:set var="errTitle" 	value="권한없음"/>
		<c:set var="errMessage" value="이용 권한이 없거나  로그인이 필요한 서비스 입니다.<br/>관리자에게 문의 하시기 바랍니다."/>
	</c:when>
</c:choose>
</head>

<body>
	<div class="error_wrap">
		<div class="error">
			<p><c:out value="${errTitle }" /></p>
			<span>죄송합니다. <c:out value="${errMessage }" /></span>
			<div class="btn_area">
				<a href="javascript:history.back()">이전페이지</a>
			</div>
		</div>
	</div>
</body>
</html>
