<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<form id="menuFrm" name="menuFrm" method="post" action="<c:url value="${managerDir }/cms/menu/menu_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="site_code"			name="site_code"		value="<c:out value="${site_code }" />" />
<input type="hidden" id="mnu_code"			name="mnu_code"			value="<c:out value="${mnu_code }" />" />

<input type="hidden" id="parm_mnuUprCode"	name="parm_mnuUprCode" 	value="<c:out value="${vo.mnu_uprCode }" />" />
<input type="hidden" id="returnUrl" 		name="returnUrl" 		value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"		name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"				name="cmd" 				value="<c:out value="${cmd}" />"/>
<input type="hidden" id="updn"				name="updn" />


<div class="form_style">
	<dl>
		<dt><label for="parm_mnuCode">메뉴코드</label></dt>
		<dd <c:if test="${vo.mnu_code == ''}">class="id"</c:if>>
			<input type="text" id="parm_mnuCode" name="parm_mnuCode" class="engNumOnly" maxlength="64" <c:if test="${vo.mnu_code != ''}">readonly="readonly"</c:if> value="<c:out value="${vo.mnu_code }" />" placeholder="메뉴코드" />
			
			<c:if test="${vo.mnu_code == ''}">
				<a href="#self" class="btn" onclick="checkExistCode()">중복확인</a> 
				<p>
					<strong>하위 Max 메뉴 코드 : </strong><c:out value="${vo.max_mnuCode }" />
					<strong style="margin-left:15rem;">현재 메뉴 코드 : </strong><c:out value="${vo.mnu_uprCode }" />
				</p>
			</c:if>
		</dd>
		<dt><label for="mnu_nm">메뉴명</label></dt>
		<dd><input type="text" id="mnu_nm" name="mnu_nm" style="ime-mode:active" maxlength="64" value="<c:out value="${vo.mnu_nm }" />" placeholder="메뉴명" /></dd>
		<dt><label for="mnu_nmKr">한글 메뉴명</label></dt>
		<dd><input type="text" id="mnu_nmKr" name="mnu_nmKr" style="ime-mode:active" maxlength="64" value="<c:out value="${vo.mnu_nmKr }" />" placeholder="한글 메뉴명" /></dd>
		<dt><label for="mnu_desc">메뉴 설명</label></dt>
		<dd><input type="text" id="mnu_desc" name="mnu_desc" style="ime-mode:active" maxlength="128" value="<c:out value="${vo.mnu_desc }" />" placeholder="메뉴 설명" /></dd>
		<dt><label for="mnu_ty">형식</label></dt>
		<dd>
			<select title="페이지 형식 선택" name="mnu_ty" id="mnu_ty">
				<option value="">선택</option>
				<option value="root"		<c:if test="${vo.mnu_ty == 'root' }">selected="selected"</c:if>		>Root</option>
				<option value="menu"		<c:if test="${vo.mnu_ty == 'menu' }">selected="selected"</c:if>>메뉴</option>
				<option value="menu+page"	<c:if test="${vo.mnu_ty == 'menu+page' }">selected="selected"</c:if>>menu+page</option>
				<option value="menu+program"<c:if test="${vo.mnu_ty == 'menu+program' }">selected="selected"</c:if>>menu+program</option>
				<option value="page"		<c:if test="${vo.mnu_ty == 'page' }">selected="selected"</c:if>>Page</option>
				<option value="page+program"<c:if test="${vo.mnu_ty == 'page+program' }">selected="selected"</c:if>>Page+program</option>
				<option value="board"		<c:if test="${vo.mnu_ty == 'board' }">selected="selected"</c:if>>게시판</option>
				<option value="page+board"	<c:if test="${vo.mnu_ty == 'page+board' }">selected="selected"</c:if>>Page+게시판</option>
				<%--
				<option value="saeolboard"	<c:if test="${vo.mnu_ty == 'saeolboard' }">selected="selected"</c:if>>새올게시판</option>
				--%>
				<option value="program"		<c:if test="${vo.mnu_ty == 'program' }">selected="selected"</c:if>>Program</option>
				<option value="link"		<c:if test="${vo.mnu_ty == 'link' }">selected="selected"</c:if>>Link</option>
				<%--
				<option value="innerLink"	<c:if test="${vo.mnu_ty == 'innerLink' }">selected="selected"</c:if>>innerLink</option>
				<option value="outerLink"	<c:if test="${vo.mnu_ty == 'outerLink' }">selected="selected"</c:if>>outerLink</option>
				<option value="shareBoard"	<c:if test="${vo.mnu_ty == 'shareBoard' }">selected="selected"</c:if>>shareBoard</option>
				--%>
			</select>
		</dd>
		<dt>메뉴 타겟</dt>
		<dd>
			<input id="mnu_target0" name="mnu_target" class="inp_rad" type="radio" value="_self" <c:if test="${vo.mnu_target == '_self' }">checked="checked"</c:if> />
			<label for="mnu_target0">현재창 [target='_self']</label>
			<input id="mnu_target1" name="mnu_target" class="inp_rad" type="radio" value="_blank" <c:if test="${vo.mnu_target == '_blank' }">checked="checked"</c:if> />
			<label for="mnu_target1">새창 [target='_blank']</label>
		</dd>
		<dt><label for="mnu_linkUrl">파일경로</label></dt>
		<dd>
			<input type="text" id="mnu_linkUrl" name="mnu_linkUrl" class="inp_txt wp80" maxlength="256" style="ime-mode:disabled" value="${vo.mnu_linkUrl }" placeholder="page.do OR cms기능 경로 (/cms/**/*.do)" />
		</dd>
		<dt><label for="mnu_param">메뉴 파라메터</label></dt>
		<dd>
			<input type="text" id="mnu_param" name="mnu_param" class="inp_txt wp80" maxlength="256"  value="<c:out value="${vo.mnu_param }" />" placeholder="mnu_code=메뉴코드&param=value&param=value" />
		</dd>
	<%--
		<dt>리다이렉트URL</dt>
		<dd>
			
		</dd>
		--%>
		<dt>레벨</dt>
		<dd>
			<c:out value="${vo.mnu_level }" />
		</dd>
		<dt>정렬순서</dt>
		<dd>
			<c:out value="${vo.mnu_sort }" />
		</dd>
		<dt>개인정보 포함</dt>
		<dd>
			<input id="mnu_privacy" name="mnu_privacy" type="checkbox" value="Y" class="inp_rad" <c:if test="${vo.mnu_privacy =='Y' }">checked="checked"</c:if> />
			<label for="mnu_privacy">포함</label>
		</dd>
		<%--
		<dt>키보드보안</dt>
		<dd>
			<input id="mnu_secKybdYn" name="mnu_secKybdYn" type="checkbox" value="1" class="inp_rad" <c:if test="${vo.mnu_secKybdYn == 1 }">checked="checked"</c:if> />
			<label for="mnu_secKybdYn">사용</label>
		</dd>
			--%>
		<dt>GNB 보이기 여부</dt>
		<dd>
			<input id="mnu_visibleGnb1" name="mnu_visibleGnb" class="inp_rad" type="radio" value="1" <c:if test="${vo.mnu_visibleGnb == 1 }">checked="checked"</c:if>>
			<label for="mnu_visibleGnb1">보임</label>
			<input id="mnu_visibleGnb0" name="mnu_visibleGnb" class="inp_rad" type="radio" value="0" <c:if test="${vo.mnu_visibleGnb == 0 }">checked="checked"</c:if>>
			<label for="mnu_visibleGnb0">숨김</label>
		</dd>
		<dt>SNB 보이기 여부</dt>
		<dd>
			<input id="mnu_visibleSnb1" name="mnu_visibleSnb" class="inp_rad" type="radio" value="1" <c:if test="${vo.mnu_visibleSnb == 1 }">checked="checked"</c:if>>
			<label for="mnu_visibleSnb1">보임</label>
			<input id="mnu_visibleSnb0" name="mnu_visibleSnb" class="inp_rad" type="radio" value="0" <c:if test="${vo.mnu_visibleSnb == 0 }">checked="checked"</c:if>>
			<label for="mnu_visibleSnb0">숨김</label>
		</dd>
		<dt>TAB 보이기 여부</dt>
		<dd>
			<c:set var="mnu_visibleTab" value="0" />
			<c:if test="${vo!= null }"><c:set var="mnu_visibleTab" value="${vo.mnu_visibleTab}" /></c:if>
			<input id="mnu_visibleTab1" name="mnu_visibleTab" class="inp_rad" type="radio" value="1" <c:if test="${mnu_visibleTab == 1 }">checked="checked"</c:if>>
			<label for="mnu_visibleTab1">보임</label>
			<input id="mnu_visibleTab0" name="mnu_visibleTab" class="inp_rad" type="radio" value="0" <c:if test="${mnu_visibleTab == 0 }">checked="checked"</c:if>>
			<label for="mnu_visibleTab0">숨김</label>
		</dd>
		<dt>인증후 보이기 여부</dt>
		<dd>
			<input id="mnu_visibleAuth1" name="mnu_visibleAuth" class="inp_rad" type="radio" value="1" <c:if test="${vo.mnu_visibleAuth == 1}">checked="checked"</c:if>>
			<label for="mnu_visibleAuth1">인증사용자만 보기</label>
			<input id="mnu_visibleAuth0" name="mnu_visibleAuth" class="inp_rad" type="radio" value="0" <c:if test="${vo.mnu_visibleAuth == 0}">checked="checked"</c:if>>
			<label for="mnu_visibleAuth0">미인증사용자도 보기</label>
		</dd>
		<dt>사용유무</dt>
		<dd>
			<input id="mnu_sttus1" name="mnu_sttus" type="radio" value="1" class="inp_rad" <c:if test="${vo.mnu_sttus == 1 }">checked="checked"</c:if> />
			<label for="mnu_sttus1">사용</label>
			<input id="mnu_sttus0" name="mnu_sttus" type="radio" value="0" class="inp_rad" <c:if test="${vo.mnu_sttus == 0 }">checked="checked"</c:if> />
			<label for="mnu_sttus0">중지</label>
		</dd>
		<dt>담당부서</dt>
		<dd class="grow">
			<input type="hidden" id="mnu_deptId" name="mnu_deptId" maxlength="64" value="<c:out value="${vo.mnu_deptId }" />" />
			<input type="hidden" id="mnu_uptEmpCd" name="mnu_uptEmpCd" maxlength="32" value="<c:out value="${vo.mnu_uptEmpCd }" />" />
			<input type="text" id="mnu_deptNm" name="mnu_deptNm" class="inp_txt" style="width:33%;" maxlength="64" value="<c:out value="${vo.mnu_deptNm }" />" placeholder="담당부서" />
			<a href="#self" class="btn" onclick="openDept()">담당자 검색</a>
			
			<div>
				<input type="checkbox" class="inp_chk" id="lowApply" name="lowApply" value="1"><label for="lowApply">하위적용</label>
			</div>
			<div>
				<input type="text" id="mnu_staffNm" name="mnu_staffNm" style="width:33%;" maxlength="64" value="<c:out value="${vo.mnu_staffNm }" />" placeholder="담당직원 " />
				<input type="text" id="mnu_staffTelno" name="mnu_staffTelno"  style="width:33%;" maxlength="64" value="<c:out value="${vo.mnu_staffTelno }" />" placeholder="담당직원 전화번호 " />
				<input type="text" id="mnu_staffEml" name="mnu_staffEml" style="width:33%;" maxlength="128" value="<c:out value="${vo.mnu_staffEml }" />" placeholder="담당직원 메일주소 " />
			</div>
		</dd>
		<dt>CCL</dt>
		<dd>
			<input id="mnu_cclTy1" name="mnu_cclTy" type="radio" value="1" class="inp_rad" <c:if test="${vo.mnu_cclTy == 1 }">checked="checked"</c:if> />
			<label for="mnu_cclTy1">사용</label>
			<input id="mnu_cclTy0" name="mnu_cclTy" type="radio" value="0" class="inp_rad" <c:if test="${vo.mnu_cclTy == 0 }">checked="checked"</c:if> />
			<label for="mnu_cclTy0">중지</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="mnu_cclGrade1" name="mnu_cclGrade" type="radio" value="1" class="inp_rad" <c:if test="${vo.mnu_cclGrade == 1 }">checked="checked"</c:if> />
			<label for="mnu_cclGrade1">1유형</label>
			<input id="mnu_cclGrade2" name="mnu_cclGrade" type="radio" value="2" class="inp_rad" <c:if test="${vo.mnu_cclGrade == 2 }">checked="checked"</c:if> />
			<label for="mnu_cclGrade2">2유형</label>
			<input id="mnu_cclGrade3" name="mnu_cclGrade" type="radio" value="3" class="inp_rad" <c:if test="${vo.mnu_cclGrade == 3 }">checked="checked"</c:if> />
			<label for="mnu_cclGrade3">3유형</label>
			<input id="mnu_cclGrade4" name="mnu_cclGrade" type="radio" value="4" class="inp_rad" <c:if test="${vo.mnu_cclGrade == 4 }">checked="checked"</c:if> />
			<label for="mnu_cclGrade4">4유형</label>

		</dd>
		<%--
		<dt>업데이트 주기</dt>
		<dd>
			<input type="text" id="mnu_uptCycl" name="mnu_uptCycl" class="inp_txt numberOnly wp10" maxlength="3" value="<c:out value="${vo.mnu_uptCycl }" />" />
			<select id="mnu_uptCyclUnit" name="mnu_uptCyclUnit" class="wp10">
				<option value="">단위</option>
				<option value="D">일</option>
				<option value="W">주</option>
				<option value="M">월</option>
				<option value="Q">분기</option>
				<option value="Y">년</option>
			</select>
		</dd>
		--%>
	<c:if test="${vo != null}">
		<dt>등록 ID /일시</dt>
		<dd><c:out value="${vo.mnu_regID }" /> / <fmt:formatDate value="${vo.mnu_regDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
		<dt>수정 ID / 일시</dt>
		<dd><c:out value="${vo.mnu_mdfcnID }" /> / <fmt:formatDate value="${vo.mnu_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
	</c:if>
	
	</dl>
