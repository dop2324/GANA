<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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

<link rel="stylesheet" href="<c:url value='/design/company/css/main.css' />">
<script src="<c:url value='/design/company/js/main.js' />"></script>


<c:import url="/common/link/link.do">
	<c:param name="path" value="/WebContent/common/inc_popupScript" />
	<c:param name="lgp_sn" value="10" />
</c:import>

</head>
<body>
	<div id="skipBtn">
        <a href="#content">본문 바로가기</a>
    </div>
    <header id="header">
    	<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_header">
			<c:param name="depth2Code" value="${depth2Code}" />
			<c:param name="depth3Code" value="${depth3Code}" />
			<c:param name="depth4Code" value="${depth4Code}" />
		</c:import>
    </header>

	<%--레이어 팝업 시작 --%>
    <div id="layerPopup" class="wrap">
	    <c:import url="/common/link/link.do">
			<c:param name="path" value="/WebContent/common/inc_popupLayer" />
			<c:param name="lgp_sn" value="7" />
		</c:import>
    </div>

	<div id="container" class="wrap">
	    <div class="cont01">
	    	<article class="visual left">
					<%--알림창 --%>
			    <c:import url="/common/link/link.do">
						<c:param name="path" value="/WebContent/common/inc_popupZone" />
						<c:param name="lgp_sn" value="8" />
					</c:import>
		    </article>

		    <div class="right">
		    <article class="board">
			    <h2 class="hidden">게시판</h2>
				<div class="tab">
				    <h3 class="on"><a href="#board01">게시판01</a></h3>
				    <h3><a href="#board02">게시판02</a></h3>
				    <h3><a href="#board03">게시판03</a></h3>
				</div>

				<div class="tab_cont">
					<!--공지사항-->
					<div id="board01">
						<c:import url="/common/board/board.do">
							<c:param name="mnu_code" 	value="m0010313"/>
							<c:param name="mnu_nm"  	value="유튜브 게시판"/>
							<c:param name="pageSize"  	value="4"/>
							<c:param name="classOn"  	value="0"/>
							<c:param name="path" 		value="/WebContent/${path }/include/inc_mainBoardList" />
						</c:import>
					</div>
					<div id="board01">
						<c:import url="/common/board/board.do">
							<c:param name="mnu_code" 	value="m0010301"/>
							<c:param name="mnu_nm"  	value="본인확인 게시판"/>
							<c:param name="pageSize"  	value="4"/>
							<c:param name="classOn"  	value="0"/>
							<c:param name="path" 		value="/WebContent/${path }/include/inc_mainBoardList" />
						</c:import>
					</div>
					<!--<div id="board01">
						<c:import url="/common/board/board.do">
							<c:param name="mnu_code" 	value="m0010310"/>
							<c:param name="mnu_nm"  	value="미인증 게시판"/>
							<c:param name="pageSize"  	value="4"/>
							<c:param name="classOn"  	value="0"/>
							<c:param name="path" 		value="/WebContent/${path }/include/inc_mainBoardList" />
						</c:import>
					</div>-->
				</div>
			</article>
			</div>
		</div>
		<div class="cont02">
			<div class="tit">
				<h2>Quick Link</h2>
				<p>주요사이트를 빠르게<br>이용하실수 있도록 안내합니다.</p>
				<div class="control pink">
					<div>
						<a href="" class="prev">이전</a>
						<a href="" class="stop">정지</a>
						<a href="" class="play">재생</a>
						<a href="" class="next">다음</a>
					</div>
				</div>
			</div>
			<div class="right">
				<div class="site slide swiper-container">
					<ul class="swiper-wrapper">
						<li class="swiper-slide icon1"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon2"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon3"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon4"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon5"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon6"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon7"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon8"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon9"><a href="#">사이트링크</a></li>
						<li class="swiper-slide icon10"><a href="#">사이트링크</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>

	<footer id="footer">
        <c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
    </footer>
</body>
</html>
