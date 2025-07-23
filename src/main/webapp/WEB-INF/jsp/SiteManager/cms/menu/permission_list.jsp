<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<div class="board_area">
		<form id="permFrm" name="permFrm" method="post" action="<c:url value="${managerDir }/cms/permission/permission_process.do" />">
		<double-submit:preventer/>
		<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
		<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
		<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
		<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

		<input type="hidden" id="prm_id"		name="prm_id"	/>
		<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />
		<input type="hidden" id="prm_alwAdm"	name="prm_alwAdm"	/>
		<input type="hidden" id="prm_alwDel"	name="prm_alwDel"	/>
		<input type="hidden" id="prm_alwUpd"	name="prm_alwUpd"	/>
		<input type="hidden" id="prm_alwRpl"	name="prm_alwRpl"	/>
		<input type="hidden" id="prm_alwIns"	name="prm_alwIns"	/>
		<input type="hidden" id="prm_alwSel"	name="prm_alwSel"	/>
		<input type="hidden" id="prm_alwLst"	name="prm_alwLst"	/>
		<input type="hidden" id="prm_dnyAdm"	name="prm_dnyAdm"	/>
		<input type="hidden" id="prm_dnyDel"	name="prm_dnyDel"	/>
		<input type="hidden" id="prm_dnyUpd"	name="prm_dnyUpd"	/>
		<input type="hidden" id="prm_dnyRpl"	name="prm_dnyRpl"	/>
		<input type="hidden" id="prm_dnyIns"	name="prm_dnyIns"	/>
		<input type="hidden" id="prm_dnySel"	name="prm_dnySel"	/>
		<input type="hidden" id="prm_dnyLst"	name="prm_dnyLst"	/>
		<input type="hidden" id="lowApply"		name="lowApply"  />

		<table class="board_table">
			<caption>권한 설정</caption>
			<thead>
				<tr>
					<th scope="col" rowspan="2" style="width:20%;">권한사용자<br/>(부서/직원/그룹/회원)</th>
					<th scope="col" colspan="8">권한</th>
					<th scope="col" rowspan="2" style="width:15%;">수정 ID / 일시</th>
					<th scope="col" rowspan="2" style="width:15%;">수정 / 삭제</th>
				</tr>
				<tr>
					<th scope="col">구분</th>
					<th scope="col">관리</th>
					<th scope="col">삭제</th>
					<th scope="col">수정</th>
					<th scope="col">답변</th>
					<th scope="col">쓰기</th>
					<th scope="col">보기</th>
					<th scope="col">목록</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td rowspan="2">
						<div>
							<input type="hidden" id="parm_deptEmpTy" name="parm_deptEmpTy" />
							<input type="hidden" id="parm_deptEmpId" name="parm_deptEmpId" />
							<input type="text" id="parm_deptEmpNm" name="parm_deptEmpNm" readonly="readonly" placeholder="부서/직원" style="width:calc(100% - 100rem)" />
							<c:set var="orgPopup" value="" />
							<c:choose>
								<c:when test="${orgType == 'saeol' }">
									<a href="#self" class="btn" onclick="openSaeolDept()">부서/직원</a>
								</c:when>
								<c:otherwise>
									<a href="#self" class="btn" onclick="openDept()">부서/직원</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div style="margin-top:5rem;">
							<input type="hidden" id="member_groupMemId" name="member_groupMemId" />
							<input id="member_groupMemName" name="member_groupMemName" type="text" readonly="readonly" placeholder="그룹/회원" style="width:calc(100% - 100rem)"  />
							<a href="#self" class="btn" onclick="openGroupMember()">그룹/회원</a>
						</div>
					</td>
					<td class="green">허용</td>
					<td><input id="parm_prm_alwAdm_0" name="parm_prm_alwAdm_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwDel_0" name="parm_prm_alwDel_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwUpd_0" name="parm_prm_alwUpd_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwRpl_0" name="parm_prm_alwRpl_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwIns_0" name="parm_prm_alwIns_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwSel_0" name="parm_prm_alwSel_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwLst_0" name="parm_prm_alwLst_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td rowspan="2"></td>
					<td rowspan="2">
						<input id="lowApply_0" name="lowApply_0" type="checkbox" value="1" class="inp_chk"> <label for="lowApply_0">하위적용</label><br />
						<a href="#self" class="btn blue" onclick="saveData(0, 4)">추가</a>
					</td>
				</tr>
				<tr class="bgf4">
					<td class="red">거부</td>
					<td><input id="parm_prm_dnyAdm_0" name="parm_prm_dnyAdm_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyDel_0" name="parm_prm_dnyDel_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyUpd_0" name="parm_prm_dnyUpd_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyRpl_0" name="parm_prm_dnyRpl_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyIns_0" name="parm_prm_dnyIns_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnySel_0" name="parm_prm_dnySel_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyLst_0" name="parm_prm_dnyLst_0" type="checkbox" value="1" onclick="setPermission(this)"></td>
				</tr>
				
			<c:forEach var="vo" items="${permissionList}" varStatus="status">
				<tr>
					<td rowspan="2">
						<p class="b blue fs18"><c:out value="${vo.org_type}" /></p> 
						<p><c:out value="${vo.org_name}" /> (<c:out value="${vo.prm_id}" />)</p>
					</td>
					<td class="green b">허용</td>
					<td><input id="parm_prm_alwAdm_${vo.prm_id}" name="parm_prm_alwAdm_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwAdm == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwDel_${vo.prm_id}" name="parm_prm_alwDel_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwDel == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwUpd_${vo.prm_id}" name="parm_prm_alwUpd_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwUpd == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwRpl_${vo.prm_id}" name="parm_prm_alwRpl_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwRpl == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwIns_${vo.prm_id}" name="parm_prm_alwIns_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwIns == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwSel_${vo.prm_id}" name="parm_prm_alwSel_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwSel == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_alwLst_${vo.prm_id}" name="parm_prm_alwLst_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_alwLst == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td rowspan="2" class="fs14">
						<c:out value="${vo.prm_mdfcnID }" /><br />
						<fmt:formatDate value="${vo.prm_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td rowspan="2">
						<c:if test="${vo.prm_id != 'GRP_000' }">
						<input id="lowApply_${vo.prm_id}" name="lowApply_${vo.prm_id}" type="checkbox" value="1" class="inp_chk"> <label for="lowApply_${vo.prm_id}">하위적용</label><br />
						<a href="#self" class="btn" onclick="saveData('${vo.prm_id}', 4)">수정</a>
						<a href="#self" class="btn pink" onclick="deleteData('${vo.prm_id}', 32)">삭제</a>
						</c:if>
					</td>
				</tr>
				<tr class="bgf4">
					<td class="red b">거부</td>
					<td><input id="parm_prm_dnyAdm_${vo.prm_id}" name="parm_prm_dnyAdm_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyAdm == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyDel_${vo.prm_id}" name="parm_prm_dnyDel_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyDel == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyUpd_${vo.prm_id}" name="parm_prm_dnyUpd_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyUpd == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyRpl_${vo.prm_id}" name="parm_prm_dnyRpl_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyRpl == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyIns_${vo.prm_id}" name="parm_prm_dnyIns_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyIns == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnySel_${vo.prm_id}" name="parm_prm_dnySel_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnySel == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
					<td><input id="parm_prm_dnyLst_${vo.prm_id}" name="parm_prm_dnyLst_${vo.prm_id}" type="checkbox" value="1" <c:if test="${vo.prm_dnyLst == 1 }">checked="checked"</c:if> onclick="setPermission(this)"></td>
				</tr>	
			</c:forEach>
			</tbody>
		</table>

		</form>
	</div>
