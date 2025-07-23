$(document).ready(function(){
    
    $('.m_nav.ver1 .depth1').addClass('on');
    $('#snb a.on+.depth02').show();
    $('#snb .depth01 > li.has > a').attr('title', '열기');
    $('#snb .depth01 > li.has > a.on').attr('title', '닫기');
    $('#snb .depth01 > li.has > a').on('click', function() {
        if ($(this).hasClass('off')) {
        $('#snb .depth01 > li > a').removeClass('on').addClass('off').attr('title', '열기');
        $('#snb .depth02').stop().slideUp(200);
        $(this).removeClass('off').addClass('on').attr('title', '닫기');
        $(this).next('ul').stop().slideDown(200);
        } else {
        $(this).removeClass('on').addClass('off').attr('title', '열기');
        $(this).next('ul').slideUp(200);
        };
        if ($(this).next().length > 0) {
        return false
        };
    });

    
    $('.dark_mode_btn').on('click', function () {
        $('body').toggleClass('dark_mode');
        $(this).toggleClass('on');
        return false;
    });
});