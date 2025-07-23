<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_siteCode 	= "<c:out value="${site_code}" />";

function setCode(site_code) {
	this.parm_siteCode = site_code;
	viewTab("");
}
function viewTab(tabID) {
	if(tabID == "") tabID = this.frmTy;
	
	if(this.parm_deptId == "") {

		if(d.selectedNode != null) {
			this.parm_siteCode = d.aNodes[d.selectedNode].id;
		}
		
		if(this.parm_siteCode == "") {
			alert("사이트를 선택하세요!");
			return;
		}
	}
	
	clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	location.href = "./page.do?mnu_code="+this.mnu_code+"&site_code="+this.parm_siteCode;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
	<script>
		d = new dTree("d");
		d.add("root", "-1", "사이트 목록", "javascript:setCode('')", "", "", "", "", "", 1);
		<c:forEach var="vo" items="${siteList}" varStatus="status">
			d.add("<c:out value="${vo.site_code}" />"
				, "root"
				, "(<c:out value="${vo.site_code}" />) <c:out value="${vo.site_nm}" /> <c:out value="${vo.site_useYn == 0 ? '(중지)':''}" />"
				, "javascript:setCode('<c:out value="${vo.site_code}" />');"
				, "", "", "", "", ""
				, <c:out value="${vo.site_useYn}" />
				);
		</c:forEach>
		document.write(d);
		d.aNodes.forEach((item, index) => {
		    if(item.id == parm_siteCode) {
		    	d.s(index);
		    }
		});
		d.clearCookie();
	</script>
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab01" class="on"><a href="<c:out value="${listLink }"/>" id="tabBtn01">설문 목록</a></li>	
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:if test="${site_code != '' }">
		<c:import url="/SiteManager/cms/survey/initSurvey.do">
			<c:param name="site_code" 		value="${site_code}" />
			<c:param name="queryString" 	value="${queryString}" />
		</c:import>
		</c:if>
	</div>
</div>	