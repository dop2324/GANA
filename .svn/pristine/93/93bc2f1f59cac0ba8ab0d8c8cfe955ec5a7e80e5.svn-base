<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="listForm" name="listForm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code } "/>" />
	
		<div class="board_search">
			<select title="분류 선택" name="srchSe" id="srchSe">
				<option value="TTL" <c:out value="${srchMap.srchSe eq 'TTL'?' selected':'' }" />>축제명</option>
			</select>
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" required placeholder="검색어" />
			<button type="submit" class="bt3 srch">검색</button>
			<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>
		</div>
	</form>
	
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">축제명</th>
				<th scope="col">행사기간</th>
				<th scope="col">행사시간</th>
				<th scope="col">장소</th>
				<th scope="col">출력유무</th>
				<th scope="col">관리</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(resultList) == 0 }">
			<tr>
				<td colspan="7">데이터가 없습니다</td>
			</tr>
			</c:if>
			<c:forEach var="vo" items="${resultList}" varStatus="status">
			<tr class="fs13">
				<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
				<td><c:out value="${vo.ttl }" /></td>
				<td>
					<c:out value="${vo.festvlBgngYmd }" /> ~ <c:out value="${vo.festvlEndYmd }" />
				</td>
				<td>
					<c:out value="${vo.festvlBgngHm }" /> ~ <c:out value="${vo.festvlEndHm }" />
				</td>
				<td><c:out value="${vo.plcNm }" /></td>
				<td><c:out value="${vo.useYn }" /></td>
				<td>
					<a href="<c:out value="${listLink }cmd=16&fsvUnqId=${vo.fsvUnqId }" />" class="btn">수정</a>
				</td>
			</tr>
			<c:set var="no" value="${no -1 }" />
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>

</div>
