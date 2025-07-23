<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<ul class="gnb-depth-1">

<c:forEach var="gnb" items="${gnbMenuList }" varStatus="status">
	<c:set var="menulink" value="/${path }/page.do?${gnb.mnu_param}"/>
	<c:if test="${gnb.mnu_ty == 'link' }">
		<c:set var="menulink">${gnb.mnu_linkUrl }</c:set>
	</c:if>
	
	<li class="depth-1">
		<a href="<c:url value="${menulink }" />" target="<c:out value="${gnb.mnu_target}" />" class="depth-1-link">
			<span><c:out value="${gnb.mnu_nm }" /></span>
		</a>
		
		<c:if test="${fn:length(gnbSubMenuMap[gnb.mnu_code]) > 0 }">
		<div class="depth-item">
			<div class="item-title">
				<strong><c:out value="${gnb.mnu_nm }" /></strong>
				<p><c:out value="${gnb.mnu_desc }" /></p>
			</div>
			
			<ul class="gnb-depth-2">
			<c:forEach var="sub" items="${gnbSubMenuMap[gnb.mnu_code] }" varStatus="">
			<c:set var="menulink" value="/${path }/page.do?${sub.mnu_param}"/>
				<c:if test="${sub.mnu_ty == 'link' }">
					<c:set var="menulink">${sub.mnu_linkUrl }</c:set>
				</c:if>
				
				<li class="depth-2">
					<a href="<c:url value="${menulink }" />" target="<c:out value="${sub.mnu_target}" />" class="depth-2-link">
						<span><c:out value="${sub.mnu_nm}" /></span>
					</a>
				</li>
			</c:forEach>
			</ul>
		</div>
		</c:if>
	</li>
</c:forEach>

</ul>