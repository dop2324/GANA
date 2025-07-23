<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<c:import url="/EgovPageLink.do?link=error/include/inc_head" />
<title>500 서버 에러 발생</title>
</head>

<body>
	<div class="error_wrap">
		<div class="error">
			<p>알수 없는 오류 발생<em>(Internal Server Error)</em></p>
			<span>서비스 이용에 불편을 드려 대단히 죄송합니다.<br />빠른 시간내에 문제를 처리하겠습니다.</span>
			<div class="btn_area">
				<a href="javascript:history.back()">이전페이지</a>
				<a href="<c:url value='/' />">메인화면</a>
			</div>
		</div>
	</div>
</body>
</html>
