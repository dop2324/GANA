<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_siteCode 	= "<c:out value="${site_code}" />";
var frmTy = "<c:out value="${frmTy}" />";

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
	
	location.href = "./page.do?frmTy="+tabID+"&mnu_code="+this.mnu_code+"&site_code="+this.parm_siteCode;
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
			<li id="tab_ip" 	<c:if test="${frmTy == 'ip'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('ip')">차단 IP</a></li>
			<li id="tab_user" 	<c:if test="${frmTy == 'user'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('user')">차단 사용자</a></li>	
			<li id="tab_log" 	<c:if test="${frmTy == 'log'}">class="on"</c:if>><a href="#self" id="tabBtn03" onclick="viewTab('log')">차단 로그</a></li>		
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:set var="importUrl" value="" />
		<c:choose>
			<c:when test="${frmTy == 'ip' }">
				<c:set var="importUrl" value="/SiteManager/cms/blacklist/blackListIp.do" />
			</c:when>
			<c:when test="${frmTy == 'user' }">
				<c:set var="importUrl" value="/SiteManager/cms/blacklist/blackListUser.do" />
			</c:when>
			<c:otherwise>
				<c:set var="importUrl" value="/SiteManager/cms/blacklist/blockLog.do" />
			</c:otherwise>
		</c:choose>
		
		<c:if test="${importUrl != '' }">
			<c:import url="${importUrl }" >
				<c:param name="queryString" value="${queryString}" />
			</c:import>
		</c:if>
	</div>
</div>	