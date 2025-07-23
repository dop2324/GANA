<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html lang="<c:out value="${htmlLang}" />">
<head>
	<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_head" />
	<title><c:out value="${siteName}" /></title>
</head>
<script>


</script>
<body class="popup_body">

<form id="groupFrm" name="groupFrm" method="post" action="<c:url value="${managerDir }/cms/board/info/group/group_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />
<input type="hidden" id="cmd"			name="cmd" 	/>

<input type="hidden" id="bgp_sn"		name="bgp_sn" />
<input type="hidden" id="bgp_nm"		name="bgp_nm" />
<input type="hidden" id="bgp_sort"		name="bgp_sort" />
<div class="board_area">
	<table class="board_table">
		<colgroup>
			<col class="wp15" />
			<col />
			<col class="wp15" />
			<col class="wp30" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">순서</th>
				<th scope="col">관리</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>추가</td>
				<td><input id="bgp_nm_0" name="bgp_nm_0" class="inp_txt wp99" maxlength="32" placeholder="분류명" /></td>
				<td><input id="bgp_sort_0" name="bgp_sort_0" class="inp_txt numberOnly wp99" maxlength="3" placeholder="순서" /></td>
				<td>
					<a href="#self" class="btn" onclick="saveData(0, 4)">저장</a>
				</td>
			</tr>
		<c:forEach var="vo" items="${groupList}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td>
					<input id="bgp_nm_<c:out value="${vo.bgp_sn}" />" name="bgp_nm_<c:out value="${vo.bgp_sn}" />" class="inp_txt wp99" maxlength="32" value="<c:out value="${vo.bgp_nm}" />" placeholder="분류명" />
				</td>
				<td>
					<input id="bgp_sort_<c:out value="${vo.bgp_sn}" />" name="bgp_sort_<c:out value="${vo.bgp_sn}" />" class="inp_txt numberOnly wp99" maxlength="3" value="<c:out value="${vo.bgp_sort}" />" placeholder="순서" />
				</td>
				<td>
					<a href="#self" class="btn blue" onclick="saveData(<c:out value="${vo.bgp_sn}" />, 16)">수정</a>
					<a href="#self" class="btn pink" onclick="deleteData(<c:out value="${vo.bgp_sn}" />, 32)">삭제</a>
				</td>
			</tr>	
		</c:forEach>
		</tbody>
	</table>
</div>

</form>

<script>
function saveData(bgp_sn, cmd)
{
	$("#bgp_sn").val(bgp_sn);
	$("#bgp_nm").val($("#bgp_nm_"+bgp_sn).val());
	$("#bgp_sort").val($("#bgp_sort_"+bgp_sn).val());
	
	if($("#bgp_nm").val() == "") {
		alert("분류명을 입력하십시요");
		return false;
	}
	
    $("#cmd").val(cmd);
	$("#groupFrm").submit();
}

function deleteData(bgp_sn, cmd)
{
	if(confirm("["+$("#bgp_nm_"+bgp_sn).val()+"]를 삭제 하시겠습니까?")) {
		$("#bgp_sn").val(bgp_sn);
		$("#cmd").val(cmd);
		$("#groupFrm").submit();
	}
}
</script>
</body>
</html>