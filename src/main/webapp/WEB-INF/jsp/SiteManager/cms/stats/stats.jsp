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
	
	if(this.parm_siteCode == "") {
		if(d.selectedNode != null) {
			this.parm_siteCode = d.aNodes[d.selectedNode].id;
		}
	}
	clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	//if(this.parm_siteCode == "root") this.parm_siteCode = "";
	location.href = "./page.do?frmTy="+tabID+"&mnu_code="+this.mnu_code+"&site_code="+this.parm_siteCode;
}
</script>
<div class="panel_left">	
	<div class="list_wrap list_tree">
	<script>
		d = new dTree("d");
		d.add("", "-1", "사이트 목록", "javascript:setCode('')", "", "", "", "", "", 1);
		<c:forEach var="vo" items="${siteList}" varStatus="status">
			d.add("<c:out value="${vo.site_code}" />"
				, ""
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
			<li id="tab_site" 	<c:if test="${frmTy == 'site'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('site')">사이트별</a></li>
			<li id="tab_year" 	<c:if test="${frmTy == 'year'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('year')">연도별</a></li>
			<li id="tab_month" 	<c:if test="${frmTy == 'month'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('month')">월별</a></li>
			<li id="tab_day" 	<c:if test="${frmTy == 'day'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('day')">일별</a></li>
			<li id="tab_time" 	<c:if test="${frmTy == 'time'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('time')">시간별</a></li>
			<li id="tab_menu" 	<c:if test="${frmTy == 'menu'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('menu')">메뉴별</a></li>
			<li id="tab_log" 	<c:if test="${frmTy == 'log'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('log')">접속로그</a></li>	
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<div class="board_wrap">
			<c:set var="importUrl" value="" />
			<c:choose>
				<c:when test="${frmTy == 'year' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsYear.do" />
				</c:when>
				<c:when test="${frmTy == 'month' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsMonth.do" />
				</c:when>
				<c:when test="${frmTy == 'day' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsDay.do" />
				</c:when>
				<c:when test="${frmTy == 'time' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsTime.do" />
				</c:when>
				<c:when test="${frmTy == 'menu' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsMenu.do" />
				</c:when>
				<c:when test="${frmTy == 'log' }">
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsLog.do" />
				</c:when>
				<c:otherwise>
					<c:set var="importUrl" value="/SiteManager/cms/stats/statsSite.do" />
				</c:otherwise>
			</c:choose>
		</div>
		
		<c:if test="${importUrl != '' }">
			<c:import url="${importUrl }" >
				<c:param name="queryString" value="${queryString}" />
			</c:import>
		</c:if>
	</div>
</div>	