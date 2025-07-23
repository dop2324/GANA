<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="surveyFrm" name="surveyFrm" method="post" enctype="multipart/form-data" action="<c:url value="${managerDir }/cms/survey/question/question_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="survey_sn"		name="survey_sn"	value="<c:out value="${vo.survey_sn }" />" />
<input type="hidden" id="qus_sn"		name="qus_sn"		value="<c:out value="${quesVo.qus_sn }" />" />
<input type="hidden" id="parm_examSn"	name="parm_examSn"	/>

<div class="form_style">
	<dl>
		<dt><label for="qus_question">설문 문항</label></dt>
		<dd>
			<input type="text" id="qus_question" name="qus_question" maxlength="128" value="<c:out value="${quesVo.qus_question }" />" placeholder="설문 문항" />
		</dd>
		<dt><label for="qus_cn">문항 설명</label></dt>
		<dd>
			<textarea id="qus_cn" name="qus_cn" maxlength="500" placeholder="문항 설명"><c:out value="${quesVo.qus_cn}" escapeXml="false" /></textarea>
		</dd>
		<dt>문항 구분</dt>
		<dd>
			<input type="checkbox" id="qus_ty" name="qus_ty" value="1" <c:if test="${quesVo.qus_ty == 1 }">checked="checked"</c:if> /> 
			<label for="qus_ty">다항</label>
			<input type="checkbox" id="qus_required" name="qus_required" value="1" <c:if test="${quesVo.qus_required == 1 }">checked="checked"</c:if> /> 
			<label for="qus_required">필수선택</label>
		</dd>
		<dt>사용유무</dt>
		<dd>
			<c:set var="qus_sttus" value="1" />
			<c:if test="${quesVo != null }"><c:set var="qus_sttus" value="${quesVo.qus_sttus}" /></c:if>
			<input type="radio" id="qus_sttus_1" name="qus_sttus" value="1" <c:if test="${qus_sttus == 1 }">checked="checked"</c:if> /> 
			<label for="qus_sttus_1">사용</label>
			<input type="radio" id="qus_sttus_0" name="qus_sttus" value="0" <c:if test="${qus_sttus == 0 }">checked="checked"</c:if> /> 
			<label for="qus_sttus_0">중지</label>
		</dd>
		<dt>보기</dt>
		<dd>
			<div><a href="#self" class="btn" onclick="addExamRow()">보기추가</a></div>
			<div id="examList" style="width:100%;">
				<c:forEach var="v" items="${quesVo.surveyExample}" varStatus="status">
					<div style="margin:15rem 0;">
						<input type="hidden" id="exam_sn<c:out value="${v.exam_sn }" />" name="exam_sn" value="<c:out value="${v.exam_sn }" />" />
						<input type="text" id="exam_example<c:out value="${v.exam_sn }" />" name="exam_example" maxlength="128" placeholder="보기" value="<c:out value="${v.exam_example }" />" style="width:calc(100% - 92rem);"/>
						
						<select id="exam_ty<c:out value="${v.exam_sn }" />" name="exam_ty">
							<option value="0" <c:if test="${v.exam_ty == 0 }"> selected="selected"</c:if>>객관식</option>
							<option value="1" <c:if test="${v.exam_ty == 1 }"> selected="selected"</c:if>>주관식</option>
						</select>
						<input type="file" id="exam_examFile<c:out value="${status.index }" />" name="exam_examFile<c:out value="${status.index }" />" />
						
						<c:if test="${v.exam_file != null && v.exam_file != '' }">
							<input type="text" id="org_examFile<c:out value="${status.index }" />" name="org_examFile<c:out value="${status.index }" />" value="${v.exam_file }" class="inp_txt wp40" />
							<input type="checkbox" id="deleteFile<c:out value="${status.index }" />" name="deleteFile<c:out value="${status.index }" />" value="1" /> 
							<label for="deleteFile">삭제</label>
						</c:if>
						<input type="text" id="exam_linkUrl<c:out value="${v.exam_sn }" />" name="exam_linkUrl" maxlength="128" placeholder="링크주소(https://)" value="<c:out value="${v.exam_linkUrl }" />" />		
						<div>
							<input type="radio" id="exam_sttus_1" name="exam_sttus<c:out value="${status.index }" />" value="1" <c:if test="${v.exam_sttus == 1 }">checked="checked"</c:if> /> 
							<label for="exam_sttus_1">사용</label>
							<input type="radio" id="exam_sttus_0" name="exam_sttus<c:out value="${status.index }" />" value="0" <c:if test="${v.exam_sttus == 0 }">checked="checked"</c:if> /> 
							<label for="exam_sttus_0">중지</label>
							<a href="#self" class="btn pink" onclick="deleteExample(<c:out value="${v.exam_sn }" />)">삭제</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

