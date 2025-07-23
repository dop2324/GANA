<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="siteFrm" name="siteFrm" method="post" action="<c:url value="${managerDir }/cms/menu/site/site_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"		value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="cmd"			name="cmd" 			value="<c:out value="${cmd}" />"/>
<input type="hidden" id="returnUrl" 	name="returnUrl" 	value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"	value="<c:out value="${queryString }" escapeXml="false" />" />

<div class="form_style type2">
	<dl>
		<dt><label for="site_code">사이트 코드</label></dt>
		<dd class="id">
			<input type="text" id="site_code" name="site_code" class="inp_txt wp60 engNumOnly" maxlength="64" <c:if test="${vo != null}">readonly="readonly"</c:if> value="<c:out value="${vo.site_code }" />" placeholder="사이트 코드" />
			
			<c:if test="${vo == null}">
				<a href="#self" class="btn" onclick="checkExistCode()">중복확인</a> 
			</c:if>
			
			<c:if test="${vo.site_useYn == 9 }">
				<span class="red b">폐쇄</span>
			</c:if>
		</dd>
		<dt><label for="site_nm">사이트 명</label></dt>
		<dd>
			<input type="text" id="site_nm" name="site_nm" maxlength="64" value="<c:out value="${vo.site_nm }" />" placeholder="사이트 명" />
		</dd>
		<dt><label for="site_directory">디렉토리</label></dt>
		<dd>
			<input type="text" id="site_directory" name="site_directory" maxlength="64" value="<c:out value="${vo.site_directory }" />" placeholder="디렉토리" />
		</dd>
		<dt><label for="site_cmsUrl">사이트 URL주소</label></dt>
		<dd>
			<input type="text" id="site_cmsUrl" name="site_cmsUrl" maxlength="128" value="<c:out value="${vo.site_cmsUrl }" />" placeholder="사이트 URL주소" />
		</dd>
		<dt><label for="site_domain1">도메인 1</label></dt>
		<dd>
			<input type="text" id="site_domain1" name="site_domain1" maxlength="128" value="<c:out value="${vo.site_domain1 }" />" placeholder="사이트 도메인1" />
		</dd>
		<dt><label for="site_domain2">도메인 2</label></dt>
		<dd>
			<input type="text" id="site_domain2" name="site_domain2" maxlength="128" value="<c:out value="${vo.site_domain2 }" />" placeholder="사이트 도메인2" />
		</dd>
		<dt>언어</dt>
		<dd>
			<c:set var="site_locale" value="ko" />
			<c:if test="${vo != null }"><c:set var="site_locale" value="${vo.site_locale}" /></c:if>
			
			<input id="site_locale_1" name="site_locale" type="radio" value="ko" <c:if test="${site_locale == 'ko' }">checked="checked"</c:if> />
			<label for="site_locale_1">한국어</label>
			
			<%-- 
			<input id="site_locale_2" name="site_locale" type="radio" value="en" <c:if test="${site_locale == 'en' }">checked="checked"</c:if> />
			<label for="site_locale_2">영어</label>
			<input id="site_locale_3" name="site_locale" type="radio" value="cn" <c:if test="${site_locale == 'cn' }">checked="checked"</c:if> />
			<label for="site_locale_3">중국어</label>
			<input id="site_locale_4" name="site_locale" type="radio" value="jp" <c:if test="${site_locale == 'jp' }">checked="checked"</c:if> />
			<label for="site_locale_4">일본어</label>
			
			&nbsp;&nbsp;&nbsp;
			<input id="site_locale_99" name="site_locale" type="radio" value="i18n" <c:if test="${site_locale == 'i18n' }">checked="checked"</c:if> />
			<label for="site_locale_99">국제화</label>
			--%>
		</dd>
	<%--
		<dt>모바일 유무</dt>
		<dd>
			<c:set var="site_isMobile" value="0" />
			<c:if test="${vo != null }"><c:set var="site_isMobile" value="${vo.site_isMobile}" /></c:if>
			
			<input id="site_isMobile_0" name="site_isMobile" type="radio" value="0" <c:if test="${site_isMobile == 0 }">checked="checked"</c:if> />
			<label for="site_isMobile_0">비 모바일</label>
			<input id="site_isMobile_1" name="site_isMobile" type="radio" value="1" <c:if test="${site_isMobile == 1 }">checked="checked"</c:if> />
			<label for="site_isMobile_1">모바일 환경</label>
		</dd>
		--%>
		<dt>관리자 여부</dt>
		<dd>
			<c:set var="site_mngYn" value="0" />
			<c:if test="${vo != null }"><c:set var="site_mngYn" value="${vo.site_mngYn}" /></c:if>
			
			<input id="site_mngYn_0" name="site_mngYn" type="radio" value="0" <c:if test="${site_mngYn == 0 }">checked="checked"</c:if> />
			<label for="site_mngYn_0">사용자 환경</label>
			<input id="site_mngYn_1" name="site_mngYn" type="radio" value="1" <c:if test="${site_mngYn == 1 }">checked="checked"</c:if> />
			<label for="site_mngYn_1">관리자 환경</label>
		</dd>
		<dt>사용 유무</dt>
		<dd>
			<c:set var="site_useYn" value="1" />
			<c:if test="${vo != null }"><c:set var="site_useYn" value="${vo.site_useYn}" /></c:if>
			
			<input id="site_useYn_1" name="site_useYn" type="radio" value="1" <c:if test="${site_useYn == 1 }">checked="checked"</c:if> />
			<label for="site_useYn_1">사용</label>
			<input id="site_useYn_0" name="site_useYn" type="radio" value="0" <c:if test="${site_useYn == 0 }">checked="checked"</c:if> />
			<label for="site_useYn_0">중지</label>
			
			<input id="site_useYn_9" name="site_useYn" type="radio" value="9" <c:if test="${site_useYn == 9 }">checked="checked"</c:if> />
			<label for="site_useYn_9">폐쇄</label>
		</dd>
		<dt>접속 IP 검사</dt>
		<dd>
			<c:set var="site_accessIpYn" value="0" />
			<c:if test="${vo != null }"><c:set var="site_accessIpYn" value="${vo.site_accessIpYn}" /></c:if>
			
			<input id="site_accessIpYn_1" name="site_accessIpYn" type="radio" value="1" <c:if test="${site_accessIpYn == 1 }">checked="checked"</c:if> />
			<label for="site_accessIpYn_1">사용</label>
			<input id="site_accessIpYn_0" name="site_accessIpYn" type="radio" value="0" <c:if test="${site_accessIpYn == 0 }">checked="checked"</c:if> />
			<label for="site_accessIpYn_0">사용안함</label>
		</dd>
		<dt>사이트 구분</dt>
		<dd>
			<c:set var="site_se" value="site" />
			<c:if test="${vo != null }"><c:set var="site_se" value="${vo.site_se}" /></c:if>
			
			<input id="site_se_0" name="site_se" type="radio" value="main" <c:if test="${site_se == 'main' }">checked="checked"</c:if> />
			<label for="site_se_0">대표</label>
			
			<input id="site_se_1" name="site_se" type="radio" value="dept" <c:if test="${site_se == 'dept' }">checked="checked"</c:if> />
			<label for="site_se_1">부서</label>
			
			<input id="site_se_2" name="site_se" type="radio" value="dong" <c:if test="${site_se == 'dong' }">checked="checked"</c:if> />
			<label for="site_se_2">읍면동</label>
			
			<input id="site_se_3" name="site_se" type="radio" value="office" <c:if test="${site_se == 'office' }">checked="checked"</c:if> />
			<label for="site_se_3">직속기관/사업소</label>
			
			<input id="site_se_4" name="site_se" type="radio" value="site" <c:if test="${site_se == 'site' }">checked="checked"</c:if> />
			<label for="site_se_4">개별사이트</label>
		</dd>
		<dt><lable for="dept_nm">관리 부서</label></dt>
		<dd class="id">
			<input type="hidden" id="dept_id" name="dept_id" maxlength="32" value="<c:out value="${vo.dept_id }" />" />
			<input type="text" id="dept_nm" name="dept_nm" class="inp_txt" maxlength="64" value="<c:out value="${vo.dept_nm }" />" placeholder="담당부서" />
			<a href="" onclick="openDept()" class="btn" >부서 검색</a>
		</dd>
		<dt>등록일시</dt>
		<dd>
			<fmt:formatDate value="${vo.site_regDt}" pattern="yyyy-MM-dd HH:mm:ss" />
		</dd>
		<dt>수정일시</dt>
		<dd>
			<fmt:formatDate value="${vo.site_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />
		</dd>
	</dl>
