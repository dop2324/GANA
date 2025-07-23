<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_grpId 		= "<c:out value="${parm_grpId}" />";
var clickedTabBtn;

function viewTab(tabID) {

	if(this.parm_grpId == "") {

		if(d.selectedNode != null) {
			this.parm_grpId = d.aNodes[d.selectedNode].id;
		}
		
		if(this.parm_grpId == "") {
			alert("메뉴를 선택하세요!");
			return;
		}
	}
	
	clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	var ifrmLink = "";
	switch(tabID) {
	
		case "01" :	
			ifrmLink = "./page.do?frmTy=insert&mnu_code="+this.mnu_code+"&parm_uprGrpId="+this.parm_grpId;
			break;
	
		case "02" :	
			ifrmLink = "./page.do?frmTy=update&mnu_code="+this.mnu_code+"&parm_grpId="+this.parm_grpId;
			break;
	}
	
	location.href = ifrmLink;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/member/group/group_tree.do" />
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab01" <c:if test="${param.frmTy == 'insert'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('01')">그룹 등록</a></li>
			<li id="tab02" <c:if test="${param.frmTy == 'update'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('02')">그룹 수정</a></li>			
		</ul>
	</div>
	
	<div class="frm_wrap">
		<c:import url="/SiteManager/cms/member/group/group_form.do" />
	</div>
</div>	