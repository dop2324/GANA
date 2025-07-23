<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="deptFrm" name="deptFrm" method="post" action="<c:url value="${managerDir }/cms/org/dept/dept_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"			name="mnu_code"			value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="cmd"				name="cmd" 				value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 		name="returnUrl" 		value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"		name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="updn"				name="updn" />

<div class="form_style">
	<dl>
		<dt><label for="dept_id">부서 ID</label></dt>
		<dd>
			<input type="text" id="dept_id" name="dept_id" maxlength="32" <c:if test="${vo.dept_id != ''}">readonly="readonly"</c:if> value="<c:out value="${vo.dept_id }" />" placeholder="부서 ID" />
		</dd>
		<dt><label for="upper_deptID">상위 부서 ID</label></dt>
		<dd>
			<input type="text" id="upper_deptID" name="upper_deptID" maxlength="32" readonly="readonly" value="<c:out value="${vo.upper_deptID }" />" placeholder="상위 부서 ID" />
		</dd>
		<dt><label for="dept_nm">부서 이름</label></dt>
		<dd><input type="text" id="dept_nm" name="dept_nm" class="inp_txt wp50" maxlength="64" value="<c:out value="${vo.dept_nm }" />" placeholder="부서 이름" /></dd>
		<dt><label for="dept_fullNm">부서 전체 이름</label></dt>
		<dd><input type="text" id="dept_fullNm" name="dept_fullNm" class="inp_txt wp50" maxlength="256" value="<c:out value="${vo.dept_fullNm }" />" placeholder="부서 전체 이름" /></dd>
		<dt><label for="dept_telno">부서 전화</label></dt>
		<dd><input type="text" id="dept_telno" name="dept_telno" class="inp_txt wp30" maxlength="32" value="<c:out value="${vo.dept_telno }" />" placeholder="부서 전화" /></dd>
		<dt><label for="dept_fax">부서 팩스</label></dt>
		<dd><input type="text" id="dept_fax" name="dept_fax" class="inp_txt wp30" maxlength="32" value="<c:out value="${vo.dept_fax }" />" placeholder="부서 팩스" /></dd>
		<dt><label for="dept_biz">부서 업무</label></dt>
		<dd>
			<textarea id="dept_biz" name="dept_biz" class="wp80 h100" maxlength="2000" placeholder="부서 업무"><c:out value="${vo.dept_biz}" escapeXml="false" /></textarea>
		</dd>
		<dt><label for="dept_se">부서 구분</label></dt>
		<dd><input type="text" id="dept_se" name="dept_se" class="inp_txt wp10" maxlength="16" value="<c:out value="${vo.dept_se }" />" placeholder="부서 구분" /></dd>
		<dt><label for="dept_rank">레벨</dt>
		<dd><input type="text" id="dept_rank" name="dept_rank" class="inp_txt numberOnly wp10" maxlength="3" value="<c:out value="${vo.dept_rank }" />" placeholder="레벨" /></dd>
		<dt><label for="dept_sort">정렬순서</label></dt>
		<dd><input type="text" id="dept_sort" name="dept_sort" class="inp_txt numberOnly wp10" maxlength="3" value="<c:out value="${vo.dept_sort }" />" placeholder="정렬순서" /></dd>
		<dt>사용유무</dt>
		<dd>
			<input id="dept_useYn_1" name="dept_useYn" type="radio" value="1" <c:if test="${vo.dept_useYn == 1 }">checked="checked"</c:if> />
			<label for="dept_useYn_1">사용</label>
			<input id="dept_useYn_0" name="dept_useYn" type="radio" value="0" <c:if test="${vo.dept_useYn == 0 }">checked="checked"</c:if> />
			<label for="dept_useYn_0">중지</label>
		</dd>
		<dt>최종수정 일시</dt>
		<dd>
			<fmt:formatDate value="${vo.dept_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			
			<c:if test="${vo.dept_mdfcnDt != null }">
				/ <fmt:formatDate value="${vo.dept_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</c:if>
		</dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	
	<c:if test="${vo.dept_id != ''}">
		<a href="#self" class="btn pink floatR" onclick="deleteData()">삭제</a>
	</c:if>
</div>

<script>
function saveData()
{
	if ($("#dept_id").val() == "") {
        alert("부서 ID를 입력하세요!");
        $("#dept_id").select();
        return false;
    }
	
    if ($("#dept_nm").val() == "") {
        alert("부서 이름을 입력하세요!");
        $("#dept_nm").select();
        return false;
    }
	
    $("#deptFrm").submit();
} 

function deleteData()
{
	if ($("#dept_id").val() == "") {
        alert("삭제할 부서 ID를 선택하여 주십시요!");
        return false;
    }

	if(confirm("현재 부서를 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#deptFrm").submit();
	}
}

function setOrder(updn)
{
	if ($("#dept_id").val() == "") {
        alert("순위 조정 부서 ID를 선택하여 주십시요!");
        return false;
    }

	$("#updn").val(updn);
	$("#cmd").val(64);
	$("#groupFrm").submit();
}
</script>