<%@ page contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%>
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
<fmt:setBundle basename="egovframework.egovProps.globals_config" />

<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0">
	<channel>

		<title><fmt:message key="Globals.SiteName" /></title>
		<link><c:out value="${currDomain }" /></link>
		<description><![CDATA[<fmt:message key="Globals.SiteName" /> <c:out value="${infoVo.mnu_nm }" /> RSS]]></description>
		<language>ko-kr</language>


		<c:forEach var="vo" items="${boardList}" varStatus="status">
		<c:set var="rss_link" value="${currDomain }/${vo.site_code }/page.do" />
			<item>
				<title><![CDATA[<c:out value="${vo.bod_ttl }" />]]></title>
				<link><![CDATA[<c:url value="${rss_link}?mnu_code=${vo.mnu_code }&bod_sn=${vo.bod_sn}&cmd=2"/>]]></link>
				<description><![CDATA[<c:out value="${vo.con_cn }" />]]></description>
				<author><![CDATA[<c:out value="${vo.con_nm }" />]]></author>
				<pubDate><![CDATA[<fmt:formatDate value="${vo.con_regDt}" pattern="yyyy-MM-dd" />]]></pubDate>
			</item>
		</c:forEach>

</channel>
</rss>
