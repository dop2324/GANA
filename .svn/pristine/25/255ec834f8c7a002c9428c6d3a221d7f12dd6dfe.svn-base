<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_area">
	<table class="board_table">
		<caption>RSS 목록, 메뉴명, RSS 주소 순서대로 안내하는 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" class="num">번호</th>
				<th scope="col" class="bgbar">메뉴명</th>
				<th scope="col">RSS 주소</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(rssBoardList) == 0 }">
			<tr>
				<td colspan="3">RSS 설정이 없습니다</td>
			</tr>
		</c:if>
		<c:forEach var="vo" items="${rssBoardList}" varStatus="status">
			<c:set var="rssLink" value="${currDomain }/common/board/rss_list.do?mnu_code=${vo.mnu_code }" />
			<tr>
				<td class="num"><fmt:formatNumber value="${status.count}" groupingUsed="true"/></td>
				<td class="bgbar"><c:out value="${vo.mnu_nm }" /></td>
				<td class="taL">
					<a href="<c:url value="${rssLink }" />" target="_blank">
						<c:out value="${rssLink }" />
					</a>
				</td>
			</tr>

		</c:forEach>
		
		</tbody>
	</table>

</div>