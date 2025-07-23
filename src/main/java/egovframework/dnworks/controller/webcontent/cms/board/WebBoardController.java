package egovframework.dnworks.controller.webcontent.cms.board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.com.cmm.util.EgovFormBasedFileVo;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cmm.FileUploadUtil;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.MailUtil;
import egovframework.dnworks.cmm.util.Thumbnailator;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.board.info.service.BoardFieldVo;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardContentVo;
import egovframework.dnworks.cms.board.service.BoardDeptService;
import egovframework.dnworks.cms.board.service.BoardDeptVo;
import egovframework.dnworks.cms.board.service.BoardFieldValueService;
import egovframework.dnworks.cms.board.service.BoardFieldValueVo;
import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.board.service.BoardVo;
import egovframework.dnworks.cms.board.service.BoardVodService;
import egovframework.dnworks.cms.board.service.BoardVodVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/WebContent/cms/board")
public class WebBoardController extends WebDefault
{
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardDeptService boardDeptService;
	
	@Autowired
	private BoardVodService boardVodService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardFieldValueService boardFieldValueService;
	
	@Autowired
	private BlackListIPService blackListIPService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	private String list_location = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_list";
	private String form_location = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_form";
	private String view_location = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_view";
	private String pw_location 	 = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_pw";
	private String auth_location = EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_auth";
	
	/*
	 * board
	 */
	@RequestMapping("/board.do")
	public String board(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location 	= null;
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int prmVal 			= (int) request.getAttribute("prmVal");
		SiteVo siteVo		= (SiteVo) request.getAttribute("siteVo");
		
		int cmd				= Util.nvl(request.getParameter("cmd"), 1);
		int pageNo			= Util.nvl(request.getParameter("pageNo"), 1);
		
		//권한을 확인 한다
		boolean hasPrmFlag 	= Util.hasPermission(prmVal, cmd);
				
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		//udp.put("parm_mnuCode"	, "");
		udp.put("srchKwd"		, "");
		udp.put("srchBgpSn"		, "");
		udp.put("srchSttus"		, "");
		udp.put("srchSDate"		, "");
		udp.put("srchEDate"		, "");
		if(cmd != 1) {
			udp.put("pageNo"	, "");
			//일정(달력)
			udp.put("year"		, "");
			udp.put("month"		, "");
			udp.put("day"		, "");
		}
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink = String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		BoardInfoVo infoVo = boardInfoService.select(mnu_code);
		if(infoVo == null) {
			return EgovProperties.getProperty("Globals.PublicPath")+"/cms/board/board_noConf";
		}
		
		//회원전용게시판
		if(infoVo.getInf_fncMbrCheckAuth() == 1) {
			//권한이 없고 그룹아이디 회원 아닌경우 (회원 인증 기능, 본인 확인 기능 동시 사용)
			if(!hasPrmFlag && Util.getSession(session, "GRP_ID").indexOf("GRP_004") == -1) {
				return "/error/error.do?err_code=5000";
			}
		}
		
		//차단 IP
		if(infoVo.getInf_fncAdmBlockIP() == 1) {
			Map<String, Object> blipMap = new HashMap<>();
			blipMap.put("site_code", site_code);
			blipMap.put("accessip", IPUtil.getXForwardedFor(request));
			int blackListIPCnt = blackListIPService.isBlackListIP(blipMap);
			if(blackListIPCnt > 0) {
				//세션 삭제
				request.getSession().invalidate();
				String clientIp = IPUtil.getXForwardedFor(request);
				super.insertBlockLog(request, clientIp, "게시판 차단 IP");
				model.addAttribute("message", "게시판 이용 차단 IP 입니다\n관리자에게 문의 바랍니다");
				model.addAttribute("location", "location.href='/'");
				return "common/scriptAlert";
			}
		}
		
		//차단 ID
		if(infoVo.getInf_fncAdmBlockID() == 1) {
			Map<String, Object> bluMap = new HashMap<>();
			bluMap.put("blu_id", Util.getSession(request.getSession(), "USR_ID"));
	    	int blackListUserIDCnt = blackListUserService.isBlackListUser(bluMap);
	    	bluMap.put("blu_id", Util.getSession(request.getSession(), "USR_DI"));
	    	int blackListUserDICnt = blackListUserService.isBlackListUser(bluMap);
	    	int blackListUserCnt = blackListUserIDCnt+blackListUserDICnt;
	    	if(blackListUserCnt > 0) {
	    		//세션 삭제
				request.getSession().invalidate();
				String log_id = Util.getSession(request.getSession(), "USR_ID");
				if(blackListUserIDCnt == 0) log_id = Util.getSession(request.getSession(), "USR_DI");
				super.insertBlockLog(request, log_id, "게시판 차단 사용자");
				model.addAttribute("message", "게시판 이용 차단 사용자 입니다\n관리자에게 문의 바랍니다");
				model.addAttribute("location", "location.href='/'");
				return "common/scriptAlert";
	    	}
		}
		
		boolean hasAdm = Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode());
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		
		model.addAttribute("infoVo"			, infoVo);
		model.addAttribute("hasAdm"			, hasAdm);
		model.addAttribute("HasToken"		, Code.Prm.HasToken.getCode());
		
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("cmd"			, cmd);
		
