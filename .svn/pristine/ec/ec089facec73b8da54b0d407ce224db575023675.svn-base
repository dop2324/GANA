package egovframework.dnworks.controller.sitemanager.cms.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardDeptFileService;
import egovframework.dnworks.cms.board.service.BoardDeptFileVo;
import egovframework.dnworks.cms.board.service.BoardDeptService;
import egovframework.dnworks.cms.board.service.BoardDeptVo;


@Controller
@RequestMapping("/SiteManager/cms/board/dept")
public class BoardDeptController extends WebDefault
{
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	private BoardDeptService boardDeptService;
	
	@Autowired
	private BoardDeptFileService boardDeptFileService;
	
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
		
		return managerDir+"/cms/board/dept/dept_viewList";
	}
	
	@RequestMapping(value = "/dept_process.do", method = RequestMethod.POST)
	public String dept_process(@ModelAttribute BoardDeptVo deptVo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%sbod_sn=%s&cmd=2'", returnUrl, queryString, deptVo.getBod_sn());
		
		int prmVal 			= super.getPermission(request, session);
		boolean hasAdm 		= Util.hasPermission(prmVal, Code.Prm.PrmAdm.getCode());
		
		int rtnVal = -1;
		String message 	= "";
		String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+deptVo.getWebDir();
		String[] BadExt 	= EgovProperties.getProperty("Globals.file.BadExt").split(",");
		int MaxUploadSize	= Util.nvl(EgovProperties.getProperty("Globals.file.MaxUploadSize"), 50);
		
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		BoardInfoVo infoVo = boardInfoService.select(parm_mnuCode);
		BoardDeptVo selDeptVo = boardDeptService.select(deptVo.getDept_sn());
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			deptVo.setDept_regIP(IPUtil.getXForwardedFor(request));
			deptVo.setDept_mdfcnIP(IPUtil.getXForwardedFor(request));	
			
			if(cmd == Code.Prm.PrmDel.getCode()) {
				if(!hasAdm) {
					if(deptVo.getUser_id().equals(Util.getSession(session, "USR_ID"))) {
						model.addAttribute("message", "권한이 없습니다.");
						model.addAttribute("location", location);
						return "common/scriptAlert";
					}
				}
			}
			
			if(cmd != Code.Prm.PrmDel.getCode())
			{
				BoardDeptFileVo fileVo = null;
				List<BoardDeptFileVo> boardDeptFileList = new ArrayList<BoardDeptFileVo>();
				
				List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, MaxUploadSize, BadExt);
				
				if(files != null && files.size() > 0) {

					for(EgovFormBasedFileVo file:files) {
						if(file.getFileSn() != -1) {
							BoardDeptFileVo updFileVo = boardDeptFileService.select(file.getFileSn());
							this.deleteDeptFile(uploadDir, updFileVo);
						}
						fileVo = new BoardDeptFileVo();
						//fileVo.setFile_sn(-1);
						fileVo.setFile_sn(file.getFileSn());
						fileVo.setFile_srcFileNm(file.getFileName());
						fileVo.setFile_phyFileNm(String.format("%s/%s", file.getServerSubPath(), file.getPhysicalName()) );
						fileVo.setFile_phyFileSize(Util.nvl(file.getSize(), 0));
						fileVo.setFile_desc(file.getFileDescription() != "" ? file.getFileDescription():fileVo.getFile_srcFileNm());
						fileVo.setFile_ext(file.getFileExtension());
						fileVo.setFile_ty(Util.isImageFile(file.getPhysicalName()) ? 1 : 0);
						
						boardDeptFileList.add(fileVo);
					}
					deptVo.setBoardDeptFileList(boardDeptFileList);
				}
				
				if(deptVo.getDept_sn() != 0) {
					String[] fileSn 	= request.getParameterValues("fileSn");
					if(fileSn != null) {
	
						for(int i=0; i < fileSn.length; i++) {
							int file_sn 		= Util.nvl(fileSn[i], 0);
							String file_desc 	= Util.nvl(request.getParameter("fileDesc_"+file_sn));
							
							if(file_sn != 0) {
								fileVo = new BoardDeptFileVo();
								fileVo.setFile_sn(file_sn);
								fileVo.setFile_desc(file_desc);
								boardDeptFileList.add(fileVo);
							}
						}
						deptVo.setBoardDeptFileList(boardDeptFileList);
					}
				}
			}
			
			String msg = "";
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode()) {
				switch(cmd) {
					case 4 	: 	msg = "입력"; 	
								deptVo.setDept_sn(-1);
								break;
					case 16 : msg = "수정";	break;
				}
				
				rtnVal = boardDeptService.save(deptVo);
				
				if(rtnVal == -1) {
					message 	= String.format("%s실패 : 처리중 오류가 발생하였습니다.", msg);
					this.deleteDeptFileList(uploadDir, deptVo.getBoardDeptFileList());
				}else {
					//delete file
					String[] deleteFile =  request.getParameterValues("deleteFile");
					if(deleteFile != null) {
						for(String val:deleteFile){
							BoardDeptFileVo fileVo = boardDeptFileService.select(Util.nvl(val, 0));
							this.deleteDeptFile(uploadDir, fileVo);
							boardDeptFileService.delete(Util.nvl(val, 0));
						}
					}
					
					if(cmd == Code.Prm.PrmIns.getCode()) {
						if(infoVo.getInf_fncAdmSms() == 1) {
							boolean smsFlag = false;
							String smsMsg = "";
							switch(deptVo.getDept_sttus()) {
								case 5 : 	smsFlag = true;
											smsMsg = String.format("답변이 완료 되었습니다");
											break;
							}
						}
					}
				}
				
				log_what = String.format("%s Board Dept : 작성자:%s(%s)\n등록IP:%s\n%s"
										, msg
										, deptVo.getDept_id(), deptVo.getDept_nm()
										, deptVo.getUser_id(), deptVo.getUser_nm()
										, deptVo.getDept_regIP()
										, message
									);
				
			}else if(cmd == Code.Prm.PrmDel.getCode()) {
				
				rtnVal = boardDeptService.delete(deptVo.getDept_sn());
				
				if(rtnVal == 0) {
					message = "삭제실패 : 처리중 오류가 발생하였습니다.";
				}else {
					this.deleteDeptFileList(uploadDir, selDeptVo.getBoardDeptFileList());
				}
				
				log_what = String.format("Delete Board Dept : 작성자:%s(%s)\n등록IP:%s\n%s"
						, msg
						, selDeptVo.getDept_id(), selDeptVo.getDept_nm()
						, selDeptVo.getUser_id(), selDeptVo.getUser_nm()
						, deptVo.getDept_regIP()
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
	private void deleteDeptFileList(String uploadDir, List<BoardDeptFileVo> boardFile) {
		//file delete
		for(BoardDeptFileVo fileVo:boardFile) {
			this.deleteDeptFile(uploadDir, fileVo);
		}
	}
	
	private void deleteDeptFile(String uploadDir, BoardDeptFileVo fileVo) {
		
		System.out.println(String.format("%s/%s", uploadDir, fileVo.getFile_phyFileNm() ));
		FileManager.deleteFile(String.format("%s/%s", uploadDir, fileVo.getFile_phyFileNm() ) );
	}
}
