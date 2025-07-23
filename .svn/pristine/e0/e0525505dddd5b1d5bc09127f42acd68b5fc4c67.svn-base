<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="commtFrm" name="commtFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="site_code"		name="site_code" 	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="frmTy"			name="frmTy"		value="<c:out value="${frmTy }" />" />
	<input type="hidden" id="cmd"			name="cmd" />

		<div class="board_search">
			<div class="date">
				<input type="text" id="srchSDate" name="srchSdate" value="${srchMap.srchSdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
				<span>~</span>
				<input type="text" id="srchEdate" name="srchEdate" value="${srchMap.srchEdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
			</div>
			<input id="srchKwd" name="srchKwd" type="text" value="${srchMap.srchKwd }" placeholder="검색어" />
			<a href="#self" onclick="searchData()">검색</a>
		</div>

	</form>
	<div class="board_area">
		<table class="board_table">
			<thead>
				<tr>
					<th scope="col" style="width:25%;">메뉴명</th>
					<th scope="col">만족도</th>
					<th scope="col" style="width:45%;">의견</th>
					<th scope="col" style="width:15%;">등록일시</th>
				</tr>

			</thead>
			<tbody>
			<c:if test="${fn:length(gratifyList) == 0 }">
				<tr>
					<td colspan="4">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${gratifyList}" varStatus="status">
				<tr>
					<td><c:out value="${vo.mnu_nm }" /></td>
					<td>
						<c:choose>
							<c:when test="${vo.gra_point5 == 1 }">매우만족</c:when>
							<c:when test="${vo.gra_point4 == 1 }">만족</c:when>
							<c:when test="${vo.gra_point3 == 1 }">보통</c:when>
							<c:when test="${vo.gra_point2 == 1 }">불만족</c:when>
							<c:when test="${vo.gra_point1 == 1 }">매우불만족</c:when>
						</c:choose>
					</td>
					<td class="taL"><c:out value="${vo.gra_commt }" /></td>
					<td><fmt:formatDate value="${vo.gra_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			<c:set var="no" value="${no + 1 }" />
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="board_paging">
		<c:out value="${paging}" escapeXml="false" />
	</div>
</div>

<script>
function searchData()
{
	$("#commtFrm").submit();
}
</script>