		switch(cmd)
		{
			case 32	:
			case 16	:
			case 2	:
						location = this.password(request, session, model, infoVo, hasAdm, siteVo, queryString);
						break;
						
			case 2+256 :
						cmd = cmd & ~Code.Prm.HasToken.getCode();
						location = this.select(request, session, model, infoVo, hasAdm, queryString);
						break;
						
			case 8	:
			case 4	:
			case 16+256 :
						location = this.write(request, session, model, infoVo, hasAdm, queryString);
						break;
			default :
						switch(infoVo.getInf_skinType()) {
							case "calendar" : this.calendar(request, session, model, infoVo, hasAdm, queryString); 	break;
							default			: this.list(request, session, model, infoVo, hasAdm, queryString); 		break;
						}
						
						location = list_location;
						break;
		}
		
		return location;
	}
	
	/* password */
	private String password(HttpServletRequest request, HttpSession session, ModelMap model, BoardInfoVo infoVo, boolean hasAdm, SiteVo siteVo, String queryString) throws Exception {
		String location = pw_location;
		int cmd			= Util.nvl(request.getParameter("cmd"), 1);
		int bod_sn  	= Util.nvl(request.getParameter("bod_sn"), 0);
		String listLink	= String.format("location.href='?%s'", queryString);
		String viewLink	= String.format("location.href='?%s&bod_sn=%s&cmd=2'", queryString, bod_sn);
		
		String con_pw 	= "";
		
		Map<String, Object> boardMap = boardService.select(bod_sn);
		if(boardMap == null)
		{
			model.addAttribute("message", "존재하지 않는 게시물 입니다.");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}
		
		model.addAttribute("bod_sn"	, bod_sn);
		model.addAttribute("cmd"	, cmd);
		
		//수정 삭제시 게시글 작성자 본인 확인
		if(cmd == Code.Prm.PrmUpd.getCode() || cmd == Code.Prm.PrmDel.getCode()) {
			if(!hasAdm) {
				String con_id	= Util.nvl(boardMap.get("con_id"));
				String con_regDI= Util.nvl(boardMap.get("con_regDI"));
				
				String usr_id	= Util.getSession(session, "USR_ID");
				String usr_di	= Util.getSession(session, "USR_DI");
				
				//본인 확인 기능 DI 확인
				if(infoVo.getInf_fncUsrCheckAuth() == 1) {
					//본인확인 페이지로 이동
					if(usr_di.equals("") && !con_regDI.equals(usr_di)) {
						return auth_location;
					}
					
					//본인확인 후 자신의 글이 아니면 내용보기로 보낸다
					if(usr_id.equals(guestID) && !con_regDI.equals(usr_di)) {
						model.addAttribute("message"	, "권한이 없습니다.");
						model.addAttribute("location"	, viewLink);
						return "common/scriptAlert";
					}
				}
				
				//회원 인증 기능
				if(infoVo.getInf_fncMbrCheckAuth() == 1) {
					//비로그인 사용자
					if(usr_id.equals(guestID)) {
						model.addAttribute("message"	, "로그인이 필요 합니다");
						model.addAttribute("location"	, "location.href='?mnu_code=login'");
						return "common/scriptAlert";
					}
					//글작성자 ID와 DI가 일치하지 않으면
					if(!con_id.equals(usr_id) && !con_regDI.equals(usr_di)) {
						model.addAttribute("message"	, "권한이 없습니다.");
						model.addAttribute("location"	, viewLink);
						return "common/scriptAlert";
					}
				}
			}
		}
		
		//조회 비밀글이 아니면
		if(cmd == Code.Prm.PrmSel.getCode() && Util.nvl(boardMap.get("bod_lock"), 0) == 0) {
			location = this.select(request, session, model, infoVo, hasAdm, queryString);
			return location;
		}else {
			if(cmd != Code.Prm.PrmDel.getCode()) {
				cmd = cmd+Code.Prm.HasToken.getCode();
			}

			if(hasAdm) {
				con_pw = AESUtil.decrypt(Util.nvl(boardMap.get("con_pw")));
			}
		}
		
		model.addAttribute("cmd"			, cmd);
		model.addAttribute("con_pw"			, con_pw);
		model.addAttribute("boardMap"		, boardMap);
		return location;
	}
	
	//작성자 본인글만 확인 
	private boolean writerFlag(HttpSession session, BoardInfoVo infoVo, boolean hasAdm, Map<String, Object> boardMap) {

		if(infoVo.getInf_fncWriterOnly() == 1) {
			
			String sessionID = Util.getSession(session, "USR_ID");
			String sessionDI = Util.getSession(session, "USR_DI", Util.getSession(session, "USR_ID"));
			
			String con_id 	= Util.nvl(boardMap.get("con_id"));
			String con_regDI= Util.nvl(boardMap.get("con_regDI"));
			
			//본인 확인 기능
			if(infoVo.getInf_fncUsrCheckAuth() == 1) {
				if(!con_regDI.equals(sessionDI)) {
					return false;
				}
			}
			//회원 인증 기능
			if(infoVo.getInf_fncMbrCheckAuth() == 1) {
				if(!con_id.equals(sessionID)) {
					return false;
				}
			}
		}
		return true;
	}

	/* write */
	private String write(HttpServletRequest request, HttpSession session, ModelMap model, BoardInfoVo infoVo, boolean hasAdm, String queryString) throws Exception
	{
		String location = form_location;
		int bod_sn 		= Util.nvl(request.getParameter("bod_sn"), 0);
		int cmd			= Util.nvl(request.getParameter("cmd"), 1);
		String listLink	= String.format("location.href='?%s'", queryString);
		//String viewLink	= String.format("location.href='?%s&bod_sn=%s&cmd=2'", queryString, bod_sn);
		
		
		if(infoVo.getInf_fncUsrCheckAuth() == 1
				&& (cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmRpl.getCode())
				&& !hasAdm
				) {
			
			//회원 제외
			if(Util.getSession(session, "USR_ID").equals("guest") && Util.getSession(session, "GRP_ID").indexOf("GRP_004") == -1) {
				//본인인증 세션 USR_DI 확인
				if(Util.getSession(session, "USR_DI").equals("")) {
					return auth_location;
				}
			}
		}
		
		Map<String, Object> boardMap = boardService.select(bod_sn);
		
		if(boardMap != null) {
			String bod_startDate = Util.nvl(boardMap.get("bod_startDate"));
			String bod_endDate = Util.nvl(boardMap.get("bod_endDate"));
			
			if(bod_startDate.equals("2000-01-01")) 	boardMap.put("bod_startDate", "");
			if(bod_endDate.equals("9999-12-31")) 	boardMap.put("bod_endDate", "");
			
			// 사용자 필드
			List<BoardFieldValueVo> boardFieldValueList = (List<BoardFieldValueVo>) boardMap.get("boardFieldValueList");
			HashMap<String, String> boardFieldValue = null;
			if( boardFieldValueList != null ) {
				boardFieldValue = new HashMap<String, String>();
				for(BoardFieldValueVo bfv:boardFieldValueList) {
					boardFieldValue.put(bfv.getBfd_id(), bfv.getBfd_val());
				}
			}
			model.addAttribute("boardFieldValue"	, boardFieldValue);
		}
		
		if(cmd == Code.Prm.PrmIns.getCode()) {
			
			//boardMap.put("con_nm", Util.getSession(session, "USR_NM"));
			
		}else if(cmd == Code.Prm.PrmUpd.getCode()+Code.Prm.HasToken.getCode()) {
			cmd = cmd & ~Code.Prm.HasToken.getCode();

			String con_pw = Util.nvl(request.getParameter("con_pw"));
			con_pw = AESUtil.encrypt(con_pw);
			if(!con_pw.equals(Util.nvl(boardMap.get("con_pw")))) {
				model.addAttribute("message"	, "비밀번호가 맞지 않습니다!");
				model.addAttribute("location"	, listLink);
				return "common/scriptAlert";
			}
			
			//작성자 본인글만 확인 
			if(!this.writerFlag(session, infoVo, hasAdm, boardMap)) {
				model.addAttribute("message"	, "게시글 조회 권한이 없습니다");
				model.addAttribute("location"	, listLink);
				return "common/scriptAlert";
			}
			boardMap.put("con_pw", AESUtil.decrypt(Util.nvl(boardMap.get("con_pw"))) );
			
		}else if(cmd == Code.Prm.PrmRpl.getCode()) {

			boardMap.put("bod_lock"				, null);
			boardMap.put("bod_notice"			, null);
			boardMap.put("bod_pushGroup"		, null);
			boardMap.put("con_nm"				, Util.getSession(session, "USR_NM"));
			boardMap.put("con_pw"				, null);
			boardMap.put("con_eml"				, null);
			boardMap.put("con_telno"			, null);
			boardMap.put("con_cn"				, null);
			boardMap.put("con_zip"				, null);
			boardMap.put("con_addr"				, null);
			boardMap.put("con_linkurl"			, null);
			boardMap.put("con_regDt"			, null);
			boardMap.put("boardFileList"		, null);
			boardMap.put("boardVodList"			, null);
			boardMap.put("boardFieldValueList"	, null);
			boardMap.put("boardShareMemberList"	, null);
			model.addAttribute("boardFieldValue"	, null);
		}
		
		model.addAttribute("cmd"			, cmd);
		model.addAttribute("boardMap"		, boardMap);
		return location;
	}
	
	/*
	 * calendar
	 */
	private void calendar(HttpServletRequest request, HttpSession session, ModelMap model, BoardInfoVo infoVo, boolean hasAdm, String queryString) throws Exception {

		//달력 기본 설정
		Calendar calendar = Calendar.getInstance();
		int initYear 	= Util.nvl(request.getParameter("year")	, calendar.get(Calendar.YEAR));
		int initMonth 	= Util.nvl(request.getParameter("month"), calendar.get(Calendar.MONTH) + 1);
		int initDay 	= Util.nvl(request.getParameter("day")	, calendar.get(Calendar.DATE));
		
		if(initMonth > 12) {
			initYear += 1; initMonth = 1;
		}
		if(initMonth < 1) {
			initYear -= 1;  initMonth = 12;
		}
		
		calendar.set(initYear, initMonth-1, initDay);
		model.addAttribute("initYear"			, initYear);
		model.addAttribute("initMonth"			, String.format("%02d", initMonth));
		model.addAttribute("initDay"			, String.format("%02d", initDay));
		
		//해당월의 1일로 캘린더 설정.
		calendar.set(Calendar.DATE, 1); 
		model.addAttribute("firstDayOfMonth"	, calendar.getTime());
		model.addAttribute("firstDayOfWeek"		, calendar.get(Calendar.DAY_OF_WEEK));
		model.addAttribute("lastDayOfMonth"		, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//해당월의 마지막일로 캘린더 설정.
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		model.addAttribute("lastDayOfLastWeek"	, calendar.get(Calendar.DAY_OF_WEEK));
		
		//다음달의 1일로 설정.
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DATE, 1);
		model.addAttribute("firstDayOfNextMonth", calendar.getTime());
		
		model.addAttribute("initDay"			, String.format("%02d", initDay));
		
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= infoVo.getInf_pageSize();
		
		String srchColumn	= Util.nvl(request.getParameter("srchColumn"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String srchCalTy	= Util.nvl(request.getParameter("srchCalTy"), "month");
		String srchDate		= String.format("%s%02d%02d", initYear, initMonth, initDay);
		
		//boardList
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, 1000000);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		map.put("mnu_code"		, mnu_code);
		map.put("srchColumn"	, srchColumn);
		map.put("srchKwd"		, srchKwd);
		map.put("srchNotice"	, 0);
		map.put("srchSttus"		, 1);
		map.put("srchDelete"	, "0");	
		map.put("hasPrmAdm"		, hasAdm);
		map.put("srchCalTy"		, srchCalTy);
		map.put("srchDate"		, srchDate);
		map.put("infFncAdmTerm"		, infoVo.getInf_fncAdmTerm());
		map.put("infFncAdmTermType"	, infoVo.getInf_fncAdmTermTy());
		map.put("infBoardPublic"	, infoVo.getBoardPublicList().size());
		
		int totalCnt = boardService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		int totalPage = (totalCnt == 0) ? 1 : ((totalCnt/infoVo.getInf_pageSize())+1);
		List<Map<String, Object>> boardList = boardService.selectList(map);
		
		
		model.addAttribute("selectDate"				, srchDate);
		model.addAttribute("totalCnt"				, totalCnt);
		model.addAttribute("no"						, no);
		model.addAttribute("totalPage"				, totalPage);
		model.addAttribute("boardList"				, boardList);
	}
	
	/* list */
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, BoardInfoVo infoVo, boolean hasAdm, String queryString) throws Exception {
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= infoVo.getInf_pageSize();
		
		String srchColumn	= Util.nvl(request.getParameter("srchColumn"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String srchBgpSn 	= Util.nvl(request.getParameter("srchBgpSn"));
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"));
		String srchEDate	= Util.nvl(request.getParameter("srchEDate"));
		String srchBodSkin	= Util.nvl(request.getParameter("srchBodSkin"));

		//boardList
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		map.put("mnu_code"		, mnu_code);
		map.put("srchColumn"	, srchColumn);
		map.put("srchKwd"		, srchKwd);
		map.put("srchBgpSn"		, srchBgpSn);
		map.put("srchNotice"	, 0);
		map.put("srchSttus"		, 1);
		map.put("srchDelete"	, "0");	
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
		map.put("hasPrmAdm"		, hasAdm);
		map.put("srchBodSkin"	, srchBodSkin);
		map.put("infFncAdmTerm"		, infoVo.getInf_fncAdmTerm());
		map.put("infFncAdmTermTy"	, infoVo.getInf_fncAdmTermTy());
		map.put("infBoardPublic"	, infoVo.getBoardPublicList().size());
		
		String sessionID = Util.getSession(request.getSession(), "USR_ID");
		String sessionDI = Util.getSession(request.getSession(), "USR_DI", Util.getSession(request.getSession(), "USR_ID"));
		
		//작성자 본인글만 
		map.put("writerOnly"	, infoVo.getInf_fncWriterOnly());
		map.put("UsrCheckAuth"	, infoVo.getInf_fncUsrCheckAuth());
		map.put("MbrCheckAuth"	, infoVo.getInf_fncMbrCheckAuth());
		map.put("sessionID"		, sessionID);
		map.put("sessionDI"		, sessionDI);
		
		int totalCnt = boardService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		int totalPage = (totalCnt == 0) ? 1 : ((totalCnt/infoVo.getInf_pageSize())+1);
		List<Map<String, Object>> boardList = boardService.selectList(map);
		
		//사용자필드 게시판
		if(infoVo.getInf_skinType().equals("usrField")) {
			for(Map<String, Object> m:boardList) {
				List<BoardFieldValueVo> boardFieldValueList = boardFieldValueService.selectList(Util.nvl(m.get("bod_sn"), 0));
				m.put("boardFieldValueList", boardFieldValueList);
			}
		}
		
		//notice
		map.put("pageNo"			, 1);
		map.put("pageSize"			, 10000);
		map.put("srchKwd"			, "");
		map.put("srchBgpSn"			, "");
		map.put("srchNotice"		, 1);
		map.put("srchSttus"			, 1);
		List<Map<String, Object>> noticeList = boardService.selectList(map);

		//marge list
		List<Map<String, Object>> boardLists = new ArrayList<Map<String, Object>>();
		boardLists.addAll(noticeList);
		boardLists.addAll(boardList);
		
		model.addAttribute("totalCnt"				, totalCnt);
		model.addAttribute("no"						, no);
		model.addAttribute("totalPage"				, totalPage);
		model.addAttribute("boardLists"				, boardLists);
		
		map.put("srchKwd"	, srchKwd);
		map.put("srchBgpSn" , srchBgpSn);
		model.addAttribute("srchMap"				, map);
		
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
	/* select */
	private String select(HttpServletRequest request, HttpSession session, ModelMap model, BoardInfoVo infoVo, boolean hasAdm, String queryString) throws Exception {
		String location = view_location;
		int bod_sn 		= Util.nvl(request.getParameter("bod_sn"), 0);
		String con_pw 	= Util.nvl(request.getParameter("con_pw"));
		String listLink	= String.format("location.href='?%s'", queryString);
		String viewLink	= String.format("location.href='?%s&bod_sn=%s&cmd=2'", queryString, bod_sn);
		
		Map<String, Object> boardMap = boardService.select(bod_sn);
		
		if(boardMap == null) {
			model.addAttribute("message", "존재하지 않는 게시물 입니다.");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}
		
		//작성자 본인글만 
		if(!this.writerFlag(session, infoVo, hasAdm, boardMap)) {
			model.addAttribute("message"	, "게시글 조회 권한이 없습니다");
			model.addAttribute("location"	, listLink);
			return "common/scriptAlert";
		}
		
		//조회수 증가
		if (session.getAttribute("board_cookie") == null) {
		 	session.setAttribute("board_cookie", bod_sn);
		 	boardService.updateReadCnt(bod_sn);
		}else {
			if(!session.getAttribute("board_cookie").toString().contains(Integer.toString(bod_sn))) {
				session.setAttribute("board_cookie", session.getAttribute("board_cookie")+","+bod_sn);
				boardService.updateReadCnt(bod_sn);
			}
		}
		
		//비밀글 확인
		//if(!hasAdm && Util.nvl(boardMap.get("bod_lock"), 0) == 1) {
		if(Util.nvl(boardMap.get("bod_lock"), 0) == 1) {
			con_pw = AESUtil.encrypt(con_pw);
			if(!con_pw.equals(boardMap.get("con_pw"))) {
				model.addAttribute("message", "비밀번호가 맞지 않습니다!");
				model.addAttribute("location", listLink);
				return "common/scriptAlert";
			}
		}
		
		if(!infoVo.getInf_skinType().equals("writer")) {
			//boardPreNxt
			Map<String, Object> preNxtMap = new HashMap<>();
			preNxtMap.put("bod_sn"		, bod_sn);
			preNxtMap.put("bod_sttus"	, 1);
			preNxtMap.put("bgp_sn"		, Util.nvl(request.getParameter("srchBgpSn"), -1));
			preNxtMap.put("hasAdm"		, 1);
			boardService.selectPreNxt(preNxtMap);
			model.addAttribute("preNxtMap"	, preNxtMap);
		}
		
		if(infoVo.getInf_skinType().equals("reply")) {
			//부서 답변 
			List<BoardDeptVo> boardDeptList = boardDeptService.selectList(bod_sn);
			model.addAttribute("boardDeptList"	, boardDeptList);
		}
		
		model.addAttribute("boardMap"	, boardMap);
		
		return location;
	}
	
	@RequestMapping(value = "/board_process.do", method = RequestMethod.POST)
	public String board_process(@ModelAttribute BoardVo boardVo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		String viewLocation = String.format("location.href='%s?%sbod_sn=%s&cmd=2'", returnUrl, queryString, boardVo.getBod_sn());
		
		int rtnVal = -1;
		String message 	= "";

		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			//upload
			String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+boardVo.getWebDir();
			String[] BadExt 	= EgovProperties.getProperty("Globals.file.BadExt").split(",");
			String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
			int prmVal 			= super.getPermission(request, session);
			
			
			BoardInfoVo infoVo = boardInfoService.select(mnu_code);
			boolean hasAdm = Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode());

			boardVo.setCmd(cmd);
			boardVo.setMnu_code(mnu_code);
			
			Map<String, Object> selectBodMap = boardService.select(boardVo.getBod_sn());
			
			//수정 삭제시 게시글 작성자 본인 확인
			if(cmd == Code.Prm.PrmUpd.getCode() || cmd == Code.Prm.PrmDel.getCode()) {
				if(!hasAdm) {
					String con_id	= Util.nvl(selectBodMap.get("con_id"));
					String con_regDI= Util.nvl(selectBodMap.get("con_regDI"));
					
					//본인 확인 기능 DI 확인
					if(infoVo.getInf_fncUsrCheckAuth() == 1) {
						if(!con_regDI.equals(Util.getSession(session, "USR_DI"))) {
							model.addAttribute("message"	, "권한이 없습니다\\n로그인 또는 본인확인 바랍니다");
							model.addAttribute("location"	, location);
							return "common/scriptAlert";
						}
					}
					
					//회원 인증 기능
					String sessionDI = Util.getSession(request.getSession(), "USR_DI", Util.getSession(request.getSession(), "USR_ID"));
					if(infoVo.getInf_fncMbrCheckAuth() == 1) {
						if(!con_id.equals(Util.getSession(session, "USR_ID")) && !con_regDI.equals(sessionDI)) {
							model.addAttribute("message"	, "권한이 없습니다\\n로그인 또는 본인확인 바랍니다");
							model.addAttribute("location"	, location);
							return "common/scriptAlert";
						}
					}
				}
			}
			
			if(cmd != Code.Prm.PrmDel.getCode()) {
				
				//term
				String bod_startDate= Util.nvl(request.getParameter("bod_startDate"), "2000-01-01");
				String bod_endDate 	= Util.nvl(request.getParameter("bod_endDate"), "9999-12-31");
				boardVo.setBod_startDate(DateUtil.getDateTimeFromString(String.format("%s 00:00:00", bod_startDate)));
				boardVo.setBod_endDate(	DateUtil.getDateTimeFromString(String.format("%s 23:59:59", bod_endDate)));
				//비공개 기능 설정 opt 강재비공개 일때
				if(infoVo.getInf_fncUsrLockOpt() == 2) {
					boardVo.setBod_lock(1);
				}
				//sttus
				boardVo.setBod_sttus(infoVo.getInf_fncUsrAutoEnable());
				//글 분류
				boardVo.setBgp_sn(Util.nvl(request.getParameter("bgp_sn"), 0) );
				//부서정보
				String dept_id = Util.nvl(request.getParameter("dept_id"), Util.getSession(session, "DPT_ID"));
				String dept_nm = Util.nvl(request.getParameter("dept_nm"), Util.getSession(session, "DPT_NM"));
				boardVo.setDept_id(dept_id);
				boardVo.setDept_nm(dept_nm);
				boardVo.setBod_regIP(IPUtil.getXForwardedFor(request));
				boardVo.setBod_regID(Util.getSession(session, "USR_ID"));
				
				//content
				BoardContentVo contentVo = new BoardContentVo();
				contentVo.setCon_id(Util.getSession(session, "USR_ID") );
				contentVo.setCon_nm(Util.nvl(request.getParameter("con_nm")));
				contentVo.setCon_pw(Util.nvl(request.getParameter("con_pw")));
				contentVo.setCon_eml(Util.nvl(request.getParameter("con_eml")));
				contentVo.setCon_telno(Util.nvl(request.getParameter("con_telno")));
				contentVo.setCon_regip(IPUtil.getXForwardedFor(request));
				contentVo.setCon_cn(Util.nvl(request.getParameter("con_cn")));
				contentVo.setCon_addCn(Util.nvl(request.getParameter("con_addCn")));
				contentVo.setCon_point(0);
				contentVo.setCon_html(infoVo.getInf_fncUsrEditor());
				contentVo.setCon_zip(Util.nvl(request.getParameter("con_zip")));
				contentVo.setCon_addr(Util.nvl(request.getParameter("con_addr")));
				contentVo.setCon_regDI(Util.getSession(session, "USR_DI", Util.getSession(session, "USR_ID")) );
				contentVo.setCon_linkUrl( Util.nvl(request.getParameter("con_linkUrl")) );
				String con_regDt = Util.nvl(request.getParameter("con_regDt"));
				contentVo.setCon_regDt(DateUtil.getDateTimeFromString(con_regDt));
				boardVo.setBoardContentVo(contentVo);
				
				//board Field
				BoardFieldValueVo fieldValueVo = null;
				List<BoardFieldValueVo> fieldValueList = null;
				if(infoVo.getInf_fncUsrField() == 1 && infoVo.getBoardFieldList().size() > 0) {
					fieldValueList = new ArrayList<>();
					for(BoardFieldVo bf:infoVo.getBoardFieldList()) {
						String bfd_value = Util.nvl(request.getParameter(bf.getBfd_id()));
						if(bf == null || bfd_value.equals("")) continue;
						
						if(bf.getBfd_sttus() == 1 && !bfd_value.equals("")) {
							fieldValueVo = new BoardFieldValueVo();
							fieldValueVo.setBfd_id(bf.getBfd_id());
							fieldValueVo.setBfd_encYn(bf.getBfd_encYn());
							fieldValueVo.setBfd_val(bfd_value);
							if(bf.getBfd_encYn() == 1) {
								fieldValueVo.setBfd_val(AESUtil.encrypt(bfd_value));
							}
							fieldValueList.add(fieldValueVo);
						}
					}
					boardVo.setBoardFieldValueList(fieldValueList);
				}
				
				//vod
				if(infoVo.getInf_fncAdmVod() == 1) {
					String[] deleteVod =  request.getParameterValues("deleteVod");
					List<Integer> arrayDeleteVod = null;
					if(deleteVod != null) {
						arrayDeleteVod = new ArrayList<>();
						for(String val:deleteVod) {
							int vod_uid = Util.nvl(val, 0);
							arrayDeleteVod.add(vod_uid);
							boardVodService.delete(vod_uid);
						}
					}
					BoardVodVo vodVo = null;
					List<BoardVodVo> boardVodList = null;
					String[] arrayVodsn		= request.getParameterValues("vod_sn");
					String[] arrayVodttl 	= request.getParameterValues("vod_ttl");
					String[] arrayVodLink 	= request.getParameterValues("vod_linkUrl");
					
					if(arrayVodLink != null) {
						
						boardVodList = new ArrayList<>();
						for(int i=0; i < arrayVodLink.length; i++) {
							String vod_linkUrl 	= Util.nvl(arrayVodLink[i]);
							if(vod_linkUrl.equals("")) continue;
							
							int vod_sn 			= Util.nvl(arrayVodsn[i], -1);
							String vod_ttl 		= Util.nvl(arrayVodttl[i]);

							//삭제하는것 추가 금지
							if(arrayDeleteVod != null) {
								if(arrayDeleteVod.contains(vod_sn)) continue;
							}

							vodVo = new BoardVodVo();
							vodVo.setVod_sn(vod_sn);
							vodVo.setVod_ttl(vod_ttl);
							vodVo.setVod_linkUrl(vod_linkUrl);
							boardVodList.add(vodVo);
						}
					}
					boardVo.setBoardVodList(boardVodList);
				}
				
				String[] filterWordList = null;
				// 금칙 단어 체크
				if(!Util.nvl(infoVo.getInf_filterChar(),"").equals("")) { 
					filterWordList = infoVo.getInf_filterChar().split(",");
					
					if(filterWordList != null) {
						for(String filterWord:filterWordList) {
							if(filterWord.length() == 0) continue;
							
							if(Util.hasContainWord(boardVo.getBod_ttl(), filterWord.trim()) 
								|| Util.hasContainWord(boardVo.getBoardContentVo().getCon_cn(), filterWord.trim())
								|| Util.hasContainWord(boardVo.getBoardContentVo().getCon_addCn(), filterWord.trim())
							) {
								model.addAttribute("message"	, String.format("[%s](이)가 금지된 단어입니다!", Util.replaceRegex(filterWord, "\\r\\n", "")));
								model.addAttribute("location"	, location);
								return "common/scriptAlert";
							}
						}
					}
				}
				
				// 스크립트 방지
				/*
				if(!Util.hasPermission(prmVal, Code.Prm.PrmAdm)) {
					if(Util.hasContainWord(boardVo.getBod_ttl().toLowerCase(), "<script")
						|| Util.hasContainWord(boardVo.getBoardContentVo().getCon_cn().toLowerCase(), "<script" )
						|| Util.hasContainWord(boardVo.getBoardContentVo().getCon_nm().toLowerCase(), "<script" )
						|| Util.hasContainWord(boardVo.getBoardContentVo().getCon_addCn().toLowerCase(), "<script" )
					) {
						model.addAttribute("message"	, "스크립트코드는 입력하실수 없습니다.");
						model.addAttribute("location"	, "history.back()");
						return "common/scriptAlert";
					}
				}
				*/
				
				//file
				boolean firstYn = false;
				String alwExt 	= Util.nvl(infoVo.getInf_fileExt());
				int MaxUploadSize= Util.nvl(infoVo.getInf_fileSizeLmt(), 0);
				BoardFileVo fileVo = null;
				List<BoardFileVo> boardFileList = new ArrayList<BoardFileVo>();
				
				List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, MaxUploadSize, BadExt, alwExt);
				
				if(files != null && files.size() > 0) {
					for(EgovFormBasedFileVo file:files) {
						if(file.getFileSn() != -1) {
							BoardFileVo updFileVo = boardFileService.select(file.getFileSn());
							this.deleteBoardFile(uploadDir, updFileVo);
						}
						fileVo = new BoardFileVo();
						fileVo.setFile_sn(file.getFileSn());
						fileVo.setFile_srcFileNm(file.getFileName());
						fileVo.setFile_phyFileNm(String.format("%s/%s", file.getServerSubPath(), file.getPhysicalName()) );
						fileVo.setFile_phyFileSize(Util.nvl(file.getSize(), 0));
						fileVo.setFile_desc(file.getFileDescription() != "" ? file.getFileDescription():fileVo.getFile_srcFileNm());
						fileVo.setFile_ext(file.getFileExtension());
						fileVo.setFile_ty(Util.isImageFile(file.getPhysicalName()) ? 1 : 0);
						fileVo.setFile_firstYn(file.getFirstYn());
						
						if(file.getFirstYn() == 1) firstYn = true;
						
						//create thumbnail 
						String thumbFileNm = null;
						if( Util.isImageFile(file.getPhysicalName()) ) {
							thumbFileNm = String.format("t_%s", file.getPhysicalName());
							fileVo.setFile_thumbFileNm(String.format("%s/%s", file.getServerSubPath(), thumbFileNm) );
							Thumbnailator.createThumbImage(uploadDir, file.getServerSubPath(), file.getPhysicalName(), thumbFileNm);
						}
						
						//동영상 썸네일 추출
						if( Util.isMediaFile(file.getPhysicalName()) ) {
							thumbFileNm 	= String.format("t_%s.png", Util.getFileName(file.getPhysicalName()));
							String source 	= String.format("%s/%s/%s", uploadDir, file.getServerSubPath(), file.getPhysicalName());
							String thumbnail= String.format("%s/%s/%s", uploadDir, file.getServerSubPath(), thumbFileNm);
							int frameNumber = 30;
							Picture picture = FrameGrab.getFrameFromFile(new File(source), frameNumber);
							BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
							if(bufferedImage != null) {
								ImageIO.write(bufferedImage, "png", new File(thumbnail));
							}
							
							fileVo.setFile_thumbFileNm(String.format("%s/%s", file.getServerSubPath(), thumbFileNm) );
						}
						boardFileList.add(fileVo);
					}
				}
				
				//file desc
				if(boardVo.getBod_sn() != 0) {
					String[] fileSn 	= request.getParameterValues("fileSn");

					if(fileSn != null) {

						for(int i=0; i < fileSn.length; i++) {
							int file_sn 		= Util.nvl(fileSn[i], 0);
							String file_desc 	= Util.nvl(request.getParameter("fileDesc_"+file_sn));
							int fileFirstYn 	= Util.nvl(request.getParameter("fileFirstYn"), 0);
							
							if(file_sn != 0) {
								fileVo = new BoardFileVo();
								fileVo.setFile_sn(file_sn);
								fileVo.setFile_desc(file_desc);
								fileVo.setFile_firstYn(file_sn == fileFirstYn ? 1:0);
								boardFileList.add(fileVo);
							}
						}
					}
				}
				boardVo.setBoardFileList(boardFileList);
			}
			
			String msg = "";
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode() || cmd == Code.Prm.PrmRpl.getCode()) {
				switch(cmd) {
					case 4 : 	msg = "입력";
								break;
					case 8 : 	msg = "답변";
								break;
					case 16 : 	msg = "수정";
								location 	= viewLocation;
								break;
				}
				
				rtnVal = boardService.save(boardVo);

				if(rtnVal == -1) {
					message 	= String.format("%s실패 : 처리중 오류가 발생하였습니다.", msg);
					//file delete
					this.deleteBoardFileList(uploadDir, boardVo.getBoardFileList());
				}else {
					//delete file
					String[] deleteFile =  request.getParameterValues("deleteFile");
					if(deleteFile != null) {
						for(String val:deleteFile){
							BoardFileVo fileVo = boardFileService.select(Util.nvl(val, 0));
							this.deleteBoardFile(uploadDir, fileVo);
							boardFileService.delete(Util.nvl(val, 0));
						}
					}
					
					if(cmd == Code.Prm.PrmIns.getCode()) {
						//관리자 알림 메일보내기
						if(infoVo.getInf_fncAdmMail() == 1 && !Util.nvl(infoVo.getInf_fncAdmMailAddr()).equals("")) {
							String admNoticeContent = "";
							
							if(infoVo.getInf_fncUsrEditor() == 1) {
								admNoticeContent = boardVo.getBoardContentVo().getCon_cn();
							}else {
								admNoticeContent = Util.htmlEncode(boardVo.getBoardContentVo().getCon_cn());
							}
							String mailTitle 	= String.format("%s [%s]이 등록 되었습니다.", infoVo.getMnu_nm(), boardVo.getBod_ttl());
							String mailContent	= String.format("<strong>%s</strong><br/><br/>%s ", boardVo.getBod_ttl(), admNoticeContent);
							
							//gmail메일 사용 
							//MailUtil.gMailSend(Util.nvl(infoVo.getInf_fncAdmMailAddr(), EgovProperties.getProperty("Globals.AdminMail")), mailTitle, mailContent);
							
							MailUtil.naverMailSend(Util.nvl(infoVo.getInf_fncAdmMailAddr(), EgovProperties.getProperty("Globals.AdminMail")), mailTitle, mailContent);
							
							//alarmlog
							mailContent = mailContent.replaceAll("<br/>", "\n");
							super.insertAlarmLog(boardVo.getMnu_code(), "mail", "", infoVo.getInf_fncAdmMailAddr(), String.format("%s\n%s", mailTitle, mailContent));
							
						}
						//관리자 알림 SMS보내기
						if(!Util.nvl(infoVo.getInf_fncAdmSms()).equals("")) {
							String smsMsg 	= String.format("%s [%s] 등록", infoVo.getMnu_nm(), boardVo.getBod_ttl());
							
							//alarmlog
							super.insertAlarmLog(boardVo.getMnu_code(), "sms", "", infoVo.getInf_deptTelno(), smsMsg);
						}
						/*
						//모바일 앱 Push Send
						if(infoVo.getInf_pushGroup() != "0" ) {
							this.mobilePushSend(infoVo, boardVo, "android");
						}
						*/
					}
				}
				
				log_what = String.format("%s Board : 제목:%s\n작성자:%s(%s)\nIP:%s\n%s"
										, msg
										, boardVo.getBod_ttl()
										, boardVo.getBoardContentVo().getCon_nm(), boardVo.getBoardContentVo().getCon_id()
										, boardVo.getBoardContentVo().getCon_regip()
										, message
									);
				
				
			}else if(cmd == Code.Prm.PrmDel.getCode()) {
				
				if(!hasAdm) {
					String con_pw = Util.nvl(request.getParameter("con_pw"));
					String bod_pw = Util.nvl(selectBodMap.get("con_pw"));
					
					if(!bod_pw.equals(AESUtil.encrypt(con_pw))) {
						model.addAttribute("message"	, "비밀번호가 맞지 않습니다!!!");
						model.addAttribute("location"	, viewLocation);
						return "common/scriptAlert";
					}
					
					/*
					String con_regDI = Util.nvl(selectBodMap.get("con_regDI"));
					String sessionDI = Util.getSession(request.getSession(), "USR_DI", Util.getSession(request.getSession(), "USR_ID"));
					
					if(!con_regDI.equals(sessionDI)) {
						model.addAttribute("message", "삭제 권한이 없습니다\n로그인 또는 본인확인 바랍니다");
						model.addAttribute("location", viewLocation);
						return "common/scriptAlert";
					}
					*/	
				}

				//삭제 표시
				String delUserId 		= Util.getSession(session, "USR_ID");
				String bod_delReason 	= Util.nvl(request.getParameter("bod_delReason"), "사용자 삭제");
				
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("bod_sn"			, boardVo.getBod_sn());
				m.put("bod_delUserId"	, delUserId);
				m.put("bod_delReason"	, bod_delReason);

				rtnVal = boardService.deleteSttus(m);

				if(rtnVal == -1) {
					message = "삭제실패 : 처리중 오류가 발생하였습니다.";
				}

				log_what = String.format("delete Board : 제목:%s\n작성자:%s(%s)\n등록IP:%s\n%s"
										, msg
										, selectBodMap.get("bod_ttl")
										, selectBodMap.get("con_nm"), selectBodMap.get("con_id")
										, selectBodMap.get("con_regip")
										, message
									);
			}
			super.insertLog(request, log_what);
		}

		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
	
	//게시물 첨부파일 지우기
	private void deleteBoardFileList(String uploadDir, List<BoardFileVo> boardFile) {
		//file delete
		for(BoardFileVo fileVo:boardFile) {
			this.deleteBoardFile(uploadDir, fileVo);
		}
	}
	
	private void deleteBoardFile(String uploadDir, BoardFileVo fileVo) {
		FileManager.deleteFile(String.format("%s/%s", uploadDir, fileVo.getFile_phyFileNm() ) );
		//썸네일 지우기
		FileManager.deleteFile(String.format("%s/%s", uploadDir, fileVo.getFile_thumbFileNm() ) );
	}
}
