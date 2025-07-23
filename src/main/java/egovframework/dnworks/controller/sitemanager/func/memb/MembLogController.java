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

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembLogService;

@Controller
@RequestMapping("/SiteManager/func/memb/log")
public class MembLogController extends WebDefault {

	@Autowired
	MembLogService membLogService;
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model
			, HttpSession session) throws Exception {
		
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
			default :
				this.list(request, session, model, queryString);
				location = managerDir+"/func/memb/log/list";
			break;			
		}
		
		return location;
	}

	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchUseYn 	= Util.nvl(request.getParameter("srchUseYn"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		String srchUseOrg 	= Util.nvl(request.getParameter("srchUseOrg"));
		String srchSdate 	= Util.nvl(request.getParameter("srchSdate"));
		String srchEdate 	= Util.nvl(request.getParameter("srchEdate"));
		String srchLoginYn 	= Util.nvl(request.getParameter("srchLoginYn"));
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srchSe"	, srchSe);
		map.put("srchUseYn"	, srchUseYn);
		map.put("srchKwd", srchKwd);
		map.put("srchUseOrg", srchUseOrg);
		map.put("srchSdate", srchSdate);
		map.put("srchEdate", srchEdate);
		map.put("srchLoginYn", srchLoginYn);
		
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = membLogService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MembInfoVO> resultList = membLogService.selectList(map);
		
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("no"				, no);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("resultList"		, resultList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
}