</div>
</form>

<div class="board_btn">
	<a href="#self" class="btn blue" onclick="saveData()">저장</a>
	
	<c:if test="${vo != null}">
		<a href="#self" onclick="setOrder('UP')" >▲</a>
		<a href="#self" onclick="setOrder('DN')" >▼</a>
	</c:if>
	
	<c:if test="${vo != null}">
		<a href="#self" class="btn pink floatR" onclick="deleteData()" <c:if test="${vo != null}">disabled="disabled"</c:if> >삭제</a>
	</c:if>
</div>

<script>
$(function(){
	$("#mnu_nm").keyup(function() {
		$('#mnu_desc').val($(this).val());
		$('#mnu_nmKr').val($(this).val());
	});

});

function checkExistCode()
{
	var parm_mnuCode = $("#parm_mnuCode").val();
	
	if(parm_mnuCode == "") {
		alert("메뉴 코드를 입력하세요!");
		$("#parm_mnuCode").focus();
        return false;
	}
	else
	{
		$.ajax({
			type:"post"
			, url: globalConfigJs.contextPath + "/SiteManager/cms/menu/checkExistCode.do"
			, dataType:"json"
			, data: {
				mnu_code : $("#mnu_code").val()
				, parm_mnuCode : parm_mnuCode
			}
			, success:function(data)
			{
				if(data.result == 0) {
					alert("사용 가능한 메뉴 코드 입니다.");
				}else{
					alert("사용 불가능한 메뉴 코드 입니다.");
					$("#parm_mnuCode").val(null);
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
	if($("#parm_mnuCode").val() == "") {
		alert("메뉴코드를 입력하여 주십시요!");
		$("#parm_mnuCode").select();
		return false;
	}
	if($("#mnu_nm").val() == "") {
		alert("메뉴명을 입력하여 주십시요!");
		$("#mnu_nm").select();
		return false;
	}
	if($("#mnu_ty").val() == "") {
		alert("메뉴형식을 입력하여 주십시요!");
		$("#mnu_ty").select();
		return false;
	}
	
	$("#menuFrm").submit();
}

function deleteData()
{
	if($("#parm_mnuCode").val() == "") {
		alert("삭제할 메뉴를 선택하세요");
		return false;
	}

	if(confirm("현재 메뉴를 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#menuFrm").submit();
	}
}

function setOrder(updn)
{
	if($("#parm_mnuCode").val() == "") {
		alert("순위 조정할 메뉴를 선택하세요");
		return false;
	}
	$("#updn").val(updn);
	$("#cmd").val(64);
	$("#menuFrm").submit();
}

function openDept()
{
	var popupLink = "<c:out value="${pageContext.request.contextPath}${managerDir }/cms/org/deptEmp.do?mnu_code=${mnu_code}" escapeXml="false" />";
	window.open(popupLink, "popDeptEmp", "width=1200,height=600,resizable=yes,scrollbars=yes");
}

function setDeptEmp(dept_id, dept_nm, emp_id, emp_nm, emp_telno, emp_moblphon, emp_eml)
{
	$("#mnu_deptId").val(dept_id);
	$("#mnu_deptNm").val(dept_nm);
	
	$("#mnu_uptEmpCd").val(emp_id);	
	$("#mnu_staffNm").val(emp_nm);
	$("#mnu_staffTelno").val(emp_telno);
	$("#mnu_staffEml").val(emp_eml);
}
</script>
