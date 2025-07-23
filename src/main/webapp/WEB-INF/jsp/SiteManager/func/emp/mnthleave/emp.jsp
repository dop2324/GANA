<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_deptId 	= "<c:out value="${dept_id}" />";
var clickedTabBtn;

function viewTab(tabID) {

	if(this.parm_deptId == "") {

		if(d.selectedNode != null) {
			this.parm_deptId = d.aNodes[d.selectedNode].id;
		}
		
		if(this.parm_deptId == "") {
			alert("부서를 선택하세요!");
			return;
		}
	}
	
	clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	var ifrmLink = "./page.do?mnu_code="+this.mnu_code+"&dept_id="+this.parm_deptId;;
	
	location.href = ifrmLink;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/org/dept/dept_tree.do" />
	</div>
</div>

<div class="panel_right">
	
	<div class="frm_wrap">
		<c:import url="/SiteManager/func/emp/mnthleave/mnthleave.do">
			<c:param name="queryString" 	value="${queryString}" />
		</c:import>
	</div>
</div>	