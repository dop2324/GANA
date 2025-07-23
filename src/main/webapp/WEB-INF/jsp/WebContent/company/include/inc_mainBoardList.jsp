<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<h4 class="hidden"><c:out value="${mnu_nm }" /></h4>
<div class="recent">
	<c:forEach var="b" items="${boardList}" varStatus="status">
	<c:if test="${status.index == 0 }">
    <a href="<c:out value="./page.do?mnu_code=${b.mnu_code}&bod_sn=${b.bod_sn}&cmd=2" /><c:if test="${b.bgp_sn != 0}">&srchBgpSn=${b.bgp_sn}</c:if>">
        <div class="date" data-date="<c:out value="${b.con_regDt }" />">
            <p class="d"><fmt:formatDate value="${b.con_regDt}" pattern="dd" /></p>
            <p class="ym"><fmt:formatDate value="${b.con_regDt}" pattern="yyyy.MM" /></p>
            <c:if test="${b.bod_dateDiff < 1 }">
            <span class="icon_new">새글</span>
            </c:if>
        </div>
        
        <div class="detail">
            <p class="tit"><c:out value="${b.bod_ttl }" /></p>
            <p class="txt"><c:out value="${b.con_cn }" /></p>
        </div>
    </a>
    </c:if>
    </c:forEach>
</div>
<ul class="board_list">
	<c:forEach var="b" items="${boardList}" varStatus="status">
	<c:if test="${status.index > 0 }">
		<li><a href="<c:out value="./page.do?mnu_code=${b.mnu_code}&bod_sn=${b.bod_sn}&cmd=2" /><c:if test="${b.bgp_sn != 0}">&srchBgpSn=${b.bgp_sn}</c:if>">
			<span class="tit"><c:out value="${b.bod_ttl }" /></span>
			<span class="date" data-date="<c:out value="${b.con_regDt }" />">
				<fmt:formatDate value="${b.con_regDt}" pattern="yyyy-MM-dd" />
			</span>
			</a>
		</li>
	</c:if>
	</c:forEach>
</ul>
<a href="./page.do?mnu_code=<c:out value="${mnu_code }" />" class="more">더보기</a>