package egovframework.dnworks.controller.webcontent.cms.menu;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.PageService;
import egovframework.dnworks.cms.menu.service.PageVo;

@Controller
@RequestMapping("/WebContent/cms/menu/page")
public class WebPageController extends WebDefault
{
	@Autowired
    private PageService pageService;
	
	private String pagejspSavePath 	= Util.nvl(EgovProperties.getProperty("page.jsp.save.path"));
	
	/*
	 * 페이지 보기
	 */
	@RequestMapping(value="/pageViewJson.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
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
	
	@RequestMapping(value="/pageView.do")
	public String page_view(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("parm_mnuCode"));
		if(mnu_code.equals("")) {
			mnu_code = Util.nvl(request.getParameter("mnu_code"));
		}
		
		PageVo pageVo = pageService.selectMenuCode(mnu_code);
		pageVo = this.pageContent(pageVo);
		
		model.addAttribute("pageVo", pageVo);
		return publicPath+"/cms/menu/page/page_view";
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
					return null;
				} else {
					if(!file.isFile()) {
						return null;
					} else {
						jspFile = String.format("page/cms_jsp%s/%s_%04d", jspFilePath, mnu_code, vo.getPage_sn());
					}
				}
				vo.setPage_jspPath(jspFile);
	  		}
		}
		
		return vo;
	}
	

	//read write
	private String getJspPath(String jspFilePath) {
  		return String.format("%s/%s%s", EgovProperties.getProperty("Globals.SystemPath"), pagejspSavePath, jspFilePath);
  	}
	private String getJspFileNm(String mnu_code, int page_sn) {
  		return String.format("%s_%04d.jsp", mnu_code, page_sn);
  	}
	
	/*
	private String readJspFile(String jspFilePath, String mnu_code, int page_sn) {
		String jspFile = String.format("%s/%s", this.getJspPath(jspFilePath), this.getJspFileNm(mnu_code, page_sn));
		String page_cn = FileManager.readFile(jspFile);
		
		if(page_cn == null) {
			return null;
		}else {
			page_cn = page_cn.replaceAll("<%@ page contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>\n\n", "");
		}
		return page_cn;
	}
	*/
}
