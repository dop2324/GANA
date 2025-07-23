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
import egovframework.dnworks.cms.menu.service.LogErrorService;
import egovframework.dnworks.cms.menu.service.LogErrorVo;
import egovframework.dnworks.cms.menu.service.LogService;
import egovframework.dnworks.cms.menu.service.LogVo;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogService;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/menu/log")
public class LogController extends WebDefault
{
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private LogErrorService logErrorService;
	
	@Autowired
	private MenuAlarmLogService menuAlarmLogService;
	
	/*
	 * Log
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/log.do")
	public String log(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
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
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "log");

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


		return managerDir+"/cms/menu/log/log";
	}
	
	/*
	 * log_list
	 */
	@RequestMapping("/log_list.do")
	public String log_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		udp.put("frmTy"			, "");
		udp.put("srchSdate"		, "");
		udp.put("srchEdate"		, "");
		udp.put("srchHow"		, "");
		udp.put("srchPrivacy"	, "");
		udp.put("srchKwd"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("frmTy"			, frmTy);
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String srchSdate 	= Util.nvl(request.getParameter("srchSdate"));
		String srchEdate 	= Util.nvl(request.getParameter("srchEdate"));
		String srchHow 		= Util.nvl(request.getParameter("srchHow"));
		String srchPrivacy 	= Util.nvl(request.getParameter("srchPrivacy"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("mnu_code"		, parm_mnuCode);
		map.put("srchSdate"		, srchSdate);
		map.put("srchEdate"		, srchEdate);
		map.put("srchHow"		, srchHow);
		map.put("srchPrivacy"	, srchPrivacy);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = logService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<LogVo> logList = logService.selectList(map);
		
		model.addAttribute("no"				, no);
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("logList"		, logList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));

		return managerDir+"/cms/menu/log/log_list";
	}
	
	/*
	 * log_list
	 */
	@RequestMapping("/logError_list.do")
	public String logError_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
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
		map.put("mnu_code"		, parm_mnuCode);
		map.put("srchSdate"		, srchSdate);
		map.put("srchEdate"		, srchEdate);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = logErrorService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<LogErrorVo> logErrorList = logErrorService.selectList(map);
		
		model.addAttribute("no"				, no);
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("logErrorList"	, logErrorList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));

		return managerDir+"/cms/menu/log/logError_list";
	}
	
	/*
	 * alarmLog_list
	 */
	@RequestMapping("/alarmLog_list.do")
	public String alarmLog_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		udp.put("frmTy"			, "");
		udp.put("srchSdate"		, "");
		udp.put("srchEdate"		, "");
		udp.put("srchHow"		, "");
		udp.put("srchPrivacy"	, "");
		udp.put("srchKwd"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("frmTy"			, frmTy);
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String srchSdate 	= Util.nvl(request.getParameter("srchSdate"));
		String srchEdate 	= Util.nvl(request.getParameter("srchEdate"));
		String srchMethod 	= Util.nvl(request.getParameter("srchMethod"));
		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("mnu_code"		, parm_mnuCode);
		map.put("srchSdate"		, srchSdate);
		map.put("srchEdate"		, srchEdate);
		map.put("srchMethod"	, srchMethod);
		map.put("srchSe"		, srchSe);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = menuAlarmLogService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MenuAlarmLogVo> logList = menuAlarmLogService.selectList(map);
		
		model.addAttribute("no"				, no);
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("logList"		, logList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));

		return managerDir+"/cms/menu/log/alarmLog_list";
	}
}
