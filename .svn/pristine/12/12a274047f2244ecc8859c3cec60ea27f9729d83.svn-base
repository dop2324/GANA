$(document).ready(function () {
    function navClose() {
       $('.pc_nav a').removeClass('on');
       $('.pc_nav.ver3 a').removeClass('active');
       $('.pc_nav.ver1+.nav_bg, .pc_nav.ver2+.nav_bg').attr('style','display:none;');
    }
    $('.pc_nav+.nav_bg').on('mouseover focusin', function () {
        navClose();
    });

    //pc_nav ver1,ver2
    $('.pc_nav.ver1 a, .pc_nav.ver2 a').on('mouseover focusin', function () {
        $(this).parent().parent().children().find('a').removeClass('on');
        $('.pc_nav.ver1 .depth3 a, .pc_nav.ver1 .depth2 > li > a, .pc_nav.ver2 .depth3 a, .pc_nav.ver2 .depth2 > li > a').removeClass('on');
        $(this).parent().parent().prev().addClass('on');
        $(this).addClass('on');
        $(this).parents().children('.pc_nav+.nav_bg').attr('style','display:block;');
    });
    const $navLinks = $('.pc_nav.ver1 .depth1 > li > a, .pc_nav.ver2 .depth1 > li > a');
    const $header = $('header');
    $navLinks.on('mouseover focusin', function () {
        const $this = $(this);
        const $nextElement = $this.next('.depth2_wrap');
        if ($nextElement.length > 0) {
            $nextElement.css('visibility', 'visible');
            $navLinks.not($this).next('.depth2_wrap').css('visibility', 'hidden');
            $header.addClass('active');
            //접근성
            const $lastFocusable = $nextElement.find('a').last();
            $lastFocusable.off('focusout').on('focusout', function (e) {
                setTimeout(() => {
                    const active = document.activeElement;
                    if (!$(active).closest('.pc_nav').length) {
                        navClose();
                        $navLinks.next('.depth2_wrap').css('visibility', 'hidden');
                        $header.removeClass('active');
                    }
                }, 0);
            });
        } else {
            navClose();
            $navLinks.next('.depth2_wrap').css('visibility', 'hidden');
            $header.removeClass('active');
        }
    });
    $('.pc_nav.ver1 .wrap, .pc_nav.ver2 .wrap').on('mouseleave', function () {
        navClose();
        $navLinks.next('.depth2_wrap').css('visibility', 'hidden');
        $header.removeClass('active');
    });

    //pc_nav ver3
    $('.pc_nav.ver3 .depth2 > li > a').each(function() {
        if ($(this).next().length > 0) {
            $(this).addClass('more');
        }
    });
    $('.pc_nav.ver3 a').on('mouseover focusin', function() {
        $(this).parent().parent().children().find('a').removeClass('active');
        $(this).addClass('active');
    });
    $('.pc_nav.ver3 .depth1 > li').on('mouseleave', function() {
        navClose();
    });

    //pc_nav ver4
    $('.pc_nav.ver4 .depth1 > li').each(function() {
        if ($(this).children('.depth2_wrap').length > 0) {
            $(this).addClass('has');
        }
    });
    var ver4_li_num = $('.pc_nav.ver4 .depth1 > li.has').length;
    var ver4_li_width = 100/ver4_li_num;
    $('.pc_nav.ver4 .depth1 > li').each(function() {
        var ver4_li_eq = $(this).index();
        $(this).children('.depth2_wrap').attr('style','width:' + 100/ver4_li_num + '%; left:' + ver4_li_width * ver4_li_eq + '%;');
    });
	$('.pc_nav.ver4 .depth1 > li > a').on('mouseover focusin', function () {
		$('header').addClass('on');
		$('.pc_nav.ver4 .depth1 > li > a').removeClass('on');
		$(this).addClass('on');
	});
	$('.pc_nav.ver4 a').on('mouseover focusin', function () {
		$('.pc_nav.ver4 a').removeClass('on');
		$(this).addClass('on');
		$(this).parents('.depth2_wrap').prev().addClass('on');
	});
    $('.pc_nav.ver4').on('mouseleave', function() {
		$('header').removeClass('on');
        navClose();
    });

    //m_nav
    $('.m_nav .link_list a').each(function () {
        if ($(this).next().length > 0) {
            $(this).parents('.link_list').addClass('slide_type');
        };
    });
    $('.m_nav .slide_type li > a').attr('title', '열림');
    $('.m_nav .slide_type li > a').on('click', function () {
        $(this).toggleClass('on');
        if ($(this).hasClass('on')) {
            $(this).attr('title', '닫기');
        } else {
            $(this).attr('title', '열림');
        }
        return false;
    });
    $('.m_nav a').each(function () {
        if ($(this).next().length > 0) {
            $(this).addClass('more');
        }
    });
    $('#m_nav_open').on('click', function () {
        $('.m_nav, .m_nav+.nav_bg').fadeIn();
        $('.m_nav > div').attr('style', 'right:0');
        $('html, body').addClass('noScroll');
        return false;
    });
    $('.m_nav .depth1 > li > a.on').attr('title', '선택됨').next().show();
    $('.m_nav .depth1 > li > a').on('click', function () {
        if($(this).next().length >= 1){
          $('.m_nav .depth1').addClass('on');
        }
        $('.m_nav .depth1 > li > a').removeAttr('title');
        $(this).attr('title', '선택됨').addClass('on');
        $(this).next().show();
        $('.m_nav .depth1 > li > a').not($(this)).next().hide();
        $('.m_nav .depth1 > li > a').not($(this)).removeClass('on');
        if ($(this).next().length > 0) {
            return false;
        }
    });
    $('.m_nav .depth2 > li > a.on').next('.depth3').attr('style', 'display:block');
    $('.m_nav .depth2 > li > a').addClass('off');
    $('.m_nav .depth2 > li > a.on').removeClass('off').children('ul').show();
    $('.m_nav .depth2 > li > a.more').attr('title', '열기');
    $('.m_nav .depth2 > li > a.more.on').attr('title', '닫기');
    $('.m_nav .depth2 > li > a.more').on('click', function () {
        if ($(this).hasClass('off')) {
            $('.m_nav .depth2 > li > a.more').removeClass('on').addClass('off').attr('title', '열기');
            $('.m_nav .depth3').stop().slideUp();
            $(this).attr('title', '닫기').removeClass('off').addClass('on');
            $(this).next().stop().slideDown();
        } else {
            $(this).attr('title', '열기').removeClass('on').addClass('off');
            $(this).next().slideUp();
        }
        if ($(this).next().length > 0) {
            return false;
        }
    });
    $('#m_nav_close').on('click', function () {
        $('.m_nav > div').attr('style', 'right:-100%');
        setTimeout(function () {
            $('.m_nav, .m_nav+.nav_bg').fadeOut();
            $('html, body').removeClass('noScroll');
        }, 700);
        return false;
    });

    //search
    $('#search_open').on('click', function () {
        $('#search_area').attr('style','display:flex;');
        return false;
    });
    $('#search_close').on('click', function () {
        $('#search_area').hide();
        return false;
    });

    //go_top
    $('body').append('<a href="#skipBtn" class="go_top"><span>상단으로 가기</span></a>')
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            $('.go_top').css({ opacity:1, visibility:'visible'});
        } else {
            $('.go_top').css({ opacity:0, visibility:'hidden'});
        }
    });
    $('.go_top').on('click', function() {
        $('body,html').animate({
            scrollTop: 0
        }, 500);
        $('#skipBtn').focus();
        return false;
    });

    //sub_nav
    $('.sub_nav .depth1 a').each(function () {
        if ($(this).next().length > 0) {
            $(this).addClass('more');
        }
    });
    $('.sub_nav .depth1 a:not(.on)').addClass('off');
    $('.sub_nav .depth1 a.more').attr('title', '축소됨')
    $('.sub_nav .depth1 a.on.more').attr('title', '확장됨')
    $('.sub_nav .depth1 a.on').next().show();
    $('.sub_nav .depth1 a').on('click', function (e) {
        e.stopImmediatePropagation();
        var depth = $(this).next().attr('class')
        if ($(this).hasClass('off')) {
            $(this).parent().parent().find('a.more').removeClass('on').addClass('off').attr('title', '축소됨');
            $(this).removeClass('off').addClass('on').attr('title', '확장됨');
            $('.sub_nav ' + '.' + depth).slideUp();
            $(this).next().slideDown();
        } else if ($(this).hasClass('on')) {
            $(this).parent().parent().find('a.more').removeClass('on').addClass('off').attr('title', '축소됨');
            $('.sub_nav ' + '.' + depth).slideUp();
        }
        if( $(this).next().length > 0 ){ return false; }
    });

    //sub_util share
    $('.sub_util .share > a').on('click', function() {
        $(this).toggleClass('open');
        if($(this).hasClass('open')){
            $(this).next('div').stop().slideDown('fast');
            $(this).attr('title', '닫힘');
        } else {
            $(this).next('div').stop().slideUp('fast')
            $(this).attr('title', '열림');
        }
        return false;
    });

    //content
    function table_resize() {
        $('.table').each(function () {
            var tableDiv = $(this).width() + 5;
            var table = $(this).children('table')[0].scrollWidth;
            if (tableDiv < table) {
                $(this).prev('.table_scroll').show();
            } else {
                $(this).prev('.table_scroll').hide();
            }
        });
    };
    $(window).on('resize', function () {
        table_resize();
    });
    $('.table').before('<div class="table_scroll">좌우로 움직여 내용을 확인하세요.</div>');
    $('.table_scroll').hide();
    table_resize();

});
