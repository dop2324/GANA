package egovframework.dnworks.controller.sitemanager.cms.stats;

import java.util.Calendar;
import java.util.Date;
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
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.SiteVo;
import egovframework.dnworks.cms.stats.service.SessionService;
import egovframework.dnworks.cms.stats.service.SessionVo;
import egovframework.dnworks.cms.stats.service.StatsService;
import egovframework.dnworks.cms.stats.service.StatsVo;

@Controller
@RequestMapping("/SiteManager/cms/stats")
public class StatsController extends WebDefault
{
	@Autowired
	private StatsService statsService;
	
	@Autowired
	private SessionService sessionService;
	/*
	 * Stats
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/stats.do")
	public String stats(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		List<SiteVo> siteList = null;
		if(defaultSiteMap != null) {
			defaultSite = (String) defaultSiteMap.get("defaultSite");
			siteList = (List<SiteVo>) defaultSiteMap.get("sitePrmList");
		}
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "site");

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("frmTy"			, "");
		udp.put("srchSDate"		, "");
		udp.put("srchEDate"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		//List<SiteVo> siteList = siteService.selectList(null);
		model.addAttribute("siteList"		, siteList);
		
		model.addAttribute("defaultSite"	, defaultSite);
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("frmTy"			, frmTy);


		return managerDir+"/cms/stats/stats";
	}
	
	private String selectStatsListModel(HttpServletRequest request, ModelMap model, String statsTy, String srchSDate, String srchEDate)
	{
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		
		Date dtStartDt		= DateUtil.getDateFromString(srchSDate);
		Date dtEndDt		= DateUtil.getDateFromString(srchEDate);

		if(dtStartDt.compareTo(dtEndDt) > 0) {
			model.addAttribute("message", "시작일이 종료일 보다 큽니다");
			model.addAttribute("location", "history.back()");
			return "common/scriptAlert";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
		List<StatsVo> siteList = null;
		switch(statsTy)
		{
			case "site"		: siteList = statsService.selectSite(map); break;
			case "year"		: siteList = statsService.selectYear(map); break;
			case "month"	: siteList = statsService.selectMonth(map); break;
			case "day"		: siteList = statsService.selectDay(map); break;
			case "time"		: siteList = statsService.selectTime(map); break;
			case "menu"		: siteList = statsService.selectMenu(map); break;
		}
				

		model.addAttribute("siteList"		, siteList);
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("srchMap"		, map);
		
		return null;
	}
	
	@RequestMapping("/statsSite.do")
	public String statsSite(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int lastDate = cal.getMaximum(Calendar.DAY_OF_MONTH);
		String startDate 	= String.format("%04d-%02d-01", currYear, currMonth);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, lastDate);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String location = this.selectStatsListModel(request, model, "site", srchSDate, srchEDate);
		if(location != null) return location;

		return managerDir+"/cms/stats/statsSite";
	}
	
	@RequestMapping("/statsYear.do")
	public String statsYear(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		String startDate 	= String.format("%04d-01-01", currYear -1);
		String endDate 		= String.format("%04d-12-31", currYear);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate);
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate);
		
		String location = this.selectStatsListModel(request, model, "year", srchSDate, srchEDate);
		if(location != null) return location;

		return managerDir+"/cms/stats/statsYear";
	}
	
	@RequestMapping("/statsMonth.do")
	public String statsMonth(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		String startDate 	= String.format("%04d-01-01", currYear);
		String endDate 		= String.format("%04d-12-31", currYear);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String location = this.selectStatsListModel(request, model, "month", srchSDate, srchEDate);
		if(location != null) return location;
		
		return managerDir+"/cms/stats/statsMonth";
	}
	
	@RequestMapping("/statsDay.do")
	public String statsDay(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int lastDate = cal.getMaximum(Calendar.DAY_OF_MONTH);
		String startDate 	= String.format("%04d-%02d-01", currYear, currMonth);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, lastDate);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String location = this.selectStatsListModel(request, model, "day", srchSDate, srchEDate);
		if(location != null) return location;
		
		return managerDir+"/cms/stats/statsDay";
	}
	
	@RequestMapping("/statsTime.do")
	public String statsTime(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int currDate = cal.get(Calendar.DATE)-1;
		String startDate 	= String.format("%04d-%02d-%02d", currYear, currMonth, currDate);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, currDate);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String location = this.selectStatsListModel(request, model, "time", srchSDate, srchEDate);
		if(location != null) return location;
		
		return managerDir+"/cms/stats/statsTime";
	}
	
	@RequestMapping("/statsMenu.do")
	public String statsMenu(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int lastDate = cal.getMaximum(Calendar.DAY_OF_MONTH);
		String startDate 	= String.format("%04d-%02d-01", currYear, currMonth);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, lastDate);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String location = this.selectStatsListModel(request, model, "menu", srchSDate, srchEDate);
		if(location != null) return location;

		return managerDir+"/cms/stats/statsMenu";
	}
	
	@RequestMapping("/statsLog.do")
	public String statsLog(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int currDate = cal.get(Calendar.DATE);
		String startDate 	= String.format("%04d-%02d-%02d", currYear, currMonth, currDate);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, currDate);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String queryString	= (String) request.getAttribute("queryString");	

		Date dtStartDt		= DateUtil.getDateFromString(srchSDate);
		Date dtEndDt		= DateUtil.getDateFromString(srchEDate);

		if(dtStartDt.compareTo(dtEndDt) > 0) {
			model.addAttribute("message", "시작일이 종료일 보다 큽니다");
			model.addAttribute("location", "history.back()");
			return "common/scriptAlert";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = sessionService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<SessionVo> logList = sessionService.selectList(map);
		
		model.addAttribute("no"			, no);
		model.addAttribute("pageNo"		, pageNo);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("logList"	, logList);
		model.addAttribute("srchMap"	, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
		
		return managerDir+"/cms/stats/statsLog";
	}
}
