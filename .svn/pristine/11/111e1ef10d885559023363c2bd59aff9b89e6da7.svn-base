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
var parm_deptId 	= "<c:out value="${dept_id}" />";
var parm_deptNm;
var clickedTabBtn;

function viewTab(tabID) {
	parm_deptNm = d.aNodes[d.selectedNode].name;
	var ifrmLink = "?mnu_code="+this.mnu_code+"&dept_id="+this.parm_deptId;
	location.href = ifrmLink;
}

function setDeptEmp()
{
	var dept_id = $("#dept_id").val();
	var dept_nm = $("#dept_nm").val();
	var emp_id = $("#emp_id").val();
	var emp_nm = $("#emp_nm").val();
	var emp_telno = $("#emp_telno").val();
	var emp_moblphon = $("#emp_moblphon").val();
	var emp_eml = $("#emp_eml").val();
	
	opener.setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml);
	self.close();
}


</script>
<body class="popup_body">
	<div class="panel_left">
		<div class="list_wrap list_tree">
			<c:import url="/SiteManager/cms/org/dept/dept_tree.do" />
		</div>
	</div>
	
	<div class="panel_right">
		<div class="board_search">
			<input type="hidden" id="deptEmp_ty" name="deptEmp_ty" value="부서" />
			
			<input type="hidden" id="dept_id" name="dept_id" value="<c:out value="${deptVo.dept_id }" />" />
			<input type="text" class="inp_txt wp20" id="dept_nm" name="dept_nm" placeholder="부서 이름" value="<c:out value="${deptVo.dept_nm }" />" />
			
			<input type="hidden" id="emp_id" name="emp_id" />
			<input type="text" class="inp_txt wp15" id="emp_nm" name="emp_nm" placeholder="직원 이름" />
			<input type="text" class="inp_txt wp15" id="emp_telno" name="emp_telno" placeholder="직원 전화번호" />
			<input type="text" class="inp_txt wp15" id="emp_moblphon" name="emp_moblphon" placeholder="직원 휴대전화번호" />
			<input type="text" class="inp_txt wp20" id="emp_eml" name="emp_eml" placeholder="직원 이메일" />
			
			<a href="#self"onclick="setDeptEmp()">추가</a>
		</div>
		
		<div class="frm_wrap pr10">
			<c:import url="/SiteManager/cms/org/deptEmp_list.do">
				<c:param name="queryString" 	value="${queryString}" />
			</c:import>
		</div>
	</div>

</body>
</html>