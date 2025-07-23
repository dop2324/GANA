<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
function searchData()
{
	$("#statsFrm").submit();
}
</script>

<form method="post" id="statsFrm" name="statsFrm" action="<c:out value="${actionMenuLink }" />">
<input type="hidden" id="site_code"		name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="frmTy"			name="frmTy"		value="<c:out value="${frmTy }" />" />
<input type="hidden" id="cmd"			name="cmd" />
	<div class="board_search">
		<input value="◀◀◀" onclick="searchPre('y')" type="button" style="font-size:11rem;" />
		<input value="◀◀" onclick="searchPre('m')" type="button" style="font-size:11rem;" />
		<input value="◀" onclick="searchPre('d')" type="button" style="font-size:11rem;" />
		<div class="date">
			<input type="text" id="srchSDate" name="srchSDate" value="${srchMap.srchSDate }" class="datepicker" readonly="readonly" />
			<span>~</span>
			<input type="text" id="srchEDate" name="srchEDate" value="${srchMap.srchEDate }" class="datepicker" readonly="readonly" />
		</div>
		<input value="▶" onclick="searchNxt('d')" type="button" style="font-size:11rem;" />
		<input value="▶▶" onclick="searchNxt('m')" type="button" style="font-size:11rem;" />
		<input value="▶▶▶" onclick="searchNxt('y')" type="button" style="font-size:11rem;" />
		
		<a href="#self" onclick="searchData()">검색</a>
			
	</div>

</form>