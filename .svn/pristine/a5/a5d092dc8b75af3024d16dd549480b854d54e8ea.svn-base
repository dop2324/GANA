<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:set var="totalCnt" value="${fn:length(orgList)}" />
<div class="C_body" id="job_sc">
	<ul class="Cmenu_Title">
		<li><c:out value="${mnu_nm }" /> 검색</li>
		<li>총 <span><fmt:formatNumber value="${totalCnt }" groupingUsed="true"/></span>건</li>
	</ul>
	
	<div id="tableContente" class="mobile">
		<table class="basic_table center">
			<caption>업무/직원</caption>
			<thead>
				<tr class="tblHead">
					<th>부서/팀</th>
					<th>직위</th>
					<th>이름</th>
					<th>전화</th>
					<th>담당업무</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="map" items="${orgList}" varStatus="status">
				<tr>
					<td><c:out value="${map.dept_nm }"/></td>
					<td><c:out value="${map.emp_jbps }"/></td>
					<td><c:out value="${map.emp_nm }"/></td>
					<td><c:out value="${map.emp_telno }"/></td>
					<td><c:out value="${util:htmlEncode(map.emp_task) }" escapeXml="false" /></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>		
	
	<c:if test="${totalCnt > 0 && param.pageSize < 10 }">
		<script>
			$(function(){
				var totalSrchCnt = Number($("#totalCntTop").text());
				var totalSrchCnt = totalSrchCnt+<c:out value="${totalCnt}" />;
				$("#totalCntTop").text(totalSrchCnt);
				$(".totalCntClass").text(totalSrchCnt);
				$(".<c:out value="${param.ctgry}" />Cnt").text(<c:out value="${totalCnt}" />);
			});
			
		</script>
		<div class="more"><a title="검색 더보기" href="<c:out value="?ctgry=${param.ctgry }&kwd=${kwd }" />">더보기</a></div>
	</c:if>
</div>
