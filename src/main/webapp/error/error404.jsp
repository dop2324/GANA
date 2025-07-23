<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=yes" />
	<link rel="stylesheet" href="<c:url value='/design/common/css/common.css' />" />
	<link rel="stylesheet" href="<c:url value='/design/common/template/css.css' />" />
	<style>
		body { font-size:17rem; }
		:root {
			--p_color:#1e60c8;
		}
	</style>
	<title>404 존재하지 않는 페이지 요청</title>
</head>

<body>
	<div class="error_wrap">
		<div class="error">
			<p>존재하지 않는 페이지 요청<em>(404 Page Not Found)</em></p>
			<span>페이지의 주소가 잘못 입력되었거나, 변경, 삭제되어 찾을 수 없습니다.<br />입력하신 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다.</span>
			<div class="btn_area">
				<a href="javascript:history.back()">이전페이지</a>
				<a href="<c:url value='/' />">메인화면</a>
			</div>
		</div>
	</div>
</body>
</html>
