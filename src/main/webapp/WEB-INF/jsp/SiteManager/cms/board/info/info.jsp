<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


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
			<c:import url="/SiteManager/cms/board/info/board_tree.do?site_code=${site_code}" />
		</c:if>
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab_board" 		<c:if test="${frmTy == 'board'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('board')">게시판 관리</a></li>
			<li id="tab_info" 		<c:if test="${frmTy == 'info'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('info')">게시판 설정</a></li>
			<li id="tab_permission" <c:if test="${frmTy == 'permission'}">class="on"</c:if>><a href="#self" id="tabBtn03" onclick="viewTab('permission')">권한설정</a></li>				
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:set var="importUrl" value="" />
		<c:if test="${parm_mnuCode != '' }">
		<c:choose>
			<c:when test="${frmTy == 'permission' }">
				<c:set var="importUrl" value="/SiteManager/cms/permission/permission_list.do" />
			</c:when>
			<c:when test="${frmTy == 'board'}">
				<c:set var="importUrl" value="/SiteManager/cms/board/board.do" />
			</c:when>
			<c:otherwise>
				<c:set var="importUrl" value="/SiteManager/cms/board/info/info_form.do" />
			</c:otherwise>
		</c:choose>
		
		<c:if test="${importUrl != '' }">
			<c:import url="${importUrl }" >
				<c:param name="queryString" value="${queryString}" />
			</c:import>
		</c:if>
		</c:if>
	</div>
</div>

<script>
var mnu_code = "<c:out value="${mnu_code}" />";
var parm_mnuCode = "<c:out value="${parm_mnuCode}" />";
var frmTy = "<c:out value="${frmTy}" />";

function selectSite()
{
	location.href = "<c:out value="?mnu_code=${mnu_code}" escapeXml="false"  />&site_code="+$("#site_code").val();
}   

function viewTab(tabID) {

	if(tabID == "") tabID = this.frmTy;
	
	if(this.parm_mnuCode == "") {
		if(d.selectedNode != null) {
			this.parm_mnuCode = d.aNodes[d.selectedNode].id;
		}
		if(this.parm_mnuCode == "") {
			alert("메뉴를 선택하세요!");
			return;
		}
	}
	
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	var ifrmLink = ifrmLink = "./page.do?frmTy="+tabID+"&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&parm_mnuCode="+this.parm_mnuCode;
	location.href = ifrmLink;
	
	this.frmTy = tabID;
}
</script>