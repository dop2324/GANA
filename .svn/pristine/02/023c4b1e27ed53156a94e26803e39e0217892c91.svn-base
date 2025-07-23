<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="logFrm" name="logFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="site_code"		name="site_code"	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="frmTy"			name="frmTy" 		value="<c:out value="${frmTy }" />" />
	<input type="hidden" id="cmd"			name="cmd" />

		<div class="board_search">
			
			<div class="date">
				<input type="text" id="srchSDate" name="srchSdate" value="${srchMap.srchSdate }" class="datepicker inp_txt w150" readonly="readonly" placeholder="검색일자" />
				<span>~</span>
				<input type="text" id="srchEdate" name="srchEdate" value="${srchMap.srchEdate }" class="datepicker inp_txt w150" readonly="readonly" placeholder="검색일자" />
			</div>
			
			<select id="srchHow" name="srchHow">
				<option value="">처리단계</option>
				<option value="login" <c:if test="${srchMap.srchHow == 'login'}">selected="selected"</c:if>>Login</option>
				<option value="1" 	<c:if test="${srchMap.srchHow == '1'}">selected="selected"</c:if>>목록</option>
				<option value="2" 	<c:if test="${srchMap.srchHow == '2'}">selected="selected"</c:if>>조회</option>
				<option value="4" 	<c:if test="${srchMap.srchHow == '4'}">selected="selected"</c:if>>입력</option>
				<option value="16" 	<c:if test="${srchMap.srchHow == '16'}">selected="selected"</c:if>>수정</option>
				<option value="32" 	<c:if test="${srchMap.srchHow == '32'}">selected="selected"</c:if>>삭제</option>
			</select>
			
			<select id="srchPrivacy" name="srchPrivacy">
				<option value="">개인정보</option>
				<option value="Y" <c:if test="${srchMap.srchPrivacy == 'Y'}">selected="selected"</c:if>>포함</option>
			</select>
			
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
		<thead>
		<tr>
			<th scope="col" class="wp5">번호</th>
			<th scope="col" class="wp10">일시</th>
			<th scope="col" class="wp12">메뉴</th>
			<th scope="col" class="wp13">아이디</th>
			<th scope="col">내용</th>
			<th scope="col" class="wp8">개인정보</th>
			<th scope="col" class="wp7">CMD</th>
			<th scope="col" class="wp9">IP</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(logList) == 0 }">
			<tr>
				<td colspan="8">데이터가 없습니다</td>
			</tr>
		</c:if>
		<c:forEach var="vo" items="${logList}" varStatus="status">
			<tr class="fs13">
				<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
				<td><c:out value="${fn:substring(vo.log_when, 0, 23) }"/></td>
				<td>
					<p><c:out value="${vo.mnu_nm }"/></p>
					<p>(<c:out value="${vo.mnu_code }"/>)</p>
				</td>
				<td>
					<c:out value="${vo.log_who }"/>
				</td>
				<td class="taL"><c:out value="${util:htmlEncode(vo.log_what) }" escapeXml="false" /></td>
				<td><c:out value="${vo.log_privacy }"/></td>
				<td>
					<c:set var="crud" value="${vo.log_how }" />
					<c:choose>
						<c:when test="${crud == '1'}">목록조회</c:when>
						<c:when test="${crud == '2'}">조회</c:when>
						<c:when test="${crud == '4'}">입력</c:when>
						<c:when test="${crud == '8'}">답변</c:when>
						<c:when test="${crud == '16'}">수정</c:when>
						<c:when test="${crud == '32'}">삭제</c:when>
						<c:when test="${crud == '64'}">관리</c:when>
						<c:when test="${crud == 'login'}">로그인</c:when>
						<c:when test="${crud == 'logout'}">로그아웃</c:when>
					</c:choose>
					<%--
					/	
					<c:out value="${vo.log_how }"/>
					--%>
				</td>
				<td><c:out value="${vo.log_ip }"/></td>
			</tr>
		<c:set var="no" value="${no -1 }" />
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