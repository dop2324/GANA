<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:forEach var="dept" items="${boardDeptList}" varStatus="status">
<div class="boardView">
	<dl class="title">
		<dd>
			<dl class="data">
				<dt>부서</dt>
				<dd><c:out value="${dept.dept_nm }" /></dd>

				<dt>담당자</dt>
				<dd><c:out value="${dept.user_nm }" /></dd>
				<dt>처리현황</dt>
				<dd>
					<c:choose>
						<c:when test="${dept.dept_sttus == 2 }">접수</c:when>
						<c:when test="${dept.dept_sttus == 4 }">담당자지정</c:when>
						<c:when test="${dept.dept_sttus == 5 }">완료</c:when>
						<c:when test="${dept.dept_sttus == 6 }">접수 불가</c:when>
						<c:otherwise>신청</c:otherwise>
					</c:choose>
				</dd>
				<dt class="b">답변일자</dt>
				<dd>
					<fmt:formatDate value="${dept.dept_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
				</dd>
			</dl>
		</dd>

	<%-- 첨부파일 --%>
	<c:if test="${fn:length(dept.boardDeptFileList) > 0 }">
		<dd>
			<ul>
				<li>
				<c:forEach var="file" items="${dept.boardDeptFileList}" varStatus="f">
					<p class="file">
						<span <c:if test="${f.count > 1 }">class="pl10"</c:if>>
						<a href="<c:url value="/boardDept_download.do?mnu_code=${boardMap.mnu_code }&file_sn=${file.file_sn }" />" title="<c:out value="${file.file_desc }" />"> <c:out value="${file.file_srcFileNm}"/></a>
						
						<c:if test="${SynapUseYn == 'Y' }">
							<span class="pl5"><a title="새창" href="#self" onclick="openDeptViewFiles(${file.file_sn })">[미리보기]</a></span>
						</c:if>
						</span>
					</p>
				</c:forEach>
				</li>
			</ul>
		</dd>
	</c:if>
	</dl>
	
	<div class="cont">
		<div id="bbs_content" class="board_content">
			<c:out value="${dept.dept_cn }" escapeXml="false" />
		</div>		
	</div>
</div>

</c:forEach>