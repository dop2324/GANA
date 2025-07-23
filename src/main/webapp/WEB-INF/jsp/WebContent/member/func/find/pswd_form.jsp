<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form name="pswdFrm" id="pswdFrm" method="post" action="<c:url value='/WebContent/func/member/pswdchg_process.do' />?mnu_code=<c:out value='${mnu_code }' />" onsubmit="return frm_validate(this);">
	<div class="c-wrap mgb_40">
	
	    <div class="txt-box txt mgb_20">
	        <ul class="list">
	            <li>영문 대·소문자, 숫자, 특수문자를 모두 혼합하여 <strong class="t_blue">9~30자</strong>의 길이로 설정합니다.</li>
	            <li>특수문자는 아래 기호만 사용 가능합니다.</li>
	            <li><strong>허용 기호</strong> : [<strong class="t_blue"> ~ </strong>], [<strong class="t_blue"> ! </strong>], [<strong class="t_blue"> @ </strong>], [<strong class="t_blue"> # </strong>], [<strong class="t_blue"> $ </strong>], [<strong class="t_blue"> ^ </strong>], [<strong class="t_blue"> * </strong>], [<strong class="t_blue"> ( </strong>], [<strong class="t_blue"> ) </strong>], [<strong class="t_blue"> = </strong>], [<strong class="t_blue"> _ </strong>], [<strong class="t_blue"> . </strong>], [<strong class="t_blue"> | </strong>]</li>
	        </ul>
	    </div>
	    
	    <div class="fieldset">
	
		    <div class="form-group">
		        <div class="form-tit">
		            <label for="consult_name3">아이디</label>
		        </div>
		        <div class="form-conts">
		            <c:out value="${membId }" />
		        </div>
		    </div>
		
		    <div class="form-group">
		        <div class="form-tit">
		            <label for="consult_name">새 비밀번호</label>
		        </div>
		        <div class="form-conts">
		            <input type="password" name="newPswd" id="newPswd" class="krds-input" placeholder="새 비밀번호" />
		        </div>
		    </div>
		
		    <div class="form-group">
		        <div class="form-tit">
		            <label for="consult_name">새 비밀번호 확인</label>
		        </div>
		        <div class="form-conts">
		            <input type="password" name="chkPswd" id="chkPswd" class="krds-input" placeholder="새 비밀번호 확인" />
		        </div>
		    </div>
	
		    <div class="btn-wrap">
		        <button type="submit" class="krds-btn xlarge primary">비밀번호 변경</button>
		        <a href="<c:url value='/member/page.do' />?mnu_code=<c:out value='${mnu_code }' />" class="krds-btn xlarge secondary">취소</a>
		    </div>
		    
	    </div>
	</div>
</form>

<script type="text/javascript">
//<![CDATA[
	function frm_validate(f){
	    var pswd = f.newPswd.value;
	    var chkPswd = f.chkPswd.value;

	    // 비밀번호 공백 확인
	    if (pswd.trim() === "") {
	        alert("새 비밀번호를 입력해주세요.");
	        document.getElementById("newPswd").focus();
	        return false;
	    }

	    if (chkPswd.trim() === "") {
	        alert("비밀번호 확인을 입력해주세요.");
	        document.getElementById("chkPswd").focus();
	        return false;
	    }

	    // 비밀번호 일치 확인
	    if (pswd !== chkPswd) {
	        alert("비밀번호가 일치하지 않습니다.");
	        document.getElementById("chkPswd").focus();
	        return false;
	    }

	    // 비밀번호 규칙: 영문, 숫자, 특수문자 포함, 8~15자
	    var pswdRule = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,15}$/;
	    if (!pswdRule.test(pswd)) {
	        alert("비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.");
	        document.getElementById("newPswd").focus();
	        return false;
	    }

	    return true;
	}
//]]>
</script>