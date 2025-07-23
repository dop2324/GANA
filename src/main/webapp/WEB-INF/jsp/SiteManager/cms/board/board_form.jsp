<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="boardFrm" name="boardFrm" method="post" enctype="multipart/form-data" action="<c:url value="${managerDir }/cms/board/board_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>

<input type="hidden" id="bod_sn"		name="bod_sn"		value="<c:out value="${boardMap.bod_sn }" />" />

<div class="board_wrap">
	<div class="board_write form_style">
		<dl>
			<dt><label for="bod_ttl">제목</label></dt>
			<dd>
				<c:if test="${infoVo.inf_fncUsrGroup == 1}">
					<select id="bgp_sn" name="bgp_sn" title="분류선택">
						<optgroup>
						<option value="">분류선택</option>
						<c:forEach var="vo" items="${infoVo.boardGroupList}" varStatus="status">
						<option value="${vo.bgp_sn }" <c:if test="${vo.bgp_sn == boardMap.bgp_sn}">selected="selected"</c:if> ><c:out value="${vo.bgp_nm }" /></option>
					</c:forEach>	
					</optgroup>
					</select>
				</c:if>
				
				<input type="text" id="bod_ttl" name="bod_ttl" maxlength="128" value="<c:out value="${boardMap.bod_ttl }" escapeXml="false" />" placeholder="게시글 제목" />
				<div>
					<c:if test="${infoVo.inf_fncAdmNotice == 1}">
						<input type="checkbox" id="bod_notice" name="bod_notice" value="1" <c:if test="${boardMap.bod_notice == 1}">checked="checked"</c:if> />
						<label for="bod_notice">공지설정</label>
					</c:if>
					
					<c:if test="${infoVo.inf_pushGroup == 1}">
						<c:choose>
							<c:when test="${boardMap != null }">
								
								<c:set var="bod_pushGroupSttus" value="모바일 App Push 미전송" />
								<c:if test="${boardMap.bod_pushGroup == 1 }"><c:set var="bod_pushGroupSttus" value="모바일 App Push 전송" /></c:if>
								<c:out value="${bod_pushGroupSttus }" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" value="1" name="bod_pushGroup" id="bod_pushGroup" /> <label for="bod_pushGroup">모바일 App Push 보내기</label>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
			</dd>
		<c:if test="${infoVo.inf_fncUsrLock == 1}">
			<dt>공개유무</dt>
			<dd>
				<c:choose>
					<c:when test="${infoVo.inf_fncUsrLockOpt == 0 }"><c:set var="bod_lock" value="0" /></c:when>
					<c:when test="${infoVo.inf_fncUsrLockOpt == 1 }"><c:set var="bod_lock" value="1" /></c:when>
				</c:choose>

				<c:if test="${boardMap != null }"><c:set var="bod_lock" value="${boardMap.bod_lock }" /></c:if>
				<input type="radio" id="bod_lock_1" name="bod_lock" value="0" <c:if test="${bod_lock == 0}">checked="checked"</c:if>/> <label for="bod_lock_1">공개</label>
				<input type="radio" id="bod_lock_0" name="bod_lock" value="1" <c:if test="${bod_lock == 1}">checked="checked"</c:if>/> <label for="bod_lock_0">비공개</label>
			</dd>
		</c:if>
			
		<c:if test="${infoVo.inf_fncAdmTerm == 1 }">
			<dt>기간설정</dt>
			<dd class="grow">
				<input id="bod_startDate" name="bod_startDate" type="text" class="datepicker" value="<c:out value="${boardMap.bod_startDate }" />"  />
				<c:if test="${infoVo.inf_fncAdmTermTy == 1 }">
					<span>~</span>
					<input id="bod_endDate" name="bod_endDate" type="text" class="datepicker" value="<c:out value="${boardMap.bod_endDate }" />"  />
				</c:if>
			</dd>
		</c:if>
		
		<c:if test="${fn:length(infoVo.boardShareList) > 0}">
			<dt>게시물 공유</dt>
			<dd>
			<c:forEach var="vo" items="${infoVo.boardShareList}" varStatus="status">
				<c:set var="shrCheck" value="" />

				<c:forEach var="v" items="${boardMap.boardShareMemberList}" varStatus="status">
					<c:if test="${vo.shr_code == v.shr_code }">
						<c:set var="shrCheck" value="checked='checked'" />
					</c:if>
				</c:forEach>

				<div style="width:100%;"> 
					<input type="checkbox" id="shr_code_<c:out value="${vo.shr_code }" />" name="shr_code" value="<c:out value="${vo.shr_code }" />" <c:out value="${shrCheck }" /> />
					<label for="shr_code_<c:out value="${vo.shr_code }" />"><c:out value="${vo.mnu_path }" /></label>
				</div>
			</c:forEach>
			</dd>
		</c:if>
		
		<c:if test="${infoVo.inf_fncDeptGroup == 1}">
			<dt>부서</dt>
			<dd class="id">
				<c:set var="dept_id" value="" />
				<c:set var="dept_nm" value="" />
				<c:if test="${boardMap != null }">
					<c:set var="dept_id" value="${boardMap.dept_id }" />
					<c:set var="dept_nm" value="${boardMap.dept_nm }" />
				</c:if>
				<input type="hidden" id=dept_id name="dept_id" value="<c:out value="${dept_id }" />" />
				<input type="text" id="dept_nm" name="dept_nm" maxlength="64" value="<c:out value="${dept_nm }" />" />
				<a href="#self" onclick="openDept();" class="btn" >부서선택</a>
			</dd>
		</c:if>
			<dt><label for="con_nm">작성자</label></dt>
			<dd>
				<c:set var="con_nm" value="${USR_NM }" />
				<c:if test="${boardMap != null }"><c:set var="con_nm" value="${boardMap.con_nm }" /></c:if>
				<input type="text" id="con_nm" name="con_nm" maxlength="64" value="<c:out value="${con_nm }" />" />
			</dd>
		
		<%-- 개인정보  --%>
		<c:if test="${infoVo.inf_fieldEml == 1 }">
			<dt><label for="con_eml">이메일</label></dt>
			<dd><input type="text" id="con_eml" name="con_eml" maxlength="128" value="<c:out value="${boardMap.con_eml }" />" placeholder="이메일 주소" /></dd>
		</c:if>
		<c:if test="${infoVo.inf_fieldTelno == 1 }">
			<dt><label for="con_telno">연락처</label></dt>
			<dd><input type="text" id="con_telno" name="con_telno" maxlength="32" value="<c:out value="${boardMap.con_telno }" />" placeholder="연락처" /></dd>
		</c:if>
		<c:if test="${infoVo.inf_fieldAddr == 1 }">
			<dt>주소</dt>
			<dd class="address">
				<input type="text" id="con_zip" name="con_zip" maxlength="5" value="<c:out value="${boardMap.con_zip }" />" placeholder="우편번호" />
				<a href="#self" onclick="execDaumPostcode();" class="btn" >주소찾기</a>
				<input type="text" id="con_addr" name="con_addr" maxlength="128" autocomplete="off" value="<c:out value="${boardMap.con_addr }" />" placeholder="주소" />
			</dd>
		</c:if>
	
		<%-- 사용자 필드 --%>
		<c:if test="${infoVo.inf_fncUsrField == 1}">
		<c:forEach var="bf" items="${infoVo.boardFieldList}" varStatus="status">
			<dt><label for="<c:out value="${bf.bfd_id}" />"><c:out value="${bf.bfd_nm }" /></label></dt>
			<dd>
				<input type="text" id="<c:out value="${bf.bfd_id}" />" name="<c:out value="${bf.bfd_id}" />" maxlength="<c:out value="${bf.bfd_maxLen}" />" value="<c:out value="${boardFieldValue[bf.bfd_id]}" />" style="width:<c:out value="${bf.bfd_width}" />px"  placeholder="<c:out value="${bf.bfd_nm }" />"/>
			</dd>
		</c:forEach>
		</c:if>
	
	
		<c:if test="${infoVo.inf_fncAdmVod == 1 }">
			<dt>YouTube 동영상 URL</dt>
			<dd class="youtube">
				<c:set var="vodCnt" value="1" />
				<c:forEach var="vod" items="${boardMap.boardVodList}" varStatus="status">
					<div class="del">
						<input type="hidden" id="vod_sn" name="vod_sn" value="<c:out value="${vod.vod_sn }" />" />
						<label class="hidden" for="vod_ttl_<c:out value="${vodCnt}" />"><c:out value="${vodCnt}" />번 동영상 제목</label>
						<input type="text" id="vod_ttl_<c:out value="${vodCnt}" />" name="vod_ttl" maxlength="128" value="<c:out value="${vod.vod_ttl }" />" placeholder="동영상 제목" />
						<p>https://www.youtube.com/embed/</p>
						<label class="hidden" for="vod_linkUrl_<c:out value="${vodCnt}" />"><c:out value="${vodCnt}" />번 동영상 코드</label>
						<input type="text" id="vod_linkUrl_<c:out value="${vodCnt}" />" name="vod_linkUrl" value="<c:out value="${vod.vod_linkUrl }" />" />
						<div class="del_check">
							<input type="checkbox" id="deleteVod_<c:out value="${vodCnt}" />" name="deleteVod" value="<c:out value="${vod.vod_sn }" />" /><label for="deleteVod_<c:out value="${vodCnt}" />"><span class="hidden"><c:out value="${vodCnt}" />번 동영상</span>삭제</label>
						</div>
					</div>
				<c:set var="vodCnt" value="${vodCnt+1}" />
				</c:forEach>
				<div>
					<input type="hidden" id="vod_sn" name="vod_sn"  />
					<label class="hidden" for="vod_ttl_<c:out value="${vodCnt}" />"><c:out value="${vodCnt}" />번 동영상 제목</label>
					<input type="text" id="vod_ttl_<c:out value="${vodCnt}" />" name="vod_ttl" maxlength="64" placeholder="동영상 제목" />
					<p>https://www.youtube.com/embed/</p>
					<label class="hidden" for="vod_linkUrl_<c:out value="${vodCnt}" />"><c:out value="${vodCnt}" />번 동영상 코드</label>
					<input type="text" id="vod_linkUrl_<c:out value="${vodCnt}" />" name="vod_linkUrl" maxlength="128" placeholder="동영상 코드" />
				</div>
			</dd>
		</c:if>
	
		<c:if test="${infoVo.inf_skinType == 'link' }">
			<dt><label for="con_linkUrl">링크정보</label></dt>
			<dd>
				<input type="text" id="con_linkUrl" name="con_linkUrl" maxlength="256" autocomplete="off" value="<c:out value="${boardMap.con_linkUrl }" />" placeholder="링크정보" />
			</dd>
		</c:if>
	
			<dt>내용</dt>
			<dd style="width:80%;">
				<c:set var="con_cn" value="${infoVo.inf_defaultCn }" />
				<c:if test="${boardMap != null }"><c:set var="con_cn" value="${boardMap.con_cn }" /></c:if>

				<textarea id="con_cn" name="con_cn" rows="10"><c:out value="${con_cn }" escapeXml="false" /></textarea>
				
				<c:set var="fncUsrEditor" value="${infoVo.inf_fncUsrEditor}" />
				<c:if test="${boardMap.con_html == 1 }"><c:set var="fncUsrEditor" value="1" /></c:if>
				<c:if test="${boardMap.con_html == 0 }"><c:set var="fncUsrEditor" value="0" /></c:if>
				<c:if test="${fncUsrEditor == 1 }">
					<script type="module">
					//editorConfig.js에서 CKEditor 설정 정보 불러오기
					import { editorConfig } from 'editorConfig';
					import { ClassicEditor } from 'ckeditor5';
	
					//editorConfig = 이전 포스팅에서 설정한 CKEditor의 설정입니다.
					ClassicEditor.create(document.querySelector('#con_cn'), editorConfig)
						.then( editor => {
							window.editor = editor;
							console.log('CKEditor 전송 정보 : ' + editor);
						})
						.catch(err => {
							console.log('발생 오류 : '+err);
						});
					</script>
				</c:if>
			</dd>
			<dt><label for="con_pw">비밀번호</label></dt>
			<dd><input type="password" id="con_pw" name="con_pw" maxlength="32" value="<c:out value="${boardMap.con_pw}" />" autocomplete="new-password" placeholder="비밀번호" /></dd>
			<dt><label for="con_regDt">작성일</label></dt>
			<dd>
 				<input type="text" id="con_regDt" name="con_regDt" maxlength="19" value="<fmt:formatDate value="${boardMap.con_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />" placeholder="yyyy-MM-dd HH:mm:ss" />
				<p>예) 2017-12-11 19:59:46</p>
			</dd>
		
		<c:if test="${infoVo.inf_fileCnt > 0 }">
			<dt>
				첨부파일 (<c:out value="${infoVo.inf_fileSizeLmt }" />MB)
			</dt>
			<dd class="file">
				<c:if test="${infoVo.inf_fileExt != '' }">
					<p class="check_txt">첨부파일 확장자(<c:out value="${infoVo.inf_fileExt }" />)만 업로드 가능합니다</p>
				</c:if>
				
				<c:set var="fileCnt" value="0" />
				<c:forEach var="fileVo" items="${boardMap.boardFileList}" varStatus="status">
					<div class="del">
						<input type="hidden" name="fileSn" value="<c:out value="${fileVo.file_sn }" />" />
						<p><input type="radio" id="fileFirstYn<c:out value="${fileVo.file_sn }" />" name="fileFirstYn<c:out value="${boardMap.bod_sn }" />" value="<c:out value="${fileVo.file_sn }" />" <c:if test="${fileVo.file_firstYn == 1 }">checked="checked"</c:if> />
						<label for="fileFirstYn<c:out value="${fileVo.file_sn }" />">대표이미지</label></p>
						<!--p class="file_name"><c:out value="${fileVo.file_srcFileNm }" /></p-->
						<div class="del_check">
							<input type="checkbox" id="deleteFile<c:out value="${fileVo.file_sn }" />" name="deleteFile" value="<c:out value="${fileVo.file_sn }" />" />
							<label for="deleteFile<c:out value="${fileVo.file_sn }" />"><c:out value="${fileVo.file_desc }" />삭제</label>
						</div>
						<label class="hidden" for="fileDesc_<c:out value="${fileCnt}" />"><c:out value="${fileVo.file_desc }" /> 설명</label>
						<input type="text" id="fileDesc_<c:out value="${fileCnt}" />" name="fileDesc" class="inp_txt w300" value="<c:out value="${fileVo.file_desc }" />" />
					</div>
					<c:set var="fileCnt" value="${fileCnt+1 }" />
				</c:forEach>

				<c:forEach var="val" begin="${fileCnt}" end="${infoVo.inf_fileCnt-1}" step="1" >
					<div>
						<p><input type="radio" id="fileFirstYn_<c:out value="${val}" />" name="fileFirstYn" value="<c:out value="${val }" />" /> <label for="fileFirstYn_<c:out value="${val }" />">대표이미지</label></p>
						<label class="hidden" for="fileUpload_<c:out value="${val}" />"><c:out value="${val + 1}" />번 파일 첨부</label>
						<input type="file" id="fileUpload_<c:out value="${val}" />" name="fileUpload_<c:out value="${val }" />" title="attachFile" />
						<label class="hidden" for="fileDescription_<c:out value="${val}" />"><c:out value="${val + 1}" />번 파일 설명</label>
						<input type="text" id="fileDescription_<c:out value="${val}" />" name="fileDescription_<c:out value="${val }" />" placeholder="파일 설명" />
					</div>
				</c:forEach>
			</dd>
		</c:if>
		</dl>
	</div>
