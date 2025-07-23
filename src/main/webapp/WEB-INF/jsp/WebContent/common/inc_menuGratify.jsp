<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



	<%-- 페이지 만족도 --%>
	<div class="page_research_box">
		<h3 class="hidden">페이지 만족도조사</h3>
		<form id="gratifyFrm" name="gratifyFrm" method="post" action="<c:url value="${publicDir }/cms/menu/gratify/gratify_process.do" />">
		<double-submit:preventer/>
		<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${menuVo.site_code }" />" />
		<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${menuVo.mnu_code }" />" />

		<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
		<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />

			<div class="page_research_box_form form-group">
				<div class="form-tit">
					<h4>현재 페이지의 내용과 사용편의성에 대해서 만족하십니까?</h4>
				</div>
				<div class="form-conts">
					<div class="krds-check-area">
						<div class="krds-form-check medium">
							<input type="radio" name="gra_point" id="gra_point5" value="5" />
							<label for="gra_point5">매우만족</label>
						</div>
						<div class="krds-form-check medium">
							<input type="radio" name="gra_point" id="gra_point4" value="4" />
							<label for="gra_point4">만족</label>
						</div>
						<div class="krds-form-check medium">
							<input type="radio" name="gra_point" id="gra_point3" value="3" />
							<label for="gra_point3">보통</label>
						</div>
						<div class="krds-form-check medium">
							<input type="radio" name="gra_point" id="gra_point2" value="2" />
							<label for="gra_point2">불만족</label>
						</div>
						<div class="krds-form-check medium">
							<input type="radio" name="gra_point" id="gra_point1" value="1" />
							<label for="gra_point1">매우불만족</label>
						</div>
					</div>
		
					<div class="input-group">
						<input type="text" id="gra_commt" name="gra_commt" maxlength="128" class="krds-input small" placeholder="여러분의 소중한 의견을 남겨주세요." />
						<button type="button" class="krds-btn small" onclick="saveGratify();">평가하기</button>
					</div>			
				</div>
			</div>
		</form>
		<%-- 부서명 또는 담당자 있을때 --%>
		<c:if test="${menuVo.mnu_deptNm != '' || menuVo.mnu_staffTelno != '' }">
			<div class="page_manager">
				<p><strong>담당부서</strong><c:out value="${menuVo.mnu_deptNm }" /></p>
				<p><strong>연락처</strong><c:out value="${menuVo.mnu_staffTelno }" /></p>
				<c:if test="${fn:indexOf(menuVo.mnu_ty, 'page') > -1}">
					<p class="update"><strong>최종수정일</strong><fmt:formatDate value="${menuVo.mnu_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
				</c:if>
			</div>
		</c:if>
	</div>

<script>
function saveGratify() {
	if(confirm("만족도 등록 하시겠습니까?")) {
		$("#gratifyFrm").submit();
	}
}
</script>
