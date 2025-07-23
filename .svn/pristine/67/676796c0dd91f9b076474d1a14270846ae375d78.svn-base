<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_head" />
	<title><c:out value="${siteName}" /></title>
</head>
<script>
var mnu_code = "<c:out value="${mnu_code}" />";
var parm_mnuCode;

function selectSite()
{
	location.href = "<c:out value="?mnu_code=${mnu_code}" escapeXml="false"  />&site_code="+$("#site_code").val();
} 

function viewTab(tabID) {
	var ref_mnuCode = d.aNodes[d.selectedNode].id;
	var ref_mnuNm = d.aNodes[d.selectedNode].name;
	
	$("#ref_mnuCode").val(ref_mnuCode);
	$("#ref_mnuNm").val(ref_mnuNm);
	
	viewPage();
}

function setRefPage()
{
	opener.selectRefPage($("#ref_mnuCode").val(), $("#ref_mnuNm").val());
	self.close();
}

function viewPage()
{
	if($("#ref_mnuCode").val() == "") {
		alert("메뉴를 선택하여 주십시요!");
        return false;
	}
	else
	{
		$.ajax({
			type:"post"
			, url:"/SiteManager/cms/menu/page/page_viewJson.do"
			, dataType:"json"
			, data: {
				mnu_code : mnu_code
				, parm_mnuCode : $("#ref_mnuCode").val()
			}
			, success:function(data)
			{
				console.log(data);
				if(data.result == 0) {
					$("#viewPageCn").html(data.message);
				}else{
					$("#viewPageTtl").text(data.page_ttl);
					$("#viewPageCn").html(data.page_cn);
				}
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
}
</script>

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
				&nbsp;
				<a href="#self" class="btn" onclick="selectSite()">조회</a>
			</form>
		</div>
		
		<div class="list_wrap list_tree">
			<c:if test="${fn:length(siteList) > 0 }">
				<c:import url="/SiteManager/cms/menu/page/page_tree.do?site_code=${site_code}" />
			</c:if>
		</div>
	</div>
	
	<div class="panel_right">
		<div class="board_search">
			<input type="text" class="inp_txt wp20" id="ref_mnuCode" name="ref_mnuCode" placeholder="메뉴 코드" />
			<input type="text" class="inp_txt wp20" id="ref_mnuNm" name="ref_mnuNm" placeholder="메뉴 명" />
			<a href="#self" onclick="setRefPage()">추가</a>
		</div>
			
		<div class="frm_wrap pr10">
		
			<div id="viewPageTtl"></div>
			<div class="wp100 h300 p10" id="viewPageCn" style="border:1px solid #e1e1e1; overflow: auto; white-space:pre-wrap;"></div>
			<%--
			<c:import url="/SiteManager/cms/menu/page/page_view.do" >
				<c:param name="queryString" 	value="${queryString}" />
			</c:import>
			 --%>
		</div>
	</div>	

</body>
</html>