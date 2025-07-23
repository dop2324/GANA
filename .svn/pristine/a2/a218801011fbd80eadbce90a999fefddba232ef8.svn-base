<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
function setGroupId(grp_id) {
	
	this.parm_grpId = grp_id;
	if(clickedTabBtn != null) {
		clickedTabBtn.click();
	}
	else
	{
		viewTab("02");
	}
}

function openTree(mnu_code) {
	d.closeAll();
	d.openTo(mnu_code, true);
	setGroupId(mnu_code);
}

d = new dTree("d");
<c:forEach var="vo" items="${groupTree}" varStatus="status">
d.add("<c:out value="${vo.grp_id}" />"
		, "<c:out value="${vo.grp_uprID}" />"
		, "<c:out value="${vo.grp_nm}" /> <c:out value="${vo.grp_sttus == 0 ? '(중지)':''}" />"
		, "javascript:setGroupId('<c:out value="${vo.grp_id}" />');"
		, "", "", "", "", ""
		, <c:out value="${vo.grp_sttus}" />
		);
</c:forEach>
document.write(d);
d.aNodes.forEach((item, index) => {
    if(item.id == this.parm_siteCode) {
    	d.s(index);
    }
});
d.openAll();
d.clearCookie();
</script>