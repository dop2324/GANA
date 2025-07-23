<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="deptFrm" name="deptFrm" method="post" enctype="multipart/form-data" action="<c:url value="${managerDir }/cms/board/dept/dept_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="bod_sn"		name="bod_sn"		value="<c:out value="${bod_sn }" />" />
<input type="hidden" id="dept_sn"		name="dept_sn"		value="<c:out value="${dept_sn }" />" />

<table class="tbl_st_write">
	<colgroup>
		<col class="wp15" /><col />
	</colgroup>
	<tbody>

		<tr>
			<th>부서</th>
			<td>
				<c:set var="dept_id" value="${DPT_ID }" />
				<c:set var="dept_nm" value="${DPT_NM }" />
				<c:if test="${deptVo != null }">
					<c:set var="dept_id" value="${deptVo.dept_id }" />
					<c:set var="dept_nm" value="${deptVo.dept_nm }" />
				</c:if>
				<input type="hidden" id="dept_id" name="dept_id" maxlength="32" value="<c:out value="${dept_id }" />" />
				<input type="text" id="dept_nm" name="dept_nm" class="inp_txt w200" maxlength="64" value="<c:out value="${dept_nm }" />" />
				<a href="#self" onclick="openDept();" class="bt2" >부서선택</a>
			</td>
		</tr>

		<tr>
			<th>작성자</th>
			<td>
				<c:set var="user_id" 	value="${USR_ID }" />
				<c:set var="user_nm" 	value="${USR_NM }" />
				<c:if test="${deptVo != null }">
					<c:set var="user_id" value="${deptVo.user_id }" />
					<c:set var="user_nm" value="${deptVo.user_nm }" />
				</c:if>
				<input type="hidden" id="user_id" name="user_id" maxlength="64" value="<c:out value="${user_id }" />" />
				<input type="text" id="user_nm" name="user_nm" class="inp_txt w200" maxlength="64" value="<c:out value="${user_nm }" />" />
			</td>
		</tr>
		
		<tr>
			<th>처리상태</th>
			<td>
				<c:set var="dept_sttus" value="0" />
				<c:if test="${deptVo != null }"><c:set var="dept_sttus" value="${deptVo.dept_sttus}" /></c:if>
				<input id="dept_sttus0" name="dept_sttus" class="inp_rad" type="radio" value="0" <c:if test="${dept_sttus == 0}">checked="checked"</c:if> /><label for="dept_sttus0">표시없음</label>
				<input id="dept_sttus1" name="dept_sttus" class="inp_rad" type="radio" value="1" <c:if test="${dept_sttus == 1}">checked="checked"</c:if> /><label for="dept_sttus1">신청</label>
				<input id="dept_sttus2" name="dept_sttus" class="inp_rad" type="radio" value="2" <c:if test="${dept_sttus == 2}">checked="checked"</c:if> /><label for="dept_sttus2">접수</label>

				<input id="dept_sttus4" name="dept_sttus" class="inp_rad" type="radio" value="4" <c:if test="${dept_sttus == 4}">checked="checked"</c:if> /><label for="dept_sttus4">담당자지정</label>
				<input id="dept_sttus5" name="dept_sttus" class="inp_rad" type="radio" value="5" <c:if test="${dept_sttus == 5}">checked="checked"</c:if> /><label for="dept_sttus5">완료</label>
				<input id="dept_sttus6" name="dept_sttus" class="inp_rad" type="radio" value="6" <c:if test="${dept_sttus == 6}">checked="checked"</c:if> /><label for="dept_sttus6">접수불가</label>
			</td>
		</tr>
	
		<tr>
			<th>내용</th>
			<td>
				<c:set var="dept_cn" value="" />
				<c:if test="${deptVo != null }"><c:set var="dept_cn" value="${deptVo.dept_cn }" /></c:if>
				<textarea id="dept_cn" name="dept_cn" rows="10" ><c:out value="${dept_cn }" escapeXml="false" /></textarea>
				<script>
				CKEDITOR.replace("dept_cn", {
					toolbar : "simple"
					, width : "90%"
					, height : "250"
				});
				</script>
			</td>
		</tr>
		
		<tr>
			<th>답변 사용유무</th>
			<td>
				<c:set var="dept_useYn" value="1" />
				<c:if test="${deptVo != null }"><c:set var="dept_useYn" value="${deptVo.dept_useYn}" /></c:if>
				<input id="dept_useYn1" name="dept_useYn" class="inp_rad" type="radio" value="1" <c:if test="${dept_useYn == 1}">checked="checked"</c:if> /><label for="dept_sttus1">사용</label>
				<input id="dept_useYn0" name="dept_useYn" class="inp_rad" type="radio" value="0" <c:if test="${dept_useYn == 0}">checked="checked"</c:if> /><label for="dept_sttus0">사용안함</label>
			</td>
		</tr>

		<tr>
			<th>
				첨부파일 (<c:out value="${infoVo.inf_fileSizeLmt }" />MB)
			</th>
			<td>
				<c:if test="${infoVo.inf_fileExt != '' }">
					<p class="red b">첨부파일 확장자(<c:out value="${infoVo.inf_fileExt }" />)만 업로드 가능합니다</p>
				</c:if>
				
				<c:set var="fileCnt" value="1" />
				<c:forEach var="fileVo" items="${deptVo.boardDeptFileList}" varStatus="status">
					<div class="h30 ">
						<input type="hidden" name="fileSn" value="<c:out value="${fileVo.file_sn }" />" />

						<span class="pl10"><c:out value="${fileVo.file_srcFileNm }" /></span>
						<span class="pl10"><input type="text" id="fileDesc" name="fileDesc" class="inp_txt w300" value="<c:out value="${fileVo.file_desc }" />" /></span>
						<span class="pl10">
							<input type="checkbox" id="deleteFile<c:out value="${fileVo.file_sn }" />" name="deleteFile" value="<c:out value="${fileVo.file_sn }" />" />
							<label for="deleteFile<c:out value="${fileVo.file_sn }" />">삭제</label>
						</span>
					</div>
					<c:set var="fileCnt" value="${fileCnt+1 }" />
				</c:forEach>

				<c:forEach var="val" begin="${fileCnt}" end="5" step="1" >
					<div>
					<input type="file" id="fileUpload_<c:out value="${val }" />" name="fileUpload_<c:out value="${val }" />" class="inp_txt w300" title="attachFile" />
					<input type="text" id="fileDescription_<c:out value="${val }" />" name="fileDescription_<c:out value="${val }" />" class="inp_txt w300" placeholder="파일 설명" />
					</div>
				</c:forEach>
			</td>
		</tr>
	</tbody>
