<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function daumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
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
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('zip').value = data.zonecode;
	            document.getElementById("addr").value = roadAddr;
	            
	            // 도로명주소가 없을경우
	            if(roadAddr == ''){
	            	document.getElementById("addr").value = data.jibunAddress;
	            }
	        }
	    }).open();
	}
	
	function checkExistID(){
		var membId = $('#membId').val();
		if(membId == "") {
			alert("ID을 입력하세요!");
			$("#membId").focus();
	        return false;			
		}else{
			$.ajax({
				type:"post"
				, url:"${pageContext.request.contextPath}/WebContent/func/member/checkExistID.do"
				, dataType:"json"
				, data: {
					mnu_code : $("#mnu_code").val()
					, membId : membId
				}
				, success:function(data)
				{
					const inputGroup = $('#membId').closest('.input-group');
					const hint = inputGroup.siblings('p.form-hint, p.form-hint-invalid, p.form-hint-success');
					
					if(data.result == true){
						inputGroup.removeClass('is-error').addClass('is-success');
						hint
						.removeClass('form-hint form-hint-invalid')
						.addClass('form-hint-success')
						.text('해당 ID는 사용할 수 있습니다.');
						
						$('#isValidateId').val('Y');
					}else{
						inputGroup.removeClass('is-success').addClass('is-error');
						hint
						.removeClass('form-hint form-hint-success')
						.addClass('form-hint-invalid')
						.text(data.msg);
						
						$('#isValidateId').val('N');
						
						$('#membId').focus();
						return false;
					}
				}
				, error:function(jqXHR, textStatus, errorThrown)
				{
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
					$('#isValidateId').val('N');
					alert('통신중에 장애가 발생하였습니다. 잠시후 다시 시도해주시기 바랍니다.');
				}
			});
		}
	}
	
	function frm_validate(f){
		// ID 체크
		if(f.membId.value == ''){
			alert('ID를 입력해주세요.');
			f.membId.focus();
			return false;
		}
		if(f.isValidateId.value == 'N'){
			alert('해당 ID는 사용하실 수 없습니다. ID사용확인을 클릭해주세요.');
			return false;
		}
		
		// 비밀번호 유효성 체크
		var pswd = f.pswd.value.trim();
		var pswdChg = f.pswdChg.value.trim();
		
		if (pswd === '') {
			alert('비밀번호를 입력해주세요.');
			f.pswd.focus();
			return false;
		}
		
		// 비밀번호 조건: 8~15자, 영문+숫자+특수문자 조합
		var pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+=\-]).{8,15}$/;
		if (!pwRegex.test(pswd)) {
			alert('비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.');
			f.pswd.focus();
			return false;
		}
		
		if (pswdChg === '') {
			alert('비밀번호 확인을 입력해주세요.');
			f.pswdChg.focus();
			return false;
		}

		if (pswd !== pswdChg) {
			alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
			f.pswdChg.focus();
			return false;
		}
		
		// 성별 체크 (radio 중 선택 확인)
		var sexRadios = document.getElementsByName("sexdstn");
		var genderSelected = false;
		for (var i = 0; i < sexRadios.length; i++) {
			if (sexRadios[i].checked) {
				genderSelected = true;
				break;
			}
		}
		if (!genderSelected) {
			alert('성별을 선택해주세요.');
			return false;
		}
		
		// 이메일 체크
		if (f.emlAddr.value.trim() === '') {
			alert('이메일을 입력해주세요.');
			f.emlAddr.focus();
			return false;
		}

		var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailRegex.test(f.emlAddr.value.trim())) {
			alert('올바른 이메일 형식을 입력해주세요.');
			f.emlAddr.focus();
			return false;
		}
		
		f.submit();
		return true;
	}
</script>
<div class="container">
<form name="writeFrm" id="writeFrm" method="post" action="<c:url value='${publicDir }/func/member/signup_process.do' />?mnu_code=<c:out value='${mnu_code }' />" onsubmit="return frm_validate(this);">
<double-submit:preventer/>
<input type="hidden" name="membType" value="<c:out value='${membType }' />" />

