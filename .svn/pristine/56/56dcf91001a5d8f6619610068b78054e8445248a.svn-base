package egovframework.dnworks.controller.webcontent.cms.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardDeptService;
import egovframework.dnworks.cms.board.service.BoardDeptVo;

@Controller
@RequestMapping("/WebContent/cms/board/dept")
public class WebBoardDeptController extends WebDefault
{
	
	@Autowired
	private BoardDeptService boardDeptService;
	
	/*
	 * comment
	 */									
	@RequestMapping(value = {"/dept_viewList.do"} )
	public String dept_viewList(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		BoardInfoVo infoVo 	= (BoardInfoVo) request.getAttribute("infoVo");
		String queryString 	= (String) request.getAttribute("queryString");
		boolean hasAdm		= (boolean) request.getAttribute("hasAdm");
		int bod_sn 			= Util.nvl(request.getParameter("bod_sn"), 0);
		
		//답변목록
		List<BoardDeptVo> boardDeptList = boardDeptService.selectList(bod_sn);

		model.addAttribute("infoVo"			, infoVo);
		model.addAttribute("queryString"	, queryString);
		model.addAttribute("hasAdm"			, hasAdm);
		
		model.addAttribute("bod_sn"			, bod_sn);
		model.addAttribute("boardDeptList"	, boardDeptList);
		
		return publicPath+"/cms/board/dept/dept_viewList";
	}
}
