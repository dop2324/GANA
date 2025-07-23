package egovframework.dnworks.func.cmm.utl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.memb.service.MembInfoVO;

public class MoaSessionUtil {
	
	/**
	 * 로그인 여부 체크
	 * */
	public static boolean isLogin(HttpServletRequest request) throws Exception {
		boolean result = false;
		
		try {
			
			if(request != null) {
				HttpSession session = request.getSession(false);
				if (session != null) {
					Object loginInfo = session.getAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
					if (loginInfo != null) {
						result = true;
					}
				}
			}
			
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 본인인증 Session 정보 저장
	 * */
	public static void createSessionNicecAuth(HttpServletRequest request) {
		EgovMap rtnMap = new EgovMap();
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			rtnMap.put("sRequestNumber"		, session.getAttribute(SessionKey.NICE_REQUESTSEQ.getKey()));
			rtnMap.put("sResponseNumber"	, session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()));
			rtnMap.put("sAuthType"			, session.getAttribute(SessionKey.NICE_AUTHTYPE.getKey()));
			rtnMap.put("sName"				, session.getAttribute(SessionKey.NICE_NAME.getKey()));
			rtnMap.put("sBirthDate"			, session.getAttribute(SessionKey.NICE_BIRTH.getKey()));
			rtnMap.put("sGender"			, session.getAttribute(SessionKey.NICE_GENDER.getKey()));
			rtnMap.put("sNationalInfo"		, session.getAttribute(SessionKey.NICE_NATIONALINFO.getKey()));
			rtnMap.put("sDupInfo"			, session.getAttribute(SessionKey.NICE_DUPINFO.getKey()));
			rtnMap.put("sConnInfo"			, session.getAttribute(SessionKey.NICE_CONNINFO.getKey()));
			rtnMap.put("sMobileNo"			, session.getAttribute(SessionKey.NICE_MOBILE.getKey()));
			
			session.setAttribute("niceAuthMap", rtnMap);
		}
	}
	
	/**
	 * 본인인증 Session 정보 삭제
	 * **/
	public static void removeSessionNiceAuth(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionKey.NICE_REQUESTSEQ.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_RESPONSENUMBER.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_AUTHTYPE.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_NAME.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_BIRTH.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_GENDER.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_NATIONALINFO.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_DUPINFO.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_CONNINFO.getKey());
		request.getSession().removeAttribute(SessionKey.NICE_MOBILE.getKey());
	}

	/**
	 * SESSION 정보 생성
	 * **/
	public static void createSessionMoaMember(HttpServletRequest request, MembInfoVO moaMember) {
		HttpSession session = request.getSession();
		
		int sessionTime = Util.nvl(EgovProperties.getProperty("Globals.sessionTime"), 10);
		session.setMaxInactiveInterval(sessionTime*60);
		
		session.setAttribute(SessionKey.MOA_LOGIN_INFO.getKey(), moaMember);
		session.setAttribute(SessionKey.MOA_MEMB_ID.getKey(), moaMember.getMembId());
		
		// CMS 로그인 체크 만들지 않음
		/****
		Util.setSession(session, "USR_ID"		, "MOA_"+moaMember.getMembId());
		Util.setSession(session, "USR_NM"		, moaMember.getMembNm());
		Util.setSession(session, "GRP_ID"		, EgovProperties.getProperty("Site.grp.GuestID"));
		Util.setSession(session, "GRP_NM"		, "모아회원");
		Util.setSession(session, "USR_MAIL"		, moaMember.getEmlAddr());
		Util.setSession(session, "USR_DI"		, moaMember.getDiVal());
		****/
		
	}
	
	/**
	 * SESSION 정보 삭제
	 * */
	public static void removeSessionMoaMember(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
		session.removeAttribute(SessionKey.MOA_MEMB_ID.getKey());
	}
	
}
