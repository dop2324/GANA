package egovframework.dnworks.controller.webcontent.cms.board;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.service.BoardVoteService;
import egovframework.dnworks.cms.board.service.BoardVoteVo;

@Controller
@RequestMapping("/WebContent/cms/board/vote")
public class WebBoardVoteController extends WebDefault
{
	@Autowired
	private BoardVoteService boardVoteService;	
	
	@RequestMapping(value="/vote_process.do", produces="text/plain; charset=utf-8")
	public @ResponseBody String vote_process(HttpServletRequest request, ModelMap model) throws Exception
	{
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		int con_sn 		= Util.nvl(request.getParameter("con_sn"), 0);
		int vote_ty 	= Util.nvl(request.getParameter("vote_ty"), 0);
		String vote_id	= Util.getSession(request.getSession(), "USR_ID");

		if(con_sn == 0) {
			json.put("result"	, -2);
			json.put("message"	, "데이터가 부정확 합니다.");
			return json.toString();
		}

		if(vote_id.equals("guest") || vote_id.equals("")) {
			vote_id = request.getSession().getId();
		}

		String voteTypeStr = "";
		switch(vote_ty) {
			case 1 : voteTypeStr = "추천"; break;
			case 0 : voteTypeStr = "신고"; break;
		}

		BoardVoteVo vo = new BoardVoteVo();
		vo.setCon_sn(con_sn);
		vo.setVote_id(vote_id);

		Integer typeVal = boardVoteService.selectVoteType(vo);
		if(typeVal == null) typeVal = -1;
		
		if(vote_ty == typeVal) {
			json.put("result"	, 0);
			json.put("applyCnt"	, typeVal);
			json.put("message"	, "이미 "+voteTypeStr+" 선택 되어 있습니다.");
		}else{
			vo.setVote_ty(vote_ty);
			
			int voteCnt = boardVoteService.checkBoardVote(vo);
			int rtnVal = boardVoteService.save(vo);
			
			BoardVoteVo sumVo = boardVoteService.selectSumVoteCnt(con_sn);
			if(sumVo != null) {
				json.put("voteYesCnt"	, sumVo.getVote_yesCnt());
				json.put("voteNoCnt"	, sumVo.getVote_noCnt());
			}
			
			if(rtnVal != -1) {
				json.put("result"	, 1);
				json.put("voteCnt"	, voteCnt);
				json.put("message"	, "success");

			}else {
				json.put("result"	, 0);
				json.put("voteCnt"	, voteCnt);
				json.put("message"	, "fail");
			}
		}

		return json.toString();
	}
}
