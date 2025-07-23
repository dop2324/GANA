<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<form id="authfrm" name="authfrm" method="post" action="<c:url value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="bod_sn" 		name="bod_sn" 		value="<c:out value="${bod_sn }" />" />
<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />
</form>

<%-- 공통 인증 호출 --%>
<c:import url="/EgovPageLink.do?link=/${publicDir }/cms/common/inc_auth" />


<script>
$(function(){
	$("title").text("본인확인 | "+$("title").text());
});

function authNameEnd(){
    $("#authfrm").submit();
}
</script>