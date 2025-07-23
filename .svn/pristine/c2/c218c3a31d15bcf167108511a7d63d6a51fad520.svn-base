<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form method="post" id="empFrm" name="empFrm" action="<c:out value="${actionMenuLink }" />">
<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="cmd"			name="cmd" />

	<div class="board_search">
		<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchKwd }" />" />
		<a href="#self"  onclick="searchData()">검색</a>
		<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>
	</div>
	
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col" style="width:5%;">번호</th>
				<th scope="col" style="width:10%;">부서이름</th>
				<th scope="col" style="width:7%;">이름</th>
				<th scope="col" style="width:7%;">직위</th>
				<th scope="col" style="width:10%;">전화번호</th>
				<th scope="col" style="width:10%;">휴대전화</th>
				<th scope="col">직원업무</th>	
				<th scope="col" style="width:7%;">사용유무</th>		
				<th scope="col" style="width:15%;">등록일자/수정일자</th>
				<th scope="col" style="width:7%;">관리</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(empList) == 0 }">
				<tr>
					<td colspan="10">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${empList}" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td><c:out value="${vo.dept_nm }"/></td>
					<td><c:out value="${vo.emp_nm }"/></td>
					<td><c:out value="${vo.emp_jbps }"/> / <c:out value="${vo.emp_jbgd }"/></td>
					<td><c:out value="${vo.emp_telno }"/></td>
					<td><c:out value="${vo.emp_moblphon }"/></td>
					<td class="taL p5"><c:out value="${util:htmlEncode(vo.emp_task) }" escapeXml="false" /></td>
					<td>
						<c:out value="${vo.emp_useYn == 1 ? '사용':'중지' }"/>
					</td>
					<td>
						<fmt:formatDate value="${vo.emp_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
					
						<c:if test="${vo.emp_mdfcnDt != null }">
						<br/> <fmt:formatDate value="${vo.emp_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						</c:if>
					</td>
					<td>
						<a href="<c:out value="${listLink }cmd=16&emp_id=${vo.emp_id }" />" class="btn">관리</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	
</form>