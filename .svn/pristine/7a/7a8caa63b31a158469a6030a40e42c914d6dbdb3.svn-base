package egovframework.dnworks.controller.sitemanager.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.SiteVo;


@Controller
@RequestMapping("/SiteManager")
public class ManagerController extends WebDefault
{
	@Autowired
	private MenuService menuService;
	
	@Value("${Globals.AdminPath}")
	protected String managerDir;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/page.do"})
	public String page(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);	
		String site_code			= (String) base.get("site_code");
		String mnu_code				= (String) base.get("mnu_code");
		SiteVo siteVo 				= (SiteVo) base.get("siteVo");
		MenuVo menuVo 				= (MenuVo) base.get("menuVo");
		MenuVo tmnuVo 				= (MenuVo) base.get("tmnuVo");
		MenuVo pmnuVo 				= (MenuVo) base.get("pmnuVo");
		List<MenuVo> menuPath 		= (List<MenuVo>) base.get("menuPath");
		List<String> prmSelMenuList = (List<String>) base.get("prmSelMenuList");
		List<String> prmAdmMenuList = (List<String>) base.get("prmAdmMenuList");
		
		//권한체크
		int prmVal 					= super.getPermission(request, session);
		
		if(siteVo == null) return "/error/error404";
		if(menuVo == null) return "/error/error404";
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("siteVo"			, siteVo);
		model.addAttribute("menuVo"			, menuVo);
		model.addAttribute("tmnuVo"			, tmnuVo);
		model.addAttribute("pmnuVo"			, pmnuVo);
		model.addAttribute("menuPath"		, menuPath);
		model.addAttribute("prmVal"			, prmVal);
		model.addAttribute("prmSelMenuList"	, prmSelMenuList);
		model.addAttribute("prmAdmMenuList"	, prmAdmMenuList);
		
		Map<String, Object> menusMap = new HashMap<>();
		menusMap.put("site_code"	, menuVo.getSite_code());
		menusMap.put("mnu_code"		, siteVo.getMnu_code());
		menusMap.put("mnu_sttus"	, 1);
		//GNB
		LinkedHashMap<String, MenuVo> gnbMenuTree = menuService.selectTree(menusMap);
		Map<String, Object> gnbMenuMap = super.refineMenusFactory(session, gnbMenuTree, 1, "gnb", true, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("gnbMenuList"	, gnbMenuMap.get("topMenuList"));
		model.addAttribute("gnbSubMenuMap"	, gnbMenuMap.get("subMenuListMap"));
		
		//SNB
		String snb_mnuCode = tmnuVo.getMnu_code();
		menusMap.put("mnu_code"		, snb_mnuCode);
		LinkedHashMap<String, MenuVo> snbMenuTree = menuService.selectTree(menusMap);
		Map<String, Object> snbMenuMap = super.refineMenusFactory(session, snbMenuTree, 2, "snb", true, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("snbMenuList"	, snbMenuMap.get("topMenuList"));
		model.addAttribute("snbSubMenuMap"	, snbMenuMap.get("subMenuListMap"));
		
		//TAB, Depth
		String menuDepth2 = "";
		String menuDepth3 = "";
		String menuDepth4 = "";
		String menuDepth5 = "";
		for(MenuVo m:menuPath) {
			if(m.getMnu_level() == 2) menuDepth2 = m.getMnu_code();
			if(m.getMnu_level() == 3) menuDepth3 = m.getMnu_code();
			if(m.getMnu_level() == 4) menuDepth4 = m.getMnu_code();
			if(m.getMnu_level() == 5) menuDepth5 = m.getMnu_code();
		}
		
		menusMap.put("mnu_code"		, menuDepth2);
		LinkedHashMap<String, MenuVo> tabMenuTree = menuService.selectTree(menusMap);

		Map<String, Object> tabMenuMap = super.refineMenusFactory(session, tabMenuTree, 3, "tab", true, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("tabMenuList"	, tabMenuMap.get("topMenuList"));
		model.addAttribute("tabSubMenuMap"	, tabMenuMap.get("subMenuListMap"));
		
		model.addAttribute("menuDepth2"	, menuDepth2);
		model.addAttribute("menuDepth3"	, menuDepth3);
		model.addAttribute("menuDepth4"	, menuDepth4);
		model.addAttribute("menuDepth5"	, menuDepth5);
		
		return managerDir+"/page";
	}	
}
