package egovframework.dnworks.controller.sitemanager.cms.link;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.com.cmm.util.EgovFormBasedFileVo;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cmm.FileUploadUtil;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.link.service.LinkClickService;
import egovframework.dnworks.cms.link.service.LinkClickVo;
import egovframework.dnworks.cms.link.service.LinkGroupService;
import egovframework.dnworks.cms.link.service.LinkGroupVo;
import egovframework.dnworks.cms.link.service.LinkService;
import egovframework.dnworks.cms.link.service.LinkVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/link")
public class LinkController extends WebDefault
{
	@Autowired
	private LinkGroupService linkGroupService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private LinkClickService linkClickService;
	
	/*
	 * link
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/link.do")
	public String link(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		List<SiteVo> siteList = null;
		if(defaultSiteMap != null) {
			defaultSite = (String) defaultSiteMap.get("defaultSite");
			siteList = (List<SiteVo>) defaultSiteMap.get("sitePrmList");
		}
		model.addAttribute("siteList"		, siteList);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String srchGroup 	= Util.nvl(request.getParameter("srchGroup"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);
		
		Map<String, Object> map = new HashMap<>();
		map.put("site_code", site_code);
		map.put("lgp_sttus", 1);
		List<LinkGroupVo> groupList = linkGroupService.selectList(map);
		model.addAttribute("groupList"		, groupList);

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("srchKwd"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("srchGroup"		, srchGroup);

		return managerDir+"/cms/link/link";
	}
	
	@RequestMapping("/initLink.do")
	public String initLink(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location = null;
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String srchGroup 	= Util.nvl(request.getParameter("srchGroup"));
		
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);

		String queryString	= (String) request.getAttribute("queryString");	
		if(!srchGroup.equals("")) {
			queryString = String.format("%ssrchGroup=%s&", queryString, srchGroup);
		}
		
		String listLink 	= String.format("?%s", queryString);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("srchGroup"		, srchGroup);
		model.addAttribute("cmd"			, cmd);
			
		model.addAttribute("queryString"	, queryString);
		model.addAttribute("listLink"		, listLink);
		
		switch(cmd)
		{
			case 2 :
						this.stats(request, session, model, queryString);			
						location = managerDir+"/cms/link/link_stats";
						break;
			case 16	:
			case 4 :
						this.select(request, session, model, queryString);			
						location = managerDir+"/cms/link/link_form";
						break;
			default :			
						this.list(request, session, model, queryString);			
						location = managerDir+"/cms/link/link_list";
						break;
		}
		
		return location;
	}
	
	private void stats(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		int currYear = cal.get(Calendar.YEAR);
		int currMonth= cal.get(Calendar.MONTH)+1;
		int lastDate = cal.getMaximum(Calendar.DAY_OF_MONTH);
		String startDate 	= String.format("%04d-%02d-01", currYear, currMonth);
		String endDate 		= String.format("%04d-%02d-%02d", currYear, currMonth, lastDate);
		
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"), startDate );
		String srchEDate 	= Util.nvl(request.getParameter("srchEDate"), endDate );
		
		int lnk_sn 			= Util.nvl(request.getParameter("lnk_sn"), 1);
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		LinkVo vo = linkService.select(lnk_sn);
		model.addAttribute("vo", vo);
		
		//검색 map
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lnk_sn"		, lnk_sn);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
				
		int totalCnt = linkClickService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<LinkClickVo> clickList = linkClickService.selectList(map);
		
		model.addAttribute("no"			, no);
		model.addAttribute("clickList"	, clickList);
		model.addAttribute("srchMap"	, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
	/* list */
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String srchSttus 	= Util.nvl(request.getParameter("srchSttus"));
		String srchGroup 	= Util.nvl(request.getParameter("srchGroup"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchGroup"		, srchGroup);
		map.put("srchSttus"		, srchSttus);
		map.put("srchKwd"		, srchKwd);
		
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = linkService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<LinkVo> linkList = linkService.selectList(map);
		
		model.addAttribute("no"			, no);
		model.addAttribute("pageNo"		, pageNo);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("linkList"	, linkList);
		model.addAttribute("srchMap"	, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
	/* select */
	private void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int lnk_sn 	= Util.nvl(request.getParameter("lnk_sn"), 0);
		int cmd		= lnk_sn!= 0 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		LinkVo vo = null;
		if(cmd == Code.Prm.PrmUpd.getCode())
		{
			vo = linkService.select(lnk_sn);
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
	}
	
	@RequestMapping(value = "/link_process.do", method = RequestMethod.POST)
	public String link_process(@ModelAttribute LinkVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setLnk_mdfcnID(Util.getSession(session, "USR_ID"));
			
			int deleteFile = Util.nvl(request.getParameter("deleteFile"), 0);
			String org_imgFileNm = Util.nvl(request.getParameter("org_imgFileNm"));
			
			String lnk_startDt 	= Util.nvl(request.getParameter("lnk_startDt"), "2000-01-01");
			String lnk_startDtHH = Util.nvl(request.getParameter("lnk_startDtHH"), "00");
			String startDt 		= String.format("%s %s:00:00", lnk_startDt, lnk_startDtHH);
			
			String lnk_endDt 	= Util.nvl(request.getParameter("lnk_endDt"), "2999-12-31");
			String lnk_endDtHH 	= Util.nvl(request.getParameter("lnk_endDtHH"), "00");
			String endDt 		= String.format("%s %s:00:00", lnk_endDt, lnk_endDtHH);
			if(lnk_endDtHH.equals("00")) {
				endDt = String.format("%s 23:59:59", lnk_endDt);
			}

			vo.setLnk_startDt(DateUtil.getDateTimeFromString(startDt));
			vo.setLnk_endDt(DateUtil.getDateTimeFromString(endDt));
			
			//upload
			String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+vo.getWebDir();
			String[] BadExt 	= EgovProperties.getProperty("Globals.file.BadExt").split(",");
			int MaxUploadSize	= Util.nvl(EgovProperties.getProperty("Globals.file.MaxUploadSize"), 30);
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode()) 
			{
				//delete file
				if(deleteFile != 0) {
					FileManager.deleteFile(String.format("%s/%s", uploadDir, org_imgFileNm ) );
					vo.setLnk_imgFileNm("");
				}else{
					vo.setLnk_imgFileNm(org_imgFileNm);
				}
				
				//file upload
				List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, MaxUploadSize, BadExt);
				if(files.size() > 0) {
					//수정 새로운 이미지 첨부시 원본을 지운다
					if(vo.getLnk_sn() != 0) FileManager.deleteFile(String.format("%s/%s", uploadDir, org_imgFileNm ) );
						
					for(EgovFormBasedFileVo file:files) {
						vo.setLnk_imgFileNm(String.format("%s/%s", file.getServerSubPath(), file.getPhysicalName()) );
						vo.setLnk_imgPath(file.getServerSubPath());
					}
				}
				
				rtnVal = linkService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Link : %s (%s), %s~%s status : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getLnk_nm(), vo.getLgp_sn()
										, vo.getLnk_startDt(), vo.getLnk_endDt()
										, vo.getLnk_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode()) 
			{
				LinkVo delVo = linkService.select(vo.getLnk_sn());
				
				rtnVal = linkService.delete(vo.getLnk_sn());
				if(rtnVal == 0) {
					message = "삭제실패 : 삭제 처리 중 오류가 발생하였습니다!";
				}
				FileManager.deleteFile(String.format("%s/%s", uploadDir, delVo.getLnk_imgFileNm() ) );
				
				log_what = String.format("delete Link : %s (%s), %s~%s status : %s\n%s"
										, delVo.getLnk_nm(), delVo.getLgp_sn()
										, delVo.getLnk_startDt(), delVo.getLnk_endDt()
										, delVo.getLnk_sttus()
										, message
									);
				
			}
			else if(cmd == Code.Prm.PrmAdm.getCode())
			{
				String[] arrayChkLnkSn = request.getParameterValues("chkLnkSn");
				
				if(arrayChkLnkSn != null) {
					int successCnt = 0;
					int failCnt = 0;
					for(String value:arrayChkLnkSn) {
						
						int lnk_sn = Util.nvl(value, 0);
						LinkVo delVo = linkService.select(lnk_sn);
						
						rtnVal = linkService.delete(lnk_sn);
						
						if(rtnVal == 0) {
							failCnt++;
						}else {
							successCnt++;
							FileManager.deleteFile(String.format("%s/%s", uploadDir, delVo.getLnk_imgFileNm() ) );
						}
					}
					
					log_what = String.format("Link 목록 선택 삭제\n선택 : %s\n삭제 성공 : %s\n삭제실패 : %s", arrayChkLnkSn.length, successCnt, failCnt);
				}
			}
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
