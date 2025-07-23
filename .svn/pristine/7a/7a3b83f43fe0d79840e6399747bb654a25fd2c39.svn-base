<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="memberFrm" name="memberFrm" action="<c:out value="${actionMenuLink }" escapeXml="false" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="srchGroup"		name="srchGroup"	value="<c:out value="${srchMap.srchGroup }" />" />
	<input type="hidden" id="cmd"			name="cmd" />
	
		<div class="board_search">
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" />
			<a href="#self" onclick="searchData()">검색</a>
		</div>
	</form>
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">사이트</th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">상태</th>	
				<th scope="col">등록일시</th>		
				<th scope="col">최근로그인</th>
				<th scope="col">관리</th>
			</tr>
			</thead>
		
		<c:forEach var="vo" items="${memberList}" varStatus="status">
			<tr>
				<td><c:out value="${no }" /></td>
				<td><c:out value="${vo.site_nm }"/></td>
				<td><c:out value="${vo.mem_id }"/></td>
				<td><c:out value="${vo.mem_nm }"/></td>
				<td>
					<c:choose>
						<c:when test="${vo.mem_sttus == 0 }">중지</c:when>
						<c:when test="${vo.mem_sttus == 1 }">활동</c:when>
						<c:when test="${vo.mem_sttus == 9 }"><span class="red">탈퇴</span></c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${vo.mem_regDt}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${vo.mem_lastLoginDt}" pattern="yyyy-MM-dd" /></td>
				<td>
					<a href="#self" onclick="setUserId('<c:out value="${vo.mem_id }" />', '<c:out value="${vo.mem_nm }" />')" class="btn">선택</a>
				</td>
			</tr>
			<c:set var="no" value="${no -1 }" />
		</c:forEach>
		
		</table>	
	</div>
	
	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
</div>
<script>
function searchData()
{
	$("#memberFrm").submit();
}

function setUserId(mem_id, mem_nm)
{
	$("#groupMem_ty").val("회원")
	$("#groupMem_id").val(mem_id);
	$("#groupMem_nm").val(mem_nm);
}
</script>