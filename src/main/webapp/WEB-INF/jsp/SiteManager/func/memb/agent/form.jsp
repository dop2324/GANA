<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="writeFrm" name="writeFrm" method="post" action="<c:url value="${managerDir }/func/memb/agent/process.do" />" onsubmit="return frm_vaildate(this);">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="useOrgId"		name="useOrgId" 	value="<c:out value="${vo.useOrgId}" />"/>

<div class="board_wrap">
	<div class="board_write form_style">
		<dl>
			<dt><label for="useOrgNm">이용기관명</label></dt>
			<dd>
				<input type="text" id="useOrgNm" name="useOrgNm" maxlength="150" value="<c:out value='${vo.useOrgNm }' />" required placeholder="이용기관명" />
			</dd>
			<dt><label for="hmpgNm">홈페이지명</label></dt>
			<dd>
				<input type="text" id="hmpgNm" name="hmpgNm" maxlength="150" value="<c:out value='${vo.hmpgNm }' />" required placeholder="홈페이지명" />
			</dd>
			<dt><label for="hmpgUrl">홈페이지주소(URL)</label></dt>
			<dd>
				<input type="text" id="hmpgUrl" name="hmpgUrl" maxlength="150" value="<c:out value='${vo.hmpgUrl }' />" required placeholder="https:// 정보부터 입력바랍니다." />
			</dd>
			<dt><label for="hmpgUrl">콜백주소(URL)</label></dt>
			<dd>
				<input type="text" id="callbackUrl" name="callbackUrl" maxlength="150" value="<c:out value='${vo.callbackUrl }' />" required placeholder="https:// 정보부터 입력바랍니다." />
				<div class="board_desc">
					CallBack페이지주소는 반드시 홈페이지주소 기반이어야 합니다.(예시. 홈페이지주소:www.abc.go.kr / CallBack페이지주소:www.abc.go.kr/callback.do) 
				</div>
			</dd>
			<dt><label for="orgPicNm">담당자명</label></dt>
			<dd>
				<input type="text" id="orgPicNm" name="orgPicNm" maxlength="80" value="<c:out value='${vo.orgPicNm }' />" required placeholder="담당자명" />
			</dd>
			<dt><label for="orgPicTelno">담당자 연락처</label></dt>
			<dd class="short">
				<input type="text" id="orgPicTelno" name="orgPicTelno" maxlength="80" value="<c:out value='${vo.orgPicTelno }' />" placeholder="000-0000-0000" />
			</dd>
			<dt><label for="orgPicEmlAddr">담당자 이메일</label></dt>
			<dd class="short">
				<input type="text" id="orgPicEmlAddr" name="orgPicEmlAddr" maxlength="80" value="<c:out value='${vo.orgPicEmlAddr }' />" placeholder="mail@doamin.com" />
			</dd>
			<dt><label for="clientId">Client ID</label></dt>
			<dd class="short">
				<input type="text" id="clientId" name="clientId" maxlength="25" value="<c:out value='${vo.clientId }' />" readonly placeholder="해당정보는 자동생성됩니다." />
			</dd>
			<dt><label for="useYn">사용유무</label></dt>
			<dd class="short">
				<input type="radio" name="useYn" id="useYn_Y" value="Y" required <c:out value="${vo.useYn eq 'Y'?' checked':'' }" /> /> <label for="useYn_Y">사용</label>
				<input type="radio" name="useYn" id="useYn_N" value="N" required <c:out value="${vo.useYn eq 'N'?' checked':'' }" /> /> <label for="useYn_N">미사용</label>
			</dd>
		</dl>
	</div>
</div>

<div class="board_btn">
	<button type="submit" class="btn blue">저장</button>
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

</form>

<script type="text/javascript">
//<![CDATA[
	function frm_vaildate(f){
		
		var email = f.orgPicEmlAddr.value.trim();
		if (email !== "") {
			var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(email)) {
				alert("유효한 이메일 주소를 입력해 주세요.");
				f.orgPicEmlAddr.focus();
				return false;
			}
		}
		
	    var hmpgUrl = f.hmpgUrl.value.trim();
	    var callbackUrl = f.callbackUrl.value.trim();

	    // https://로 시작하는지 검사
	    if (!hmpgUrl.startsWith("https://")) {
	        alert("홈페이지 주소는 반드시 https://로 시작해야 합니다.");
	        f.hmpgUrl.focus();
	        return false;
	    }
	    if (!callbackUrl.startsWith("https://")) {
	        alert("콜백 주소는 반드시 https://로 시작해야 합니다.");
	        f.callbackUrl.focus();
	        return false;
	    }

	    try {
	        var hmpgDomain = new URL(hmpgUrl).hostname;
	        var callbackDomain = new URL(callbackUrl).hostname;

	        if (hmpgDomain !== callbackDomain) {
	            alert("홈페이지 주소와 콜백 주소의 도메인이 일치하지 않습니다.\n예: https://www.abc.go.kr 와 https://www.abc.go.kr/callback.do");
	            f.callbackUrl.focus();
	            return false;
	        }
	    } catch (e) {
	        alert("홈페이지 주소 또는 콜백 주소가 유효한 URL 형식이 아닙니다.");
	        return false;
	    }
		
		return true;
	}
//]]>
</script>