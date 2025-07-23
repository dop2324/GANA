<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var mnu_code = "<c:out value="${mnu_code}" />";
var parm_grpId ;
var clickedTabBtn;

function selectSite()
{
	location.href = "<c:out value="?mnu_code=${mnu_code}" escapeXml="false"  />&srchGroup="+this.parm_grpId+"&site_code="+$("#site_code").val();
}  

function viewTab(tabID) {

	if(this.parm_grpId == "") {

		if(d.selectedNode != null) {
			this.parm_grpId = d.aNodes[d.selectedNode].id;
		}
		
		if(this.parm_grpId == "" ) {
			alert("그룹을 선택하세요!");
			return;
		}
	}
	
	var ifrmLink = "?mnu_code="+this.mnu_code+"&srchGroup="+this.parm_grpId;
	location.href = ifrmLink;
}
</script>
<div class="panel_left">
	<%--
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
			&nbsp;
			<a href="#self" class="btn" onclick="selectSite()">조회</a>
		</form>
	</div>
	 --%>
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/member/group/group_tree.do" />
	</div>
</div>

<div class="panel_right">

	<div class="tab_menu">
		<ul>
			<li id="tab01" class="on"><a href="<c:out value="${listLink }"/>" id="tabBtn01">회원 목록</a></li>		
		</ul>
	</div>

	<div class="frm_wrap pr10">
		<c:import url="/SiteManager/cms/member/initMember.do">
			<c:param name="queryString" 	value="${queryString}" />
		</c:import>
	</div>
</div>	