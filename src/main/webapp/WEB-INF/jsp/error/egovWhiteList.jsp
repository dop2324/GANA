<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<c:import url="/EgovPageLink.do?link=error/include/inc_head" />
<title>세션종료</title>
</head>

<body>
	<div class="error_wrap">
		<div class="error">
			<p>WhiteList에 포함 되지 않은 주소입니다</em></p>
			<span>관리자에게 문의 바랍니다</span>
			<div class="btn_area">
				<a href="<c:url value='/' />">메인화면</a>
				<a href="javascript:history.back()">이전페이지</a>
			</div>
		</div>
	</div>
</body>
</html>
