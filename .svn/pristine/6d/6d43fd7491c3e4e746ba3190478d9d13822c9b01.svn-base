<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="surveyFrm" name="surveyFrm" method="post" action="<c:url value="${managerDir }/cms/survey/survey_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="survey_sn"		name="survey_sn"	value="<c:out value="${vo.survey_sn }" />" />

<div class="form_style">
	<dl>
		<dt><label for="survey_ttl">설문 제목</label></dt>
		<dd>
			<input type="text" id="survey_ttl" name="survey_ttl" maxlength="128" value="<c:out value="${vo.survey_ttl }" />" placeholder="설문 제목" />
		</dd>
		<dt>설문 기간</dt>
		<dd class="grow">
			<input type="text" id="survey_startDt" name="survey_startDt" class="datepicker" value="<fmt:formatDate value="${vo.survey_startDt}" pattern="yyyy-MM-dd"/>" placeholder="시작 일자" />
			
			<fmt:formatDate var="survey_startDtHH" value="${vo.survey_startDt}" pattern="HH" />
			<select id="survey_startDtHH" name="survey_startDtHH">
				<option value="">시간</option>
			<c:forEach var="i" begin="1" end="23">
				<fmt:formatNumber var="no" minIntegerDigits="2" value="${i}" type="number"/>
				<option value="<c:out value="${no }" />" <c:if test="${survey_startDtHH == no }">selected="selected"</c:if>><c:out value="${no }" /></option>
			</c:forEach>
			</select>
			~ 
			
			<input type="text" id="survey_endDt" name="survey_endDt" class="datepicker" value="<fmt:formatDate value="${vo.survey_endDt}" pattern="yyyy-MM-dd"/>" placeholder="종료 일자" />
			
			<fmt:formatDate var="survey_endDtHH" 	value="${vo.survey_endDt}" pattern="HH" />
			<fmt:formatDate var="survey_endDtHHmm" 	value="${vo.survey_endDt}" pattern="HH:mm" />
			<c:if test="${survey_endDtHHmm == '23:59' }"><c:set var="survey_endDtHH" value="" /></c:if>
			
			<select id="survey_endDtHH" name="survey_endDtHH">
				<option value="">시간</option>
			<c:forEach var="i" begin="1" end="23">
				<fmt:formatNumber var="no" minIntegerDigits="2" value="${i}" type="number"/>
				<option value="<c:out value="${no }" />" <c:if test="${survey_endDtHH == no }">selected="selected"</c:if>><c:out value="${no }" /></option>
			</c:forEach>
			</select>
		</dd>
		<dt>본인확인</dt>
		<dd>
			<c:set var="survey_authYn" value="1" />
			<c:if test="${vo != null }"><c:set var="survey_authYn" value="${vo.survey_authYn}" /></c:if>
			<input type="radio" id="survey_authYn_1" name="survey_authYn" value="1" class="inp_rad" <c:if test="${survey_authYn == 1 }">checked="checked"</c:if> /> 
			<label for="survey_authYn_1">사용 (회원)</label>
			<input type="radio" id="survey_authYn_0" name="survey_authYn" value="0" class="inp_rad" <c:if test="${survey_authYn == 0 }">checked="checked"</c:if> /> 
			<label for="survey_authYn_0">사용안함</label>
		</dd>
		<dt>사용유무</dt>
		<dd>
			<c:set var="survey_sttus" value="1" />
			<c:if test="${vo != null }"><c:set var="survey_sttus" value="${vo.survey_sttus}" /></c:if>
			<input type="radio" id="survey_sttus_1" name="survey_sttus" value="1" class="inp_rad" <c:if test="${survey_sttus == 1 }">checked="checked"</c:if> /> 
			<label for="survey_sttus_1">사용</label>
			<input type="radio" id="survey_sttus_0" name="survey_sttus" value="0" class="inp_rad" <c:if test="${survey_sttus == 0 }">checked="checked"</c:if> /> 
			<label for="survey_sttus_0">중지</label>
		</dd>
		<dt><label for="survey_cn">설문 내용</label></dt>
		<dd>
			<textarea id="survey_cn" name="survey_cn" maxlength="2000" placeholder="설문 내용"><c:out value="${vo.survey_cn}" escapeXml="false" /></textarea>
		</dd>
	<c:if test="${vo != null }">
		<dt>수정ID / 수정일시</dt>
		<dd><c:out value="${vo.survey_regID }" /> / <c:out value="${vo.survey_regIP }" /> / <fmt:formatDate value="${vo.survey_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
	</c:if>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	<c:if test="${vo != null }">
		<a href="#self" class="btn pink" onclick="deleteData()">삭제</a>
	</c:if>
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

<script>
function saveData() {
	if($("#survey_ttl").val() == "") {
		alert("설문제목을 입력하세요");
		$("#survey_ttl").select()
		return false;
	}
	
	$("#surveyFrm").submit();
}

function deleteData()
{
	if(confirm("삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#surveyFrm").submit();
	}
}
</script>

