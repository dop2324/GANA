<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="boardFrm" name="boardFrm" method="post" action="<c:out value="${actionMenuLink }" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="bod_uid"		name="bod_sn"		value="<c:out value="${boardMap.bod_sn }" />" />
<input type="hidden" id="con_uid"		name="con_sn"		/>
<input type="hidden" id="vote_type"		name="vote_ty"		/>
</form>

<div class="board_wrap">
	<div class="board_view">
		<div class="title">
			<h4>
				<c:if test="${infoVo.inf_fncUsrGroup == 1 && fn:length(infoVo.boardGroupList) > 0}">
					(<c:out value="${boardMap.bgp_nm }"/>)
				</c:if>
				<c:out value="${boardMap.bod_ttl }" />
			</h4>
			<div class="data">
				<dl>
					<dt>작성자</dt>
					<dd>
						<c:choose>
							<c:when test="${boardMap.bod_lock == 1 && !hasAdm}"><c:out value="${fn:substring(boardMap.con_nm, 0, 1) }XX"/></c:when>
							<c:otherwise><c:out value="${boardMap.con_nm}"/></c:otherwise>
						</c:choose>
					</dd>
				</dl>
				<dl>
					<dt>등록일</dt>
					<dd><fmt:formatDate value="${boardMap.con_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
				</dl>
				<dl>
					<dt>조회</dt>
					<dd><fmt:formatNumber value="${boardMap.bod_readCnt}" groupingUsed="true"/></dd>
				</dl>
			</div>

		<%-- 추가필드 --%>
		<c:forEach var="bfd" items="${boardMap.boardFieldValueList}" varStatus="status">
			<div class="data">
				<dl>
					<dt><c:out value="${bfd.bfd_nm }" /></dt>
					<dd><c:out value="${bfd.bfd_val }" /></dd>
				</dl>
			</div>
		</c:forEach>

		<%-- 개인정보
		<c:if test="${infoVo.inf_fieldAddr == 1 && boardMap.con_addr != ''}">
			<div class="data">
				<dl>
					<dt>주소</dt>
					<dd>(<c:out value="${boardMap.con_zip }" />) <c:out value="${boardMap.con_addr }" /></dd>
				</dl>
			</div>
		</c:if>
		--%>

		<%-- 첨부파일 --%>
		<c:if test="${fn:length(boardMap.boardFileList) > 0 }">
			<div class="file">
			<c:forEach var="file" items="${boardMap.boardFileList}" varStatus="f">
				<div class="file">
					<a href="<c:url value="/board_download.do?mnu_code=${boardMap.mnu_code }&bod_sn=${boardMap.bod_sn }&file_sn=${file.file_sn }" />" title="<c:out value="${file.file_desc }" />"> <c:out value="${file.file_srcFileNm}"/></a>
					<c:if test="${SynapUseYn == 'Y' }">
						<a href="<c:out value="/synap/board_fileViewer.do?file_sn=${file.file_sn }" />" class="review" target="_blank" title="새창">미리보기</a>
					</c:if>
				</div>
			</c:forEach>
			</div>
		</c:if>
		</div>

		<div class="cont">
			<%-- 이미지 --%>
			<c:if test="${infoVo.inf_fncUseThumb == 1 }">
				<c:forEach var="file" items="${boardMap.boardFileList}" varStatus="status">
					<c:if test="${file.file_ty == 1 }">
						<c:set var="imgFileNm" value="${file.file_phyFileNm }" />
						<c:if test="${file.file_thumbFileNm != '' }"><c:set var="imgFileNm" value="${file.file_thumbFileNm }" /></c:if>
						<c:out value="${util:getImgTag('board', imgFileNm, thumbWidth, file.file_desc) }" escapeXml="false" />
					</c:if>
				</c:forEach>
			</c:if>

			<%-- 동영상 --%>
			<c:if test="${infoVo.inf_fncAdmVod == 1 }">
				<%-- YouTube --%>
				<c:forEach var="vod" items="${boardMap.boardVodList}" varStatus="status">
					<div><c:out value="${vod.vod_ttl }" /></div>
					<iframe style="max-width:960px;" src="https://www.youtube.com/embed/${vod.vod_linkUrl }" title="<c:out value="${vod.vod_ttl }" />"
						frameborder="0"
						allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture web-share"
						referrerpolicy="strict-origin-when-cross-origin"
						allowfullscreen>
						</iframe>
				</c:forEach>

				<%-- 업로드 MP4 --%>
				<c:forEach var="file" items="${boardMap.boardFileList}" varStatus="status">
					<c:if test="${util:isMediaFile(util:getFileFullName(file.file_phyFileNm))}">
						<video style="max-width:960px;" poster="<c:out value="/upload/board/${file.file_thumbFileNm }"/>" controls>
							<c:set var="mine_ext" value="" />
							<c:forTokens var="token" items="${file.file_phyFileNm}" delims="." varStatus="status">
								<c:if test="${status.last}"><c:set var="mine_ext" value="${token }" /></c:if>
							</c:forTokens>

							<source src="<c:out value="/upload/board/${file.file_phyFileNm }"/>">
						</video>
					</c:if>
				</c:forEach>
			</c:if>

			<div id="bbs_content" class="board_content">
				<c:choose>
					<c:when test="${boardMap.con_html == 1}">
						<c:out value="${boardMap.con_cn }" escapeXml="false" />
					</c:when>
					<c:otherwise>
						<c:out value="${util:htmlEncode(boardMap.con_cn)}" escapeXml="false" />
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="${infoVo.inf_fncUsrVote == 1 }">
				<div class="count">
					<a href="#self" class="good" onclick="vote(<c:out value="${boardMap.con_sn}" />, 1)">
						추천 <span id="conYesCnt"><fmt:formatNumber value="${boardMap.con_yesCnt }" groupingUsed="true"/></span>
					</a>
					<a href="#self" class="bad" onclick="vote(<c:out value="${boardMap.con_sn}" />, 0)">
						신고 <span id="conNoCnt"><fmt:formatNumber value="${boardMap.con_noCnt }" groupingUsed="true"/></span>
					</a>
				</div>
			</c:if>

		</div>

		<%--이전글다음글 --%>
		<div class="view_control">
			<c:if test="${preNxtMap.prev_sn != null }">
				<a href="<c:out value="?${queryString}bod_sn=${preNxtMap.prev_sn}&pageNo=${pageNo}&cmd=2" />">
				<strong>이전글</strong><span><c:out value="${preNxtMap.prev_ttl }" /></span></a>
			</c:if>
			<c:if test="${preNxtMap.next_sn != null }">
				<a href="<c:out value="?${queryString}bod_sn=${preNxtMap.next_sn}&pageNo=${pageNo}&cmd=2" />">
				<strong>다음글</strong><span><c:out value="${preNxtMap.next_ttl }" /></span></a>
			</c:if>
		</div>

		<%-- 부서 답변 --%>
		<c:if test="${infoVo.inf_skinType == 'reply' }">
			<c:import url="${publicDir }/cms/board/dept/dept_viewList.do" >
				<c:param name="infoVo" 			value="${infoVo}" />
				<c:param name="queryString" 	value="${queryString}" />
				<c:param name="hasAdm" 			value="${hasAdm}" />
			</c:import>
		</c:if>

		<%-- 댓글 --%>
		<c:if test="${infoVo.inf_fncUsrCmnts == 1 && boardMap.bod_notice == 0 }">
			<c:import url="${publicDir }/cms/board/comment/board_comment.do" >
				<c:param name="infoVo" 			value="${infoVo}" />
				<c:param name="queryString" 	value="${queryString}" />
				<c:param name="hasAdm" 			value="${hasAdm}" />
			</c:import>
		</c:if>
	</div>


	<div class="board_btn">
		<c:if test="${boardMap.bod_notice == 0}">
			<c:if test="${util:hasPermission(prmVal, 8) }">
				<c:set var="replyLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=8" />
				<a href="<c:out value="${replyLink }"/>">답변</a>	</c:if>
		</c:if>

		<c:if test="${util:hasPermission(prmVal, 16) }">
			<c:set var="modifyLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=16" />
			<a href="<c:out value="${modifyLink }"/>">수정</a> </c:if>
		<c:if test="${util:hasPermission(prmVal, 32) }">
			<c:set var="deleteLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=32" />
			<a href="<c:out value="${deleteLink }"/>">삭제</a> </c:if>


		<a href="<c:out value="${listLink }"/>" id="btnList">목록</a>
	</div>
</div>

<script>
$(function(){
	$("title").text("(글보기)<c:out value="${boardMap.bod_ttl }" /> | "+$("title").text());
});
function vote(con_sn, vote_ty)
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
			}
			, success : function(data, status){
				if(data.result != 1) {
					alert(data.message);
				}else{
					$("#conYesCnt").text(data.voteYesCnt.toLocaleString());
					$("#conNoCnt").text(data.voteNoCnt.toLocaleString());
					if(data.voteCnt > 0) alert("변경 되었습니다.");
				}
			}
			, error : function(request, status, opt){
				alert("서비스에 실패하였습니다. Error Code ["+status+"]");
			}
		});
	}
}
</script>
