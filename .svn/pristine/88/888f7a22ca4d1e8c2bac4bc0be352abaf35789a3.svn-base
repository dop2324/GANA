package egovframework.dnworks.func.cmm;

import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.SessionKey;

@Controller
@RequestMapping(value="/common/NICE")
public class NiceSessionController extends WebDefault {

	@RequestMapping(value="/sessionInfo.do")
	@ResponseBody
	public EgovMap sessionInfo(HttpSession session) throws Exception {
		EgovMap result = new EgovMap();
		
		result.put("sName", Util.getSession(session, SessionKey.NICE_NAME.getKey(), ""));
		result.put("sBirthDate", Util.getSession(session, SessionKey.NICE_BIRTH.getKey(), ""));
		result.put("sGender", Util.getSession(session, SessionKey.NICE_GENDER.getKey(), ""));
		result.put("sMobileNo", Util.getSession(session, SessionKey.NICE_MOBILE.getKey(), ""));
		
		return result;
	}
	
}
