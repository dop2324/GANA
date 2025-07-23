<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach var="p" items="${reverseMenuPath}" varStatus="status">
	<c:if test="${p.mnu_level > 0 }"><c:out value="${p.mnu_nm }" /> | </c:if>
</c:forEach>
