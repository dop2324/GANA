<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="findfrm" name="findfrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" name="site_code"	value="<c:out value="${siteVo.site_code }" />" />
<input type="hidden" id="mnu_code" 	name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="step" 		name="step" 		value="<c:out value="${step }" />" />
</form>

<%-- 공통 인증 호출 --%>
<c:import url="/EgovPageLink.do?link=/${publicDir }/cms/common/inc_auth" />

<script>
function authNameEnd(){
    $("#findfrm").submit();
}
</script>
