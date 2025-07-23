<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<ul class="depth1">
<%-- depth01 --%>

<c:forEach var="gnb" items="${gnbMenuList }" varStatus="status">
	<c:set var="menulink" value="/${path }/page.do?${gnb.mnu_param}"/>
	<c:if test="${gnb.mnu_ty == 'link' }">
		<c:set var="menulink">${gnb.mnu_linkUrl }</c:set>
	</c:if>

	<li <c:out value="${param.mobile == 1 ? 'class=\"has\"':'' }" escapeXml="false" />>
		<a href="<c:url value="${menulink }" />" target="<c:out value="${gnb.mnu_target}" />" class="<c:out value="${tmnuVo.mnu_code == gnb.mnu_code ? 'on':'off'}" />">
		<span><c:out value="${gnb.mnu_nm }" /></span></a>

		<%-- depth02 --%>
		<c:if test="${fn:length(gnbSubMenuMap[gnb.mnu_code]) > 0 }">
			<div class="depth2_wrap">
				<div class="wrap">
					<dl>
						<dt>${gnb.mnu_nm }</dt>
						<dd>메뉴 설명이 들어가는 영역</dd>
					</dl>
					<ul class="depth2">
						<c:forEach var="sub" items="${gnbSubMenuMap[gnb.mnu_code] }" varStatus="">
						<c:set var="menulink" value="/${path }/page.do?${sub.mnu_param}"/>
						<c:if test="${sub.mnu_ty == 'link' }">
							<c:set var="menulink">${sub.mnu_linkUrl }</c:set>
						</c:if>
						<li <c:out value="${param.mobile == 1 ? 'class=\"has\"':'' }" escapeXml="false" />>
							<a href="<c:url value="${menulink }" />" target="<c:out value="${sub.mnu_target}" />" class="<c:out value="${param.depth2Code == sub.mnu_code ? 'on':'off'}" />">
							<span><c:out value="${sub.mnu_nm}" /></span></a>
							<%-- depth03 --%>
							<c:if test="${fn:length(mobileSubMenuMap[sub.mnu_code]) > 0 }">
							<ul class="depth3">
							<c:forEach var="s" items="${mobileSubMenuMap[sub.mnu_code] }" varStatus="">
								<c:set var="menulink" value="/${path }/page.do?${s.mnu_param}"/>
								<c:if test="${s.mnu_ty == 'link' }">
									<c:set var="menulink">${s.mnu_linkUrl }</c:set>
								</c:if>
								<li><a href="<c:url value="${menulink }" />" target="${s.mnu_target}" class="<c:out value="${param.depth3Code == s.mnu_code ? 'on':'off'}" />">
									<span><c:out value="${s.mnu_nm}" /></span></a>
								</li>
							</c:forEach>
							</ul>
							</c:if>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>
	</li>
</c:forEach>
</ul>
