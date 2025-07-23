<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="/common/__jodit__/jodit.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/common/__jodit__/jodit.min.js"></script>

<form id="pageFrm" name="pageFrm" method="post" action="<c:url value="${managerDir }/cms/menu/page/page_process.do" />">
<double-submit:preventer/>
<input type="hidden" id="mnu_code"		name="mnu_code"			value="<c:out value="${mnu_code }" />" />
<input type="hidden" id="parm_mnuCode"	name="parm_mnuCode"		value="<c:out value="${menuVo.mnu_code }" />" />
<input type="hidden" id="ref_code"		name="ref_code" 		value="<c:out value="${pageVo.ref_code}" />" />

<input type="hidden" id="returnUrl" 	name="returnUrl" 		value="<c:out value="${returnUrl }" />" />
<input type="hidden" id="queryString"	name="queryString"		value="<c:out value="${queryString }" escapeXml="false" />" />
<input type="hidden" id="cmd"			name="cmd" 				value="<c:out value="${cmd}" />"/>
<input type="hidden" id="saveDB" 		name="saveDB" />
<input type="hidden" id="saveJsp" 		name="saveJsp" />

<div class="form_style">
	<dl>
		<dt>페이지 목록</dt>
		<dd>
			<select id="page_sn" name="page_sn" onchange="selectPage()" size="5" >

			<c:forEach var="vo" items="${pageList}" varStatus="status">
				<c:set var="sttus" value="" />
				<c:set var="saveTy" value="" />
				<c:set var="optionStyle" value="" />

				<c:choose>
					<c:when test="${vo.page_sttus == 1 }"><c:set var="sttus" value="사용" /></c:when>
					<c:when test="${vo.page_sttus == -1 }"><c:set var="sttus" value="백업" /></c:when>
					<c:otherwise><c:set var="sttus" value="중지" /></c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${vo.page_saveTy == 1 }"><c:set var="saveTy" value="DB" /></c:when>
					<c:when test="${vo.page_saveTy == 0 }"><c:set var="saveTy" value="JSP" /></c:when>
				</c:choose>

				<c:if test="${pageVo.page_ttl != '' }"><c:out value="${pageVo.page_ttl }" /></c:if>

				<c:if test="${pageVo.page_sn == vo.page_sn }"><c:set var="optionStyle" value="background-color:#66b2ff" /></c:if>

				<fmt:formatNumber var="no" minIntegerDigits="4"  pattern="####" value="${vo.page_sn}" />
				<fmt:formatDate var="page_mdfcnDt" value="${vo.page_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" />

				<option value="${vo.page_sn }" style="padding:4px;${optionStyle}" <c:if test="${pageVo.page_sn == vo.page_sn }">selected="selected"</c:if> >
					<c:out value="${no}. [${sttus}] [${saveTy}] ${vo.page_ttl} (${vo.page_mdfcnID } / ${page_mdfcnDt }) " />
				</option>

			</c:forEach>
			</select>
		</dd>
		<dt>페이지 상태</dt>
		<dd>
			<c:choose>
				<c:when test="${pageVo.page_sttus == 1}"><span class="orange">사용중인 페이지</span></c:when>
				<c:otherwise>
					<a href="#self" class="btn" onclick="sttusPage()">사용</a>
				</c:otherwise>
			</c:choose>
		</dd>
		<dt>최종수정ID / 일시</dt>
		<dd><c:out value="${pageVo.page_mdfcnID }" /> / <fmt:formatDate value="${pageVo.page_mdfcnDt}" pattern="yyyy-MM-dd HH:mm:ss" /></dd>
		<dt>다른 페이지 참조</dt>
		<c:choose>
			<c:when test="${pageVo.ref_code == '' || pageVo.ref_code == null}">
				<dd>
					<a href="#self" class="btn" onclick="openRefPage()">페이지 찾기</a>
				</dd>
				<dt>참조선택 페이지</dt>
				<dd>
					<input id="ref_mnuNm" name="ref_mnuNm" type="text" class="inp_txt wp70" readonly="readonly"/>
				</dd>
			</c:when>
			<c:otherwise>
				<dd>
					<a href="#self" class="btn" onclick="removeRefPage()">참조 제거</a>
				</dd>
				<dt>참조 페이지</dt>
				<dd>
					<c:out value="${pageVo.ref_code}" /> : <c:out value="${pageVo.ref_mnuNm}" />
				</dd>
			</c:otherwise>
		</c:choose>
		<dt>페이지 제목</dt>
		<dd>
			<input type="text" id="page_ttl" name="page_ttl" class="inp_txt wp90" maxlength="128" value="<c:out value="${pageVo.page_ttl }" />" placeholder="페이지 제목" />
		</dd>
		<dt>페이지 내용</dt>
		<dd>
			<c:choose>
				<%-- //jsp 파일 사용 (page_saveTy 1:DB, 0:jsp) --%>
				<c:when test="${pageVo.page_saveTy == 0 }">
					<c:out value="${pageVo.page_jspPath }" escapeXml="false" />
				</c:when>
				<c:otherwise>
					<c:set var="page_cn" value="${pageVo.page_cn}" />
					
					<c:choose>
						<c:when test="${pageVo == null || (pageVo.page_saveTy == 1 && pageVo.ref_code == null) }">
							<textarea id="page_cn" name="page_cn" class="h500"><c:out value="${page_cn}" escapeXml="false" /></textarea>
							<script>
								Jodit.make('#page_cn', {
									height: 350
									, toolbarButtonSize: "small"
									, defaultMode: Jodit.MODE_SOURCE
									//, license: '%your license key%'
								});
							</script>
						</c:when>
						<c:when test="${pageVo.ref_code != '' || pageVo.ref_code != null}">
							<div id="viewPageCn" style="width:100%; border:1px solid #e1e1e1; overflow: auto;">
								<c:out value="${pageVo.page_cn }" escapeXml="false" />
							</div>
						</c:when>
						<c:otherwise>
							<textarea id="page_cn" name="page_cn"><c:out value="${page_cn}" escapeXml="false" /></textarea>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</dd>
	</dl>
