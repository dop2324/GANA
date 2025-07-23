$(document).ready(function () {
    // 헤더 gnb 스크립트
    let header = $('.header');
    const DEFAULT_HEADER_HEIGHT = 90;
    const DURATION = 500;
    initGnb(header);

    function initGnb(header) {
        let gnb = header.find('.header-gnb');
        let gnbMenu = gnb.find('.gnb-depth-1 .depth-1');
        let gnbMenuLink = gnb.find('.gnb-depth-1 .depth-1 > a');
        let depthItem = gnb.find('.depth-item');

        header.on('mouseleave', function () {
            gnbClose(header, depthItem);
        })

        gnbMenu.on('mouseenter focusin', function () {
            $(this).addClass('current');
        });
        gnbMenu.on('mouseleave focusout', function () {
            $(this).removeClass('current');
        });

        gnbMenuLink.on('mouseenter focusin', function () {
            let otherItems = $('.header-gnb .depth-item');
            let item = $(this).siblings('.depth-item');
            otherItems.hide().css('opacity', '0');
            gnbOpen(header, item);
        });

        gnbMenu.find('a').last().on('focusout', function () {
            gnbClose(header, depthItem);
        });

        function gnbOpen(header, item) {
            let itemHeight = calculateMaxHeight(item);
            let headerHeight = itemHeight + DEFAULT_HEADER_HEIGHT;
            header.stop().animate({
                height: headerHeight,
            }, DURATION);
            item.show().stop().animate({
                opacity: 1,
            }, DURATION);
            header.addClass('open');
        }

        function gnbClose(header, item) {
            header.stop().animate({
                height: DEFAULT_HEADER_HEIGHT,
            }, 0);
            header.removeClass('open');
            item.hide().css('opacity', '0');
        }

        function calculateMaxHeight(item) {
            let height = item.outerHeight(true);
            return height;
        }
    }

    // 모바일 헤더 gnb 스크립트
    let mobileGnb = $('.mobile-gnb');
    initMobileGnb(mobileGnb);

    function initMobileGnb(mobileGnb) {
        let html = $('html');
        let sidebarButton = mobileGnb.find('.sidebar-btn');
        let mobileMenu = mobileGnb.find('.depth-1 a');

        sidebarButton.on('click', function () {
            if (mobileGnb.hasClass('open')) {
                mobileGnbClose(mobileGnb);
            } else {
                mobileGnbOpen(mobileGnb);
            }
        });

        mobileMenu.on('click', function () {
            let thisLink = $(this);
            onClickMobileMenu(thisLink);
        });

        function mobileGnbOpen(gnb) {
            gnb.addClass('open');
            html.addClass('not-scroll');
        }

        function mobileGnbClose(gnb) {
            gnb.removeClass('open');
            html.removeClass('not-scroll');
        }

        function onClickMobileMenu(link) {
            let target = link.parent();
            let depthTarget = link.siblings('ul');
            let otherLinks = target.siblings('li');
            let otherItems = otherLinks.find('ul');

            if (target.hasClass('current')) {
                target.removeClass('current');
                depthTarget.stop().slideUp(300);
            } else {
                otherLinks.removeClass('current');
                otherItems.stop().slideUp(300);
                target.addClass('current');
                depthTarget.stop().slideDown(300);
            }
        }
    }


    // 메인배너
    var swiper = new Swiper(".mainbanner", {
        slidesPerView: 1,
        loop: true,
        effect: "fade",
        autoplay: {
            delay: 4000,
        },
        pagination: {
            el: ".mainbanner .swiper-pagination",
            clickable: true,
        },
        navigation: {
            nextEl: ".mainbanner .next",
            prevEl: ".mainbanner .prev",
        },
    });

    $(function () {
        $('.mainbanner .stop').click(function () {
            $('.mainbanner .stop').toggleClass('start')
            if ($(this).hasClass('start') == true) {
                swiper.autoplay.stop()
            } else {
                swiper.autoplay.start()
            }
        })
    });

    //이용안내 모바일일 때만 슬라이드
    var ww = $(window).width();
    var mySwiper = undefined;

    function initSwiper() {

        if (ww < 991 && mySwiper == undefined) {
            mySwiper = new Swiper(".icon-container", {
                slidesPerView: 3,
                spaceBetween: 10,
                simulateTouch: true,
                loop: true,
                //autoplay: {
                //    delay: 2000,
                //    disableOnInteraction: false,
                //},
                navigation: {
                    nextEl: ".icon-container .next",
                    prevEl: ".icon-container .prev",
                },
            });
        } else if (ww >= 991 && mySwiper != undefined) {
            mySwiper.destroy();
            mySwiper = undefined;
        }
    }

    initSwiper();

    $(window).on('resize', function () {
        ww = $(window).width();
        initSwiper();
    });

    //메인페이지 - 탭
    var $tab_list = $('.tab-wrap');
    $tab_list.find('.tab-cont').hide();
    $tab_list.find('li.active>.tab-cont').show();
    $tab_list.each(function () {
        var $this = $(this);
        $this.height($this.find('li.active>.tab-cont').height() + 95); // tab_cont가 absolute로 띄워져 있으므로 tab이 height값을 가지지 못함. active된 li의 tab_cont height값으로 강제 지정
        if (ww < 800) {
            $this.height($this.find('li.active>.tab-cont').height() + 0);
        }
    });

    function tabtoggle() {
        var $this = $(this);
        $this.siblings('.tab-cont').show();
        $this.parent('li').addClass('active');
        $this.parent('li').siblings('li').removeClass('active');
        $this.parent('li').siblings('li').children(".tab-cont").hide();
        return false;
    }
    $tab_list.find('.tab-menu').click(tabtoggle).focus(tabtoggle);

    //퀵 리모콘
    $(".quick-wrap .btn-close").on("click", function () {
        $(".quick-wrap").toggleClass("open");
        return false;
    });

    //위로가기 버튼
    $('.quick .top').click(function (e) {
        e.preventDefault();
        $('html, body').animate({
            scrollTop: 0
        }, 100);
    });


});

