<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="logFrm" name="logFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code } "/>" />
	
		<div class="board_search">
			<select name="srchUseOrg" id="srchUseOrg" class="w100">
				<option value="">요청사이트</option>
				<c:set var="list" value="${moa:getUseOrgList(pageContext.request) }" />
				<c:forEach var="list" items="${list }" varStatus="status">
					<option value="<c:out value='${list.useOrgId }' />" <c:out value="${srchMap.srchUseOrg eq list.useOrgId?'selected':'' }" />><c:out value='${list.hmpgNm }' /></option>
				</c:forEach>
			</select>
			<div class="date">
				<input type="text" id="srchSdate" name="srchSdate" value="${srchMap.srchSdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
				<span>~</span>
				<input type="text" id="srchEdate" name="srchEdate" value="${srchMap.srchEdate }" class="datepicker" readonly="readonly" placeholder="검색일자" />
			</div>
			<select name="srchLoginYn" id="srchLoginYn" class="w100">
				<option value="">로그인 성공여부</option>
				<option value="Y" <c:out value="${srchMap.srchLoginYn eq 'Y'?'selected':'' }" />>성공</option>
				<option value="N" <c:out value="${srchMap.srchLoginYn eq 'N'?'selected':'' }" />>실패</option>
			</select>
			<select title="검색조건 선택" name="srchSe" id="srchSe" class="w100">
				<option value="">검색조건</option>
				<option value="MEMB_ID" <c:out value="${srchMap.srchSe eq 'MEMB_ID'?'selected':'' }" />>회원ID</option>
				<option value="LGN_IP" <c:out value="${srchMap.srchSe eq 'LGN_IP'?'selected':'' }" />>접근IP</option>
			</select>
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
		</div>
		
		<div>
			로그인 이력은 최대 90일까지 보관합니다.(3개월)
		</div>
	</form>
	
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col">회원명 ID</th>
				<th scope="col">요청기관 ID</th>
				<th scope="col">요청시간</th>
				<th scope="col">로그인 성공여부</th>
				<th scope="col">실패사유</th>
				<th scope="col">접근IP</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(resultList) == 0 }">
			<tr>
				<td colspan="6">데이터가 없습니다</td>
			</tr>
			</c:if>
			<c:forEach var="vo" items="${resultList}" varStatus="status">
			<tr class="fs13">
				<td><c:out value="${vo.membId }" /></td>
				<td><c:out value="${moa:getUseOrgNm(pageContext.request, vo.useOrgId) }" /></td>
				<td><c:out value="${vo.lgnDt }" /></td>
				<td><c:out value="${vo.lgnSuccsYn }" /></td>
				<td><c:out value="${vo.lgnFailRs }" /></td>
				<td><c:out value="${vo.lgnIp }" /></td>
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
	$("#logFrm").submit();
}
</script>