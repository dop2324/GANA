<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="blockLogFrm" name="blockLogFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="site_code"		name="site_code"	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="frmTy"			name="frmTy" 		value="<c:out value="${frmTy }" />" />
	<input type="hidden" id="cmd"			name="cmd" />

		<div class="board_search">
			<div class="date">
				<input type="text" id="srchSDate" name="srchSdate" value="${srchMap.srchSdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
				<span>~</span>
				<input type="text" id="srchEdate" name="srchEdate" value="${srchMap.srchEdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
			</div>
			<input id="srchKwd" name="srchKwd" type="text" value="${srchMap.srchKwd }" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col" style="width:7%;">번호</th>
				<th scope="col" style="width:12%;">차단 사이트</th>
				<th scope="col" style="width:14%;">차단 ID</th>
				<th scope="col" style="width:14%;">차단 이름</th>
				<th scope="col" style="width:14%;">IP</th>
				<th scope="col">URL</th>
				<th scope="col" style="width:10%;">차단일시</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(blockLogList) == 0 }">
				<tr>
					<td colspan="7">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${blockLogList}" varStatus="status">
				<tr>
					<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
					<td><c:out value="${vo.site_nm }"/></td>
					<td><c:out value="${vo.log_id }"/></td>
					<td><c:out value="${vo.log_nm }"/></td>
					<td><c:out value="${vo.log_ip }"/></td>
					<td class="taL"><c:out value="${vo.log_url }"/></td>
					
					<td><fmt:formatDate value="${vo.log_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			<c:set var="no" value="${no -1 }" />
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
</div>

<script>
function searchData()
{
	$("#blockLogFrm").submit();
}
</script>