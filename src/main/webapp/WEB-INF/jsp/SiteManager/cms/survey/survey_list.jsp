<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form method="post" id="surveyFrm" name="surveyFrm" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

	<div class="board_search">
		<select title="사용유무 선택" name="srchSttus" id="srchSttus">
			<option value="">사용유무</option>
			<option value="1" <c:out value="${srchMap.srchSttus == '1' ? 'selected=\"selected\"':'' }" />>사용</option>
			<option value="0" <c:out value="${srchMap.srchSttus == '0' ? 'selected=\"selected\"':'' }" />>중지</option>			
		</select>	
		<input id="srchKwd" name="srchKwd" type="text" value="<c:out value="${srchMap.srchKwd }" />"  placeholder="검색어" />
		<a href="#self" onclick="searchData()">검색</a>
		<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>	
	</div>
	
	<div class="survey_area board_area">
		<table class="board_table">
			<thead>
				<tr>
					<th scope="col" style="width:7%;">번호</th>
					<th scope="col">제목</th>
					<th scope="col" style="width:15%;">기간</th>
					<th scope="col" style="width:8%;">참여자</th>
					<th scope="col" style="width:11%;">본인확인</th>
					<th scope="col" style="width:8%;">사용유무</th>
					<th scope="col" style="width:23%;">관리</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(surveyList) == 0 }">
				<tr>
					<td colspan="7">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${surveyList}" varStatus="status">
				<tr>
					<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
					<td class="taL">
						<c:choose>
							<c:when test="${vo.survey_dateEnd == 0 }"><span class="green">[대기]</span></c:when>
							<c:when test="${vo.survey_dateEnd == 1 }"><span class="blue">[진행]</span></c:when>
							<c:when test="${vo.survey_dateEnd == 2 }"><span class="red">[종료]</span></c:when>
						</c:choose>
						<span style="padding-left:5rem;"><c:out value="${vo.survey_ttl }" /></span>
					</td>
					<td>
						<p><fmt:formatDate value="${vo.survey_startDt}" pattern="yyyy-MM-dd HH" />시</p>
						<p>~
							<fmt:formatDate var="survey_endDtHHmm" value="${vo.survey_endDt}" pattern="HH:mm" />
							<c:choose>
								<c:when test="${survey_endDtHHmm == '23:59' }">
									<fmt:formatDate value="${vo.survey_endDt}" pattern="yyyy-MM-dd" />
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${vo.survey_endDt}" pattern="yyyy-MM-dd HH" />시
								</c:otherwise>
							</c:choose>
						</p>
					</td>
					<td>
						<fmt:formatNumber value="${vo.survey_userCnt }" pattern="#,###" />
					</td>
					<td><c:out value="${vo.survey_authYn == 1 ? '사용 (회원)':'사용안함' }"/></td>
					<td><c:out value="${vo.survey_sttus == 1 ? '사용':'중지' }"/></td>
					<td>
						<c:set var="admLink" value="?${queryString}survey_sn=${vo.survey_sn}&pageNo=${searchMap.pageNo}" />
						<a href="<c:out value="${admLink }&cmd=16" />" class="btn">관리</a>
						<a href="<c:out value="${admLink }&cmd=8" />" class="btn blue">문항등록</a>
						<a href="<c:out value="${admLink }&cmd=64" />" class="btn pink">설문결과</a>

					</td>
				</tr>
			<c:set var="no" value="${no-1}" />
			</c:forEach>
			</tbody>
		</table>
	</div>
</form>

<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>