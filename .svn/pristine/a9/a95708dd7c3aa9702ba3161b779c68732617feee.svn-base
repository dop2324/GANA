<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:set var="errTitle" value="" />
<c:set var="errMessage" value="" />
<c:choose>
	<c:when test="${param.errCode == '404' }">
		<c:set var="errTitle" value="존재하지 않는 페이지" />
		<c:set var="errMessage" value="요청하신 페이지는 존재하지 않습니다." />
	</c:when>
	<c:when test="${param.errCode == '404' }">
		<c:set var="errTitle" value="존재하지 않는 페이지" />
		<c:set var="errMessage" value="요청하신 페이지는 존재하지 않습니다." />
	</c:when>
	<c:otherwise>
		<c:set var="errTitle" value="알수 없는 오류가 발생하였습니다." />
		<c:set var="errMessage" value="서비스 이용에 불편을 드려 대단히 죄송합니다." />
	</c:otherwise>
</c:choose>
    
<div class="ctn">
	<div class="error_str_wrap">
		<div class="box_be_top">
			<dl class="error_page_ctn">
				<dt><strong><c:out value="${errTitle }" /></strong></dt>
				<dd><c:out value="${errMessage }" /><br /><br /></dd>
				<dd class="alignRight" >
					<a href="javascript:history.back()">[이전페이지]</a>
					&nbsp;&nbsp;
					<a href="/">[메인페이지]</a>
				</dd>
			</dl>
		</div>
		<div class="box_be_bottom"></div>
	</div>
</div>