package egovframework.dnworks.controller.sitemanager.cms.blacklist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlockLogService;
import egovframework.dnworks.cms.blacklist.service.BlockLogVo;

@Controller
@RequestMapping("/SiteManager/cms/blacklist")
public class BlockLogController extends WebDefault
{
	@Autowired
	private BlockLogService blockLogService;
	
	/*
	 * BlockLog
	 */
	@RequestMapping("/blockLog.do")
	public String blockLog(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("frmTy"			, "");
		udp.put("srchSdate"		, "");
		udp.put("srchEdate"		, "");
		udp.put("srchKwd"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("frmTy"			, frmTy);
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String srchSdate 	= Util.nvl(request.getParameter("srchSdate"));
		String srchEdate 	= Util.nvl(request.getParameter("srchEdate"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchSdate"		, srchSdate);
		map.put("srchEdate"		, srchEdate);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = blockLogService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<BlockLogVo> blockLogList = blockLogService.selectList(map);
		
		model.addAttribute("no"				, no);
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("blockLogList"	, blockLogList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));

		return managerDir+"/cms/blacklist/blockLog";
	}
}
