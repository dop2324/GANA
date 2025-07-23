<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="calendar">

	<div class="monthTab">
		<a href="<c:out value="?${queryString }year=${initYear-1 }&month=12" />" class="arrow prev"><span class="hidden"><c:out value="${initYear-1 }"/>년</span></a>
		<p><c:out value="${initYear }"/>년</p>
		<a href="<c:out value="?${queryString }year=${initYear+1 }&month=1" />" class="arrow next"><span class="hidden"><c:out value="${initYear+1 }"/>년</span></a>
		<div class="month_list">
  		<c:forEach var="i" begin="1" end="12">
  			<c:set var="currClass" value="" />
  			<c:set var="currTitle" value="" />
  			<c:if test="${initMonth == i }">
  				<c:set var="currClass" value="class='on'" />
  				<c:set var="currTitle" value="title='선택됨'" />
  			</c:if>
  			<a <c:out value="${currClass }" escapeXml="false" /> <c:out value="${currTitle }" escapeXml="false" /> href="<c:out value="?${queryString }year=${initYear}&month=${i}&day=1" />">
  				<c:out value="${i}"/>월</a>
  		</c:forEach>
		</div>
	</div>
	
	<%-- 스킨 분기 --%>
	<c:choose>

		<c:when test="${listType == 'month' }">
			<c:import url="/EgovPageLink.do?link==${publicDir }/cms/board/board_listCalendarMonth" />
		</c:when>
		<c:otherwise>
			<c:import url="/EgovPageLink.do?link=${publicDir }/cms/board/board_listCalendarDay" />
		</c:otherwise>
	</c:choose>

</div>