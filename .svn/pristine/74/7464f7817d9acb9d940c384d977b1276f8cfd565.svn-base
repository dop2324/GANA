package egovframework.dnworks.controller.sitemanager.func.memb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;

/**
 * 장기미사용자 조회
 * */
@Controller
@RequestMapping("/SiteManager/func/memb/lngtm")
public class LngtmMembController extends WebDefault {

	@Autowired
	MembService membService;

	/*
	 * 울산모아 회원 장기미사용자 조회 list
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
		udp.put("srchLngtm"		, "");
		
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		model.addAttribute("mnu_code"		, mnu_code);

		switch(cmd) {
			case 2:
				this.select(request, response, model, queryString);
				location = managerDir+"/func/memb/lngtm/view";
			break;
			default :
				this.list(request, session, model, queryString);
				location = managerDir+"/func/memb/lngtm/list";
			break;
		}
		
		return location;
	}
	
private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		
		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchUseYn 	= Util.nvl(request.getParameter("srchUseYn"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		String srchLngtm	= Util.nvl(request.getParameter("srchLngtm"), "ALL");
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srchSe"	, srchSe);
		map.put("srchUseYn"	, srchUseYn);
		map.put("srchKwd", srchKwd);
		map.put("srchLngtm", srchLngtm);
		
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

}
