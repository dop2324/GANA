<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<header id="header">
	<h1><c:out value="${siteName}" /></h1>
	<nav id="gnb">
		<h2 class="hide">메뉴구성</h2>
		<c:set var="id">1</c:set>
		<c:forEach var="gnb" items="${gnbMenuList}" varStatus="status">
			<a href="page.do?${gnb.mnu_param }" class="mm<c:out value="${id }" /> <c:out value="${gnb.mnu_code == tmnu.mnu_code ? 'on':''}" />"><c:out value="${gnb.mnu_nm }" /></a>
			<c:set var="id" value="${id+1}" />
		</c:forEach>
	</nav>
	<div class="user_info">
		<c:set var="grpInfo" value="${moa:getGroupInfo(pageContext.request, GRP_ID)}" />
		<p><c:out value="${grpInfo.grp_nm }" /></p>
		<select name="">
			<option value="">기수선택</option>
		</select>
		<p><strong><c:out value="${USR_NM}" /></strong>님 반갑습니다.</p>
		<a href="managerLogout.do" class="user_logout">logout</a>
		<c:if test="${GRP_ID == 'GRP_004_02' }">
		<a href="#self" onclick="empInfo();" id="empInfo" class="user_logout">정보변경</a>
		</c:if>
	</div>
</header>