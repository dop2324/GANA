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

	if($("#parm_mnuCode").val() == ref_mnuCode) {
		alert("같은 게시판은 이동 불가능 합니다");
		return false;
	}
	
	$("#move_mnuCode").val(ref_mnuCode);
	$("#moveMenuNm").val(ref_mnuNm);
}

function saveData()
{
	if($("#move_mnuCode").val() == "")
	{
		alert("이동하려는 메뉴를 선택하세요.!");
		return false;
	}
	
	$("#moveFrm").submit();
}
</script>

<body class="p20">

	<div class="panel_left" style="width:280px;">
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
				<a href="#self" class="bt3 srch" onclick="selectSite()">조회</a>
			</form>
		</div>
		
		<div class="list_wrap list_tree">
			<c:if test="${fn:length(siteList) > 0 }">
				<c:import url="/SiteManager/cms/board/info/board_tree.do?site_code=${site_code}" />
			</c:if>
		</div>
	</div>
	
	<div class="panel_right" style="width:500px;">
		<div class="frm_wrap pt30">
		<form id="moveFrm" name="moveFrm" method="post" action="<c:url value="${managerDir }/cms/board/move/move_process.do" />">
		<double-submit:preventer/>
		<input type="hidden" id="site_code" 	name="site_code" 		value="<c:out value="${site_code }" />" />
		<input type="hidden" id="mnu_code"		name="mnu_code"			value="<c:out value="${mnu_code }" />" />
		<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"		value="<c:out value="${parm_mnuCode }" />" />
		
		<input type="hidden" id="returnUrl" 	name="returnUrl" 		value="<c:out value="${returnUrl }" />" />
		<input type="hidden" id="queryString"	name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
		<input type="hidden" id="cmd"			name="cmd" 				value="<c:out value="${cmd}" />"/>
		
		<input type="hidden" id="bod_sn"		name="bod_sn"			value="<c:out value="${bod_sn }" />" />
		<input type="hidden" id="checkBosSnStr"	name="checkBosSnStr" 	value="<c:out value="${checkBosSnStr }" />" />
		<input type="hidden" id="move_mnuCode"	name="move_mnuCode"		/>
		
		<table class="tbl_st_write">
			<colgroup>
				<col class="wp15" /><col />
			</colgroup>
			<tbody>
				<tr>
					<th>이동 메뉴</th>
					<td><input type="text" id="moveMenuNm" class="inp_txt" readonly="readonly" name="moveMenuNm" /></td>
				</tr>
			</tbody>
		</table>
		</form>
		
		<div class="bt_wrap taC">
			<a href="#self" class="bt2 mod" onclick="saveData()">저장</a>
			<a href="#self" class="bt2 del" onclick="self.close();">취소</a>
		</div>
		</div>
	</div>	

</body>
</html>