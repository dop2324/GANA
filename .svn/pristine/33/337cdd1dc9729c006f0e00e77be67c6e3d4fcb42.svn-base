<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="survey_area board_area">
	<table class="board_table">
		<caption>설문조사 목록, 제목, 기간, 담당부서, 참여자 순서대로 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="num">번호</th>
				<th scope="col" class="subject">제목</th>
				<th scope="col" class="dateTerm">기간</th>
				<th scope="col" class="answer">참여자</th>
				<th scope="col" class="funcBtn">참여</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(surveyList) == 0 }">
			<tr>
				<td colspan="5">진행중인 설문이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="vo" items="${surveyList}" varStatus="status">
			<tr>
				<td class="num"><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
				<td class="subject taL">
					<c:choose>
						<c:when test="${vo.survey_dateEnd == 0 }"><span class="green">[대기]</span></c:when>
						<c:when test="${vo.survey_dateEnd == 1 }"><span class="blue">[진행]</span></c:when>
						<c:when test="${vo.survey_dateEnd == 2 }"><span class="red">[종료]</span></c:when>
					</c:choose>
					
					<span class="pad_l5"><c:out value="${vo.survey_ttl }" /></span>
				</td>
				<td class="dateTerm">
					<fmt:formatDate value="${vo.survey_startDt}" pattern="yyyy-MM-dd HH" />
					~
					<fmt:formatDate value="${vo.survey_endDt}" pattern="yyyy-MM-dd HH" />
				</td>
				<td class="answer">
					<fmt:formatNumber value="${vo.survey_userCnt }" pattern="#,###" />
				</td>
				<td class="funcBtn">
					<c:set var="link"><c:out value="?${queryString}survey_sn=${vo.survey_sn}&pageNo=${pageNo}" /></c:set>

					<c:choose>
						<c:when test="${vo.survey_dateEnd == 0}">
							<a href="#self" class="status_2">대기</a>
						</c:when>
						<c:when test="${vo.survey_dateEnd == 1 && vo.survey_sttus == 1}">
							<a href="${link }&cmd=4" class="status_1">참여</a>
						</c:when>
						<c:when test="${vo.survey_dateEnd == 2}">
							<a href="${link }&cmd=2" class="status_3">결과</a>
						</c:when>
					</c:choose>
				</td>
			</tr>
		<c:set var="no" value="${no-1}" />
		</c:forEach>
		
		</tbody>
	</table>

</div>

<div class="board_paging">${paging}</div>