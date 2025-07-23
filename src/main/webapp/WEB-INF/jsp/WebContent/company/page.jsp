<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<fmt:formatNumber var="mnuOrder" minIntegerDigits="2" value="${tmnuVo.mnu_sort}" type="number" />
<c:set var="depth2Code" value="" />
<c:set var="depth2Nm" value="" />
<c:set var="depth2Link" value="" />

<c:set var="depth3Code" value="" />
<c:set var="depth3Nm" value="" />
<c:set var="depth3Link" value="" />

<c:set var="depth4Code" value="" />
<c:set var="depth4Nm" value="" />

<c:forEach var="path" items="${menuPath}" varStatus="status">
	<c:choose>
		<c:when test="${path.mnu_level == 2 }">
			<c:set var="depth2Code" value="${path.mnu_code }" />
			<c:set var="depth2Nm" 	value="${path.mnu_nm }" />
			<c:set var="depth2Link" value="?${path.mnu_param }" />
		</c:when>
		<c:when test="${path.mnu_level == 3 }">
			<c:set var="depth3Code" value="${path.mnu_code }" />
			<c:set var="depth3Nm" 	value="${path.mnu_nm }" />
			<c:set var="depth3Link"	value="./page.do?${path.mnu_param }" />
		</c:when>
		<c:when test="${path.mnu_level == 4 }">
			<c:set var="depth4Code" value="${path.mnu_code }" />
			<c:set var="depth4Nm" 	value="${path.mnu_nm }" />
		</c:when>
	</c:choose>
</c:forEach>

<!DOCTYPE html>
<html lang="ko">
<head>
<title><c:import url="/EgovPageLink.do?link=/WebContent/common/inc_titleTag" /><c:out value="${siteVo.site_nm }" /></title>
<meta name="title" 			content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="subject" 		content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="keywords" 		content="<c:out value="${siteVo.site_nm }" />">
<meta name="description" 	content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="author" 		content="<c:out value="${siteVo.site_nm }" />">
<meta property="og:type" content="website">
<meta property="og:description" content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteVo.site_nm }" />">
<meta property="og:image" content="">
<meta property="og:url" content="">

<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_head" />

<!-- 서브 -->
<link rel="stylesheet" href="<c:url value='/design/company/css/sub.css' />">
<link rel="stylesheet" href="<c:url value='/design/company/css/content.css' />">

<script src="<c:url value='/design/company/js/sub.js' />"></script>

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
        <div id="breadcrumb">
            <div class="wrap">
                <ul>
                    <li class="home"><a href="./main.do">홈</a></li>
                    <c:forEach var="path" items="${menuPath}" varStatus="status">
						<c:if test="${path.mnu_level > 0 }">
							<li><a href="${path.mnu_linkUrl }?${path.mnu_param }"><c:out value="${path.mnu_nm }" /></a></li>
						</c:if>
		            </c:forEach>
                </ul>
            </div>
        </div>

        <div id="inner">
			<div class="wrap">
				<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_snb">
					<c:param name="depth2Code" value="${depth2Code}" />
					<c:param name="depth3Code" value="${depth3Code}" />
					<c:param name="depth4Code" value="${depth4Code}" />
				</c:import>

				<article id="content" class="ver1">
				    <h3><c:out value="${menuVo.mnu_nm }" /></h3>
				    <c:import url="/EgovPageLink.do?link=/WebContent/common/inc_tabMenu" />
				    <c:import url="/EgovPageLink.do?link=/WebContent/common/inc_content" />
				</article>
			</div>


        </div>
	</div>

	<footer id="footer">
        <c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
    </footer>
</body>
</html>
