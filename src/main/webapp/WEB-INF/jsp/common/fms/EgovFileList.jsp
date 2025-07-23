<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script>
const mnu_code = "<c:out value='${mnu_code}' />";

	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/common/fms/FileDown.do?mnu_code="+mnu_code+"&atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}	
	
	function fn_egov_deleteFile(atchFileId, fileSn) {
		
		if(!confirm('<spring:message code="common.delete.msg" />')){
			return false;
		}
		
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].atchFileId) != "undefined" &&
					typeof(forms[i].fileSn) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}
		
		form.atchFileId.value = atchFileId;
		form.fileSn.value = fileSn;
		form.action = "<c:url value='/common/fms/deleteFileInfs.do'/>?mnu_code=<c:out value='${mnu_code}' />";
		form.submit();
	}
</script>
<input type="hidden" name="atchFileId" value="<c:out value='${decodedAtchFileId}'/>">
<input type="hidden" name="fileSn" >
<input type="hidden" name="fileListCnt" value="<c:out value='${fileListCnt}'/>">
<input type="hidden" name="returnUrl" value="<c:out value='${paramUrl }' />" >

<c:forEach var="fileVO" items="${fileList}" varStatus="status">
<div>
	<p class="file_name">
		<a href="#self" onclick="javascript:fn_egov_downFile('<c:out value="${atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
			<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileSize}"/>&nbsp;byte]
		</a>
		(<c:out value="${fileVO.fileCn}"/>)
	</p>
	<c:if test="${updateFlag=='Y'}">
		<button type="button" class="btn red" onclick="fn_egov_deleteFile('<c:out value="${decodedAtchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');">삭제</button>
	</c:if>
</div>
</c:forEach>

