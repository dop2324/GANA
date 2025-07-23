<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="empFrm" name="empFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="dept_id"		name="dept_id"	value="<c:out value="${srchMap.dept_id }" />" />
	<input type="hidden" id="cmd"			name="cmd" />
	
		<div class="board_search">
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" />
			<a href="#self" onclick="searchData()">검색</a>
		</div>
	</form>
	
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col" style="width:7%;">번호</th>
				<th scope="col" style="width:10%;">부서이름</th>
				<th scope="col" style="width:10%;">이름</th>
				<th scope="col" style="width:10%;">직위</th>
				<th scope="col" style="width:13%;">전화번호</th>
				<th scope="col" style="width:13%;">휴대전화</th>
				<th scope="col">직원업무</th>	
				<th scope="col" style="width:8%;">선택</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="vo" items="${empList}" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><c:out value="${vo.dept_nm }"/></td>
					<td><c:out value="${vo.emp_nm }"/></td>
					<td><c:out value="${vo.emp_jbps }"/> / <c:out value="${vo.emp_jbgd }"/></td>
					<td><c:out value="${vo.emp_telno }"/></td>
					<td><c:out value="${vo.emp_moblphon }"/></td>
					<td class="taL p5"><c:out value="${util:htmlEncode(vo.emp_task) }" escapeXml="false" /></td>
					<td>
						<a href="#self" onclick="setEmp('<c:out value="${vo.dept_id }" />', '<c:out value="${vo.dept_nm }" />', '<c:out value="${vo.emp_id }" />', '<c:out value="${vo.emp_nm }" />', '<c:out value="${vo.emp_telno }" />', '<c:out value="${vo.emp_moblphon }" />', '<c:out value="${vo.emp_eml }" />')" class="btn">선택</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script>
function searchData()
{
	$("#empFrm").submit();
}

function setEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml)
{
	$("#deptEmp_ty").val("직원")
	$("#dept_id").val(dept_id);
	$("#dept_nm").val(dept_nm);
	$("#emp_id").val(emp_id);
	$("#emp_nm").val(emp_nm);
	$("#emp_telno").val(emp_telno);
	$("#emp_moblphon").val(emp_moblphon);
	$("#emp_eml").val(emp_eml);
}
</script>
