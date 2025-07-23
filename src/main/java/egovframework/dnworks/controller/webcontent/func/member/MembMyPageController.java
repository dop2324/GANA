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

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.cmm.utl.MoaSessionUtil;
import egovframework.dnworks.func.cmm.utl.SessionKey;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;

@Controller
@RequestMapping("/WebContent/func/member")
public class MembMyPageController extends WebDefault {

	@Autowired
	MembService membService;
	
	private static final String TAG = "MembMyPageController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");

	@RequestMapping("/mypage.do")
	public String mypage(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String funcId = Util.nvl(request.getParameter("funcId"), "main");
		
		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("mnu_code"			, "");
		udp.put("funcId"			, "");
		String queryString = super.getParameters(request, udp, true, true);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		model.addAttribute("mnu_code"		, mnu_code);
		
		String pageLocation = null;
		switch(funcId) {
			case "mdfcn":
				// 개인정보수정
				select(request, response, session, model);
				pageLocation = "mdfcn";
			break;
			case "pswdchg":
				// 비밀번호변경
				pageLocation = "pswdchg";
			break;
			case "wdrw":
				// 회원탈퇴
				pageLocation = "wdrw";
			break;
			case "main":
				pageLocation = "main";
			break;
		}
		return String.format("%s/member/func/mypage/%s", publicPath, pageLocation);
	}

	private void select(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
		MembInfoVO membInfo = (MembInfoVO)session.getAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
		if(membInfo == null) {
			JavaScriptUtil.flushJsAlertAndLocation(response, "로그인정보가 없습니다.", request.getContextPath()+"/member");
			return;
		}
		
		try {
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("membUnqId", membInfo.getMembUnqId());
		}catch(Exception e) {
			LOGGER.info("["+TAG+"] 요청ID {} Exception {}" , membInfo.getMembId() , e.toString());
		}
	}
	
	/**
	 * 개인정보수정 처리
	 * @param MembInfoVO searchVO 
	 * 이름, 전화번호는 session 정보로 받음
	 * */
	@RequestMapping("/mdfcnProcess.do")
	public String mdfcnProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		
		MembInfoVO membInfo = new MembInfoVO();
		membInfo = (MembInfoVO) session.getAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
		
		if(membInfo == null) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인정보가 없습니다. 두개 이상의 기기 또는 브라우저에서 중복 로그인시 로그인이 실패할 수 있습니다.");
			return null;
		}
		
		MembInfoVO updateVO = new MembInfoVO();
		updateVO.setMembUnqId(membInfo.getMembUnqId());
		
		try {
			// 개명 및 전화번호 변경
			String niceRequestSeq = Util.getSession(session, SessionKey.NICE_REQUESTSEQ.getKey(), "");
			if(!"".equals(niceRequestSeq)) {
				// 개명 및 전화번호 변경시 CI 값이 다를경우 계정양도불가에 의해 갱신안됨
				if(!membInfo.getCiVal().equals( Util.getSession(session, SessionKey.NICE_CONNINFO.getKey()) )) {
					MoaSessionUtil.removeSessionNiceAuth(request);
					JavaScriptUtil.flushJsAlertAndHistoryBack(response, "가입자와 다른 명의의 인증정보입니다.");
					return null;
				}
				
				// 이름갱신확인
				updateVO.setMembNm( Util.getSession(session, SessionKey.NICE_NAME.getKey()) );
				
				// 전화번호갱신 확인
				updateVO.setMbtlnum( Util.getSession(session, SessionKey.NICE_MOBILE.getKey()) );
				updateVO.setDiVal( Util.getSession(session, SessionKey.NICE_DUPINFO.getKey()) );
				updateVO.setAuthMthd( Util.getSession(session, SessionKey.NICE_AUTHTYPE.getKey()) );
			}
			
			Map <String, Object> map = new HashMap<String, Object>();
			map.put("membId", membInfo.getMembId());
						
			// 주소정보 갱신
			updateVO.setEmlAddr(request.getParameter("emlAddr"));
			updateVO.setZip(Util.nvl(request.getParameter("zip")));
			updateVO.setAddr(Util.nvl(request.getParameter("addr")));
			updateVO.setDtlAddr(Util.nvl(request.getParameter("dtlAddr")));
			
			this.membService.updateMembInfo(updateVO);
			
			// 인증정보 갱신
			MoaSessionUtil.removeSessionNiceAuth(request);
			MoaSessionUtil.removeSessionMoaMember(request);
			
			MembInfoVO result = this.membService.selectLogin(map);
			MoaSessionUtil.createSessionMoaMember(request, result);
			
			JavaScriptUtil.flushJsAlertAndLocation(response, "회원정보가 수정되었습니다.", request.getContextPath()+"/member/page.do?mnu_code="+mnu_code);
			return null;
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.info("["+TAG+"] 요청ID {} Exception {}" , membInfo.getMembId() , e.toString());
			JavaScriptUtil.flushJsAlertAndLocation(response, "회원정보가 수정에 실패했습니다.", request.getContextPath()+"/member/page.do?mnu_code="+mnu_code);
			return null;
		}
	}
	
	/**
	 * 개인정보수정 처리
	 * @param MembInfoVO searchVO 
	 * 이름, 전화번호는 session 정보로 받음
	 * */
	@RequestMapping("/pswdchgProcess.do")
	public String pswdchgProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		
		MembInfoVO membInfo = new MembInfoVO();
		membInfo = (MembInfoVO) session.getAttribute(SessionKey.MOA_LOGIN_INFO.getKey());
		
		if(membInfo == null) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "로그인정보가 없습니다. 두개 이상의 기기 또는 브라우저에서 중복 로그인시 로그인이 실패할 수 있습니다.");
			return null;
		}
		
		try {
			
			// 현재비밀번호 체크
			String nowPswd = Util.nvl(request.getParameter("nowPswd"));
			String encNowPswd = SHA256Util.getSHA256(nowPswd, membInfo.getSalt());
			if(!membInfo.getPswd().equals(encNowPswd)) {
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "비밀번호가 맞지 않습니다.");
				return null;
			}
			
			// 신규비밀번호 암호화
			String newPswd = Util.nvl(request.getParameter("newPswd"));
			String encNewPswd = SHA256Util.getSHA256(newPswd, membInfo.getSalt());
			
			MembInfoVO updateVO = new MembInfoVO();
			updateVO.setMembId(membInfo.getMembId());
			updateVO.setPswd(encNewPswd);
			this.membService.updatePswd(updateVO);
			
			JavaScriptUtil.flushJsAlertAndLocation(response, "비밀번호가 변경되었습니다.", request.getContextPath()+"/member/page.do?mnu_code="+mnu_code);
			return null;
			
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.info("["+TAG+"] 요청ID {} Exception {}" , membInfo.getMembId() , e.toString());
			JavaScriptUtil.flushJsAlertAndLocation(response, "비밀번호 변경에 실패했습니다.", request.getContextPath()+"/member/page.do?mnu_code="+mnu_code);
			return null;
		}
	}
}
