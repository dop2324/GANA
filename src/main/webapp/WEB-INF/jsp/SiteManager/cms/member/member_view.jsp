<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div class="form_style">
	<dl>
		<c:if test="${vo != null}">
		<c:if test="${vo.mem_sttus != 9 }">
			<dt>로그인 실패횟수</dt>
			<dd>
				<span id="initLoginFailCnt"><c:out value="${vo.mem_loginFailCnt}" /></span> 회
				&nbsp;  
				<a href="#self" onClick="initLoginFailCnt();" id="btnInitLoginFailCnt" class="bt2 mod">초기화</a>
			</dd>
		</c:if>
		</c:if>
			<dt>사이트</dt>
			<dd><c:out value="${siteVo.site_nm }" /></dd>
			<dt>회원 그룹</dt>
			<dd>
				<c:out value="${vo.grp_id }" /> 
				<c:if test="${vo.grp_nm != null }">
					(<c:out value="${vo.grp_nm }" />)
				</c:if>
			</dd>
			<dt>회원 ID</dt>
			<dd><c:out value="${vo.mem_id }" /></dd>
			<dt>이름</dt>
			<dd><c:out value="${vo.mem_nm }" /></dd>
			<dt>이메일</dt>
			<dd><c:out value="${vo.mem_mail }" /></dd>
			<dt>생년월일</dt>
			<dd><c:out value="${vo.mem_birth }" /></dd>
			<dt>전화번호</dt>
			<dd><c:out value="${vo.mem_telno }" /></dd>
			<dt>휴대전화</dt>
			<dd><c:out value="${vo.mem_moblphone }" /></dd>
			<dt>성별</dt>
			<dd>
				<c:choose>
					<c:when test="${vo.mem_gender == 1 }">남성</c:when>
					<c:when test="${vo.mem_gender == 2 }">여성</c:when>
					<c:when test="${vo.mem_gender == 0 }">비공개</c:when>
				</c:choose>
			</dd>
			<dt>주소</dt>
			<dd>
				<c:out value="${vo.mem_zip }" />
				<br/>
				<c:out value="${vo.mem_addr }" /><br/>
				<c:out value="${vo.mem_addrDtl }" />
			</dd>
			<dt>직장명</dt>
			<dd><c:out value="${vo.mem_company }" /></dd>
			<dt>직위</dt>
			<dd><c:out value="${vo.mem_pos }" /></dd>
			<dt>메일링수신여부</dt>
			<dd>
				<c:choose>
					<c:when test="${vo.mem_emlYn == 1 }">허용</c:when>
					<c:when test="${vo.mem_emlYn == 0 }">거부</c:when>
				</c:choose>
			</dd>
			<dt>활동여부</dt>
			<dd>
				<c:choose>
					<c:when test="${vo.mem_sttus == 0 }">중지</c:when>
					<c:when test="${vo.mem_sttus == 1 }">활동</c:when>
					<c:when test="${vo.mem_sttus == 6 }">거부</c:when>
					<c:when test="${vo.mem_sttus == 9 }">탈퇴</c:when>
				</c:choose>
			</dd>
			<dt>Parameter</dt>
			<dd><c:out value="${vo.mem_param }" /></dd>
			<dt>최근 로그인일시</dt>
			<dd>
				<fmt:formatDate value="${vo.mem_lastLoginDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</dd>
			<dt>최초등록 일시 / 수정 일시</dt>
			<dd>
				<fmt:formatDate value="${vo.mem_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
				<c:if test="${vo.mem_mdfcnDt != null }">
					/ <fmt:formatDate value="${vo.mem_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
				</c:if>
			</dd>
	</dl>
</div>

<div class="board_btn">
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>


