<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:set var="bhc_visibleNo" value="1" />
<c:set var="bhc_visibleGroup" value="0" />
<c:set var="bhc_visibleTtl" value="1"  />
<c:set var="bhc_visibleNm" value="1" />
<c:set var="bhc_visibleDept" value="0" />
<c:set var="bhc_visibleDate" value="1" />
<c:set var="bhc_visibleFile" value="1" />
<c:set var="bhc_visibleReadCnt" value="1" />
<c:if test="${infoVo != null }">
	<c:set var="boardHeadColumnVo" value="${infoVo.boardHeadColumnVo}" />
	<c:set var="bhc_visibleNo" 		value="${boardHeadColumnVo.bhc_visibleNo }" />
	<c:set var="bhc_visibleGroup" 	value="${boardHeadColumnVo.bhc_visibleGroup }" />
	<c:set var="bhc_visibleTtl" 	value="${boardHeadColumnVo.bhc_visibleTtl }"  />
	<c:set var="bhc_visibleNm" 		value="${boardHeadColumnVo.bhc_visibleNm }" />
	<c:set var="bhc_visibleDept" 	value="${boardHeadColumnVo.bhc_visibleDept }" />
	<c:set var="bhc_visibleDate" 	value="${boardHeadColumnVo.bhc_visibleDate }" />
	<c:set var="bhc_visibleFile" 	value="${boardHeadColumnVo.bhc_visibleFile }" />
	<c:set var="bhc_visibleReadCnt" value="${boardHeadColumnVo.bhc_visibleReadCnt }" />
</c:if>


