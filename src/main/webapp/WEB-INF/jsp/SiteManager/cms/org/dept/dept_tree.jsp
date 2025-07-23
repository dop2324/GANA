<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
function setDeptId(dept_id) {
	
	this.parm_deptId = dept_id;
	if(clickedTabBtn != null) {
		clickedTabBtn.click();
	}
	else
	{
		viewTab("02");
	}
}

function openTree(dept_id) {
	d.closeAll();
	d.openTo(dept_id, true);
	setDeptId(dept_id);
}

d = new dTree("d");
<c:forEach var="vo" items="${deptTree}" varStatus="status">
d.add("<c:out value="${vo.dept_id}" />"
		, "<c:out value="${vo.upper_deptID != null ? vo.upper_deptID:'-1'}" />"
		, "(<c:out value="${vo.dept_id}" />) <c:out value="${vo.dept_nm}" /> <c:out value="${vo.dept_useYn == 0 ? '(중지)':''}" />"
		, "javascript:setDeptId('<c:out value="${vo.dept_id}" />');"
		, "", "", "", "", ""
		, <c:out value="${vo.dept_useYn}" />
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