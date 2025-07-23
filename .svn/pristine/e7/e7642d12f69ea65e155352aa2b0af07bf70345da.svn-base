package egovframework.dnworks.controller.sitemanager.cms.menu;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;

@Controller
@RequestMapping("/SiteManager/cms/menu")
public class MenuController extends WebDefault
{
	@Autowired
    private MenuService menuService;
	
	
	/*
	 * 메뉴관리
	 */
	@RequestMapping("/menu.do")
	public String menu(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code = Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));
		
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String parm_mnuUprCode 	= Util.nvl(request.getParameter("parm_mnuUprCode"));
		String frmTy 			= Util.nvl(request.getParameter("frmTy"), "update");
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		udp.put("frmTy"			, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("parm_mnuUprCode", parm_mnuUprCode);
		model.addAttribute("frmTy"			, frmTy);
		
		return managerDir+"/cms/menu/menu";
	}
	
	/*
	 * 메뉴트리
	 */
	@RequestMapping("/menu_tree.do")
	public String menu_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String site_code 		= Util.nvl(request.getParameter("site_code"));
		String mnu_code 		= Util.nvl(request.getParameter("mnu_code"));
		String parm_mnuUprCode 	= Util.nvl(request.getParameter("parm_mnuUprCode"));
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"), parm_mnuUprCode);
		
		Map<String, Object> map = new HashMap<>();
		map.put("site_code"	, site_code);
		//SiteVo siteVo = siteService.select(map);
		LinkedHashMap<String, MenuVo> menuTree = menuService.selectTree(map);
		model.addAttribute("menuTree"		, menuTree);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		
		return managerDir+"/cms/menu/menu_tree";
	}
	
	/*
	 * 메뉴 Code 중복 확인
	 */
	@RequestMapping(value="/checkExistCode.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String checkExistCode(HttpServletRequest request, HttpSession session) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("parm_mnuCode"));
		
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		if(!mnu_code.equals("")) {
			MenuVo vo = menuService.select(mnu_code);
			
			if(vo != null) json.put("result", 1);
		}
		
		return json.toString();
	}
	
	/*
	 * 메뉴관리
	 */
	@RequestMapping("/menu_form.do")
	public String menu_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String mnu_code 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String mnu_uprCode 	= Util.nvl(request.getParameter("parm_mnuUprCode"));
		int cmd 			= 0;
		
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);
		
		MenuVo vo = null;
		if(!mnu_code.equals("")) {
			cmd = Code.Prm.PrmUpd.getCode();
			vo = menuService.select(mnu_code);
		} else if(!mnu_uprCode.equals("")) {
			cmd = Code.Prm.PrmIns.getCode();
			vo = menuService.select(mnu_uprCode);
			
			vo.setMnu_code(mnu_code);
			vo.setMnu_uprCode(mnu_uprCode);
			vo.setMnu_nm("");
			vo.setMnu_desc("");
			vo.setMnu_ty("menu");
			vo.setMnu_linkUrl("page.do");
			vo.setMnu_param("");
			
			//현재max 메뉴코드 
			vo.setMax_mnuCode(menuService.selectMaxMnuCode(mnu_uprCode));
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
		
		return managerDir+"/cms/menu/menu_form";
	}
	
	/*
	 * 메뉴처리
	 */
	@RequestMapping("/menu_process.do")
	public String menu_process(@ModelAttribute @Validated MenuVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{

		String site_code 		= Util.nvl(request.getParameter("site_code"));
		String mnu_code 		= Util.nvl(request.getParameter("mnu_code"));
		
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String parm_mnuUprCode 	= Util.nvl(request.getParameter("parm_mnuUprCode"));
		
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String updn 		= Util.nvl(request.getParameter("updn"));
		int lowApply 		= Util.nvl(request.getParameter("lowApply"),0);
		
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(parm_mnuCode);
			vo.setMnu_uprCode(parm_mnuUprCode);
			vo.setMnu_privacy(Util.nvl(request.getParameter("mnu_privacy"), "N"));
			
			vo.setMnu_regID(Util.getSession(session, "USR_ID"));
			vo.setMnu_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				if(cmd == Code.Prm.PrmIns.getCode()) {
					location 	= String.format("location.href='%s?frmTy=update&site_code=%s&mnu_code=%s&parm_mnuCode=%s'"
												, returnUrl
												, site_code
												, mnu_code
												, parm_mnuUprCode
												);
					//메뉴 코드 존재 확인
					MenuVo menuVo = menuService.select(vo.getMnu_code());
					if(menuVo != null) {
						model.addAttribute("message", "메뉴 코드가 존재 합니다");
						model.addAttribute("location", location);
						return "common/scriptAlert";
					}
					
					//mnu_param
					vo.setMnu_param(String.format("mnu_code=%s%s"
												, vo.getMnu_code()
												, !Util.nvl(vo.getMnu_param()).equals("") ? "":vo.getMnu_param()
												)
									);
				}
				
				rtnVal = menuService.save(vo, cmd, lowApply);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Menu : %s (%s)\n%s:%s?%s\nstatus : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getMnu_nm(), vo.getMnu_code()
										, vo.getMnu_ty(), vo.getMnu_linkUrl(), vo.getMnu_param()
										, vo.getMnu_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				MenuVo menuVo = menuService.select(vo.getMnu_code());
				
				rtnVal = menuService.delete(vo.getMnu_code());
				
				if(rtnVal == -2) {
					message 	= "삭제 실패 : 등록된 하위 메뉴가 있습니다. 하위 메뉴를 먼저 삭제하세요!";
				}else if(rtnVal == -3) {
					message 	= "삭제 실패 : 등록된 페이지가 있습니다. 페이지를 먼저 삭제하세요!";
				}else if(rtnVal == -4) {
					message 	= "삭제 실패 : 게시판 설정을 먼저 삭제하세요!";
				}else if(rtnVal == -5) {
					message 	= "삭제 실패 : 게시판 정보 삭제 중 오류 (게시물 존재)가 발생하였습니다! ";
				}else if(rtnVal == -6) {
					message 	= "삭제 실패 : 메뉴 삭제 순서 처리 중 오류가 발생하였습니다!";
				}else if(rtnVal == -7) {
					message 	= "삭제 실패 : 메뉴 삭제 처리 중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Menu : %s (%s)\n%s:%s?%s\n%s : %s"
										, vo.getMnu_nm(), vo.getMnu_code()
										, vo.getMnu_ty(), vo.getMnu_linkUrl(), vo.getMnu_param()
										, rtnVal, message
									);
				
				
				location 	= String.format("location.href='%s?frmTy=update&site_code=%s&mnu_code=%s&parm_mnuCode=%s'"
											, returnUrl
											, site_code
											, mnu_code
											, menuVo.getMnu_uprCode()
											);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode() )
			{
				Map<String, Object> map = new HashMap<>();
				map.put("mnu_code"	, vo.getMnu_code());
				map.put("UPDN"		, updn);
				rtnVal = menuService.updateOrder(map);
				
				if(rtnVal == -1) {
					message 	= "순서조정 : 처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("setOrder Menu : %s (%s)\n%s:%s?%s\nupdn : %s\n%s : %s"
										, vo.getMnu_nm(), vo.getMnu_code()
										, vo.getMnu_ty(), vo.getMnu_linkUrl(), vo.getMnu_param()
										, updn
										, rtnVal, message
									);
			}
			
			super.insertLog(request, log_what);
		
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