</div>

<script>
function openDept()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/deptEmp.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popPermission", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function openSaeolDept()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/saeol/deptEmp.do?mnu_code=${mnu_code}" />";
	window.open(popupLink, "hierarchyWin", "width=300,height=600,resizable=yes,scrollbars=yes");
}

function setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_eml)
{
	$("#parm_deptEmpTy").val(emp_id == "" ? "dept":"emp");
	$("#parm_deptEmpId").val(emp_id == "" ? dept_id:emp_id);
	$("#parm_deptEmpNm").val(emp_id == "" ? dept_nm:emp_nm);
}

//그룹 회원
function openGroupMember()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/member/groupMember.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popPermission", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setGroupMember(groupMem_ty, groupMem_id, groupMem_name)
{
	$("#member_groupMemId").val(groupMem_id);
	$("#member_groupMemName").val(groupMem_ty+" : "+groupMem_id+" ("+groupMem_name+")");
	
	$("#parm_prmDept").val(null);
	$("#parm_prmDeptName").val(null);
}


function setPermission(cbxObj)
{
	var arrayAlwPrm = new Array("parm_prm_alwAdm","parm_prm_alwDel","parm_prm_alwUpd","parm_prm_alwRpl","parm_prm_alwIns","parm_prm_alwSel","parm_prm_alwLst");
	var arrayDnyPrm = new Array("parm_prm_dnyAdm","parm_prm_dnyDel","parm_prm_dnyUpd","parm_prm_dnyRpl","parm_prm_dnyIns","parm_prm_dnySel","parm_prm_dnyLst");

	var flag = false;
	var prmId = "";
	var chkVal = cbxObj.checked;

	for(var key in arrayAlwPrm)
	{
		if(cbxObj.id.indexOf(arrayAlwPrm[key]) == 0)
		{
			flag = true;
			prmId = cbxObj.id.replace(arrayAlwPrm[key], "");
		}

		if(flag)
		{
			document.getElementById(arrayAlwPrm[key] + prmId).checked = chkVal;
		}
	}

	flag = false;

	for(var key in arrayDnyPrm)
	{
		if(cbxObj.id.indexOf(arrayDnyPrm[key]) == 0)
		{
			flag = true;
			prmId = cbxObj.id.replace(arrayDnyPrm[key], "");
		}

		if(flag)
		{
			document.getElementById(arrayDnyPrm[key] + prmId).checked = chkVal;
		}
	}
}

