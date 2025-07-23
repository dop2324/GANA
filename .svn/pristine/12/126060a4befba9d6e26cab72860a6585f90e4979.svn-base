<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="join_wrap">
    <dl class="join_end">
        <dt>가입완료</dt>
        <dd><strong><c:out value="${vo.mem_nm }" /></strong>님 회원 가입되었습니다.</dd>
        <dd>회원 아이디 : <strong><c:out value="${vo.mem_id }" /></strong></dd>
    </dl>
     <div class="join_btn">
        <a href="?mnu_code=login">로그인 바로가기</a>
    </div>
</div>

<script>
//스택 추가
history.pushState(null, null, location.href);

// 뒤로라기 이벤트감지 -> 현재페이지로 이동
window.onpopstate = function() {
	history.go(1);
}

$("title").text("가입완료 | "+$("title").text());
</script>