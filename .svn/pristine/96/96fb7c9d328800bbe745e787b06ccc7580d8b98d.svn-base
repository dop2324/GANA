<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="join_wrap">
    <c:choose>
        <c:when test="${rtnVal == 0 }">
        <dl class="join_end">
            <dt>재설정 오류</dt>
            <dd>
                <p>비밀번호 재설정 오류가 발생하였습니다</p>
                <p>다시 시도 하거나 관리자에게 문의 바랍니다</p>
            </dd>
        </dl>	    
        <div class="join_btn">
            <a href="./page.do?mnu_code=login">로그인 바로가기</a>
        </div>
        </c:when>
        <c:otherwise>
        <dl class="join_end">
            <dt>재설정 완료</dt>
            <dd>
                <p>비밀번호 재설정되었습니다.</p>
                <p>로그인 해주시기 바랍니다.</p>
            </dd>
        </dl>
        <div class="join_btn">
            <a href="./page.do?mnu_code=login">로그인 바로가기</a>
        </div>	       	
        </c:otherwise>
    </c:choose>
    </dl>
</div>

<script>
//스택 추가
history.pushState(null, null, location.href);

// 뒤로라기 이벤트감지 -> 현재페이지로 이동
window.onpopstate = function() {
	history.go(1);
}

$("title").text("비밀번호 재설정 완료 | "+$("title").text());
</script>