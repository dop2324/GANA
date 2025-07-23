<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="c-wrap mgb_40">
    <div class="txt-box">
        <h3 class="con-tit">해당하는 회원구분 선택 후 본인확인을 진행하여 주시기 바랍니다.</h3>
        <ul class="list mgt_10 mgl_10 txt">
            <li><strong>문의전화</strong> 052-120</li>
            <li><strong>운영시간</strong> 월~금 09:00~18:00 (12:00~13:00 점심시간)</li>
        </ul>
        <div class="btn-wrap mgt_20"><a href="<c:url value='/member/page.do' />?mnu_code=m0001" class="krds-btn primary medium">문의게시판 바로가기 <i class="svg-icon ico-more"></i></a></div>
    </div>

    <div class="txt-box sky">
        <h3 class="con-tit">본인확인을 통한 찾기</h3>
        <ul class="list mgt_10 mgl_10 txt">
            <li>인증수단을 선택해 주세요.</li>
            <li>인증수단 선택 후 팝업창이 나타나지 않으면 브라우저의 팝업차단을 해제하시기 바랍니다.</li>
        </ul>
    </div>

    <ul class="krds-structured-list type3 mgt_20 txt">
        <li class="structured-item">
            <div class="card-body">
                <p class="con-tit"><strong>휴대폰 본인확인 서비스</strong></p>
                <p class="t_center">
                    <img src="img/sub/certification_icon.png" alt="휴대폰 본인확인 서비스 아이콘">
                </p>
                <p class="c-txt">
                    본인 명의의 휴대폰으로 본인확인을 할 수 있습니다. 
                    <br class="pc"><br class="pc">
                </p>
                <p class="c-txt">* 안내 1600-1522</p>
            </div>
            <div class="card-btm krds-center">
                <button type="button" onclick="fn_nameCheck();" class="krds-btn medium">인증하기</button>
            </div>
        </li>
        <li class="structured-item">
            <div class="card-body">
                <p class="con-tit"><strong>아이핀(I-PIN)</strong></p>
                <p class="t_center">
                    <img src="img/sub/certification_icon2.png" alt="아이핀(I-PIN) 아이콘">
                </p>
                <p class="c-txt">
                    발급기관과 상관없이 본인이 발급받은 아이핀을 이용하여 본인확인을 할 수 있습니다.
                </p>
                <p class="c-txt">* 안내 1600-1522</p>
            </div>
            <div class="card-btm krds-center">
                <button type="button" onclick="fn_ipinCheck();" class="krds-btn medium">인증하기</button>
            </div>
        </li>
    </ul>
</div>

<script type="text/javascript">
//<![CDATA[
	
	// 휴대폰 본인인증
	function fn_nameCheck(){
		const url = '${pageContext.request.contextPath}/common/component/NICE/checkplus_main.jsp';
		window.open(url, '휴대폰 본인인증', 'width=700, height=460');
	}
	
	// IPIN 본인인증
	function fn_ipinCheck(){
		const url = '${pageContext.request.contextPath}/common/component/IPIN/ipin_main.jsp';
		window.open(url, '아이핀 본인인증', 'width=700, height=460');
	}
	
	// 인증결과 페이지이동
	function doPageMove(){
		location.href = '<c:url value="/member/page.do" />?mnu_code=<c:out value="${mnu_code}" />&funcId=id_result';
	}
	
//]]>
</script>
