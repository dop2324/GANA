<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="c-wrap mgb_40">
    <div class="txt-box txt">
        <ul class="list">
            <li>본인확인 결과와 일치하는 아이디는 아래와 같습니다. </li>
            <li>찾으신 아이디는 개인정보보호를 위하여 일부만 공개됩니다.</li>
            <li><strong>[비밀번호 재설정]</strong>을 선택하면 아이디 전체 확인과 비밀번호 재설정이 가능합니다.</li>
        </ul>

        <div class="id-box mgt_20">
            <p>아이디 : <strong><c:out value="${membId }" /></strong></p>
            <p class="mgt_5">가입일 : <c:out value="${joinDt }" /></p>
        </div>

        <div class="btn-wrap mgt_20 t_center">
			<a href="<c:url value='/member/page.do' />?mnu_code=<c:out value='${mnu_code }' />&funcId=pswd_form" class="krds-btn small">비밀번호 재설정</a>
			<a href="<c:url value='/member/page.do' />?mnu_code=<c:out value='${mnu_code }' />" class="krds-btn secondary small">처음으로</a>
        </div>
    </div>

</div>
