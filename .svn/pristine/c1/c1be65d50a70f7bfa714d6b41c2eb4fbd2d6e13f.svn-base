<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="blackIpFrm" name="blackIpFrm" method="post" action="<c:url value="${managerDir }/cms/blacklist/blackListIp_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="bli_sn"		name="bli_sn" />
<input type="hidden" id="bli_startIP"	name="bli_startIP" />
<input type="hidden" id="bli_endIP"		name="bli_endIP" />
<input type="hidden" id="bli_rng"		name="bli_rng" />
<input type="hidden" id="bli_desc"		name="bli_desc" />
<input type="hidden" id="bli_sttus"		name="bli_sttus" />

<div class="board_area">
	<table class="board_table">
	<caption>관리자 접속 IP 설정</caption>
	<thead>
		<tr>
			<th scope="col" style="width:7%;">순번</th>
			<th scope="col" style="width:35%;">차단 IP 대역</th>
			<th scope="col">설명</th>
			<th scope="col" style="width:12%;">범위</th>
			<th scope="col" style="width:8%;">사용유무</th>
			<th scope="col" style="width:14%;">수정 ID/일시</th>
			<th scope="col" style="width:14%;">관리</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>추가</td>
			<td>
				<input id="bli_startIP_0" name="bli_startIP_0" type="text" class="wp40" maxlength="15" placeholder="시작IP" />
				 ~ 
				<input id="bli_endIP_0" 	name="bli_endIP_0" type="text" class="wp40" maxlength="15" placeholder="종료IP" />
			</td>
			<td><input id="bli_desc_0" name="bli_desc_0" type="text" class="inp_txt" maxlength="100" placeholder="설명" /></td>
			<td>
				<input id="bli_rng_0" name="bli_rng_0" type="radio" value="0" checked="checked" />
				<label for="bli_rng_0">사이트</label>
				<input id="bli_rng_1" name="bli_rng_0" type="radio" value="1" />
				<label for="bli_rng_1">전체</label>
			</td>
			<td><input id="bli_sttus_0" name="bli_sttus_0" type="checkbox" value="1" /></td>
			<td></td>
			<td>
				<a href="#self" class="btn blue" onclick="saveData(0, 4)">저장</a>
			</td>
		</tr>
	<c:forEach var="vo" items="${blackIPList}" varStatus="status">
		<tr>
			<td><c:out value="${status.count }" /></td>
			<td>
				<input id="bli_startIP_<c:out value="${vo.bli_sn }" />" type="text" class="wp40" maxlength="15" value="<c:out value="${vo.bli_startIP }" />" />
				 ~ 
				<input id="bli_endIP_<c:out value="${vo.bli_sn }" />" type="text" class="wp40" maxlength="15" value="<c:out value="${vo.bli_endIP }" />" />
			</td>
			<td><input id="bli_desc_<c:out value="${vo.bli_sn }" />" type="text" class="inp_txt" maxlength="100" value="<c:out value="${vo.bli_desc }" />" /></td>
			<td>
				<input id="bli_rng_0_<c:out value="${vo.bli_sn }" />" name="bli_rng_<c:out value="${vo.bli_sn }" />" type="radio" value="0" <c:if test="${vo.bli_rng == 0 }">checked="checked"</c:if> />
				<label for="bli_rng_0_<c:out value="${vo.bli_sn }" />">사이트</label>
				<input id="bli_rng_1_<c:out value="${vo.bli_sn }" />" name="bli_rng_<c:out value="${vo.bli_sn }" />" type="radio" value="1" <c:if test="${vo.bli_rng == 1 }">checked="checked"</c:if> />
				<label for="bli_rng_1_<c:out value="${vo.bli_sn }" />">전체</label>
			</td>
			<td>
				<input id="bli_sttus_<c:out value="${vo.bli_sn }" />" type="checkbox" value="1" <c:if test="${vo.bli_sttus == 1 }">checked="checked"</c:if> />
			</td>
			<td>
				<p><c:out value="${vo.bli_mdfcnID }" /></p>
				<p><fmt:formatDate value="${vo.bli_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
			</td>
			<td>
				<a href="#self" class="btn" onclick="saveData(<c:out value="${vo.bli_sn }" />, 16)">수정</a>
				<a href="#self" class="btn pink" onclick="saveData(<c:out value="${vo.bli_sn }" />, 32)">삭제</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>

</form>

<script>
function saveData(bli_sn, cmd)
{
	$("#bli_sn").val(bli_sn);
	$("#bli_startIP").val($("#bli_startIP_"+bli_sn).val());
	$("#bli_endIP").val($("#bli_endIP_"+bli_sn).val());
	$("#bli_rng").val($("input[name='bli_rng_"+bli_sn+"']:checked").val());
	$("#bli_desc").val($("#bli_desc_"+bli_sn).val());
	$("#bli_sttus").val($("#bli_sttus_"+bli_sn).is(":checked") ? 1:0);

	if($("#site_code").val() == "") {
		alert("사이트를 선택하여 주십시요"); 
		return false;
	}
	if($("#bli_startIP").val() == "" || !validateIP($("#bli_startIP").val())) {
		alert("유효한 ip주소가 아닙니다."); 
		$("#bli_startIP").select();
		return false;
	}
	if($("#bli_endIP").val() == "" || !validateIP($("#bli_endIP").val())) {
		alert("유효한 ip주소가 아닙니다."); 
		$("#bli_endIP").select();
		return false;
	}
	
	$("#cmd").val(cmd);
	$("#blackIpFrm").submit();
}

function deleteData(bli_sn, cmd)
{
	var bli_startIP	= $("#bli_startIP"+bli_sn).val();
	var bli_endIP	= $("#bli_endIP"+bli_sn).val();
	
	if(confirm("[" + bli_startIP + " ~ " + bli_endIP + "]를 삭제 하시겠습니까?")) {
		$("#bli_sn").val(bli_sn);
		$("#cmd").val(cmd);
		$("#blackIpFrm").submit();
	}
}
</script>