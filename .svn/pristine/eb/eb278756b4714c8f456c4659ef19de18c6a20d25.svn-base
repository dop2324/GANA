<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:if test="${linkList != null && fn:length(linkList) > 0 }">
<script>
$(function() {
		
	cookiedata = document.cookie;
	<c:forEach var="vo" items="${linkList}" varStatus="status">
	<c:if test="${vo.lnk_sttus == 1 }">
	if ( cookiedata.indexOf("<c:out value="popup_${vo.lnk_sn}" />=done") < 0 ) {
		 window.open("/common/link/openPopup.do?id=<c:out value="${vo.lnk_sn}" />"
				 	, "popup_<c:out value="${vo.lnk_sn}" />"
				 	, "width=<c:out value="${vo.lnk_width}" />, height=<c:out value="${vo.lnk_height}" />");
	}
	</c:if>
	</c:forEach> 
});
</script>
</c:if>