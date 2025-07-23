<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<%-- 머릿말 --%>
	<div class="board_header">
		<c:out value="${infoVo.inf_header }" escapeXml="false" />
	</div>

	<form id="boardFrm" name="boardFrm" method="post" action="<c:out value="${actionMenuLink }" />">
	<double-submit:preventer/>
	<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />

	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

	<div class="board_search">
		<div class="date">
			<label class="hidden" for="srchSDate">검색시작일 선택</label>
			<input type="text" id="srchSDate" name="srchSDate" value="${srchMap.srchSDate }" class="datepicker" placeholder="검색시작일" title="YYYY-MM-DD 순으로 작성해주세요." />
			<span>~</span>
			<label class="hidden" for="srchEDate">검색종료일 선택</label>
			<input type="text" id="srchEDate" name="srchEDate" value="${srchMap.srchEDate }" class="datepicker" placeholder="검색종료일" title="YYYY-MM-DD 순으로 작성해주세요."  />
		</div>
		<c:if test="${infoVo.inf_fncUsrGroup == 1 && infoVo.boardGroupList != null && fn:length(infoVo.boardGroupList) > 0 }">
			<label class="hidden" for="srchBgpSn">분류 선택</label>
			<select id="srchBgpSn" name="srchBgpSn">
			<option value="">분류 전체</option>
				<c:forEach var="v" items="${infoVo.boardGroupList}" varStatus="s">
					<option value="${v.bgp_sn }" <c:if test="${v.bgp_sn == srchMap.srchBgpSn}">selected="selected"</c:if> ><c:out value="${v.bgp_nm }" /></option>
				</c:forEach>
			</select>
		</c:if>

		<label class="hidden" for="srchColumn">검색조건 선택</label>
		<select id="srchColumn" name="srchColumn">
			<option>전체</option>
			<option value="bod_ttl"	<c:if test="${srchMap.srchColumn == 'bod_ttl'}">selected="selected"</c:if>>제목</option>
			<option value="con_nm" <c:if test="${srchMap.srchColumn == 'con_nm'}">selected="selected"</c:if>>작성자</option>
			<option value="con_cn" <c:if test="${srchMap.srchColumn == 'con_cn'}">selected="selected"</c:if>>내용</option>
		</select>

		<label class="hidden" for="srchKwd">검색어 입력</label>
		<input type="text" id="srchKwd" name="srchKwd" value="<c:out value="${srchMap.srchKwd }" />" placeholder="검색어를 입력하세요."/>
		<input type="submit" value="검색" />
	</div>

	</form>

	<div class="board_page">
		현재 페이지 <strong><fmt:formatNumber value="${pageNo}" groupingUsed="true"/></strong>
		/
		전체 페이지 <fmt:formatNumber value="${totalPage}" groupingUsed="true"/>
	</div>

	<%-- 1차 게시판 스킨별 분기 --%>
	<div class="board_area">
		<c:choose>
			<%-- 사용자 필드 --%>
			<c:when test="${infoVo.inf_skinType == 'usrField' }">
				<c:import url="/EgovPageLink.do?link=${publicDir }/cms/board/board_listUsrField" />
			</c:when>
			<%-- 일정 달력 --%>
			<c:when test="${infoVo.inf_skinType == 'calendar' }">
				<c:import url="/EgovPageLink.do?link=${publicDir }/cms/board/board_listCalendar" />
			</c:when>
			<%-- 사진 --%>
			<c:when test="${infoVo.inf_skinType == 'photo' }">
				<c:import url="/EgovPageLink.do?link=${publicDir }/cms/board/board_listPhoto" />
			</c:when>

			<%-- 기본, 민원(답변), 링크 --%>
			<c:otherwise>
				<c:import url="/EgovPageLink.do?link=${publicDir }/cms/board/board_listBasic" />
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="${infoVo.inf_skinType != 'calendar' }">
		<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
	</c:if>

	<%-- button --%>
	<div class="board_btn">
		<div class="taR">
			<%--쓰기권한 --%>
			<c:if test="${util:hasPermission(prmVal, 4) }">
				<a href="<c:out value="${listLink }cmd=4" />" class="btn_write">글쓰기</a>
			</c:if>
		</div>
	</div>

	<%-- 꼬릿말 --%>
	<div class="board_footer">
		<c:out value="${infoVo.inf_footer }" escapeXml="false" />
	</div>
</div>

<script>
	$(function(){
		$("title").text("(글목록)"+$("title").text());
	});
</script>
