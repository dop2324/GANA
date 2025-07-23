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
	
	var ifrmLink = "";
	switch(tabID) {
	
		case "01" :	
			ifrmLink = "./page.do?frmTy=insert&mnu_code="+this.mnu_code+"&upper_deptID="+this.parm_deptId;
			break;
	
		case "02" :	
			ifrmLink = "./page.do?frmTy=update&mnu_code="+this.mnu_code+"&dept_id="+this.parm_deptId;
			break;
	}
	
	location.href = ifrmLink;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/org/dept/dept_tree.do" />
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab01" <c:if test="${param.frmTy == 'insert'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('01')">부서 등록</a></li>
			<li id="tab02" <c:if test="${param.frmTy == 'update'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('02')">부서 수정</a></li>			
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:import url="/SiteManager/cms/org/dept/dept_form.do" />
	</div>
</div>	