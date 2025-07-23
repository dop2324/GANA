package egovframework.dnworks.controller.webcontent.func.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.listener.MoaSessionListener;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.cmm.utl.MoaMberComponent;
import egovframework.dnworks.func.cmm.utl.MoaSessionUtil;
import egovframework.dnworks.func.cmm.utl.SessionKey;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;

@Controller
@RequestMapping("/WebContent/func/member")
public class MembLoginController extends WebDefault {

	@Autowired
	MembService membService;
	
	@Autowired
	MoaMberComponent moaMberComponent;
	
	private static final String TAG = "MembLoginController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");
	
	private static final String MEMBER_CONFPATH = EgovProperties.getPathProperty("Globals.MemberConfPath");
	
	/**
	 * 로그인 페이지
	 * */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session) throws Exception {
		// 로그인상태 체크
		if(MoaSessionUtil.isLogin(request)) {
			String returnUrl = request.getContextPath()+"/member/page.do?mnu_code=" + EgovProperties.getProperty(MEMBER_CONFPATH, "moa.login.frst.mnuCode");
			JavaScriptUtil.flushJsAlertAndLocation(response, "", returnUrl);
			return null;
		}

		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));

		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("mnu_code"			, "");
		String queryString = super.getParameters(request, udp, true, true);
		
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("queryString"	, queryString);
		
		return String.format("%s/member/func/login/%s", publicPath, "login");
	}
	
	/**
	 * 로그아웃
	 * **/
	@RequestMapping("/logout.do")
	public String loout(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session) throws Exception {
		String useOrgId = Util.nvl(request.getParameter("useOrgId"), "USEORG_000000000000000003");
		String logoutUrl = request.getContextPath()+Util.nvl(request.getParameter("logoutUrl"), "/member");
		
		MembInfoVO membInfo = (MembInfoVO) session.getAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
		moaMberComponent.membLoginLog(null, membInfo.getMembId(), useOrgId, "N", "로그아웃", IPUtil.getXForwardedFor(request));
		MoaSessionUtil.removeSessionMoaMember(request);
		
		JavaScriptUtil.flushJsAlertAndLocation(response, "", logoutUrl);
		return null;
	}
	
	
	/**
	 * 로그인 처리
	 * **/
	@RequestMapping("/login_process.do")
	public String login_process(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session) throws Exception {
		String returnUrl	= request.getContextPath()+"/member/page.do?mnu_code=membMypage";
		
		String membId = Util.nvl(request.getParameter("membId"), "");
		String pswd = Util.nvl(request.getParameter("pswd"), "");
		String useOrgId = Util.nvl(request.getParameter("useOrgId"), "");
		
		if(EgovStringUtil.isBlank(membId) || EgovStringUtil.isBlank(pswd)) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인정보가 올바르지 않습니다.");
			return null;
		}
		
		try {
			
			// 모아회원정보 조회
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("membId", membId);
						
			MembInfoVO membInfo = new MembInfoVO();
			membInfo = this.membService.selectLogin(map);
			
			// 회원정보 있는지 체크
			if(membInfo == null) {
				moaMberComponent.membLoginLog(null, membId, useOrgId, "N", "회원정보없음", IPUtil.getXForwardedFor(request));
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인에 실패하였습니다.");
				return null;
			}
			
			// 회원상태(MEMB_STAT_CD) 체크 & 장기미사용자의 경우 로그인 성공
			if(!"P".equals(membInfo.getMembStatCd()) && !"S".equals(membInfo.getMembStatCd())) {
				moaMberComponent.membLoginLog(null, membId, useOrgId, "N", "MEMB_STATCD ("+membInfo.getMembStatCd()+")", IPUtil.getXForwardedFor(request));
				
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인에 실패하였습니다.");
				return null;
			}
			
			// 비밀번호 확인, 비밀번호 오류 로그인 실패시 실패카운터 1 증가
			String membPswd = membInfo.getPswd();
			String paramPswd = SHA256Util.getSHA256(pswd, membInfo.getSalt());
			int lgnFailCnt = membInfo.getLgnFailCnt() + 1;
			if(!paramPswd.equals(membPswd)) {
				
				moaMberComponent.membLoginLog(null, membId, useOrgId, "N", "비밀번호 불일치 ("+lgnFailCnt+"회 실패)", IPUtil.getXForwardedFor(request));
				
				// 비밀번호 실패 카운터
				this.membService.updateLgnFailCnt(map);
				
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인에 실패하였습니다.");
				return null;
			}
			
			/**
			 * 로그인 성공
			 * */
			moaMberComponent.membLoginLog(membInfo.getMembUnqId(), membId, useOrgId, "Y", "로그인", IPUtil.getXForwardedFor(request));
			// 비밀번호 실패카운터 초기화 및 마지막 로그인시간 업데이트
			this.membService.updateLgnSucc(map);
			
			// 세션생성
			MoaSessionUtil.createSessionMoaMember(request, membInfo);
			
			// 중복로그인 체크
			HttpSession existingSession = MoaSessionListener.sessionMap.get(membId);
			if (existingSession != null && existingSession != session) {
	            try {
	            	// 중복로그인 기존 세션 종료
	                existingSession.invalidate(); // 기존 세션 종료
	                
	                moaMberComponent.membLoginLog(membInfo.getMembUnqId(), membId, useOrgId, "N", "중복 로그인 세션삭제", IPUtil.getXForwardedFor(request));
	            } catch (IllegalStateException ignored) {}
	        }
			
			MoaSessionUtil.createSessionMoaMember(request, membInfo);
			MoaSessionListener.sessionMap.put(membId, session);
			
			JavaScriptUtil.flushJsAlertAndLocation(response, "", returnUrl);
			return null;
			
		}catch(Exception e) {
			LOGGER.debug("["+TAG+"] " + e.toString());
			moaMberComponent.membLoginLog(null, membId, useOrgId, "N", e.toString(), IPUtil.getXForwardedFor(request));
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "처리중 장애가 발생했습니다.");
			return null;
		}
	}
}
