<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="leftm_tit">
	<h2><c:out value="${tmnuVo.mnu_nm }" /></h2>
</div>
<div class="leftm_list">
	<c:if test="${fn:length(snbMenuList) > 0 }">
	<ul class="ul_dep01">
	<c:forEach var="snb" items="${snbMenuList}" varStatus="status">
		<%-- 로그인 상태일때는 출력되지 않는 메뉴 --%>
		<c:if test="${not (moa:isLogin(pageContext.request)
				and (snb.mnu_code eq 'mlogin' 
						or snb.mnu_code eq 'membReg'
						or snb.mnu_code eq 'membFind'))}">

			<c:set var="snbClass" value="off" />
			<c:if test="${param.depth2Code == snb.mnu_code }"><c:set var="snbClass" value="on" /></c:if>
			<c:set var="depth2_link" value="?${snb.mnu_param }" />
			
			<%-- depth 1 --%>
			<c:choose>
				<c:when test="${snb.mnu_ty == 'link'}"><c:set var="depth2_link" value="${snb.mnu_linkUrl }" /></c:when>
				<c:otherwise><c:set var="depth2_link" value="?${snb.mnu_param}" /></c:otherwise>
			</c:choose>
			<c:set var="has" value="" />
			<c:if test="${snb.mnu_childCnt > 0 }"><c:set var="has" value="class=on" /></c:if>
			
			<li class="<c:out value="${snbClass }" />">
				<a href="<c:url value="${depth2_link }" />" target="<c:out value="${snb.mnu_target}" />" class="<c:out value="${snbClass }" />">
					<span><c:out value="${snb.mnu_nm }" /></span>
				</a>
			</li>

		</c:if>
	</c:forEach>
	</ul>
	</c:if>
</div>