</table>

</form>

<div class="bt_wrap taC">
	
	<a href="<c:out value="${viewLink }"/>" class="bt1 can">내용보기</a>
	<c:if test="${util:hasPermission(prmVal, 4)}">
	<a href="#self" class="bt2 mod" onclick="saveData()">저장</a>
	</c:if>
	<a href="<c:out value="${listLink }"/>" class="bt1 can">목록</a>
		
	<div class="fR">
		<c:if test="${deptVo != null && util:hasPermission(prmVal, 32) }">
			<a href="#self>" class="bt2 del" onclick="deleteDeptData()">삭제</a>
		</c:if>
	</div>

</div>


<script>
function saveData()
{
	if($("#dept_nm").val() == "") {
		alert("부서 명을 입력하여 주십시요");
		$("#dept_nm").select();
	    return false;
	}	
	if($("#user_nm").val() == "") {
		alert("담당자 명을 입력하여 주십시요");
		$("#user_nm").select();
	    return false;
	}
	
	$("#deptFrm").submit();
}

function deleteDeptData() {
	if (confirm("부서답변 글을 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#deptFrm").submit();
	}
}

function openDept()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/deptEmp.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popDeptEmp", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml)
{
	$("#dept_id").val(dept_id);
	$("#dept_nm").val(dept_nm);
	
	if(emp_nm != "") {
		$("#user_id").val(emp_id);
		$("#user_nm").val(emp_nm);
	}
}
</script>