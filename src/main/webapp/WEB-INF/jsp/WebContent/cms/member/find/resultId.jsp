<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="join_wrap">
	<dl class="join_end">
		<c:choose>
			<c:when test="${memberVo == null }">
				<dt>아이디 찾기</dt>
				<dd>가입정보가 없습니다.</dd>
			</c:when>
			<c:otherwise>
				<dt>아이디 찾기</dt>
				<dd>회원님의 아이디는 아래와 같습니다.</dd>
				<dd>회원 아이디 : <strong><c:out value="${memberVo.mem_id }" /></strong></dd>
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

$("title").text("아이디 찾기 | "+$("title").text());
</script>