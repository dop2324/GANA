<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="join_wrap">
    <c:choose>
    	<c:when test="${memberVo == null }">
		<dl class="join_end">
     		<dt>비밀번호 찾기</dt>
		    <dd>가입정보가 없습니다.</dd>
		</dl>
     	</c:when>
	 	<c:otherwise>
			<div class="join_form form_style">
				<p class="title">비밀번호 재설정</p>
				<!--p class="check_txt">새로 사용할 비밀번호를 입력하세요.</p-->
		
				<form id="findFrm" name="findFrm" method="post" action="<c:out value="${actionMenuLink }" />">
				<double-submit:preventer/>
				<input type="hidden" id="site_code" name="site_code"	value="<c:out value="${siteVo.site_code }" />" />
				<input type="hidden" id="mnu_code" 	name="mnu_code" 	value="<c:out value="${mnu_code }" />" />
				<input type="hidden" id="step" 		name="step" 		value="<c:out value="${step }" />" />
					<fieldset>
					<legend class="hidden">비밀번호 정보</legend>
					<dl>
						<dt><label for="id"><span class="red" title="필수입력">*</span>아이디</label></dt>
						<dd>
							<strong><c:out value="${memberVo.mem_id }" /></strong>
							<input type="hidden" id="mem_id" name="mem_id" maxlength="32" value="${memberVo.mem_id }" />
						</dd>
						<dt><label for="change_pw"><span class="red" title="필수입력">*</span>새 비밀번호</label></dt>
						<dd><input type="password" id="mem_pw" name="mem_pw" maxlength="20" />
							<p class="check_txt">영문(대소문자), 숫자,특수문자(!@#$%^&*)를 조합하여 8자부터 20자까지 가능합니다.</p>
						</dd>
						<dt><label for="chk_pw"><span class="red" title="필수입력">*</span>새 비밀번호 확인</label></dt>
						<dd><input type="password" id="chk_pw" name="chk_pw" maxlength="20"></dd>
					</dl>
					</fieldset>
				</form>
			</div>
			<div class="join_btn">
				<a href="#self" onclick="changePW()">확인</a>
				<a href="#self" onclick="reset()">다시입력</a>
			</div>
	 	</c:otherwise>
	</c:choose>
	
</div>

<script>
//스택 추가
history.pushState(null, null, location.href);

// 뒤로라기 이벤트감지 -> 현재페이지로 이동
window.onpopstate = function() {
	history.go(1);
}

$("title").text("아이디 찾기 | "+$("title").text());

function changePW()
{
	if($("#mem_pw").val() == "") {
		alert("새 비밀번호를 입력하세요!");
		$("#mem_pw").select();
		return false;
	}
	if($("#mem_pw").val() != $("#chk_pw").val()) {
		alert("새 비밀번호가 일치하지않습니다.");
		$("#chk_pw").select();
		return false;
	}	
	
	$("#findFrm").submit();
}

function reset()
{
	$("#mem_pw").val("");
	$("#chk_pw").val("");
}
</script>