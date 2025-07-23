<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- 로그인 체크 --%>
<c:if test="${moa:isLogin(pageContext.request)}">
    <c:redirect url="/member/page.do">
        <c:param name="mnu_code" value="membMypage"/>
    </c:redirect>
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<title><c:out value="${siteVo.site_nm }" /></title>
<meta name="title" 			content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="subject" 		content="<c:out value="${menuVo.mnu_nm }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="keywords" 		content="<c:out value="${siteName }" />">
<meta name="description" 	content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteVo.site_nm }" />">
<meta name="author" 		content="<c:out value="${siteVo.site_nm }" />">
<meta property="og:type" content="website">
<meta property="og:description" content="<c:out value="${menuVo.mnu_desc }" /> | <c:out value="${siteVo.site_nm }" />">
<meta property="og:image" content="">
<meta property="og:url" content="">

<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_head" />

<c:import url="/common/link/link.do">
	<c:param name="path" value="/WebContent/common/inc_popupScript" />
	<c:param name="lgp_sn" value="10" />
</c:import>

</head>
<body>
<div id="skip_nav">
		<ul>
			<li><a class="accessibility" href="#top_layout">주요 메뉴로 건너뛰기</a></li>
			<li><a class="accessibility" href="#body_layout">본문으로가기</a></li>
		</ul>
</div>
<!--#skip_nav E-->

<div id="wrap" class="">

<%-- header S --%>
<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_header">
	<c:param name="depth2Code" value="${depth2Code}" />
	<c:param name="depth3Code" value="${depth3Code}" />
	<c:param name="depth4Code" value="${depth4Code}" />
</c:import>
<%-- header E --%>

<div id="body_layout">
	<div class="top_box_logo">
		<span class="slogn">새로 만드는 위대한 울산</span>
	</div>
	

	<div class="mainbg">
		<div class="loginWrap">
			<div class="inner">
				<h2>울산모아 <span class="gradient">통합예약 로그인</span></h2>
				
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
							
							<div class="sbox">
								<ul>
									<li><a href="<c:url value='/member/page.do' />?mnu_code=membReg" class="medium link pure"><span class="color">회원가입</span></a></li>
									<li><a href="<c:url value='/member/page.do' />?mnu_code=membFind" class="medium link pure"><span>아이디ㆍ비밀번호 찾기</span></a></li>
									<li><a href="#!" class="medium link pure"><span>비밀번호 재발급</span></a></li>
								</ul>
							</div>
						</div>
	
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<!--loginWrap E-->
	
	<div id="foot_layout" class="foot_layout">
		<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
	</div>
	
</div>
<%-- body_layout E --%>

</div>

</body>
</html>