<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="memberFrm" name="memberFrm" method="post" action="<c:url value="${managerDir }/cms/member/member_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"			name="mnu_code"			value="<c:out value="${mnu_code }"/>" />

<input type="hidden" id="returnUrl" 		name="returnUrl" 		value="<c:out value="${returnUrl }"/>" />
<input type="hidden" id="queryString"		name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"				name="cmd" 				value="<c:out value="${cmd}"/>" />


<div class="form_style">
	<dl>
	<c:if test="${vo != null}">
		<c:if test="${vo.mem_sttus != 9 }">
			<dt>로그인 실패횟수</dt>
			<dd>
				<span id="initLoginFailCnt"><c:out value="${vo.mem_loginFailCnt}" /></span> 회
				&nbsp;  
				<a href="#self" onClick="initLoginFailCnt();" id="btnInitLoginFailCnt" class="btn">초기화</a>
			</dd>
		</c:if>
	</c:if>
		<dt><label for="site_code">사이트</labe></dt>
		<dd>
			<c:set var="siteList" value="${defaultSiteMap.sitePrmList }" />
			<c:set var="siteCode" value="${site_code }" />

			<c:choose>
				<c:when test="${vo != null }"><c:set var="siteCode" value="${vo.site_code }" /></c:when>
			</c:choose>

			<select title="사이트 선택" name="site_code" id="site_code">
				<option value="">사이트 선택</option>
				<c:forEach var="siteVo" items="${siteList}" varStatus="status">
					<option value="<c:out value="${siteVo.site_code}" />" <c:if test="${siteVo.site_code == siteCode}">selected="selected"</c:if>><c:out value="${siteVo.site_nm }" /></option>
				</c:forEach>			
			</select>
		</dd>
		<dt>회원 그룹</dt>
		<dd>
			<c:set var="grp_id" value="${srchGroup }" />
			<c:if test="${vo.mem_id != null}"><c:set var="grp_id" value="${vo.grp_id }" /></c:if>
			<c:out value="${grp_id }" /> 
			<c:if test="${vo.grp_nm != null }">
				(<c:out value="${vo.grp_nm }" />)
			</c:if>
			<input type="hidden" id="grp_id" name="grp_id" value="<c:out value="${grp_id }" />" />
		</dd>
		<dt><label for="mem_id">회원 ID</label></dt>
		<dd <c:if test="${vo == null}">class="id"</c:if>>
			<input type="text" id="mem_id" name="mem_id" maxlength="32" value="<c:out value="${vo.mem_id }" />" <c:if test="${vo != null}">readonly="readonly"</c:if> autocomplete="one-time-code" placeholder="회원 ID (영문+숫자)" />
			<c:if test="${vo == null}">
				<a href="#self" class="btn" onclick="checkExistID()">중복확인</a> 
			</c:if>
		</dd>
		<dt><label for="mem_pw">비밀번호</label></dt>
		<dd>
			<input type="password" id="mem_pw" name="mem_pw" maxlength="20" autocomplete="new-password" placeholder="비밀번호 (영문+숫자+특수문자) 8자~20자" />
			
			<c:if test="${vo != null}">
			<input type="checkbox" id="changePw" name="changePw" value="1" /><label for="changePw">비밀번호 변경</label>
			</c:if>
		</dd>
		<dt><label for="chk_pw">비밀번호 확인</label></dt>
		<dd><input type="password" id="chk_pw" name="chk_pw" maxlength="20" placeholder="비밀번호 확인" autocomplete="new-password" /></dd>
		<dt><label for="mem_nm">이름</label></dt>
		<dd><input type="text" id="mem_nm" name="mem_nm" maxlength="64" value="<c:out value="${vo.mem_nm }" />" style="ime-mode:active" placeholder="이름" /></dd>
		<dt><label for="mem_mail">이메일</label></dt>
		<dd>
			<input type="text" id="mem_mail" name="mem_mail" maxlength="128" value="<c:out value="${vo.mem_mail }" />" placeholder="이메일 (id@domain.co.kr)" />
		</dd>
		<dt><label for="mem_birth">생년월일</label></dt>
		<dd>
			<input type="text" id="mem_birth" name="mem_birth" maxlength="12" value="<c:out value="${vo.mem_birth }" />" placeholder="생년월일 (YYYYMMDD)" />
		</dd>
		<dt><label for="mem_telno">전화번호</label></dt>
		<dd>
			<input type="text" id="mem_telno" name="mem_telno" maxlength="16" value="<c:out value="${vo.mem_telno }" />" placeholder="전화번호 숫자만" />
		</dd>
		<dt><label for="mem_moblphone">휴대전화</label></dt>
		<dd>
			<input type="text" id="mem_moblphone" name="mem_moblphone" maxlength="16" value="<c:out value="${vo.mem_moblphone }" />" placeholder="휴대전화 숫자만" />
		</dd>
		<dt>성별</dt>
		<dd>
			<input id="mem_gender_1" name="mem_gender" type="radio"class="inp_rad" value="1" <c:if test="${vo.mem_gender == 1}">checked="checked"</c:if> /> 
			<label for="mem_gender_1">남성</label>
			<input id="mem_gender_2" name="mem_gender" type="radio"class="inp_rad" value="2" <c:if test="${vo.mem_gender == 2}">checked="checked"</c:if> /> 
			<label for="mem_gender_2">여성</label>
			<input id="mem_gender_0" name="mem_gender" type="radio"class="inp_rad" value="0" <c:if test="${vo.mem_gender == 0}">checked="checked"</c:if> /> 
			<label for="mem_gender_0">비공개</label>
		</dd>
		<dt>주소</dt>
		<dd class="address">
			<input type="text" id="mem_zip" name="mem_zip" maxlength="5" value="<c:out value="${vo.mem_zip }" />" placeholder="우편번호"  />
			<a href="#self" class="btn" onclick="execDaumPostcode();return false;">주소찾기</a> 
			<br/>
			<input type="text" id="mem_addr" name="mem_addr" maxlength="128" value="<c:out value="${vo.mem_addr }" />" placeholder="주소"  /><br/>
			<input type="text" id="mem_addrDtl" name="mem_addrDtl" maxlength="64" value="<c:out value="${vo.mem_addrDtl }" />" placeholder="상세주소"  />
		</dd>
		<dt><label for="mem_company">직장명</label></dt>
		<dd>
			<input type="text" id="mem_company" name="mem_company" maxlength="128" value="<c:out value="${vo.mem_company }" />" placeholder="직장명" />
		</dd>
		<dt><label for="mem_pos">직위</label></dt>
		<dd>
			<input type="text" id="mem_pos" name="mem_pos" maxlength="32" value="<c:out value="${vo.mem_pos }" />" placeholder="직위" />
		</dd>
		<dt>메일링수신여부</dt>
		<dd>
			<input id="mem_emlYn_1" name="mem_emlYn" type="radio"class="inp_rad" value="1" <c:if test="${vo.mem_emlYn == 1}">checked="checked"</c:if> /> 
			<label for="mem_emlYn_1">수신</label>
			<input id="mem_emlYn_0" name="mem_emlYn" type="radio"class="inp_rad" value="0" <c:if test="${vo.mem_emlYn == 0}">checked="checked"</c:if> /> 
			<label for="mem_emlYn_0">수신거부</label>
		</dd>
		<dt>활동여부</dt>
		<dd>
			<c:choose>
				<c:when test="${vo.mem_sttus == 9 }">
					탈퇴회원
				</c:when>
				<c:otherwise>
					<input id="mem_sttus_1" name="mem_sttus" type="radio"class="inp_rad" value="1" <c:if test="${vo.mem_sttus == 1}">checked="checked"</c:if> /> 
					<label for="mem_sttus_1">활동</label>
					<input id="mem_sttus_0" name="mem_sttus" type="radio"class="inp_rad" value="0" <c:if test="${vo.mem_sttus == 0}">checked="checked"</c:if> /> 
					<label for="mem_sttus_0">중지</label>
					
					<input id="mem_sttus_6" name="mem_sttus" type="radio"class="inp_rad" value="6" <c:if test="${vo.mem_sttus == 6}">checked="checked"</c:if> /> 
					<label for="mem_sttus_6">거부</label>
				</c:otherwise>
			</c:choose>
		</dd>
		<dt><label for="mem_param">Parameter</label></dt>
		<dd>
			<input type="text" id="mem_param" name="mem_param" maxlength="256" value="<c:out value="${vo.mem_param }" />" placeholder="Parameter key||value,key||value" />
			<p class="red"> 회원의 별도기능을 위한 값입니다. <span class="b">(key||value,key||value)</span> 구별하여 사용</p>
		</dd>
		<dt>최근 로그인일시</dt>
		<dd>
			<fmt:formatDate value="${vo.mem_lastLoginDt}" pattern="yyyy-MM-dd HH:mm:ss" />
		</dd>
		<dt>비밀번호 변경 일시</dt>
		<dd>
			<fmt:formatDate value="${vo.mem_chgPwDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			
			<c:set var="expirePwDtDiff"><fmt:message bundle="${globalsConfig}" key="Globals.expirePwDt" /></c:set>
			<c:if test="${expirePwDtDiff <= vo.mem_chgPwDtDiff}">
				<p class="red" style="width:100%;">비밀번호 만료 <fmt:formatNumber value="${vo.mem_chgPwDtDiff - expirePwDtDiff}" groupingUsed="true"/> 일 경과</p>
			</c:if>
		</dd>
		<dt>최초등록 일시 / 수정 일시</dt>
		<dd>
			<fmt:formatDate value="${vo.mem_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			<c:if test="${vo.mem_mdfcnDt != null }">
				/ <fmt:formatDate value="${vo.mem_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
			</c:if>
		</dd>
	<dl>
