<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="board_area">
	<table class="board_table">
		<caption>사이트 현황을 나타낸 표입니다.</caption>
		<thead>
			<tr>
				<th scope="col" style="width:10%;">분류명</th>
				<th scope="col" style="width:12%;">이미지</th>
				<th scope="col">링크명</th>
				<th scope="col" style="width:13%;">기간</th>
				<th scope="col" style="width:10%;">Target</th>
				<th scope="col" style="width:7%;">순서</th>
				<th scope="col" style="width:7%;">상태</th>
				<th scope="col" style="width:12%;">수정일시</th>
				<th scope="col" style="width:10%;">전월 / 당월</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${vo.lgp_nm }"/></td>
				<td class="p5">
					<c:if test="${vo.lnk_imgFileNm != ''}">
						<c:out value="${util:getImgTag(vo.getWebDir(), vo.lnk_imgFileNm, 80, vo.lnk_nm) }" escapeXml="false" />
					</c:if>
				</td>
				<td>
					<span class="b"><c:out value="${vo.lnk_nm }"/></span>
				</td>
				<td>
					<fmt:formatDate value="${vo.lnk_startDt}" pattern="yyyy-MM-dd HH" />
					~
					<fmt:formatDate value="${vo.lnk_endDt}" pattern="yyyy-MM-dd HH" />
				</td>
				<td><c:out value="${vo.lnk_target }"/></td>
				<td><c:out value="${vo.lnk_sort }"/></td>
				<td><c:out value="${vo.lnk_sttus == 1 ? \"사용\":\"중지\" }"/></td>
				<td><fmt:formatDate value="${vo.lnk_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<fmt:formatNumber value="${vo.lnk_prevCnt }" pattern="#,###" />
					/ 
					<fmt:formatNumber value="${vo.lnk_currCnt }" pattern="#,###" />
				</td>
			</tr>			
		</tbody>
	</table>
</div>

	<form id="statsfrm" name="statsfrm" method="get" action="<c:out value="${actionMenuLink }" />">
	<input type="hidden" id="site_code" name="site_code" 	value="<c:out value="${site_code }" />" />
	<input type="hidden" id="mnu_code"	name="mnu_code"		value="<c:out value="${mnu_code}" />" />
	<input type="hidden" id="srchGroup"	name="srchGroup"	value="<c:out value="${srchGroup}" />" />
	<input type="hidden" id="lnk_sn"	name="lnk_sn"		value="<c:out value="${srchMap.lnk_sn}" />" />
	<input type="hidden" id="cmd"		name="cmd"			value="<c:out value="${cmd}" />" />
	<input type="hidden" id="srchYmd"	name="srchYmd" />
		<div class="board_search" style="margin-top:20rem;">
			<input type="button" style="font-size:11rem;" onclick="searchPre('y')" value="◀◀◀"/>
			<input type="button" style="font-size:11rem;" onclick="searchPre('m')" value="◀◀"/>
			<input type="button" style="font-size:11rem;" onclick="searchPre('d')" value="◀"/>
			<div class="date">
				<input type="text" id="srchSDate" name="srchSDate" value="${srchMap.srchSDate }" class="datepicker" readonly="readonly" />
				<span>~</span>
				<input type="text" id="srchEDate" name="srchEDate" value="${srchMap.srchSDate }" class="datepicker" readonly="readonly" />
			</div>
			<input type="button" style="font-size:11rem;" onclick="searchNxt('d')" value="▶"/>
			<input type="button" style="font-size:11rem;" onclick="searchNxt('m')" value="▶▶"/>
			<input type="button" style="font-size:11rem;" onclick="searchNxt('y')" value="▶▶▶"/>
			<a href="<c:out value="${listLink }" />" >목록</a>
		</div>
	</form>

<div class="board_area">
	<table class="board_table">
		<caption>사이트 접속현황을 나타낸 표입니다.</caption>
		<thead>
		<tr>
			<th scope="col" style="width:10%;">No</th>
			<th scope="col" style="width:20%;">DateTime</th>
			<th scope="col" style="width:20%;">IP</th>
			<th scope="col">Info</th>
			<th scope="col" style="width:10%;">Locale</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="vo" items="${clickList}" varStatus="status">
		<tr>
			<td><fmt:formatNumber value="${no}" groupingUsed="true"/></td>
			<td><fmt:formatDate value="${vo.click_dt}" pattern="yyyy-MM-dd HH:mm:ss S" /></td>
			<td class="blue">${vo.click_ip }</td>
			<td class="taL">
				<p class="orange"><c:out value="${fn:substring(vo.click_referer,0 , 80)}" /></p>
				<p class="fs13"><c:out value="${fn:substring(vo.click_agent, 0, 100)}" /></p>
			</td>
			<td class="">${fn:substring(vo.click_locale, 0, 10)}</td>
		</tr>
		<c:set var="no" value="${no-1}" />
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="board_paging">${paging}</div>

<script>
function searchPre(ymdType)
{
	$("#srchYmd").val(ymdType);
	var srchStartDate = document.getElementById("srchSDate");
	var srchEndDate = document.getElementById("srchEDate");
	
	var viewDate = getDateScope(srchEndDate.value, -1, ymdType);

	srchStartDate.value = viewDate[0];
	srchEndDate.value = viewDate[1];
		
	searchData();
}

function searchNxt(ymdType)
{
	$("#srchYmd").val(ymdType);
	var srchStartDate = document.getElementById("srchSDate");
	var srchEndDate = document.getElementById("srchEDate");

	var viewDate = getDateScope(srchEndDate.value, +1, ymdType);

	srchStartDate.value = viewDate[0];
	srchEndDate.value = viewDate[1];
		
	searchData();
}

function searchData()
{
	$("#statsfrm").submit();
}

</script>