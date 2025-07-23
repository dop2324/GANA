<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
<form method="post" id="groupFrm" name="groupFrm" action="<c:url value="${managerDir }/cms/member/member_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"			value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="group_mem_id" 	name="mem_id" />
<input type="hidden" id="group_grp_id" 	name="grp_id" />
<input type="hidden" id="group_cmd"		name="cmd" />
<input type="hidden" id="returnUrl"		name="returnUrl"		value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
</form>

<form method="post" id="memberFrm" name="memberFrm" action="<c:out value="${actionMenuLink }" />">
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="usr_id" 		name="usr_id" />
<input type="hidden" id="cmd"			name="cmd" />

	<div class="board_search">

		<c:set var="siteList" value="${defaultSiteMap.sitePrmList }" />
			
		<select title="사이트 선택" name="site_code" id="site_code" class="w150">
			<option value="">사이트 전체</option>
			<c:forEach var="vo" items="${siteList}" varStatus="status">
				<option value="<c:out value="${vo.site_code}" />" <c:if test="${vo.site_code == srchMap.site_code}">selected="selected"</c:if>><c:out value="${vo.site_nm }" /></option>
			</c:forEach>			
		</select>			

		<select title="상태 선택" name="srchSttus" id="srchSttus" class="w100">
			<option value="">활동유무</option>
			<option value="1" <c:if test="${srchMap.srchSttus == '1'}">selected="selected"</c:if>>활동</option>
			<option value="0" <c:if test="${srchMap.srchSttus == '0'}">selected="selected"</c:if>>중지</option>
			
			<option value="6" <c:if test="${srchMap.srchSttus == '6'}">selected="selected"</c:if>>거부</option>
			<option value="9" <c:if test="${srchMap.srchSttus == '9'}">selected="selected"</c:if>>탈퇴</option>			
		</select>	
		
		<input id="srchKwd" name="srchKwd" type="text" value="<c:out value="${srchMap.srchKwd }" />"  placeholder="검색어" />
		<a href="#self" onclick="searchData()">검색</a>
		
		<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>		
	</div>
	
	<div id="changeGroup" style="display:none;" class="p5">	
		<div>
			GROUP_ID : <span id="change_grp_id"></span>
			<a href="#self" onclick="changeGroup()" class="btn">GROUP 변경</a>	
		</div>
	</div>
</form>

<div class="board_area">
	<table class="board_table">
		<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">사이트</th>
			<th scope="col">그룹</th>
			<th scope="col">아이디</th>
			<th scope="col">이름</th>
			<th scope="col">메일</th>
			<th scope="col">접속수</th>
			<th scope="col">상태</th>	
			<th scope="col">등록일시</th>		
			<th scope="col">최근로그인</th>
			<th scope="col" class="wp15">관리</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(memberList) == 0 }">
			<tr>
				<td colspan="11">데이터가 없습니다</td>
			</tr>
		</c:if>
		
	<c:forEach var="vo" items="${memberList}" varStatus="status">
		<tr>
			<td>${no }</td>
			<td><c:out value="${vo.site_nm }"/></td>
			<td><c:out value="${vo.grp_nm }"/></td>
			<td>
				<c:choose>
					<c:when test="${(GRP_ID == 'GRP_000' || GRP_ID == 'GRP_001') && vo.mem_sttus != 9}">
						<a class="blue" href="<c:out value="${managerDir }/cms/member/memberChange.do" />" onclick="loginSUMember(this.href, '<c:out value="${vo.mem_id }"/>');return false;">
						
						<c:out value="${vo.mem_id }"/></a>
					</c:when>
					<c:otherwise>
						
						<del><c:out value="${vo.mem_id }"/></del>
					</c:otherwise>
				</c:choose>
				
			</td>
			<td><c:out value="${vo.mem_nm }"/></td>
			<td><c:out value="${vo.mem_mail }"/></td>
			<td><c:out value="${vo.mem_joinCnt }"/></td>
			<td>
				<c:choose>
					<c:when test="${vo.mem_sttus == 0 }">중지</c:when>
					<c:when test="${vo.mem_sttus == 1 }">활동</c:when>
					<c:when test="${vo.mem_sttus == 6 }">거부</c:when>
					<c:when test="${vo.mem_sttus == 9 }"><span class="red">탈퇴</span></c:when>
				</c:choose>
			</td>
			<td><fmt:formatDate value="${vo.mem_regDt}" pattern="yyyy-MM-dd" /></td>
			<td>
				<fmt:formatDate value="${vo.mem_lastLoginDt}" pattern="yyyy-MM-dd" />
				
				<c:set var="expirePwDtDiff"><fmt:message bundle="${globalsConfig}" key="Globals.expirePwDt" /></c:set>
				<c:if test="${expirePwDtDiff <= vo.mem_chgPwDtDiff}">
					<p class="red webSML">비밀번호 만료 <fmt:formatNumber value="${vo.mem_chgPwDtDiff - expirePwDtDiff}" groupingUsed="true"/> 일 경과</p>
				</c:if>
			</td>
			<td>
				<c:choose>
					<c:when test="${vo.mem_sttus == 9 }">
						<a href="<c:out value="${listLink }cmd=2&mem_id=${vo.mem_id }" />" class="btn">보기</a>
					</c:when>
					<c:otherwise>
						<a href="<c:out value="${listLink }cmd=16&mem_id=${vo.mem_id }" />" class="btn blue">수정</a>
						<a href="#self" onclick="openGroupUser('<c:out value="${vo.mem_id }" />')" class="btn pink">그룹변경</a>
					</c:otherwise>
				</c:choose>
				
			</td>
		</tr>
		<c:set var="no" value="${no -1 }" />
	</c:forEach>
		</tbody>
	</table>
</div>	

<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>

</div>
<script>
function searchData()
{
	$("#memberFrm").submit();
}

function loginSUMember(url, usr_id)
{
	if(confirm("[" + usr_id + "] 로 사용자 전환 하시겠습니까?"))
	{
		//location.href = url+"?usr_id="+usr_id;
		$("#usr_id").val(usr_id);
		$("#memberFrm").attr("action", url);
		$("#memberFrm").submit();
	}
}

function openGroupUser(mem_id)
{
	$("#group_mem_id").val(mem_id);
	
	window.open("<c:out value="${pageContext.request.contextPath}${managerDir }/cms/member/group/groupFrame.do?mnu_code=${mnu_code}" />", "hierarchyWin", "width=600,height=500,resizable=yes,scrollbars=yes");
}

function setGroupMember(groupMem_id, groupMem_nm)
{
	$("#changeGroup").show();
	$("#group_grp_id").val(groupMem_id);
	$("#change_grp_id").text(groupMem_nm);

}

function changeGroup()
{
	var user;
	if(confirm($("#group_mem_id").val()+" 회원 그룹을 "+$("#group_grp_id").val()+"으로 변경  하시겠습니까?")) {
		$("#group_cmd").val(64);
		$("#groupFrm").submit();
	}
}
</script>