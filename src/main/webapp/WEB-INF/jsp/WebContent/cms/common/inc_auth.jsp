<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="auth_wrap">
	<div class="auth">
		<dl class="title">
			<dt>본인확인</dt>
			<dd>휴대폰 인증으로 회원 아이디를 확인합니다.</dd>
		</dl>
		<div class="certify_list">
		<fmt:message var="authTy" bundle="${globalsConfig}" key="auth.service.mode" />
			<c:set var="authTitle" value="휴대폰 인증하기" />
			<fmt:message var="authLink" key="auth.service.url" />

			<c:set var="authLink" value="${authLink }" />

			<c:if test="${authTy == 'test' }">
				<c:set var="authTitle" value="테스트 인증하기" />
				<c:set var="authLink" value="/auth/dev/auth_dev.do" />
			</c:if>
			<dl>
				<dt>본인 명의<br/>휴대폰으로 인증</dt>
				<dd>
					<p>문의 02-1600-1522</p>
					<a class="link" href="https://www.niceid.co.kr" title="새창 열림" target="_blank">www.niceid.co.kr</a>
					<a class="btn" href="<c:url value="${authLink }" />" title="새창 열림" onclick="window.open(this.href, '_auth', 'width=500, height=550, top=100, left=100');return false;"><c:out value="${authTitle }" /></a>
				</dd>
			</dl>
			<c:if test="${authTy != 'test' }">
			<dl>
				<dt>NICE 제공<br/>주민번호 대체 서비스</dt>
				<dd>
					<p class="call">문의 02-1600-1522</p>
					<a class="link" href="https://www.niceid.co.kr" title="새창 열림" target="_blank;">www.niceid.co.kr</a>
					<a class="btn" href="/common/ipin/ipin_main.jsp" title="새창 열림" onclick="window.open(this.href, '_auth', 'width=500, height=550, top=100, left=100');return false;">아이핀(I-PIN) 인증하기</a>
				</dd>
			</dl>
			</c:if>
		</div>
		<p class="check_txt">본인인증 관련 장애발생시 반드시 브라우저의 '팝업차단'을 해제해 주시기 바랍니다.</p>
	</div>
</div>
<script>
   function ipin() {
	wWidth = 360;
	wHight = 120;

	wX = (window.screen.width - wWidth) / 2;
	wY = (window.screen.height - wHight) / 2;

	var w = window.open("http://www.dudc.or.kr:9902/G-PIN/Sample-AuthRequest.jsp", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
}
</script>
