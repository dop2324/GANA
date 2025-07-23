<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="linkFrm" name="linkFrm" method="post" enctype="multipart/form-data" action="<c:url value="${managerDir }/cms/link/link_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="lnk_sn"		name="lnk_sn"		value="<c:out value="${vo.lnk_sn }" />" />

<div class="form_style">
	<dl>
		<dt><label for="site_code2">사이트</label></dt>
		<dd>
			<c:set var="siteList" value="${defaultSiteMap.sitePrmList }" />
			<c:set var="siteCode" value="${site_code }" />
			<c:choose>
				<c:when test="${vo != null }"><c:set var="siteCode" value="${site_code }" /></c:when>
			</c:choose>
			<select title="사이트 선택" name="site_code" id="site_code2">
				<option value="">사이트 선택</option>
				<c:forEach var="siteVo" items="${siteList}" varStatus="status">
					<option value="<c:out value="${siteVo.site_code}" />" <c:if test="${siteVo.site_code == site_code}">selected="selected"</c:if>><c:out value="${siteVo.site_nm }" /></option>
				</c:forEach>			
			</select>
		</dd>
		<dt>링크 구분</dt>
		<dd>
			<c:set var="lgp_sn" value="" />
			<c:forEach var="groupVo" items="${groupList}" varStatus="status">
				<c:if test="${status.index == 0 }">
					<c:set var="lgp_sn" value="${groupVo.lgp_sn }" />
				</c:if>
			</c:forEach>
			
			<c:forEach var="groupVo" items="${groupList}" varStatus="status">
				<input type="radio" id="lgp_sn_<c:out value="${groupVo.lgp_sn }" />" name="lgp_sn" <c:out value="${groupVo.lgp_sn == srchGroup ? 'checked=\"checked\"':'' }" /> value="<c:out value="${groupVo.lgp_sn }" />" /> 
				<label for="lgp_sn_<c:out value="${groupVo.lgp_sn }" />"><c:out value="${groupVo.lgp_nm }" /></label>
			</c:forEach>
		</dd>
		<dt><label for="lnk_nm">링크 이름</label></dt>
		<dd>
			<input type="text" id="lnk_nm" name="lnk_nm" maxlength="128" value="<c:out value="${vo.lnk_nm }" />" placeholder="링크 이름" />
		</dd>
		<dt>링크 타겟</dt>
		<dd>
			<c:set var="lnk_target" value="_blank" />
			<c:if test="${vo != null }"><c:set var="lnk_target" value="${vo.lnk_target }" /></c:if>
			<input id="lnk_target_0" name="lnk_target" class="inp_rad" type="radio" value="_self"	<c:if test="${lnk_target == '_self' }">checked="checked"</c:if> /> 
			<label for="lnk_target_0">현재창 [target='_self']</label>
			<input id="lnk_target_1" name="lnk_target" class="inp_rad" type="radio" value="_blank"	<c:if test="${lnk_target == '_blank' }">checked="checked"</c:if> /> 
			<label for="lnk_target_1">새창 [target='_blank']</label>
		</dd>
		<dt>링크 기간</dt>
		<dd class="grow">
			
			<fmt:formatDate var="varDate" value="${vo.lnk_startDt}" pattern="yyyy-MM-dd"/>
			<input type="text" id="lnk_startDt" name="lnk_startDt" class="datepicker wp30" placeholder="시작 일자" value="<fmt:formatDate value="${vo.lnk_startDt}" pattern="yyyy-MM-dd"/>"  />
			<fmt:formatDate var="lnk_startDtHH" value="${vo.lnk_startDt}" pattern="HH" />
			<select id="lnk_startDtHH" name="lnk_startDtHH" class="wp10">
				<option value="">시간</option>
			<c:forEach var="i" begin="1" end="23">
				<fmt:formatNumber var="no" minIntegerDigits="2" value="${i}" type="number"/>
				<option value="<c:out value="${no }" />" <c:if test="${lnk_startDtHH == no }">selected="selected"</c:if>><c:out value="${no }" /></option>
			</c:forEach>
			</select>
			<span>~</span>
			
			<input type="text" id="lnk_endDt" name="lnk_endDt"  class="datepicker wp30" placeholder="종료 일자" value="<fmt:formatDate value="${vo.lnk_endDt}" pattern="yyyy-MM-dd"/>"  />
			<fmt:formatDate var="lnk_endDtHH" 	value="${vo.lnk_endDt}" pattern="HH" />
			<fmt:formatDate var="lnk_endDtHHmm" value="${vo.lnk_endDt}" pattern="HH:mm" />
			<c:if test="${lnk_endDtHHmm == '23:59' }"><c:set var="lnk_endDtHH" value="" /></c:if>
			
			<select id="lnk_endDtHH" name="lnk_endDtHH" class="wp10">
				<option value="">시간</option>
			<c:forEach var="i" begin="1" end="23">
				<fmt:formatNumber var="no" minIntegerDigits="2" value="${i}" type="number"/>
				<option value="<c:out value="${no }" />" <c:if test="${lnk_endDtHH == no }">selected="selected"</c:if>><c:out value="${no }" /></option>
			</c:forEach>
			</select>
		</dd>
		<dt><label for="lnk_imgFileNm">링크 이미지</label></dt>
		<dd>
			<c:if test="${vo != null && (vo.lnk_imgFileNm != null || vo.lnk_imgFileNm != '') }">
				<input type="text" id="org_imgFileNm" name="org_imgFileNm" value="${vo.lnk_imgFileNm }" style="width:calc(80% - 110rem); margin-right:10rem;"  />
				<input type="checkbox" id="deleteFile" name="deleteFile" value="1"/> <label for="deleteFile">삭제시 체크</label>
			</c:if>
			<input type="file" id="lnk_imgFileNm" name="lnk_imgFileNm" />
		</dd>
		<dt><label for="lnk_linkUrl">링크 URL</label></dt>
		<dd>
			<input type="text" id="lnk_linkUrl" name="lnk_linkUrl" maxlength="500" value="<c:out value="${vo.lnk_linkUrl }" />" placeholder="링크 URL" />
		</dd>
