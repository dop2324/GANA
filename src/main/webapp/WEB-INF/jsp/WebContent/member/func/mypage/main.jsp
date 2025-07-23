<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="conts-area">
	<div class="conts-wrap">
		
		<h4>나의 정보</h4>
		
		<div class="txt-box-my">
			<p class="outline-txt graybg">
				<strong><c:out value="${sessionScope.MOA_LOGIN_INFO.membNm }" />(<c:out value="${sessionScope.MOA_LOGIN_INFO.membId }" />)</strong> 님 반갑습니다.
			</p>
			
			<div class="conts-desc-wrap">
				<h5>바로가기</h5>
				<p class="outline-txt graybg">울산광역시에서는 모아회원의 개인정보를 보호하기 위하여 비밀번호 연속실패, 장기미접속시등 그외 기타 이용약관에 위배될 경우 서비스 이용중지를 하고있습니다.</p>
				<a href="#" class="krds-btn small">회원이용약관</a>
				<a href="#" class="krds-btn small">공지사항</a>
				<a href="#" class="krds-btn small">자주하는질문</a>
			</div>
			
			<div class="conts-desc-wrap">
				<h5>연동된 인증정보</h5>
				네이버/카카오/휴대폰인증
			</div>
			
			<div class="conts-desc-wrap">
				<h5>이용가능한 감면정보</h5>
				만 65세 이상<br />
				울산시 거주자<br />
				북구 거주자<br />
				다자녀
			</div>
		</div>
		<!--txt-box-my-->
		
		<h4>정보변경</h4>
		<div class="graybg"><p>가입일시 2025-06-26 09:11:22</p><p>/ 마지막 로그인 2025-06-26 09:11:22</p></div>
	
		<div class="greenbg">
			<ul>
				<li><a href="<c:url value='${listLink }' />funcId=mdfcn" target="_self" title="개인정보수정"><span>개인정보수정</span></a></li>
				<li><a href="<c:url value='${listLink }' />funcId=pswdchg" target="_self"><span>비밀번호 변경</span></a></li>
				<li><a href="<c:url value='${listLink }' />funcId=wdrw" target="_self"><span>회원탈퇴</span></a></li>
				<li><a href="#" target="_blank"><span>비대면 감면 인증정보</span></a></li>
			</ul>
		</div>
	
	</div>
</div>

