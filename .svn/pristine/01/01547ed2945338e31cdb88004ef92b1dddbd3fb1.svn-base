<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:if test="${fn:length(boardDeptList) > 0 }">
	<tr>
		<th colspan="4"  class="bgeee taL">답변내용</th>
	</tr>
</c:if>
			
<c:forEach var="dept" items="${boardDeptList}" varStatus="status">
	<tr>
		<th>부서</th>
		<td><c:out value="${dept.dept_nm }" /></td>
		<th>담당자</th>
		<td><c:out value="${dept.user_nm }" /></td>
	</tr>
	<tr>
		<th>처리상태</th>
		<td>
			<c:choose>
				<c:when test="${dept.dept_sttus == 2 }">접수</c:when>
				<c:when test="${dept.dept_sttus == 4 }">담당자지정</c:when>
				<c:when test="${dept.dept_sttus == 5 }">완료</c:when>
				<c:when test="${dept.dept_sttus == 6 }">접수 불가</c:when>
				<c:otherwise>신청</c:otherwise>
			</c:choose>
		</td>
		<th>답변일</th>
		<td>
			<fmt:formatDate value="${dept.dept_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			(<c:out value="${dept.dept_regIP }" />)

			<c:if test="${dept.dept_mdfcnIP != null }">
				/ 수정 :
				<fmt:formatDate value="${dept.dept_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
				(<c:out value="${dept.dept_mdfcnIP }" />)
			</c:if>
		</td>
	</tr>
	<tr>
		<th>답변 사용유무</th>
		<td>
			<c:choose>
				<c:when test="${dept.dept_useYn == 1 }"><span class="blue">사용</span></c:when>
				<c:when test="${dept.dept_useYn == 0 }"><span class="red">사용안함</span></c:when>
			</c:choose>
		</td>
		<th></th>
		<td></td>
	</tr>
	<tr>
		<th>답변내용</th>
		<td colspan="3" class="line_h22"><c:out value="${dept.dept_cn }" escapeXml="false" /></td>
	</tr>
	<c:if test="${fn:length(dept.boardDeptFileList) > 0 }">
		<tr>
			<th>첨부파일</th>
			<td colspan="3" class="line_h22">
				<c:forEach var="file" items="${dept.boardDeptFileList}" varStatus="status">
					<span <c:if test="${status.count > 1 }">class="pl10"</c:if>>
					<img src="/Manager/img/common/ico_file.gif" alt="첨부파일 " />
					<a href="/boardDept_download.do?mnu_code=${mnu_code }&file_sn=${file.file_sn }" title="<c:out value="${file.file_desc }" />"><c:out value="${file.file_srcFileNm}" /></a>
					</span>
				</c:forEach>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="4" class="taC fs16 h40">
			<a href="<c:url value="?${queryString }bod_sn=${dept.bod_sn }&dept_sn=${dept.dept_sn }&cmd=${8+256 }" />" class="bt2">수정</a>
		</td>
	</tr>
	
</c:forEach>