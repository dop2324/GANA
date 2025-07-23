<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<nav class="sub_nav ver2">
	<h2>
		<c:out value="${tmnuVo.mnu_nm }" />
		<span>메뉴설명</span>
	</h2>
	<c:if test="${fn:length(snbMenuList) > 0 }">
		<ul class="depth1">

			<c:forEach var="snb" items="${snbMenuList}" varStatus="status">
			<c:set var="snbClass" value="off" />
			<c:if test="${param.depth2Code == snb.mnu_code }"><c:set var="snbClass" value="on" /></c:if>
			
			<c:set var="depth2_link" value="?${snb.mnu_param }" />
			<%-- depth 1 --%>
			<c:choose>
				<c:when test="${snb.mnu_ty == 'link'}"><c:set var="depth2_link" value="${snb.mnu_linkUrl }" /></c:when>
				<c:otherwise><c:set var="depth2_link" value="?${snb.mnu_param}" /></c:otherwise>
			</c:choose>
			<c:set var="has" value="" />
			<c:if test="${snb.mnu_childCnt > 0 }"><c:set var="has" value="class='has'" /></c:if>
			<li <c:out value="${has}" escapeXml="false" />>
				<a href="<c:out value="${depth2_link }" />" target="<c:out value="${snb.mnu_target}" />" class="<c:out value="${snbClass }" />">
					<span><c:out value="${snb.mnu_nm }" /></span>
				</a>

				<%-- depth 2 --%>
    			<c:if test="${fn:length(snbSubMenuMap[snb.mnu_code]) > 0 }">
    			<ul class="depth2">
    			<c:forEach var="sub" items="${snbSubMenuMap[snb.mnu_code] }" varStatus="">
					<c:set var="snbClass" value="off" />
					<c:if test="${param.depth3Code == sub.mnu_code }"><c:set var="snbClass" value="on" /></c:if>
					
					<c:set var="menulink">?${sub.mnu_param}</c:set>
					<c:if test="${t.mnu_type == 'link' }"><c:set var="menulink">${sub.mnu_linkUrl }</c:set></c:if>
				
					<li>
						<a href="<c:out value="${menulink }" />" target="<c:out value="${sub.mnu_target}" />" class="<c:out value="${snbClass }" />">
							<span><c:out value="${sub.mnu_nm}" /></span>
						</a>
					</li>
				</c:forEach>
    			</ul>
    			</c:if>
			</c:forEach>
		</ul>
	</c:if>	
</nav>