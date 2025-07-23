<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="blackUserFrm" name="blackUserFrm" method="post" action="<c:url value="${managerDir }/cms/blacklist/blackListUser_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="blu_sn"		name="blu_sn" />
<input type="hidden" id="blu_id"		name="blu_id" />
<input type="hidden" id="blu_nm"		name="blu_nm" />
<input type="hidden" id="blu_rng"		name="blu_rng" />
<input type="hidden" id="blu_desc"		name="blu_desc" />
<input type="hidden" id="blu_sttus"		name="blu_sttus" />

<div class="board_area">
	<table class="board_table">
		<caption>관리자 접속 IP 설정</caption>
		<thead>
			<tr>
				<th scope="col" style="width:7%;">순번</th>
				<th scope="col" class="wp25">차단 사용자 ID</th>
				<th scope="col">설명</th>
				<th scope="col" style="width:15%;">범위</th>
				<th scope="col" style="width:10%;">사용유무</th>
				<th scope="col" style="width:15%;">수정 ID/일시</th>
				<th scope="col" style="width:10%;">관리</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>추가</td>
				<td>
					<input id="blu_id_0" name="blu_id_0" type="text" maxlength="256" placeholder="아이디, DI, CI" style="width:50%;" />
					<input id="blu_nm_0" name="blu_nm_0" type="text" maxlength="32" placeholder="이름" style="width:40%;" />
				</td>
				<td><input id="blu_desc_0" name="blu_desc_0" type="text" class="inp_txt" maxlength="100" placeholder="설명" /></td>
				<td>
					<input id="blu_rng_0" name="blu_rng_0" type="radio" value="0" checked="checked" />
					<label for="blu_rng_0">사이트</label>
					<input id="blu_rng_1" name="blu_rng_0" type="radio" value="1" />
					<label for="blu_rng_1">전체</label>
				</td>
				<td><input id="blu_sttus_0" name="blu_sttus_0" type="checkbox" value="1" /></td>
				<td></td>
				<td>
					<a href="#self" class="btn blue" onclick="saveData(0, 4)">저장</a>
				</td>
			</tr>
		<c:forEach var="vo" items="${blackUserList}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td>
					<input id="blu_id_<c:out value="${vo.blu_sn }" />" type="text" class="wp60" maxlength="256" value="<c:out value="${vo.blu_id }" />" />
					<input id="blu_nm_<c:out value="${vo.blu_sn }" />" type="text" class="wp30" maxlength="16" value="<c:out value="${vo.blu_nm }" />" />
				</td>
				<td><input id="blu_desc_<c:out value="${vo.blu_sn }" />" type="text" class="inp_txt" maxlength="100" value="<c:out value="${vo.blu_desc }" />" /></td>
				<td>
					<input id="blu_rng_0_<c:out value="${vo.blu_sn }" />" name="blu_rng_<c:out value="${vo.blu_sn }" />" type="radio" value="0" <c:if test="${vo.blu_rng == 0 }">checked="checked"</c:if> />
					<label for="blu_rng_0_<c:out value="${vo.blu_sn }" />">사이트</label>
					<input id="blu_rng_1_<c:out value="${vo.blu_sn }" />" name="blu_rng_<c:out value="${vo.blu_sn }" />" type="radio" value="1" <c:if test="${vo.blu_rng == 1 }">checked="checked"</c:if> />
					<label for="blu_rng_1_<c:out value="${vo.blu_sn }" />">전체</label>
				</td>
				<td>
					<input id="blu_sttus_<c:out value="${vo.blu_sn }" />" type="checkbox" value="1" <c:if test="${vo.blu_sttus == 1 }">checked="checked"</c:if> />
				</td>
				<td>
					<p><c:out value="${vo.blu_mdfcnID }" /></p>
					<p><fmt:formatDate value="${vo.blu_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
				</td>
				<td>
					<a href="#self" class="btn" onclick="saveData(<c:out value="${vo.blu_sn }" />, 16)">수정</a>
					<a href="#self" class="btn pink" onclick="saveData(<c:out value="${vo.blu_sn }" />, 32)">삭제</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

</form>

<script>
function saveData(blu_sn, cmd)
{
	$("#blu_sn").val(blu_sn);
	$("#blu_id").val($("#blu_id_"+blu_sn).val());
	$("#blu_nm").val($("#blu_nm_"+blu_sn).val());
	$("#blu_rng").val($("input[name='blu_rng_"+blu_sn+"']:checked").val());
	$("#blu_desc").val($("#blu_desc_"+blu_sn).val());
	$("#blu_sttus").val($("#blu_sttus_"+blu_sn).is(":checked") ? 1:0);

	if($("#site_code").val() == "") {
		alert("사이트를 선택하여 주십시요"); 
		return false;
	}
	if($("#blu_id").val() == "") {
		alert("차단 아이디를 입력 하여 주십시요"); 
		$("#blu_id").select();
		return false;
	}
	if($("#blu_nm").val() == "") {
		alert("차단 사용자 이름을 입력 하여 주십시요"); 
		$("#blu_nm").select();
		return false;
	}
	
	$("#cmd").val(cmd);
	$("#blackUserFrm").submit();
}

function deleteData(blu_sn, cmd)
{
	var blu_id	= $("#blu_id_"+blu_sn).val();
	
	if(confirm("[" + blu_id + "]를 삭제 하시겠습니까?")) {
		$("#blu_sn").val(blu_sn);
		$("#cmd").val(cmd);
		$("#blackUserFrm").submit();
	}
}
</script>