<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- Login --%>
<c:set var="LoginLink" value="/${path }/page.do?mnu_code=login" />
<c:url var="LoginLink" value="${LoginLink }">
	<c:set var="ParamMnuCode" value="" />
	<c:if test="${mnu_code != null }">
		<c:set var="ParamMnuCode" value="?mnu_code=${mnu_code }" />
	</c:if>
	<c:param name="loginUrl" value="${returnUrl }${ParamMnuCode }" />
</c:url>

<%-- Logout --%>
<%--
<c:set var="LogoutLink" value="/${path }/page.do?mnu_code=logout" />
 --%>
<c:set var="LogoutLink" value="/common/logout.do?mnu_code=logout" />
<c:url var="LogoutLink" value="${LogoutLink }">
	<c:param name="logoutUrl" value="/${path }/main.do" />
</c:url>

<div class="wrap">
	<h1 id="h_logo"><a href="./main.do"><c:out value="${siteName }" /></a></h1>
	<nav class="pc_nav ver2">
		<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_gnb" />
	</nav>
	<div class="nav_bg"></div>
	<div class="right">
		<c:choose>
			<c:when test="${(USR_ID != null && USR_ID != 'guest')}">
				<div class="logout_toggle">
					<a href="<c:out value="${LogoutLink }" escapeXml="false" />" class="logout">로그아웃</a>
					<a href="./page.do?mnu_code=memberInfo" class="mypage">마이페이지</a>
				</div>
			</c:when>
			<c:otherwise>
				<a href="<c:out value="${LoginLink }" escapeXml="false" />" class="login">로그인</a>
			</c:otherwise>
		</c:choose>

		<%-- 검색 --%>
 		<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_search" />

		<a class="sitemap" id="openPcnav" href="">사이트맵</a>
		<a class="sitemap" id="m_nav_open" href="">메뉴오픈</a>
		<nav class="m_nav ver1">
			<div>
				<div class="site_name"><strong><c:out value="${siteName }" /></strong> 홈페이지에 오신걸을 환영합니다.</div> 
				<c:choose>
					<c:when test="${(USR_ID != null && USR_ID != 'guest')}">
						<ul class="link_list"> <!-- 고정 메뉴 타입 -->
							<li><a href="<c:out value="${LogoutLink }" escapeXml="false" />" class="logout">로그아웃</a></li>
							<li><a href="./page.do?mnu_code=memberInfo" class="mypage">마이페이지</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="link_list"> <!-- 고정 메뉴 타입 -->
							<li><a href="<c:out value="${HeaderLoginLink }" escapeXml="false" />" class="login">로그인</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
				<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_gnb">
					<c:param name="mobile" value="1" />
				</c:import>
				<a href="" id="m_nav_close"><span>닫기</span></a>
			</div>
		</nav>
		<div class="nav_bg"></div>
		
	</div>
</div>
