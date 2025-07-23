<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="day_type">
	<div class="left">
		<!--p class="calendar_month"><c:out value="${initMonth }"/>월<span><c:out value="${initMonthName }"/></span></p-->
		<div class="calendar_table">
			<table>
				<caption><c:out value="${initMonth }" />월 달력을 나타낸 표입니다.</caption>
				<thead>
					<tr>
						<th scope="col">일</th>
						<th scope="col">월</th>
						<th scope="col">화</th>
						<th scope="col">수</th>
						<th scope="col">목</th>
						<th scope="col">금</th>
						<th scope="col">토</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:if test="${firstDayOfWeek != '1'}">
							<%-- 해당 월의 가장 첫째줄에 있는 공백부분을 셈해서 처리한다.--%>
			      			<c:forEach var="i" begin="1" end="${firstDayOfWeek-1}">
								<td><span class="date other_month"></span> </td>
							</c:forEach>
						</c:if>

						<%-- 이 달의 끝날까지 메모의 제목과 날짜(숫자)를 출력한다 --%>
						<c:set var="dbIndex" value="0"/>
		      			<c:forEach var="currentDay" begin="1" end="${lastDayOfMonth}">
		      				<td>
		      					<c:set var="var" value="${currentDay-(8-firstDayOfWeek) }" />
		      					<c:set var="link" value="?${queryString }year=${initYear}&month=${initMonth}&day=${currentDay }" />
		      					<c:set var="currentDay"><fmt:formatNumber value="${currentDay }" pattern="00" /></c:set>

		      					<span class="date">
		      						<c:set var="dayClassTitle" value="" />
		      						<c:if test="${currentDay == initDay }">
		      							<c:set var="dayClassTitle" value="class='on' title='선택됨'" />
		      						</c:if>
		      						<a href="<c:out value="${link }" />" <c:out value="${dayClassTitle }" escapeXml="false" />><c:out value="${currentDay}"/></a>
		      					</span>
		      				</td>
		      				<%-- 만약 한주의 마지막날(토요일)이고 이 달의 마지막 날이 아니라면 다음 줄로 넘어간다. --%>
							<c:if test="${((currentDay-(8-firstDayOfWeek)) % 7) == 0 && currentDay != lastDayOfMonth}">
								</tr><tr>
							</c:if>

		      			</c:forEach>

		      			<%-- 해당 월의 가장 마지막 줄에 있는 공백부분을 셈해서 처리한다.--%>
						<c:if test="${lastDayOfLastWeek != '7'}">
							<c:forEach var="i" begin="1" end="${7-lastDayOfLastWeek}">
								<td>&nbsp;</td>
							</c:forEach>
						</c:if>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="right detail">
		<p class="title"><!--<c:out value="${initYear }"/>. --><c:out value="${initMonth }"/>. <c:out value="${initDay }"/>. 상세일정</p>
		<c:if test="${fn:length(boardList) == 0 }">
			<p class="noEvent">행사 일정이 없습니다</p>
		</c:if>
		<c:forEach var="map" items="${boardList}" varStatus="status">
			<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2&year=${initYear}&month=${initMonth}&day=${initDay }" />
			<fmt:formatDate var="start" value="${map.bod_startDate}" pattern="yyyy-MM-dd" />
			<fmt:formatDate var="end" value="${map.bod_endDate}" pattern="yyyy-MM-dd"/>
			<a href="<c:out value="${viewLink }" />">
				<dl>
					<dt><span class="bgp_<c:out value="${map.bgp_sn}" />"><c:out value="${map.bod_ttl }"/></span></dt>
					<dd><c:out value="${start }" /> ~ <c:out value="${end }" /></dd>
				</dl>
			</a>
		</c:forEach>
	</div>
</div>
