package egovframework.dnworks.controller.sitemanager.cms.board.info;

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

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardPublicService;
import egovframework.dnworks.cms.board.info.service.BoardPublicVo;
import egovframework.dnworks.cms.board.info.service.BoardShareService;
import egovframework.dnworks.cms.board.info.service.BoardShareVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;

@Controller
@RequestMapping("/SiteManager/cms/board/info/share")
public class BoardShareController extends WebDefault
{
	@Autowired
	private BoardPublicService boardPublicService;
	
	@Autowired
	private BoardShareService boardShareService;
	
	@Autowired
	private MenuService menuService;
	
	/*
	 * publicFrame
	 */
	@RequestMapping("/share.do")
	public String share(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code 	= Util.nvl(request.getParameter("site_code"), defaultSite);
		String bod_mnuCode 	= Util.nvl(request.getParameter("bod_mnuCode"));
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "public");
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("bod_mnuCode"	, "");
		udp.put("frmTy"			, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("bod_mnuCode"	, bod_mnuCode);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("frmTy"			, frmTy);
		
		return managerDir+"/cms/board/info/share/share";
	}
	
	/*
	 * public list
	 */
	@RequestMapping("/public_list.do")
	public String public_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String bod_mnuCode 	= Util.nvl(request.getParameter("bod_mnuCode"));
		String pub_code 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String queryString	= (String) request.getAttribute("queryString");
		
		model.addAttribute("bod_mnuCode"	, bod_mnuCode);
		model.addAttribute("queryString"	, queryString);
		
		List<BoardPublicVo> publicList = boardPublicService.selectList(bod_mnuCode);
		model.addAttribute("publicList"		, publicList);
		
		boolean existsFlag = false;
		MenuVo menuVo = null;
		if(!pub_code.equals("")) {
			menuVo = menuService.select(pub_code);
			
			for(BoardPublicVo v:publicList) {
				if(v.getPub_code().equals(pub_code)) {
					existsFlag = true;
					break;
				}
			}
		}
		model.addAttribute("menuVo"			, menuVo);
		model.addAttribute("existsFlag"		, existsFlag);

		return managerDir+"/cms/board/info/share/public_list";
	}
	
	@RequestMapping(value = "/public_process.do", method = RequestMethod.POST)
	public String public_process(@ModelAttribute BoardPublicVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 4);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		String bod_mnuCode	= Util.nvl(request.getParameter("bod_mnuCode"));
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(bod_mnuCode);

			if(cmd == Code.Prm.PrmIns.getCode())
			{
				rtnVal = boardPublicService.insert(vo);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("insert Board Info Public\nmenu_code : %s, pub_code : %s\n%s"
										, vo.getMnu_code(), vo.getPub_code()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				rtnVal = boardPublicService.delete(vo);
				if(rtnVal == 0) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("delete Board Info Public\nmenu_code : %s, pub_code : %s\n%s"
										, vo.getMnu_code(), vo.getPub_code()
										, message
									);
			}
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
	
	
	/*
	 * share list
	 */
	@RequestMapping("/share_list.do")
	public String share_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String bod_mnuCode 	= Util.nvl(request.getParameter("bod_mnuCode"));
		String shr_code 	= Util.nvl(request.getParameter("parm_mnuCode"));
		String queryString	= (String) request.getAttribute("queryString");
		
		model.addAttribute("bod_mnuCode"	, bod_mnuCode);
		model.addAttribute("queryString"	, queryString);
		
		List<BoardShareVo> shareList = boardShareService.selectList(bod_mnuCode);
		model.addAttribute("shareList"		, shareList);
		
		boolean existsFlag = false;
		MenuVo menuVo = null;
		if(!shr_code.equals("")) {
			menuVo = menuService.select(shr_code);
			
			for(BoardShareVo v:shareList) {
				if(v.getShr_code().equals(shr_code)) {
					existsFlag = true;
					break;
				}
			}
		}
		model.addAttribute("menuVo"			, menuVo);
		model.addAttribute("existsFlag"		, existsFlag);
		
		return managerDir+"/cms/board/info/share/share_list";
	}
	
	@RequestMapping(value = "/share_process.do", method = RequestMethod.POST)
	public String share_process(@ModelAttribute BoardShareVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 4);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		String bod_mnuCode	= Util.nvl(request.getParameter("bod_mnuCode"));
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(bod_mnuCode);

			if(cmd == Code.Prm.PrmIns.getCode())
			{
				rtnVal = boardShareService.insert(vo);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("insert Board Info Public\nmenu_code : %s, pub_code : %s\n%s"
										, vo.getMnu_code(), vo.getShr_code()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				rtnVal = boardShareService.delete(vo);
				if(rtnVal == 0) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("delete Board Info Public\nmenu_code : %s, pub_code : %s\n%s"
										, vo.getMnu_code(), vo.getShr_code()
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
