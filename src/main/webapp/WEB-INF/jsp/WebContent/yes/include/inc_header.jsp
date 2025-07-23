<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div id="top_layout" class="header-wrap">

	<div class="h-utill">
	    <div class="h-utill-inner">
	        <a href="https://www.ulsan.go.kr/u/rep/main.ulsan" target="_blank">울산광역시청</a>
	        <div class="h-info">
	            <ul>
	                <li><a href="#">로그인</a></li>
	                <li><a href="#">회원가입</a></li>
	            </ul>
	        </div>
	    </div>
	</div>
	
	<header class="header">
		<div class="header-inner">
			<h1 class="logo">
				<a href="<c:url value='/yes' />"><span class="sr-only">울산모아 통합예약</span></a>
			</h1>
			
			<!-- PC gnb -->
			<nav id="gnb" class="header-gnb">
				<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_gnb" />
			</nav>
			<!-- PC gnb -->
			
			<!-- Mobile gnb -->
			<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_mgnb" />
			<!-- Mobile gnb -->
		</div>
	</header>
</div>
