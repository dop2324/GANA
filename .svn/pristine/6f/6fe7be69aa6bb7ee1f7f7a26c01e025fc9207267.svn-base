<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>검색 | <c:out value="${siteName }" /></title>

<meta name="title" 			content="검색 | <c:out value="${siteName }" />">
<meta name="subject" 		content="검색 | <c:out value="${siteName }" />">
<meta name="keywords" 		content="<c:out value="${siteName }" />">
<meta name="description" 	content="검색 | <c:out value="${siteName }" />">
<meta name="author" 		content="<c:out value="${siteName }" />">
<meta property="og:type" content="website">
<meta property="og:description" content="검색 | <c:out value="${siteName }" />">
<meta property="og:image" content="">
<meta property="og:url" content="">

<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_head" />
<!-- 서브 -->
<link rel="stylesheet" href="/design/company/css/search.css">
<script src="/design/company/js/sub.js"></script>

</head>
<body>
	<div id="skipBtn">
        <a href="#content">본문 바로가기</a>
    </div>
    <header id="header">
    	<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_header">
			<c:param name="depth2Code" value="${depth2Code}" />
			<c:param name="depth3Code" value="${depth3Code}" />
			<c:param name="depth4Code" value="${depth4Code}" />
		</c:import>
    </header>
    
    <div id="container">
		<c:import url="/WebContent/search/content.do">
			<c:param name="siteVo" 		value="${siteVo}" />	
			<c:param name="tmnuVo" 		value="${tmnuVo}" />	
			<c:param name="pmnuVo" 		value="${pmnuVo}" />
			<c:param name="menuVo" 		value="${menuVo}" />
			<c:param name="prmVal" 		value="${prmVal}" />
			<c:param name="publicDir"	value="${publicDir }" />			
		</c:import>
    </div>
    	

	<footer id="footer">
        <c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
    </footer>
</body>
</html>