function saveData(prm_id, cmd)
{
	var permissionID = prm_id;
	var parm_prmID = $("#parm_prmID").val()

	if(prm_id ==  0)
	{
		var parm_prmID = "";
		
		//부서 직원
		if($("#parm_deptEmpId").val() != "")  parm_prmID = $("#parm_deptEmpId").val();
		
		//그룹 회원
		if($("#member_groupMemId").val() != "") parm_prmID = $("#member_groupMemId").val();

		if (parm_prmID == "") {
	        alert("사용자 / 그룹을 선택하세요!");
	        return false;
    	}
		else
		{
			permissionID = parm_prmID;
		}
	}


	var parm_prm_alwAdm = document.getElementById("parm_prm_alwAdm_"+prm_id);
	var parm_prm_alwDel = document.getElementById("parm_prm_alwDel_"+prm_id);
	var parm_prm_alwUpd = document.getElementById("parm_prm_alwUpd_"+prm_id);
	var parm_prm_alwRpl = document.getElementById("parm_prm_alwRpl_"+prm_id);
	var parm_prm_alwIns = document.getElementById("parm_prm_alwIns_"+prm_id);
	var parm_prm_alwSel = document.getElementById("parm_prm_alwSel_"+prm_id);
	var parm_prm_alwLst = document.getElementById("parm_prm_alwLst_"+prm_id);
	var parm_prm_dnyAdm = document.getElementById("parm_prm_dnyAdm_"+prm_id);
	var parm_prm_dnyDel = document.getElementById("parm_prm_dnyDel_"+prm_id);
	var parm_prm_dnyUpd = document.getElementById("parm_prm_dnyUpd_"+prm_id);
	var parm_prm_dnyRpl = document.getElementById("parm_prm_dnyRpl_"+prm_id);
	var parm_prm_dnyIns = document.getElementById("parm_prm_dnyIns_"+prm_id);
	var parm_prm_dnySel = document.getElementById("parm_prm_dnySel_"+prm_id);
	var parm_prm_dnyLst = document.getElementById("parm_prm_dnyLst_"+prm_id);
	var lowApply		= document.getElementById("lowApply_"+prm_id);

	document.getElementById("prm_id").value 	= permissionID;
	document.getElementById("prm_alwAdm").value = parm_prm_alwAdm.checked ? 1 : 0;
	document.getElementById("prm_alwDel").value = parm_prm_alwDel.checked ? 1 : 0;
	document.getElementById("prm_alwUpd").value = parm_prm_alwUpd.checked ? 1 : 0;
	document.getElementById("prm_alwRpl").value = parm_prm_alwRpl.checked ? 1 : 0;
	document.getElementById("prm_alwIns").value = parm_prm_alwIns.checked ? 1 : 0;
	document.getElementById("prm_alwSel").value = parm_prm_alwSel.checked ? 1 : 0;
	document.getElementById("prm_alwLst").value = parm_prm_alwLst.checked ? 1 : 0;
	document.getElementById("prm_dnyAdm").value = parm_prm_dnyAdm.checked ? 1 : 0;
	document.getElementById("prm_dnyDel").value = parm_prm_dnyDel.checked ? 1 : 0;
	document.getElementById("prm_dnyUpd").value = parm_prm_dnyUpd.checked ? 1 : 0;
	document.getElementById("prm_dnyRpl").value = parm_prm_dnyRpl.checked ? 1 : 0;
	document.getElementById("prm_dnyIns").value = parm_prm_dnyIns.checked ? 1 : 0;
	document.getElementById("prm_dnySel").value = parm_prm_dnySel.checked ? 1 : 0;
	document.getElementById("prm_dnyLst").value = parm_prm_dnyLst.checked ? 1 : 0;
	document.getElementById("lowApply").value 		 = lowApply.checked ? 1 : 0;
	document.getElementById("cmd").value = cmd;
	document.getElementById("permFrm").submit();
}

function deleteData(prm_id, cmd)
{
	if(confirm("권한을 삭제 하시겠습니까?")) {
		document.getElementById("prm_id").value = prm_id;
		document.getElementById("lowApply").value = document.getElementById("lowApply_"+prm_id).checked ? 1 : 0;
		document.getElementById("cmd").value = cmd;
		document.getElementById("permFrm").submit();
	}
}
</script>