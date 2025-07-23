package egovframework.dnworks.controller.sitemanager.cms.blacklist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/blacklist")
public class BlackListController extends WebDefault
{
	/*
	 * BlackList
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/blacklist.do")
	public String blacklist(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		List<SiteVo> siteList = null;
		if(defaultSiteMap != null) {
			defaultSite = (String) defaultSiteMap.get("defaultSite");
			siteList = (List<SiteVo>) defaultSiteMap.get("sitePrmList");
		}
		
		String site_code 	= Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "ip");

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("frmTy"			, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);

		//List<SiteVo> siteList = siteService.selectList(null);
		model.addAttribute("siteList"		, siteList);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("frmTy"			, frmTy);


		return managerDir+"/cms/blacklist/blacklist";
	}
}
