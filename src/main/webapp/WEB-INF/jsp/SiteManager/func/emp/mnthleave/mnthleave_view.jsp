<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="mnthFrm" name="mnthFrm" method="post" action="<c:url value="${managerDir }/func/emp/mnthleave/mnthleave_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />

<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="mnth_sn"		name="mnth_sn"		value="<c:out value="${mnthVo.mnth_sn }" />" />

<div class="form_style">
	<dl>
		<dt>직원 ID</dt>
		<dd>
			<c:set var="emp_id" value="${empVo.emp_id }" />
			<c:if test="${mnthVo != null }"><c:set var="emp_id" value="${mnthVo.emp_id }" /></c:if>
			<input id="emp_id" name="emp_id" maxlength="32" <c:if test="${emp_id != null}">readonly="readonly"</c:if> value="<c:out value="${emp_id }" />" placeholder="직원 ID" />
		</dd>
		<dt>월차 사용일자</dt>
		<dd>
			<c:set var="mnth_startDt" value="" />
			<c:set var="mnth_endDt" value="" />
			
			<c:forEach var="v" items="${mnthVo.mnthDtList}" varStatus="status">
				<c:choose>
					<c:when test="${status.first}">
						<c:set var="mnth_startDt" value="${v.mnth_dt }" />
					</c:when>
					<c:when test="${status.last}">
						<c:set var="mnth_endDt" value="${v.mnth_dt }" />
					</c:when>
				</c:choose>
			</c:forEach>
			
			<label class="" for="mnth_startDt">
				<input type="date" id="mnth_startDt" name="mnth_startDt" value="<fmt:formatDate value="${mnth_startDt }" pattern="yyyy-MM-dd" />" />
			</label>
			<span class=""> ~ </span>
			<label class="" for="mnth_endDt">
				<input type="date" id="mnth_endDt" name="mnth_endDt" value="<fmt:formatDate value="${mnth_endDt }" pattern="yyyy-MM-dd" />" />
			</label>
		</dd>
		<dt>월차 종류</dt>
		<dd>
			<select id="mnth_se" name="mnth_se">
				<option value="">월차 종류</option>
			<c:forEach var="v" items="${mnthcdList}" varStatus="status">
				<c:if test="${v.code_sttus == 1 }">
					<option value="<c:out value="${v.code_val }" />"<c:if test="${v.code_val == mnthVo.mnth_se }">selected="selected"</c:if>><c:out value="${v.code_nm }" /></option>
				</c:if>
			</c:forEach>
			</select>
		</dd>
		<dt>월차 메모</dt>
		<dd colspan="3">
			<input type="text" id="mnth_memo" name="mnth_memo" class="inp_txt wp60" maxlength="256" value="<c:out value="${mnthVo.mnth_memo }" />" />
		</dd>
		<dt>월차 사유</dt>
		<dd colspan="3">
			<textarea id="mnth_rsn" name="mnth_rsn" class="inp_txt" maxlength="1024"><c:out value="${mnthVo.mnth_rsn }" /></textarea>
		</dd>
	</dl>
</div>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

</form>


<form id="mnthSrchFrm" name="mnthSrchFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="2"/>
<input type="hidden" id="emp_id"		name="emp_id" 		value="<c:out value="${emp_id}" />"/>

<div class="board_search" style="margin-top:40rem;">
	<c:set var="year"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set> 
	<select name="srchYear" id="srchYear">
		<option value="">년도</option>
		<c:forEach var="i" begin="${year -10 }" end="${year}" step="1">
			<option value="<c:out value="${i }" />" <c:if test="${i == srchYear }">selected="selected"</c:if>><c:out value="${i }" /></option>
		</c:forEach>
	</select>
	<a href="#self" onclick="searchData()">검색</a>
</div>
</form>

<div class="board_area">
	<table class="board_table">
		<thead>
			<tr>
				<th scope="col" class="wp12">월차 일자</th>
				<th scope="col" class="wp10">월차 일수</th>
				<th scope="col" class="wp10">월차 종류</th>
				<th scope="col">월차 사유</th>
				<th scope="col" class="wp15">메모</th>
				<th scope="col" class="wp15">등록 일시</th>
				<th scope="col" class="wp12">관리</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(mnthList) == 0 }">
			<tr><td colspan="6">데이터가 없습니다</td></tr>
		</c:if>
		<c:forEach var="vo" items="${mnthList}" varStatus="status">
			<tr>
				<td class="fs14">
					<c:forEach var="v" items="${vo.mnthDtList}" varStatus="status">
						<p><fmt:formatDate value="${v.mnth_dt }" pattern="yyyy-MM-dd" /></p>
					</c:forEach>
				</td>
				<td><c:out value="${vo.mnth_days }" /></td>
				<td>
					<c:forEach var="v" items="${mnthcdList}" varStatus="s">
						<c:if test="${v.code_sttus == 1 && v.code_val == vo.mnth_se}">
							<c:out value="${v.code_nm }" />
						</c:if>
					</c:forEach>
					
				</td>
				<td><c:out value="${vo.mnth_rsn }" /></td>
				<td><c:out value="${vo.mnth_memo }" /></td>
				<td><fmt:formatDate value="${vo.mnth_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<a href="<c:out value="?${queryString}cmd=16&mnth_sn=${vo.mnth_sn }" />" class="btn blue">수정</a>

					<a href="#self" class="btn pink" onclick="deleteData(<c:out value="${vo.mnth_sn }" />)">삭제</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<script>
function searchData()
{
	$("#mnthSrchFrm").submit();
}

function deleteData(mnth_sn) {
	if(confirm("삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#mnth_sn").val(mnth_sn);
		$("#mnthFrm").submit();
	}
}

function saveData()
{
	if($("#emp_id").val() == "") {
		alert("선택 직원이 없습니다");
		return false;
	}
	
	if($("#mnth_startDt").val() == "") {
		alert("월차 시작일자를 선택하여 주십시요");
		$("#mnth_startDt").select();
		return false;
	}
	if($("#mnth_se").val() == "") {
		alert("월차 종류를 선택하여 주십시요");
		$("#mnth_se").select();
		return false;
	}
	
	$("#mnthFrm").submit();
}
</script>