<%-- ID 사용가능여부 체크 --%>
<input type="hidden" name="isValidateId" id="isValidateId" value="N" />

<div class="txt-box bg-white">
	<div class="fieldset">
	
		<div class="form-group">
			<div class="form-tit">
				<label for="membId">회원ID</label>
			</div>
			<div class="form-conts">
				<div class="input-group">
					<input type="text" name="membId" id="membId" class="krds-input" placeholder="회원ID" />
					<button type="button" onclick="checkExistID();" class="krds-btn secondary">ID사용확인</button>
				</div>
				<p class="form-hint">ID는 4~20자 이내의 영문소문자 및 숫자만 허용가능합니다.</p>
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="pswd">비밀번호</label>
			</div>
			<div class="form-conts">
				<div class="input-group">
					<input type="password" name="pswd" id="pswd" class="krds-input" placeholder="비밀번호" />
				</div>
				<div class="input-group">
					<input type="password" name="pswdChg" id="pswdChg" class="krds-input" placeholder="비밀번호확인" />
				</div>
				<p class="form-hint">비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.</p>
			</div>
		</div>
		
		<div class="form-group">
			<div class="form-tit">
				<label for="membNm">이름</label>
			</div>
			<div class="form-conts">
				<input type="text" name="membNm" id="membNm" class="krds-input" disabled value="<c:out value='${rtnMap.sName }' />" placeholder="이름" />
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="mbtlnum">휴대폰번호</label>
			</div>
			<div class="form-conts">
				<div class="input-group">
					<input type="tel" name="mbtlnum" id="mbtlnum" class="krds-input" disabled value="<c:out value='${rtnMap.sMobileNo }' />" placeholder="휴대폰번호" />
					<button type="button" class="krds-btn secondary" <c:out value="${not empty rtnMap.sMobileNo?' disabled':'' }" />>휴대폰인증</button>
				</div>
				<p class="form-hint">휴대폰인증 외에 수단으로 인증한 사용자의 경우 휴대폰인증으로 휴대폰번호를 검증할 수 있습니다.</p>
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="sexdstn">성별</label>
			</div>
			<div class="form-conts">
				<div class="krds-check-area">
					<div class="krds-form-check">
						<input type="radio" name="sexdstn" id="sexdstn_M" disabled <c:out value="${rtnMap.sGender eq '1'?'checked':'' }" /> />
						<label for="sexdstn_M">남성</label>
					</div>
					<div class="krds-form-check">
						<input type="radio" name="sexdstn" id="sexdstn_W" disabled <c:out value="${rtnMap.sGender eq '0'?'checked':'' }" /> />
						<label for="sexdstn_W">여성</label>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="zip">주소</label>
			</div>
			<div class="form-conts">
				<div class="input-group">
					<input type="text" name="zip" id="zip" class="krds-input" readonly placeholder="우편번호" />
					<button type="button" class="krds-btn secondary" onclick="daumPostcode();return false;">주소검색</button>
				</div>
				<input type="text" name="addr" id="addr" class="krds-input" readonly placeholder="주소" />
				<input type="text" name="dtlAddr" id="dtlAddr" class="krds-input" placeholder="상세주소" />
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="brthdy">생년월일</label>
			</div>
			<div class="form-conts">
				<input type="text" name="brthdy" id="brthdy" class="krds-input" disabled value="<c:out value='${rtnMap.sBirthDate }' />" placeholder="생년월일" />
			</div>
		</div>
		<div class="form-group">
			<div class="form-tit">
				<label for="emlAddr">이메일</label>
			</div>
			<div class="form-conts">
				<input type="email" name="emlAddr" id="emlAddr" class="krds-input" value="" placeholder="이메일" />
			</div>
		</div>
	
	</div>
	
	<div class="page-btn-wrap both">
		<a href="<c:url value='/member/main.do' />" class="krds-btn xlarge tertiary">취소</a>
		<button type="submit" class="krds-btn xlarge primary">회원가입</button>
	</div>
</div>

</form>
</div>

<script type="text/javascript">
</script>
