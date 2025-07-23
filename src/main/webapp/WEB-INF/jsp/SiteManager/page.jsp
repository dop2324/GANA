<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_head" />
	<title><c:out value="${siteName}" /></title>
</head>

<body class="page_back">
	
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_header" />

	<div class="wrap_admin">

		<div id="side" class="fix_admin">
			<h2><c:out value="${tmnuVo.mnu_nm }" /></h2>
			<ul id="snb">
			<c:forEach var="snb" items="${snbMenuList}" varStatus="status">
				<li><a <c:if test="${menuDepth2 == snb.mnu_code}">class="on"</c:if> href="<c:url value="page.do?${snb.mnu_param }" />"><c:out value="${snb.mnu_nm }" /></a>
				<c:if test="${fn:length(snbSubMenuMap[snb.mnu_code]) > 0 }" >
					<div>
						<c:forEach var="sub" items="${snbSubMenuMap[snb.mnu_code]}" varStatus="smidx">
							<a <c:if test="${menuDepth3 == sub.mnu_code}">class="on"</c:if>><a href="<c:url value="page.do?${sub.mnu_param }" />"><c:out value="${sub.mnu_nm }" /></a></a>
						</c:forEach>
					</div>
				</c:if>
				</li>
			</c:forEach>
			</ul>
		</div>
		<!--//side-->

		
		<div id="right">	
			<c:if test="${fn:length(tabMenuList) > 0 && menuVo.mnu_visibleTab == 1 }">
				<ul class="snb bg">	
					<c:forEach var="tab" items="${tabMenuList}" varStatus="status">
						<li>
							<a <c:if test="${menuDepth3 == tab.mnu_code}">class="on"</c:if> href="<c:url value="page.do?${tab.mnu_param }" />"><c:out value="${tab.mnu_nm }" /></a>
						<c:if test="${fn:length(tabSubMenuMap[tab.mnu_code]) > 0 }" >
							<div>
								<c:forEach var="sub" items="${tabSubMenuMap[tab.mnu_code]}" varStatus="smidx">
									<a <c:if test="${menuDepth4 == sub.mnu_code}">class="on"</c:if> href="<c:url value="page.do?${sub.mnu_param }" />"><c:out value="${sub.mnu_nm }" /></a>
								</c:forEach>
							</div>
						</c:if>
						</li>
					</c:forEach>
				</ul>
			</c:if>

            <c:choose>
            	<c:when test="${fn:length(tabMenuList) > 0 && menuVo.mnu_visibleTab == 1 }">
            		<div class="panel">
            			<div class="sub_title">
							<h3><c:out value="${menuVo.mnu_nm }" /></h3>
						</div>
						
						<div id="contents">
							<c:import url="/WEB-INF/jsp/SiteManager/include/inc_content.jsp" />
						</div>
            		</div>
            	</c:when>
            	<c:otherwise>
            		<div class="sub_title">
						<h3><c:out value="${menuVo.mnu_nm }" /></h3>
					</div>
					
					<div id="contents">
						<c:import url="/WEB-INF/jsp/SiteManager/include/inc_content.jsp" />
					</div>
            	</c:otherwise>
            </c:choose>

		</div>
		<!--//contents-->
		
	</div>
	<!--//wrap_admin-->
	
</div>
<!--//wrap_wrap_f-->

</body>
</html>