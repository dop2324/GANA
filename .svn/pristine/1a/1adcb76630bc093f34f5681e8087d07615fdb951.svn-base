<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="infoFrm" name="infoFrm" method="post" action="<c:url value="${managerDir }/cms/board/info/info_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code" 	name="site_code" 	value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"	value="<c:out value="${parm_mnuCode }" />" />

<c:if test="${infoVo  == null }">
	<div class="red taC bold">게시판 설정이 없습니다.</div>
</c:if>
<div class="form_style type3">
	<dl>
		
			<dt>이름</dt>
			<dd class="short"><c:out value="${infoVo.mnu_nm}" /> </dd>
			<dt><label for="inf_fncAllBoard">전체게시판 (관리)</label></dt>
			<dd>
				<c:set var="inf_fncAllBoard" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAllBoard" value="${infoVo.inf_fncAllBoard }" /></c:if>
				<input type="checkbox" name="inf_fncAllBoard" id="inf_fncAllBoard" value="1" <c:if test="${inf_fncAllBoard == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrRss">RSS 제공</label></dt>
			<dd>
				<c:set var="inf_fncUsrRss" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrRss" value="${infoVo.inf_fncUsrRss }" /></c:if>
				<input type="checkbox" name="inf_fncUsrRss" id="inf_fncUsrRss" value="1" <c:if test="${inf_fncUsrRss == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_pageSize">목록 수</label></dt>
			<dd>
				<c:set var="inf_pageSize" value="10" />
				<c:if test="${infoVo != null }"><c:set var="inf_pageSize" value="${infoVo.inf_pageSize }" /></c:if>
				<select title="목록갯수 선택" name="inf_pageSize" id="inf_pageSize">
				<c:forEach begin="1" end="50" step="1" var="i">
					<option value="${i}" <c:if test="${inf_pageSize == i}">selected="selected"</c:if>>${i}</option>
				</c:forEach>			
				</select>
			</dd>
			<dt><label for="inf_fileCnt">첨부 수</label></dt>
			<dd>
				<c:set var="inf_fileCnt" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fileCnt" value="${infoVo.inf_fileCnt }" /></c:if>
				<select title="파일첨부갯수 선택" name="inf_fileCnt" id="inf_fileCnt" class="w100">
				<c:forEach begin="0" end="20" step="1" var="i">
					<option value="${i}" <c:if test="${inf_fileCnt == i}">selected="selected"</c:if>>${i}</option>
				</c:forEach>			
				</select>
			</dd>
			<dt><label for="inf_fileSizeLmt">파일 용량(MB)</label></dt>
			<dd>
				<c:set var="inf_fileSizeLmt" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fileSizeLmt" value="${infoVo.inf_fileSizeLmt }" /></c:if>
				<select title="파일크기 선택" name="inf_fileSizeLmt" id="inf_fileSizeLmt" class="w100">
				<c:forEach begin="10" end="600" step="10" var="i">
					<option value="${i}" <c:if test="${inf_fileSizeLmt == i}">selected="selected"</c:if>>${i}</option>
				</c:forEach>			
				</select>
			</dd>
			<dt><label for="inf_fileExt">첨부 허용 확장자</label></dt>
			<dd>
				<input type="text" id="inf_fileExt" name="inf_fileExt" maxlength="128" value="<c:out value="${infoVo.inf_fileExt }" />" placeholder="hwp,hwpx,doc,docx 쉼표구분" />
			</dd>
			<dt><label for="inf_skinType">게시판 형식</label></dt>
			<dd>
				<c:set var="inf_skinType" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_skinType" value="${infoVo.inf_skinType }" /></c:if>
				<select title="스킨타입 선택" name="inf_skinType" id="inf_skinType" >
					<option value="basic"  		<c:if test="${inf_skinType == 'basic'}">selected="selected"</c:if>>기본</option>
					<option value="reply"  		<c:if test="${inf_skinType == 'reply'}">selected="selected"</c:if>>답변(민원)</option>
					<option value="photo"  		<c:if test="${inf_skinType == 'photo'}">selected="selected"</c:if>>사진</option>
					<option value="calendar"  	<c:if test="${inf_skinType == 'calendar'}">selected="selected"</c:if>>일정(달력)</option>
					<option value="link"  		<c:if test="${inf_skinType == 'link'}">selected="selected"</c:if>>링크</option>
					<option value="usrField"  	<c:if test="${inf_skinType == 'usrField'}">selected="selected"</c:if>>사용자필드</option>
					<option value="upload"  	<c:if test="${inf_skinType == 'upload'}">selected="selected"</c:if>>파일업로드(관리자용)</option>
				</select>
			</dd>
			<dt><label for="inf_entryPoint">게시판 시작화면</label></dt>
			<dd>
				<c:set var="inf_entryPoint" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_entryPoint" value="${infoVo.inf_entryPoint }" /></c:if>
				<select title="시작 페이지" name="inf_entryPoint" id="inf_entryPoint" class="w100">
					<option value="1"  		<c:if test="${inf_entryPoint == 1}">selected="selected"</c:if>>목록</option>
					<option value="4"  		<c:if test="${inf_entryPoint == 4}">selected="selected"</c:if>>쓰기</option>
					<option value="5"  		<c:if test="${inf_entryPoint == 5}">selected="selected"</c:if>>안내보기</option>
				</select>
			</dd>
			<dt><label for="inf_fncWriterOnly">작성자 (본인글만 확인)</label></dt>
			<dd>
				<c:set var="inf_fncWriterOnly" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncWriterOnly" value="${infoVo.inf_fncWriterOnly }" /></c:if>
				<input type="checkbox" name="inf_fncWriterOnly" id="inf_fncWriterOnly" value="1" <c:if test="${inf_fncWriterOnly == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncDeptGroup">부서선택 기능</label></dt>
			<dd>
				<c:set var="inf_fncDeptGroup" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncDeptGroup" value="${infoVo.inf_fncDeptGroup }" /></c:if>
				<input type="checkbox" name="inf_fncDeptGroup" id="inf_fncDeptGroup" value="1" <c:if test="${inf_fncDeptGroup == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncAdmBlockID">차단 ID 사용</label></dt>
			<dd>
				<c:set var="inf_fncAdmBlockID" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmBlockID" value="${infoVo.inf_fncAdmBlockID }" /></c:if>
				<input type="checkbox" name="inf_fncAdmBlockID" id="inf_fncAdmBlockID" value="1" <c:if test="${inf_fncAdmBlockID == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncAdmBlockIP">차단 IP 사용</label></dt>
			<dd>
				<c:set var="inf_fncAdmBlockIP" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmBlockIP" value="${infoVo.inf_fncAdmBlockIP }" /></c:if>
				<input type="checkbox" name="inf_fncAdmBlockIP" id="inf_fncAdmBlockIP" value="1" <c:if test="${inf_fncAdmBlockIP == 1}"> checked="checked"</c:if> />
			</dd>
			<dt>관리부서</dt>
			<dd class="id">
				<input type="hidden" id="inf_admDept" name="inf_admDept" value="${infoVo.inf_admDept }" />
				<input type="text" id="inf_admDeptNm" name="inf_admDeptNm" readonly="readonly" value="<c:out value="${infoVo.inf_admDeptNm }" />" placeholder="관리부서" />
				<a href="#self" class="btn" onclick="openDept()">부서 검색</a>
			</dd>
			<dt><label for="inf_fncAdmMail">관리자 메일 전송</label></dt>
			<dd>
				<c:set var="inf_fncAdmMail" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmMail" value="${infoVo.inf_fncAdmMail }" /></c:if>
				<input type="checkbox" name="inf_fncAdmMail" id="inf_fncAdmMail" value="1" <c:if test="${inf_fncAdmMail == 1}"> checked="checked"</c:if> />
				<input type="text" id="inf_fncAdmMailAddr" name="inf_fncAdmMailAddr" maxlength="128" value="<c:out value="${infoVo.inf_fncAdmMailAddr }" />" placeholder="관리자 메일주소" style="width:calc(100% - 30rem); margin-left:10rem;"/>
			</dd>
			<dt><label for="inf_fncAdmSms">관리자 SMS 전송</label></dt>
			<dd>
				<c:set var="inf_fncAdmSms" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmSms" value="${infoVo.inf_fncAdmSms }" /></c:if>
				<input type="checkbox" name="inf_fncAdmSms" id="inf_fncAdmSms" value="1" <c:if test="${inf_fncAdmSms == 1}"> checked="checked"</c:if> />
				<input type="text" id="inf_deptTelno" name="inf_deptTelno" maxlength="16" value="<c:out value="${infoVo.inf_deptTelno }" />" placeholder="관리자 연락처" style="width:calc(100% - 30rem); margin-left:10rem;" />
			</dd>
			<dt><label for="inf_fncAdmNotice">공지 등록 기능</label></dt>
			<dd>
				<c:set var="inf_fncAdmNotice" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmNotice" value="${infoVo.inf_fncAdmNotice }" /></c:if>
				<input type="checkbox" name="inf_fncAdmNotice" id="inf_fncAdmNotice" value="1" <c:if test="${inf_fncAdmNotice == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrEditor">에디터 사용 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrEditor" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrEditor" value="${infoVo.inf_fncUsrEditor }" /></c:if>
				<input type="checkbox" name="inf_fncUsrEditor" id="inf_fncUsrEditor" value="1" <c:if test="${inf_fncUsrEditor == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncAdmTerm">관리자 게시기간 기능</label></dt>
			<dd>
				<c:set var="inf_fncAdmTerm" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmTerm" value="${infoVo.inf_fncAdmTerm }" /></c:if>
				<input type="checkbox" name="inf_fncAdmTerm" id="inf_fncAdmTerm" value="1" <c:if test="${inf_fncAdmTerm == 1}"> checked="checked"</c:if> />
				
				<c:set var="inf_fncAdmTermTy" value="-1" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmTermTy" value="${infoVo.inf_fncAdmTermTy }" /></c:if>
				<select title="기간설정구분" name="inf_fncAdmTermTy" id="inf_fncAdmTermTy" style="margin-left:10rem;">
					<option value="1"  	<c:if test="${inf_fncAdmTermTy == 1}">selected="selected"</c:if>>기간</option>
					<option value="0"  	<c:if test="${inf_fncAdmTermTy == 0}">selected="selected"</c:if>>당일</option>		
				</select>
			</dd>
			<dt><label for="inf_fncUseThumb">이미지 보기기능</label></dt>
			<dd>
				<c:set var="inf_fncUseThumb" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUseThumb" value="${infoVo.inf_fncUseThumb }" /></c:if>
				<input type="checkbox" name="inf_fncUseThumb" id="inf_fncUseThumb" value="1" <c:if test="${inf_fncUseThumb == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncAdmVod">동영상 기능</label></dt>
			<dd>
				<c:set var="inf_fncAdmVod" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncAdmVod" value="${infoVo.inf_fncAdmVod }" /></c:if>
				<input type="checkbox" name="inf_fncAdmVod" id="inf_fncAdmVod" value="1" <c:if test="${inf_fncAdmVod == 1}"> checked="checked"</c:if> />
			</dd>
			<%--
			<dt><label for="inf_pushGroup">모바일 App Push 기능</label></dt>
			<dd>
				<c:set var="inf_pushGroup" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_pushGroup" value="${infoVo.inf_pushGroup }" /></c:if>
				<input type="checkbox" name="inf_pushGroup" id="inf_pushGroup" value="1" <c:if test="${inf_pushGroup == 1}"> checked="checked"</c:if> />
			</dd>
			 --%>
			<dt><label for="inf_fncUsrAutoEnable">자동 활성 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrAutoEnable" value="1" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrAutoEnable" value="${infoVo.inf_fncUsrAutoEnable }" /></c:if>
				<input type="checkbox" name="inf_fncUsrAutoEnable" id="inf_fncUsrAutoEnable" value="1" <c:if test="${inf_fncUsrAutoEnable == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncMbrCheckAuth">회원 인증 기능</label></dt>
			<dd>
				<c:set var="inf_fncMbrCheckAuth" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncMbrCheckAuth" value="${infoVo.inf_fncMbrCheckAuth }" /></c:if>
				<input type="checkbox" name="inf_fncMbrCheckAuth" id="inf_fncMbrCheckAuth" value="1" <c:if test="${inf_fncMbrCheckAuth == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrCheckAuth">본인 확인 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrCheckAuth" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrCheckAuth" value="${infoVo.inf_fncUsrCheckAuth }" /></c:if>
				<input type="checkbox" name="inf_fncUsrCheckAuth" id="inf_fncUsrCheckAuth" value="1" <c:if test="${inf_fncUsrCheckAuth == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrLock">잠금 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrLock" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrLock" value="${infoVo.inf_fncUsrLock }" /></c:if>
				<input type="checkbox" name="inf_fncUsrLock" id="inf_fncUsrLock" value="1" <c:if test="${inf_fncUsrLock == 1}"> checked="checked"</c:if> />
				
				<c:set var="inf_fncUsrLockOpt" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrLockOpt" value="${infoVo.inf_fncUsrLockOpt }" /></c:if>
				<select title="기본공개여부설정" name="inf_fncUsrLockOpt" id="inf_fncUsrLockOpt" style="margin-left:10rem;" >
					<option value="0"  	<c:if test="${inf_fncUsrLockOpt == 0}">selected="selected"</c:if>>공개</option>
					<option value="1"  	<c:if test="${inf_fncUsrLockOpt == 1}">selected="selected"</c:if>>비공개</option>
					<option value="2"  	<c:if test="${inf_fncUsrLockOpt == 2}">selected="selected"</c:if>>강제비공개</option>
				</select>
			</dd>
			<dt><label for="inf_fncUsrVote">추천 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrVote" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrVote" value="${infoVo.inf_fncUsrVote }" /></c:if>
				<input type="checkbox" name="inf_fncUsrVote" id="inf_fncUsrVote" value="1" <c:if test="${inf_fncUsrVote == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrCmnts">댓글 기능</label></dt>
			<dd>
				<c:set var="inf_fncUsrCmnts" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrCmnts" value="${infoVo.inf_fncUsrCmnts }" /></c:if>
				<input type="checkbox" name="inf_fncUsrCmnts" id="inf_fncUsrCmnts" value="1" <c:if test="${inf_fncUsrCmnts == 1}"> checked="checked"</c:if> />
				<input type="text" id="inf_fncUsrCmntsCharLmt" name="inf_fncUsrCmntsCharLmt" class="inp_txt numberOnly wp30" maxlength="4" value="<c:out value="${infoVo.inf_fncUsrCmntsCharLmt }" />" placeholder="댓글 제한글자 수" style="width:calc(100% - 30rem); margin-left:10rem;" />
				<p>제한글자 수 입력</p>
			</dd>
			<dt><label for="inf_fieldEml">이메일 입력 사용</label></dt>
			<dd>
				<c:set var="inf_fieldEml" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fieldEml" value="${infoVo.inf_fieldEml }" /></c:if>
				<input type="checkbox" name="inf_fieldEml" id="inf_fieldEml" value="1" <c:if test="${inf_fieldEml == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fieldTelno">전화번호 입력 사용</label></dt>
			<dd>
				<c:set var="inf_fieldTelno" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fieldTelno" value="${infoVo.inf_fieldTelno }" /></c:if>
				<input type="checkbox" name="inf_fieldTelno" id="inf_fieldTelno" value="1" <c:if test="${inf_fieldTelno == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fieldAddr">주소 입력 사용</label></dt>
			<dd>
				<c:set var="inf_fieldAddr" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fieldAddr" value="${infoVo.inf_fieldAddr }" /></c:if>
				<input type="checkbox" name="inf_fieldAddr" id="inf_fieldAddr" value="1" <c:if test="${inf_fieldAddr == 1}"> checked="checked"</c:if> />
			</dd>
			<dt><label for="inf_fncUsrGroup">게시물 분류</label></dt>
			<dd>
				<c:set var="inf_fncUsrGroup" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrGroup" value="${infoVo.inf_fncUsrGroup }" /></c:if>
				<input type="checkbox" name="inf_fncUsrGroup" id="inf_fncUsrGroup" value="1" <c:if test="${inf_fncUsrGroup == 1}"> checked="checked"</c:if> />
				
				<c:if test="${inf_fncUsrGroup == 1 }">
					<a href="#self" class="btn" onclick="openBoardGroup()" style="margin-left:10rem;">분류 관리</a>
				</c:if>
			</dd>
			<dt><label for="inf_fncUsrField">사용자 정의 필드</label></dt>
			<dd>
				<c:set var="inf_fncUsrField" value="0" />
				<c:if test="${infoVo != null }"><c:set var="inf_fncUsrField" value="${infoVo.inf_fncUsrField }" /></c:if>
				<input type="checkbox" name="inf_fncUsrField" id="inf_fncUsrField" value="1" <c:if test="${inf_fncUsrField == 1}"> checked="checked"</c:if> />
				
				<c:if test="${inf_fncUsrField == 1 }">
					<a href="#self" class="btn" onclick="openBoardField()">정의 필드 관리</a>
				</c:if>
				
			</dd>
			<dt>통합 / 공유</dt>
			<dd>
				<c:if test="${infoVo != null }">
				<a href="#self" class="btn" onclick="openBoardShare()">통합 공유 설정</a>
				</c:if>
			</dd>
		
		
		
			<dt>게시판<br/>Header</dt>
			<dd class="board_header_check" style="flex:1 1 80%;">
				<table class="board_table">
					<thead>
							<th scope="col"><label for="bhc_visibleNo">번호</label></dt>
							<th scope="col"><label for="bhc_visibleGroup">분류</label></dt>
							<th scope="col"><label for="bhc_visibleTtl">제목</label></dt>
							<th scope="col"><label for="bhc_visibleNm">작성자</label></dt>
							<th scope="col"><label for="bhc_visibleDept">작성부서</label></dt>
							<th scope="col"><label for="bhc_visibleDate">작성일</label></dt>
							<th scope="col"><label for="bhc_visibleFile">첨부파일</label></dt>
							<th scope="col"><label for="bhc_visibleReadCnt">조회수</label></dt>
						
					</thead>
					<tbody>
						
							<c:set var="bhc_visibleNo" value="1" />
							<c:set var="bhc_visibleGroup" value="0" />
							<c:set var="bhc_visibleTtl" value="1"  />
							<c:set var="bhc_visibleNm" value="1" />
							<c:set var="bhc_visibleDept" value="0" />
							<c:set var="bhc_visibleDate" value="1" />
							<c:set var="bhc_visibleFile" value="1" />
							<c:set var="bhc_visibleReadCnt" value="1" />
							<c:if test="${infoVo != null }">
								<c:set var="boardHeadColumnVo" value="${infoVo.boardHeadColumnVo}" />
								<c:set var="bhc_visibleNo" 		value="${boardHeadColumnVo.bhc_visibleNo }" />
								<c:set var="bhc_visibleGroup" 	value="${boardHeadColumnVo.bhc_visibleGroup }" />
								<c:set var="bhc_visibleTtl" 	value="${boardHeadColumnVo.bhc_visibleTtl }"  />
								<c:set var="bhc_visibleNm" 		value="${boardHeadColumnVo.bhc_visibleNm }" />
								<c:set var="bhc_visibleDept" 	value="${boardHeadColumnVo.bhc_visibleDept }" />
								<c:set var="bhc_visibleDate" 	value="${boardHeadColumnVo.bhc_visibleDate }" />
								<c:set var="bhc_visibleFile" 	value="${boardHeadColumnVo.bhc_visibleFile }" />
								<c:set var="bhc_visibleReadCnt" value="${boardHeadColumnVo.bhc_visibleReadCnt }" />
							</c:if>
							<td><input type="checkbox" id="bhc_visibleNo" 		name="bhc_visibleNo" value="1" 		<c:if test="${bhc_visibleNo == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleGroup" 	name="bhc_visibleGroup" value="1" 	<c:if test="${bhc_visibleGroup == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleTtl" 		name="bhc_visibleTtl" value="1" 	<c:if test="${bhc_visibleTtl == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleNm" 		name="bhc_visibleNm" value="1" 		<c:if test="${bhc_visibleNm == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleDept" 	name="bhc_visibleDept" value="1" 	<c:if test="${bhc_visibleDept == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleDate" 	name="bhc_visibleDate" value="1" 	<c:if test="${bhc_visibleDate == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleFile"		name="bhc_visibleFile" value="1" 	<c:if test="${bhc_visibleFile == 1}"> checked="checked"</c:if> /></td>
							<td><input type="checkbox" id="bhc_visibleReadCnt" 	name="bhc_visibleReadCnt" value="1" <c:if test="${bhc_visibleReadCnt == 1}"> checked="checked"</c:if> /></td>
						
					</tbody>
				</table>
			</dd>
			<dt><label for="inf_resourceFile">게시판 리소스 파일경로</label></dt>
			<dd style="flex:1 1 80%;">
				<input type="text" id="inf_resourceFile" name="inf_resourceFile" class="inp_txt wp90" maxlength="128" value="<c:out value="${infoVo.inf_resourceFile }" />" placeholder="리소스 파일경로" />
			</dd>
			<dt><label for="inf_oldBoardLnk">기존 게시판 링크</label></dt>
			<dd style="flex:1 1 80%;">
				<input type="text" id="inf_oldBoardLnk" name="inf_oldBoardLnk" class="inp_txt wp90" maxlength="128" value="<c:out value="${infoVo.inf_oldBoardLnk }" />" placeholder="기존 게시판 링크" />
			</dd>
			<dt><label for="inf_filterChar">필터링 단어<br/>(쉼표, 구분)</label></dt>
			<dd style="flex:1 1 80%;">
				<textarea id="inf_filterChar" name="inf_filterChar" maxlength="2000" style="height:100px"><c:out value="${infoVo.inf_filterChar}" escapeXml="false" /></textarea>
			</dd>
			<dt><label for="inf_defaultCn">기본 출력 양식/내용</label></dt>
			<dd style="flex:1 1 80%;">
				<textarea id="inf_defaultCn" name="inf_defaultCn" maxlength="2000" style="height:100px"><c:out value="${infoVo.inf_defaultCn}" escapeXml="false" /></textarea>
				<%--
				<script>
				CKEDITOR.replace( "inf_defaultCn", {
					toolbar :"source"
					, height : "80"
				});
				</script>
				 --%>
			</dd>
			<dt><label for="inf_header">머리말</label></dt>
			<dd style="flex:1 1 80%;">
				<textarea id="inf_header" name="inf_header" maxlength="4000" style="height:100px"><c:out value="${infoVo.inf_header}" escapeXml="false" /></textarea>
				<%--
				<script>
				CKEDITOR.replace( "inf_header", {
					toolbar :"source"
					, height : "80"
				});
				</script>
				 --%>
			</dd>
			<dt><label for="inf_footer">꼬리말</label></dt>
			<dd style="flex:1 1 80%;">
				<textarea id="inf_footer" name="inf_footer" maxlength="4000" style="height:100px"><c:out value="${infoVo.inf_footer}" escapeXml="false" /></textarea>
				<%--
				<script>
				CKEDITOR.replace( "inf_footer", {
					toolbar :"source"
					, height : "80"
				});
				</script>
				 --%>
			</dd>
		
	<c:if test="${infoVo != null }">
		
			<dt>수정 관리자 / 수정 일시</dt>
			<dd>
				<c:out value="${infoVo.inf_mdfcnID }" /> / <fmt:formatDate value="${infoVo.inf_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</dd>
		
	</c:if>
	</dl>
