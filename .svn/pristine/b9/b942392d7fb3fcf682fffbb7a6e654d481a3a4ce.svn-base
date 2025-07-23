package egovframework.dnworks.controller.webcontent.func.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.cmm.utl.MoaFuncUtil;
import egovframework.dnworks.func.cmm.utl.MoaMberComponent;
import egovframework.dnworks.func.cmm.utl.MoaSessionUtil;
import egovframework.dnworks.func.cmm.utl.SessionKey;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;

/***
 * 회원가입
 * step1 : 회원유형선택 (14세이상 내국인, 14세미만 내국인, 외국인)
 * step2 : 약관정보 동의
 * step3 : 본인인증 (휴대폰, 아이핀)
 * step3Proc : 회원가입유무 체크 CI
 * step4 : 개인정보 입력
 * step5 : 처리완료
 * */
@Controller
@RequestMapping("/WebContent/func/member")
public class MembSignUpController extends WebDefault {
	
	@Autowired
	MoaMberComponent moaMberComponent;
	
	@Autowired
	MembService membService;
	
	private static final String TAG = "MembSignUpController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");

	@RequestMapping("/signup.do")
	public String signup(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String step		= Util.nvl(request.getParameter("step"), "step01");
		
		try {
			long indexActionStartTime = System.currentTimeMillis();
			
			HashMap<String, Object> udp = new HashMap<String, Object>();
			udp.put("mnu_code"			, "");
			String queryString = super.getParameters(request, udp, true, true);
			
			model.addAttribute("mnu_code"		, mnu_code);
			model.addAttribute("queryString"	, queryString);
			
			// 사용자 로그인 상태일 경우 가입 안됨
			boolean sessionMoaUserIsLogin = MoaSessionUtil.isLogin(request);
			if(sessionMoaUserIsLogin) {
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "현재 로그인 중입니다.");
			}
			
			String pageLocation = null;		
			switch(step) {
				case "step01":
					this.step1(request, response, model);
					allowStep(session, "step02");
				break;
				case "step02":
					if (!isValidStep(request, response, "step02")) return null;
					this.step2(request, response, model);
					allowStep(session, "step03");
				break;
				case "step03":
					if (!isValidStep(request, response, "step03")) return null;
					this.step3(request, response, model);
					allowStep(session, "step04");
				break;
				case "step04":
					if (!isValidStep(request, response, "step04")) return null;
					this.step4(request, response, model);
				break;
			}
			pageLocation = step;
					
			long indexActionEndTime = System.currentTimeMillis();
			LOGGER.info("["+TAG+"] step {}, mnu_code: {}", step, mnu_code);
			LOGGER.info("["+TAG+"] ACTION 처리시간 : " + ((indexActionEndTime - indexActionStartTime) / 1000.0D));
			return String.format("%s/member/func/signup/%s", publicPath, pageLocation);
		}catch(Exception e) {
			
			LOGGER.info("["+TAG+"] step {}, mnu_code: {}", step, mnu_code);
			LOGGER.info("["+TAG+"] " + e.toString());
			e.printStackTrace();
			
			JavaScriptUtil.flushJsAlertAndLocation(response, "처리중에 오류가 발생했습니다.", "location.href='"+request.getContextPath()+"/member';");
			return null;
		}
	}
	
	public void step1(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
	}

	public void step2(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		// 파라미터 받기
		String membType = Util.nvl(request.getParameter("membType"));
		model.addAttribute("membType", membType);
	}
	
	public void step3(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		// 파라미터 받기
		String membType = Util.nvl(request.getParameter("membType"));
		model.addAttribute("membType", membType);
	}
	
	public void step4(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		HttpSession session = request.getSession(false);
		String returnUrl = request.getContextPath()+"/member";
		// NICE 본인인증정보 체크
		if(EgovStringUtil.isEmpty((String)session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()))) {
			JavaScriptUtil.flushJsAlertAndLocation(response, "인증정보가 없습니다.", returnUrl);
			return;
		}
		LOGGER.info("["+TAG+"] NICE_RESPONSENUMBER: {}", (String)session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()));
		
		// 파라미터 받기
		String membType = Util.nvl(request.getParameter("membType"));
		model.addAttribute("membType", membType);
		
		// 만 14세 미만의 경우 나이체크
		String birthday = Util.getSession(session, SessionKey.NICE_BIRTH.getKey());
		if("C".equals(membType)) {
			if(!MoaFuncUtil.isAge14Under(birthday)) {
				JavaScriptUtil.flushJsAlertAndLocation(response, "만 14세 이상은 어린이 회원으로 가입할 수 없습니다.", returnUrl);
				return;
			}
		}
		
		// 인증정보 중복검사 (CI, DI)
		if(!moaMberComponent.isDuplicateCi(Util.getSession(session, SessionKey.NICE_CONNINFO.getKey()))
			|| !moaMberComponent.isDuplicateDi(Util.getSession(session, SessionKey.NICE_DUPINFO.getKey()))
			) {
			
			JavaScriptUtil.flushJsAlertAndLocation(response, "해당 정보는 이미 가입되어있습니다.", returnUrl);
			return;
		}
		
		// 휴대폰번호 중복 체크
		String mbtlnum = (String) session.getAttribute(SessionKey.NICE_MOBILE.getKey());
		if(mbtlnum != null) {
			if(!moaMberComponent.isDuplicateMbtlnum(mbtlnum)) {
				JavaScriptUtil.flushJsAlertAndLocation(response, "해당 휴대폰 정보는 이미 가입되어있습니다.", returnUrl);
				return;
			}
		}
		
		// 인증정보 저장
		MoaSessionUtil.createSessionNicecAuth(request);
		model.addAttribute("rtnMap", (EgovMap)session.getAttribute("niceAuthMap"));
	}
	
	/**
	 * 저장 처리
	 * */
	@RequestMapping(value = "/signup_process.do", method = RequestMethod.POST)
	public String process(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session
			, @ModelAttribute MembInfoVO insertVO ) throws Exception {
		String returnUrl = request.getContextPath()+"/member";
				
		// NICE 본인인증정보 체크
		if(EgovStringUtil.isEmpty((String)session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()))) {
			JavaScriptUtil.flushJsAlertAndLocation(response, "인증정보가 없습니다.", returnUrl);
			return null;
		}
		LOGGER.info("["+TAG+"] NICE_RESPONSENUMBER: {}", (String)session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()));
		
		// 비밀번호 패턴체크
		if(!moaMberComponent.isVaildatePswd(insertVO.getPswd())) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.");
			return null;
		}
		// 비밀번호 암호화
		insertVO.setSalt(SHA256Util.getSalt());
		insertVO.setPswd(SHA256Util.getSHA256(insertVO.getPswd(), insertVO.getSalt()));
		
		// 인증정보 VO에 담기
		EgovMap niceAuthMap = (EgovMap)session.getAttribute("niceAuthMap");
		insertVO.setAuthMthd((String)niceAuthMap.get("sAuthType"));
		insertVO.setBrthdy((String)niceAuthMap.get("sBirthDate"));
		insertVO.setSexdstn(niceAuthMap.get("sGender").equals("1") ? "M" : "W");
		insertVO.setMembNm((String)niceAuthMap.get("sName"));
		insertVO.setDiVal((String)niceAuthMap.get("sDupInfo"));
		insertVO.setCiVal((String)niceAuthMap.get("sConnInfo"));
		insertVO.setMbtlnum((String)niceAuthMap.get("sMobileNo"));
				
		try {
			/**
			 * 중복회원 및 회원가입 가능여부 체크
			 * **/
			if (EgovDoubleSubmitHelper.checkAndSaveToken(request)) {
				
				// ID 유효성체크
				boolean result = moaMberComponent.isVaildateId(insertVO.getMembId());
				if(!result) {
					JavaScriptUtil.flushJsAlertAndHistoryBack(response, "해당 ID는 사용할 수 없습니다.");
					return null;
				}
				
				// ID중복체크
				if(!moaMberComponent.isDuplicateId(insertVO.getMembId())) {
					JavaScriptUtil.flushJsAlertAndHistoryBack(response, "해당 ID는 사용할 수 없습니다.");
					return null;
				}
				
				boolean reuslt = this.membService.createDataMemb(request, insertVO);
				if(!reuslt) {
					LOGGER.error("["+insertVO.getMembId()+"] join fail.");
					JavaScriptUtil.flushJsAlertAndHistoryBack(response, "회원가입에 실패했습니다.");
					
					return null;
				}
				
				session.removeAttribute("niceAuthMap");
				MoaSessionUtil.removeSessionNiceAuth(request);
				JavaScriptUtil.flushJsAlertAndLocation(response, "회원가입이 완료되었습니다.", returnUrl);
			}
			
		} catch(Exception e) {
			LOGGER.error("["+TAG+"]["+insertVO.getMembId()+"]"+e.toString()); e.printStackTrace();
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "회원가입에 실패했습니다.");
		}
		return null;
	}
	
	/**
	 * ID 중복검사
	 * **/
	@RequestMapping(value="/checkExistID.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	@ResponseBody
	public String checkExistID(HttpServletRequest request, HttpSession session) throws Exception {
		String membId = Util.nvl(request.getParameter("membId"));
		
		JSONObject json = new JSONObject();
		json.put("result", true);
		json.put("msg", "");
		
		// ID 유효성 체크
		if(!moaMberComponent.isVaildateId(membId)) {
			json.put("result", false);
			json.put("msg", "유효한 ID가 아닙니다.");
			return json.toString();
		}
		
		// ID 중복 체크
		if(!moaMberComponent.isDuplicateId(membId)) {
			json.put("result", false);
			json.put("msg", "해당 ID를 사용할 수 없습니다.");
			return json.toString();
		}
		
		return json.toString();
	}
	
	private void allowStep(HttpSession session, String step) {
	    @SuppressWarnings("unchecked")
	    Set<String> allowedSteps = (Set<String>) session.getAttribute("SIGNUP_ALLOWED_STEPS");
	    if (allowedSteps == null) allowedSteps = new HashSet<>();
	    allowedSteps.add(step);
	    session.setAttribute("SIGNUP_ALLOWED_STEPS", allowedSteps);
	}
	
	private boolean isValidStep(HttpServletRequest request, HttpServletResponse response, String expectedStep) throws IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null) return false;

	    @SuppressWarnings("unchecked")
	    Set<String> allowedSteps = (Set<String>) session.getAttribute("SIGNUP_ALLOWED_STEPS");
	    if (allowedSteps == null || !allowedSteps.contains(expectedStep)) {
	        JavaScriptUtil.flushJsAlertAndLocation(response, "잘못된 접근입니다.", request.getContextPath() + "/member");
	        return false;
	    }
	    return true;
	}

}
