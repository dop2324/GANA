<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:set var="actionUrl" value="${actionMenuLink }" />
<c:if test="${cmd == 32 }"><c:set var="actionUrl" value="${publicDir }/cms/board/board_process.do" /></c:if>

<form id="boardFrm" name="boardFrm" method="post" action="<c:out value="${actionUrl }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="bod_sn"		name="bod_sn"		value="<c:out value="${boardMap.bod_sn }" />" />

<fieldset>
<div class="board_wrap">
	<div class="board_pw" >
		<p class="title">
			<c:choose>
				<c:when test="${cmd == 2 || cmd == 258 }">게시글 보기</c:when>
				<c:when test="${cmd == 16 || cmd == 272}">게시글 수정</c:when>
				<c:when test="${cmd == 32 }">게시글 삭제</c:when>
			</c:choose>
		</p>
		<div>
			<p>글작성 시 입력하셨던 비밀번호를 입력하세요.</p>
			<input name="con_pw" id="con_pw" type="password" class="inp_txt wp50" maxlength="64" value="<c:out value="${con_pw }" />" autocomplete="new-password" placeholder="비밀번호">
		</div>
	</div>
	</fieldset>

	</form>

	<div class="board_btn">
		<c:choose>
			<c:when test="${cmd == 32 }">
				<a href="#self" class="bt2 mod" onclick="deleteConfirm()">확인</a>
			</c:when>
			<c:otherwise>
				<a href="#self" class="bt2 mod" onclick="pwConfirm()">확인</a>
			</c:otherwise>
		</c:choose>
		
		<c:set var="cancelLink" value="?${queryString }" />
		<c:if test="${cmd != 2+256 }"><c:set var="cancelLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=2" /></c:if>		
		<a href="<c:out value="${cancelLink }" />" class="bt1 can">취소</a>
	</div>
</div>

<script>
$(function(){
	<c:choose>
		<c:when test="${cmd == 2 || cmd == 258 }">
			$("title").text("(글보기)비밀번호 입력 | "+$("title").text());
		</c:when>
		<c:when test="${cmd == 16 || cmd == 272}">
			$("title").text("(글수정)비밀번호 입력 | "+$("title").text());
		</c:when>
		<c:when test="${cmd == 32 }">
			$("title").text("(글삭제)비밀번호 입력 | "+$("title").text());
		</c:when>
	</c:choose>
	
});

function pwConfirm()
{
	$("#boardFrm").submit();
}

function deleteConfirm()
{
	if(confirm("게시글을 삭제 하시겠습니까? ")) {
		$("#boardFrm").submit();
	}	
}
</script>

