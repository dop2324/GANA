<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="publicFrm" name="publicFrm" method="post" action="<c:url value="${managerDir }/cms/board/info/share/public_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="bod_mnuCode"	name="bod_mnuCode"	value="<c:out value="${bod_mnuCode }" />" />
<input type="hidden" id="cmd"			name="cmd" 	/>
<div class="board_area">
	<table class="board_table">
		<thead>
			<tr>
				<th scope="col" class="wp15">순번</th>
				<th scope="col">메뉴명</th>
				<th scope="col" class="wp15">관리</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<c:choose>
				<c:when test="${existsFlag == true }">
					<td colspan="3">이미 등록되어 있습니다.</td>
				</c:when>
				<c:otherwise>
					<td>추가</td>
					<td>
						<input type="hidden" id="pub_code" name="pub_code" class="shareCode" value="<c:out value="${menuVo.mnu_code }" />" />
						<input type="text" id="pub_nm" name="pub_nm" class="shareNm inp_txt wp90" readonly="readonly" value="<c:out value="${menuVo.mnu_nm }" />" />
					</td>
					<td><a href="#self" class="btn" onclick="saveData()">저장</a></td>
				</c:otherwise>
			</c:choose>
			</tr>
		<c:forEach var="vo" items="${publicList}" varStatus="status">
			<tr>
				<td><c:out value="${status.count }" /></td>
				<td class="taL"><c:out value="${vo.mnu_path }" /></td>
				<td><a href="#self" class="btn pink" onclick="deleteData('<c:out value="${vo.pub_code }" />')">삭제</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</form>

<script>
function saveData()
{
	if($("#pub_code").val() == "")
	{
		alert("통합할 게시판을 선택하여 주십시요");
		return false;
	}
	
	$("#publicFrm").submit();
}

function deleteData(pub_code)
{
	if(confirm("선택한 게시판을 통합 삭제 하시겠습니까?")) 
	{
		$("#pub_code").val(pub_code);
		$("#cmd").val(32);
		$("#publicFrm").submit();
	}
}
</script>