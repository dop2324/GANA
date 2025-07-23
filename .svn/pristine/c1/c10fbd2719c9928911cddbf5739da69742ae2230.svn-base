<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<jsp:useBean id="now" class="java.util.Date"/>

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
	<caption class="cp_no"><c:out value="${infoVo.mnu_nm }" /> 게시판</caption>
	<colgroup>
		<col style="width:6%;" />
		<c:if test="${bhc_visibleNo 	== 1 }"><col style="width:7%;" /></c:if>
		<c:if test="${bhc_visibleGroup 	== 1 && infoVo.inf_fncUsrGroup == 1}"><col style="width:10%;" /></c:if>
		<c:if test="${bhc_visibleTtl 	== 1 }"><col /></c:if>
		<c:if test="${bhc_visibleNm 	== 1 }"><col style="width:15%;" /></c:if>
		<c:if test="${bhc_visibleDept 	== 1 }"><col style="width:15%;" /></c:if>
		<c:if test="${bhc_visibleDate 	== 1 }"><col style="width:15%;" /></c:if>
		<c:if test="${bhc_visibleFile 	== 1 }"><col style="width:7%;" /></c:if>
		<c:if test="${bhc_visibleReadCnt == 1 }"><col style="width:7%;" /></c:if>
	</colgroup>

	<%-- thead --%>
	<thead>
		<tr>
			<th scope="col">선택</th>
	<c:if test="${bhc_visibleNo 	== 1 }"><th scope="col" class="bgbar">번호</th></c:if>
	<c:if test="${bhc_visibleGroup 	== 1 && infoVo.inf_fncUsrGroup == 1}"><th scope="col" class="bgbar">분류</th></c:if>
	<c:if test="${bhc_visibleTtl 	== 1 }"><th scope="col" class="bgbar">제목</th></c:if>
	<c:if test="${bhc_visibleNm 	== 1 }"><th scope="col" class="bgbar">작성자</th></c:if>
	<c:if test="${bhc_visibleDept 	== 1 }"><th scope="col" class="bgbar">부서</th></c:if>
	<c:if test="${bhc_visibleDate	== 1 }"><th scope="col" class="bgbar">작성일</th></c:if>
	<c:if test="${bhc_visibleFile 	== 1 }"><th scope="col" class="bgbar">파일</th></c:if>
	<c:if test="${bhc_visibleReadCnt == 1 }">
		<th scope="col" class="bgbar">
			<c:choose>
				<c:when test="${infoVo.inf_skinType == 'reply' || srchBodSkin == 'reply'}">답변</c:when>
				<c:otherwise>조회</c:otherwise>
			</c:choose>
		</th>
	</c:if>
		</tr>
	</thead>

	<%-- tbody --%>
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
			<td><input type="checkbox" name="bod_check" id="bod_check" class="inp_chk" value="${map.bod_sn }" /></td>

		<%-- bhc_visibleNo --%>
		<c:if test="${bhc_visibleNo == 1 }">
			<td>
			<%--공지 --%>
			<c:choose>
				<c:when test="${map.bod_notice == 1 }">[공지]</c:when>
				<c:otherwise><fmt:formatNumber value="${no}" groupingUsed="true"/></c:otherwise>
			</c:choose>
			</td>
		</c:if>

		<%--bhc_visibleGroup --%>
		<c:if test="${bhc_visibleGroup == 1 && infoVo.inf_fncUsrGroup == 1}">
			<td><c:out value="${map.bgp_nm }"/></td>
		</c:if>

		<%-- bhc_visibleTtl --%>
		<c:if test="${bhc_visibleTtl == 1 }">
			<td class="taL subject">
				<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2" />

				<a href="<c:out value="${viewLink }" />">
					<c:if test="${srchAllBoard != null}">
						<p>[<c:out value="${map.site_nm }" /> > <c:out value="${map.mnu_nm }" />]</p>
					</c:if>

					<%-- 관리자 --%>
					<c:if test="${hasAdm}">
						<div>
							<%-- 삭제글 --%>
							<c:if test="${map.bod_delSttus == 1}">
								<span class="small red">
									[삭제글] <c:out value="${map.bod_delUserId}"/> / <c:out value="${map.bod_delDt}"/>
									<c:if test="${map.bod_delReason != '' }">
										(<c:out value="${map.bod_delReason}"/>)
									</c:if>
								</span>
							</c:if>
							<%-- 사용유무 --%>
							<c:if test="${map.bod_sttus == 0}"><span class="small red">[중지]</span></c:if>
							<%-- push 여부 --%>
							<c:if test="${map.bod_pushGroup != null}"><span class="small red">[Push]</span></c:if>

							<%-- 기간설정 --%>
							<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
							<fmt:formatDate var="startDate" value="${map.bod_startDate}" pattern="yyyy-MM-dd"/>
							<fmt:formatDate var="endDate" value="${map.bod_endDate}" pattern="yyyy-MM-dd" />

							<c:if test="${startDate != '2000-01-01' || endDate != '9999-12-31'  }">
								<span class="small"><c:out value="${startDate }" /> ~ <c:out value="${endDate }" /></span>
							</c:if>

							<%-- 기간만료 (기간설정 1) --%>
							<c:if test="${infoVo.inf_fncAdmTerm == 1 && infoVo.inf_fncAdmTermTy == 1}">
								<c:choose>
									<c:when test="${today > endDate }"><span class="small red">[기간만료]</span></c:when>
									<c:when test="${today < startDate }"><span class="small red">[게시예정]</span></c:when>
								</c:choose>
							</c:if>
							<c:if test="${infoVo.inf_fncAdmTerm == 1 && infoVo.inf_fncAdmTermTy == 0}">
								[<c:out value="${startDate }" />]
							</c:if>
						</div>
					</c:if>

					<%--답변 --%>
					<c:if test="${map.bod_level > 0}">
						<c:forEach var="val" begin="1" end="${map.bod_level}" step="1" >
							&nbsp;&nbsp;
						</c:forEach>
						<div class="reply icon"><span>답변</span></div>
					</c:if>

					<%-- 제목 --%>
					<c:out value="${map.bod_ttl }"/>

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
				</a>
			</td>
		</c:if>

		<%-- bhc_visibleNm --%>
		<c:if test="${bhc_visibleNm == 1 }">
			<td>
				<%--비밀글 --%>
				<c:choose>
					<c:when test="${map.bod_lock == 1 && !hasAdm}"><c:out value="${fn:substring(map.con_nm, 0, 1) }XX"/></c:when>
					<c:otherwise><c:out value="${map.con_nm }"/></c:otherwise>
				</c:choose>
			</td>
		</c:if>

		<%-- bhc_visibleDept --%>
		<c:if test="${bhc_visibleDept == 1 }">
			<td><c:out value="${map.dept_nm }"/></td>
		</c:if>

		<%-- bhc_visibleDate --%>
		<c:if test="${bhc_visibleDate == 1 }">
			<td class="b_date"><fmt:formatDate value="${map.con_regDt}" pattern="yyyy-MM-dd" /></td>
		</c:if>

		<%-- bhc_visibleFile --%>
		<c:if test="${bhc_visibleFile == 1 }">
			<td class="b_file" >
				<c:if test="${map.file_fileCnt > 0 }"><img src="/images/board/ico_file.gif" alt="첨부파일 ${map.file_fileCnt }개" /></c:if>
			</td>
		</c:if>

		<%-- bhc_visibleReadCnt --%>
		<c:if test="${bhc_visibleReadCnt == 1 }">
			<td class="b_hit">
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
