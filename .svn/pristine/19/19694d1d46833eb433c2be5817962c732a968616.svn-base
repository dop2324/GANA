<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form method="post" id="linkFrm" name="linkFrm" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="srchGroup"		name="srchGroup"	value="<c:out value="${srchMap.srchGroup }" />" />

	<div class="board_search">
		<select title="사용유무 선택" name="srchSttus" id="srchSttus" class="wp15">
			<option value="">사용유무</option>
			<option value="1" <c:out value="${srchMap.srchSttus == '1' ? 'selected=\"selected\"':'' }" />>사용</option>
			<option value="0" <c:out value="${srchMap.srchSttus == '0' ? 'selected=\"selected\"':'' }" />>중지</option>			
		</select>			
		
		<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="<c:out value="${srchMap.srchKwd }" />"  placeholder="검색어" />
		<a href="#self" class="btn" onclick="searchData()">검색</a>
		<a href="<c:out value="${listLink }cmd=4" />" class="btn blue">등록</a>	
	</div>
	<div class="taR" style="margin-bottom:10rem;">
		<a href="#self" class="btn pink" onclick="checkDelete()">선택삭제</a>	
	</div>

	<div class="board_area">
		<table class="board_table">
			<thead>
				<tr>
					<th scope="col" class="wp4"><input id="allCheck" name="allCheck" type="checkbox" value="1"></th>
					<th scope="col" class="wp6">번호</th>
					<th scope="col" class="wp8">분류명</th>
					<th scope="col" class="wp10">이미지</th>
					<th scope="col">링크명</th>
					<th scope="col" class="wp11">기간</th>
					<th scope="col" class="wp8">Target</th>
					<th scope="col" class="wp5">순서</th>
					<th scope="col" class="wp5">상태</th>
					<th scope="col" class="wp10">수정일시</th>
					<th scope="col" class="wp8">전월 / 당월</th>
					<th scope="col" class="wp13">관리</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(linkList) == 0 }">
				<tr>
					<td colspan="12">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${linkList}" varStatus="status">
				<tr>
					<td class="num"><input type="checkbox" id="chkLnkSn_<c:out value="${vo.lnk_sn }" />" name="chkLnkSn" value="<c:out value="${vo.lnk_sn }" />"></td>
					<td><c:out value="${vo.lnk_sn }" /></td>
					<td><c:out value="${vo.lgp_nm }" /></td>
					<td class="back_noimg">
						<c:if test="${vo.lnk_imgFileNm != ''}">
							<a href="<c:out value="/cms/link/link.do?id=${vo.lnk_sn }" />" target="_blank">
								<c:out value="${util:getImgTag(vo.getWebDir(), vo.lnk_imgFileNm, 80, vo.lnk_nm) }" escapeXml="false" />
							</a>
						</c:if>
					</td>
					<td><c:out value="${vo.lnk_nm }" /></td>
					<td class="taL">
						<p>
							<fmt:formatDate value="${vo.lnk_startDt}" pattern="yyyy-MM-dd HH" />시
						</p>
						<p>
							<fmt:formatDate var="lnk_endDtHHmm" value="${vo.lnk_endDt}" pattern="HH:mm" />
							<c:choose>
								<c:when test="${lnk_endDtHHmm == '23:59' }">
									<fmt:formatDate value="${vo.lnk_endDt}" pattern="yyyy-MM-dd" />
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${vo.lnk_endDt}" pattern="yyyy-MM-dd HH" />시
								</c:otherwise>
							</c:choose>
						</p>
					</td>
					<td><c:out value="${vo.lnk_target }" /></td>
					<td><c:out value="${vo.lnk_sort }" /></td>
					<td><c:out value="${vo.lnk_sttus == 1 ? '사용':'중지' }"/></td>
					<td><fmt:formatDate value="${vo.lnk_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<fmt:formatNumber value="${vo.lnk_prevCnt }" pattern="#,###" />
						/ 
						<fmt:formatNumber value="${vo.lnk_currCnt }" pattern="#,###" />
					</td>
					<td>
						<c:set var="admLink" value="?${queryString}srchGroup=${srchMap.srchGroup }&lnk_sn=${vo.lnk_sn}&pageNo=${searchMap.pageNo}" />
						<a href="<c:out value="${admLink }&cmd=16" />" class="btn blue">수정</a>
						<a href="<c:out value="${admLink }&cmd=2" />" class="btn">현황</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</form>

<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>

<script>
$(function(){
	$("#allCheck").change(function() {
		if($(this).is(':checked')) {
        	$("input[name='chkLnkSn']").prop("checked", true);
        }else{
        	$("input[name='chkLnkSn']").prop("checked", false);
        }
	});
});

function searchData()
{
	$("#linkFrm").submit();
}

function checkDelete()
{
	if($("input[name='chkLnkSn']:checked").length == 0) {
		alert("삭제하실 링크를 선택 하십시요");
	}else{
		if (confirm("링크를 삭제 하시겠습니까?")) {
			$("#cmd").val(64);
			$("#linkFrm").attr("method", "post");
			$("#linkFrm").attr("action", "<c:out value="${managerDir }/cms/link/link_process.do" />");
			$("#linkFrm").submit();
		}
	}
}
</script>
