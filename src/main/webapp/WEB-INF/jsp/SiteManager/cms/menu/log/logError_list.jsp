<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_wrap">
	<form method="post" id="logErrorFrm" name="logErrorFrm" action="<c:out value="${actionMenuLink }" />">
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
			<input id="srchKwd" name="srchKwd" class="inp_txt" type="text" value="${srchMap.srchKwd }" placeholder="검색어" />
			<a href="#self" onclick="searchData()">검색</a>
		</div>
	</form>

	<div class="board_area">
		<table class="board_table">
			<thead>
			<tr>
				<th scope="col" class="wp5">NO </th>
				<th scope="col" class="wp10">SITE</th>
				<th scope="col" class="wp20">DATE / MENU / IP</th>
				<th scope="col">정보</th>
				<th scope="col" class="wp8">보기</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${fn:length(logErrorList) == 0 }">
				<tr>
					<td colspan="5">데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:forEach var="vo" items="${logErrorList}" varStatus="status">
				<tr class="fs13">
					<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
					<td><c:out value="${vo.site_nm }"/></td>
					<td class="taR">
						<div><c:out value="${fn:substring(vo.err_date, 0, 23) }"/></div>
						<div><c:out value="${vo.mnu_nm }"/></div>
						<div><c:out value="${vo.err_ip }"/></div>
					</td>
					<td class="taL">
						<div><span class="b">URI</span> : <c:out value="${vo.err_uri }" /></div>
						<div><span class="b">Controller</span> : <c:out value="${vo.err_controller }" /></div>
						<div><span class="b">Method</span> : <c:out value="${vo.err_method }" /></div>
						<div><span class="b">Mapping</span> : <c:out value="${vo.err_inc }" /></div>
						<div class="pt7"><span class="b">Msg</span> : <c:out value="${vo.err_msg }" /></div>
					</td>
					<td>
						<a href="#self" class="btn" onclick="viewDetail(<c:out value="${vo.err_sn }"/>)">더보기</a>
					</td>
				</tr>
				<tr id="detail_<c:out value="${vo.err_sn }"/>" class="detail" style="display:none;">
					<td colspan="5" class="p10">
						<div class="taL p20 fs13" style="border:1px solid #cecece; height:300px; overflow: scroll;">
							<c:out value="${util:htmlEncode(vo.err_detail) }" escapeXml="false" />
						</div>
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
var tmpSn;
function viewDetail(errSn)
{
	$(".detail").hide();
	$("#detail_"+errSn).show();
	if(tmpSn == errSn) {
		$("#detail_"+errSn).hide();
		tmpSn = 0;
	}else{
		tmpSn = errSn;
	}
}
function searchData()
{
	$("#logErrorFrm").submit();
}
</script>