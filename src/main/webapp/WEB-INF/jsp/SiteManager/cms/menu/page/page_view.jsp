<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:choose>
	<c:when test="${pageVo == null }">
		<c:import url="/EgovPageLink.do?link=/WebContent/common/inc_comingSoon" />
	</c:when>
	<c:when test="${pageVo.page_saveTy == 0 }">
		<c:out value="${pageVo.page_jspPath }" />
	</c:when>
	<c:otherwise>
		<c:out value="${pageVo.page_cn }" escapeXml="false" />
	</c:otherwise>
</c:choose>