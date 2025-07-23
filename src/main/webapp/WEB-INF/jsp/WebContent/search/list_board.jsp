<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="C_body" id="board_sc">
	<ul class="Cmenu_Title">
		<li><c:out value="${mnu_nm }" /> 검색</li>
		<li>총 <span><fmt:formatNumber value="${totalCnt}" groupingUsed="true"/></span>건</li>
	</ul>
	
	<c:forEach var="map" items="${boardList}" varStatus="status">
	<c:set var="link" value="/${map.site_code }/page.do?mnu_code=${map.mnu_code}&bod_sn=${map.bod_sn}&cmd=2" />
	
	<c:if test="${map.inf_skinType == 'link' }">
		<c:set var="link" value="${map.con_cn }" />
	</c:if>
	<dl class="C_Cts">
		<dt class="txt">
			<span><c:out value="${map.site_nm }" /> / <c:out value="${map.mnu_nm }" /></span>
			<div>
				<a title="<c:out value="${map.bod_ttl }" />" href="<c:out value="${link }" />" target="_blank" title="새창열림">
				<c:out value="${fn:replace(map.bod_ttl, kwd, '<strong class=\"keyword\">'+=kwd+='</strong>') }" escapeXml="false" />
				</a> 
				<span class="wGun"><fmt:formatDate value="${map.con_regDt}" pattern="yyyy-MM-dd" /></span>
			</div>
		</dt>
		<c:if test="${map.inf_skinType != 'link' }">
		<dd class="txt2">
			<c:set var="con_cn" value="${fn:substring(map.con_cn, 0, 340) }" />
			<c:out value="${fn:replace(con_cn, kwd, '<strong class=\"keyword\">'+=kwd+='</strong>') }" escapeXml="false" />
		</dd>
		</c:if>
	</dl>
	</c:forEach>
	
	<c:if test="${totalCnt > 0 && param.pageSize < 10 }">
		<script>
			$(function(){
				var totalSrchCnt = Number($("#totalCntTop").text());
				var totalSrchCnt = totalSrchCnt+<c:out value="${totalCnt}" />;
				$("#totalCntTop").text(totalSrchCnt);
				$(".totalCntClass").text(totalSrchCnt);
				$(".<c:out value="${param.ctgry}" />Cnt").text(<c:out value="${totalCnt}" />);
			});
			
		</script>
		<div class="more"><a title="검색 더보기" href="<c:out value="?ctgry=${param.ctgry }&kwd=${kwd }" />">더보기</a></div>
	</c:if>
</div>

<c:if test="${param.pageSize == 10 }">
	<div class="paging"><c:out value="${paging}" escapeXml="false" /></div>
</c:if>