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
	var ifrmLink = "?mnu_code="+this.mnu_code+"&srchGroup="+this.parm_grpId;
	location.href = ifrmLink;
	
	//$("#groupMem_id").val(parm_grpId);
	//$("#groupMem_nm").val(parm_grpNm);
}

function setGroupUser()
{
	self.close();
	opener.setGroupMember($("#groupMem_ty").val(), $("#groupMem_id").val(), $("#groupMem_nm").val());
	
}


</script>
<body class="popup_body">
	<div class="panel_left">
		<div class="list_wrap list_tree">
			<c:import url="/SiteManager/cms/member/group/group_tree.do" />
		</div>
	</div>
	
	<div class="panel_right">
		<div class="board_search">
			<input type="text" class="inp_txt wp20" id="groupMem_ty" name="groupMem_ty" value="그룹" />
			<input type="text" class="inp_txt wp20" id="groupMem_id" name="groupMem_id" value="<c:out value="${groupVo.grp_id }" />" />
			<input type="text" class="inp_txt wp20" id="groupMem_nm" name="groupMem_nm" value="<c:out value="${groupVo.grp_nm }" />" />
			<a href="#self" onclick="setGroupUser()">추가</a>
		</div>
		
		<div class="frm_wrap pr10">
			<c:import url="/SiteManager/cms/member/groupMember_list.do">
				<c:param name="queryString" 	value="${queryString}" />
			</c:import>
		</div>
	</div>

</body>
</html>