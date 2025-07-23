<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<table class="board_table user_field">
	<%-- caption --%>
	<c:set var="caption" value="${infoVo.mnu_nm } 게시판" />
	<c:if test="${infoVo.inf_fncUsrField == 1}">
		<c:forEach var="bf" items="${infoVo.boardFieldList}" varStatus="status">
		<c:if test="${bf.bfd_sttus == 1}">
			<c:set var="caption" value="${caption} ${bf.bfd_nm}" />
		</c:if>
		</c:forEach>
	</c:if>
		
	<caption><c:out value="${caption }" />로 구성되어 있습니다.</caption>
	
	<thead>
		<tr>
		<c:if test="${infoVo.inf_fncUsrField == 1}">
		<c:forEach var="bf" items="${infoVo.boardFieldList}" varStatus="status">
		<c:if test="${bf.bfd_sttus == 1}">
			<th scope="col" class="bgbar"><c:out value="${bf.bfd_nm}" /></th>
		</c:if>
		</c:forEach>
		</c:if>
		</tr>
	</thead>
	<tbody>

	<c:forEach var="map" items="${boardLists}" varStatus="status">
	<c:set var="viewLink" value="?${queryString}bod_sn=${map.bod_sn}&pageNo=${pageNo}&cmd=2" />
		<tr>
		<c:forEach var="bfd" items="${map.boardFieldValueList}" varStatus="status">
			<td class="bgbar">
				<strong class="title">${infoVo.boardFieldList[status.index].bfd_nm}</strong>
				<a href="<c:out value="${viewLink }" />"><c:out value="${bfd.bfd_val}" /></a>
			</td>
		</c:forEach>
		</tr>
	</c:forEach>
	
	</tbody>

</table>