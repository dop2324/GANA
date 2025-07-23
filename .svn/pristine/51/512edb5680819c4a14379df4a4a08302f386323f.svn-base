<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="survey_area survey_paper">
	<div class="survey_top">
		<h4><c:out value="${surveyVo.survey_ttl }" /></h4>
		<div class="date">
			<strong>설문기간</strong>
			<span>
				<fmt:formatDate value="${surveyVo.survey_startDt}" pattern="yyyy-MM-dd HH" />
				~
				<fmt:formatDate value="${surveyVo.survey_endDt}" pattern="yyyy-MM-dd HH" />
			</span>
		</div>
		<div><c:out value="${surveyVo.survey_cn}" escapeXml="false" /></div>
	</div>

	<form id="surveyfrm" name="surveyfrm" method="post" action="<c:out value="${publicDir }/cms/survey/survey_process.do" />">
	<double-submit:preventer/>
	<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="survey_sn" 	name="survey_sn" 	value="<c:out value="${survey_sn }" />" />
	<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />

	<div class="form_style">
		<dl>
		<c:if test="${fn:length(questionlist) == 0 }">
			<dt>설문 문항이 없습니다.</dt>
			<dd>설문 문항이 없습니다.</dd>
		</c:if>
		<c:forEach var="vo" items="${questionlist}" varStatus="status">
			<dt>
		 		<c:out value="${status.count }" />.&nbsp;
		 		<input type="hidden" name="qus_sn" value="${vo.qus_sn }" />

                <c:out value="${vo.qus_question }"/>
                <c:choose>
					<c:when test="${vo.qus_required == 1 }">[필수]</c:when>
					<c:when test="${vo.qus_required == 0 }">[비필수]</c:when>
				</c:choose>
                <c:if test="${vo.qus_cn != null and vo.qus_cn != ''}">
                	<div class="sub_title">(<c:out value="${vo.qus_cn }"/>)</div>
                </c:if>
		 	</dt>
			<c:if test="${fn:length(vo.surveyExample) > 0 }">
			<dd>
				<c:forEach var="examVo" items="${vo.surveyExample}" varStatus="example">
					<div>
						<input type="${vo.qus_ty == 0 ? 'radio':'checkbox' }" id="exam_vote<c:out value="${examVo.exam_sn }" />" name="exam_vote<c:out value="${vo.qus_sn }" />" onclick="checkExamVote(<c:out value="${vo.qus_sn }" />, <c:out value="${examVo.exam_sn }" />, <c:out value="${examVo.exam_ty}" />)" value="<c:out value="${examVo.exam_sn }" />" />
						<label for="exam_vote<c:out value="${examVo.exam_sn }" />"><span><c:out value="${status.count }" />-<c:out value="${example.count }" /></span><span><c:out value="${examVo.exam_example }" /></span></label>

						<c:if test="${examVo.exam_file != null  && examVo.exam_file != '' }">
							<label for="exam_vote<c:out value="${examVo.exam_sn }" />">
							<c:out value="${util:getImgTag(vo.getWebDir(), examVo.exam_file, 300, examVo.exam_example) }" escapeXml="false" />
							</label>
						</c:if>

						<c:if test="${examVo.exam_linkUrl != ''}">
							<a href="<c:out value="${examVo.exam_linkUrl }" />" target="_blank" title="새창">링크보기</a>
						</c:if>

						<c:if test="${examVo.exam_ty == 1 }">
							<input type="text" style="display:none" id="sub_cn<c:out value="${examVo.exam_sn }" />" name="sub_cn<c:out value="${examVo.exam_sn }" />" maxlength="100" class="wp70" placeholder="<c:out value="${examVo.exam_example }" />" />
							<%--
							<input type="hidden"	name="sub_sn<c:out value="${vo.qus_sn }" />" value="<c:out value="${examVo.exam_sn }" />" />
							<input type="text" id="sub_cn<c:out value="${examVo.exam_sn }" />" name="sub_cn<c:out value="${examVo.exam_sn }" />" maxlength="100" class="wp70" onblur="checkExamVote(<c:out value="${vo.qus_sn }" />, <c:out value="${examVo.exam_sn }" />)" placeholder="<c:out value="${examVo.exam_example }" />" />
							 --%>
						</c:if>
					</div>
				</c:forEach>
			</dd>
		 	</c:if>
		 </c:forEach>
		</dl>
	</div>
	</form>


	<div class="board_btn">
		<c:if test="${surveyVo.survey_dateEnd == 1 && surveyVo.survey_sttus == 1}">
			<c:if test="${fn:length(questionlist) > 0 }">
			<a href="#self" onclick="saveData()">참여하기</a>
			</c:if>
		</c:if>
		<a href="<c:out value="${listLink }"/>">목록</a>
	</div>
</div>

<script>
function checkExamVote(qus_sn, exam_sn, exam_ty) {
	$("input[name='exam_vote"+qus_sn+"']").each(function() {
		var value = $(this).val();
		var checked = $(this).prop("checked");
		console.log(qus_sn+":"+exam_sn+":"+exam_ty+" ===> "+value);

		if(value != exam_sn && exam_ty == 0) {
			$("#sub_cn"+value).val("");
			$("#sub_cn"+value).hide();
		}else{
			$("#sub_cn"+value).show();
		}
	});
}
/*
var subCn;
function checkExamVote(qus_sn, exam_sn) {
	$("input[name='exam_vote"+qus_sn+"']").prop("checked", false);
	$("#exam_vote"+exam_sn).prop("checked", true);
	if(subCn != null) {
		$("#sub_cn"+subCn).val("");
	}
	subCn = exam_sn;
}
*/
function saveData(frm) {
	<c:forEach var="vo" items="${questionlist}" varStatus="status">
		<c:if test="${vo.qus_required == 1}">
			if(!$("input:${vo.qus_ty == 0 ? 'radio':'checkbox' }[name='exam_vote${vo.qus_sn }']").is(":checked"))
			{
				alert("'<c:out value="${vo.qus_question}" />' 문항을 선택하여 주십시요");
				$("#exam_vote${vo.qus_sn }").select();
				return false;
			}
		</c:if>
	</c:forEach>

	$("#surveyfrm").submit();
}
</script>