<%--
		<dt>링크 종류</dt>
		<dd>
			<c:set var="lnk_window" value="zone" />
			<c:if test="${vo != null }"><c:set var="lnk_window" value="${vo.lnk_window }" /></c:if>
			<input id="lnk_window_zone" name="lnk_window" class="inp_rad" type="radio" value="zone"		<c:if test="${lnk_window == 'zone' }">checked="checked"</c:if> /> 
			<label for="lnk_window_zone">고정식</label>
			
			<input id="lnk_window_layer" name="lnk_window" class="inp_rad" type="radio" value="layer"	<c:if test="${lnk_window == 'layer' }">checked="checked"</c:if> /> 
			<label for="lnk_window_layer">레이어</label>
			
			<input id="lnk_window_popup" name="lnk_window" class="inp_rad" type="radio" value="popup"	<c:if test="${lnk_window == 'popup' }">checked="checked"</c:if> />
			<label for="lnk_window_popup">팝업</label> 
		</dd>
--%>
		<dt>크기</dt>
		<dd>
			<label for="lnk_width">가로</label> : <input id="lnk_width" name="lnk_width" maxlength="4" placeholder="가로 px" value="<c:out value="${vo.lnk_width }" />" class="wp10" style="margin:0 4rem;"/> px 
			<span style="width:50rem; text-align:center;">/</span>
			<label for="lnk_height">세로</label> : <input id="lnk_height" name="lnk_height" maxlength="4" placeholder="세로 px" value="<c:out value="${vo.lnk_height }" />" class="wp10" style="margin:0 4rem;" /> px
		</dd>
	
		<dt>위치</dt>
		<dd>
			<label for="lnk_top">Top</label> : <input id="lnk_top" name="lnk_top" maxlength="4" placeholder="Top px" value="<c:out value="${vo.lnk_top }" />" class="wp10" style="margin:0 4rem;" /> px <span style="width:50rem; text-align:center;">/</span>
			<label for="lnk_left">Left</label> : <input id="lnk_left" name="lnk_left" maxlength="4" placeholder="Left px" value="<c:out value="${vo.lnk_left }" />" class="wp10" style="margin:0 4rem;" /> px
		</dd>
	
	
		<dt><label for="lnk_sort">정렬순서</label></dt>
		<dd><input id="lnk_sort" name="lnk_sort" maxlength="4" class="wp10" placeholder="정렬순서" value="<c:out value="${vo.lnk_sort }" />" /></dd>
	
	
		<dt><label for="lnk_cn">링크 내용</label></dt>
		<dd>
			<textarea id="lnk_cn" name="lnk_cn" maxlength="2000" style="height:100px" placeholder="링크 내용"><c:out value="${vo.lnk_cn}" escapeXml="false" /></textarea>
		</dd>
	
	
		<dt><label for="lnk_alt">대체 텍스트</label></dt>
		<dd>
			<textarea id="lnk_alt" name="lnk_alt" maxlength="2000" style="height:100px" placeholder="대체 텍스트"><c:out value="${vo.lnk_alt}" /></textarea>
		</dd>
	
	
		<dt><label for="lnk_html">Html</label></dt>
		<dd>
			<textarea id="lnk_html" name="lnk_html" maxlength="2000" style="height:100px" placeholder="Html"><c:out value="${vo.lnk_html}" escapeXml="false" /></textarea>
		</dd>
	
	
		<dt>사용유무</dt>
		<dd>
			<c:set var="lnk_sttus" value="1" />
			<c:if test="${vo != null }"><c:set var="lnk_sttus" value="${vo.lnk_sttus }" /></c:if>
			<input id="lnk_sttus_1" name="lnk_sttus" type="radio" value="1" <c:if test="${lnk_sttus == 1}">checked="checked"</c:if> />
			<label for="lnk_sttus_1">사용</label>
			<input id="lnk_sttus_0" name="lnk_sttus" type="radio" value="0" <c:if test="${lnk_sttus == 0}">checked="checked"</c:if>/>
			<label for="lnk_sttus_0">중지</label>
		</dd>
	
		<c:if test="${vo != null }">
		<dt>수정ID / 수정일시</dt>
		<dd><c:out value="${vo.lnk_mdfcnID }" /> / <fmt:formatDate value="${vo.lnk_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
		</c:if>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	
	<a href="<c:out value="${listLink }"/>">목록</a>
	
	<c:if test="${vo != null }">
		<a href="#self" class="btn pink floatR" onclick="deleteData()">삭제</a>
	</c:if>
</div>

<script>
function saveData()
{
	if ($("#lnk_nm").val() == "") {
        alert("링크명을 입력하세요!");
        $("#lnk_nm").select();
        return false;
    }
	if ($("#lnk_linkUrl").val() == "") {
        alert("링크 URL을 입력하세요!");
        $("#lnk_linkUrl").select();
        return false;
    }
	
	$("#linkFrm").submit();
}

function deleteData()
{
	if(confirm("삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#linkFrm").submit();
	}
}
</script>