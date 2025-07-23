<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:import url="/EgovPageLink.do?link=${managerDir }/cms/stats/inc_srchForm" />

<div class="board_area">
	<table class="board_table">
	<thead>
	<tr>
	    <th scope="col" style="width:7%;">No</th>
	    <th scope="col" style="width:13%;">Time</th>
	    <th scope="col" style="width:13%;">Site</th>
	    <th scope="col" style="width:13%;">Session</th>
	    <th scope="col" style="width:17%;">Client IP</th>
	    <th scope="col">Info</th>
	    <th scope="col" style="width:13%;">Lang</th>
  	</tr>
	</thead>
	<tbody>
	<c:if test="${fn:length(logList) == 0 }">
		<tr>
			<td colspan="7">데이터가 없습니다</td>
		</tr>
	</c:if>
	
	<c:forEach var="vo" items="${logList}" varStatus="status">
		<tr class="fs13">
			<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
			<td><fmt:formatDate value="${vo.ses_joinDt}" pattern="yyyy-MM-dd HH:mm:ss S" /></td>
			<td><c:out value="${vo.site_nm }" /></td>
			<td><c:out value="${vo.ses_sesID }" /></td>
			<td class="blue"><c:out value="${vo.ses_clientIP}" /></td>
			<td class="taL">
				<p class="orange"><c:out value="${fn:substring(vo.ses_referer,0 , 80)}" /></p>
				<p><c:out value="${fn:substring(vo.ses_agent, 0, 100)}" /></p>
			</td>
			<td><c:out value="${fn:substring(vo.ses_lang, 0, 8)}" /></td>
		</tr>
	<c:set var="no" value="${no-1}" />
	</c:forEach>
	</tbody>
</table>
</div>

<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>