<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="container">
<form name="writeFrm" id="writeFrm" method="post">
<input type="hidden" name="step" value="step04" />
<input type="hidden" name="membType" value="<c:out value='${membType }' />" />

<h2>휴대폰 인증</h2>
<button type="button" onClick="fn_nameCheck();return false;" class="krds-btn secondary" title="휴대폰 인증[새창]">휴대폰 인증</button>
<br />

<h2>IPIN 인증</h2>
<button type="button" onclick="fn_ipinCheck();return false;" class="krds-btn secondary" title="IPIN 인증[새창]">IPIN 인증</button>
</form>
</div>

<script type="text/javascript">
//<![CDATA[
	
	// 휴대폰 본인인증
	function fn_nameCheck(){
		const url = '${pageContext.request.contextPath}/common/component/NICE/checkplus_main.jsp';
		window.open(url, '휴대폰 본인인증', 'width=700, height=460');
	}
	
	// IPIN 본인인증
	function fn_ipinCheck(){
		const url = '${pageContext.request.contextPath}/common/component/IPIN/ipin_main.jsp';
		window.open(url, '아이핀 본인인증', 'width=700, height=460');
	}
	
	// 인증결과 페이지이동
	function doPageMove(){
		const frm = document.forms.writeFrm;
		frm.submit();
	}
	
//]]>
</script>
