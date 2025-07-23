<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="siteFrm" name="siteFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="mnu_code"		name="mnu_code"	value="<c:out value="${mnu_code } "/>" />
	<input type="hidden" id="cmd"			name="cmd" />

		<div class="board_search">
			<select title="사이트 구분 선택" name="srchSe" id="srchSe" class="w100">
				<option value="">사이트 구분</option>
				<option value="main" 	<c:if test="${srchMap.srchSe == 'main'}">selected="selected"</c:if>>대표</option>
				<option value="dept" 	<c:if test="${srchMap.srchSe == 'dept'}">selected="selected"</c:if>>부서</option>
				<option value="dong" 	<c:if test="${srchMap.srchSe == 'dong'}">selected="selected"</c:if>>읍면동</option>	
				<option value="office" 	<c:if test="${srchMap.srchSe == 'office'}">selected="selected"</c:if>>직속기관/사업소</option>
				<option value="site" 	<c:if test="${srchMap.srchSe == 'site'}">selected="selected"</c:if>>개별사이트</option>		
			</select>
			<select title="사용유무 선택" name="srchUseYn" id="srchUseYn" class="w100">
				<option value="">사용유무</option>
				<option value="1" <c:if test="${srchMap.srchUseYn == '1'}">selected="selected"</c:if>>사용</option>
				<option value="0" <c:if test="${srchMap.srchUseYn == '0'}">selected="selected"</c:if>>중지</option>
				<option value="9" <c:if test="${srchMap.srchUseYn == '9'}">selected="selected"</c:if>>폐쇄</option>			
			</select>	
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />" placeholder="검색어" />
			<a href="#self" class="bt3 srch" onclick="searchData()">검색</a>
			<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>
		</div>
	</form>
		
	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col">사이트 코드</th>
				<th scope="col">사이트 명</th>
				<th scope="col">디렉토리</th>
				<th scope="col">도메인</th>
				<th scope="col">주소</th>
				<th scope="col">언어</th>
				<th scope="col">사이트</th>
				<%--<th scope="col">모바일</th> --%>
				<th scope="col">관리부서</th>
				<th scope="col">사용유무</th>
				<th scope="col">접속 IP 검사</th>
				<th scope="col">관리자</th>	
				<th scope="col">등록/수정일시</th>
				<th scope="col">관리</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(siteList) == 0 }">
				<tr>
					<td colspan="14">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${siteList}" varStatus="status">
				<tr>
					<td><span style="white-space:nowrap;"><c:out value="${vo.site_code }"/></span></td>
					<td><c:out value="${vo.site_nm }"/></td>
					<td><span style="white-space:nowrap;"><c:out value="${vo.site_directory }"/></span></td>
					<td>
						<c:out value="${vo.site_domain1 }"/>
						<c:if test="${vo.site_domain2 != null }">
							<br/> <c:out value="${vo.site_domain2 }"/>
						</c:if>
					</td>
					<td><c:out value="${vo.site_cmsUrl }"/></td>
					<td>
						<c:choose>
							<c:when test="${vo.site_locale == 'ko' }">한국어</c:when>
							<c:when test="${vo.site_locale == 'en' }">영어</c:when>
							<c:when test="${vo.site_locale == 'cn' }">중국어</c:when>
							<c:when test="${vo.site_locale == 'jp' }">일본어</c:when>
							<c:when test="${vo.site_locale == 'i18n' }">국제화</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${vo.site_se == 'main' }">대표</c:when>
							<c:when test="${vo.site_se == 'dept' }">부서</c:when>
							<c:when test="${vo.site_se == 'dong' }">읍면동</c:when>
							<c:when test="${vo.site_se == 'office' }">직속기관/사업소</c:when>
							<c:when test="${vo.site_se == 'site' }">개별사이트</c:when>
						</c:choose>
					</td>
				<%--
					<td>
						<c:choose>
							<c:when test="${vo.site_isMobile == 1 }">모바일</c:when>
							<c:when test="${vo.site_isMobile == 0 }">비모바일</c:when>
						</c:choose>
					</td>
				--%>
					<td><c:out value="${vo.dept_id }"/></td>
					<td>
						<c:choose>
							<c:when test="${vo.site_useYn == 1 }"><span class="blue">사용</span></c:when>
							<c:when test="${vo.site_useYn == 0 }">중지</c:when>
							<c:when test="${vo.site_useYn == 9 }"><span class="red">폐쇄</span></c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${vo.site_accessIpYn == 1 }"><span class="red">사용</span></c:when>
							<c:when test="${vo.site_accessIpYn == 0 }">사용안함</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${vo.site_mngYn == 0 }">사용자</c:when>
							<c:when test="${vo.site_mngYn == 1 }">관리자</c:when>
						</c:choose>
					</td>
					<td>
						<fmt:formatDate value="${vo.site_regDt}" pattern="yyyy-MM-dd" />
						<c:if test="${vo.site_mdfcnDt != null }">
							<br/> <fmt:formatDate value="${vo.site_mdfcnDt}" pattern="yyyy-MM-dd" />
						</c:if>
					</td>
					<td>
						<a href="<c:out value="${listLink }cmd=16&site_code=${vo.site_code }" />" class="btn">관리</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>


<script>
function searchData()
{
	$("#siteFrm").submit();
}
</script>