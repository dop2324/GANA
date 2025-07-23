<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<c:set var="actionUrl" value="${actionMenuLink }" />
<c:if test="${cmd == 32 }"><c:set var="actionUrl" value="${managerDir }/cms/board/board_process.do" /></c:if>

<form id="boardFrm" name="boardFrm" method="post" action="<c:url value="${actionUrl }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="bod_sn"		name="bod_sn"		value="<c:out value="${boardMap.bod_sn }" />" />

<div class="board_wrap">
	<div class="board_pw" >
		<p class="title">
			<c:choose>
				<c:when test="${cmd == 2 }">게시글 보기</c:when>
				<c:when test="${cmd == 16 }">게시글 수정</c:when>
				<c:when test="${cmd == 32 }">게시글 삭제</c:when>
			</c:choose>
		</p>
		<div>
			<p>글작성 시 입력하셨던 비밀번호를 입력하세요.</p>
			<c:if test="${cmd == 32 }">
				<div class="del_info_wrap">
					<div>
						<c:choose>
							<c:when test="${boardMap.bod_delSttus == 0 }">
								<input type="radio" id="deleteOpt_1" name="deleteOpt" value="1" checked="checked" /> <label for="deleteOpt_1" class="b">삭제표시</label> 
							</c:when>
							<c:when test="${boardMap.bod_delSttus == 1 }">
								<input type="radio" id="deleteOpt_0" name="deleteOpt" value="0" checked="checked" /> <label for="deleteOpt0" class="b">삭제취소</label> 
							</c:when>
						</c:choose>
						<input type="radio" id="deleteOpt_9" name="deleteOpt" value="9" /> <label for="deleteOpt_9">DB삭제 (복구불가)</label> 
					</div>
					<c:if test="${boardMap.bod_delSttus == 0 }">
						<div>
							<input type="text" name="bod_delReason" id="bod_delReason" placeholder="삭제 사유">
						</div>
					</c:if>
				</div>
			</c:if>
			<input type="password" name="con_pw" id="con_pw" value="<c:out value="${con_pw }" />" autocomplete="new-password" placeholder="비밀번호">
		</div>
		<div class="board_btn">
			<c:choose>
				<c:when test="${cmd == 32 }">
					<a href="#self" onclick="deleteConfirm()">확인</a>
				</c:when>
				<c:otherwise>
					<a href="#self" onclick="pwConfirm()">확인</a>
				</c:otherwise>
			</c:choose>

			<c:set var="cancelLink" value="?${queryString }" />
			<c:if test="${cmd != 2+256 }"><c:set var="cancelLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=2" /></c:if>		
			<a href="<c:out value="${cancelLink }" />" class="btn pink">취소</a>
		</div>
	</div>
</div>
</form>

<script>
$(function(){
	$("input[name='deleteOpt']").change(function(){
		var deleteOpt = $("input[name='deleteOpt']:checked").val();
		if(deleteOpt == 9) {
			$("#bod_delReason").hide();
		}else{
			$("#bod_delReason").show();
		}
	});
});

function pwConfirm()
{
	$("#boardFrm").submit();
}

function deleteConfirm()
{
	var depeteOpt = $("input[name='deleteOpt']:checked").val();
	
	if(depeteOpt == 9) {
		if(confirm("DB삭제는 복구 불가 합니다.\n게시글을 삭제 하시겠습니까?")) {
			$("#boardFrm").submit();
		}
	}else{
		$("#boardFrm").submit();
	}
	
}
</script>
