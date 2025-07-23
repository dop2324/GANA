package egovframework.dnworks.controller.sitemanager.cms.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.SiteService;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager/cms/menu/site")
public class SiteController extends WebDefault
{
	@Autowired
	private SiteService siteService;
	
	/*
	 * 사이트
	 */
	@RequestMapping("/site.do")
	public String site(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String location 	= null;
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);
		

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("srchSe"		, "");
		udp.put("srchUseYn"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		
		switch(cmd)
		{
			case 16	:
			case 4 :
					this.select(request, session, model, queryString);			
					location = managerDir+"/cms/menu/site/site_form";
					break;
			default :			
					this.list(request, session, model, queryString);			
					location = managerDir+"/cms/menu/site/site";
					break;
		}
		return location;

	}
	
	/*
	 * Code 중복 확인
	 */
	@RequestMapping(value="/checkExistCode.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String checkExistCode(HttpServletRequest request, HttpSession session) throws Exception
	{
		String site_code = Util.nvl(request.getParameter("site_code"));
		
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		if(!site_code.equals("")) {
			
			Map<String, Object> map = new HashMap<>();
			map.put("site_code", site_code);
			SiteVo vo = siteService.select(map);
			
			if(vo != null) json.put("result", 1);
		}
		
		return json.toString();
	}
	
	/* list */
	public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchUseYn 	= Util.nvl(request.getParameter("srchUseYn"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srchSe"	, srchSe);
		map.put("srchUseYn"	, srchUseYn);
		map.put("srchKwd", srchKwd);
		List<SiteVo> siteList = siteService.selectList(map);
		
		model.addAttribute("siteList"		, siteList);
		model.addAttribute("srchMap"		, map);
	}
	
	/* select */
	public void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		String site_code = Util.nvl(request.getParameter("site_code"));
		int cmd			= !site_code.equals("") ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		SiteVo vo = null;
		if(cmd == Code.Prm.PrmUpd.getCode())
		{
			Map<String, Object> map = new HashMap<>();
			map.put("site_code", site_code);
			vo = siteService.select(map);
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
	}
	
	/*
	 * 사이트 처리
	 */
	@RequestMapping("/site_process.do")
	public String site_process(@ModelAttribute SiteVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		int rtnVal 		= -1;
		String message 	= "";
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setSite_regID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				//사이트 코드 입력 
				if(cmd == Code.Prm.PrmIns.getCode()) {
					Map<String, Object> chkMap = new HashMap<>();
					chkMap.put("site_code", vo.getSite_code());
					SiteVo siteVo = siteService.select(chkMap);
					
					if(siteVo != null) {
						model.addAttribute("message", vo.getSite_code()+" 사이트 코드 중복 입니다");
						model.addAttribute("location", location);
						return "common/scriptAlert";
					}
				}else if(cmd == Code.Prm.PrmUpd.getCode()) {
					location 	= String.format("location.href='%s?%scmd=16&site_code=%s'", returnUrl, queryString, vo.getSite_code());
				}
				
				rtnVal = siteService.save(vo, cmd);
				
				if(rtnVal == -1) {
					message 	= "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Site : %s (%s)\n%s status : %s\n%s"
											, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
											, vo.getSite_nm(), vo.getSite_code()
											, vo.getSite_directory()
											, vo.getSite_useYn()
											, message
										);
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				rtnVal = siteService.deleteSiteUseYn(vo.getSite_code());
				if(rtnVal == 0){
					message = "삭제실패 : 삭제 처리 중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Site : %s (%s)\n%s"
										, vo.getSite_nm(), vo.getSite_code()
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
