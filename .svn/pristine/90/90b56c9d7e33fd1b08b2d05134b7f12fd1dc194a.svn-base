<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=yes">
<meta name="format-detection" content="telephone=no">
<meta name="robots" content="all">

<!-- 공통 CSS -->
<link rel="stylesheet" href="<c:url value='/design/common/css/common.css' />">
<link rel="stylesheet" href="<c:url value='/design/common/css/board.css' />">
<link rel="stylesheet" href="<c:url value='/design/common/css/member.css' />">
<link rel="stylesheet" href="<c:url value='/design/common/css/swiper-4.5.1.css' />">
<link rel="stylesheet" href="<c:url value='/design/common/template/css.css' />">

<!-- 공통 JS -->
<script src="<c:url value='/design/common/js/jquery-3.5.1.min.js' />"></script>
<script src="<c:url value='/design/common/js/jquery-ui.min.js' />"></script>
<script src="<c:url value='/design/common/js/util.js' />"></script>
<script src="<c:url value='/design/common/js/ie11CustomProperties.min.js' />"></script>
<script src="<c:url value='/design/common/js/swiper-4.5.1.js' />"></script>
<script src="<c:url value='/design/common/template/js.js' />"></script>

<!-- 회사별 CSS/JS -->
<link rel="stylesheet" href="<c:url value='/design/company/css/layout.css' />">
<script src="<c:url value='/design/company/js/layout.js' />"></script>

<%-- <link rel="stylesheet" href="/design/_common/css/swiper-bundle.min.css">
<script src="/design/_common/js/jquery-ui.min.js"></script>
<script src="/design/_common/js/swiper-bundle.min.js"></script>
<script src="/design/_company/js/search.js"></script>
<script src="/design/_common/js/util.js"></script>
<script src="/design/common/js/jquery-ui.min.js"></script>
<script src="/design/company/js/slick.js"></script> --%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" crossorigin="anonymous">

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/__ckeditor5__/ckeditor5.css">
<style>
.ck-editor {width:100% !important;} 
.ck-editor__editable_inline {height: 400px;}
.ck-source-editing-area {width: 100%; height: 400px; }
.tbl_st_write .ck-source-editing-area textarea {width:100%; overflow: auto;}
</style>
<script type="importmap">
{
	"imports": {
		"ckeditor5": "/common/__ckeditor5__/ckeditor5.js"
		, "ckeditor5/": "/common/__ckeditor5__/"
		, "editorConfig": "/common/__ckeditor5__/CKEditorConfig.js"
		, "editorConfig/": "/common/__ckeditor5__/"
	}
}
</script>
<%--
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('<c:out value="${kakaoJsKey }" />"'); //★ 수정 할 것
    console.log("isInitialized" +Kakao.isInitialized());
</script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<c:out value="${kakaoJsKey }" />&libraries=services"></script>
 --%>

<!--[if lt IE 9]>
<script src="/design/common/js/html5shiv.js"></script>
   <![endif]-->
<!--[if lte IE 8]>
<script>
alert('IE 8이하 버젼을 사용하고 있습니다. IE9이상으로 Upgrade 하셔야 정상적인 화면을 보실수있습니다.');
</script>
<![endif]-->

<script>
$(function(){
    $( ".datepicker" ).datepicker({
        dateFormat: 'yy-mm-dd'
        , monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
        , monthNamesShort : ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        , dayNamesMin: ['일','월','화','수','목','금','토']
        , showMonthAfterYear: true
        , yearSuffix: "년"
        , changeYear : true
        , changeMonth : true
    });

	$("input:text[numberTime]").on("keyup", function() {
	      $(this).val($(this).val().replace(/[^0-9-:]/g,""));
	});

	$("input:text[numberOnly]").on("keyup", function() {
	      $(this).val($(this).val().replace(/[^0-9]/g,""));
	});
});
</script>
