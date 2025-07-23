package egovframework.dnworks.controller.webcontent.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.search.service.SearchService;

@Controller
@RequestMapping("/WebContent/search")
public class WebSearchController extends WebDefault
{
	@Autowired
	private SearchService searchService;
	
	@Autowired
	protected BoardService boardService;
	
	private String search_content 	= EgovProperties.getProperty("Globals.PublicPath")+"/search/content";
	
	private String page_list 		= EgovProperties.getProperty("Globals.PublicPath")+"/search/list_page";
	private String board_list 		= EgovProperties.getProperty("Globals.PublicPath")+"/search/list_board";
	private String org_list 		= EgovProperties.getProperty("Globals.PublicPath")+"/search/list_org";
	
	@RequestMapping("/content.do")
	public String content(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		String ctgry 	= Util.nvl(request.getParameter("ctgry"));
		String kwd 		= Util.nvl(request.getParameter("kwd"));
		
		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("ctgry"		, "");
		udp.put("kwd"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("ctgry"			, ctgry);
		model.addAttribute("kwd"			, kwd);
		
		return search_content;
	}
	
	private String getQueryString(HttpServletRequest request) {
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("ctgry"	, "");
		udp.put("kwd"	, "");
		return super.getParameters(request, udp, true, false);
	}
	
	/*
	 * page 호출
	 */
	@RequestMapping(value={"/page.do"})
	public String page(HttpServletRequest request, ModelMap model) throws Exception {
		
		String mnu_nm 		= Util.nvl(request.getParameter("mnu_nm"), "");
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 10);
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		String queryString 	= this.getQueryString(request);
		
		String kwd 			= Util.nvl(request.getParameter("kwd"));
		//boardList
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"	, pageNo);
		map.put("pageSize"	, pageSize);
		map.put("offset"	, (pageNo - 1) * pageSize);
		map.put("kwd"		, kwd);
		map.put("prm_id"	, Util.getSession(request.getSession(), "GRP_ID"));
		
		int totalCnt = searchService.pageSelectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		int totalPage = (totalCnt == 0) ? 1 : ((totalCnt/pageSize)+1);
		List<Map<String, Object>> pageList = searchService.pageSelectList(map);
		
		if(pageList != null) {
			for(Map<String, Object> m:pageList) {
				String page_cn = Util.nvl(m.get("page_cn"));
				if(!page_cn.equals("")) {
					page_cn = Util.removeTag(page_cn);
				}
				m.put("page_cn", page_cn);
			}
		}
		model.addAttribute("mnu_nm"		, mnu_nm);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("no"			, no);
		model.addAttribute("totalPage"	, totalPage);
		model.addAttribute("pageList"	, pageList);
		model.addAttribute("queryString", queryString);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
		
		return page_list;
	}
	
	/*
	 * 게시판 호출
	 */
	@RequestMapping(value={"/board.do"})
	public String board(HttpServletRequest request, ModelMap model) throws Exception {
		
		String mnu_nm 		= Util.nvl(request.getParameter("mnu_nm"), "");
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 10);
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		String queryString 	= this.getQueryString(request);
		
		String kwd 		= Util.nvl(request.getParameter("kwd"));
		//boardList
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"	, pageNo);
		map.put("pageSize"	, pageSize);
		map.put("offset"	, (pageNo - 1) * pageSize);
		map.put("kwd"		, kwd);
		map.put("prm_id"	, Util.getSession(request.getSession(), "GRP_ID"));
		
		int totalCnt = searchService.boardSelectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		int totalPage = (totalCnt == 0) ? 1 : ((totalCnt/pageSize)+1);
		List<Map<String, Object>> boardList = searchService.boardSelectList(map);
		
		if(boardList != null) {
			for(Map<String, Object> m:boardList) {
				String con_cn = Util.nvl(m.get("con_cn"));
				if(!con_cn.equals("")) {
					con_cn = Util.removeTag(con_cn);
				}
				m.put("con_cn", con_cn);
			}
		}
		
		model.addAttribute("mnu_nm"		, mnu_nm);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("no"			, no);
		model.addAttribute("totalPage"	, totalPage);
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("queryString", queryString);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
		
		return board_list;
	}
	
	/*
	 * 직원 호출
	 */
	@RequestMapping(value={"/org.do"})
	public String org(HttpServletRequest request, ModelMap model) throws Exception {
		
		String mnu_nm 		= Util.nvl(request.getParameter("mnu_nm"), "");
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 10);
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		String queryString 	= this.getQueryString(request);
		
		String kwd 			= Util.nvl(request.getParameter("kwd"));
		//boardList
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"	, pageNo);
		map.put("pageSize"	, pageSize);
		map.put("offset"	, (pageNo - 1) * pageSize);
		map.put("kwd"		, kwd);
		
		List<Map<String, Object>> orgList = searchService.orgSelectList(map);
		
		model.addAttribute("mnu_nm"		, mnu_nm);
		model.addAttribute("orgList"	, orgList);
		model.addAttribute("queryString", queryString);
		
		return org_list;
	}
}
