<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
var globalConfigJs = {
	contextPath: '${pageContext.request.contextPath}'
};
</script>
<meta charset="<fmt:message bundle="${globalsConfig}" key="site.html.meta.charset" />">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=yes">

<link rel="stylesheet" href="${pageContext.request.contextPath}/design/common/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/design/common/css/board.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/design/common/css/manager.css">

<script src="${pageContext.request.contextPath}/Manager/js/dtree.js"></script>
<script src="${pageContext.request.contextPath}/Manager/js/Framework.js"></script>
<script src="${pageContext.request.contextPath}/Manager/js/manager.js"></script>
<script src="${pageContext.request.contextPath}/design/common/js/util.js"></script>

<script src="${pageContext.request.contextPath}/design/common/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/design/common/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/__ckeditor5__/ckeditor5.css">
<style>
.ck.ck-editor{max-width: 90%;}
.ck-editor__editable_inline {height: 400px;}
.ck-source-editing-area {width: 100%; height: 400px; }
.tbl_st_write .ck-source-editing-area textarea {width:100%; overflow: auto;}
</style>
<script type="importmap">
{
  "imports": {
    "ckeditor5": "${pageContext.request.contextPath}/common/__ckeditor5__/ckeditor5.js",
    "ckeditor5/": "${pageContext.request.contextPath}/common/__ckeditor5__/",
    "editorConfig": "${pageContext.request.contextPath}/common/__ckeditor5__/CKEditorConfig.js",
    "editorConfig/": "${pageContext.request.contextPath}/common/__ckeditor5__/"
  }
}
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/design/common/js/kakaoMap.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<c:out value="${kakaoJsKey}" />&libraries=services"></script>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
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
	
	function datepickerYM()
	{
		$( ".datepickerYM" ).datepicker({
			dateFormat: 'yy-mm' 
			, monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
			, monthNamesShort : ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
			, dayNamesMin: ['일','월','화','수','목','금','토']
			, showMonthAfterYear: true
			, yearSuffix: "년"
			, changeYear : true
			, changeMonth : true
		});	
	}
	
	$("input:text[numberTime]").on("keyup", function() {
	      $(this).val($(this).val().replace(/[^0-9-:]/g,""));
	});
	
	$("input:text[numberOnly]").on("keyup", function() {
	      $(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
	$(".numberOnly").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
	$(".engNumOnly").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9a-zA-Z]/g,""));
	});
	$(".emailOnly").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9a-zA-Z@.]/g,"").toLowerCase());
	});	
});
</script>