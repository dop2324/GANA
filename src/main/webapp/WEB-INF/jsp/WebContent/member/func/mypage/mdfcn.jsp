<%@ include file="/library/lib_base.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- holdOn --%>
<script src="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.css" />

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
//<![CDATA[
	function daumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var roadAddr = data.roadAddress; // 도로명 주소 변수
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
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('zip').value = data.zonecode;
	            document.getElementById("addr").value = roadAddr;
	            
	            // 도로명주소가 없을경우
	            if(roadAddr == ''){
	            	document.getElementById("addr").value = data.jibunAddress;
	            }
	        }
	    }).open();
	}
//]]>
</script>

<div class="conts-area">
<form name="mdfcnForm" id="mdfcnForm" method="post" action="<c:url value='/WebContent/func/member/mdfcnProcess.do' />?mnu_code=${mnu_code}" onsubmit="return frm_vaildate(this);">
	<div class="conts-wrap">
		<div class="txt-box outline">
			<h4 class="outline-tit">개인정보 수정</h4>
			
			<p class="outline-txt">
				비밀번호 수정 및 다른 인증매체 로그인 관리는 마이페이지에서 진행해 주시기 바랍니다.<br />
				개명 및 전화번호 수정은 휴대폰 인증을 통해 진행 해 주세요.
			</p>
		</div>
		
		<div class="txt-box bg-white">
			<div class="box-cnt">
			
				<div class="box-sec">
					<div class="form-group">
						<div class="form-tit">
	                      <label for="membNm">이름</label>
	                    </div>
	                    <div class="form-conts">
	                    	<div class="input-group">
	                    		<input type="text" name="membNm" id="membNm" class="krds-input" readonly value="<c:out value='${sessionScope.MOA_LOGIN_INFO.membNm }' />" />
	                    		<button type="button" onclick="fn_nameCheck(); return false;" class="krds-btn large secondary">개명</button>
	                    	</div>
	                    </div>
	                    <p class="form-hint">개명으로 인한 이름변경시 '개명' 버튼을 클릭하여 진행 해주시기 바랍니다.</p>
					</div>
					
					<div class="form-group">
						<div class="form-tit">
	                      <label for="mbtlnum">연락처</label>
	                    </div>
	                    <div class="form-conts">
	                    	<div class="input-group">
	                    		<input type="text" name="mbtlnum" id="mbtlnum" class="krds-input" readonly value="<c:out value='${sessionScope.MOA_LOGIN_INFO.mbtlnum }' />" />
	                    		<button type="button" onclick="fn_nameCheck(); return false;" class="krds-btn large secondary">연락처인증</button>
	                    	</div>
	                    </div>
						<p class="form-hint">연락처는 가입시 인증된 명의로 개통된 휴대폰인증을 통해서만 변경하실 수 있습니다.</p>
					</div>
					
					<div class="form-group">
						<div class="form-tit">
	                      <label for="emlAddr">이메일주소</label>
	                    </div>
	                    <div class="form-conts">
                    		<input type="email" name="emlAddr" id="emlAddr" class="krds-input" value="<c:out value='${sessionScope.MOA_LOGIN_INFO.emlAddr }' />" />
	                    </div>
					</div>
					
					<div class="form-group">
						<div class="form-tit">
	                      <label for="addr">주소</label>
	                    </div>
	                    <div class="form-conts">
	                    	<div class="input-group">
	                    		<input type="text" name="zip" id="zip" class="krds-input" readonly value="<c:out value='${sessionScope.MOA_LOGIN_INFO.zip }' />" />
	                    		<button type="button" onclick="daumPostcode();return false;" class="krds-btn large secondary">주소검색</button>
	                    	</div>
	                    	<input type="text" name="addr" id="addr" class="krds-input" readonly value="<c:out value='${sessionScope.MOA_LOGIN_INFO.addr }' />" />
	                    	<input type="text" name="dtlAddr" id="dtlAddr" class="krds-input" value="<c:out value='${sessionScope.MOA_LOGIN_INFO.dtlAddr }' />" />
	                    </div>
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="page-btn-wrap both">
          <a href="<c:url value='/member/page.do' />?mnu_code=${mnu_code}" class="krds-btn xlarge tertiary">취소하기</a>
          <button type="submit" class="krds-btn xlarge primary">수정하기</button>
        </div>
	</div>
</form>
</div>

<script type="text/javascript">
//<![CDATA[
	function frm_vaildate(f){
		var emlAddr = f.emlAddr.value.trim();
		if (emlAddr !== "") {
			var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(emlAddr)) {
				alert("유효한 이메일 주소를 입력해 주세요.");
				f.emlAddr.focus();
				return false;
			}
		}
		
		return true;
	}
	
	// 휴대폰 본인인증
	function fn_nameCheck(){
		const url = '${pageContext.request.contextPath}/common/component/NICE/checkplus_main.jsp';
		window.open(url, '휴대폰 본인인증', 'width=700, height=460');
	}
	
	function doPageMove(){
		HoldOn.open({
			theme: 'sk-circle',
			message: '처리 중입니다. 잠시만 기다려주세요...'
		});
		
		$.ajax({
			type:"post"
			, url:"${pageContext.request.contextPath}/common/NICE/sessionInfo.do"
			, dataType:"json"
			, success:function(data){
				HoldOn.close();
				
				if(data.sName != ''){
					$('input[name=membNm]').val(data.sName);
				}
				
				if(data.sName != ''){
					$('input[name=mbtlnum]').val(data.sMobileNo);
				}
			}
			, error:function(jqXHR, textStatus, errorThrown){
				HoldOn.close();
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
				alert('통신중에 장애가 발생하였습니다. 잠시후 다시 시도해주시기 바랍니다.');
			}
		});
	}
	
	doPageMove();
//]]>
</script>