</div>

</form>

<div class="board_btn">
	<a href="#self"  onclick="saveData()">저장</a>
	<c:if test="${vo.mnu_code != ''}">
	<a href="#self" class="btn pink floatR" onclick="deleteData()" <c:if test="${vo != null}">disabled="disabled"</c:if> >삭제</a>
	</c:if>
</div>

<script>
function saveData()
{
    $("#cmd").val(${cmd});
	$("#infoFrm").submit();
}

function openBoardGroup() {
	var mnu_code		= $("#mnu_code").val();
	var parm_mnuCode	= $("#parm_mnuCode").val();
	
	window.open("<c:url value="${managerDir }/cms/board/info/group/group.do" />?mnu_code="+mnu_code+"&parm_mnuCode="+parm_mnuCode, "boardGroupWin", "width=500,height=500,resizable=yes,scrollbars=yes");
}
function openBoardField() {
	var mnu_code		= $("#mnu_code").val();
	var para_mnuCode	= $("#para_mnuCode").val();

    window.open("<c:url value="${managerDir }/cms/board/info/field/field.do" />?mnu_code="+mnu_code+"&parm_mnuCode="+parm_mnuCode, "boardFieldWin", "width=1200,height=450,resizable=yes,scrollbars=yes");
}

function openBoardShare() {
	var mnu_code		= $("#mnu_code").val();
	var para_mnuCode	= $("#para_mnuCode").val();
	
	window.open("<c:url value="${managerDir }/cms/board/info/share/share.do" />?mnu_code="+mnu_code+"&bod_mnuCode="+parm_mnuCode, "boardShareWin", "width=950,height=800,resizable=yes,scrollbars=yes");
}

function openDept() {
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/deptEmp.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popDeptEmp", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml) {
	$("#inf_admDept").val(dept_id);
	$("#inf_admDeptNm").val(dept_nm);
	
	$("#inf_deptTelno").val(emp_telno);
	$("#inf_fncAdmMailAddr").val(emp_eml);
}

function deleteData()
{
	if(confirm("현재 게시판을 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#infoFrm").submit();
	}
}
</script>