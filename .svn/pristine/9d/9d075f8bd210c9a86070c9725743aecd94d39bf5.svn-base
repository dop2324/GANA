<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="accessipFrm" name="accessipFrm" method="post" action="<c:url value="${managerDir }/cms/accessip/accessip_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="ip_sn"			name="ip_sn" />
<input type="hidden" id="ip_startIP"	name="ip_startIP" />
<input type="hidden" id="ip_endIP"		name="ip_endIP" />
<input type="hidden" id="ip_desc"		name="ip_desc" />
<input type="hidden" id="ip_sttus"		name="ip_sttus" />

<div class="board_area">
	<table class="board_table">
		<caption>관리자 접속 IP 설정</caption>
		<thead>
			<tr>
				<th scope="col" style="width:7%;">순번</th>
				<th scope="col" style="width:35%;">IP 대역</th>
				<th scope="col">설명</th>
				<th scope="col" style="width:10%;">사용유무</th>
				<th scope="col" style="width:18%;">수정 ID/일시</th>
				<th scope="col" style="width:14%;">관리</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>추가</td>
				<td>
					<input id="ip_startIP_0" name="ip_startIP_0" type="text" class="inp_txt wp40" maxlength="15" placeholder="시작IP" />
					~ 
					<input id="ip_endIP_0" 	name="ip_endIP_0" type="text" class="inp_txt wp40" maxlength="15" placeholder="종료IP" />
				</td>
				<td><input id="ip_desc_0" name="ip_desc_0" type="text" class="inp_txt" maxlength="100" placeholder="설명"/></td>
				<td><input id="ip_sttus_0" name="ip_sttus_0" type="checkbox" value="1" /></td>
				<td></td>
				<td>
					<a href="#self" class="btn blue" onclick="saveData(0, 4)">저장</a>
				</td>
			</tr>
		<c:forEach var="vo" items="${accessipList}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td>
					<input id="ip_startIP_<c:out value="${vo.ip_sn }" />" type="text" class="inp_txt wp40" maxlength="15" value="<c:out value="${vo.ip_startIP }" />" />
					~ 
					<input id="ip_endIP_<c:out value="${vo.ip_sn }" />" type="text" class="inp_txt wp40" maxlength="15" value="<c:out value="${vo.ip_endIP }" />" />
				</td>
				<td><input id="ip_desc_<c:out value="${vo.ip_sn }" />" type="text" class="inp_txt" maxlength="100" value="<c:out value="${vo.ip_desc }" />" /></td>
				<td>
					<input id="ip_sttus_<c:out value="${vo.ip_sn }" />" type="checkbox" value="1" <c:if test="${vo.ip_sttus == 1 }">checked="checked"</c:if> />
				</td>
				<td>
					<p><c:out value="${vo.ip_mdfcnID }" /></p>
					<p><fmt:formatDate value="${vo.ip_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
				</td>
				<td>
					<a href="#self" class="btn" onclick="saveData(<c:out value="${vo.ip_sn }" />, 16)">수정</a>
					<a href="#self" class="btn pink" onclick="saveData(<c:out value="${vo.ip_sn }" />, 32)">삭제</a>
				</td>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
</div>

</form>

<script>
function saveData(ip_sn, cmd)
{
	$("#ip_sn").val(ip_sn);
	$("#ip_startIP").val($("#ip_startIP_"+ip_sn).val());
	$("#ip_endIP").val($("#ip_endIP_"+ip_sn).val());
	$("#ip_desc").val($("#ip_desc_"+ip_sn).val());
	$("#ip_sttus").val($("#ip_sttus_"+ip_sn).is(":checked") ? 1:0);

	if($("#ip_startIP").val() == "" || !validateIP($("#ip_startIP").val())) {
		alert("유효한 ip주소가 아닙니다."); 
		$("#ip_startIP").select();
		return false;
	}
	if($("#ip_endIP").val() != "") {
		if(!validateIP($("#ip_endIP").val())) {
			alert("종료IP 유효한 ip주소가 아닙니다."); 
			$("#ip_endIP").select();
			return false;
		}
	}
	
	$("#cmd").val(cmd);
	$("#accessipFrm").submit();
}

function deleteData(ip_sn, cmd)
{
	var ip_startIP	= $("#ip_startIP_"+ip_sn).val();
	var ip_endIP	= $("#ip_endIP_"+ip_sn).val();
	
	if(confirm("[" + ip_startIP + " ~ " + ip_endIP + "]를 삭제 하시겠습니까?")) {
		$("#ip_sn").val(ip_sn);
		$("#cmd").val(cmd);
		$("#accessipFrm").submit();
	}
}
</script>