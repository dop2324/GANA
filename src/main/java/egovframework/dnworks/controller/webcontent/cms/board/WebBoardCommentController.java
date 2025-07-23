package egovframework.dnworks.controller.webcontent.cms.board;

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
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardContentService;
import egovframework.dnworks.cms.board.service.BoardContentVo;
import egovframework.dnworks.cms.board.service.BoardService;

@Controller
@RequestMapping("/WebContent/cms/board/comment")
public class WebBoardCommentController extends WebDefault 
{
	@Autowired
	private BoardContentService boardContentService;
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * comment
	 */									
	@RequestMapping(value = {"/board_comment.do"} )
	public String board_comment(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		BoardInfoVo infoVo 	= (BoardInfoVo) request.getAttribute("infoVo");
		String queryString 	= (String) request.getAttribute("queryString");
		//boolean hasAdm		= (boolean) request.getAttribute("hasAdm");
		int bod_sn 			= Util.nvl(request.getParameter("bod_sn"), 0);
		
		//댓글목록
		Map<String, Object> map = new HashMap<>();
		map.put("bod_sn", bod_sn);
		map.put("con_ty", 0);
		List<BoardContentVo> commentList = boardContentService.selectList(map);
		
		model.addAttribute("infoVo"			, infoVo);
		model.addAttribute("queryString"	, queryString);
		//model.addAttribute("hasAdm"			, hasAdm);
		
		model.addAttribute("bod_sn"			, bod_sn);
		model.addAttribute("commentList"	, commentList);
		
		return publicPath+"/cms/board/board_comment";
	}
	
	/*
	 * 댓글 JSON
	 */
	@RequestMapping(value="/check_password.do", produces="text/plain; charset=utf-8")
	public @ResponseBody String boardCommentJson(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		int con_sn 		= Util.nvl(request.getParameter("con_sn"), 0);
		String con_pw 	= Util.nvl(request.getParameter("con_pw"));
		BoardContentVo vo = boardContentService.select(con_sn);

		JSONObject map = new JSONObject();
		map.put("result", 0);
		
		if(vo.getCon_pw().equals(AESUtil.encrypt(con_pw))) {
			map.put("result", 1);
			map.put("con_cn", vo.getCon_cn());
		}
		
		return map.toString();
	}
	
	@RequestMapping(value = "/comment_process.do", method = RequestMethod.POST)
	public String comment_process(@ModelAttribute BoardContentVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int bod_sn 			= Util.nvl(request.getParameter("bod_sn"), 0);
		String update_pw	= Util.nvl(request.getParameter("update_pw"));
		String update_cn	= Util.nvl(request.getParameter("update_cn"));
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%sbod_sn=%s&cmd=2'", returnUrl, queryString, bod_sn);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			BoardContentVo selVo = boardContentService.select(vo.getCon_sn());
			
			if(!Util.nvl(vo.getCon_pw()).equals("")) vo.setCon_pw(AESUtil.encrypt(vo.getCon_pw()));
			
			vo.setCon_id(Util.getSession(session, "USR_ID"));
			vo.setCon_regip(IPUtil.getXForwardedFor(request));
			vo.setCon_regDI(Util.getSession(session, "USR_DI", Util.getSession(session, "USR_ID")) );
			
			if(cmd == Code.Prm.PrmUpd.getCode() || cmd == Code.Prm.PrmDel.getCode()) {
				if(!selVo.getCon_pw().equals(AESUtil.encrypt(update_pw))) {
					model.addAttribute("message", "비밀번호가 맞지 않습니다");
					model.addAttribute("location", location);
					return "common/scriptAlert";
				}
			}
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode() )
			{
				if(cmd == Code.Prm.PrmIns.getCode()) vo.setCon_sn(-1);
				if(cmd == Code.Prm.PrmUpd.getCode()) vo.setCon_cn(update_cn);
				
				rtnVal = boardContentService.save(vo);
				
				if(rtnVal == -1) {
					message = "실패 : 처리중 오류가 발생하였습니다.";
				}else {
					Map<String, Object> map = new HashMap<>();
					map.put("bod_sn"	, vo.getBod_sn());
					map.put("val"		, 1);
					boardService.updateCommentCnt(model);
				}
				
				log_what = String.format("%s Board Comment\n작성자:%s(%s)\n%s\nIP:%s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getCon_nm(), vo.getCon_id()
										, vo.getCon_cn()
										, vo.getCon_regip()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode()) 
			{
				rtnVal = boardContentService.delete(vo.getCon_sn());
				
				if(rtnVal == 0) {
					message = "삭제 실패 : 삭제 처리중 오류가 발생하였습니다!";
				}else {
					Map<String, Object> map = new HashMap<>();
					map.put("bod_sn"	, vo.getBod_sn());
					map.put("val"		, -1);
					boardService.updateCommentCnt(model);
				}
				
				log_what = String.format("delete Board Comment\n작성자:%s(%s)\n%s\nIP:%s\n%s"
										, selVo.getCon_nm(), selVo.getCon_id()
										, selVo.getCon_cn()
										, selVo.getCon_regip()
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
