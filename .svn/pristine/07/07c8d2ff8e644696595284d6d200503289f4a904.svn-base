$(document).ready(function () {
	//visual
	var swiper = new Swiper('.visual_slide', {
		slidesPerView: 1,
		loop: true,
		autoplay: {
			delay: 3000,
			disableOnInteraction: true
		},
		navigation: {
			nextEl: '.visual .swiper-button-next',
			prevEl: '.visual .swiper-button-prev'
		},
		a11y: {
			enabled: true,
			prevSlideMessage: '이전 슬라이드',
			nextSlideMessage: '다음 슬라이드',
			slideLabelMessage: '총 {{slidesLength}}장의 슬라이드 중 {{index}}번 슬라이드 입니다.'
		},
		breakpoints: {
			876: {
				slidesPerView: 1
			},
			768: {
				slidesPerView: 2,
				spaceBetween: 20,
				loop: true
			}
		}
	});

	var sw = 0;
	$('.swiper-button-pause').click(function () {
		if (sw == 0) {
			$('.swiper-button-pause').addClass('on').text('재생');
			swiper.autoplay.stop();
			sw = 1;
		} else {
			$('.swiper-button-pause').removeClass('on').text('정지');
			swiper.autoplay.start();
			sw = 0;
		}
	});

	$('.swiper-button-next').click(function () {
		if (sw == 0) {
			$('.swiper-button-pause').addClass('on').text('재생');
			swiper.autoplay.stop();
			sw = 1;
		}
	});

	$('.swiper-button-prev').click(function () {
		if (sw == 0) {
			$('.swiper-button-pause').addClass('on').text('재생');
			swiper.autoplay.stop();
			sw = 1;
		}
	});

	//board
	$('article .tab_cont').each(function () {
		$(this).children('div').not(':first').hide();
	});
	$('article .tab').each(function () {
		$(this).children('.on').children().attr('title', '선택됨');
		$(this).find('a').click(function () {
			$(this).closest('article').find('.tab_cont').children('div').hide();
			$(this).closest('article').find('.tab').children().removeClass('on');
			$(this).closest('article').find('.tab').children().children().removeAttr('title');
			var idx = $(this).parent().index();
			$(this).closest('article').find('.tab_cont').children('div:eq(' + idx + ')').show();
			$(this).parent().addClass('on').children().attr('title', '선택됨');
			return false;
		});
	});

	//quick
	var swiper2 = new Swiper('.cont02 .site', {
		slidesPerView: 7,
		loop: true,
		autoplay: {
			delay: 3000,
			disableOnInteraction: true
		},
		navigation: {
			nextEl: '.control a.next',
			prevEl: '.control a.prev'
		},
		breakpoints: {
			1400: {
				slidesPerView: 5
			},
			768: {
				slidesPerView: 4,
			},
			480: {
				slidesPerView: 3,
			},
			320: {
				slidesPerView: 2,
			}
		}
	});
	$('.cont02 .stop').on('click', function(){
        swiper2.autoplay.stop()
        $(this).parent().children('.play').attr('style','display:flex;');
        $(this).hide();
        return false;
    })
    $('.cont02 .play').on('click', function(){
        swiper2.autoplay.start()
        $(this).hide();
        $(this).parent().children('.stop').attr('style','display:flex;');
        return false;
    });

	/*
	//site
	var siteSet = {
		useAutoplayToggleButton: false,
		slidesToShow: 7,
		slidesToScroll: 1,
		infinite: true,
		//autoplay: true,
		//autoplaySpeed: 3000,
		prevArrow: $('.cont02 .prev'),
		nextArrow: $('.cont02 .next'),
		responsive: [{
			breakpoint: 1440,
			settings: {
				slidesToShow: 6,
				slidesToScroll: 6,
			}
		},
		{
			breakpoint: 1240,
			settings: {
				slidesToShow: 5,
				slidesToScroll: 5,
			}
		},
		{
			breakpoint: 768,
			settings: {
				slidesToShow: 4,
				slidesToScroll: 4,
			}
		},
		{
			breakpoint: 480,
			settings: {
				slidesToShow: 3,
				slidesToScroll: 3,
			}
		},
		{
			breakpoint: 321,
			settings: {
				slidesToShow: 2,
				slidesToScroll: 2,
			}
		}
		],
	}
	$('.cont02 .slide').slick(siteSet)
	$('.cont02 .view').on('click', function () {
		$('.cont02').addClass('on');
		if ($('.cont02').hasClass('on')) {
			$('.cont02 .slide').slick('unslick');
			$('.cont02 .swiper-slide a').attr('tabindex', '0');
			$('.cont02 .swiper-slide:first-child a').focus();
		}
		var offset = $('.cont02 .swiper-container').offset();
		$('html, body').animate({
			scrollTop: offset.top - 200
		}, 400);
	});
	$('.cont02 .close').click(function () {
		$('.cont02 .slide').slick(siteSet)
		$('.cont02').removeClass('on');
	});
	$('.cont02 .stop, .cont02 .prev, .cont02 .next').on('click focusin', function () {
		$('.cont02 .slide').slick('slickPause');
	});
	$('.cont02 .play').click(function () {
		$('.cont02 .slide').slick('slickPlay');
	});
	*/
});