<div class="survey_area survey_paper">
	<div class="type_ex taR">
		<span>사용</span>
		<span class="not_use">중지</span>
	</div>
	<div class="form_style">
		<dl>
			<c:forEach var="qVo" items="${queslist}" varStatus="status">
				<dt>
					<div class="type_list">
						<c:choose>
							<c:when test="${qVo.qus_sttus == 1 }"><span class="use">사용</span></c:when>
							<c:when test="${qVo.qus_sttus == 0 }"><span class="not_use">중지</span></c:when>
						</c:choose>
						<c:choose>
							<c:when test="${qVo.qus_ty == 0 }"><span>단항</span></c:when>
							<c:when test="${qVo.qus_ty == 1 }"><span>다항</span></c:when>
						</c:choose>
						<c:choose>
							<c:when test="${qVo.qus_required == 1 }"><span>필수</span></c:when>
							<c:when test="${qVo.qus_required == 0 }"><span>비필수</span></c:when>
						</c:choose>
					</div>
					<fmt:formatNumber value="${status.count}" groupingUsed="true"/>.&nbsp;
					<c:out value="${qVo.qus_question }" />
					<c:if test="${qVo.qus_cn != ''}"><div class="sub_title"><c:out value="${util:htmlEncode(qVo.qus_cn) }" escapeXml="false" /></div></c:if>
					
					<c:set var="updLink" value="page.do?${queryString}survey_sn=${qVo.survey_sn}&pageNo=${pageNo}" />
					<div class="btn_wrap">
						<a href="<c:out value="${updLink }&qus_sn=${qVo.qus_sn }&cmd=8"/>" class="btn blue" >수정</a>
						<a href="#self" class="btn pink" onclick="deleteData(${qVo.qus_sn })">삭제</a>
					</div>
				</dt>
				<dd>
					<c:if test="${fn:length(qVo.surveyExample) > 0 }">
					<c:forEach var="examVo" items="${qVo.surveyExample}" varStatus="example">
						<div>
							<c:choose>
								<c:when test="${examVo.exam_sttus == 1 }"><span class="num"><c:out value="${status.count }" />-<c:out value="${example.count}" /></span></c:when>
								<c:when test="${examVo.exam_sttus == 0 }"><span class="num not_use"><c:out value="${status.count }" />-<c:out value="${example.count}" /></span></c:when>
							</c:choose>
							<c:out value="${examVo.exam_example }" />
							<c:if test="${examVo.exam_linkUrl != null }">
								<a href="<c:out value="${examVo.exam_linkUrl }" />" target="_blank" title="새창열림">링크주소 </a>
							</c:if>
						
							<c:if test="${examVo.exam_file != null  && examVo.exam_file != '' }">
								<c:out value="${util:getImgTag(qVo.getWebDir(), examVo.exam_file, 50, examVo.exam_example) }" escapeXml="false" />
							</c:if>

							<c:out value="${examVo.exam_ty == 1 ? '[주관식]':'' }" />
						</div>
					</c:forEach>
					</c:if>
				</dd>
			</c:forEach>
		</dl>
	</div>
</div>

<script type="text/jQuryTemplate" id="formExam">
<div id="ExamRow_{{id}}" style="margin:15rem 0;">
	<input type="hidden" id="exam_sn_{{id}}" name="exam_sn"/>
	<input type="text" id="exam_example_{{id}}" name="exam_example" maxlength="128" placeholder="보기" style="width:calc(100% - 92rem);" />
	
	<select id="exam_ty_{{id}}" name="exam_ty">
		<option value="0">객관식</option>
		<option value="1">주관식</option>
	</select>
	<input type="file" id="exam_examFile_{{id}}" name="exam_examFile{{id}}" />
	<input type="text" id="exam_linkUrl_{{id}}" name="exam_linkUrl" maxlength="128" placeholder="링크주소(https://)" />		
	<div>
		<input type="radio" id="exam_sttus_1_{{id}}" name="exam_sttus{{id}}" value="1" checked="checked" /> 
		<label for="exam_sttus_1_{{id}}">사용</label>
		<input type="radio" id="exam_sttus_0_{{id}}" name="exam_sttus{{id}}" value="0" /> 
		<label for="exam_sttus_0_{{id}}">중지</label>
		<a href="#self" class="btn pink" onclick="deleteExam({{id}})">삭제</a>
	</div>
</div>
</script>
<script>
function addExamRow()
{
	var divCnt = $("#examList div").length;
	var template = $("#formExam").html();
	template = template.replace(/{{id}}/gi, divCnt);
	$("#examList").append(template);
}

function deleteExam(id)
{
	$("#ExamRow_"+id).remove();
}

function saveData() {
	if($("#qus_question").val() == "") {
		alert("설문 문항을 입력하세요");
		$("#qus_question").select()
		return false;
	}
	
	$("#surveyFrm").submit();
}

function deleteData(qus_sn)
{
	if(confirm("문항을 삭제 하시겠습니까?")) {
		$("#qus_sn").val(qus_sn);
		$("#cmd").val(32);
		$("#surveyFrm").submit();
	}
}

function deleteExample(exam_sn){
	if(confirm("보기를 삭제하시겠습니까?")) {
		$("#parm_examSn").val(exam_sn);
		$("#cmd").val(16+32);
		$("#surveyFrm").submit();
	}
}
</script>

