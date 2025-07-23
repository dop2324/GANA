<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="survey_area survey_result">
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
				<fmt:formatNumber value="${status.count}" groupingUsed="true"/><c:out value="${qVo.qus_question }" />
				<c:if test="${qVo.qus_cn != ''}">
					<div class="sub_title"><c:out value="${util:htmlEncode(qVo.qus_cn) }" escapeXml="false" /></div>
				</c:if>


				결과/합계<fmt:formatNumber value="${qVo.qus_totCnt}" groupingUsed="true"/>
			</dt>
			<c:if test="${fn:length(qVo.surveyExample) > 0 }">
			<dd>
				<c:set var="tmpSub_cnt" value="" />
				<c:forEach var="examVo" items="${qVo.surveyExample}" varStatus="example">
					<c:set var="per" value="${(examVo.exam_voteCnt / examVo.exam_totCnt) * 100 }" />
					<c:set var="widthPer" value="" />
					<c:if test="${per > 0 }"><c:set var="widthPer" value="style='width:${per }%;'" /></c:if>
					<div class="result_box">
						<div class="quest">
							<div>
								<c:choose>
									<c:when test="${examVo.exam_sttus == 1 }"><span class="num"><c:out value="${status.count }" />-<c:out value="${example.count}" /></span></c:when>
									<c:when test="${examVo.exam_sttus == 0 }"><span class="num not_use"><c:out value="${status.count }" />-<c:out value="${example.count}" /></span></c:when>
								</c:choose>
								<span><c:out value="${examVo.exam_example }" /><c:out value="${examVo.exam_ty == 1 ? '[주관식]':'' }" /></span>
							</div>
							<c:if test="${examVo.exam_file != null  && examVo.exam_file != '' }">
								<c:out value="${util:getImgTag(qVo.getWebDir(), examVo.exam_file, 50, examVo.exam_example) }" escapeXml="false" />
							</c:if>
							<c:if test="${examVo.exam_linkUrl != null }">
								<a href="<c:out value="${examVo.exam_linkUrl }" />" target="_blank" title="새창">링크보기</a>
							</c:if>

							<c:if test="${fn:length(qVo.surveySubject) > 0 }">
								<c:set var="subflag" value="false" />
								<c:forEach var="sub" items="${qVo.surveySubject}" varStatus="subject">
									<c:if test="${examVo.exam_sn == sub.exam_sn && subflag == false }">
										<c:set var="subflag" value="true" />
									</c:if>
								</c:forEach>

								<c:if test="${subflag == true}">
								<ul class="answer_list">
									<c:if test="${tmpSub_cnt != examVo.exam_sn }"><c:set var="sub_cnt" value="1" /></c:if>
									<c:forEach var="sub" items="${qVo.surveySubject}" varStatus="subject">
										<c:if test="${examVo.exam_sn == sub.exam_sn }">
										<li><span><c:out value="${sub_cnt }" />.</span><c:out value="${sub.sub_cn }" /></span></li>
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
</div>


<div class="board_btn">
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>
