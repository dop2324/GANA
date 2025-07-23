package egovframework.dnworks.controller.cmm.auth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;

@Controller
@RequestMapping("/auth")
public class AuthController extends WebDefault
{
	@RequestMapping("/dev/auth_dev.do")
	public String auth_dev(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String authUrl = Util.nvl(request.getParameter("authUrl"), "");
		int returnTy 	= Util.nvl(request.getParameter("returnTy"), 0);
		
		model.addAttribute("authUrl"	, authUrl);
		model.addAttribute("returnTy", returnTy);
		
		return "auth/dev/auth_dev";
	}
	
	@RequestMapping("/dev/auth_devProc.do")
	public String auth_devProc(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String authUrl = Util.nvl(request.getParameter("authUrl"),"");
		int diTy 		= Util.nvl(request.getParameter("diTy"),0);
		int returnTy 	= Util.nvl(request.getParameter("returnTy"),0);

		String location = "opener.authNameEnd();self.close();";
		if(!authUrl.equals("")) {
			location = String.format("opener.location.href='%s';self.close();", authUrl);
		}
		
		String sName 			= "DevAuth";		// 성명
		String sDupInfo 		= "DI00111122223333444455556666777788889999";			// 중복가입 확인값 (DI_64 byte)
		String sConnInfo 		= "CI00111122223333444455556666777788889999";			// 중복가입 확인값 (DI_64 byte)
		String sBirthDate 		= "19001010";		// 생년월일(YYYYMMDD)
		String sGender 			= "";				// 성별
		String sNationalInfo 	= "";				// 내/외국인정보 (개발가이드 참조)
		String sMobileNo 		= "01062399987";	// 휴대폰번호
		String sMobileCo 		= "";				// 통신사
		
		if(diTy == 0) {
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			sConnInfo 	= "CI-"+dayTime.format(new Date(time));
			sDupInfo 	= "DI-"+dayTime.format(new Date(time));
			Random ran 	= new Random();
			sName 		= "테스트:"+ran.nextInt(1000)+1;
			
			
			java.util.Random generator = new java.util.Random();
			generator.setSeed(System.currentTimeMillis());

			String ramdomMobile = Util.nvl(generator.nextInt(100000000) % 100000000);
			sMobileNo 		= "000"+ramdomMobile;	// 휴대폰번호
		}
		
		session.setAttribute("USR_NM"		, sName);
		session.setAttribute("USR_DI"		, sDupInfo);
		session.setAttribute("USR_CI"		, sConnInfo);

		session.setAttribute("USR_BIRTH"	, sBirthDate);
		session.setAttribute("USR_PHONE"	, sMobileNo);
		session.setAttribute("AUTH_TY"		, "phone");
		
		//본인확인 이용자
		session.setAttribute("GRP_ID"		, "GRP_003_01");

		String alertMsg = String.format("%s\\n%s", sName, sDupInfo);
		
		model.addAttribute("message", "개발 테스트 본인확인 인증되었습니다 ");
		model.addAttribute("location", location);
		
		return "common/scriptAlert";
	}
}
