<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="empFrm" name="empFrm" method="post" action="<c:url value="${managerDir }/cms/org/emp/emp_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="dept_id" 		name="dept_id" 		value="<c:out value="${dept_id }" />" />

<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="updn"			name="updn" />

<c:set var="dept_id" value="${deptVo.dept_id }" />
<c:set var="dept_nm" value="${deptVo.dept_nm }" />

<c:if test="${vo != null }">
	<c:set var="dept_id" value="${vo.dept_id }" />
	<c:set var="dept_nm" value="${vo.dept_nm }" />
</c:if>

<div class="form_style">
	<dl>
		<dt><label for="emp_id">직원 ID</label></dt>
		<dd>
			<input type="text" id="emp_id" name="emp_id" class="inp_txt wp40" maxlength="32" <c:if test="${vo != null}">readonly="readonly"</c:if> value="<c:out value="${vo.emp_id }" />" placeholder="직원 ID" />
		</dd>
		<dt><label for="emp_nm">직원 이름</label></dt>
		<dd>
			<input type="text" id="emp_nm" name="emp_nm" class="inp_txt wp40" maxlength="64" value="<c:out value="${vo.emp_nm }" />" placeholder="직원 이름" />
		</dd>
		<dt>직위 / 직급</dt>
		<dd class="grow">
			<input type="text" id="emp_jbps" name="emp_jbps" class="inp_txt wp10" maxlength="64" value="<c:out value="${vo.emp_jbps }" />" placeholder="직위" />
			
			<input type="text" id="emp_jbgd" name="emp_jbgd" class="inp_txt wp10" maxlength="64" value="<c:out value="${vo.emp_jbgd }" />" placeholder="직급" />
		</dd>
		<dt>근무형태</dt>
		<dd>
			<input id="emp_sttus_A" name="emp_sttus" type="radio" value="1" <c:if test="${vo.emp_sttus == 1 }">checked="checked"</c:if> />
			<label for="emp_sttus_A">A 형태</label>
			<input id="emp_sttus_B" name="emp_sttus" type="radio" value="0" <c:if test="${vo.emp_sttus == 0 }">checked="checked"</c:if> />
			<label for="emp_sttus_B">B 형태</label>
			<input id="emp_sttus_C" name="emp_sttus" type="radio" value="0" <c:if test="${vo.emp_sttus == 0 }">checked="checked"</c:if> />
			<label for="emp_sttus_C">C 형태</label>
		</dd>
		<dt><label for="emp_eml">이메일</label></dt>
		<dd><input type="text" id="emp_eml" name="emp_eml" class="inp_txt wp30" maxlength="256" value="<c:out value="${vo.emp_eml }" />" placeholder="이메일" /></dd>
		<dt><label for="emp_telno">전화번호</label></dt>
		<dd><input type="text" id="emp_telno" name="emp_telno" class="inp_txt wp30" maxlength="16" value="<c:out value="${vo.emp_telno }" />" placeholder="전화번호" /></dd>
	</tr>
	<tr>
		<dt><label for="emp_moblphon">휴대전화</label></dt>
		<dd><input type="text" id="emp_moblphon" name="emp_moblphon" class="inp_txt wp30" maxlength="16" value="<c:out value="${vo.emp_moblphon }" />" placeholder="휴대전화" /></dd>
		<dt><label for="emp_task">직원 업무</label></dt>
		<dd>
			<textarea id="emp_task" name="emp_task" class="wp80 h100" maxlength="2000" placeholder="직원 업무"><c:out value="${vo.emp_task}" escapeXml="false" /></textarea>
		</dd>
		<dt>사용유무</dt>
		<dd>
			<c:set var="emp_useYn" value="1" />
			<c:if test="${vo != null }"><c:set var="emp_useYn" value="${vo.emp_useYn}" /></c:if>
			<input id="emp_useYn_1" name="emp_useYn" type="radio" value="1" <c:if test="${emp_useYn == 1 }">checked="checked"</c:if> />
			<label for="emp_useYn_1">사용</label>
			<input id="emp_useYn_0" name="emp_useYn" type="radio" value="0" <c:if test="${emp_useYn == 0 }">checked="checked"</c:if> />
			<label for="emp_useYn_0">중지</label>
		</dd>
		<dt>최종수정 일시</dt>
		<dd>
			<fmt:formatDate value="${vo.emp_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			
			<c:if test="${vo.emp_mdfcnDt != null }">
				/ <fmt:formatDate value="${vo.emp_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</c:if>
		</dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	<c:if test="${vo != null}">
		<a href="#self" class="btn pink floatR" onclick="deleteData()">삭제</a>
	</c:if>
</div>

<script>
function saveData()
{
	if ($("#emp_id").val() == "") {
        alert("직원 ID를 입력하세요!");
        $("#emp_id").select();
        return false;
    }
	
    if ($("#emp_nm").val() == "") {
        alert("직원 이름을 입력하세요!");
        $("#emp_nm").select();
        return false;
    }
	
    $("#empFrm").submit();
} 

function deleteData()
{
	if ($("#emp_id").val() == "") {
        alert("삭제 할 직원을 선택하여 주십시요!");
        return false;
    }

	if(confirm("직원을 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#empFrm").submit();
	}
}
</script>