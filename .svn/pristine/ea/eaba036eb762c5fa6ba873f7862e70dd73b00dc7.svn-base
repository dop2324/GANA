<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="board_photo">
	<c:if test="${fn:length(boardLists) == 0 }"><li class="no_list">게시글 내용이 없습니다.</li></c:if>
	<c:forEach var="map" items="${boardLists}" varStatus="status">
	<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2" />
		<a href="<c:out value="${viewLink }" />">
			<div class="thumb">
				<c:set var="imgFileNm" value="${map.bod_listFileNm }" />
				<c:out value="${util:getImgTagStyle('board', imgFileNm, map.bod_ttl, '') }" escapeXml="false" />
			</div>
			<dl>
				<dt><c:out value="${map.bod_ttl }"/></dt>
				<dd><fmt:formatDate value="${map.con_regDt}" pattern="yyyy-MM-dd" /></dd>
			</dl>
		</a>
	</c:forEach>
</div>
