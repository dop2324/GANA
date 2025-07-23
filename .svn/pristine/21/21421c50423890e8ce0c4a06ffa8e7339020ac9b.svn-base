package egovframework.dnworks.controller.sitemanager.cms.menu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.PageService;
import egovframework.dnworks.cms.menu.service.PageVo;

@Controller
@RequestMapping("/SiteManager/cms/menu/page")
public class PageController extends WebDefault 
{
	@Autowired
    private MenuService menuService;
	
	@Autowired
    private PageService pageService;
	
	private String pagejspSavePath 	= Util.nvl(EgovProperties.getProperty("page.jsp.save.path"));
	private int pagejspDeleteMonth 	= Util.nvl(EgovProperties.getProperty("page.jsp.delete.month"), -1);
	
	/*
	 * 페이지 관리
	 */
	@RequestMapping("/page.do")
	public String page(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
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
		
		return managerDir+"/cms/menu/page/page";
	}
	
	/*
	 * 메뉴트리
	 */
	@RequestMapping("/page_tree.do")
	public String page_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
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
		
		return managerDir+"/cms/menu/page/page_tree";
	}
	
	/*
	 * 페이지 관리 form
	 */
	@RequestMapping("/page_form.do")
	public String page_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		int page_sn 		= Util.nvl(request.getParameter("page_sn"), 0);
		int cmd 			= page_sn != 0 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();		
		
		String returnUrl 	= Util.nvl(request.getAttribute("javax.servlet.forward.servlet_path"));
		String queryString	= (String) request.getAttribute("queryString");	
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		model.addAttribute("queryString"		, queryString);
		
		PageVo pageVo = null;
		MenuVo menuVo = menuService.select(parm_mnuCode);
		
		if(menuVo != null) {
			if(!menuVo.getMnu_ty().equals("page") 
					&& !menuVo.getMnu_ty().equals("menu+page")
					&& !menuVo.getMnu_ty().equals("page+program") 
					&& !menuVo.getMnu_ty().equals("page+board")) 
			{
				model.addAttribute("message", "메뉴 형식이 페이지가 아닙니다");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			if(page_sn != 0) {
				pageVo = pageService.select(page_sn);
				pageVo = this.pageContent(pageVo);
			}
		}
		List<PageVo> pageList = pageService.selectList(parm_mnuCode);
		
		model.addAttribute("menuVo"			, menuVo);
		model.addAttribute("pageVo"			, pageVo);
		model.addAttribute("pageList"		, pageList);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("cmd"			, cmd);

		return managerDir+"/cms/menu/page/page_form";
	}
	
	/*
	 * 페이지 보기
	 */
	@RequestMapping(value="/page_viewJson.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String page_viewJson(HttpServletRequest request, HttpSession session) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("parm_mnuCode"));
		
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		if(!mnu_code.equals("")) {
			PageVo vo = pageService.selectMenuCode(mnu_code);
			
			if(vo == null) {
				json.put("message"	, "메뉴형식이 페이지가 아닙니다");
			} else {
				vo = this.pageContent(vo);
				json.put("result"	, 1);
				json.put("mnu_nm"	, vo.getMnu_nm());
				json.put("mnu_sttus", vo.getMnu_sttus());
				json.put("page_ttl"	, vo.getPage_ttl());
				json.put("page_cn"	, vo.getPage_cn());
			}
		}
		
		return json.toString();
	}
	@RequestMapping(value="/page_view.do")
	public String page_view(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("parm_mnuCode"));
		if(mnu_code.equals("")) {
			mnu_code = Util.nvl(request.getParameter("mnu_code"));
		}
		
		PageVo pageVo = pageService.selectMenuCode(mnu_code);
		pageVo = this.pageContent(pageVo);
		
		model.addAttribute("pageVo", pageVo);
		return managerDir+"/cms/menu/page/page_view";
	}
	
	private PageVo pageContent(PageVo vo) {
		if(vo != null) {
			String jspFilePath = super.menuPathDir(vo.getMnu_code());

			//jsp 파일 사용 (page_saveTy 1:DB, 0:jsp)
			if(vo.getPage_saveTy() == 0) {
				
				String mnu_code = vo.getMnu_code();

				//참조페이지 유무
				if(!Util.nvl(vo.getRef_code()).equals("")) {
					mnu_code = vo.getRef_code();
		  		}
				
				String jspFile = String.format("%s/%s", this.getJspPath(jspFilePath), this.getJspFileNm(mnu_code, vo.getPage_sn()));
				File file = new File(jspFile);
				if(!file.exists()) {
					vo.setPage_jspPath(jspFile+"<br/>파일이 존재하지 않습니다");
					return vo;
				} else {
					if(!file.isFile()) {
						vo.setPage_jspPath(jspFile+"<br/>파일이 존재하지 않습니다");
						return vo;
					} else {
						jspFile = String.format("page/cms_jsp%s/%s_%04d", jspFilePath, mnu_code, vo.getPage_sn());
					}
				}
				vo.setPage_jspPath(jspFile);
	  		}
		}
		
		return vo;
	}
	
	/*
	 * 페이지 처리
	 */
	@RequestMapping("/page_process.do")
	public String page_process(@ModelAttribute PageVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		int page_sn			= Util.nvl(request.getParameter("page_sn"), 0);
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		
		//db jsp 저장 구분
		int saveDB 	= Util.nvl(request.getParameter("saveDB"), 0);
		int saveJsp = Util.nvl(request.getParameter("saveJsp"), 0);
				
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s%s'", returnUrl, queryString, page_sn != 0 ? "page_sn="+page_sn:"");
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(parm_mnuCode);
			vo.setPage_saveTy(saveDB == 1 ? 1:0);
			vo.setPage_mdfcnID(Util.getSession(session, "USR_ID"));
			
			String jspFilePath = super.menuPathDir(parm_mnuCode);
			
			boolean delFlag = false;
			String jspFile = String.format("%s/%s", this.getJspPath(jspFilePath), this.getJspFileNm(vo.getMnu_code(), vo.getPage_sn()) );
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				//참조 확인
				if(!Util.nvl(vo.getRef_code()).equals("")) {
					PageVo refVo = pageService.selectMenuCode(vo.getRef_code());
					if(refVo == null) {
						model.addAttribute("message", "(페이지 참조 불가)\\n참조 페이지 메뉴가 없거나 사용 중 상태가 아닙니다.");
						model.addAttribute("location", location);
						return "common/scriptAlert";
					}else if(!Util.nvl(refVo.getRef_code()).equals("")) {
						model.addAttribute("message", "(중복 참조 불가)\\n다른 페이지(mnu_code:"+refVo.getRef_code()+")를 참조 하고 있습니다");
						model.addAttribute("location", location);
						return "common/scriptAlert";
					}
				}
				// <figure> 태그 제거
				/*
				String page_cn = vo.getPage_cn();
				page_cn = page_cn.replaceAll("<figure(.*?)>", "");   
				page_cn = page_cn.replaceAll("</figure>", "");
				vo.setPage_cn(page_cn);
				*/
				rtnVal = pageService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				boolean saveFlag = false;
				if(saveJsp == 1) {
					if(cmd == Code.Prm.PrmIns.getCode()) {
						vo.setPage_sn(vo.getKey_sn());
					}
					
					saveFlag = this.saveJspFile(jspFilePath, vo);
				}else if(saveDB == 1){
					delFlag = FileManager.deleteFile(jspFile);
				}
				
				log_what = String.format("%s Page : %s%s\nstatus : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getMnu_code()
										, saveJsp == 1 ? String.format("\njsp저장 : %s", saveFlag):String.format("\njsp삭제 : %s", delFlag)
										, vo.getPage_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmRpl.getCode())
			{
				//jsp -> DB
				delFlag = FileManager.deleteFile(jspFile);
				
				vo.setPage_saveTy(1);
				rtnVal = pageService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("JSP -> DB Page : %s\nstatus : %s\n%s"
										, vo.getMnu_code()
										, vo.getPage_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode())
			{
				//페이지 사용처리
				rtnVal = pageService.updateSttus(vo);
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("Sttus Page : %s\nstatus : %s\n%s"
										, vo.getMnu_code()
										, vo.getPage_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				PageVo pageVo = pageService.select(vo.getPage_sn());
				
				rtnVal = pageService.delete(vo.getPage_sn());
				
				if(rtnVal == 0) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				
				//jsp 파일 삭제
				if(pageVo.getPage_saveTy() == 0) {
					delFlag = FileManager.deleteFile(jspFile);
				}
				
				log_what = String.format("Delete Page : %s%s\nstatus : %s\n%s"
									, vo.getMnu_code()
									, pageVo.getPage_saveTy() == 1 ? String.format("\njsp삭제 : %s", delFlag):""
									, pageVo.getPage_sttus()
									, message
								);
			}
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
	
	private boolean saveJspFile(String jspFilePath, PageVo vo) throws IOException
	{
		String jspFile = String.format("%s/%s", this.getJspPath(jspFilePath), this.getJspFileNm(vo.getMnu_code(), vo.getPage_sn()));
		
		if(vo != null) {
			//참조 확인
			if(Util.nvl(vo.getRef_code()).equals("")) {
				FileManager.createDir(this.getJspPath(jspFilePath));
				vo.setPage_cn(String.format("<%%@ page contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%%>\n\n%s", vo.getPage_cn()));
				return FileManager.makeFile(jspFile, vo.getPage_cn());
			}
		}
		
		return false;
	}
	
	private String readJspFile(String jspFilePath, String mnu_code, int page_sn) {
		
		String jspFile = String.format("%s/%s", this.getJspPath(jspFilePath), this.getJspFileNm(mnu_code, page_sn));
		String page_cn = FileManager.readFile(jspFile);
		
		page_cn = page_cn.replaceAll("<%@ page contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>", "");
		return page_cn;
	}
	
	//read write
	private String getJspPath(String jspFilePath) {
  		return String.format("%s/%s%s", EgovProperties.getProperty("Globals.SystemPath"), pagejspSavePath, jspFilePath);
  	}
	private String getJspFileNm(String mnu_code, int page_sn) {
  		return String.format("%s_%04d.jsp", mnu_code, page_sn);
  	}
}
