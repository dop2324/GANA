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
<link rel="stylesheet" href="<c:url value='/member/css/sub.css' />">

<c:import url="/common/link/link.do">
	<c:param name="path" value="/WebContent/common/inc_popupScript" />
	<c:param name="lgp_sn" value="10" />
</c:import>
</head>
<body>

<div id="skip_nav">
	<ul>
		<li><a class="accessibility" href="#gnb">주요 메뉴로 건너뛰기</a></li>
		<li><a class="accessibility" href="#body_layout">본문으로가기</a></li>
	</ul>
</div>
<!--#skip_nav E-->

<div id="wrap" class="">

<%-- header S --%>
<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_header">
	<c:param name="depth2Code" value="${depth2Code}" />
	<c:param name="depth3Code" value="${depth3Code}" />
	<c:param name="depth4Code" value="${depth4Code}" />
</c:import>
<%-- header E --%>

<div id="body_layout">
	<div class="top_box_logo">
		<span class="slogn">새로 만드는 위대한 울산</span>
	</div>
	
	<div class="body_inner">
		<!--S left-->
		<aside>
			<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_snb">
				<c:param name="depth2Code" value="${depth2Code}" />
				<c:param name="depth3Code" value="${depth3Code}" />
				<c:param name="depth4Code" value="${depth4Code}" />
			</c:import>
		</aside>
		<!--E left-->
		
		<div id="contents_inner" class="contents_inner">
		
			<div class="line_map">
				<h2><c:out value="${menuVo.mnu_nm }" /></h2>
				
				<div class="util_box">
					<ul class="location_box">
						<li><a href="#" title="홈 바로가기"><span class="sound_only">홈</span></a></li>
						<c:forEach var="path" items="${menuPath}" varStatus="status">
							<c:if test="${path.mnu_level > 0 }">
								<li><a href="${path.mnu_linkUrl }?${path.mnu_param }"><span><c:out value="${path.mnu_nm }" /></span></a></li>
							</c:if>
						</c:forEach>
					</ul>
					
					<div class="sns_box">
						<ul>
							<li><a class="sns_print" href="#print" onclick="window.print();return false;">프린트</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="content_box">
				<c:import url="/EgovPageLink.do?link=/WebContent/common/inc_content" />
			</div>
			
		</div>
		<!-- .contents_inner -->
	
	</div>
	<!-- .body_inner ED -->

	<div id="foot_layout" class="foot_layout">
		<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
	</div>

</div>
<%-- body_layout E --%>
</div>

</body>
</html>
