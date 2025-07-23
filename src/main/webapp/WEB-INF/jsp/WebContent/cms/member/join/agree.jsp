<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<form id="agreefrm" name="agreefrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code" 	name="mnu_code" value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="step" 		name="step" 	value="<c:out value="${step }" />" />
</form>

<div class="join_wrap">
	<ul class="join_box depth1">
		<li>통합 회원으로 가입하기 위한 통합 회원 가입을 원하실 경우 동의해 주시기 바랍니다.</li>
		<li>약관 및 개인정보 수집·이용에 대한 안내를 읽으시고 동의여부를 결정하시기 바랍니다.</li>
	</ul>
	<div class="join_agree">
		<div class="agree_wrap">
			<p class="title">서비스 이용약관</p>
			<div class="content_wrap">
				이용약관이 들어갈자리
			</div>
			<div class="radio_wrap">
				<input type="radio" id="mem_check1-1" name="mem_check1" /><label for="mem_check1-1">동의합니다</label>
				<input type="radio" id="mem_check1-2" name="mem_check1" /><label for="mem_check1-2">동의하지않습니다</label>
			</div>
		</div>
		<div class="agree_wrap">
			<p class="title">개인정보 수집 이용 약관</p>
			<div class="content_wrap">
				수집하는 개인정보 항목<br/>
				필수사항 : 이름, 연락처, 핸드폰, 이메일<br/>
				선택사항 : 주소, 관심분야<br/>
				개인정보의 수집 및 이용목적<br/>
				본인식별, 공지사항 전달, 민원처리, 신고처리 등 맞춤서비스를 제공하기 위한 자료 활용<br/><br/>
				개인정보의 보유 및 이용기간<br/>
				개인정보의 수집 및 이용목적이 달성된 후에는 해당정보를 삭제합니다.<br/>
				단, 게시하신 내용은 고객님의 확인을 위하여 최대 2년간 제공됩니다.<br/>
				귀하는 개인정보 수집 · 이용에 동의하지 않으실 수 있습니다.<br/>
				동의 거부시에도 홈페이지서비스는 가능하나 우편물 발송등의 서비스는 제한될 수 있습니다.<br/><br/>
				※ 기타 개인정보보호에 관한 자세한 내용은 하단에 링크한 “개인정보처리방침”을 참고하시기 바랍니다.
			</div>
			<div class="radio_wrap">
				<input type="radio" id="mem_check2-1" name="mem_check2" /><label for="mem_check2-1">동의합니다</label>
				<input type="radio" id="mem_check2-2" name="mem_check2" /><label for="mem_check2-2">동의하지않습니다</label>
			</div>
		</div>
	</div>
	<div class="join_btn">
		<a href="#self" onclick="reqForm();">확인</a>
		<a href="<c:out value="/${path }/main.do" />">취소</a>
	</div>
</div>

<script>
$(function(){
	$("title").text("약관동의 | "+$("title").text());
});

function reqForm() {
    if(!$('#mem_check1-1').is(':checked')) {
    	alert("서비스 이용약관에 동의 하여 주십시요");
    	$('#mem_check1').focus();
    	return false;
    }

    if(!$('#mem_check2-1').is(':checked')) {
    	alert("개인정보 수집 이용 약관에 동의 하여 주십시요");
    	$('#mem_check2').focus();
    	return false;
    }

    $("#agreefrm").submit();
}
</script>
