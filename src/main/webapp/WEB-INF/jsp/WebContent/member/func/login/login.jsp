<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="container">
	<div class="conts-wrap">
		
		<div class="inner">
			<div class="login">
				<form name="loginForm" id="loginForm" method="post" action="<c:url value='/WebContent/func/member/login_process.do' />?mnu_code=mlogin">
				<input type="hidden" name="useOrgId" value="USEORG_000000000000000003" />
				<div class="moalogin">

					<div class="fieldset">
						<div class="form-group">
							<div class="form-tit">
								<label for="membId">아이디</label>
							</div>
							<div class="form-conts">
								<input type="text" name="membId" id="membId" class="krds-input" placeholder="아이디" />
							</div>
						</div>
						<div class="form-group">
							<div class="form-tit">
								<label for="pswd">비밀번호</label>
							</div>
							<div class="form-conts">
								<input type="password" name="pswd" id="pswd" class="krds-input" placeholder="비밀번호" autocomplete="off" />
							</div>
						</div>
						<button type="submit" class="krds-btn">로그인</button>
					</div>

				</div>
				</form>
			</div>
		</div>
		
	</div>
</div>