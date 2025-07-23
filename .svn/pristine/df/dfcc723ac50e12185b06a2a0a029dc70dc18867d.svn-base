<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.*"%>
<%@ page import="egovframework.dnworks.cmm.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="double-submit" uri="http://www.egovframe.go.kr/tags/double-submit/jsp" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/util.tld" %>
<%@ taglib prefix="moa" uri="http://egovframework.dnworks/functions" %>
<%
	Util util = new Util();
	pageContext.setAttribute("newLineChar", "\n");
	pageContext.setAttribute("crcn", "\r\n"); //Space, Enter      
	pageContext.setAttribute("br", "<br/>"); //br 태그
%>
<fmt:setBundle var="globalsConfig" basename="egovframework.egovProps.globals_config" />
<c:set var="newLine" 		value="\n" />
<c:set var="now" 			value="<%=new java.util.Date()%>" />
<c:set var="returnUrl" 		value="${requestScope['javax.servlet.forward.request_uri']}" />
<c:set var="actionMenuLink" value="${requestScope['javax.servlet.forward.request_uri']}?mnu_code=${mnu_code }" />

<c:set var="htmlLang">		<fmt:message bundle="${globalsConfig}" key="site.html.lang" /></c:set>
<c:set var="mainPage">		<fmt:message bundle="${globalsConfig}" key="Globals.MainPage" /></c:set>
<c:set var="siteName">		<fmt:message bundle="${globalsConfig}" key="Globals.SiteName" /></c:set>
<c:set var="publicDir">		<fmt:message bundle="${globalsConfig}" key="Globals.PublicPath" /></c:set>
<c:set var="managerDir">	<fmt:message bundle="${globalsConfig}" key="Globals.AdminPath" /></c:set>

<c:set var="noImage">		<fmt:message bundle="${globalsConfig}" key="Globals.noImage" /></c:set>
<c:set var="thumbWidth">	<fmt:message bundle="${globalsConfig}" key="Globals.thumbWidth" /></c:set>
<c:set var="SynapUseYn">	<fmt:message bundle="${globalsConfig}" key="Globals.SynapUseYn" /></c:set>
<c:set var="kakaoJsKey">	<fmt:message bundle="${globalsConfig}" key="kakao.javascriptKey" /></c:set>

<c:set var="menu_findid">	<fmt:message bundle="${globalsConfig}" key="Site.member.menu.findid" /></c:set>
<c:set var="menu_findpw">	<fmt:message bundle="${globalsConfig}" key="Site.member.menu.findpw" /></c:set>

<c:set var="menu_login">	<fmt:message bundle="${globalsConfig}" key="Site.member.menu.login" /></c:set>
<c:set var="menu_logout">	<fmt:message bundle="${globalsConfig}" key="Site.member.menu.logout" /></c:set>

<%-- 본인확인 
<c:set var="authServiceUrl"><fmt:message key="auth.service.url" /></c:set>
<c:set var="authMode">		<fmt:message key="auth.service.mode" /></c:set>
<c:if test="${authMode == 'test' }"><c:set var="authServiceUrl" value="/common/auth_dev.jsp" /></c:if>


<c:set var="orgType">		<fmt:message key="Globals.OrgType" /></c:set>
<c:set var="orgCode">		<fmt:message key="Globals.OrgCode" /></c:set>

--%>
