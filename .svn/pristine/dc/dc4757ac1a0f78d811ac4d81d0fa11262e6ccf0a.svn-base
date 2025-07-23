<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!--header S-->
<div id="uiux-top">
	<div class="toggle-wrap">
		<div class="toggle-head">
			<div class="inner">
				<span class="nuri-txt">이 누리집은 대한민국 공식 전자정부 누리집입니다.</span>
			</div>
		</div>
	</div>
</div>
<!--#uiux-top E-->

<div id="top_layout">
	<div class="lnb_box">
		<div class="inner lnb_inner">
			<ul class="link_tm">
				<c:choose>
					<c:when test="${moa:isLogin(pageContext.request) }">
						<li><a href="<c:url value='/WebContent/func/member/logout.do' />?mnu_code=mlogin">로그아웃</a></li>
						<li><a href="<c:url value='/member/page.do' />?mnu_code=m0001" target="_self" title="이용안내">이용안내</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value='/member/page.do' />?mnu_code=membReg" class="join" target="_self" title="회원가입">회원가입</a></li>
						<li><a href="" class="idpw" target="_self" title="아이디/비번찾기">아이디/비번찾기</a></li>
						<li><a href="<c:url value='/member/page.do' />?mnu_code=m0001" target="_self" title="이용안내">이용안내</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<!--lnb_box-->
	
	<div class="tophead_box">
		<div class="inner">
			<h1 class="logo"><a href="<c:url value='/member' />"><span>울산모아회원</span></a></h1>
		</div>
	</div>
	<!--tophead_box-->
</div>
<!--#top_layout E-->