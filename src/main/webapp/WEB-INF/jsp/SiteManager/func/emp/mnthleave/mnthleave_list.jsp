<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="mnthFrm" name="mnthFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />


<c:set var="year"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set> 

<div class="board_search">
	<select name="srchYear" id="srchYear">
		<option value="">년도</option>
		<c:forEach var="i" begin="${year -10 }" end="${year}" step="1">
			<option value="<c:out value="${i }" />" <c:if test="${i == srchYear }">selected="selected"</c:if>><c:out value="${i }" /></option>
		</c:forEach>
	</select>
	<input id="srchKwd" name="srchKwd" type="text" value="<c:out value="${srchKwd }" />"  placeholder="검색어" />
	<a href="#self" onclick="searchData()">검색</a>
</div>

</form>

<div class="board_area">
	<table class="board_table">
		<thead>
			<tr>
				<th scope="col" style="width:10%;">부서명</th>
				<th scope="col" style="width:8%;">직원명</th>
				<th scope="col">1월</th>
				<th scope="col">2월</th>
				<th scope="col">3월</th>
				<th scope="col">4월</th>
				<th scope="col">5월</th>
				<th scope="col">6월</th>
				<th scope="col">7월</th>
				<th scope="col">8월</th>
				<th scope="col">9월</th>
				<th scope="col">10월</th>
				<th scope="col">11월</th>
				<th scope="col">12월</th>
				<th scope="col" class="wp5">합계</th>
				<th scope="col">관리</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(empList) == 0 }">
			<tr>
				<td colspan="16">데이터가 없습니다</td>
			</tr>
		</c:if>
		<c:forEach var="map" items="${empList}" varStatus="status">
			<tr>
				<td><c:out value="${map.dept_nm }"/></td>
				<td><c:out value="${map.emp_nm }"/></td>
				<td><c:out value="${map.mnth_1 }"/></td>
				<td><c:out value="${map.mnth_2 }"/></td>
				<td><c:out value="${map.mnth_3 }"/></td>
				<td><c:out value="${map.mnth_4 }"/></td>
				<td><c:out value="${map.mnth_5 }"/></td>
				<td><c:out value="${map.mnth_6 }"/></td>
				<td><c:out value="${map.mnth_7 }"/></td>
				<td><c:out value="${map.mnth_8 }"/></td>
				<td><c:out value="${map.mnth_9 }"/></td>
				<td><c:out value="${map.mnth_10 }"/></td>
				<td><c:out value="${map.mnth_11 }"/></td>
				<td><c:out value="${map.mnth_12 }"/></td>
				<td><c:out value="${map.mnth_total }"/></td>
				<td><a href="<c:out value="${listLink }cmd=2&emp_id=${map.emp_id }" />" class="btn">관리</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<script>
function searchData()
{
	$("#mnthFrm").submit();
}
</script>