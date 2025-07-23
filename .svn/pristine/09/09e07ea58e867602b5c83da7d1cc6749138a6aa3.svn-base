$(document).ready(function(){
  function pcNavClose() {
    $('#pc_nav .depth01 > li > a').next('.gnb').hide();
    $('#header').removeClass('hover');
  }
  function mNavClose() {
    $('#m_nav').fadeOut();
    $('#m_nav nav').attr('style', 'right:-100%');
  }
  function removeNoScroll() {
    $('html, body').removeClass('noScroll');
    $('html, body').off('scroll touchmove mousewheel');
  }
  function searchClose() {
    $('#header .search_area:not(:animated)').slideUp(200);
    $('.search_open').attr('title', '열기');
    $('#header').removeAttr("style");
  }

  /*pc nav*/
  $('#pc_nav .depth01 > li > a').on('focusin mouseover', function() {
    $('#header').addClass('hover');
    $(this).next('.gnb:not(:animated)').slideDown();
    $('#pc_nav .depth01 > li > a').not($(this)).next('.gnb').hide();
    searchClose();
  });
  $('#pc_nav .depth02 > li').each(function() {
    if ($(this).children('ul').length > 0) {
      $(this).addClass('has');
    }
  });
  $('#pc_nav').on('mouseleave', function() {
    pcNavClose();
  });
  $('.right a, #container, .logo a').focus(function() {
    pcNavClose();
  });

  //로그인토글
  $('#header .right .logout_toggle').on('focusin mouseover', function(){
      $(this).addClass('hover');
      pcNavClose();
      searchClose();
      return false;
  });
  $('#header .right .logout_toggle').on('focusout mouseleave', function(){
      $(this).removeClass('hover');
      return false;
  });

  //검색토글
  $('#header .search_toggle .search_open').on('click', function() {
      $('#header .search_area:not(:animated)').slideDown(200);
      $('#header .search_toggle').addClass('on');
      $(this).attr('title', '펼쳐짐');
      return false;
  });
  $('#header .search_toggle .close').click(function(){
      searchClose();
      return false;
  });
  $('.sitemap').on('focusin', function() {
      searchClose();
  });
  $('#h_logo').on('focusin', function() {
      searchClose();
  });

  //m_nav
  $('#openMnav').on('click', function() {
    $('#m_nav').fadeIn();
    $('#m_nav nav').attr('style', 'right:0');
    $('html, body').addClass('noScroll');
    $('html, body').on('scroll touchmove mousewheel', function(event) {
      event.preventDefault();
      event.stopPropagation();
    });
    return false;
  });
  $('#closeMnav > a').on('click', function() {
    mNavClose();
    removeNoScroll();
    return false;
  });

  $('#m_nav .depth01 > li > a').on('click', function() {
    $('#m_nav .depth01 > li').removeClass('on');
    $(this).next().children().find('.depth02').slideToggle();
    $('#m_nav .depth01 > li > a').not($(this)).next().children().find('.depth02').slideUp();
    $(this).parent().addClass('on');
    if( $(this).next().length > 0 ){ return false };
  });
  $('#m_nav .depth02 > li > a').on('click', function() {
    $('#m_nav .depth02 > li').removeClass('on');
    $(this).next('.depth03').slideToggle();
    $('#m_nav .depth02 > li > a').not($(this)).next().slideUp();
    $(this).parent().addClass('on');
    if( $(this).next().length > 0 ){ return false };
  });

  $('html').click(function(e) {
    if (!$(e.target).hasClass('area')) {
      mNavClose();
      removeNoScroll();
    }
  });
});
