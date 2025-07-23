<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="groupfrm" name="groupfrm" method="post" action="<c:url value="${managerDir }/cms/link/group/group_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="lgp_sn" 		name="lgp_sn" />
<input type="hidden" id="lgp_nm" 		name="lgp_nm" />
<input type="hidden" id="lgp_sort" 		name="lgp_sort" />
<input type="hidden" id="lgp_se" 		name="lgp_se" />
<input type="hidden" id="lgp_sttus" 	name="lgp_sttus" />

<div class="board_area">
		<table class="board_table">
		<thead>
			<tr>
				<th scope="col" class="wp8">번호</th>
				<th scope="col">분류명</th>
				<th scope="col">분류 구분</th>
				<th scope="col" class="wp12">사용유무</th>
				<th scope="col" class="wp8">순위</th>
				<th scope="col" class="wp15">관리</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>추가</td>
				<td><input type="text" id="lgp_nm_0" name="lgp_nm_0" maxlength="128" placeHolder="분류명 (한글)" /></td>
				<td><input type="text" id="lgp_se_0" name="lgp_se_0" maxlength="128" placeHolder="분류구분 (영문)" /></td>
				<td>
					<input id="lgp_sttus_1" name="lgp_sttus_0" type="radio" value="1" checked="checked" />
					<label for="lgp_sttus_1">사용</label>
					<input id="lgp_sttus_0" name="lgp_sttus_0" type="radio" value="0" />
					<label for="lgp_sttus_0">중지</label>
				</td>
				<td><input type="text" id="lgp_sort_0" name="lgp_sort_0" class="taC" maxlength="4" placeHolder="순서" /></td>
				<td>
					<a href="#self" class="btn" onclick="saveData(0, 4)">등록</a>
				</td>
			</tr>
		<c:forEach var="vo" items="${groupList}" varStatus="status">
			<tr>
				<td><c:out value="${vo.lgp_sn }" /></td>
				<td><input type="text" id="lgp_nm_<c:out value="${vo.lgp_sn }" />" name="lgp_nm_<c:out value="${vo.lgp_sn }" />" maxlength="128" value="<c:out value="${vo.lgp_nm }"/>" /></td>
				<td><input type="text" id="lgp_se_<c:out value="${vo.lgp_sn }" />" name="lgp_se_<c:out value="${vo.lgp_sn }" />" maxlength="128" value="<c:out value="${vo.lgp_se }"/>" /></td>
				<td>
					<input id="lgp_sttus_1_<c:out value="${vo.lgp_sn }" />" name="lgp_sttus_<c:out value="${vo.lgp_sn }" />" type="radio" value="1" <c:if test="${vo.lgp_sttus == 1 }">checked="checked"</c:if> />
					<label for="lgp_sttus_1_<c:out value="${vo.lgp_sn }" />">사용</label>
					<input id="lgp_sttus_0_<c:out value="${vo.lgp_sn }" />" name="lgp_sttus_<c:out value="${vo.lgp_sn }" />" type="radio" value="0" <c:if test="${vo.lgp_sttus == 0 }">checked="checked"</c:if> />
					<label for="lgp_sttus_0_<c:out value="${vo.lgp_sn }" />">중지</label>
				</td>
				<td><input type="text" id="lgp_sort_<c:out value="${vo.lgp_sn }" />" name="lgp_sort_<c:out value="${vo.lgp_sn }" />" maxlength="4" placeHolder="순서" value="<c:out value="${vo.lgp_sort }" />" class="taC"/></td>
				<td>
					<a href="#self" class="btn blue" onclick="saveData(<c:out value="${vo.lgp_sn }" />, 16)">수정</a>
					<a href="#self" class="btn pink" onclick="deleteData(<c:out value="${vo.lgp_sn }" />, 32)">삭제</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

</form>

<script>
function saveData(lgp_sn, cmd)
{
	$("#lgp_sn").val(lgp_sn);
	$("#lgp_nm").val($("#lgp_nm_"+lgp_sn).val());
	$("#lgp_sort").val($("#lgp_sort_"+lgp_sn).val());
	$("#lgp_se").val($("#lgp_se_"+lgp_sn).val());
	$("#lgp_sttus").val($("input[name='lgp_sttus_"+lgp_sn+"']:checked").val());

	if($("#site_code").val() == "") {
		alert("사이트를 선택하여 주십시요"); 
		return false;
	}
	if($("#lgp_nm").val() == "") {
		alert("분류명을 입력하여 주십시요"); 
		$("#lgp_nm").select();
		return false;
	}
	if($("#lgp_se").val() == "") {
		alert("분류 구분 입력하여 주십시요"); 
		$("#lgp_se").select();
		return false;
	}
	
	$("#cmd").val(cmd);
	$("#groupfrm").submit();
}

function deleteData(lgp_sn, cmd)
{
	if(confirm("삭제 하시겠습니까?")) {
		$("#lgp_sn").val(lgp_sn);
		$("#cmd").val(cmd);
		$("#groupfrm").submit();
	}
}
</script>