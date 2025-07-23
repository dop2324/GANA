package egovframework.dnworks.controller.sitemanager.cms.link;

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

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.link.service.LinkGroupService;
import egovframework.dnworks.cms.link.service.LinkGroupVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/link/group")
public class LinkGroupController extends WebDefault
{
	@Autowired
	private LinkGroupService linkGroupService;
	
	/*
	 * linkGroup
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/group.do")
	public String group(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
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

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);

		return managerDir+"/cms/link/group/group";
	}
	
	/*
	 * linkGroup list
	 */
	@RequestMapping("/group_list.do")
	public String group_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);

		Map<String, Object> map = new HashMap<>();
		map.put("site_code", site_code);
		List<LinkGroupVo> groupList = linkGroupService.selectList(map);
		model.addAttribute("groupList"		, groupList);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);


		return managerDir+"/cms/link/group/group_list";
	}
	
	/*
	 * linkGroup 처리
	 */
	@RequestMapping("/group_process.do")
	public String group_process(@ModelAttribute LinkGroupVo vo, BindingResult bindingResult
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
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				rtnVal = linkGroupService.save(vo);
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("%s LinkGroup : %s %s sttus:%s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getLgp_nm(), vo.getLgp_se()
										, vo.getLgp_sttus()
										, message
									);

			}else if(cmd == Code.Prm.PrmDel.getCode() ) {
				
				LinkGroupVo delVo = linkGroupService.select(vo.getLgp_sn());
				rtnVal = linkGroupService.delete(vo.getLgp_sn());
				if(rtnVal == 0) {
					message = "삭제실패 : 처리중 오류가 발생하였습니다.";
				}
				log_what = String.format("delete LinkGroup : %s %s sttus:%s\n%s"
									, delVo.getLgp_nm(), delVo.getLgp_se()
									, delVo.getLgp_sttus()
									, message
								);
			}
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
