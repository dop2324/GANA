<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>



   <div class="board_wrap">
	<form method="post" id="siteFrm" name="siteFrm" action="<c:out value='${actionMenuLink }' />">
		<input type="hidden" id="mnu_code" name="mnu_code" value="<c:out value='${mnu_code }' />" />

		<div class="board_search">
			<select title="검색구분" name="srchSe" id="srchSe" class="w100">
				<option value="ORG_NM" <c:out value="${srchMap.srchSe eq 'ORG_NM'?' selected':'' }" />>기관명</option>
				<option value="HMPG_URL" <c:out value="${srchMap.srchSe eq 'HMPG_URL'?' selected':'' }" />>홈페이지URL</option>
			</select>
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text"
				value="<c:out value='${srchMap.srchKwd }' />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
			<a href="<c:out value='${listLink }cmd=4' />" class="btn blue">등록</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
		<colgroup>
			<col style="width:8%;" />
			<col style="width:auto;" />
			<col style="width:auto;" />
			<col style="width:7%;" />
			<col style="width:14%;" />
			<col style="width:14%;" />
		</colgroup>
			<thead>
				<tr>
					<th>No</th>
					<th>기관명</th>
					<th>홈페이지 주소</th>
					<th>사용유무</th>
					<th>등록일자</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${orgList}" varStatus="status">
					<tr>
						<td><c:out value="${no - status.index}" /></td>
						<td><c:out value="${item.orgNm}" /></td>
						<td><c:out value="${item.hmpgUrl}" /></td>
						<td><c:out value="${item.useYn}" /></td>
						<td><c:out value="${item.regDt}" /></td>
						<td>
							<a href="<c:out value="${listLink }cmd=2&orgUnqId=${item.orgUnqId }" />" class="btn">상세화면</a>
							<a href="<c:out value="${listLink }cmd=16&orgUnqId=${item.orgUnqId }" />" class="btn">수정</a>
						</td>
						
					</tr>
				</c:forEach>
				<c:if test="${empty orgList}">
					<tr>
						<td colspan="6">등록된 연계기관이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<!-- 페이징 영역 -->
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
