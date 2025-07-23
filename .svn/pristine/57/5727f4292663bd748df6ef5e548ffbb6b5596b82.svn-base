package egovframework.dnworks.controller.sitemanager.cms.accessip;

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
import egovframework.dnworks.cms.accessip.service.AccessIPService;
import egovframework.dnworks.cms.accessip.service.AccessIPVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/accessip")
public class AccessIPController extends WebDefault
{
	@Autowired
	private AccessIPService accessIPService;
	
	/*
	 * accessip
	 */
	@RequestMapping("/accessip.do")
	public String accessip(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
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

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		List<AccessIPVo> accessipList = accessIPService.selectList(site_code);
		model.addAttribute("accessipList"	, accessipList);
		
		model.addAttribute("siteList"		, siteList);
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);

		return managerDir+"/cms/accessip/accessip";
	}
	
	@RequestMapping("/accessip_list.do")
	public String accessip_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);
		
		List<AccessIPVo> accessipList = accessIPService.selectList(site_code);
		model.addAttribute("accessipList"	, accessipList);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		
		return managerDir+"/cms/accessip/accessip_list";
	}	
	
	/*
	 * accessip 처리
	 */
	@RequestMapping("/accessip_process.do")
	public String accessip_process(@ModelAttribute AccessIPVo vo, BindingResult bindingResult
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
			vo.setIp_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode())
			{
				rtnVal = accessIPService.insert(vo);
				if(rtnVal == -1) {
					message = "입력실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("insert AccessIP : %s ~ %s %s useYn:%s\n%s"
										, vo.getIp_startIP(), !vo.getIp_endIP().equals("") ? vo.getIp_endIP():""
										, vo.getIp_desc(), vo.getIp_sttus()
										, message
									);
			}else if(cmd == Code.Prm.PrmUpd.getCode()) {
				rtnVal = accessIPService.update(vo);
				if(rtnVal == -1) {
					message = "수정실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("update AccessIP : %s ~ %s %s useYn:%s\n%s"
										, vo.getIp_startIP(), !vo.getIp_endIP().equals("") ? vo.getIp_endIP():""
										, vo.getIp_desc(), vo.getIp_sttus()
										, message
									);
			}else if(cmd == Code.Prm.PrmDel.getCode() ) {
				
				AccessIPVo delVo = accessIPService.select(vo.getIp_sn());
				rtnVal = accessIPService.delete(vo.getIp_sn());
				if(rtnVal == 0) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("delete AccessIP : %s ~ %s %s useYn:%s\n%s"
									, delVo.getIp_startIP(), !delVo.getIp_endIP().equals("") ? delVo.getIp_endIP():""
									, delVo.getIp_desc(), delVo.getIp_sttus()
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
