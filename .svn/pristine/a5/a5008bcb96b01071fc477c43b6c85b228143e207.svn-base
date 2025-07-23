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
			<c:import url="/SiteManager/cms/menu/menu_tree.do?site_code=${site_code}" />
		</c:if>
	</div>
</div>

<div class="panel_right">
	<div class="tab_menu">
		<ul>
			<li id="tab_insert" 	<c:if test="${frmTy == 'insert'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('insert')">메뉴 등록</a></li>
			<li id="tab_update" 	<c:if test="${frmTy == 'update'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('update')">메뉴 수정</a></li>
			<li id="tab_permission" <c:if test="${frmTy == 'permission'}">class="on"</c:if>><a href="#self" id="tabBtn03" onclick="viewTab('permission')">권한설정</a></li>	
			<li id="tab_page" 		<c:if test="${frmTy == 'page'}">class="on"</c:if>><a href="#self" id="tabBtn04" onclick="viewTab('page')">페이지관리</a></li>				
		</ul>
	</div>
	
	<div class="frm_wrap pr10">
		<c:set var="importUrl" value="" />
		<c:choose>
			<c:when test="${frmTy == 'permission' }">
				<c:set var="importUrl" value="/SiteManager/cms/permission/permission_list.do" />
			</c:when>
			<c:when test="${frmTy == 'page' }">
				<c:set var="importUrl" value="/SiteManager/cms/menu/page/page_form.do" />
			</c:when>
			<c:otherwise>
				<c:set var="importUrl" value="/SiteManager/cms/menu/menu_form.do" />
			</c:otherwise>
		</c:choose>
		
		<c:if test="${importUrl != '' }">
			<c:import url="${importUrl }" >
				<c:param name="queryString" value="${queryString}" />
			</c:import>
		</c:if>
	</div>
</div>

<script>
var mnu_code = "<c:out value="${mnu_code}" />";
var parm_mnuCode = "<c:out value="${param.parm_mnuCode}" />";
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
	
	var ifrmLink = "";
	switch(tabID) {
	
		case "insert" :	
						ifrmLink = "./page.do?frmTy=insert&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&parm_mnuUprCode="+this.parm_mnuCode;
						break;
		case "update" :	
						ifrmLink = "./page.do?frmTy=update&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&parm_mnuCode="+this.parm_mnuCode;
						break;
		case "permission" :	
						ifrmLink = "./page.do?frmTy=permission&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&parm_mnuCode="+this.parm_mnuCode;
						break;
		case "page" :	
						ifrmLink = "./page.do?frmTy=page&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&parm_mnuCode="+this.parm_mnuCode;
						break;
	}
	location.href = ifrmLink;
	
	this.frmTy = tabID;
}
</script>