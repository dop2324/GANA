<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_head" />
	<title><c:out value="${siteName}" /></title>
</head>
<body class="popup_body">
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
	
	<div class="panel_right ">
		<div class="tab_menu">
			<ul>
				<li id="tab_public" <c:if test="${frmTy == 'public'}">class="on"</c:if>><a href="#self" id="tabBtn02" onclick="viewTab('public')">게시판 통합</a></li>
				<li id="tab_share" 	<c:if test="${frmTy == 'share'}">class="on"</c:if>><a href="#self" id="tabBtn01" onclick="viewTab('share')">게시판 공유</a></li>				
			</ul>
		</div>
		
		<div class="frm_wrap">

			<c:set var="importUrl" value="" />
			<c:choose>
				<c:when test="${frmTy == 'public' }">
					<c:set var="importUrl" value="/SiteManager/cms/board/info/share/public_list.do" />
				</c:when>
				<c:otherwise>
					<c:set var="importUrl" value="/SiteManager/cms/board/info/share/share_list.do" />
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
var bod_mnuCode = "<c:out value="${bod_mnuCode}" />";
var parm_mnuCode = "<c:out value="${parm_mnuCode}" />";;
var frmTy = "<c:out value="${frmTy}" />";

function selectSite()
{
	location.href = "<c:out value="?mnu_code=${mnu_code}" escapeXml="false"  />&site_code="+$("#site_code").val();
}

function viewTab(tabID) {

	if(tabID == "") tabID = this.frmTy;
	
	$(".shareCode").val(null);
	$(".shareNm").val(null);
	

	if(this.bod_mnuCode == this.parm_mnuCode) {
		alert("공유 설정할 게시판이 같은 메뉴 입니다");
		return false;
	}
	
	$(".tab_menu ul li").removeClass("on");
	$("#tab"+tabID).addClass("on");
	
	var ifrmLink = ifrmLink = "<c:out value="${returnUrl }" />?frmTy="+tabID+"&site_code="+$("#site_code").val()+"&mnu_code="+this.mnu_code+"&bod_mnuCode="+this.bod_mnuCode+"&parm_mnuCode="+this.parm_mnuCode;
	location.href = ifrmLink;
	
	this.frmTy = tabID;
}
</script>

</body>
</html>