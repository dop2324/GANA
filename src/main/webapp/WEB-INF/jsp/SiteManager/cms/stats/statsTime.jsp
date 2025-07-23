<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:import url="/EgovPageLink.do?link=${managerDir }/cms/stats/inc_srchForm" />

<div class="board_area">
	<table class="board_table">
	<thead>
	<tr>
		<th scope="col" rowspan="2">일시</th>
		<th scope="col" colspan="3">접속수</th>
	</tr>
	<tr>
	    <th scope="col">전체</th>
	    <th scope="col">회원</th>
	    <th scope="col">비회원</th>
	  </tr>
	</thead>
	<tbody>
	<c:if test="${fn:length(siteList) == 0 }">
		<tr>
			<td colspan="4">데이터가 없습니다</td>
		</tr>
	</c:if>
	
	<c:set var="totalCnt" />
	<c:set var="totalMber" />
	<c:set var="totalGuest" />
	<c:forEach var="vo" items="${siteList}" varStatus="status">
		<tr>
			<td>
				<c:out value="${vo.sts_yyyy}" />.<c:out value="${vo.sts_mm }" />.<c:out value="${vo.sts_dd }" />
				<span class="pl10 b"><c:out value="${vo.sts_hh }" />시</span>
			</td>
			<td><fmt:formatNumber value="${vo.sts_countTot }" groupingUsed="true"/></td>
			<td><fmt:formatNumber value="${vo.sts_countMber }" groupingUsed="true"/></td>
			<td><fmt:formatNumber value="${vo.sts_countGuest }" groupingUsed="true"/></td>
		</tr>
		
		<c:set var="totalCnt" value="${totalCnt + vo.sts_countTot }" />
		<c:set var="totalMber" value="${totalMber + vo.sts_countMber }" />
		<c:set var="totalGuest" value="${totalGuest + vo.sts_countGuest }" />
	</c:forEach>
	
	</tbody>
	<tfoot>
		<tr>
			<th>합계</th>
			<th><fmt:formatNumber value="${totalCnt }" groupingUsed="true"/></th>
			<th><fmt:formatNumber value="${totalMber }" groupingUsed="true"/></th>
			<th><fmt:formatNumber value="${totalGuest }" groupingUsed="true"/></th>
		</tr>
	</tfoot>
</table>
</div>