package egovframework.dnworks.controller.cmm.synap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.synap.ConvertToHtml;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;

@Controller
@RequestMapping("/synap")
public class SynapController 
{
	@Autowired
	private BoardFileService boardFileService;
	
	/*
	 * board 
	 */
	@RequestMapping("/board_fileViewer.do")
	public String board_fileViewer(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
	{
		String savePath = "";
		String saveFileName = "";
		
		//CrossUploader
		int file_sn = Util.nvl(request.getParameter("file_sn"), 0);
		/*
		if(file_sn == 0)
		{
			String downloadFormData = request.getParameter("CD_DOWNLOAD_FILE_INFO"); 
			downloadFormData = downloadFormData.replaceAll("&quot;", "\"");
			
			JSONObject json = new JSONObject(downloadFormData);
			
			file_sn = Util.nvl(json.get("fileId"), 0);
		}
		*/


		BoardFileVo file = boardFileService.select(file_sn);
		
		if(file != null)
		{
			savePath = "synap";
			ConvertToHtml cvt = new ConvertToHtml();
			
			String filePath = "";
			filePath = String.format("%s%s/%s"
											, EgovProperties.getProperty("Globals.FilePath")
											, "board"
											, file.getFile_phyFileNm()
											);
			String outputPath = cvt.makeMonthDir(EgovProperties.getProperty("Globals.FilePath")+savePath);
			saveFileName = cvt.nameGoMD5(file.getFile_phyFileNm());
			savePath = outputPath.replaceAll(EgovProperties.getProperty("Globals.SystemPath"), "");
			int outputValue = cvt.convertToHtml(filePath, outputPath, saveFileName);
		}
		else
		{
			model.addAttribute("message", "파일 정보가 없습니다.");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}

		return "redirect:/common/synap/skin/doc.html?fn="+saveFileName+"&rs="+savePath;
	}
}
