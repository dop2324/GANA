package egovframework.dnworks.cmm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.util.EgovFormBasedFileUtil;
import egovframework.com.cmm.util.EgovFormBasedFileVo;
import egovframework.dnworks.cmm.util.Util;

public class FileUploadUtil extends EgovFormBasedFileUtil
{
	private static EgovFormBasedFileVo setVo(MultipartHttpServletRequest mptRequest, MultipartFile mFile)
	{
		EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
		String inputName 	= Util.nvl(mFile.getName(),"");
		String tmp 			= mFile.getOriginalFilename();
		
		if (tmp.lastIndexOf("\\") >= 0) {
			tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
		}
		
		vo.setInputName(inputName);
		vo.setFileName(tmp);
		vo.setContentType(mFile.getContentType());
		vo.setServerSubPath(getTodayString());
		vo.setPhysicalName(getPhysicalFileName());
		vo.setSize(mFile.getSize());
		
		//file description
		int fileIndex = 0;
		String[] arrIndex = vo.getInputName().split("_");

		if(arrIndex.length > 1) {
			fileIndex = Util.nvl(arrIndex[1], 0);
			int fileSn			= Util.nvl(mptRequest.getParameter("fileSn_"+fileIndex), -1);
			String fileDesc 	= Util.nvl(mptRequest.getParameter("fileDesc_"+fileIndex), "");
			int fileType		= Util.nvl(mptRequest.getParameter("fileType_"+fileIndex), 0); 
			int fileWatermark	= Util.nvl(mptRequest.getParameter("fileWatermark_"+fileIndex), 0);
			
			//int fileFirstYn		= Util.nvl(mptRequest.getParameter("fileFirstYn_"+fileIndex), 0);
			int fileFirstYn		= Util.nvl(mptRequest.getParameter("fileFirstYn"), 0);
			if(fileFirstYn == fileIndex) fileFirstYn = 1;

			vo.setInputIndex(fileIndex);
			vo.setFileSn(fileSn);
			vo.setFileDescription(fileDesc);
			vo.setFileType(fileType);
			vo.setFirstYn(fileFirstYn == 1 ? 1:0);
			vo.setWatermark(fileWatermark);
		}
		
		return vo;
	}
	
	@SuppressWarnings("rawtypes")
	public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize, String[] badExt) throws Exception 
	{
		List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();
		MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
		Iterator fileIter = mptRequest.getFileNames();
		
		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());
			EgovFormBasedFileVo vo = setVo(mptRequest, mFile);

			String tmpExt = "";
			tmpExt = vo.getFileExtension();
			Arrays.sort(badExt);
			
			if (vo.getFileName().lastIndexOf(".") >= 0 && Arrays.binarySearch(badExt, tmpExt) < 0) {
				vo.setPhysicalName(vo.getPhysicalName() + "." + tmpExt);
			} else {
				vo.setPhysicalName(vo.getPhysicalName() + "_" + tmpExt);
				continue;
				
			}

			if (mFile.getSize() > 0) {
				InputStream is = null;

				try {
					is = mFile.getInputStream();
					saveFile(is, new File(EgovWebUtil.filePathBlackList(where+ SEPERATOR + vo.getServerSubPath()+ SEPERATOR + vo.getPhysicalName())));
				}catch(IOException e) {
					System.out.println("Exception FileUploadUtil uploadFiles ");
				} finally {
					if (is != null) {
						is.close();
					}
				}
				list.add(vo);
			}
		}

		return list;
	}	
	
	/*
	 * 20231108 허용 확장자 추가
	 */
	@SuppressWarnings("rawtypes")
	public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize, String[] badExt, String alwExt) throws Exception 
	{
		List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();
		MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
		Iterator fileIter = mptRequest.getFileNames();
		
		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());
			EgovFormBasedFileVo vo = setVo(mptRequest, mFile);
			
			String tmpExt = "";
			tmpExt = vo.getFileExtension();
			Arrays.sort(badExt);
			
			if (vo.getFileName().lastIndexOf(".") >= 0 && Arrays.binarySearch(badExt, tmpExt) < 0) {
				vo.setPhysicalName(vo.getPhysicalName() + "." + tmpExt);
			} else {
				vo.setPhysicalName(vo.getPhysicalName() + "_" + tmpExt);
				continue;
			}
			
			//허용 확장자인지 구분 하여 업로드 플레그를 설정
			boolean uploadFlag = true;
			if(!alwExt.equals("")){
				String[] arrAlwExt = alwExt.split(","); 
				Arrays.sort(arrAlwExt);
				if (vo.getFileName().lastIndexOf(".") >= 0 && Arrays.binarySearch(arrAlwExt, tmpExt) < 0) {
					uploadFlag = false;
				}
			}

			if (uploadFlag && mFile.getSize() > 0) {
				InputStream is = null;

				try {
					is = mFile.getInputStream();
					saveFile(is, new File(EgovWebUtil.filePathBlackList(where+ SEPERATOR + vo.getServerSubPath()+ SEPERATOR + vo.getPhysicalName())));
				}catch(IOException e) {
					System.out.println("Exception FileUploadUtil uploadFiles ");
				} finally {
					if (is != null) {
						is.close();
					}
				}
				list.add(vo);
			}
		}

		return list;
	}
}
