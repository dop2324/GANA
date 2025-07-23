<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="logFrm" name="logFrm" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="site_code"		name="site_code"	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
	<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
	<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
	<input type="hidden" id="frmTy"			name="frmTy" 		value="<c:out value="${frmTy }" />" />
	<input type="hidden" id="cmd"			name="cmd" />

		<div class="board_search">
			<div class="date">
				<input type="text" id="srchSDate" name="srchSdate" value="${srchMap.srchSdate }" class="datepicker inp_txt w150" readonly="readonly" placeholder="검색일자" />
				<span>~</span>
				<input type="text" id="srchEdate" name="srchEdate" value="${srchMap.srchEdate }" class="datepicker inp_txt w150" readonly="readonly" placeholder="검색일자" />
			</div>
			
			<select id="srchMethod" name="srchMethod">
				<option value="">방법</option>
				<option value="sms" 	${srchMap.srchMethod == "sms" ? "selected='selected'":"" }>SMS</option>
				<option value="mail" 	${srchMap.srchMethod == "mail" ? "selected='selected'":"" }>Mail</option>
			</select>
			
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="${srchMap.srchKwd }" placeholder="검색어" />
			<a href="#self" onclick="searchData()">검색</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col" class="wp5">번호</th>
				<th scope="col" class="wp17">일시</th>
				<th scope="col" class="wp15">메뉴</th>
				<th scope="col" class="wp5">방법</th>
				<th scope="col" class="wp15">대상</th>
				<th scope="col">메세지</th>
				<th scope="col" class="wp8">보기</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(logList) == 0 }">
				<tr>
					<td colspan="7">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${logList}" varStatus="status">
				<c:set var="mnu_alarmMsg" value="${util:htmlEncode(vo.mnu_alarmMsg) }" />
				<c:set var="moreBtn" value="${fn:length(mnu_alarmMsg) > 40 }" />
				<tr class="fs13">
					<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
					<td><fmt:formatDate value="${vo.mnu_alarmDt}" pattern="yyyy-MM-dd HH:mm:ss S" /></td>
					<td><c:out value="${vo.mnu_nm }"/></td>
					<td><c:out value="${vo.mnu_alarmMethod }"/></td>
					<td><c:out value="${vo.mnu_alarmTrgt }" /></td>
					<td class="taL">
						<c:set var="mnu_alarmMsg" value="${util:htmlEncode(vo.mnu_alarmMsg) }" />
						<c:out value="${fn:substring(mnu_alarmMsg, 0, 40) }" escapeXml="false" />
						<c:if test="${moreBtn }">...</c:if>
					</td>
					<td>
						<c:if test="${moreBtn}">
						<a href="#self" class="btn" onclick="viewDetail(<c:out value="${status.count }"/>)">더보기</a>
						</c:if>
					</td>
				</tr>
				<c:if test="${moreBtn}">
				<tr id="detail_<c:out value="${status.count }"/>" class="detail" style="display:none;">
					<td colspan="7" class="p10">
						<div class="taL p20 fs13" style="border:1px solid #cecece; height:300px; overflow: scroll;">
							<c:out value="${util:htmlEncode(vo.mnu_alarmMsg) }" escapeXml="false" />
						</div>
					</td>
				</tr>
				</c:if>
			<c:set var="no" value="${no -1 }" />
			</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="board_paging"><c:out value="${paging}" escapeXml="false" /></div>
</div>

<script>
var tmpSn;
function viewDetail(index)
{
	$(".detail").hide();
	$("#detail_"+index).show();
	if(tmpSn == index) {
		$("#detail_"+index).hide();
		tmpSn = 0;
	}else{
		tmpSn = index;
	}
}
function searchData()
{
	$("#logFrm").submit();
}
</script>