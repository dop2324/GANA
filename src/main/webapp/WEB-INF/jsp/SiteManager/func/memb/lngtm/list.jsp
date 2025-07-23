<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="board_wrap">
	<form method="post" id="srchFrm" name="srchFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code } "/>" />
		
		<div class="board_search">
			<select title="검색조건 선택" name="srchLngtm" id="srchLngtm" class="w100">
				<option value="ALL" <c:out value="${srchMap.srchLngtm eq 'ALL'?'selected':'' }" />>전체</option>
				<option value="FRST" <c:out value="${srchMap.srchLngtm eq 'FRST'?'selected':'' }" />>1차메일발송자</option>
				<option value="SCND" <c:out value="${srchMap.srchLngtm eq 'SCND'?'selected':'' }" />>2차메일발송자</option>
			</select>
			<select title="검색조건 선택" name="srchSe" id="srchSe" class="w100">
				<option value="MEMB_NM" <c:out value="${srchMap.srchSe eq 'MEMB_NM'?'selected':'' }" />>회원명</option>
				<option value="MEMB_ID" <c:out value="${srchMap.srchSe eq 'MEMB_ID'?'selected':'' }" />>회원ID</option>
			</select>
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
		</div>
	</form>
	
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col">회원명(ID)</th>
				<th scope="col">마지막로그인</th>
				<th scope="col">1차메일 발송</th>
				<th scope="col">2차메일 발송</th>
				<th scope="col">관리</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(resultList) == 0 }">
			<tr>
				<td colspan="5">데이터가 없습니다</td>
			</tr>
			</c:if>
			<c:forEach var="vo" items="${resultList}" varStatus="status">
			<tr class="fs13">
				<td><c:out value="${vo.membNm }" />(<c:out value="${vo.membId }" />)</td>
				<td><c:out value="${vo.lastLgnDt }" /></td>
				<td><c:out value="${vo.emlSndDt1 }" /></td>
				<td><c:out value="${vo.emlSndDt2 }" /></td>
				<td>
					<a href="<c:out value="${listLink }cmd=2&membUnqId=${vo.membUnqId }" />" class="btn">상세보기</a>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
	
</div>

<script>
function searchData()
{
	$("#srchFrm").submit();
}
</script>