package egovframework.dnworks.controller.webcontent.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.link.service.LinkClickService;
import egovframework.dnworks.cms.link.service.LinkClickVo;
import egovframework.dnworks.cms.link.service.LinkService;
import egovframework.dnworks.cms.link.service.LinkVo;

@Controller
public class WebCommonController extends WebDefault
{
	@Autowired
	protected LinkService linkService;
	
	@Autowired
	protected LinkClickService linkClickService;
	
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	protected BoardService boardService;
	
	/*
	 * identity
	 * 본인확인, ipin
	 */
	
	@RequestMapping(value={"/common/cert/identity.do"})
	public String identity(HttpServletRequest request, ModelMap model) throws Exception{
		
		String returnUrl 	= (String) request.getParameter("returnUrl");
		model.addAttribute("certReturnUrl"		, returnUrl);
		return "common/cert/identity";
	}
	
	@RequestMapping(value={"/common/logout.do"})
	public String logout(HttpServletRequest request, ModelMap model) throws Exception{
		
		return "forward:/WebContent/cms/member/logout.do";
	}
	
	@RequestMapping("/common/link/linkView.do")
	public String link(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		int lnk_sn	= Util.nvl(request.getParameter("id"), 0);
		if(lnk_sn > 0) {
			LinkVo linkVo = linkService.select(lnk_sn);
			
			LinkClickVo vo = new LinkClickVo();
			vo.setLnk_sn(lnk_sn);
			vo.setClick_agent(request.getHeader("user-agent"));
			vo.setClick_ip(request.getRemoteAddr());
			vo.setClick_locale(Util.nvl(request.getLocale()));
			vo.setClick_referer(request.getHeader("referer"));
			
			linkClickService.insert(vo);
			return "redirect:"+linkVo.getLnk_linkUrl();
		}
		
		return null;
	}
	@RequestMapping("/common/link/openPopup.do")
	public String openPopup(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		int lnk_sn	= Util.nvl(request.getParameter("id"), 0);
		if(lnk_sn > 0) {
			LinkVo linkVo = linkService.select(lnk_sn);
			if(linkVo != null) {
				model.addAttribute("vo", linkVo);
			}
		}
		return "WebContent/common/inc_popup";
	}
	
	/*
	 * 링크 호출
	 */
	@RequestMapping(value={"/common/link/link.do"})
	public String link(HttpServletRequest request, ModelMap model) throws Exception {
		String include_path	= Util.nvl(request.getParameter("path"), "");
		List<LinkVo> linkList = this.getLinkList(request);
		model.addAttribute("linkList", linkList);

		return include_path;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value={"/common/link/linkJson.do"}, method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String  linkJson(HttpServletRequest request, ModelMap model) throws Exception {
		List<LinkVo> linkList = this.getLinkList(request);
		JSONArray jsonArray = new JSONArray();
		for(LinkVo v:linkList) {
			Map<String, Object> map = new ObjectMapper().convertValue(v, Map.class);
			jsonArray.put(map);
		}

		return jsonArray.toString();
	}
	
	private List<LinkVo> getLinkList(HttpServletRequest request) throws Exception {
		String lgp_sn 	= (String) request.getParameter("lgp_sn");
		String[] arrayLgp = null;
		if(lgp_sn != null) {
			arrayLgp = lgp_sn.split(",");
		}

		List<Integer> item = null;
		if(arrayLgp != null) {
			item = new ArrayList<Integer>();
			for(String s:arrayLgp) {
				item.add(Util.nvl(s, 0));
			}
		}
		return linkService.selectMainList(item);
	}
	
	/*
	 * 게시판 호출
	 */
	@RequestMapping(value={"/common/board/board.do"})                 // 단일뉴스 항목추출
	public String mainBoard(HttpServletRequest request, ModelMap model) throws Exception {
		
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String mnu_nm 		= Util.nvl(request.getParameter("mnu_nm"), "");
		String include_path	= Util.nvl(request.getParameter("path"), "");
		
		int pageSize 		= Util.nvl(request.getParameter("pageSize") , 5 );
		int pageNo 			= 1;
		
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String srchBgpSn 	= Util.nvl(request.getParameter("srchBgpSn"));
		String srchSttus 	= Util.nvl(request.getParameter("srchSttus"));
		String srchDelete 	= Util.nvl(request.getParameter("srchDelete"));
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"));
		String srchEDate	= Util.nvl(request.getParameter("srchEDate"));
		
		int srchBodAll 		= Util.nvl(request.getParameter("srchBodAll") , 0 );
		
		List<Integer> arrayMenus = Arrays.asList(Util.nvl(request.getParameter("mnu_uids"),"0").split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
		
		//게시판 설정
		BoardInfoVo infoVo = boardInfoService.select(mnu_code);
		if(infoVo == null) {
			return include_path;
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
		map.put("srchDelete"	, "0");
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
		model.addAttribute("boardList"	, boardList);
		
		return include_path;
	}
}
