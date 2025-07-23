<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>

function setMenuCode(mnu_code, pageFlag) {
	if(pageFlag == 1) {
		this.parm_mnuCode = mnu_code;
		viewTab("");
	}
}

function openTree(mnu_code) {
	d.closeAll();
	d.openTo(mnu_code, true);
	setMenuCode(mnu_code);
}
var parm_mnuCode = "<c:out value="${parm_mnuCode}" />";
d = new dTree("d");
<c:forEach var="menu" items="${menuTree}" varStatus="status">
<c:set var="tree_name"><c:out value="${menu.value.mnu_nm }" /></c:set>
<c:set var="menuType" value="" />
<c:set var="pageFlag" value="0" />
<c:choose>
	<c:when test="${menu.value.mnu_ty == 'menu+page'}">		<c:set var="menuType" value="P" /><c:set var="pageFlag" value="1" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'menu+program'}">	<c:set var="menuType" value="PG" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'page'}">			<c:set var="menuType" value="P" /><c:set var="pageFlag" value="1" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'page+program'}">	<c:set var="menuType" value="P+PG" /><c:set var="pageFlag" value="1" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'board'}">			<c:set var="menuType" value="B" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'page+board'}">	<c:set var="menuType" value="P+B" /><c:set var="pageFlag" value="1" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'saeolboard'}">	<c:set var="menuType" value="Saeol" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'program'}">		<c:set var="menuType" value="PG" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'link'}">			<c:set var="menuType" value="L" /></c:when>
	<%--
	<c:when test="${menu.value.mnu_ty == 'innerLink'}">		<c:set var="menuType" value="L" /></c:when>
	<c:when test="${menu.value.mnu_ty == 'outerLink'}">		<c:set var="menuType" value="L" /></c:when>
	--%>
	<c:when test="${menu.value.mnu_ty == 'shareBoard'}">	<c:set var="menuType" value="SB" /></c:when>
</c:choose>
d.add("${menu.value.mnu_code}"
		, "<c:out value="${menu.value.mnu_uprCode == null ? -1: menu.value.mnu_uprCode}" />"
		, "<c:out value="${tree_name}" /> <c:out value="${menuType != '' ? '-':''}" /> <c:out value="${menuType}" /> <c:out value="${menu.value.mnu_sttus == 0 ? ' (중지)':''}" />"
		, "javascript:setMenuCode('<c:out value="${menu.value.mnu_code}" />', <c:out value="${pageFlag}" />);"
		, "", "", "", "", ""
		, <c:out value="${pageFlag}" />
		);
</c:forEach>
document.write(d);
d.aNodes.forEach((item, index) => {
	if(item.id == this.parm_mnuCode) {
    	d.s(index);
    }
});

d.openAll();
d.clearCookie();
</script>