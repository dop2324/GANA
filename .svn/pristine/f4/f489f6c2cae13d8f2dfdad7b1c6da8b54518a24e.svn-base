<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="conts-area">
<form name="pswdchgForm" id="pswdchgForm" method="post" action="<c:url value='/WebContent/func/member/pswdchgProcess.do' />?mnu_code=${mnu_code}" onsubmit="return frm_vaildate(this);">
	<div class="conts-wrap">
		
		<div class="txt-box bg-white">
			<div class="fieldset">
			
				<div class="form-group">
					<div class="form-tit">
						<label for="nowPswd">현재비밀번호</label>
					</div>
					<div class="form-conts">
						<input type="password" name="nowPswd" id="nowPswd" class="krds-input" />
					</div>
				</div>
			
				<div class="form-group">
					<div class="form-tit">
						<label for="newPswd">신규비밀번호</label>
					</div>
					<div class="form-conts">
						<input type="password" name="newPswd" id="newPswd" class="krds-input" />
					</div>
					<p class="form-hint">비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.</p>
				</div>
			
				<div class="form-group">
					<div class="form-tit">
						<label for="pswdChg">신규비밀번호 확인</label>
					</div>
					<div class="form-conts">
						<input type="password" name="pswdChg" id="pswdChg" class="krds-input" />
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="page-btn-wrap both">
          <a href="<c:url value='/member/page.do' />?mnu_code=${mnu_code}" class="krds-btn xlarge tertiary">취소하기</a>
          <button type="submit" class="krds-btn xlarge primary">수정하기</button>
        </div>
		
	</div>
</form>
</div>

<script type="text/javascript">
//<![CDATA[
	function frm_vaildate(f){
		var nowPswd = f.nowPswd.value;
		var newPswd = f.newPswd.value;
		var pswdChg = f.pswdChg.value;
		
		if(nowPswd == ''){
			alert('현재 비밀번호를 입력해주시기 바랍니다.');
			f.nowPswd.focus();
			return false;
		}
		
		if(newPswd == ''){
			alert('신규 비밀번호를 입력해주시기 바랍니다.');
			f.newPswd.focus();
			return false;
		}
		
		// 비밀번호 조건: 8~15자, 영문+숫자+특수문자 조합
		var pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+=\-]).{8,15}$/;
		if (!pwRegex.test(newPswd)) {
			alert('비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.');
			f.newPswd.focus();
			return false;
		}
		
		if (pswdChg === '') {
			alert('비밀번호 확인을 입력해주세요.');
			f.pswdChg.focus();
			return false;
		}

		if (newPswd !== pswdChg) {
			alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
			f.pswdChg.focus();
			return false;
		}
		
		f.submit();
	}
//]]>
</script>
