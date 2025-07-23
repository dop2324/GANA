<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
$(function(){
	
	const allowedExtensions = "${param.allowedExtensions}".split('.').filter(ext => ext !== '');
	const maxFileSizeMB = parseInt("${param.posblAtchFileSize }");
	const maxFileSizeKB = maxFileSizeMB * 1024;
	
    function isValidExtension(fileName) {
        let fileExt = fileName.split('.').pop().toLowerCase();
        return allowedExtensions.includes(fileExt);
    }
	
	$('.input-file').on('change', function(){
		let file = this.files[0];
		if (file) {
			let fileName = file.name;
			let fileSize = file.size / 1024;
			let fileExt = fileName.split('.').pop();
			
			if (!isValidExtension(file.name)) {
				alert('허용되지 않은 확장자입니다.');
				$(this).val('');
				return false;
			}
			
			if (fileSize > maxFileSizeKB) {
				alert('파일 크기가 ' + maxFileSize + 'KB를 초과할 수 없습니다.');
				$(this).val('');
				return false;				
			}
		}
	});
});
</script>

<div class="board_desc">
	<ul>
		<li>첨부파일 최대 사이즈는 <strong><c:out value="${param.posblAtchFileSize }" /></strong>MB 입니다.</li>
		<li>첨부가능한 파일은 <strong><c:out value="${param.allowedExtensions }" /></strong> 입니다.</li>
		<li>첨부가능한 파일의 개수는 총 <strong><c:out value="${param.posblAtchFileNumber }" /></strong>개 입니다.</li>
	</ul>
</div>

<c:forEach var="val" begin="1" end="${param.posblAtchFileNumber}" step="1">
<div>
	<label class="hidden" for="atchFile_<c:out value="${val}" />"><c:out value="${val + 1}" />번 파일 첨부</label>
	<input type="file" id="atchFile_<c:out value="${val}" />" name="atchFile_<c:out value="${val }" />" class="input-file" title="attachFile" />
	<label class="hidden" for="atchFileCn_<c:out value="${val}" />"><c:out value="${val + 1}" />번 파일 설명</label>
	<input type="text" id="atchFileCn_<c:out value="${val}" />" name="atchFileCn_<c:out value="${val }" />" placeholder="파일 설명" />
</div>
</c:forEach>