<table class="board_table">
	<%-- caption --%>
	<c:set var="caption" value="${infoVo.mnu_nm } 게시판" />
	<c:if test="${bhc_visibleNo == 1 }">	<c:set var="caption" value="${caption} 번호" /></c:if>
	<c:if test="${bhc_visibleGroup == 1 }">	<c:set var="caption" value="${caption}, 분류" /></c:if>
	<c:if test="${bhc_visibleTtl == 1 }">	<c:set var="caption" value="${caption}, 제목" /></c:if>
	<c:if test="${bhc_visibleNm == 1 }">	<c:set var="caption" value="${caption}, 작성자" /></c:if>
	<c:if test="${bhc_visibleDept == 1 }">	<c:set var="caption" value="${caption}, 부서" /></c:if>
	<c:if test="${bhc_visibleDate == 1 }">	<c:set var="caption" value="${caption}, 작성일" /></c:if>
	<c:if test="${bhc_visibleFile == 1 }">	<c:set var="caption" value="${caption}, 파일" /></c:if>
	<c:if test="${bhc_visibleReadCnt == 1 }">
		<c:set var="caption" value="${caption}, ${infoVo.inf_skinType == 'reply' ? '답변':'조회' }" />
	</c:if>
	<caption><c:out value="${caption}" /> 구성되어 있습니다.</caption>
	
	<%-- thead --%>
	<c:set var="replyThClass" value="hit" />
	<c:if test="${bhc_visibleReadCnt == 1 && infoVo.inf_skinType == 'reply'}">
		<c:set var="replyThClass" value="answer" />
	</c:if>

	<thead>
		<tr>
			<c:if test="${bhc_visibleNo 	== 1 }"><th scope="col" class="num">번호</th></c:if>
			<c:if test="${bhc_visibleGroup 	== 1 && infoVo.inf_fncUsrGroup == 1}"><th scope="col" class="cate">분류</th></c:if>
			<c:if test="${bhc_visibleTtl 	== 1 }"><th scope="col" class="subject">제목</th></c:if>
			<c:if test="${bhc_visibleNm 	== 1 }"><th scope="col" class="writer">작성자</th></c:if>
			<c:if test="${bhc_visibleDept 	== 1 }"><th scope="col" class="writer">부서</th></c:if>
			<c:if test="${bhc_visibleDate	== 1 }"><th scope="col" class="date">작성일</th></c:if>
			<c:if test="${bhc_visibleFile 	== 1 }"><th scope="col" class="file">파일</th></c:if>
			<c:if test="${bhc_visibleReadCnt == 1 }">
				<th scope="col" class="<c:out value="${replyThClass }" />">
					<c:choose>
						<c:when test="${infoVo.inf_skinType == 'reply' || srchBodSkin == 'reply'}">답변</c:when>
						<c:otherwise>조회</c:otherwise>
					</c:choose>
				</th>
			</c:if>
		</tr>
	</thead>
	<tbody>
	<c:if test="${fn:length(boardLists) == 0 }">
		<tr>
			<c:set var="colspan" value="8" />
			<c:if test="${bhc_visibleNo != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleGroup != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleTtl != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleNm != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleDept != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleDate != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleFile != 1 }">	<c:set var="colspan" value="${colspan-1 }" /></c:if>
			<c:if test="${bhc_visibleReadCnt != 1 }"><c:set var="colspan" value="${colspan-1 }" /></c:if>
			<td colspan="${colspan }">게시물이 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="map" items="${boardLists}" varStatus="status">
		<tr>
		<%-- bhc_visibleNo --%>
		<c:if test="${bhc_visibleNo == 1 }">
			<td class="num">
			<%--공지 --%>
			<c:choose>
				<c:when test="${map.bod_notice == 1 }">[공지]</c:when>
				<c:otherwise><fmt:formatNumber value="${no}" groupingUsed="true"/></c:otherwise>
			</c:choose>
			</td>
		</c:if>
		
		<%--bhc_visibleGroup --%>
		<c:if test="${bhc_visibleGroup == 1 && infoVo.inf_fncUsrGroup == 1}">
			<td class="bgbar"><c:out value="${map.bgp_nm }"/></td>
		</c:if>
		
		<%-- bhc_visibleTtl --%>
		<c:if test="${bhc_visibleTtl == 1 }">
			<td class="taL subject">
				<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2" />
				
				<c:set var="linkTarget" value="" />
				<c:if test="${infoVo.inf_skinType == 'link' }">
					<c:set var="linkTarget" value="target='_balnk' title='새창열림'" />
					<c:set var="viewLink" value="${map.con_linkUrl}" />
				</c:if>
				<%--답변 --%>
				<c:if test="${map.bod_level > 0}">
					<c:forEach var="val" begin="1" end="${map.bod_level}" step="1" ></c:forEach>
					<div class="reply icon"><span>답변</span></div>
				</c:if>	
				<a href="<c:out value="${viewLink }" />" <c:out value="${linkTarget }" />>
					<%-- 제목 --%>
					<c:out value="${map.bod_ttl }"/>
				</a>
				<%-- 새글 --%>
				<c:if test="${map.bod_dateDiff < 1}">
					<div class="new icon"><span>새글</span></div>
				</c:if>
				<%-- 댓글수 --%>
				<c:if test="${map.bod_commentCnt > 0}"><c:out value="(${map.bod_commentCnt})"/></c:if>
				<%--비밀글 --%>
				<c:if test="${map.bod_lock == 1}">
					<div class="secret icon"><span>비밀글</span></div>
				</c:if>
			</td>
		</c:if>
		
		<%-- bhc_visibleNm --%>
		<c:if test="${bhc_visibleNm == 1 }">
			<td class="writer">
				<%--비밀글 --%>
				<c:choose>
					<c:when test="${map.bod_lock == 1 && !hasAdm}"><c:out value="${fn:substring(map.con_nm, 0, 1) }XX"/></c:when>
					<c:otherwise><c:out value="${map.con_nm }"/></c:otherwise>
				</c:choose>
			</td>
		</c:if>
		
		<%-- bhc_visibleDept --%>
		<c:if test="${bhc_visibleDept == 1 }">
			<td class="writer"><c:out value="${map.dept_nm }"/></td>
		</c:if>
		
		<%-- bhc_visibleDate --%>
		<c:if test="${bhc_visibleDate == 1 }">
			<td class="date"><fmt:formatDate value="${map.con_regDt}" pattern="yyyy-MM-dd" /></td>
		</c:if>
		
		<%-- bhc_visibleFile --%>
		<c:if test="${bhc_visibleFile == 1 }">
			<td class="file" >
				<c:if test="${map.file_fileCnt > 0 }">
					<div class="file_icon icon"><span>첨부파일 ${map.file_fileCnt }개</span></div>
				</c:if>
			</td>
		</c:if>
		
		<%-- bhc_visibleReadCnt --%>
		<c:if test="${bhc_visibleReadCnt == 1 }">
			<td class="<c:out value="${replyThClass }" />">
				<c:choose>
					<c:when test="${infoVo.inf_skinType == 'reply' || srchBodSkin == 'reply' }">
						<c:choose>
							<c:when test="${map.dept_sttus == 2 }">접수</c:when>
							<c:when test="${map.dept_sttus == 4 }">담당자지정</c:when>
							<c:when test="${map.dept_sttus == 5 }">완료</c:when>
							<c:when test="${map.dept_sttus == 6 }">접수 불가</c:when>
							<c:otherwise>신청</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<fmt:formatNumber value="${map.bod_readCnt}" groupingUsed="true"/>
					</c:otherwise>
				</c:choose>
			</td>
		</c:if>
		</tr>
	<c:if test="${map.bod_notice != 1}"><c:set var="no" value="${no-1}" /></c:if>
	</c:forEach>
	</tbody>
</table>
