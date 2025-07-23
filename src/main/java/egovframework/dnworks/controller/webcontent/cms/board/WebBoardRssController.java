package egovframework.dnworks.controller.webcontent.cms.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/common/board")
public class WebBoardRssController extends WebDefault
{
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	private BoardService boardService;
	
	
	private String rss_location = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/rss/rss";
	private String rss_list 	= EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/rss/rss_list";
	
	/*
	 * rss
	 */
	@RequestMapping("/rss.do")
	public String rss(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		SiteVo siteVo		= (SiteVo) request.getAttribute("siteVo");
		
		List<BoardInfoVo> rssBoardList = boardInfoService.selectRssList(siteVo.getSite_code());
		model.addAttribute("rssBoardList"	, rssBoardList);
		model.addAttribute("currDomain"		, Util.getDomainInfo(request));
		
		return rss_location;
	}
	
	/*
	 * rss
	 */
	@RequestMapping("/rss_list.do")
	public String rss_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String mnu_nm 		= Util.nvl(request.getParameter("mnu_nm"), "");

		int pageSize 		= Util.nvl(request.getParameter("pageSize") , 10 );
		int pageNo 			= 1;
		
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String srchBgpSn 	= Util.nvl(request.getParameter("srchBgpSn"));
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"));
		String srchEDate	= Util.nvl(request.getParameter("srchEDate"));
		

		//게시판 설정
		BoardInfoVo infoVo = boardInfoService.select(mnu_code);
		
		if(infoVo.getInf_fncUsrRss() == 0) {
			model.addAttribute("message", "RSS 설정이 없습니다");
			model.addAttribute("location", "history.back()");
			return "common/scriptAlert";
		}

		//boardList
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		map.put("mnu_code"		, mnu_code);
		map.put("srchKwd"		, srchKwd);
		map.put("srchBgpSn"		, srchBgpSn);
		map.put("srchNotice"	, 0);
		map.put("srchSttus"		, 1);
		map.put("srchDelete"	, 0);	
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
		map.put("hasPrmAdm"		, false);
		//map.put("arrayMenus"	, arrayMenus);
		
		map.put("infFncAdmTerm"		, infoVo.getInf_fncAdmTerm());
		map.put("infFncAdmTermType"	, infoVo.getInf_fncAdmTermTy());
		map.put("infBoardPublic"	, infoVo.getBoardPublicList().size());
		List<Map<String, Object>> boardList = boardService.selectList(map);
		
		if(boardList != null) {
			for(Map<String, Object> m:boardList) {
				String con_cn = Util.nvl(m.get("con_cn"));
				if(!con_cn.equals("")) {
					con_cn = Util.removeTag(con_cn);
				}
				m.put("con_cn", con_cn);
			}
		}
		
		model.addAttribute("mnu_code"	, mnu_code);
		model.addAttribute("mnu_nm"		, mnu_nm);
		model.addAttribute("srchBgpSn"	, srchBgpSn);
		
		model.addAttribute("infoVo"		, infoVo);
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("currDomain"	, Util.getDomainInfo(request));
		
		return rss_list;
	}
}
