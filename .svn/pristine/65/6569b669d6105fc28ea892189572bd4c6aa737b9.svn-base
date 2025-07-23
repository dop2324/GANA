<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_siteCode 	= "<c:out value="${site_code}" />";
var parm_mnuCode 	= "<c:out value="${parm_mnuCode}" />";
var frmTy = "<c:out value="${frmTy}" />";

function selectSite()
{
	location.href = "<c:out value="?mnu_code=${mnu_code}" escapeXml="false"  />&frmTy="+frmTy+"&site_code="+$("#site_code").val();
}  

function viewTab(tabID) {
	if(tabID == "") tabID = this.frmTy;
	
	if(this.parm_deptId == "") {

		if(d.selectedNode != null) {
			this.parm_siteCode = d.aNodes[d.selectedNode].id;
		}
		
	}
	
	clickedTabBtn = $("#tabBtn"+tabID);
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	location.href = "./page.do?frmTy="+tabID+"&mnu_code="+this.mnu_code+"&site_code="+this.parm_siteCode+"&parm_mnuCode="+this.parm_mnuCode;
}
</script>
<div class="panel_left">
	<div class="srch_wrap">
		<form>
			<c:set var="siteList" value="${defaultSiteMap.sitePrmList }" />
			<select title="사이트 선택" name="site_code" id="site_code">
				<c:set var="id">1</c:set>
				<c:forEach var="vo" items="${siteList}" varStatus="status">
					<option value="<c:out value="${vo.site_code}" />" <c:if test="${site_code == vo.site_code}">selected="selected"</c:if>><c:out value="${vo.site_nm }" /></option>
				<c:set var="id" value="${id+1}" />
				</c:forEach>
							
			</select>
			<a href="#self" class="btn" onclick="selectSite()">조회</a>
		</form>
	</div>
	
	<div class="list_wrap list_tree">
		<c:if test="${fn:length(siteList) > 0 }">
			<c:import url="/SiteManager/cms/menu/menu_tree.do?site_code=${site_code}" />
		</c:if>
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab_log" 		<c:if test="${frmTy == 'log'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('log')">CMS 로그</a></li>
			<li id="tab_error" 		<c:if test="${frmTy == 'error'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('error')">CMS 오류 로그</a></li>
			<li id="tab_blocklog" 	<c:if test="${frmTy == 'blocklog'}">class="on"</c:if>><a href="#self" id="tabBtn03" onclick="viewTab('blocklog')">차단 로그</a></li>
			<li id="tab_alarmlog" 	<c:if test="${frmTy == 'alarmlog'}">class="on"</c:if>><a href="#self" id="tabBtn03" onclick="viewTab('alarmlog')">알림 로그</a></li>
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:set var="importUrl" value="" />
		<c:choose>
			<c:when test="${frmTy == 'log' }">
				<c:set var="importUrl" value="/SiteManager/cms/menu/log/log_list.do" />
			</c:when>
			<c:when test="${frmTy == 'error' }">
				<c:set var="importUrl" value="/SiteManager/cms/menu/log/logError_list.do" />
			</c:when>
			<c:when test="${frmTy == 'blocklog' }">
				<c:set var="importUrl" value="/SiteManager/cms/blacklist/blockLog.do" />
			</c:when>
			<c:when test="${frmTy == 'alarmlog' }">
				<c:set var="importUrl" value="/SiteManager/cms/menu/log/alarmLog_list.do" />
			</c:when>
		</c:choose>
		
		<c:if test="${importUrl != '' }">
			<c:import url="${importUrl }" >
				<c:param name="queryString" value="${queryString}" />
			</c:import>
		</c:if>
	</div>
</div>	