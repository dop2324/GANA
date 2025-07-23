<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<a href="" id="search_open">통합검색창 열기</a>
<div id="search_area">
	<div class="search_area_wrap">
        <form name="frmSearch" id="frmSearch" action="./search.do" target="_blank" method="post">
			<label for="kwd">검색어 입력</label>
			<input type="text" id="kwd" name="kwd" placeholder="검색어를 입력하세요.">
			<input type="hidden" id="preKwds" name="preKwds" value="">
			<a href="#self" class="submit" onclick="checkSearchData()" title="새창열림">검색</a>
		</form>
		<div class="search_keyword">
			<a href="">추천검색어1</a>
			<a href="">추천검색어2</a>
			<a href="">추천검색어3</a>
			<a href="">추천검색어4</a>
			<a href="">추천검색어5</a>
			<a href="">추천검색어6</a>
			<a href="">추천검색어7</a>
			<a href="">추천검색어8</a>
		</div>
		<a href="" id="search_close">통합검색창 닫기</a>
	</div>
</div>

<script>
function checkSearchData() {
 	if( $("#qt").val() == "") {
		alert('검색어를 입력하여 주십시요');
		$("#qt").focus();
		return false;
	}
	$("#frmSearch").submit();
}
</script>