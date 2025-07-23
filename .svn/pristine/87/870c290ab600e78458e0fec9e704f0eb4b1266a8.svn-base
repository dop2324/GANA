<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="infoFrm" name="infoFrm" method="post" action="<c:out value="${publicDir }/cms/member/update_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${siteVo.site_code }" />" />
<input type="hidden" id="mnu_code" 		name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd" 			name="cmd" 			value="<c:out value="${cmd }" />" />
<input type="hidden" id="step" 			name="step" 		value="<c:out value="${step }" />" />

<div class="join_wrap">
	<ul class="join_box depth1">
		<li>회원가입을 위하여 아래사항을 입력해 주시기 바랍니다.</li>
		<li><span class="red">*</span>은 필수 입력 항목입니다. 회원가입을 위해 반드시 기입해 주시기 바랍니다</li>
	</ul>
	<div class="join_form form_style">
		<p class="title">아이디 정보</p>
		<fieldset>
	        <dl>
	            <dt><span class="red" title="필수입력">*</span>아이디</dt>
	            <dd><c:out value="${memberVo.mem_id }" /></dd>
	            <dt><label for="mem_pw"><span class="red" title="필수입력">*</span>비밀번호</label></dt>
	            <dd>
	            	<input type="password" id="mem_pw" name="mem_pw" maxlength="20" placeholder="비밀번호" />
	            	<input type="checkbox" id="changePw" name="changePw" value="1" /> <label for="changePw">비밀번호 수정</label>

                    <p class="check_txt wp100">영문(대소문자), 숫자,특수문자(!@#$%^&*)를 조합하여 8자부터 20자까지 가능합니다.</p>
                    <p class="check_txt wp100">동일한 문자 혹은 숫자를 4번이상 사용할 수 없습니다.(ex-1111,aaaa)</p>
                    <p class="check_txt wp100">연속된 문자 혹은 숫자를 4번이상 사용할 수 없습니다.(ex-1234,4321,abcd,dcba)</p>
	            </dd>
	            <dt><label for="chk_pw"><span class="red" title="필수입력">*</span>비밀번호 확인</label></dt>
	            <dd><input id="chk_pw" name="chk_pw" type="password" maxlength="20" placeholder="비밀번호 확인" /></dd>
	        </dl>
		</fieldset>

		<p class="title">개인 정보</p>
		<fieldset>
			<legend class="hidden">개인 정보</legend>
            <dl>
                <dt><label for="mem_nm"><span class="red" title="필수입력">*</span>이름</label></dt>
                <dd>
                    <input id="mem_nm" name="mem_nm" type="text" maxlength="64" value="<c:out value="${memberVo.mem_nm }" />" readonly="readonly" placeholder="이름" />
                </dd>
                <dt><label for="mem_mail">이메일</label></dt>
                <dd><input id="mem_mail" name="mem_mail" type="text" maxlength="128" value="<c:out value="${memberVo.mem_mail }" />" placeholder="이메일" /></dd>
                <dt><label for="mem_birth">생년월일</label></dt>
                <dd>
                	<input id="mem_birth" name="mem_birth" type="text" maxlength="12" value="<c:out value="${memberVo.mem_birth }" />" placeholder="생년월일" />
                </dd>
                <dt><label for="mem_moblphone">휴대전화</label></dt>
                <dd>
                	<input id="mem_moblphone" name="mem_moblphone" type="text" maxlength="16" value="<c:out value="${memberVo.mem_moblphone }" />" placeholder="휴대전화" />
                </dd>
                <dt><label for="mem_telno">전화번호</label></dt>
                <dd><input id="mem_telno" name="mem_telno" type="text" maxlength="16" value="<c:out value="${memberVo.mem_telno }" />" placeholder="전화번호" /></dd>
                <dt>성별</dt>
                <dd>
                	<input id="mem_gender_1" name="mem_gender" type="radio"class="inp_rad" value="1" <c:if test="${memberVo.mem_gender == 1}">checked="checked"</c:if> />
					<label for="mem_gender_1">남성</label>
					<input id="mem_gender_2" name="mem_gender" type="radio"class="inp_rad" value="0" <c:if test="${memberVo.mem_gender == 2}">checked="checked"</c:if> />
					<label for="mem_gender_2">여성</label>
                </dd>
                <dt>주소</dt>
                <dd class="address">
                	<label for="mem_zip" class="hidden">우편번호</label>
                	<input id="mem_zip" name="mem_zip" type="text" value="<c:out value="${memberVo.mem_zip }" />" placeholder="우편번호" />
                	<a href="#self" class="bt2" onclick="execDaumPostcode();return false;">주소찾기</a>

                	<label for="mem_addr" class="hidden">주소</label>
                	<input id="mem_addr" name="mem_addr" type="text" maxlength="128" value="<c:out value="${memberVo.mem_addr }" />" placeholder="주소" />

                	<label for="mem_addrDtl" class="hidden">주소 상세</label>
                	<input id="mem_addrDtl" name="mem_addrDtl" type="text" maxlength="64" value="<c:out value="${memberVo.mem_addrDtl }" />" placeholder="주소 상세" />
                </dd>
                <dt><label for="mem_company">직장정보</label></dt>
                <dd>
                	<label for="mem_company" class="hidden">직장명</label>
                	<input id="mem_company" name="mem_company" type="text" maxlength="128" value="<c:out value="${memberVo.mem_company }" />" placeholder="직장명" />
                	<label for="mem_pos" class="hidden">직위</label>
                	<input id="mem_pos" name="mem_pos" type="text" maxlength="32" value="<c:out value="${memberVo.mem_pos }" />" placeholder="직위" />
                </dd>
                <dt>이메일 수신동의</dt>
                <dd>
                	<input id="mem_emlYn_1" name="mem_emlYn" type="radio"class="inp_rad" value="1" <c:if test="${memberVo.mem_emlYn == 1}">checked="checked"</c:if> />
					<label for="mem_emlYn_1">수신</label>
					<input id="mem_emlYn_0" name="mem_emlYn" type="radio"class="inp_rad" value="0" <c:if test="${memberVo.mem_emlYn == 0}">checked="checked"</c:if> />
					<label for="mem_emlYn_0">수신거부</label>
                </dd>
            </dl>
		</fieldset>
	</div>

	<div class="join_btn">
		<a href="#self" onclick="saveData();">확인</a>
		<a href="<c:out value="?mnu_code=${mnu_code }" />" class="gray">취소</a>
	</div>
</div>
</form>

<script>
$(function(){
	$("title").text("정보수정 | "+$("title").text());
});

function saveData()
{
	if($('#changePw').is(":checked") == true)
	{
		if($("#mem_pw").val() == "") {
			alert("비밀번호를 입력하여 주십시요");
			$("#mem_pw").select();
			return false;
		}

		if(!regPw.test($("#mem_pw").val())) {
			alert("비밀번호는 영문 숫자 특수기호 조합 8자리 이상 사용해야 합니다.");
			$("#mem_pw").select();
			return false;
		}

		if($("#chk_pw").val() == "") {
			alert("비밀번호 확인을 입력하여 주십시요");
			$("#chk_pw").select();
			return false;
		}

		if($('#mem_pw').val()!=$('#chk_pw').val()) {
			alert("비밀번호 확인이 일치하지 않습니다.");
			$('#chk_pw').select();
			return false;
		}
	}

	$("#infoFrm").submit();
}

function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			//console.log(data);

			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
	        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	            extraRoadAddr += data.bname;
	        }
	        // 건물명이 있고, 공동주택일 경우 추가한다.
	        if(data.buildingName !== '' && data.apartment === 'Y'){
	            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	        }
	        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	        if(extraRoadAddr !== ''){
	            extraRoadAddr = ' (' + extraRoadAddr + ')';
	        }

	        $("#mem_zip").val(data.zonecode);
			$("#mem_addr").val(roadAddr);
		}
	}).open({
		popupTitle: '우편번호 검색 팝업' //팝업창 타이틀 설정 (영문,한글,숫자 모두 가능)
	});
}
</script>
