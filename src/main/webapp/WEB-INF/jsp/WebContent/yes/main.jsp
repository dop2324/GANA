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

<c:import url="/common/link/link.do">
	<c:param name="path" value="/WebContent/common/inc_popupScript" />
	<c:param name="lgp_sn" value="10" />
</c:import>
</head>
<body>
    <div id="krds-skip-link">
        <a href="#gnb">주요 메뉴로 바로가기</a>
        <a href="#main-visual">본문 바로가기</a>
    </div>
    
	<%-- header S --%>
	<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_header">
		<c:param name="depth2Code" value="${depth2Code}" />
		<c:param name="depth3Code" value="${depth3Code}" />
		<c:param name="depth4Code" value="${depth4Code}" />
	</c:import>
	<%-- header E --%>
	
	<!-- 메인비주얼 -->
    <div id="main-visual" class="main-visual">
        <div class="inner">
            <!-- 메인배너 -->
            <div class="mainbanner">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <a href="#">
                            <img src="img/main/mainbanner01.jpg" alt="">
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#">
                            <img src="img/main/mainbanner02.jpg" alt="">
                        </a>
                    </div>
                </div>

                <div class="control">
                    <!-- If we need pagination -->
                    <div class="swiper-pagination"></div>

                    <!-- If we need navigation buttons -->
                    <div class="prev btn">이전으로</div>
                    <a href="javascript:void(0);" class="stop btn">정지</a>
                    <div class="next btn">다음으로</div>
                </div>
            </div>
            <!-- //메인배너 -->

            <!-- 오른쪽 내용 -->
            <div class="right-section">
                <div class="tit">
                    <p class="t1">울산의 다양한 시설을 이용해보세요!</p>
                    <h2 class="t2">울산모아 <span>통합예약</span></h2>
                    <div class="t3"><span>이용하시려는 서비스</span>를 예약해보세요!</div>
                </div>

                <!-- 검색박스 -->
                <div class="search-box-wrap">
                    <form name="totalSearchForm" action="totalsearch.do" method="get">
                        <fieldset>
                            <legend>통합검색</legend>
                            <div class="search-box">
                                <select name="q_reservTab" class="search-select search-item" title="검색영역선택">
                                    <option value="tab01">통합검색</option>
                                    <option value="tab02">체육시설</option>
                                    <option value="tab03">시설대관</option>
                                    <option value="tab04">문화체험</option>
                                    <option value="tab05">교육강좌</option>
                                    <option value="tab06">보건</option>
                                    <option value="tab06">나의 예약현황</option>
                                </select>
                                <span class="search-input search-item">
                                    <input type="text" title="검색어" placeholder="검색어를 입력하세요" class="search-query" name="q-searchVal">
                                </span>
                                <span class="search-button search-item">
                                    <button type="submit" class="search-submit">검색</button>
                                </span>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <!-- //검색박스 -->

                <!-- 모바일-예약하기 -->
                <div class="mobile-reserve">
                    <h3>예약하기</h3>
                    <ul>
                        <li>
                            <select name="" class="search-select search-item" title="검색영역선택">
                                <option value="">서비스 선택</option>
                                <option value="">체육시설</option>
                                <option value="">시설대관</option>
                                <option value="">문화체험</option>
                                <option value="">교육강좌</option>
                                <option value="">보건</option>
                            </select>
                        </li>
                        <li>
                            <select name="" class="search-select search-item" title="검색영역선택">
                                <option value="">기관선택</option>
                                <option value="">기관선택1</option>
                                <option value="">기관선택2</option>
                                <option value="">기관선택3</option>
                                <option value="">기관선택4</option>
                            </select>
                        </li>
                        <li>
                            <select name="" class="search-select search-item" title="검색영역선택">
                                <option value="">강좌선택</option>
                                <option value="">강좌선택1</option>
                                <option value="">강좌선택2</option>
                                <option value="">강좌선택3</option>
                                <option value="">강좌선택4</option>
                            </select>
                        </li>
                        <li>
                            <a href="javascript:void(0);" class="btn-reserve search-item"><span>예약하기</span></a>
                        </li>
                    </ul>
                </div>
                <!-- //모바일-예약하기 -->

            </div>
            <!-- //오른쪽 내용 -->
        </div>
    </div>
    <!-- //메인비주얼 -->
    
    <!-- 이용안내 -->
    <div class="information">
        <div class="inner">
            <h3>이용안내</h3>
            <div class="icon-container">
                <ul class="swiper-wrapper clearfix">
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_01.png" alt="접수중인 강좌 아이콘"></div>
                            <p>접수중인 강좌</p>
                        </a>
                    </li>
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_02.png" alt="FAQ 아이콘"></div>
                            <p>FAQ</p>
                        </a>
                    </li>
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_03.png" alt="예약변경안내 아이콘"></div>
                            <p>예약변경안내</p>
                        </a>
                    </li>
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_04.png" alt="결제안내 아이콘"></div>
                            <p>결제안내</p>
                        </a>
                    </li>
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_05.png" alt="결제오류해결 아이콘"></div>
                            <p>결제오류해결</p>
                        </a>
                    </li>
                    <li class="swiper-slide">
                        <a href="#">
                            <div class="imgWrap"><img src="img/main/information_06.png" alt="공공시설안내 아이콘"></div>
                            <p>공공시설안내</p>
                        </a>
                    </li>
                </ul>

                <!-- If we need navigation buttons -->
                <div class="prev slide-btn">이전으로</div>
                <div class="next slide-btn">다음으로</div>
            </div>
        </div>
    </div>
    <!-- //이용안내 -->

    <!-- pc-예약하기 -->
    <div class="reserve">
        <div class="inner">
            <h3>예약하기</h3>

            <div class="tab-wrap">
                <ul>
                    <!-- 서비스별 -->
                    <li class="active">
                        <a href="#" class="tab-menu"><span>서비스별 보기</span></a>

                        <div class="tab-cont">
                            <div class="step-wrap">
                                <!--Step01-->
                                <div class="step step01">
                                    <h5 class="title">Step 01 서비스선택</h5>
                                    <ul>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip" id="rdo_chip_1" checked>
                                            <label for="rdo_chip_1">
                                                <img src="img/main/option01.png" alt="체육시설">
                                                <span>체육시설</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip" id="rdo_chip_2">
                                            <label for="rdo_chip_2">
                                                <img src="img/main/option02.png" alt="시설대관">
                                                <span>시설대관</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip" id="rdo_chip_3">
                                            <label for="rdo_chip_3">
                                                <img src="img/main/option03.png" alt="문화체험">
                                                <span>문화체험</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip" id="rdo_chip_4">
                                            <label for="rdo_chip_4">
                                                <img src="img/main/option04.png" alt="교육강좌">
                                                <span>교육강좌</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip" id="rdo_chip_5">
                                            <label for="rdo_chip_5">
                                                <img src="img/main/option05.png" alt="보건">
                                                <span style="padding-right: 40px;">보건</span>
                                            </label>
                                        </li>
                                    </ul>
                                </div>

                                <!--Step02-->
                                <div class="step step02">
                                    <h5 class="title">Step 02 기관선택</h5>
                                    <div class="sch-wrap">
                                        <span class="search-input search-item">
                                            <input type="text" title="검색어" placeholder="검색어를 입력하세요" class="search-query" name="">
                                        </span>
                                        <button type="button" class="search-button search-item krds-btn icon">
                                            <span class="sr-only">검색</span>
                                            <i class="svg-icon ico-sch"></i>
                                        </button>
                                    </div>
                                    <div class="list">
                                        <ul>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution1" checked>
                                                <label for="institution1">울산광역시여성회관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution2">
                                                <label for="institution2">울산여성인력개발센터</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution3">
                                                <label for="institution3">울산박물관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution4">
                                                <label for="institution4">울산대곡박물관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution5">
                                                <label for="institution5">울산암각화박물관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution6">
                                                <label for="institution6">약사동제방유적전시관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution7">
                                                <label for="institution7">울산시립미술관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution" id="institution8">
                                                <label for="institution8">울산혁신도시 복합혁신센터</label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <!--Step03-->
                                <div class="step step03">
                                    <h5 class="title">Step 03 강좌선택</h5>
                                    <div class="sch-wrap">
                                        <span class="search-input search-item">
                                            <input type="text" title="검색어" placeholder="검색어를 입력하세요" class="search-query" name="">
                                        </span>
                                        <button type="button" class="search-button search-item krds-btn icon">
                                            <span class="sr-only">검색</span>
                                            <i class="svg-icon ico-sch"></i>
                                        </button>
                                    </div>
                                    <div class="list type2">
                                        <ul>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture1" checked>
                                                <label for="lecture1">001-인테리어 생활도배</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture2">
                                                <label for="lecture2">전시연계 교육프로그램 - 도심 속 자연공방 (19일> 오전 - 10:30~12:00)</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture3">
                                                <label for="lecture3">003-재산보호와 투자</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture4">
                                                <label for="lecture4">004-퓨전영양떡</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture5">
                                                <label for="lecture5">005-앙금플라워</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="lecture" id="lecture6">
                                                <label for="lecture6">006-재봉틀 기초</label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <a class="btn_reserve" href="#"><span>예약<br>하기</span></a>
                            </div>
                        </div>
                    </li>

                    <!-- 지역별 -->
                    <li>
                        <a href="#" class="tab-menu"><span>지역별 보기</span></a>

                        <div class="tab-cont active">
                            <div class="step-wrap">
                                <!--Step01-->
                                <div class="step step01">
                                    <h5 class="title">Step 01 서비스선택</h5>
                                    <ul>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip_2" id="rdo_chip_2_1" checked>
                                            <label for="rdo_chip_2_1">
                                                <img src="img/main/option01.png" alt="체육시설">
                                                <span>체육시설</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip_2" id="rdo_chip_2_2">
                                            <label for="rdo_chip_2_2">
                                                <img src="img/main/option02.png" alt="시설대관">
                                                <span>시설대관</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip_2" id="rdo_chip_2_3">
                                            <label for="rdo_chip_2_3">
                                                <img src="img/main/option03.png" alt="문화체험">
                                                <span>문화체험</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip_2" id="rdo_chip_2_4">
                                            <label for="rdo_chip_2_4">
                                                <img src="img/main/option04.png" alt="교육강좌">
                                                <span>교육강좌</span>
                                            </label>
                                        </li>
                                        <li>
                                            <input type="radio" class="radio" name="rdo_chip_2" id="rdo_chip_2_5">
                                            <label for="rdo_chip_2_5">
                                                <img src="img/main/option05.png" alt="보건">
                                                <span style="padding-right: 40px;">보건</span>
                                            </label>
                                        </li>
                                    </ul>
                                </div>

                                <!--Step02-->
                                <div class="step step02 map">
                                    <h5 class="title">Step 02 기관선택</h5>
                                    <div class="map-wrap">
                                        <div class="map-img">
                                            <img src="img/main/area_1.png" alt="울산 남구">
                                        </div>

                                        <ul class="map-list">
                                            <li class="area_1">
                                                <input type="radio" class="radio" name="area" id="area_1" checked>
                                                <label for="area_1">
                                                    <span>남구</span>
                                                </label>
                                            </li>
                                            <li class="area_2">
                                                <input type="radio" class="radio" name="area" id="area_2">
                                                <label for="area_2">
                                                    <span>중구</span>
                                                </label>
                                            </li>
                                            <li class="area_3">
                                                <input type="radio" class="radio" name="area" id="area_3">
                                                <label for="area_3">
                                                    <span>동구</span>
                                                </label>
                                            </li>
                                            <li class="area_4">
                                                <input type="radio" class="radio" name="area" id="area_4">
                                                <label for="area_4">
                                                    <span>북구</span>
                                                </label>
                                            </li>
                                            <li class="area_5">
                                                <input type="radio" class="radio" name="area" id="area_5">
                                                <label for="area_5">
                                                    <span>울주군</span>
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <!--Step03-->
                                <div class="step step03">
                                    <h5 class="title">Step 03 강좌선택</h5>
                                    <div class="sch-wrap">
                                        <span class="search-input search-item">
                                            <input type="text" title="검색어" placeholder="검색어를 입력하세요" class="search-query" name="">
                                        </span>
                                        <button type="button" class="search-button search-item krds-btn icon">
                                            <span class="sr-only">검색</span>
                                            <i class="svg-icon ico-sch"></i>
                                        </button>
                                    </div>
                                    <div class="list">
                                        <ul>
                                            <li>
                                                <input type="radio" class="radio" name="institution_2" id="institution_2_1" checked>
                                                <label for="institution_2_1">울산광역시여성회관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution_2" id="institution_2_2">
                                                <label for="institution_2_2">울산여성인력개발센터</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution_2" id="institution_2_3">
                                                <label for="institution_2_3">울산박물관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution_2" id="institution_2_4">
                                                <label for="institution_2_4">울산대곡박물관</label>
                                            </li>
                                            <li>
                                                <input type="radio" class="radio" name="institution_2" id="institution_2_5">
                                                <label for="institution_2_5">울산암각화박물관</label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <a class="btn_reserve" href="#"><span>예약<br>하기</span></a>
                            </div>
                        </div>

                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //pc-예약하기 -->

    <!-- 추천서비스 -->
    <div class="service">
        <div class="inner">
            <h3>
                <p>추천 서비스</p> <span>자주 이용되는 공공서비스를 추천해드립니다!</span>
            </h3>

            <div class="tab-wrap clearfix">
                <ul class="clearfix">
                    <!-- 강좌/교육 -->
                    <li class="active">
                        <a href="#" class="tab-menu"><span>강좌/교육</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-01">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service01.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service04.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 공연/전시 -->
                    <li>
                        <a href="#" class="tab-menu"><span>공연/전시</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-02">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 체험견학 -->
                    <li>
                        <a href="#" class="tab-menu"><span>체험견학</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-03">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/service03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type1">접수중</span>
                                                <span class="type2">강좌/교육</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //추천서비스 -->

    <!-- 울산시 축제 -->
    <div class="service type2">
        <div class="inner">
            <h3>
                <p>울산시 축제</p> <span>울산시에서 열리는 다양한 축제를 알려드려요!</span>
            </h3>

            <div class="tab-wrap">
                <ul>
                    <!-- 전체 -->
                    <li class="active">
                        <a href="#" class="tab-menu"><span>전체</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-04">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival01.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival01.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival01.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 중구 -->
                    <li>
                        <a href="#" class="tab-menu"><span>중구</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-05">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 남구 -->
                    <li>
                        <a href="#" class="tab-menu"><span>남구</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-06">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival03.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 동구 -->
                    <li>
                        <a href="#" class="tab-menu"><span>동구</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-07">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival04.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival04.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival04.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>

                    <!-- 울주군 -->
                    <li>
                        <a href="#" class="tab-menu"><span>울주군</span></a>

                        <div class="tab-cont">
                            <div class="service-slider service-slider-08">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type3">예정</span>
                                                <span class="type2">울주군</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                    <div class="swiper-slide">
                                        <a href="#">
                                            <div class="imgWrap">
                                                <img src="img/main/festival02.jpg" alt="">
                                            </div>
                                            <div class="state">
                                                <span class="type4">진행중</span>
                                                <span class="type2">남구</span>
                                            </div>
                                            <div class="title">001-인테리어 생활도배</div>
                                            <ul class="time">
                                                <li class="time-item">
                                                    <span>접수일자ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                                <li class="time-item">
                                                    <span>교육기간ㅣ</span> 25.02.03 14:00 ~ 25.04.28 16:00
                                                </li>
                                            </ul>
                                        </a>
                                    </div>
                                </div>

                                <!-- If we need pagination -->
                                <div class="control">
                                    <div class="swiper-pagination"></div>
                                    <!-- <a href="javascript:void(0);" class="stop btn">정지</a> -->
                                </div>

                                <!-- If we need navigation buttons -->
                                <div class="slide-btn prev">이전으로</div>
                                <div class="slide-btn next">다음으로</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- //울산시 축제 -->

    <!-- 공지사항&자주묻는 질문 -->
    <div class="news-wrap">
        <div class="inner">
            <!-- 공지사항 -->
            <div class="notice">
                <h3>공지사항</h3>
                <ul class="notice-cont">
                    <li>
                        <a href="#">
                            <div>[미포체육센터] 2025년 4월 대회 및 행사 일정(변경) 2025년 4월 대회 및 행사 일정(변경)</div>
                            <p class="date">2025.03.28</p>
                            <span><img src="img/main/i_arrow_03.png" alt="화살표"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div>[미포체육센터] 2025년 4월 대회 및 행사 일정(변경)</div>
                            <p class="date">2025.03.28</p>
                            <span><img src="img/main/i_arrow_03.png" alt="화살표"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div>[미포체육센터] 2025년 4월 대회 및 행사 일정(변경)</div>
                            <p class="date">2025.03.28</p>
                            <span><img src="img/main/i_arrow_03.png" alt="화살표"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div>[미포체육센터] 2025년 4월 대회 및 행사 일정(변경)</div>
                            <p class="date">2025.03.28</p>
                            <span><img src="img/main/i_arrow_03.png" alt="화살표"></span>
                        </a>
                    </li>
                </ul>
                <a class="btn_plus" href="#">더보기</a>
            </div>
            <!-- //공지사항 -->

            <!-- 자주묻는 질문 -->
            <div class="qna">
                <h3>자주묻는 질문</h3>
                <ul class="qna-cont">
                    <li>
                        <a href="#">
                            <span>Q</span>
                            <p>인기 강좌 예약 성공 가이드</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span>Q</span>
                            <p>결제하기 오류 해결 방법</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span>Q</span>
                            <p>공공예약시스템 홈페이지 작동이 잘 안되는 경우</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span>Q</span>
                            <p>회원정보와 비밀번호는 어떻게 변경하나요?</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span>Q</span>
                            <p>아이디와 비밀번호를 잊었습니다.</p>
                        </a>
                    </li>
                </ul>
                <a class="btn_plus" href="#">더보기</a>
            </div>
            <!-- //자주묻는 질문 -->
        </div>
    </div>
    <!-- //공지사항&자주묻는 질문 -->

    <!-- 퀵 리모콘 -->
    <div class="quick-wrap">
        <a href="javascript:void(0);" class="btn-close">닫기</a>
        <div class="quick">
            <div class="font-size">
                <p>글자크기</p>
                <ul class="btn-wrap">
                    <li><a class="plus btn" href="javascript:void(0)">확대</a></li>
                    <li><a class="minus btn" href="javascript:void(0)">축소</a></li>
                </ul>
            </div>
            <ul class="icons">
                <li>
                    <a href="#">
                        <div class="imgWrap"><img src="img/main/quick_01.png" alt="최근 본 화면 아이콘"></div>
                        <p>최근 본 화면</p>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="imgWrap"><img src="img/main/quick_02.png" alt="예약현황 아이콘"></div>
                        <p>예약현황</p>
                    </a>
                </li>
            </ul>
            <button class="top"><span>위로가기</span></button>
        </div>
    </div>
    <!-- //퀵 리모콘 -->
	
	
	<c:import url="/EgovPageLink.do?link=/WebContent/${path }/include/inc_footer" />
	
</body>
</html>