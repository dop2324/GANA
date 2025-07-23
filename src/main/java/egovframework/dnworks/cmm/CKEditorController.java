package egovframework.dnworks.cmm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovFormBasedFileVo;
import egovframework.dnworks.cmm.util.Thumbnailator;
import egovframework.dnworks.cmm.util.Util;

@Controller
public class CKEditorController 
{
	@RequestMapping(value={"/common/ckupload.do"}, produces = "text/json; charset=UTF-8")
	public @ResponseBody String uploadCkFile(final MultipartHttpServletRequest request, ModelMap model) throws Exception
	{
		String ckbase = "ckuploads";
		
		String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+ckbase;
		String[] ext 		= EgovProperties.getProperty("Globals.file.BadExt").split(",");
		String[] imgExt 	= EgovProperties.getProperty("Globals.file.ImgExt").split(",");
		int 	maxFileSize	= Util.nvl(EgovProperties.getProperty("Globals.CFG_MAXFILESIZE"), 0);
		
		List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, maxFileSize, ext);
		
		String uploadPath = "";
		boolean uploadOk = false;
		if(files.size() > 0) {
			for(EgovFormBasedFileVo file:files) {
				if( !Util.contains(imgExt, file.getFileExtension())) {
					File f = new File(String.format("%s%s/%s", uploadDir, file.getServerSubPath(), file.getPhysicalName()));
					f.delete();
					uploadOk = false;
				} else {
					//create thumbnail 
					Thumbnailator.createThumbImage(uploadDir, file.getServerSubPath(), file.getPhysicalName());
					uploadOk = true;
				}
				
				uploadPath = String.format("%s%s/%s/%s"
						, EgovProperties.getProperty("Globals.UploadPath")
						, ckbase
						, file.getServerSubPath(), file.getPhysicalName()
				);
			}
		}
		
		//{"filename" : "filename", "uploaded" : 1, "url":"${uploadPath}"}
		JSONObject json = new JSONObject();
		json.put("filename"	, "");
		json.put("uploaded"	, uploadOk ? 1:0);
		json.put("url"		, uploadPath);
		
		return json.toString();

	}
	
	/**
	 * 기능프로그램 CKEditor 용 추가
	 * */
	@RequestMapping(value={"/common/func/ckupload.do"}, produces = "text/json; charset=UTF-8")
	public @ResponseBody String funcUploadCkFile(final MultipartHttpServletRequest request, ModelMap model) throws Exception
	{
		String ckbase = "ckuploads";
		
		String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+ckbase;
		String[] ext 		= EgovProperties.getProperty("Globals.file.BadExt").split(",");
		String[] imgExt 	= EgovProperties.getProperty("Globals.file.ImgExt").split(",");
		int 	maxFileSize	= Util.nvl(EgovProperties.getProperty("Globals.CFG_MAXFILESIZE"), 0);
		
		List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, maxFileSize, ext);
		
		String uploadPath = "";
		boolean uploadOk = false;
		if(files.size() > 0) {
			for(EgovFormBasedFileVo file:files) {
				if( !Util.contains(imgExt, file.getFileExtension())) {
					File f = new File(String.format("%s%s/%s", uploadDir, file.getServerSubPath(), file.getPhysicalName()));
					f.delete();
					uploadOk = false;
				} else {
					//create thumbnail 
					Thumbnailator.createThumbImage(uploadDir, file.getServerSubPath(), file.getPhysicalName());
					uploadOk = true;
				}
				
				uploadPath = String.format("%s%s?path=%s&phySicalName=%s"
						, request.getContextPath()
						, "/common/func/image.do"
						, file.getServerSubPath()
						, file.getPhysicalName()
				);
			}
		}
		
		//{"filename" : "filename", "uploaded" : 1, "url":"${uploadPath}"}
		JSONObject json = new JSONObject();
		json.put("filename"	, "");
		json.put("uploaded"	, uploadOk ? 1:0);
		json.put("url"		, uploadPath);
		
		return json.toString();

	}
	
	@RequestMapping(value = "/common/func/image.do")
	public void ckImage(HttpServletRequest request, HttpServletResponse response
			, @RequestParam("path") String path
			, @RequestParam("phySicalName") String phySicalName) throws Exception {
		String uploadPath = EgovProperties.getProperty("Globals.FilePath");
		String ckbase = "ckuploads";
		
		String filePath = String.format("%s%s/%s/%s"
				, uploadPath , ckbase , path , phySicalName);
		
		File file = new File(filePath);
		if (!file.exists()) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        return;
	    }
		
		String mimeType = request.getServletContext().getMimeType(file.getName());
	    if (mimeType == null) {
	        mimeType = "application/octet-stream"; // fallback
	    }
	    
	    response.setContentType(mimeType);
	    response.setContentLengthLong(file.length());
		
	    // 이미지 파일 바이너리 스트림 전송
	    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
	         BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {

	        byte[] buffer = new byte[8192];
	        int bytesRead;
	        while ((bytesRead = in.read(buffer)) != -1) {
	            out.write(buffer, 0, bytesRead);
	        }
	        out.flush();
	    }
	}
	
}
