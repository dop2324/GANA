<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="commentFrm" name="commentFrm" method="post" action="<c:out value="${publicDir }/cms/board/comment/comment_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="comm_site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="comm_mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />

<input type="hidden" id="comm_returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="comm_queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="commentCmd"	name="cmd" 			/>

<input type="hidden" id="bod_sn"		name="bod_sn"		value="<c:out value="${bod_sn }" />" />
<input type="hidden" id="con_sn"		name="con_sn"		/>

<div class="board_comment">
	<div class="title"><span class="sp_icon">댓글의견</span><b class="num">(<fmt:formatNumber value="${fn:length(commentList) }" groupingUsed="true"/>)</b></div>

	<c:forEach var="comment" items="${commentList}" varStatus="status">
		<div class="comment_view">
			<div class="comment_info">
				<div>
					<p class="name"><c:out value="${comment.con_nm }" /></p>
					<p class="date"><fmt:formatDate value="${comment.con_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
					<%--<p class="date"><c:out value="${comment.con_regip }" /></p>--%>
				</div>
				<div class="btn1">
					<c:if test="${infoVo.inf_fncUsrVote == 1 }">
						<a href="#self" class="good" onclick="commVote(<c:out value="${comment.con_sn}" />, 1)">
						추천<span id="commYesCnt<c:out value="${comment.con_sn}" />">(<fmt:formatNumber value="${comment.con_yesCnt }" groupingUsed="true"/>)</span>
						</a>
						<a href="#self" class="bad" onclick="commVote(<c:out value="${comment.con_sn}" />, 0)">
							신고<span id="commNoCnt<c:out value="${comment.con_sn}" />">(<fmt:formatNumber value="${comment.con_noCnt }" groupingUsed="true"/>)</span>
						</a>
					</c:if>
				</div>
				<div class="btn2">
					<a href="#self" class="modi" onclick="comment(${comment.con_sn}, 16)">수정</a>
					<a href="#self" class="del" onclick="comment(${comment.con_sn}, 32)">삭제</a>
				</div>
			</div>
			<div class="comment_txt">
				<div id="comment<c:out value='${comment.con_sn}'/>" class="commentContent"><c:out value="${util:htmlEncode(comment.con_cn)}" escapeXml="false" /></div>
				<div id="comment_content${comment.con_sn}" class="updateComment" style="display:none;"></div>
			</div>
		</div>
	</c:forEach>

<%-- 삭제글 신규 댓글 방지 --%>
<c:if test="${boardMap.bod_delSttus == 0 }">
	<!--댓글-->
	<div class="comment_write">
		<div class="comment_member">
			<label for="con_nm">이름</label>
			<input type="text" id="con_nm" name="con_nm" maxlength="32" value="<c:out value="${USR_NM }" />" />
			<label for="con_pw">비밀번호</label><input type="password" id="con_pw" name="con_pw" maxlength="32" autocomplete="new-password" value="" />
		</div>
		<div class="comment_txt2 ">
			<c:if test="${infoVo.inf_fncUsrCmntsCharLmt > 0}">
			<div>
				<div>댓글 <strong><c:out value="${infoVo.inf_fncUsrCmntsCharLmt}"/></strong> 글자 이내로 작성하여 주십시요</div>
				<div>(<span id="commentWordCnt">0</span> / <c:out value="${infoVo.inf_fncUsrCmntsCharLmt}" />)</div>
			</div>
			</c:if>
			<textarea id="con_cn" name="con_cn" <c:if test="${infoVo.inf_fncUsrCmntsCharLmt > 0}">maxlength="<c:out value="${infoVo.inf_fncUsrCmntsCharLmt}" />"</c:if>></textarea>
			<a href="#self" onclick="saveData()">저장</a>
		</div>
	</div>
</c:if>

</div>

</form>

<script>
var commentWordLimit = <c:out value="${infoVo.inf_fncUsrCmntsCharLmt}" />;

function saveData() {
	if($("#con_nm").val() == "") {
		alert("이름을 입력하여 주십시요");
		$("#con_nm").select();
	    return false;
	}

	if($("#con_pw").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#con_pw").select();
	    return false;
	}

	if($("#con_cn").val() == "") {
		alert("내용을 입력하여 입력하여 주십시요");
		$("#con_cn").select();
	    return false;
	}

	if(commentWordLimit > 0  && $("#con_cn").length > commentWordLimit) {
		alert("댓글 글자수 제한 입니다.\n"+commentWordLimit+"를 초과할 수 없습니다.");
		con_content.select();
	    return false;
	}

	$("#commentCmd").val(4);
	$("#commentFrm").submit();
}