</div>

</form>

<div class="board_btn">
	<c:if test="${util:hasPermission(prmVal, 4)}">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	</c:if>
	<c:if test="${boardMap != null && util:hasPermission(prmVal, 32) }">
		<c:set var="deleteLink" value="?${queryString }bod_sn=${boardMap.bod_sn }&cmd=32" />
		<a href="<c:out value="${deleteLink }"/>" class="btn pink">삭제</a>
	</c:if>
	<a href="<c:out value="${listLink }"/>">목록</a>

</div>


<script>
function saveData()
{
<c:if test="${infoVo.inf_fncUsrGroup == 1}">
	if($("#bgp_sn").val() == "") {
		alert("게시물 분류를 선택하여 주십시요");
		$("#bgp_sn").select();
	    return false;
	}
</c:if>
	
	if($("#bod_ttl").val() == "") {
		alert("제목을 입력하여 주십시요");
		$("#bod_ttl").select();
	    return false;
	}
	
	if($("#con_nm").val() == "") {
		alert("작성자명을 입력하여 주십시요");
		$("#con_nm").select();
	    return false;
	}
	
	if($("#con_pw").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#con_pw").select();
	    return false;
	}
	
	$("#boardFrm").submit();
}

function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			//console.log(data);
	
			var extraRoadAddr = ''; // 참고 항목 변수
	
			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
	        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	            extraRoadAddr += data.bname;
	        }
	        // 건물명이 있고, 공동주택일 경우 추가한다.
	        if(data.buildingName !== '' && data.apartment === 'Y'){
	            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	        }
	        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	        if(extraRoadAddr !== ''){
	            extraRoadAddr = ' (' + extraRoadAddr + ')';
	        }
	
	        $("#con_zip").val(data.zonecode);
			$("#con_addr").val(roadAddr);
		}
	}).open({
		popupTitle: '우편번호 검색 팝업' //팝업창 타이틀 설정 (영문,한글,숫자 모두 가능)
	});
}

function openDept()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/deptEmp.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popDeptEmp", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml)
{
	$("#dept_id").val(dept_id);
	$("#dept_nm").val(dept_nm);
	
	if(emp_nm != "") {
		$("#con_nm").val(emp_nm);
	}
}
</script>