//추천서비스&울산시축제 슬라이드
$(window).on('load', function () {
    slider();
});

function slider() {
    $(".service-slider").each(function (index) {
        let $this = $(this);
        let swiper = undefined;
        let slideNum = $this.find('.swiper-slide').length //슬라이드 총 개수
        let slideInx = 0; //현재 슬라이드 index
        
        //디바이스 체크
        let oldWChk = window.innerWidth > 650 ? 'pc' : 'mo';
        sliderAct();
        $(window).on('resize', function () {
            let newWChk = window.innerWidth > 650 ? 'pc' : 'mo';
            if (newWChk != oldWChk) {
                oldWChk = newWChk;
                sliderAct();
            }
        })

        function sliderAct() {
            //슬라이드 인덱스 클래스 추가
            $this.addClass(`service-slider${index}`);

            //슬라이드 초기화 
            if (swiper != undefined) {
                swiper.destroy();
                swiper = undefined;
            }

            //slidesPerView 옵션 설정
            let viewNum = oldWChk == 'pc' ? 4 : 1;
            //loop 옵션 체크
            let loopChk = slideNum > viewNum;

            swiper = new Swiper(`.service-slider${index}`, {
                slidesPerView: 4,
                initialSlide: slideInx,
                spaceBetween: 40,
                slidesPerGroup: 1,
                loop: loopChk,
                pagination: {
                    el: $(`.service-slider${index} .swiper-pagination`)[0],
                    clickable: true,
                },
                navigation: {
                    prevEl: $(`.service-slider${index} .prev`)[0],
                    nextEl: $(`.service-slider${index} .next`)[0],
                },
                on: {
                    activeIndexChange: function () {
                        slideInx = this.realIndex; //현재 슬라이드 index 갱신
                    }
                },
                breakpoints: {
                    // 650px 이상에서는 2개의 슬라이드를 보여줌
                    500: {
                        slidesPerView: 1,
                        spaceBetween: 20
                    },
                    // 991px 이상에서는 3개의 슬라이드를 보여줌
                    991: {
                        slidesPerView: 3,
                        spaceBetween: 20
                    },
                    // 1300px 이상에서는 4개의 슬라이드를 보여줌
                    1300: {
                        slidesPerView: 4,
                        spaceBetween: 30
                    }
                }
            });
        }
    });
}
