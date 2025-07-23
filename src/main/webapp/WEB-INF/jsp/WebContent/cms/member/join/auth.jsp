<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<ul class="joinstep">
	<li class="active"><strong>STEP 01</strong> 본인확인</li>
	<li><strong>STEP 02</strong> 회원이용약관</li>
	<li><strong>STEP 03</strong> 회원정보입력</li>
	<li><strong>STEP 04</strong> 회원가입완료</li>
</ul>


<form id="joinfrm" name="joinfrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code" 	name="mnu_code" value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="step" 		name="step" 	value="<c:out value="${step }" />" />
</form>

<%-- 공통 인증 호출 --%>
<c:import url="/EgovPageLink.do?link=/${publicDir }/cms/common/inc_auth" />

<script>
$(function(){
	$("title").text("본인확인 | "+$("title").text());
});

function authNameEnd(){
    $("#joinfrm").submit();
}
</script>