</div>

</form>

<div class="board_btn">
	<c:if test="${pageVo.page_saveTy == 0}">
		<a href="#self" class="floatL" onclick="saveDB()">JSP->DB 저장</a>
	</c:if>
	<a href="#self" id="btnJsp" onclick="saveData(0, 1)">JSP 저장</a>
	<c:if test="${cmd == 4 || pageVo.page_saveTy == 1 }">
		<a href="#self" id="btnDB" class="btn blue" onclick="saveData(1, 0)">DB 저장</a>
	</c:if>
	<c:if test="${cmd != 4}">
		<a href="#self" class="btn pink floatR" onclick="deleteData()" <c:if test="${pageVo.page_sn == 0 }">disabled="disabled"</c:if>>삭제</a>
	</c:if>
</div>

<script>
function saveData(saveDB, saveJsp) {

	if($("#ref_code").val() == "") {
		var page_cn = $("#page_cn").val();

		if(page_cn == "") {
			//page_cn = CKEDITOR.instances['page_cn'].getData();
			page_cn = editor.getData();
		}
		if(page_cn == "") {
			alert("페이지 내용을 입력하십시요");
			$("#page_cn").select();
			return false;
		}
	}
	$("#saveDB").val(saveDB);
	$("#saveJsp").val(saveJsp);
	$("#pageFrm").submit();
}

function saveDB() {
	if(confirm("JSP ->DB 변경하시겠습니까?")) {
		$("#cmd").val(8);
		$("#pageFrm").submit();
	}
}

function deleteData() {
	if($("#page_sn").val() == "") {
		alert("삭제할 페이지를 선택하세요");
		return false;
	}

	if(confirm("현재 페이지 내용을 삭제 하시겠습니까?")) {
		$("#cmd").val(32);
		$("#pageFrm").submit();
	}
}

function selectPage() {
	location.href = "<c:out value="${returnUrl}?${queryString}" escapeXml="false" />page_sn="+$("#page_sn").val();
}

function sttusPage() {
	if($("#page_sn").val() == "") {
		alert("사용할 페이지를 선택하세요");
		return false;
	}

	$("#cmd").val(64);
	$("#pageFrm").submit();
}

function openRefPage() {
	window.open("<c:out value="${managerDir }" />/cms/menu/page/refPage.do?mnu_code="+$("#mnu_code").val(), "refPageFrameWin", "width=1100,height=750,resizable=yes,scrollbars=yes");
}

function selectRefPage(ref_code, ref_mnuNm)
{
	$("#ref_code").val(ref_code);
	$("#ref_mnuNm").val(ref_mnuNm);
}

function removeRefPage() {
	if(confirm("참조를 제거 하시겠습니까?")) {
		$("#ref_code").val(null);
		saveData(1,0);
	}
}
</script>
