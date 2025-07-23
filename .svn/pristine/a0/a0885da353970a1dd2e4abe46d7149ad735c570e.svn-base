<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="groupFrm" name="groupFrm" method="post" action="<c:url value="${managerDir }/cms/member/group/group_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"			name="mnu_code"			value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="cmd"				name="cmd" 				value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 		name="returnUrl" 		value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"		name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="updn"				name="updn" />

<div class="form_style">
	<dl>
		<dt><label for="grp_id">그룹 ID</label></dt>
		<dd>
			<input type="text" id="grp_id" name="grp_id" maxlength="32" <c:if test="${vo.grp_id != ''}">readonly="readonly"</c:if> value="<c:out value="${vo.grp_id }" />" placeholder="그룹 ID" />
		</dd>
		<dt><label for="grp_uprID">상위 그룹 ID</label></dt>
		<dd>
			<input type="text" id="grp_uprID" name="grp_uprID" maxlength="32" readonly="readonly" value="<c:out value="${vo.grp_uprID }" />" placeholder="상위 그룹 ID" />
		</dd>
		<dt><label for="grp_nm">그룹 이름</label></dt>
		<dd>
			<input type="text" id="grp_nm" name="grp_nm" maxlength="32" value="<c:out value="${vo.grp_nm }" />" placeholder="그룹 이름" />
		</dd>
		<dt><label for="grp_desc">그룹 설명</label></dt>
		<dd>
			<input type="text" id="grp_desc" name="grp_desc" maxlength="100" value="<c:out value="${vo.grp_desc }" />" placeholder="그룹 설명" />
		</dd>
		<dt>레벨</dt>
		<dd><c:out value="${vo.grp_level }" /></dd>
		<dt>정렬순서</dt>
		<dd><c:out value="${vo.grp_sort }" /></dd>
		<dt>사용유무</dt>
		<dd>
			<input id="grp_sttus_1" name="grp_sttus" type="radio" value="1" <c:if test="${vo.grp_sttus == 1 }">checked="checked"</c:if> />
			<label for="grp_sttus_1">사용</label>
			<input id="grp_sttus_0" name="grp_sttus" type="radio" value="0" <c:if test="${vo.grp_sttus == 0 }">checked="checked"</c:if> />
			<label for="grp_sttus_0">중지</label>
		</dd>
		<dt>최종수정 관리자 ID</dt>
		<dd><c:out value="${vo.grp_mdfcnID }" /></dd>
		<dt>최종수정 일시</dt>
		<dd><fmt:formatDate value="${vo.grp_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	
	<a href="#self" onclick="setOrder('UP')" >▲</a>
	<a href="#self" onclick="setOrder('DN')" >▼</a>
	
	<a href="#self" class="btn pink floatR " onclick="deleteData()" <c:if test="${vo != null}">disabled="disabled"</c:if> >삭제</a>
</div>

<script>
function saveData()
{
	if ($("#grp_id").val() == "") {
        alert("그룹 ID를 입력하세요!");
        $("#grp_id").select();
        return false;
    }
	
    if ($("#grp_nm").val() == "") {
        alert("그룹 이름을 입력하세요!");
        $("#grp_nm").select();
        return false;
    }
	
    $("#groupFrm").submit();
} 

function deleteData()
{
	if ($("#grp_id").val() == "") {
        alert("삭제할 그룹 ID를 선택하여 주십시요!");
        return false;
    }

	if(confirm("현재 그룹을 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#groupFrm").submit();
	}
}

function setOrder(updn)
{
	if ($("#grp_id").val() == "") {
        alert("순위 조정 그룹 ID를 선택하여 주십시요!");
        return false;
    }

	$("#updn").val(updn);
	$("#cmd").val(64);
	$("#groupFrm").submit();
}
</script>