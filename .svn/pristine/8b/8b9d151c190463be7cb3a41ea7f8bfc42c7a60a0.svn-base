<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div class="frm_wrap pr10">
	<form id="orgForm" name="orgForm" method="post" action="<c:url value="${managerDir }/func/org/info_process.do" />" enctype="multipart/form-data">
		<double-submit:preventer/>
		<input type="hidden" name="orgUnqId" value="<c:out value='${orgVo.orgUnqId}'/>">
		<input type="hidden" name="mnu_code" value="<c:out value='${mnu_code}'/>">
		<input type="hidden" name="cmd" value="<c:out value='${cmd}'/>">
		<input type="hidden" name="returnUrl" value="<c:out value='${returnUrl}'/>">
		<input type="hidden" name="queryString" value="<c:out value='${queryString}'/>">

		<div class="form_style">
			<dl>
				<dt><label for="orgNm">기관명 <span class="require">*</span></label></dt>
				<dd><input type="text" id="orgNm" name="orgNm" class="inp_txt" value="<c:out value='${orgVo.orgNm}'/>" required></dd>
		
				<dt><label for="zip">우편번호 <span class="require">*</span></label></dt>
				<dd>
					<input type="text" id="zip" name="zip" class="inp_txt" readonly value="<c:out value='${orgVo.zip}'/>" required>
					<a href="#self" class="btn" onclick="execDaumPostcode()">주소검색</a>
				</dd>
		
				<dt><label for="roadNmAddr">도로명 주소 <span class="require">*</span></label></dt>
				<dd><input type="text" id="roadNmAddr" name="roadNmAddr" class="inp_txt" readonly value="<c:out value='${orgVo.roadNmAddr}'/>" required></dd>
		
				<dt><label for="dtlAddr">상세 주소 <span class="require">*</span></label></dt>
				<dd><input type="text" id="dtlAddr" name="dtlAddr" class="inp_txt" value="<c:out value='${orgVo.dtlAddr}'/>" required></dd>
		
				<dt><label for="rprsntTelno">대표전화 <span class="require">*</span></label></dt>
				<dd><input type="text" id="rprsntTelno" name="rprsntTelno" class="inp_txt" value="<c:out value='${orgVo.rprsntTelno}'/>" required></dd>
		
				<dt><label for="smsSndngTelno">SMS 발신번호 <span class="require">*</span></label></dt>
				<dd><input type="text" id="smsSndngTelno" name="smsSndngTelno" class="inp_txt" value="<c:out value='${orgVo.smsSndngTelno}'/>" required></dd>
				
				<dt><label for="prgrmCn">기관 소개</label></dt>
				<dd>
					<textarea id="prgrmCn" name="prgrmCn" rows="5" maxlength="4000" placeholder="소개를 입력하세요">${orgVo.prgrmCn}</textarea>
				</dd>      
				
				<dt><label for="hmpgUrl">관련 홈페이지 URL</label></dt>
				<dd>
					<input type="url" id="hmpgUrl" name="hmpgUrl" maxlength="300" value="<c:out value='${orgVo.hmpgUrl}' />" />
				</dd>
	
				<dt><label for="mapLo">지도 좌표</label></dt>
				<dd class="grow">
					<a href="#self" class="btn" onclick="getAddressSearch();">좌표추출</a>
					<input type="text" id="mapLo" name="mapLo" maxlength="50" value="<c:out value='${orgVo.mapLo}' />" placeholder="X좌표" />
					<input type="text" id="mapLa" name="mapLa" maxlength="50" value="<c:out value='${orgVo.mapLa}' />" placeholder="Y좌표" />
				</dd>
		
		
				<dt>사용 여부 <span class="require">*</span></dt>
				<dd>
					<input id="useYnY" name="useYn" type="radio" value="Y" <c:if test="${orgVo.useYn eq 'Y'}">checked</c:if> class="inp_rad" required>
					<label for="useYnY">사용</label>
					<input id="useYnN" name="useYn" type="radio" value="N" <c:if test="${orgVo.useYn eq 'N'}">checked</c:if> class="inp_rad" required>
					<label for="useYnN">중지</label>
				</dd>
				
				<dt><label for="atchFileId">첨부이미지</label></dt>
				<dd class="file">
					<%-- 등록된 첨부파일 있을경우 --%>
					<c:if test="${not empty orgVo.atchFileId}">
						<c:import url="/common/fms/selectFileInfs.do" charEncoding="utf-8">
		                    <c:param name="param_atchFileId" value="${moa:encrypt(orgVo.atchFileId)}" />
		                    <c:param name="param_updateFlag" value="Y" />
		                    <c:param name="param_returnUrl" value="${pageContext.request.contextPath }/SiteManager/page.do?mnu_code=${mnu_code }&cmd=${cmd }&fsvUnqId=${orgVo.orgUnqId }" />
		                </c:import>
					</c:if>
					
					<c:import url="/EgovPageLink.do?link=${managerDir }/include/inc_fileinsert">
						<c:param name="posblAtchFileSize" value="10" />
						<c:param name="posblAtchFileNumber" value="${3 - atchFileCnt }" />
						<c:param name="allowedExtensions" value=".png.jpg.bmp" />
					</c:import>
				</dd>
			</dl>
		</div>
		

		<div class="board_btn">
			<a href="#self" class="btn blue" onclick="submitOrgForm()">저장</a>
			<a href="javascript:void(0);" class="btn pink" onclick="confirmDelete('<c:out value="${orgVo.orgUnqId}" />')">삭제</a>
			<a href="<c:out value='${listLink}'/>" class="btn">목록</a>
		</div>
	</form>
</div>

<script>
function execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			document.getElementById('zip').value = data.zonecode;
			document.getElementById('roadNmAddr').value = data.roadAddress;
			document.getElementById('dtlAddr').focus();
		}
	}).open();
}

function submitOrgForm() {
	if (!$("#orgNm").val()) {
		alert("기관명을 입력하세요.");
		$("#orgNm").focus();
		return false;
	}
	$("#orgForm").submit();
}

function confirmDelete(orgUnqId) {
	if (confirm("정말로 삭제하시겠습니까?")) {
		document.getElementById("orgForm").cmd.value = "32";
		document.getElementById("orgForm").submit();
	}
}

function getAddressSearch(){
	const addr = document.getElementById('roadNmAddr').value + document.getElementById('dtlAddr').value;
	if(addr == ''){
		alert('주소정보를 입력해주시기 바랍니다.');
		return false;
	}
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	geocoder.addressSearch(addr, function(result, status) {
		document.getElementById('mapLo').value =result[0].y;
		document.getElementById('mapLa').value =result[0].x; 
	});
}
</script>
