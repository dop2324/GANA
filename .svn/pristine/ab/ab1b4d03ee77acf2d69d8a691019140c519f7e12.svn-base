<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="board_wrap">
	<form method="post" id="siteFrm" name="siteFrm" action="<c:out value='${actionMenuLink }' />">
		<input type="hidden" id="mnu_code" name="mnu_code" value="<c:out value='${mnu_code }' />" />

		<div class="board_search">
			<select title="검색구분" name="srchSe" id="srchSe" class="w100">
				<option value="ORG_NM" <c:out value="${srchMap.srchSe eq 'ORG_NM'?' selected':'' }" />>기관명</option>
			</select>
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text"
				value="<c:out value='${srchMap.srchKwd }' />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
			<a href="<c:out value='${listLink }cmd=4' />" class="btn blue">등록</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
			<thead>
				<tr>
					<th>No</th>
					<th>기관명</th>
					<th>우편번호</th>
					<th>도로명 주소</th>
					<th>상세 주소</th>
					<th>대표전화</th>
					<th>SMS 발신번호</th>
					<th>사용여부</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${orgList}" varStatus="status">
					<tr>
						<td><c:out value="${no - status.index}" /></td>
						<td><c:out value="${item.orgNm}" /></td>
						<td><c:out value="${item.zip}" /></td>
						<td><c:out value="${item.roadNmAddr}" /></td>
						<td><c:out value="${item.dtlAddr}" /></td>
						<td><c:out value="${item.rprsntTelno}" /></td>
						<td><c:out value="${item.smsSndngTelno}" /></td>
						<td><c:out value="${item.useYn}" /></td>
						<td>
							<a href="<c:out value='${listLink }cmd=16&orgUnqId=${item.orgUnqId }' />" class="btn">수정</a>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty orgList}">
					<tr>
						<td colspan="13">등록된 기관이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<div class="board_paging">
			<c:out value="${paging}" escapeXml="false" />
		</div>
	</div>
</div>

<script>
function searchData() {
	document.siteFrm.submit();
}
</script>
