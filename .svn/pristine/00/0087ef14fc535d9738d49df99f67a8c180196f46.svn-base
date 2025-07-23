<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- 머릿말 --%>
<div class="board_header">
	<c:out value="${infoVo.inf_header }" escapeXml="false" />
</div>

<form id="boardFrm" name="boardFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

	<div class="board_search">
	
		<div class="date">
			<input type="text" id="srchSDate" name="srchSDate" value="${srchMap.srchSDate }" class="datepicker" readonly="readonly" placeholder="작성일자" />
			<span>~</span>
			<input type="text" id="srchEDate" name="srchEDate" value="${srchMap.srchEDate }" class="datepicker" readonly="readonly" placeholder="작성일자" />
		</div>
		
		<c:if test="${infoVo.inf_fncUsrGroup == 1 && fn:length(infoVo.boardGroupList) > 0}">
			<select id="srchBgpSn" name="srchBgpSn" title="분류선택">
				<option value="">분류 전체</option>
				<c:forEach var="g" items="${infoVo.boardGroupList}" varStatus="status">
					<option value="${g.bgp_sn }" <c:if test="${g.bgp_sn == srchMap.srchBgpSn }">selected="selected"</c:if>><c:out value="${g.bgp_nm }" /></option>
				</c:forEach>
			</select>
		</c:if>

		<c:if test="${infoVo.inf_skinType == 'minwon' || srchBodSkin == 'minwon' }">
			<select id="srchDeptSttus" name="srchDeptSttus" title="답변처리상태">
				<option value="">답변처리상태</option>
				<option value="0" <c:if test="${srchMap.srchDeptSttus == '0'}">selected="selected"</c:if>>신청</option>
				<option value="2" <c:if test="${srchMap.srchDeptSttus == '2'}">selected="selected"</c:if>>부서이첩</option>
				<option value="4" <c:if test="${srchMap.srchDeptSttus == '4'}">selected="selected"</c:if>>담당자지정</option>
				<option value="5" <c:if test="${srchMap.srchDeptSttus == '5'}">selected="selected"</c:if>>답변완료</option>
				<option value="6" <c:if test="${srchMap.srchDeptSttus == '6'}">selected="selected"</c:if>>접수불가</option>
			</select>
		</c:if>

		<select title="게시물 상태" name="srchSttus" id="srchSttus">
			<option value="">게시물 상태</option>
			<option value="1" <c:out value="${srchMap.srchSttus == 1 ? 'selected=\"selected\"':'' }" />>사용</option>
			<option value="0" <c:out value="${srchMap.srchSttus == 0 ? 'selected=\"selected\"':'' }" />>중지</option>
		</select>

		<select id="srchColumn" name="srchColumn" title="검색 유형">
			<option value="">검색</option>
			<option value="bod_ttl"	${srchMap.srchColumn == 'bod_ttl' 	? 'selected=\"selected\"':'' }>제목</option>
			<option value="con_nm"	${srchMap.srchColumn == 'con_nm' 		? 'selected=\"selected\"':'' }>작성자</option>
			<option value="con_cn"	${srchMap.srchColumn == 'con_cn' 	? 'selected=\"selected\"':'' }>내용</option>
		</select>
		<input type="text" class="wp15" id="srchKwd" name="srchKwd" value="<c:out value="${srchMap.srchKwd }" />"  placeholder="검색어" title="검색어 입력"/>
		<a href="#self" onclick="searchData()">검색</a>
		
	</div>

	<div>
		<c:if test="${hasAdm }">
			<c:set var="srchMemember" value="${srchMap.srchMemId }" />
			<c:if test="${srchMap.srchGrpId != '' }"><c:set var="srchMemember" value="${srchMap.srchGrpId }" /></c:if>

			<input type="text" class="wp15" id="srchMemember" name="srchMemember" value="<c:out value="${srchMemember }" />" />
			<input type="hidden" id="srchMemId" name="srchMemId" value="<c:out value="${srchMap.srchMemId }" />" />
			<input type="hidden" id="srchGrpId" name="srchGrpId" value="<c:out value="${srchMap.srchGrpId }" />" />
			<a href="#self" class="btn" onclick="openGroupMember()">회원ID 검색</a>
		</c:if>
		
		<c:if test="${srchMap.srchBodAll != 1 }">
		<c:if test="${util:hasPermission(prmVal, 4)}">
			<a href="#self" class="btn pink" onclick="downloadXls()">XLS Down</a>
			<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>
		</c:if>
		</c:if>
		
	</div>

	<div class="board_page">
		현재 페이지 <strong><fmt:formatNumber value="${pageNo}" groupingUsed="true"/></strong>
		 /
		전체 페이지 <fmt:formatNumber value="${totalPage}" groupingUsed="true"/>
	</div>

	<%-- board skin --%>
	<div class="board_area">
	<c:choose>
		<c:when test="${infoVo.inf_skinType == 'upload' }">
			<c:import url="/EgovPageLink.do?link=${managerDir }/cms/board/board_listUpload" />
		</c:when>
		<c:otherwise>
			<c:import url="/EgovPageLink.do?link=${managerDir }/cms/board/board_listBasic" />
		</c:otherwise>
	</c:choose>
	</div>

	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
</form>

<%-- 꼬릿말 --%>
<div class="board_footer">
	<c:out value="${infoVo.inf_footer }" escapeXml="false" />
</div>

<script>
function searchData()
{
	$("#boardFrm").submit();
}

function downloadXls()
{
	$("#boardFrm").attr("action", "<c:url value="${managerDir }/cms/board/xls/board_xlsDownload.do" />");
	$("#boardFrm").submit();
}

//그룹 회원
function openGroupMember()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/member/groupMember.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popPermission", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setGroupMember(groupMem_ty, groupMem_id, groupMem_name)
{
	$("#srchMemember").val(groupMem_id);
	if(groupMem_ty == "그룹") {
		$("#srchGrpId").val(groupMem_id);
	}else{
		$("#srchMemId").val(groupMem_id);
	}
}
</script>
