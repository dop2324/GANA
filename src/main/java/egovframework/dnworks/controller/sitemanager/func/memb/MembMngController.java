package egovframework.dnworks.controller.sitemanager.func.memb;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;
import egovframework.transfer.mcs.component.McsComponent;

@Controller
@RequestMapping("/SiteManager/func/memb/mng")
public class MembMngController extends WebDefault {

	@Autowired
	MembService membService;
	
	@Autowired
	McsComponent sender;
	
	/*
	 * 울산모아 회원관리 list
	 * @param request  HttpServletRequest 객체
	 * @param response ServletResponse 객체
	 * @param model    ModelMap 객체
	 * @return 화면 경로
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
				
		String location 	= null;
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("srchSe"		, "");
		udp.put("srchUseYn"		, "");
		
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		model.addAttribute("mnu_code"		, mnu_code);
		
		switch(cmd) {
			case 2:
				this.select(request, response, model, queryString);
				location = managerDir+"/func/memb/mng/view";
			break;
			default :
				this.list(request, session, model, queryString);
				location = managerDir+"/func/memb/mng/list";
			break;
		}
		
		return location;
	}
	
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		
		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchUseYn 	= Util.nvl(request.getParameter("srchUseYn"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srchSe"	, srchSe);
		map.put("srchUseYn"	, srchUseYn);
		map.put("srchKwd", srchKwd);
		
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);

		int totalCnt = membService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MembInfoVO> resultList = membService.selectList(map);
		
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("no"				, no);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("resultList"		, resultList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response, ModelMap model, String queryString) throws Exception {
		MembInfoVO resultVO = new MembInfoVO();
		String membUnqId = Util.nvl(request.getParameter("membUnqId"),"");
		if(EgovStringUtil.isBlank(membUnqId)) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "선택된 값이 없습니다.");
			return;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("membUnqId", membUnqId);
		
		resultVO = this.membService.select(map);
		if(resultVO == null) {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, ".");
			return;
		}
		model.addAttribute("resultVO", resultVO);
	}
	
	/**
	 * 비밀번호 초기화
	 * */
	@RequestMapping(value="/pswdInit.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap pswdInit(HttpServletRequest request) throws Exception {
		String membUnqId = Util.nvl(request.getParameter("membUnqId"), "");
		
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", true);
		resultMap.put("msg", "");
		
		if(EgovStringUtil.isBlank(membUnqId)) {
			resultMap.put("result", false);
			resultMap.put("msg", "유효한 값이 넘어오지 않았습니다.");
			return resultMap;			
		}
		
		// 비밀번호 암호화
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("membUnqId", membUnqId);
		MembInfoVO membInfo = this.membService.select(searchMap);
		updatePswd(request, membInfo.getMembId(), SHA256Util.getSHA256(membInfo.getMembId()+"1@", membInfo.getSalt()));
		
		return resultMap;
	}
	
	/**
	 * 임시비밀번호 발급
	 * */
	@RequestMapping(value="/pswdRegen.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap pswdRegen(HttpServletRequest request) throws Exception {
		String membUnqId = Util.nvl(request.getParameter("membUnqId"), "");
		
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", true);
		resultMap.put("msg", "");
		
		if(EgovStringUtil.isBlank(membUnqId)) {
			resultMap.put("result", false);
			resultMap.put("msg", "유효한 값이 넘어오지 않았습니다.");
			return resultMap;			
		}
		
		// 임시비밀번호 발급
		String newPswd = generateTempPassword(8);
		
		// 비밀번호 암호화
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("membUnqId", membUnqId);
		MembInfoVO membInfo = this.membService.select(searchMap);
		updatePswd(request, membInfo.getMembId(), SHA256Util.getSHA256(newPswd, membInfo.getSalt()));
		
		// 해당 내용에 대한 문자발송
		String msg = membInfo.getMembId()+"회원님의 임시비밀번호가 발급되었습니다. \n" + newPswd;
		sender.sendMMS(membInfo.getMembNm(), membInfo.getMbtlnum(), msg);
		
		return resultMap;
	}
	
	/**
	 * 회원상태 변경
	 * */
	@RequestMapping(value="/membStatChg.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap membStatChg(HttpServletRequest request, HttpSession session) throws Exception {
				
		String membStatCd = Util.nvl(request.getParameter("membStatCd"), "");
		String membId = Util.nvl(request.getParameter("membId"), "");
		
		
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", true);
		resultMap.put("msg", "");
		
		if(EgovStringUtil.isBlank(membId) || EgovStringUtil.isBlank(membStatCd)) {
			resultMap.put("result", false);
			resultMap.put("msg", "유효한 값이 넘어오지 않았습니다.");
			return resultMap;
		}
		
		EgovMap searchMap = new EgovMap();
		searchMap.put("membStatCd", membStatCd);
		searchMap.put("membId", membId);
		this.membService.updateMembStatCd(searchMap);
		
		log_what = "모아회원 회원상태 변경 " + membId + "/" + membStatCd;
		super.insertLog(request, log_what, "16");
		
		return resultMap;
	}
	
	/**
	 * 비밀번호 update
	 * @param membId, pswd
	 * */
	public void updatePswd(HttpServletRequest request, String membId, String pswd) throws Exception {
		
		MembInfoVO updateVO = new MembInfoVO();
		updateVO.setMembId(membId);
		updateVO.setPswd(pswd);
		this.membService.updatePswd(updateVO);
		
		log_what = "모아회원 비밀번호 변경 " + membId;
		super.insertLog(request, log_what, "16");
		
	}

	/**
	 * 임시비밀번호 생성 (영문 대소문자 + 숫자, 지정 길이)
	 */
	private String generateTempPassword(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			int idx = random.nextInt(chars.length());
			sb.append(chars.charAt(idx));
		}
		return sb.toString();
	}
}
