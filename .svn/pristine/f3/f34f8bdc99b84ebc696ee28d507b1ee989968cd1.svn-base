package egovframework.dnworks.controller.sitemanager.cms.board.info;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import egovframework.dnworks.cms.board.info.service.BoardHeadColumnVo;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;

@Controller
@RequestMapping("/SiteManager/cms/board/info")
public class BoardInfoController extends WebDefault
{
	@Autowired
    private MenuService menuService;
	
	@Autowired
	private BoardInfoService boardInfoService;
	
	/*
	 * boardInfo
	 */
	@RequestMapping("/info.do")
	public String info(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code = Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));
		
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"));

		String frmTy 			= Util.nvl(request.getParameter("frmTy"), "board");
		
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
		model.addAttribute("frmTy"			, frmTy);
		
		return managerDir+"/cms/board/info/info";
	}
	
	/*
	 * 메뉴트리
	 */
	@RequestMapping("/board_tree.do")
	public String board_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("site_code"	, site_code);
		//SiteVo siteVo = siteService.select(map);
		LinkedHashMap<String, MenuVo> menuTree = menuService.selectTree(map);
		model.addAttribute("menuTree"		, menuTree);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		return managerDir+"/cms/board/info/board_tree";
	}
	

	@RequestMapping("/info_form.do")
	public String info_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String parm_mnuCode 	= Util.nvl(request.getParameter("parm_mnuCode"));
		
		BoardInfoVo infoVo = null;
		
		if(!parm_mnuCode.equals("")) {
			infoVo = boardInfoService.select(parm_mnuCode);
		}
		int cmd = infoVo == null ? Code.Prm.PrmIns.getCode():Code.Prm.PrmUpd.getCode();
		
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("infoVo"			, infoVo);
		model.addAttribute("cmd"			, cmd);
		
		return managerDir+"/cms/board/info/info_form";
	}
	
	@RequestMapping(value = "/info_process.do", method = RequestMethod.POST)
	public String info_process(@ModelAttribute BoardInfoVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		String parm_mnuCode	= Util.nvl(request.getParameter("parm_mnuCode"));
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(parm_mnuCode);
			vo.setInf_mdfcnID(Util.getSession(session, "USR_ID"));
			
			//header
			BoardHeadColumnVo boardHeadColumnVo = new BoardHeadColumnVo();
			boardHeadColumnVo.setMnu_code(			vo.getMnu_code());
			boardHeadColumnVo.setBhc_visibleNo(		Util.nvl(request.getParameter("bhc_visibleNo"), 0));
			boardHeadColumnVo.setBhc_visibleGroup(	Util.nvl(request.getParameter("bhc_visibleGroup"), 0));
			boardHeadColumnVo.setBhc_visibleTtl(	Util.nvl(request.getParameter("bhc_visibleTtl"), 0));
			boardHeadColumnVo.setBhc_visibleNm(		Util.nvl(request.getParameter("bhc_visibleNm"), 0));
			boardHeadColumnVo.setBhc_visibleDept(	Util.nvl(request.getParameter("bhc_visibleDept"), 0));
			boardHeadColumnVo.setBhc_visibleDate(	Util.nvl(request.getParameter("bhc_visibleDate"), 0));
			boardHeadColumnVo.setBhc_visibleFile(	Util.nvl(request.getParameter("bhc_visibleFile"), 0));
			boardHeadColumnVo.setBhc_visibleReadCnt(Util.nvl(request.getParameter("bhc_visibleReadCnt"), 0));
			vo.setBoardHeadColumnVo(boardHeadColumnVo);
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				rtnVal = boardInfoService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Board Info : mnu_code : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getMnu_code()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				rtnVal = boardInfoService.delete(vo.getMnu_code());
				
				if(rtnVal == -3) {
					message = "삭제실패 : 분류 정보  삭제 처리 중 오류가 발생하였습니다!";
				}else if(rtnVal == -2) {
					message = "삭제실패 : 등록된 게시물이 있습니다. 게시물을 먼저 삭제하세요!";
				}else if(rtnVal == 0) {
					message = "삭제실패 : 삭제 처리중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Board Info : mnu_code : %s\n%s"
										, vo.getMnu_code()
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
