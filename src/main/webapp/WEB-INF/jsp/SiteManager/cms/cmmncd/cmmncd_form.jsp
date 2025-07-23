<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="codeFrm" name="codeFrm" method="post" action="<c:url value="${managerDir }/cms/cmmncd/cmmncd_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="code_sn"		name="code_sn" 		value="<c:out value="${vo.code_sn }" />" />
<input type="hidden" id="code_uprSn"	name="code_uprSn" 	value="<c:out value="${vo.code_uprSn }" />" />

<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="updn"			name="updn" />

<div class="form_style type2">
	<dl>
		<dt><label for="code_nm">코드명</label></dt>
		<dd>
			<input type="text" id="code_nm" name="code_nm" maxlength="64" <c:if test="${vo.code_nm != ''}">readonly="readonly"</c:if> value="<c:out value="${vo.code_nm }" />" placeholder="코드명" />
		</dd>
		<dt><label for="code_val">코드값</label></dt>
		<dd>
			<input type="text" id="code_val" name="code_val" maxlength="32" value="<c:out value="${vo.code_val }" />" placeholder="코드값" />
		</dd>
		<dt><label for="code_ty">코드구분</label></dt>
		<dd><input type="text" id="code_ty" name="code_ty" maxlength="32" value="<c:out value="${vo.code_ty }" />" placeholder="코드구분" /></dd>
		<dt>레벨</dt>
		<dd><c:out value="${vo.code_level }" /></dd>
		<dt>정렬순서</dt>
		<dd><c:out value="${vo.code_sort }" /></dd>
		<dt>사용유무</dt>
		<dd>
			<input id="code_sttus_1" name="code_sttus" type="radio" value="1" <c:if test="${vo.code_sttus == 1 }">checked="checked"</c:if> />
			<label for="code_sttus_1">사용</label>
			<input id="code_sttus_0" name="code_sttus" type="radio" value="0" <c:if test="${vo.code_sttus == 0 }">checked="checked"</c:if> />
			<label for="code_sttus_0">중지</label>
		</dd>
		<dt>최종수정 일시</dt>
		<dd>
			<c:out value="${vo.code_mdfcnID }" /> / <fmt:formatDate value="${vo.code_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
		</dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	
	<c:if test="${vo.code_sn != 0}">
		<a href="#self" class="btn pink floatR " onclick="deleteData()">삭제</a>
	</c:if>
</div>

<script>
function saveData()
{
    if ($("#code_nm").val() == "") {
        alert("코드명을 입력하세요!");
        $("#code_nm").select();
        return false;
    }
	
    $("#codeFrm").submit();
} 

function deleteData()
{
	if ($("#code_sn").val() == "") {
        alert("삭제할 코드를 선택하여 주십시요!");
        return false;
    }

	if(confirm("현재 코드를 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#codeFrm").submit();
	}
}

function setOrder(updn)
{
	if ($("#code_sn").val() == "") {
        alert("순위 조정 코드를 선택하여 주십시요!");
        return false;
    }

	$("#updn").val(updn);
	$("#cmd").val(64);
	$("#codeFrm").submit();
}
</script>