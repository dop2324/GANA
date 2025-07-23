package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cryptography.EgovCryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  ----------     --------    ---------------------------
 *  2009.03.25     이삼섭        최초 생성
 *  2016.10.13     장동한        deleteFileInf 메소드 return 방식 수정
 *  2022.12.02     윤창원        File ID 암호화 처리
 *  2022.12.22     신용호        JSTL 커스텀 태그 추가 및 기능 보완
 *
 *      </pre>
 */
@Controller
public class EgovFileMngController extends WebDefault {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngController.class);
	
	/** 암호화서비스 */
	private static EgovCryptoService cryptoService;
	
	@Resource(name = "egovARIACryptoService")
	public void setEgovCryptoService(EgovCryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	public static final String ALGORITHM_KEY = EgovProperties.getProperty("Globals.File.algorithmKey");
	
	/**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping("/common/fms/selectFileInfs.do")
	public String selectFileInfs(HttpServletRequest request, ModelMap model,
			@ModelAttribute("searchVO") FileVO fileVO,
    		@RequestParam("param_atchFileId") String param_atchFileId,
    		@RequestParam(value = "param_updateFlag", required = false) String param_updateFlag,
    		@RequestParam(value = "param_returnUrl", required = false) String param_returnUrl) throws Exception {
		
		String atchFileId = paramAtchFileIdToDecrypt(param_atchFileId);
		
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);

		// FileId를 유추하지 못하도록 세션ID와 함께 암호화하여 표시한다. (2022.12.06 추가) - 파일아이디가 유추 불가능하도록 조치
		for (FileVO file : result) {
			String sessionId = request.getSession().getId();
			String toEncrypt = sessionId + "|" + file.getAtchFileId();
			file.setAtchFileId(Base64.getEncoder().encodeToString(
					cryptoService.encrypt(toEncrypt.getBytes(), ALGORITHM_KEY)));
		}

		model.addAttribute("fileList", result);
		model.addAttribute("updateFlag", param_updateFlag);
		model.addAttribute("fileListCnt", result.size());
		model.addAttribute("atchFileId", param_atchFileId);
		model.addAttribute("decodedAtchFileId", atchFileId);
		model.addAttribute("paramUrl", param_returnUrl);
		
		return "common/fms/EgovFileList";
	}
	
	/**
	 * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	 *
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/common/fms/FileDown.do")
	public void cvplFileDownload(HttpServletRequest request, HttpServletResponse response
			, @RequestParam("atchFileId") String atchFileId
			, @RequestParam("fileSn") String fileSn) throws Exception {
		
		// 조회권한 체크
		int prmVal = super.getPermission(request, request.getSession());
		if(!Util.hasPermission(prmVal, Code.Prm.PrmSel.getCode())) {
    		JavaScriptUtil.flushJsAlertAndHistoryBack(response, "다운로드 권한이 없습니다.");
    	}

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(paramAtchFileIdToDecrypt(atchFileId));
		fileVO.setFileSn(Integer.parseInt(fileSn));
		FileVO fvo = fileService.selectFileInf(fileVO);

		String fileStreCours = EgovWebUtil.filePathBlackList(fvo.getFileStreCours());
		String streFileNm = EgovWebUtil.filePathBlackList(fvo.getStreFileNm());
		File uFile = new File(fileStreCours, streFileNm);
		long fSize = uFile.length();

		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);
			setDisposition(fvo.getOrignlFileNm(), request, response);

			/*
			 * FileCopyUtils.copy(in, response.getOutputStream());
			 * in.close();
			 * response.getOutputStream().flush();
			 * response.getOutputStream().close();
			 */
			BufferedInputStream in = null;
			BufferedOutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());

				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (Exception ex) {
				// 다음 Exception 무시 처리
				// Connection reset by peer: socket write error
				LOGGER.debug("IGNORED: {}", ex.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception ignore) {
						LOGGER.debug("IGNORED: {}", ignore.getMessage());
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						LOGGER.debug("IGNORED: {}", ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
			printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("<br><br><br>&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}
	
	/**
     * 첨부파일을 이미지로 조회하여 브라우저에 표시합니다.
     * <img src="/common/fms/ImageView.do?atchFileId=...&fileSn=..." /> 형태로 사용됩니다.
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param atchFileId 암호화된 파일 ID
     * @param fileSn     파일 순번
     * @throws Exception
     */
	@RequestMapping(value = "/common/fms/ImageView.do")
	public void imageView(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("atchFileId") String atchFileId,
            @RequestParam("fileSn") String fileSn) throws Exception {
		
		FileVO fileVO = new FileVO();
        fileVO.setAtchFileId(paramAtchFileIdToDecrypt(atchFileId));
        fileVO.setFileSn(Integer.parseInt(fileSn));
        
        FileVO fvo = fileService.selectFileInf(fileVO);
        
        // 파일 경로 및 이름에 대한 보안 처리
        String fileStreCours = EgovWebUtil.filePathBlackList(fvo.getFileStreCours());
        String streFileNm = EgovWebUtil.filePathBlackList(fvo.getStreFileNm());
        File uFile = new File(fileStreCours, streFileNm);
        
        if (uFile.exists() && uFile.length() > 0) {
        	String originalFileName = fvo.getOrignlFileNm();
        	String fileExtension = StringUtils.substringAfterLast(originalFileName, ".").toLowerCase();
        	
        	String mimetype = "application/octet-stream";
        	switch (fileExtension) {
	            case "jpeg":
	            case "jpg":
	                mimetype = "image/jpeg";
	                break;
	            case "png":
	                mimetype = "image/png";
	                break;
	            case "gif":
	                mimetype = "image/gif";
	                break;
	            case "bmp":
	                mimetype = "image/bmp";
	                break;
	            case "webp":
	                mimetype = "image/webp";
	                break;
	        }
	        response.setContentType(mimetype);
	        
		     // Content-Disposition 헤더를 설정하지 않아 브라우저가 이미지를 인라인으로 표시하도록 함
		     // setDisposition(fvo.getOrignlFileNm(), request, response); // 이 라인을 제거 또는 주석 처리
	        
	        // 이미지 캐싱을 위한 헤더 설정 (선택 사항이지만 성능에 도움)
	        response.setHeader("Cache-Control", "max-age=86400"); // 24시간 동안 캐시
	        
	        BufferedInputStream in = null;
            BufferedOutputStream out = null;

            try {
                in = new BufferedInputStream(new FileInputStream(uFile));
                out = new BufferedOutputStream(response.getOutputStream());

                FileCopyUtils.copy(in, out);
                out.flush();
            } catch (Exception ex) {
                // Connection reset by peer: socket write error 등의 예외는 무시
                LOGGER.debug("IGNORED: {}", ex.getMessage());
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception ignore) {
                        LOGGER.debug("IGNORED: {}", ignore.getMessage());
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception ignore) {
                        LOGGER.debug("IGNORED: {}", ignore.getMessage());
                    }
                }
            }
        }else {
        	// --- 4. 파일이 없을 경우의 처리 (변경) ---
            // HTML 오류 페이지 대신 404 Not Found 응답을 보내는 것이 일반적입니다.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
	}
	
    /**
     * 첨부파일에 대한 삭제를 처리한다.
     *
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/common/fms/deleteFileInfs.do")
    public String deleteFileInf(HttpServletRequest request,HttpServletResponse response, ModelMap model
    		, @RequestParam("atchFileId") String atchFileId
			, @RequestParam("fileSn") String fileSn
    		, @RequestParam("returnUrl") String returnUrl ) throws Exception {
    	
    	try {
        	// 삭제권한이 있는지 체크
        	int prmVal = super.getPermission(request, request.getSession());
        	if(!Util.hasPermission(prmVal, Code.Prm.PrmDel.getCode())) {
        		JavaScriptUtil.flushJsAlertAndHistoryBack(response, "삭제권한이 없습니다.");
        	}
        	
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(atchFileId);
    		fileVO.setFileSn(Integer.parseInt(fileSn));
        	
    		// 실제 물리적 파일 삭제
    		FileVO fvo = this.fileService.selectFileInf(fileVO);
    		if (fvo != null) {
    		    String fullPath = fvo.getFileStreCours() + File.separator + fvo.getStreFileNm();
    		    File file = new File(fullPath);
    		    if (file.exists()) {
    		        boolean deleted = file.delete();
    		        if (!deleted) {
    		            LOGGER.warn("파일 삭제 실패: " + fullPath);
    		        }
    		    } else {
    		        LOGGER.warn("삭제할 파일이 존재하지 않음: " + fullPath);
    		    }
    		}
    		
        	// 삭제처리
        	fileService.deleteFileInf(fileVO);
        	
        	JavaScriptUtil.flushJsAlertAndLocation(response, "삭제되었습니다.", returnUrl);
        	return null;
    		
    	}catch(Exception e) {
    		LOGGER.error(e.toString());
    		JavaScriptUtil.flushJsAlertAndHistoryBack(response, "삭제에 실패했습니다.");
    		return null;
    	}
    }
    
    /**
     * atchFileId 파라미터 암호화를 복호화 하기
     * @param String encAtchFileId
     * */
    public String paramAtchFileIdToDecrypt(String encAtchFileId) {
    	String atchFileId = "";
    	
    	// atchFileId가 URL 파라미터로 전달되면서 '+' 문자가 공백으로 변환될 수 있으므로 다시 '+'로 치환
    	encAtchFileId = encAtchFileId.replaceAll(" ", "+");
    	byte[] decodedBytes = Base64.getDecoder().decode(encAtchFileId);
    	String decodedString = new String(cryptoService.decrypt(decodedBytes, ALGORITHM_KEY));
    	
		if (decodedString.contains("|")) {
			atchFileId = StringUtils.substringAfter(decodedString, "|");
		} else {
			atchFileId = decodedString;
		}
    	
    	return atchFileId;
    }
	
	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	/**
	 * Disposition 지정하기.
	 *
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			//throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	/**
	 * 원본 문자열을 암호화 하는 메서드.
	 * 
	 * @param source 원본 문자열
	 * @return 암호화 문자열
	*/
	public static String encrypt(String atchFileId) {
		String returnVal = "-";
		returnVal = Base64.getEncoder().encodeToString(cryptoService.encrypt(atchFileId.getBytes(), ALGORITHM_KEY));
		return returnVal;
	}

	/**
	 * 원본 문자열을 암호화 하는 메서드.
	 * 
	 * @param source 원본 문자열
	 * @return 암호화 문자열
	 */
	public static String encryptSession(String atchFileId, String sessionId) {
		String returnVal = "-";
		String toEncrypt = sessionId + "|" + atchFileId;
		returnVal = Base64.getEncoder().encodeToString(cryptoService.encrypt(toEncrypt.getBytes(), ALGORITHM_KEY));
		return returnVal;
	}
		
	/**
	 * 암호화 문자열을 복호화 하는 메서드.
	 * @param source 암호화 문자열
	 * @return 원본 문자열
	 */
	public static String decrypt(String base64AtchFileId) {
		String returnVal = "FILE_ID_DECRIPT_EXCEPTION_02";
		if (base64AtchFileId!=null && !"".equals(base64AtchFileId)) {
			try {
				byte[] encrypted_atchFileId = Base64.getDecoder().decode(base64AtchFileId);
				returnVal = new String(cryptoService.decrypt(encrypted_atchFileId, ALGORITHM_KEY));
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
			}
		}
		return returnVal;
	}
}