</div>
</form>

<div class="board_btn">

	<c:if test="${vo.mem_sttus != 9 }">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	</c:if>
	
	<a href="<c:out value="${listLink }"/>" class="btn">목록</a>
	
	<c:if test="${vo.mem_sttus != 9 }">
	<a href="#self" class="btn pink floatR" onclick="deleteData()" <c:if test="${vo != null}">disabled="disabled"</c:if> >삭제</a>
	</c:if>

</div>

<script>
function checkExistID()
{
	var mem_id = $("#mem_id").val();
	
	if(mem_id == "") {
		alert("ID을 입력하세요!");
		$("#mem_id").focus();
        return false;
	}
	else
	{
		$.ajax({
			type:"post"
			, url:"/SiteManager/cms/member/checkExistID.do"
			, dataType:"json"
			, data: {
				mnu_code : $("#mnu_code").val()
				, mem_id : mem_id
			}
			, success:function(data)
			{
				if(data.result == 0) {
					alert("사용 가능한 아이디입니다.");
				}else{
					alert("사용 불가능한 아이디입니다.");
				}
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
}

function initLoginFailCnt()
{
	var mem_id = $("#mem_id").val();
	
	$.ajax({
		type:"post"
		, url:"/SiteManager/cms/member/initLoginFailCnt.do"
		, dataType:"json"
		, data: {
			mnu_code : $("#mnu_code").val()
			, mem_id : mem_id
		}
		, success:function(data)
		{
			if(data.result == 0) {
				alert("처리 중 오류가 발생하였습니다 다시 시도하여 주십시요");
			}else{
				$("#initLoginFailCnt").text("0");
				alert("초기화 하였습니다.");
			}
		}
		, error:function(jqXHR, textStatus, errorThrown)
		{
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
}

function saveData()
{
	if($("#site_code").val() == "") {
		alert("사이트를 선택하여 주십시요");
		$("#site_code").select();
		return false;
	}
	
	if($("#mem_id").val() == "") {
		alert("회원 ID를 입력하여 주십시요");
		$("#mem_id").select();
		return false;
	}
	
	if(!validateEng($("#mem_id").val())) {
		alert("회원 ID를 영문 숫자조합으로 입력하여 주십시요");
		$("#mem_id").select();
		return false;
	}
	
<c:if test="${cmd == 4}">
	if($("#mem_pw").val() == "") {
		alert("비밀번호를 입력하여 주십시요");
		$("#mem_pw").select();
		return false;
	}
	
	if(!regPw.test($("#mem_pw").val())) {
		alert("비밀번호는 영문 숫자 특수기호 조합 8자리 이상 사용해야 합니다.");
		$("#mem_pw").select();
		return false;
	}
	
	if($("#chk_pw").val() == "") {
		alert("비밀번호 확인을 입력하여 주십시요");
		$("#chk_pw").select();
		return false;
	}
	
	if($('#mem_pw').val()!=$('#chk_pw').val()) {
		alert("비밀번호 확인이 일치하지 않습니다.");
		$('#chk_pw').focus();
		return false;
	}
</c:if>
	if($("#mem_nm").val() == "") {
		alert("회원이름을 입력하여 주십시요");
		$("#mem_nm").select();
		return false;
	}

	
	$("#memberFrm").submit();
}

function deleteData()
{
	if(confirm("삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#memberFrm").submit();
	}
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
	
	        $("#mem_zip").val(data.zonecode);
			$("#mem_addr").val(roadAddr);
		}
	}).open({
		popupTitle: '우편번호 검색 팝업' //팝업창 타이틀 설정 (영문,한글,숫자 모두 가능)
	});
}
</script>
