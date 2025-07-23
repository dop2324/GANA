<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

	<%-- tab menu --%>
	<c:if test="${menuVo.mnu_visibleTab == 1 }">

		<%-- tab menu 3depth --%>
		<c:forEach var="snb" items="${snbMenuList}" varStatus="status">
		<c:if test="${menuDepth2 == snb.mnu_code }">
		<c:if test="${snb.mnu_visibleTab == 1 && fn:length(snbSubMenuMap[snb.mnu_code]) > 0 }">
			<h4 class="hidden"><c:out value="${menuVo.mnu_nm }" />탭</h4>
			<ul class="sub_tab">
				<c:forEach var="tab" items="${snbSubMenuMap[snb.mnu_code] }" varStatus="">
				    <c:set var="tab_link" value="?${tab.mnu_param }" />
					<c:if test="${tab.mnu_ty == 'link'}"><c:set var="tab_link" value="${tab.mnu_linkUrl }" /></c:if>
			        <li <c:if test="${tab.mnu_code == menuDepth3 }">class="on"</c:if>>
			        	<a href="<c:out value="${tab_link }" />" target="<c:out value="${tab.mnu_target}" />"><span><c:out value="${tab.mnu_nm }" /></span></a>
			        </li>
				</c:forEach>
			</ul>
		</c:if>
		</c:if>
		</c:forEach>


		<%-- tab menu 4depth --%>
		<c:if test="${fn:length(tab4MenuList) > 0}">
		<h4 class="hidden"><c:out value="${menuVo.mnu_nm }" />탭</h4>
		<div class="sub_tab2">
		    <c:forEach var="tab" items="${tab4MenuList}" varStatus="status">
		    <c:if test="${menuDepth3 == tab.mnu_uprCode }">
		    	<c:set var="tab_link" value="?${tab.mnu_param }" />
				<c:if test="${tab.mnu_ty == 'link'}"><c:set var="tab_link" value="${tab.mnu_linkUrl }" /></c:if>
				<a href="<c:out value="${tab_link }" />" <c:if test="${tab.mnu_code == menuDepth4 }">class="on"</c:if> target="<c:out value="${tab.mnu_target}" />"><span><c:out value="${tab.mnu_nm }" /></span></a>
		    </c:if>
			</c:forEach>
		</div>
		</c:if>


		<%-- tab menu 5depth --%>
		<c:if test="${fn:length(tab5MenuMap[menuVo.mnu_uprCode]) > 0 }">
		<h4 class="hidden"><c:out value="${menuVo.mnu_nm }" />탭</h4>
		<div class="sub_tab3">
			<c:forEach var="tab" items="${tab5MenuMap[menuVo.mnu_uprCode] }" varStatus="status">
				<c:set var="tab_link" value="?${tab.mnu_param }" />
				<c:if test="${tab.mnu_ty == 'link'}"><c:set var="tab_link" value="${tab.mnu_linkUrl }" /></c:if>
				<a href="<c:out value="${tab_link }" />" <c:if test="${tab.mnu_code == menuDepth5 }">class="on"</c:if> target="<c:out value="${tab.mnu_target}" />"><span><c:out value="${tab.mnu_nm }" /></span></a>
			</c:forEach>
		</div>
		</c:if>
	</c:if>
