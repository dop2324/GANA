package egovframework.dnworks.controller.sitemanager.cms.menu;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;

@Controller
@RequestMapping("/SiteManager/cms/menu/page")
public class RefPageController extends WebDefault
{
	@RequestMapping("/refPage.do")
	public String refPage(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code = Util.nvl(request.getParameter("site_code"), defaultSite);
		
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String parm_mnuUprCode 	= Util.nvl(request.getParameter("parm_mnuUprCode"));
		String frmTy 			= Util.nvl(request.getParameter("frmTy"), "update");
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		
		return managerDir+"/cms/menu/page/refPage";
	}
}
