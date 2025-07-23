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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * 아이디/비밀번호 찾기
 * 본인인증 후 ID 정보 조회
 * */
@Controller
@RequestMapping("/WebContent/func/member")
public class MembFindController extends WebDefault {
	
	@Autowired
	MembService membService;
	
	@Autowired
	MoaMberComponent moaMberComponent;

	private static final String TAG = "MembFindController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");

	@RequestMapping("/find.do")
	public String findid(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
		
		// 로그인상태 체크
		if(MoaSessionUtil.isLogin(request)) {
			JavaScriptUtil.flushJsAlertAndLocation(response, "", request.getContextPath()+"/member/page.do?mnu_code=membMypage");
		}
		
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
			case "main":
				pageLocation = "main";
			break;
			case "id_result":
				this.findId(request, response, session, model);
				pageLocation = "id_result";
			break;
			case "pswd_form":
				this.findId(request, response, session, model);
				pageLocation = "pswd_form";
			break;
		}
		
		return String.format("%s/member/func/find/%s", publicPath, pageLocation);
	}

	private void findId(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
		try {
			// NICE 본인인증정보 체크
			if(EgovStringUtil.isEmpty((String)session.getAttribute(SessionKey.NICE_RESPONSENUMBER.getKey()))) {
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "인증정보가 없습니다.");
				return;
			}
			LOGGER.info("["+TAG+"] NICE_RESPONSENUMBER: {}", Util.getSession(session, SessionKey.NICE_CONNINFO.getKey()));
			
			// CI 값으로 ID 조회
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ciVal", Util.getSession(session, SessionKey.NICE_CONNINFO.getKey()));
			MembInfoVO resultVO = this.membService.selectMembInfo(map);
			
			// ID 정보가 없을경우
			if(resultVO == null) {
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "사용자 정보가 없습니다.");
				return;
			}
			
			// ID 정보 세팅 첫 두자리만 노출 나머지는 *** 변환 (예. userid > use***)
			String findMembId = resultVO.getMembId();
			findMembId = MoaFuncUtil.maskId(findMembId);
			model.addAttribute("membId", findMembId);
			
			// 가입일
			String joinDt = resultVO.getJoinDt();
			model.addAttribute("joinDt", joinDt);
			
		}catch(Exception e) {
			LOGGER.info("["+TAG+"] {}", e.toString());
		}
	}
	
	@RequestMapping(value = "/pswdchg_process.do", method = RequestMethod.POST)
	public String process(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session
			, @ModelAttribute MembInfoVO updateVO) throws Exception {
		String returnUrl = request.getContextPath()+"/member";
		
		String newPswd = Util.nvl(request.getParameter("newPswd"), "");
		if("".equals(newPswd)) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "유효한 값이 넘어오지 않았습니다.");
			return null;
		}
		
		if(!moaMberComponent.isVaildatePswd(newPswd)) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "비밀번호는 8~15자의 영문, 숫자, 특수문자를 포함해야 합니다.");
			return null;
		}
		
		// CI 값으로 ID 조회
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ciVal", Util.getSession(session, SessionKey.NICE_CONNINFO.getKey()));
		MembInfoVO resultVO = this.membService.selectMembInfo(map);
		
		updateVO.setMembId(resultVO.getMembId());
		updateVO.setPswd(SHA256Util.getSHA256(newPswd, resultVO.getSalt()));
		this.membService.updatePswd(updateVO);
		
		// 인증정보 삭제
		MoaSessionUtil.removeSessionNiceAuth(request);
		
		JavaScriptUtil.flushJsAlertAndLocation(response, "비밀번호가 변경되었습니다.", returnUrl);
		
		return null;
	}
	
}
