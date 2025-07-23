<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="conts-area">
<form name="wdrwForm" id="wdrwForm" method="post" action="<c:url value='/WebContent/func/member/wdrwProcess.do' />?mnu_code=${mnu_code}" onsubmit="return frm_vaildate(this);">
	<div class="conts-wrap">
		
		<div class="txt-box bg-white">
			<div class="fieldset">
			
				<div class="form-group">
					<div class="form-tit">
						<label for="wdrwCn">회원탈퇴 사유를 입력해주시기 바랍니다.</label>
					</div>
					<div class="form-conts">
					<textarea name="wdrwCn" id="wdrwCn" class="krds-input"></textarea>
					</div>
				</div>
			
			</div>
		</div>
		
		<div class="page-btn-wrap both">
          <a href="<c:url value='/member/page.do' />?mnu_code=${mnu_code}" class="krds-btn xlarge tertiary">취소하기</a>
          <button type="submit" class="krds-btn xlarge primary">탈퇴하기</button>
        </div>
        
	</div>	
</form>
</div>

<script type="text/javascript">
//<![CDATA[
	function frm_vaildate(f){
		return true;
	}
//]]>
</script>