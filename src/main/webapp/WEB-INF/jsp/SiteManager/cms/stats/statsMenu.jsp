<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:import url="/EgovPageLink.do?link=${managerDir }/cms/stats/inc_srchForm" />

<div class="board_area">
	<table class="board_table">
	<thead>
	<tr>
		<th scope="col">상위 메뉴</th>
		<th scope="col">메뉴</th>
		<th scope="col">접속수</th>
	</tr>

	</thead>
	<tbody>
	<c:if test="${fn:length(siteList) == 0 }">
		<tr>
			<td colspan="3">데이터가 없습니다</td>
		</tr>
	</c:if>
	
	<c:set var="totalCnt" />
	<c:forEach var="vo" items="${siteList}" varStatus="status">
		<tr>
			<td><c:out value="${vo.upr_nm}" /></td>
			<td><c:out value="${vo.mnu_nm }" /></td>
			<td><fmt:formatNumber value="${vo.tpd_joinCnt }" groupingUsed="true"/></td>
		</tr>
		
		<c:set var="totalCnt" value="${totalCnt + vo.tpd_joinCnt }" />
	</c:forEach>
	
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2">합계</th>
			<th><fmt:formatNumber value="${totalCnt }" groupingUsed="true"/></th>
		</tr>
	</tfoot>
</table>
</div>