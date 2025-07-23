<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<form id="writeFrm" name="writeFrm" method="post" action="<c:url value='${managerDir}/func/festvl/process.do' />" onsubmit="return frm_validate(this);" enctype="multipart/form-data">
<double-submit:preventer/>
<input type="hidden" id="mnu_code" name="mnu_code" value="<c:out value='${mnu_code }' />" />
<input type="hidden" id="queryString" name="queryString" value="<c:out value='${queryString }' escapeXml='false' />" />
<input type="hidden" id="cmd" name="cmd" value="<c:out value='${cmd}' />"/>
<input type="hidden" id="fsvUnqId" name="fsvUnqId" value="<c:out value='${resultVO.fsvUnqId}' />"/>

<div class="board_wrap">
	<div class="board_write form_style">
		<dl>
			<dt><label for="ttl">축제 제목</label></dt>
			<dd>
				<input type="text" id="ttl" name="ttl" maxlength="200" required value="<c:out value='${resultVO.ttl}' />" placeholder="축제 제목을 입력하세요" />
			</dd>
			
			<dt><label for="plcNm">장소명</label></dt>
			<dd>
				<input type="text" id="plcNm" name="plcNm" maxlength="100" value="<c:out value='${resultVO.plcNm}' />" />
			</dd>

			<dt><label for="zip">주소정보</label></dt>
			<dd class="grow">
				<a href="#self" class="btn" onclick="daumPostcode()">주소검색</a>
				<input type="text" id="zip" name="zip" maxlength="8" class="inp_txt" value="<c:out value='${resultVO.zip}' />" />
				<div style="width:100%;">
					<input type="text" id="addr" name="addr" maxlength="1000" class="inp_txt" value="<c:out value='${resultVO.addr}' />" />
				</div>
				<div style="width:100%;">
					<input type="text" id="dtlAddr" name="dtlAddr" maxlength="1000" class="inp_txt" value="<c:out value='${resultVO.dtlAddr}' />" />
				</div>
			</dd>
			<dt><label for="festvlBgngYmd">축제 시작일자</label></dt>
			<dd>
				<input type="date" id="festvlBgngYmd" name="festvlBgngYmd" value="<c:out value='${resultVO.festvlBgngYmd}' />" />
			</dd>

			<dt><label for="festvlEndYmd">축제 종료일자</label></dt>
			<dd>
				<input type="date" id="festvlEndYmd" name="festvlEndYmd" value="<c:out value='${resultVO.festvlEndYmd}' />" />
			</dd>

			<dt><label for="festvlBgngHm">시작시간</label></dt>
			<dd>
				<input type="time" id="festvlBgngHm" name="festvlBgngHm" pattern="^([01]\d|2[0-3]):([0-5]\d)$" value="<c:out value='${resultVO.festvlBgngHm}' />" />
			</dd>

			<dt><label for="festvlEndHm">종료시간</label></dt>
			<dd>
				<input type="time" id="festvlEndHm" name="festvlEndHm" pattern="^([01]\d|2[0-3]):([0-5]\d)$" value="<c:out value='${resultVO.festvlEndHm}' />" />
			</dd>

			<dt><label for="inqEmlAddr">문의 이메일</label></dt>
			<dd>
				<input type="email" id="inqEmlAddr" name="inqEmlAddr" maxlength="100" value="<c:out value='${resultVO.inqEmlAddr}' />" />
			</dd>

			<dt><label for="inqTelno">문의 전화번호</label></dt>
			<dd>
				<input type="text" id="inqTelno" name="inqTelno" maxlength="30" value="<c:out value='${resultVO.inqTelno}' />" />
			</dd>

			<dt><label for="freemYn">무료 여부</label></dt>
			<dd>
				<input type="radio" name="freemYn" id="freemYn_Y" value="Y" required <c:if test="${resultVO.freemYn eq 'Y'}">checked</c:if> /> <label for="freemYn_Y">무료</label>
				<input type="radio" name="freemYn" id="freemYn_N" value="N" required <c:if test="${resultVO.freemYn eq 'N'}">checked</c:if> /> <label for="freemYn_N">유료</label>
			</dd>

			<dt><label for="vwGd">관람 등급</label></dt>
			<dd>
				<input type="text" id="vwGd" name="vwGd" maxlength="30" value="<c:out value='${resultVO.vwGd}' />" />
			</dd>

			<dt><label for="prgrmCn">행사 및 프로그램 소개</label></dt>
			<dd>
				<textarea id="prgrmCn" name="prgrmCn" rows="5" maxlength="4000" placeholder="프로그램 소개를 입력하세요">${resultVO.prgrmCn}</textarea>
				<script type="module">
					//editorConfig.js에서 CKEditor 설정 정보 불러오기
					import { editorConfig } from 'editorConfig';
					import { ClassicEditor } from 'ckeditor5';

					editorConfig.simpleUpload.uploadUrl = '${pageContext.request.contextPath}/common/func/ckupload.do';
	
					//editorConfig = 이전 포스팅에서 설정한 CKEditor의 설정입니다.
					ClassicEditor.create(document.querySelector('#prgrmCn'), editorConfig)
						.then( editor => {
							window.editor = editor;
							console.log('CKEditor 전송 정보 : ' + editor);
						})
						.catch(err => {
							console.log('발생 오류 : '+err);
						});
				</script>
			</dd>

			<dt><label for="hmpgUrl">관련 홈페이지 URL</label></dt>
			<dd>
				<input type="url" id="hmpgUrl" name="hmpgUrl" maxlength="300" value="<c:out value='${resultVO.hmpgUrl}' />" />
			</dd>

			<dt><label for="mapLo">지도 좌표</label></dt>
			<dd class="grow">
				<a href="#self" class="btn" onclick="getAddressSearch();">좌표추출</a>
				<input type="text" id="mapLo" name="mapLo" maxlength="50" value="<c:out value='${resultVO.mapLo}' />" placeholder="X좌표" />
				<input type="text" id="mapLa" name="mapLa" maxlength="50" value="<c:out value='${resultVO.mapLa}' />" placeholder="Y좌표" />
			</dd>

			<dt><label for="useYn">사용유무</label></dt>
			<dd>
				<input type="radio" name="useYn" id="useYn_Y" value="Y" required <c:if test="${resultVO.useYn eq 'Y'}">checked</c:if> /> <label for="useYn_Y">사용</label>
				<input type="radio" name="useYn" id="useYn_N" value="N" required <c:if test="${resultVO.useYn eq 'N'}">checked</c:if> /> <label for="useYn_N">미사용</label>
			</dd>

			<dt><label for="atchFileId">첨부이미지</label></dt>
			<dd class="file">
				<%-- 등록된 첨부파일 있을경우 --%>
				<c:if test="${not empty resultVO.atchFileId}">
					<c:import url="/common/fms/selectFileInfs.do" charEncoding="utf-8">
	                    <c:param name="param_atchFileId" value="${moa:encrypt(resultVO.atchFileId)}" />
	                    <c:param name="param_updateFlag" value="Y" />
	                    <c:param name="param_returnUrl" value="${pageContext.request.contextPath }/SiteManager/page.do?mnu_code=${mnu_code }&cmd=${cmd }&fsvUnqId=${resultVO.fsvUnqId }" />
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
</div>

<div class="board_btn">
	<button type="submit" class="btn blue">저장</button>
	<c:if test="${cmd eq '16' }">
	<a href="javascript:void(0);" class="btn pink" onclick="confirmDelete();">삭제</a>
	</c:if>
	<a href="<c:out value='${listLink}'/>">목록</a>
</div>

</form>

<script>
function daumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 주소 정보 세팅
			document.getElementById('zip').value = data.zonecode;
			document.getElementById('addr').value = data.roadAddress;

			// 상세주소 포커스
			document.getElementById('dtlAddr').focus();
		}
	}).open();
}

function getAddressSearch(){
	const addr = document.getElementById('addr').value + document.getElementById('dtlAddr').value;
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

function frm_validate(f) {
	const ttl = f.ttl.value.trim();
	if (ttl === "") {
		alert("제목은 필수 입력입니다.");
		f.ttl.focus();
		return false;
	}
	
	const prgrmCn = editor.getData();
	console.log(prgrmCn);
	
	return true;
}

function confirmDelete() {
	if (confirm("정말로 삭제하시겠습니까?")) {
		const form = document.getElementById("writeFrm");
		form.cmd.value = "32"; 
		form.submit();
	}
}
</script>
