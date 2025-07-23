<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_area">
	<table class="board_table">
		<thead>
			<tr>
				<th scope="col" style="width:30%;">메뉴명</th>
				<th scope="col">매우만족</th>
				<th scope="col">만족</th>
				<th scope="col">보통</th>
				<th scope="col">불만족</th>
				<th scope="col">매우불만족</th>
				<th scope="col">합계</th>
			</tr>

		</thead>
		<tbody>
		<c:if test="${fn:length(gratifyList) == 0 }">
			<tr>
				<td colspan="7">데이터가 없습니다</td>
			</tr>
		</c:if>
		<c:forEach var="vo" items="${gratifyList}" varStatus="status">
			<tr>
				<td><c:out value="${vo.mnu_nm }" /></td>
				<td><fmt:formatNumber value="${vo.gra_point5}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${vo.gra_point4}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${vo.gra_point3}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${vo.gra_point2}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${vo.gra_point1}" groupingUsed="true"/></td>
				<td><fmt:formatNumber value="${vo.gra_total}" groupingUsed="true"/></td>
			</tr>
		<c:set var="no" value="${no + 1 }" />
		</c:forEach>
		</tbody>
	</table>
</div>