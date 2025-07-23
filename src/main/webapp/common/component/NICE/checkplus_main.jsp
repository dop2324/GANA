<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@page import="egovframework.dnworks.func.cmm.utl.MoaSessionUtil"%>
<%
	// cache 초기화
	response.setHeader("Pragma", 		"no-cache" );
	response.setDateHeader("Expires", 	0);
	response.setHeader("Pragma", 		"no-store");
	response.setHeader("Cache-Control", "no-cache" );
	
	//본인인증 관련 세션값 초기화
	MoaSessionUtil.removeSessionNiceAuth(request);
	
	//사용자 정의 파라미터
	
	// 안심본인인증 설정파일
	String NICE_PROPERTIES_PATH = EgovProperties.getPathProperty("Globals.CheckplusConfPath");
	
	// 안심본인인증 구동
	NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	
	//NICE로부터 부여받은 사이트 코드
	String sSiteCode = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sSiteCode");
	
	//NICE로부터 부여받은 사이트 패스워드
	String sSitePassword = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sSitePassword");
	
	String sRequestNumber = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sRequestNumber");
	
	//해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	session.setAttribute("REQ_SEQ" , sRequestNumber);
	
	//없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
	String sAuthType = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sAuthType");
	
	//Y : 취소버튼 있음 / N : 취소버튼 없음
	String popgubun = "N";
	
	// 없으면 기본 웹페이지 / Mobile : 모바일페이지
	String customize = "";
	
	// 서버 defautl url
	String returnDefaultUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	
	// 리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
	String sReturnUrl 	= returnDefaultUrl + EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sReturnUrl");     // 성공시 이동될 URL
	String sErrorUrl	= returnDefaultUrl + EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sErrorUrl");	   	// 실패시 이동될 URL
	
	// 입력될 plain 데이타를 만든다.
	String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                    "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                    "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                    "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                    "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                    "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
	
	String sMessage = "";
	String sEncData = "";
	
	int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	if( iReturn == 0 )
	{
	    sEncData = niceCheck.getCipherData();
	}
	else if( iReturn == -1)
	{
	    sMessage = "암호화 시스템 에러입니다.";
	}    
	else if( iReturn == -2)
	{
	    sMessage = "암호화 처리오류입니다.";
	}    
	else if( iReturn == -3)
	{
	    sMessage = "암호화 데이터 오류입니다.";
	}    
	else if( iReturn == -9)
	{
	    sMessage = "입력 데이터 오류입니다.";
	}    
	else
	{
	    sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	}
%>
<html>
<head>
	<title>NICE평가정보 - CheckPlus 안심본인인증 테스트</title>
	
	<script language='javascript'>
	window.name ="Parent_window";
	
	function fnPopup(){
		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.form_chk.submit();
	}
	window.onload = function() {
		fnPopup();
	}
	</script>
</head>
<body>
	<!-- 본인인증 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
	<form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" name="EncodeData" value="<%= sEncData %>">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	</form>
</body>
</html>
