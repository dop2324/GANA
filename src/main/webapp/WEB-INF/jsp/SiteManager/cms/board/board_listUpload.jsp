<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<table class="board_table">
	<caption class="cp_no"><c:out value="${infoVo.mnu_nm }" /> 게시판</caption>
	<thead>
		<tr>
			<th scope="col" class="wp5">번호</th>
			<th scope="col" class="wp10">미리보기</th>
			<th scope="col">파일크기</th>
			<th scope="col">파일명 / 파일경로</th>
			<th scope="col" class="wp10">주소복사</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="map" items="${boardLists}" varStatus="status">
		<tr>
			<td>
				<%--공지 --%>
				<c:choose>
					<c:when test="${map.bod_notice == 1 }">[공지]</c:when>
					<c:otherwise><fmt:formatNumber value="${no}" groupingUsed="true"/></c:otherwise>
				</c:choose>
			</td>
			<td></td>
			<td></td>
			<td class="taL b" colspan="2">
				<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2" />
				<a href="<c:out value="${viewLink}"/>" class="blue"><c:out value="${map.bod_ttl }"/></a>
			</td>		
		</tr>
		<%-- 첨부파일 목록 --%>
		<c:forEach var="file" items="${map.boardFileList}" varStatus="s">
		<tr>
			<td></td>
			<td>
				<c:if test="${file.file_ty == 1 }">
					<c:set var="imgFileNm" value="${file.file_phyFileNm }" />
					<c:if test="${file.file_thumbFileNm != '' }"><c:set var="imgFileNm" value="${file.file_thumbFileNm }" /></c:if>
					<div class="taC p10"><c:out value="${util:getImgTag('board', imgFileNm, 50, file.file_desc) }" escapeXml="false" /></div>
				</c:if>
			</td>
			<td><fmt:formatNumber value="${file.file_phyFileSize / 1024}" pattern="#.##" groupingUsed="true"/>KB</td>
			<td class="taL fs13">
				<p><c:out value="${file.file_srcFileNm}" /></p>
				<p id="file_${file.file_sn }"><c:out value="${util:getImgSrc('board', file.file_phyFileNm) }" /></p>
			</td>
			<td>
				<input type="button" class="bt2 mod" onclick="copyToClipboard('#file_${file.file_sn }');" value="복사" />
			</td>
		</tr>
		</c:forEach>
		
	<c:if test="${map.bod_notice != 1}"><c:set var="no" value="${no-1}" /></c:if>
	</c:forEach>
	</tbody>
</table>