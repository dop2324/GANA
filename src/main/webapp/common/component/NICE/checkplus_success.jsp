<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@page import="egovframework.dnworks.func.cmm.utl.MoaSessionUtil"%>
<%@page import="egovframework.dnworks.func.cmm.utl.SessionKey"%>
<%

	//cache 초기화
	response.setHeader("Pragma", 		"no-cache" );
	response.setDateHeader("Expires", 	0);
	response.setHeader("Pragma", 		"no-store");
	response.setHeader("Cache-Control", "no-cache" );
	
	//사용자 정의 파라미터
	
	//안심본인인증 설정파일
	String NICE_PROPERTIES_PATH = EgovProperties.getPathProperty("Globals.CheckplusConfPath");

	NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

    String sSiteCode = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sSiteCode");
    String sSitePassword = EgovProperties.getProperty(NICE_PROPERTIES_PATH, "NICE.sSitePassword");

    String sCipherTime = "";			// 복호화한 시간
    String sRequestNumber = "";			// 요청 번호
    String sResponseNumber = "";		// 인증 고유번호
    String sAuthType = "";				// 인증 수단
    String sName = "";					// 성명
    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
    String sBirthDate = "";				// 생년월일(YYYYMMDD)
    String sGender = "";				// 성별
    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
	String sMobileNo = "";				// 휴대폰번호
	String sMobileCo = "";				// 통신사
    String sMessage = "";
    String sPlainData = "";
    
    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

    if( iReturn == 0 )
    {
        sPlainData = niceCheck.getPlainData();
        sCipherTime = niceCheck.getCipherDateTime();
        
        // 데이타를 추출합니다.
        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
        
        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
        sResponseNumber = (String)mapresult.get("RES_SEQ");
        sAuthType		= (String)mapresult.get("AUTH_TYPE");
        sName			= (String)mapresult.get("NAME");
		//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
        sBirthDate		= (String)mapresult.get("BIRTHDATE");
        sGender			= (String)mapresult.get("GENDER");
        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
        sDupInfo		= (String)mapresult.get("DI");
        sConnInfo		= (String)mapresult.get("CI");
        sMobileNo		= (String)mapresult.get("MOBILE_NO");
        sMobileCo		= (String)mapresult.get("MOBILE_CO");
        
        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
        if(!sRequestNumber.equals(session_sRequestNumber))
        {
            sMessage = "세션값 불일치 오류입니다.";
            sResponseNumber = "";
            sAuthType = "";
        }
        
        // 본인인증 세션저장
        if( sDupInfo!=null && !"".equals(sDupInfo) ){
			session.setAttribute(SessionKey.NICE_REQUESTSEQ.getKey()		, sRequestNumber);	// 본인확인 요청번호
			session.setAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()	, sResponseNumber);	// 본인확인 요청수신번호
			session.setAttribute(SessionKey.NICE_AUTHTYPE.getKey()			, "CheckPlus"); 		// 인증수단
			session.setAttribute(SessionKey.NICE_NAME.getKey()				, sName);			// 성명
			session.setAttribute(SessionKey.NICE_BIRTH.getKey()				, sBirthDate); 		// 생년월일
			session.setAttribute(SessionKey.NICE_GENDER.getKey()			, sGender); 		// 성별
			session.setAttribute(SessionKey.NICE_NATIONALINFO.getKey()		, sNationalInfo);	// 국적 정보 - 0:내국인 / 1:외국인
			session.setAttribute(SessionKey.NICE_DUPINFO.getKey()			, sDupInfo);		// 개인 회원의 중복가입여부 확인을 위해 사용되는 값
			session.setAttribute(SessionKey.NICE_CONNINFO.getKey()			, sConnInfo);		// 주민등록번호와 1:1로 매칭되는 고유키
			session.setAttribute(SessionKey.NICE_MOBILE.getKey()			, sMobileNo);		// 휴대폰번호
        }
    }
    else if( iReturn == -1)
    {
        sMessage = "복호화 시스템 오류입니다.";
    }    
    else if( iReturn == -4)
    {
        sMessage = "복호화 처리 오류입니다.";
    }    
    else if( iReturn == -5)
    {
        sMessage = "복호화 해쉬 오류입니다.";
    }    
    else if( iReturn == -6)
    {
        sMessage = "복호화 데이터 오류입니다.";
    }    
    else if( iReturn == -9)
    {
        sMessage = "입력 데이터 오류입니다.";
    }    
    else if( iReturn == -12)
    {
        sMessage = "사이트 패스워드 오류입니다.";
    }    
    else
    {
        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
    }

%>
<%!

	public String requestReplace (String paramValue, String gubun) {

        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
%>

<html>
<head>
    <title>NICE평가정보 - CheckPlus 안심본인인증 결과</title>
    <script type="text/javascript">
    	try{
    		window.opener.doPageMove();
    		self.close();
    	}catch(e){
    		alert(e);
    	}
    </script>
</head>
<body>
<%--
    <center>
    <p><p><p><p>
    본인인증이 완료 되었습니다.<br>
    <table border=1>
        <tr>
            <td>복호화한 시간</td>
            <td><%= sCipherTime %> (YYMMDDHHMMSS)</td>
        </tr>
        <tr>
            <td>요청 번호</td>
            <td><%= sRequestNumber %></td>
        </tr>            
        <tr>
            <td>NICE응답 번호</td>
            <td><%= sResponseNumber %></td>
        </tr>            
        <tr>
            <td>인증수단</td>
            <td><%= sAuthType %></td>
        </tr>
		<tr>
            <td>성명</td>
            <td><%= sName %></td>
        </tr>
		<tr>
            <td>중복가입 확인값(DI)</td>
            <td><%= sDupInfo %></td>
        </tr>
		<tr>
            <td>연계정보 확인값(CI)</td>
            <td><%= sConnInfo %></td>
        </tr>
		<tr>
            <td>생년월일(YYYYMMDD)</td>
            <td><%= sBirthDate %></td>
        </tr>
		<tr>
            <td>성별</td>
            <td><%= sGender %></td>
        </tr>
				<tr>
            <td>내/외국인정보</td>
            <td><%= sNationalInfo %></td>
        </tr>
        </tr>
			<td>휴대폰번호</td>
            <td><%= sMobileNo %></td>
        </tr>
		<tr>
			<td>통신사</td>
			<td><%= sMobileCo %></td>
        </tr>
		<tr>
			<td colspan="2">인증 후 결과값은 내부 설정에 따른 값만 리턴받으실 수 있습니다. <br>
			일부 결과값이 null로 리턴되는 경우 관리담당자 또는 계약부서(02-2122-4615)로 문의바랍니다.</td>
		</tr>
		</table><br><br>        
    <%= sMessage %><br>
    </center>
--%>
</body>
</html>