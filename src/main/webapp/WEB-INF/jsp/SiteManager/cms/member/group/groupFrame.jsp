<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_head" />
	<title><c:out value="${siteName}" /></title>
</head>
<script>
var mnu_code 		= "<c:out value="${mnu_code}" />";
var parm_grpId 		= "<c:out value="${parm_grpId}" />";
var clickedTabBtn;

function viewTab(tabID) {
	var parm_grpNm = d.aNodes[d.selectedNode].name;
	
	$("#groupMem_id").val(parm_grpId);
	$("#groupMem_nm").val(parm_grpNm);
}

function setGroupUser()
{
	opener.setGroupMember($("#groupMem_id").val(), $("#groupMem_nm").val());
	self.close();
}
</script>
<body class="p30">
<input type="text" class="inp_txt w150" id="groupMem_id" name="groupMem_id" value="" />
<input type="text" class="inp_txt w200" id="groupMem_nm" name="groupMem_nm" value="" />
<input type="button" class="bt2 mod" value="선택" onclick="setGroupUser()">


<div class="pt20">	
	<div class="list_wrap list_tree">
		<c:import url="/SiteManager/cms/member/group/group_tree.do" />
	</div>
</div>

</body>
</html>