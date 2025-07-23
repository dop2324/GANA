<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 	= "<c:out value="${mnu_code}" />";
var parm_codeSn = "<c:out value="${code_sn}" />";
var frmTy 		= "<c:out value="${frmTy}" />";

function viewTab(tabID) {
	if(tabID == "") tabID = this.frmTy;
	if(this.parm_codeSn == "") {
		if(d.selectedNode != null) {
			this.parm_codeSn = d.aNodes[d.selectedNode].id;
		}
		if(this.parm_codeSn == "") {
			alert("코드를 선택하세요!");
			return;
		}
	}
	
	//clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	var ifrmLink = "";
	switch(tabID) {
	
		case "insert" :	
			ifrmLink = "./page.do?frmTy=insert&mnu_code="+this.mnu_code+"&code_uprSn="+this.parm_codeSn;
			break;
	
		case "update" :	
			ifrmLink = "./page.do?frmTy=update&mnu_code="+this.mnu_code+"&code_sn="+this.parm_codeSn;
			break;
	}
	
	location.href = ifrmLink;
	this.frmTy = tabID;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/cmmncd/cmmncd_tree.do" />
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab01" <c:if test="${frmTy == 'insert'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('insert')">코드 등록</a></li>
			<li id="tab02" <c:if test="${frmTy == 'update'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('update')">코드 수정</a></li>			
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:import url="/SiteManager/cms/cmmncd/cmmncd_form.do" />
	</div>
</div>	