function comment(con_sn, cmd) {
	//비밀번호확인
	chk_password = ""
						+ "<input type=\"password\" id=\"con_password\" name=\"update_pw\" maxlength=\"32\" autocomplete=\"new-password\" placeholder=\"비밀번호를 입력하세요\" /> "
						+ "<div>"
						+ "<a href=\"#self\" onclick=\"checkPassword("+con_sn+", "+cmd+")\">확인</a>"
						+ "<a href=\"#self\"onclick=\"updateCancel("+con_sn+")\">취소</a>"
						+ "</div>";
	$(".commentContent").show();
	$(".updateComment").hide();
	$(".updateComment").html(null);

	$("#comment"+con_sn).hide();
	$("#comment_content"+con_sn).show();
	$("#comment_content"+con_sn).html(chk_password);
}

function updateCancel(con_sn) {
	$(".commentContent").show();
	$(".updateComment").hide();
	$(".updateComment").html(null);
}

function updateData(con_sn) {
	$("#con_sn").val(con_sn);
	$("#commentCmd").val(16);
	$("#commentFrm").submit();
}

function deleteData(con_sn) {
	if(confirm("삭제 하시겠습니까?")) {
		$("#con_sn").val(con_sn);
		$("#commentCmd").val(32);
		$("#commentFrm").submit();
	}else{
		updateCancel(con_sn);
	}
}

function checkPassword(con_sn, cmd) {

	if($("#con_password").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#con_password").select();
	    return false;
	}

	$.ajax({
		type:"get"
		, url:"<c:out value="${publicDir }/cms/board/comment/check_password.do" />"
		, dataType:"json"
		, data: {
			mnu_code : $("#mnu_code").val()
			, con_sn : con_sn
			, con_pw : $("#con_password").val()
		}
		, success:function(data) {
			if(data.result != 0) {
				if(cmd == 16) {
					updateContent = "<input type=\"password\" name=\"update_pw\" maxlength=\"32\" class=\"inp_txt\" value=\""+$("#con_password").val()+"\" />"
									+ "<textarea id=\"update_content\" name=\"update_cn\">"+data.con_cn+"</textarea> "
									+ "<div><a href=\"#self\" onclick=\"updateData("+con_sn+")\">의견쓰기</a>"
									+ "<a href=\"#self\" onclick=\"updateCancel("+con_sn+")\">취소</a></div>";

					$(".updateComment").html(null);
					$("#comment_content"+con_sn).html(updateContent);
				}else{
					deleteData(con_sn);
				}
			}else{
				alert("비밀번호가 맞지않습니다.");
				comment(con_sn, cmd);
			}

		}, error:function(jqXHR, textStatus, errorThrown){
			console.log(textStatus+"\n"+errorThrown);
		}
	});
}

function commVote(con_sn, vote_ty)
{
	var title = vote_ty == 1 ? "추천" : "신고";

	if (confirm(title + " 하시겠습니까?")) {
		$.ajax({
			type : "post"
			, url : "<c:out value="${publicDir }/cms/board/vote/vote_process.do" />"
			, dataType : "json"
			, data : {
				mnu_code : $("#mnu_code").val()
				, con_sn : con_sn
				, vote_ty : vote_ty
			}, success : function(data, status){


				if(data.result != 1) {
					alert(data.message);
				}else{

					$("#commYesCnt"+con_sn).text(data.voteYesCnt.toLocaleString());
					$("#commNoCnt"+con_sn).text(data.voteNoCnt.toLocaleString());

					if(data.voteCnt > 0) alert("변경 되었습니다.");
				}
			}, error : function(request, status, opt){
				alert("서비스에 실패하였습니다. Error Code ["+status+"]");
			}
		});
	}
}

$(function(){
	$("#con_cn").on("keydown", function () {
		var inputLength = $(this).val().length;
		var remain = commentWordLimit - inputLength;

		$("#commentWordCnt").html(inputLength);

		if (remain >= 0) {
			$("#commentWordCnt").css('color','#0054c7');
		} else {
			$("#commentWordCnt").css('color','#c90000')
		}
	});
});
</script>
