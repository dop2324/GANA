package egovframework.dnworks.controller.sitemanager.cms.board;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovFormBasedFileUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.service.BoardDeptFileService;
import egovframework.dnworks.cms.board.service.BoardDeptFileVo;
import egovframework.dnworks.cms.board.service.BoardDeptService;
import egovframework.dnworks.cms.board.service.BoardDeptVo;
import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;
import egovframework.dnworks.cms.board.service.BoardService;

@Controller
public class BoardDownloadController extends WebDefault
{
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardDeptService boardDeptService;
	
	@Autowired
	private BoardDeptFileService boardDeptFileService;
	
	/*
	 * download
	 */
	@RequestMapping("/board_download.do")
	public String board_download(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception, FileNotFoundException
	{
		int prmVal = super.getPermission(request, request.getSession());
		
		if(!Util.hasPermission(prmVal, Code.Prm.PrmSel.getCode())) {
			model.addAttribute("message", "조회 권한이 없습니다");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}else {
			
			int bod_sn = Util.nvl(request.getParameter("bod_sn"), 0);
			if(bod_sn == 0) {
				model.addAttribute("message", "게시물 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			BoardFileVo file = boardFileService.select(Util.nvl(request.getParameter("file_sn"), 0));
			
			if(file == null) {
				model.addAttribute("message", "파일 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}else if (bod_sn != file.getBod_sn()){
				model.addAttribute("message", "파일 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			Map<String, Object> boardMap = boardService.select(file.getBod_sn());
			int bod_sttus = Util.nvl(boardMap.get("bod_sttus"), 0);
			int bod_delSttus = Util.nvl(boardMap.get("bod_delSttus"), 0);
			
			//비밀글의 첨부파일
			int bod_lock = Util.nvl(boardMap.get("bod_lock"), 0);
			boolean hasAdm = Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode());
			
			//비밀글 관리자 아닐경우 등록DI 비교
			if(bod_lock == 1 && !hasAdm) {
				String con_regDI = Util.nvl(boardMap.get("con_regDI"));
				String sessionDI = Util.getSession(request.getSession(), "USR_DI", Util.getSession(request.getSession(), "USR_ID"));
				if(!con_regDI.equals(sessionDI)) {
					model.addAttribute("message", "다운로드 권한이 없습니다\n로그인 또는 본인확인 바랍니다");
					model.addAttribute("location", "history.back();");
					return "common/scriptAlert";
				}
			}
			
			if(file != null && bod_sttus == 1 && bod_delSttus == 0) {
					EgovFormBasedFileUtil.downloadFile(response
													, EgovProperties.getProperty("Globals.FilePath")
													, "board"
													, file.getFile_phyFileNm()
													, file.getFile_srcFileNm()
													);
			}else{
				model.addAttribute("message", "파일 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
		}
		
		return null;
	}
	
	/*
	 * download
	 */
	@RequestMapping("/boardDept_download.do")
	public String boardDept_download(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception, FileNotFoundException
	{
		int prmVal = super.getPermission(request, request.getSession());
		
		if(!Util.hasPermission(prmVal, Code.Prm.PrmSel.getCode())) {
			model.addAttribute("message", "조회 권한이 없습니다");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}else {
			
			int bod_sn = Util.nvl(request.getParameter("bod_sn"), 0);
			if(bod_sn == 0) {
				model.addAttribute("message", "게시물 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			BoardDeptFileVo file = boardDeptFileService.select(Util.nvl(request.getParameter("file_sn"), 0));			
			BoardDeptVo deptVo = boardDeptService.select(file.getDept_sn());
			
			if(deptVo == null) {
				model.addAttribute("message", "게시물 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}else if (bod_sn != deptVo.getBod_sn()){
				model.addAttribute("message", "파일 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			Map<String, Object> boardMap = boardService.select(deptVo.getBod_sn());
			int bod_sttus = Util.nvl(boardMap.get("bod_sttus"), 0);
			int bod_delSttus = Util.nvl(boardMap.get("bod_delSttus"), 0);
			
			//비밀글의 첨부파일
			int bod_lock = Util.nvl(boardMap.get("bod_lock"), 0);
			boolean hasAdm = Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode());
			
			//비밀글 관리자 아닐경우 등록DI 비교
			if(bod_lock == 1 && !hasAdm) {
				String con_regDI = Util.nvl(boardMap.get("con_regDI"));
				String sessionDI = Util.getSession(request.getSession(), "USR_DI", Util.getSession(request.getSession(), "USR_ID"));
				if(!con_regDI.equals(sessionDI)) {
					model.addAttribute("message", "다운로드 권한이 없습니다\n로그인 또는 본인확인 바랍니다");
					model.addAttribute("location", "history.back();");
					return "common/scriptAlert";
				}
			}
			
			if(file != null && bod_sttus == 1 && bod_delSttus == 0) {
					EgovFormBasedFileUtil.downloadFile(response
													, EgovProperties.getProperty("Globals.FilePath")
													, "board/dept"
													, file.getFile_phyFileNm()
													, file.getFile_srcFileNm()
													);
			}else{
				model.addAttribute("message", "파일 정보가 없습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
		}
		
		return null;
	}
}
