package egovframework.dnworks.controller.sitemanager.cms.menu;

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
import egovframework.dnworks.cms.menu.service.MenuGratifyService;
import egovframework.dnworks.cms.menu.service.MenuGratifyVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/menu/gratify")
public class MenuGratifyController extends WebDefault
{
	@Autowired
	private MenuGratifyService menuGratifyService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/gratify.do")
	public String gratify(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "SiteManager";
		List<SiteVo> siteList = null;
		if(defaultSiteMap != null) {
			defaultSite = (String) defaultSiteMap.get("defaultSite");
			siteList = (List<SiteVo>) defaultSiteMap.get("sitePrmList");
		}
		
		String site_code 	= Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "gratify");

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("frmTy"			, "");
		udp.put("srchSdate"		, "");
		udp.put("srchEdate"		, "");
		udp.put("srchKwd"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("siteList"		, siteList);
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("frmTy"			, frmTy);
		
		return managerDir+"/cms/menu/gratify/gratify";
	}

	@RequestMapping("/point.do")
	public String point(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String site_code 	= (String) request.getAttribute("site_code");
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("mnu_code"		, parm_mnuCode);
		List<MenuGratifyVo> gratifyList = menuGratifyService.selectList(map);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("gratifyList"	, gratifyList);
		
		return managerDir+"/cms/menu/gratify/gratify_point";
	}
	
	@RequestMapping("/comment.do")
	public String comment(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String site_code 	= (String) request.getAttribute("site_code");
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"),1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"),20);
		String srchSdate 	= Util.nvl(request.getParameter("srchSdate"));
		String srchEdate 	= Util.nvl(request.getParameter("srchEdate"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String queryString	= (String) request.getAttribute("queryString");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("mnu_code"		, parm_mnuCode);
		map.put("srchSdate"		, srchSdate);
		map.put("srchEdate"		, srchEdate);
		map.put("srchKwd"		, srchKwd);
		
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);

		int totalCnt = menuGratifyService.selectCmmntListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MenuGratifyVo> gratifyList = menuGratifyService.selectCmmntList(map);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("no"				, no);
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("gratifyList"	, gratifyList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
		
		return managerDir+"/cms/menu/gratify/gratify_comment";
	}
}
