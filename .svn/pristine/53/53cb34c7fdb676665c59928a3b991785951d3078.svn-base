package egovframework.dnworks.controller.sitemanager.cms.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardContentService;
import egovframework.dnworks.cms.board.service.BoardContentVo;
import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.board.service.BoardVo;
import egovframework.dnworks.cms.board.service.BoardVodService;
import egovframework.dnworks.cms.board.service.BoardVodVo;

@Controller
@RequestMapping("/SiteManager/cms/board/move")
public class BoardMoveController extends WebDefault
{
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardVodService boardVodService;
	
	@Autowired
	private BoardContentService boardContentService;
	
	@RequestMapping("/board_move.do")
	public String board_move(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code = Util.nvl(request.getParameter("site_code"), defaultSite);
		
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String bod_sn 		= Util.nvl(request.getParameter("bod_sn"));
		String[] checkBosSn	= request.getParameterValues("bod_check");
		String checkBosSnStr = "";
		if(checkBosSn != null) {
			int i = 1;
			for(String s:checkBosSn) {
				checkBosSnStr += String.format("%s%s", s, checkBosSn.length != i++ ? ",":"");
			}
		}
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		udp.put("bod_sn"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("bod_sn"			, bod_sn);
		model.addAttribute("checkBosSnStr"	, checkBosSnStr);
		
		return managerDir+"/cms/board/move/move";
	}
	
	@RequestMapping("/move_process.do")
	public String move_process(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		
		String move_mnuCode = Util.nvl(request.getParameter("move_mnuCode"));
		String moveMenuNm 	= Util.nvl(request.getParameter("moveMenuNm"));
		int bod_sn 			= Util.nvl(request.getParameter("bod_sn"), 0);
		String checkBosSnStr= Util.nvl(request.getParameter("checkBosSnStr"));
		
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			BoardInfoVo boardInfoVo = boardInfoService.select(move_mnuCode);
			
			if(boardInfoVo == null) {
				model.addAttribute("message"	, "게시판 설정이 없습니다");
				model.addAttribute("location"	, location);
				return "common/scriptAlert";
			}
			
			location = "opener.location.reload();self.close();";
			if(bod_sn != 0) {
				rtnVal = this.moveProcess(bod_sn, move_mnuCode, moveMenuNm);
				
				message = "게시물 이동처리 되었습니다";
				if(rtnVal == -1) {
					message = "게시물 처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("Board Move : bod_sn : %s\n%s(%s)\n%s"
										, bod_sn
										, moveMenuNm, move_mnuCode
										, message
									);
				
				
			}else {
				int successCnt = 0;
				int failCnt = 0;
				
				if(!checkBosSnStr.contentEquals("")) {
					String[] arrayBoardCheck = checkBosSnStr.split("\\,");
					
					for(String uid:arrayBoardCheck)
					{
						rtnVal = this.moveProcess(Util.nvl(uid,  0), move_mnuCode, moveMenuNm);
						if(rtnVal == -1) {
							failCnt++;
						}else{
							successCnt++;
						}
					}
					message = String.format("선택한 게시물 이동 성공:%s, 실패:%d 처리되었습니다", successCnt, failCnt);
					
					log_what = String.format("Board Move : bod_sn : %s\n%s(%s)\n성공 : %s 실패 : %s\n%s"
											, checkBosSnStr
											, moveMenuNm, move_mnuCode
											, successCnt, failCnt
											, message
										);
				}
			}
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message"	, message);
		model.addAttribute("location"	, location);
		return "common/scriptAlert";
	}
	
	@SuppressWarnings("unchecked")
	private int moveProcess(int bod_sn, String move_mnuCode, String moveMenuNm) throws Exception
	{
		Map<String, Object> map = boardService.select(bod_sn);
		
		String move_ttl 		= String.format("게시물이동 [%s] - %s", moveMenuNm, map.get("bod_ttl"));
		String move_cn 			= String.format(String.format("[%s]으로 게시물이 이동 되었습니다. ", moveMenuNm ));
		map.put("move_ttl"		, move_ttl);
		map.put("move_cn"		, move_cn);
		map.put("moveMenuNm"	, moveMenuNm);
		map.put("move_mnuCode"	, move_mnuCode);
		

		BoardVo vo = new BoardVo();
		//이동처리되면 기존글 업데이트
		vo.setCmd(					Code.Prm.PrmUpd.getCode());
		vo.setMnu_code(				Util.nvl(map.get("mnu_code")));
		vo.setBod_sn(				Util.nvl(map.get("bod_sn"), 0));
		vo.setBod_ttl(				Util.nvl(map.get("move_ttl")) );
		
		String bod_startDate 		= Util.nvl(map.get("bod_startDate"));
		String bod_endDate 			= Util.nvl(map.get("bod_endDate"));
		vo.setBod_startDate(		DateUtil.getDateTimeFromString(String.format("%s 00:00:00", bod_startDate)) );
		vo.setBod_endDate(			DateUtil.getDateTimeFromString(String.format("%s 23:59:59", bod_endDate)) );
		vo.setBod_lock(				Util.nvl(map.get("bod_lock"), 0));
		vo.setBod_notice(			Util.nvl(map.get("bod_notice"), 0));
		vo.setBod_sttus(			Util.nvl(map.get("bod_sttus"), 0));
		vo.setBgp_sn(				Util.nvl(map.get("bgp_sn"), 0));
		
		BoardContentVo contentVo = new BoardContentVo();
		contentVo.setCon_id(		(String)map.get("con_id"));
		contentVo.setCon_nm(		(String)map.get("con_nm"));
		
		String con_pw = AESUtil.decrypt(Util.nvl(map.get("con_pw")));
		contentVo.setCon_pw(		con_pw);
		contentVo.setCon_eml(		(String)map.get("con_eml"));
		contentVo.setCon_telno(		(String)map.get("con_telno"));
		contentVo.setCon_regip(		(String)map.get("con_regip"));
		contentVo.setCon_cn(		(String)map.get("move_cn"));
		contentVo.setCon_addCn(		(String)map.get("con_addCn"));
		contentVo.setCon_ty(		(int)map.get("con_ty"));
		contentVo.setCon_point(		(int)map.get("con_point"));
		contentVo.setCon_html(		(int)map.get("con_html"));
		contentVo.setCon_zip(		(String)map.get("con_zip"));
		contentVo.setCon_addr(		(String)map.get("con_addr"));
		contentVo.setCon_regDI(		(String)map.get("con_regDI"));
		contentVo.setCon_linkUrl( 	(String)map.get("con_linkUrl"));
		
		String con_regDt 			= Util.nvl(map.get("con_regDt"));
		String con_mdfcnDt 			= Util.nvl(map.get("con_mdfcnDt"));
		
		contentVo.setCon_regDt(		DateUtil.getDateTimeFromString(con_regDt));
		contentVo.setCon_mdfcnDt(	DateUtil.getDateTimeFromString(con_mdfcnDt));
		vo.setBoardContentVo(contentVo);
		
		int rtnVal = boardService.save(vo);
		
		if(rtnVal != -1) 
		{
			//게시물이동 처리
			vo.setCmd(					Code.Prm.PrmIns.getCode());
			vo.setMnu_code(				move_mnuCode);
			vo.setBod_ttl(				(String)map.get("bod_ttl"));
			contentVo.setCon_pw(		con_pw);
			contentVo.setCon_cn(		(String)map.get("con_cn"));
			vo.setBoardContentVo(contentVo);
			
			//첨부파일
			if(map.get("boardFileList") != null) {
				List<BoardFileVo> boardFileList = new ArrayList<>();
				for(BoardFileVo file:(List<BoardFileVo>) map.get("boardFileList")) 
				{
					BoardFileVo f = new BoardFileVo();
					f.setFile_sn(-1);
					f.setFile_srcFileNm(file.getFile_srcFileNm());
					f.setFile_phyFileNm(file.getFile_phyFileNm());
					f.setFile_thumbFileNm(file.getFile_thumbFileNm());
					f.setFile_phyFileSize(file.getFile_phyFileSize());
					f.setFile_desc(file.getFile_desc());
					f.setFile_ext(file.getFile_ext());
					f.setFile_ty(file.getFile_ty());
					f.setFile_path(file.getFile_path());
					f.setFile_firstYn(file.getFile_firstYn());
					boardFileList.add(f);
					
					//첨부파일 삭제
					boardFileService.delete(file.getFile_sn());
				}
				vo.setBoardFileList(boardFileList);
			}
			
			//동영상
			if(map.get("boardVodList") != null) {
				List<BoardVodVo> boardVodList = new ArrayList<>();
				for(BoardVodVo vod:(List<BoardVodVo>) map.get("boardVodList"))
				{
					BoardVodVo v = new BoardVodVo();
					v.setVod_sn(-1);
					v.setVod_ttl(vod.getVod_ttl());
					v.setVod_linkUrl(vod.getVod_linkUrl());
					boardVodList.add(v);
					
					//동영상 삭제
					boardVodService.delete(vod.getVod_sn());
				}
				vo.setBoardVodList(boardVodList);
			}
			
			//댓글목록
			Map<String, Object> commMap = new HashMap<>();
			commMap.put("bod_sn", bod_sn);
			commMap.put("con_ty", 0);
			List<BoardContentVo> commentList = boardContentService.selectList(commMap);
			
			if(commentList != null && commentList.size() > 0) {
				List<BoardContentVo> boardContentList = new ArrayList<>();
				for(BoardContentVo comm:commentList) {
					int con_sn = comm.getCon_sn();
					comm.setCon_sn(-1);
					boardContentList.add(comm);
					//댓글 삭제
					boardContentService.delete(con_sn);
				}
				vo.setBoardContentList(boardContentList);
			}
			
			rtnVal = boardService.save(vo);

		}
		
		return rtnVal;
	}
}