</div>

</form>


<c:if test="${vo.site_useYn == 9 }">
	<div class="taC red b">사이트 패쇄 상태여서 정보를 수정 할수 없습니다</div>
</c:if>				
<div class="board_btn">
	<c:if test="${vo.site_useYn != 9 }">
	<a href="#self" onclick="saveData()" class="btn blue">저장</a>
	</c:if>
	<a href="<c:out value="${listLink }"/>">목록</a>
	<c:if test="${vo.site_useYn != 9 }">
		<c:if test="${vo != null}">
			<a href="#self" class="btn pink floatR" onclick="deleteData()">삭제</a>
		</c:if>
	</c:if>
</div>

<script>
function checkExistCode()
{
	var site_code = $("#site_code").val();
	
	if(site_code == "") {
		alert("사이트 코드를 입력하세요!");
		$("#site_code").focus();
        return false;
	}
	else
	{
		$.ajax({
			type:"post"
			, url: globalConfigJs.contextPath+"/SiteManager/cms/menu/site/checkExistCode.do"
			, dataType:"json"
			, data: {
				mnu_code : $("#mnu_code").val()
				, site_code : site_code
			}
			, success:function(data)
			{
				if(data.result == 0) {
					alert("사용 가능한 사이트 코드 입니다.");
				}else{
					alert("사용 불가능한 사이트 코드 입니다.");
					$("#site_code").val(null);
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

function saveData()
{
	if($("#site_code").val() == "") {
		alert("사이트 코드를 입력하여 주십시요!");
		$("#site_code").select();
		return false;
	}
	if($("#site_nm").val() == "") {
		alert("사이트 명을 입력하여 주십시요!");
		$("#site_nm").select();
		return false;
	}
	if($("#site_directory").val() == "") {
		alert("디렉토리를 입력하여 주십시요!");
		$("#site_directory").select();
		return false;
	}
	
	if(!$('input:radio[name=site_locale]').is(':checked')) {
		alert("언어를 선택하여 주십시요!");
		$("#site_locale").select();
		return false;
	}
	
	$("#siteFrm").submit();
}

function deleteData()
{
	if(confirm("현재 사이트를 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#siteFrm").submit();
	}
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
}
</script>
