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

<form id="fieldFrm" name="fieldFrm" method="post" action="<c:url value="${managerDir }/cms/board/info/field/field_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />
<input type="hidden" id="cmd"			name="cmd" 	/>
<input type="hidden" id="updn"			name="updn" />

<input type="hidden" id="bfd_id"			name="bfd_id" />
<input type="hidden" id="bfd_nm"			name="bfd_nm" />
<input type="hidden" id="bfd_width"			name="bfd_width" />
<input type="hidden" id="bfd_maxLen"		name="bfd_maxLen" />
<input type="hidden" id="bfd_isMandatory"	name="bfd_isMandatory" />
<input type="hidden" id="bfd_encYn"			name="bfd_encYn" />
<input type="hidden" id="bfd_sttus"			name="bfd_sttus" />

<div class="board_area">
	<table class="board_table">
	<colgroup>
		<col class="wp8" />
		<col class="wp15" />
		<col class="wp15" />
		<col class="wp8" />
		<col class="wp8" />
		<col class="wp5" />
		<col class="wp5" />
		<col class="wp5" />
		<col class="wp10" />
		<col class="wp14" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">필드 아이디</th>
			<th scope="col">필드 이름</th>
			<th scope="col">넓이 (px)</th>
			<th scope="col">최대 글자 수</th>
			<th scope="col">필수</th>
			<th scope="col">암호화</th>
			<th scope="col">사용</th>
			<th scope="col">순서</th>
			<th scope="col">관리</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td>추가</td>
			<td>
				<fmt:formatNumber var="fieldCnt" minIntegerDigits="3" value="${fn:length(fieldList)+1}" type="number"/>
				
				<input id="bfd_id_0" name="bfd_id_0" class="inp_txt wp99" maxlength="64" value="<c:out value="${parm_mnuCode }_${fieldCnt }" />" placeholder="필드 아이디" />
			</td>
			<td><input id="bfd_nm_0" name="bfd_nm_0" class="inp_txt wp99" maxlength="64" placeholder="필드 이름" /></td>
			<td><input id="bfd_width_0" name="bfd_width_0" class="inp_txt numberOnly wp99" maxlength="4" placeholder="넓이 (px)" /></td>
			<td><input id="bfd_maxLen_0" name="bfd_maxLen_0" class="inp_txt numberOnly wp99" maxlength="4" placeholder="최대 글자 수" /></td>
			<td><input type="checkbox" name="bfd_isMandatory_0" id="bfd_isMandatory_0" class="inp_chk" value="1" /></td>
			<td><input type="checkbox" name="bfd_encYn_0" id="bfd_encYn_0" class="inp_chk" value="1" /></td>
			<td><input type="checkbox" name="bfd_sttus_0" id="bfd_sttus_0" class="inp_chk" value="1" /></td>
			<td></td>
			<td>
				<a href="#self" class="btn" onclick="saveData(0, 4)">저장</a>
			</td>
		</tr>
	<c:forEach var="vo" items="${fieldList}" varStatus="status">
		<tr>
			<td><c:out value="${status.count }" /></td>
			<td><input id="bfd_id_<c:out value="${vo.bfd_id}" />" name="bfd_id_<c:out value="${vo.bfd_id}" />" class="inp_txt wp99" maxlength="64" value="<c:out value="${vo.bfd_id}" />" placeholder="필드 아이디" /></td>
			<td><input id="bfd_nm_<c:out value="${vo.bfd_id}" />" name="bfd_nm_<c:out value="${vo.bfd_id}" />" class="inp_txt wp99" maxlength="64" value="<c:out value="${vo.bfd_nm}" />" placeholder="필드 이름" /></td>
			<td><input id="bfd_width_<c:out value="${vo.bfd_id}" />" name="bfd_width_<c:out value="${vo.bfd_id}" />" class="inp_txt numberOnly wp99" value="<c:out value="${vo.bfd_width}" />" placeholder="넓이 (px)" /></td>
			<td><input id="bfd_maxLen_<c:out value="${vo.bfd_id}" />" name="bfd_maxLen_<c:out value="${vo.bfd_id}" />" class="inp_txt numberOnly wp99" value="<c:out value="${vo.bfd_maxLen}" />" placeholder="최대 글자 수" /></td>
			<td>
				<input type="checkbox" name="bfd_isMandatory_<c:out value="${vo.bfd_id}" />" id="bfd_isMandatory_<c:out value="${vo.bfd_id}" />" class="inp_chk" value="1" <c:if test="${vo.bfd_isMandatory == 1}"> checked="checked"</c:if> />
			</td>
			<td>
				<input type="checkbox" name="bfd_encYn_<c:out value="${vo.bfd_id}" />" id="bfd_encYn_<c:out value="${vo.bfd_id}" />" class="inp_chk" value="1" <c:if test="${vo.bfd_encYn == 1}"> checked="checked"</c:if> />
			</td>
			<td>
				<input type="checkbox" name="bfd_sttus_<c:out value="${vo.bfd_id}" />" id="bfd_sttus_<c:out value="${vo.bfd_id}" />" class="inp_chk" value="1" <c:if test="${vo.bfd_sttus == 1}"> checked="checked"</c:if> />
			</td>
			<td>
				<a href="#self" class="btn" onclick="setOrder('<c:out value="${vo.bfd_id}" />', 'UP')" >▲</a>
				<a href="#self" class="btn" onclick="setOrder('<c:out value="${vo.bfd_id}" />', 'DN')" >▼</a>
			</td>
			<td>
				<a href="#self" class="btn blue" onclick="saveData('<c:out value="${vo.bfd_id}" />', 16)">수정</a>
				<a href="#self" class="btn pink" onclick="deleteData('<c:out value="${vo.bfd_id}" />', 32)">삭제</a>
			</td>
		</tr>	
	</c:forEach>
	</tbody>

</table>

</form>

<script>
function saveData(bfd_id, cmd)
{
	$("#bfd_id").val($("#bfd_id_"+bfd_id).val());
	$("#bfd_nm").val($("#bfd_nm_"+bfd_id).val());
	$("#bfd_width").val($("#bfd_width_"+bfd_id).val());
	$("#bfd_maxLen").val($("#bfd_maxLen_"+bfd_id).val());
	$("#bfd_isMandatory").val($("#bfd_isMandatory_"+bfd_id).is(":checked") ? 1:0);
	$("#bfd_encYn").val($("#bfd_encYn_"+bfd_id).is(":checked") ? 1:0);
	$("#bfd_sttus").val($("#bfd_sttus_"+bfd_id).is(":checked") ? 1:0);
	
	if($("#bfd_nm").val() == "") {
		alert("필드 이름을 입력하세요!");
		return false;
	}
	if($("#bfd_width").val() == "") {
		alert("넓이는 숫자를 입력하세요!");
		return false;
	}
	if($("#bfd_maxLen").val() == "") {
		alert("최대 글자수를 숫자 0~256 사이의 값으로 입력하세요!");
		return false;
	}
	
    $("#cmd").val(cmd);
	$("#fieldFrm").submit();
}

function setOrder(bfd_id, updn)
{
	$("#bfd_id").val(bfd_id);
	$("#updn").val(updn);
	$("#cmd").val(64);
	$("#fieldFrm").submit();
}

function deleteData(bfd_id, cmd)
{
	if(confirm("["+$("#bfd_nm_"+bfd_id).val()+"]를 삭제 하시겠습니까?")) {
		$("#bfd_id").val(bfd_id);
		$("#cmd").val(cmd);
		$("#fieldFrm").submit();
	}
}
</script>
</body>
</html>