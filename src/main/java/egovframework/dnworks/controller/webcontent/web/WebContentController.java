package egovframework.dnworks.controller.webcontent.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.SiteVo;


@Controller
public class WebContentController extends WebDefault
{
	@Autowired
	private MenuService menuService;

	
	@RequestMapping(value= {"/{path}/main.do", "/{path}/index.do"})
	public String main(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		if(siteVo == null) {
			return "/error/error404";
		}
		
		this.configMenu(request, session, model, base, 0);
		
		model.addAttribute("path", path);
		return String.format("%s/%s/main", publicPath, path);
	}
	
	@RequestMapping("/{path}/page.do")
	public String page(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		MenuVo menuVo = (MenuVo) base.get("menuVo");
			
		if(siteVo == null) return "/error/error404";
		if(menuVo == null) return "/error/error404";
		
		//권한체크
		int prmVal = super.getPermission(request, session);
		
		//메뉴 사용중지
		if(menuVo.getMnu_sttus() == 0 && !Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode())) {
			return "/error/error404";
		}
		
		//메뉴설정
		this.configMenu(request, session, model, base, prmVal);
		
		model.addAttribute("path", path);
		return String.format("%s/%s/page", publicPath, path);
	}
	
	@RequestMapping(value= {"/{path}/{path2}/main.do", "/{path}/{path2}/index.do"})
	public String main(@PathVariable("path") String path, @PathVariable("path2") String path2
					, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		if(siteVo == null) {
			return "/error/error404";
		}

		this.configMenu(request, session, model, base, 0);
		
		String location = String.format("%s/%s/%s/main", publicPath, path, path2);

		if(!Util.nvl(siteVo.getSite_directory()).equals("")) {
			path = siteVo.getSite_directory();
			location = String.format("%s%s/main", publicPath, siteVo.getSite_directory());
		}
		
		model.addAttribute("path"	, path);
		
		return location;
	}
	
	@RequestMapping("/{path}/{path2}/page.do")
	public String page(@PathVariable("path") String path, @PathVariable("path2") String path2
			, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		MenuVo menuVo = (MenuVo) base.get("menuVo");
			
		if(siteVo == null) return "/error/error404";
		if(menuVo == null) return "/error/error404";
		
		//권한체크
		int prmVal = super.getPermission(request, session);
		
		//메뉴 사용중지
		if(menuVo.getMnu_sttus() == 0 && !Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode())) {
			return "/error/error404";
		}
		
		//메뉴설정
		this.configMenu(request, session, model, base, prmVal);
		
		String location = String.format("%s/%s/%s/page", publicPath, path, path2);
		if(!Util.nvl(siteVo.getSite_directory()).equals("")) {
			path = siteVo.getSite_directory();
			location = String.format("%s%s/page", publicPath, siteVo.getSite_directory());
		}
		
		model.addAttribute("path"	, path);
		
		return location;
	}
	
	@SuppressWarnings("unchecked")
	private void configMenu(HttpServletRequest request, HttpSession session, ModelMap model, Map<String, Object> base, int prmVal) throws Exception
	{
		String site_code			= (String) base.get("site_code");
		String mnu_code				= (String) base.get("mnu_code");
		SiteVo siteVo 				= (SiteVo) base.get("siteVo");
		MenuVo menuVo 				= (MenuVo) base.get("menuVo");
		MenuVo tmnuVo 				= (MenuVo) base.get("tmnuVo");
		MenuVo pmnuVo 				= (MenuVo) base.get("pmnuVo");
		List<MenuVo> menuPath 		= (List<MenuVo>) base.get("menuPath");
		List<String> prmSelMenuList = (List<String>) base.get("prmSelMenuList");
		List<String> prmAdmMenuList = (List<String>) base.get("prmAdmMenuList");

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
		
		//ReverseMenuPath
		if(menuPath != null) {
		List<MenuVo> reverseMenuPath = new ArrayList<>(menuPath);
			Collections.reverse(reverseMenuPath);
			model.addAttribute("reverseMenuPath", reverseMenuPath);
		}
		
		Map<String, Object> menusMap = new HashMap<>();
		menusMap.put("site_code"	, menuVo.getSite_code());
		menusMap.put("mnu_code"		, siteVo.getMnu_code());
		menusMap.put("mnu_sttus"	, 1);
		
		//GNB
		LinkedHashMap<String, MenuVo> gnbMenuTree = menuService.selectTree(menusMap);
		Map<String, Object> gnbMenuMap = super.refineMenusFactory(session, gnbMenuTree, 1, "gnb", false, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("gnbMenuList"	, gnbMenuMap.get("topMenuList"));
		model.addAttribute("gnbSubMenuMap"	, gnbMenuMap.get("subMenuListMap"));
		
		//Mobile
		Map<String, Object> mobileMenuMap = super.refineMenusFactory(session, gnbMenuTree, 2, "snb", false, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("mobileMenuList"		, mobileMenuMap.get("topMenuList"));
		model.addAttribute("mobileSubMenuMap"	, mobileMenuMap.get("subMenuListMap"));
		
		//SNB
		if(tmnuVo != null) {
			String snb_mnuCode = tmnuVo.getMnu_code();
			menusMap.put("mnu_code"		, snb_mnuCode);
		}
		LinkedHashMap<String, MenuVo> snbMenuTree = menuService.selectTree(menusMap);
		Map<String, Object> snbMenuMap = super.refineMenusFactory(session, snbMenuTree, 2, "snb", false, prmSelMenuList, prmAdmMenuList);
		model.addAttribute("snbMenuList"	, snbMenuMap.get("topMenuList"));
		model.addAttribute("snbSubMenuMap"	, snbMenuMap.get("subMenuListMap"));
		
		
		//TAB
		String menuDepth2 = "";
		String menuDepth3 = "";
		String menuDepth4 = "";
		String menuDepth5 = "";
		if(menuPath != null) {
			for(MenuVo m:menuPath) {
				if(m.getMnu_level() == 2) menuDepth2 = m.getMnu_code();
				if(m.getMnu_level() == 3) menuDepth3 = m.getMnu_code();
				if(m.getMnu_level() == 4) menuDepth4 = m.getMnu_code();
				if(m.getMnu_level() == 5) menuDepth5 = m.getMnu_code();
			}
		}
		model.addAttribute("menuDepth2"	, menuDepth2);
		model.addAttribute("menuDepth3"	, menuDepth3);
		model.addAttribute("menuDepth4"	, menuDepth4);
		model.addAttribute("menuDepth5"	, menuDepth5);
		
		if(menuVo.getMnu_visibleTab() == 1) {
			//TAB
			menusMap.put("mnu_code"		, menuDepth2);
			LinkedHashMap<String, MenuVo> tabMenuTree = menuService.selectTree(menusMap);

			Map<String, Object> tabMenuMap = null;
			//tab 4depth, tab 5depth
			tabMenuMap = super.refineMenusFactory(session, tabMenuTree, 4, "tab", false, prmSelMenuList, prmAdmMenuList);
			model.addAttribute("tab4MenuList"	, tabMenuMap.get("topMenuList"));
			model.addAttribute("tab5MenuMap"	, tabMenuMap.get("subMenuListMap"));
			
		}
	}
	
	@RequestMapping(value= {"/{path}/login.do"})
	public String login(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		if(siteVo == null) {
			return "/error/error404";
		}
		
		return String.format("redirect:/%s/page.do?mnu_code=login", path);
	}
	
	//검색페이지
	@RequestMapping(value= {"/{path}/search.do"})
	public String search(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception{

		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		if(siteVo == null) {
			return "/error/error404";
		}
		
		this.configMenu(request, session, model, base, 0);
		
		model.addAttribute("path", path);
		return String.format("%s/search/main", publicPath, path);
	}
}
