<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="survey_area survey_result">
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

	<div class="form_style">
		<dl>
		<c:forEach var="vo" items="${questionlist}" varStatus="status">
			<dt>
				<c:out value="${status.count }" />.&nbsp;<c:out value="${vo.qus_question }"/>
                <c:if test="${vo.qus_cn != null and vo.qus_cn != ''}">
                	<div class="sub_title">(<c:out value="${vo.qus_cn }"/>)</div>
                </c:if>
		 	</dt>
			<c:if test="${fn:length(vo.surveyExample) > 0 }">
			<dd>
				<c:set var="tmpSub_cnt" value="" />
				<c:forEach var="examVo" items="${vo.surveyExample}" varStatus="example">

					<c:set var="per" value="${(examVo.exam_voteCnt / examVo.exam_totCnt) * 100 }" />
					<c:set var="widthPer" value="" />
					<c:if test="${per > 0 }"><c:set var="widthPer" value="style='width:${per }%;'" /></c:if>
					<div class="result_box">
						<div class="quest">
							<div><span><c:out value="${status.count }" />-<c:out value="${example.count }" /></span><span><c:out value="${examVo.exam_example }" /></span></div>
							<c:if test="${examVo.exam_file != null  && examVo.exam_file != '' }">
								<c:out value="${util:getImgTag(vo.getWebDir(), examVo.exam_file, 300, examVo.exam_example) }" escapeXml="false" />
							</c:if>
							<c:if test="${examVo.exam_linkUrl != ''}">
								<a href="<c:out value="${examVo.exam_linkUrl }" />" target="_blank" title="새창">링크보기</a>
							</c:if>
							<c:if test="${fn:length(vo.surveySubject) > 0 }">
								<c:set var="subflag" value="false" />
								<c:forEach var="sub" items="${vo.surveySubject}" varStatus="subject">
									<c:if test="${examVo.exam_sn == sub.exam_sn && subflag == false }">
										<c:set var="subflag" value="true" />
									</c:if>
								</c:forEach>
								
								<c:if test="${subflag == true}">
								<ul class="answer_list">
									<c:if test="${tmpSub_cnt != examVo.exam_sn }"><c:set var="sub_cnt" value="1" /></c:if>
									<c:forEach var="sub" items="${vo.surveySubject}" varStatus="subject">
										<c:if test="${examVo.exam_sn == sub.exam_sn }">
										<li><span><c:out value="${sub_cnt }" />.</span><c:out value="${sub.sub_cn }" /></li>
										<c:set var="sub_cnt" value="${sub_cnt+1 }" />
										</c:if>
									</c:forEach>
								</ul>
								</c:if>
							</c:if>
						</div>
						<div class="graph">
							<div class="bar"><span ${widthPer }></span></div>
							<p class="count"><fmt:formatNumber value="${examVo.exam_voteCnt}" groupingUsed="true"/> / <fmt:formatNumber value="${examVo.exam_totCnt}" groupingUsed="true"/></p>
						</div>
					</div>

				</c:forEach>
			</dd>
		 	</c:if>
		 </c:forEach>
		</dl>
	</div>



	<div class="board_btn">
		<a href="<c:out value="${listLink }"/>">목록</a>
	</div>
</div>

<script>

</script>
