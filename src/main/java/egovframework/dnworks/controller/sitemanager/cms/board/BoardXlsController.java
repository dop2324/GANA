package egovframework.dnworks.controller.sitemanager.cms.board;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovFormBasedFileUtil;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.service.BoardService;

@Controller
@RequestMapping("/SiteManager/cms/board/xls")
public class BoardXlsController extends WebDefault 
{
	@Autowired
	private BoardInfoService boardInfoService;
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * xls download
	 */
	@RequestMapping("/board_xlsDownload.do")
	public String board_xlsDownload(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception
	{
		//게시판 설정 유무 확인
		String mnu_code = Util.nvl(request.getParameter("parm_mnuCode"));
		boolean hasAdm = Util.hasPermission(super.getPermission(request, request.getSession()), Code.Prm.PrmAdm.getCode());
		
		BoardInfoVo infoVo = boardInfoService.select(mnu_code);
		
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), infoVo.getInf_pageSize());
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		String srchBgpSn 	= Util.nvl(request.getParameter("srchBgpSn"));
		String srchSttus 	= Util.nvl(request.getParameter("srchSttus"));
		String srchDelete 	= Util.nvl(request.getParameter("srchDelete"));
		String srchSDate	= Util.nvl(request.getParameter("srchSDate"));
		String srchEDate	= Util.nvl(request.getParameter("srchEDate"));
		String srchBodSkin	= Util.nvl(request.getParameter("srchBodSkin"));
		String srchBodAll	= Util.nvl(request.getParameter("srchBodAll"));
		String srchDeptSttus= Util.nvl(request.getParameter("srchDeptSttus"));
		
		//boardList
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		map.put("mnu_code"		, mnu_code);
		map.put("srchKwd"		, srchKwd);
		map.put("srchBgpSn"		, srchBgpSn);
		map.put("srchNotice"	, 0);
		map.put("srchSttus"		, srchSttus);
		map.put("srchDelete"	, srchDelete);
		map.put("srchSDate"		, srchSDate);
		map.put("srchEDate"		, srchEDate);
		map.put("hasPrmAdm"		, hasAdm);
		map.put("boardPublic"	, infoVo.getBoardPublicList().size());
		map.put("srchBodSkin"	, srchBodSkin);
		map.put("srchBodAll"	, srchBodAll);
		//map.put("srchProcess"	, srchProcess);

		if(!srchSDate.equals("2000-01-01") || !srchEDate.equals("9999-12-31"))
		{
			int totalCnt = boardService.selectListCnt(map);
			if(totalCnt > pageSize ) map.put("pageSize", totalCnt);
		}
		
		List<Map<String, Object>> boardList = boardService.selectList(map);

		String destDir = "temp";
		String destFile = infoVo.getMnu_nm();
		List<String> columnList = new ArrayList<String>();
		
		FileOutputStream fileoutputstream = null;
		try {
			if(boardList != null && boardList.size() > 0) {
				Map<String, Object> m = boardList.get(0);
				for(String k:m.keySet()) {
					if(k.equals("bod_no")) continue;
					if(k.equals("bod_commentCnt")) continue;
					if(k.equals("bod_replyCnt")) continue;
					if(k.equals("bod_fileCnt")) continue;
					if(k.equals("bod_startDate")) continue;
					if(k.equals("bod_endDate")) continue;
					if(k.equals("bod_lock")) continue;
					if(k.equals("bod_notice")) continue;
					if(k.equals("bod_sttus")) continue;
					if(k.equals("bod_delSttus")) continue;
					if(k.equals("bod_ref")) continue;
					if(k.equals("bod_level")) continue;
					if(k.equals("bod_sort")) continue;
					if(k.equals("bod_pushGroup")) continue;
					
					if(k.equals("con_regDI")) continue;
					if(k.equals("con_yesCnt")) continue;
					if(k.equals("con_noCnt")) continue;
					
					
					if(k.equals("bgp_sn")) continue;
					if(k.equals("dept_sttus")) continue;
					if(k.equals("mnu_code")) continue;
					if(k.equals("mnu_nm")) continue;
					if(k.equals("mnu_linkUrl")) continue;
					if(k.equals("mnu_param")) continue;
					if(k.equals("site_code")) continue;
					if(k.equals("site_nm")) continue;
					if(k.equals("inf_directory")) continue;
					if(k.equals("bod_dateDiff")) continue;
					if(k.equals("file_fileCnt")) continue;
					if(k.equals("bod_deptCnt")) continue;
					if(k.equals("bod_listFileNm")) continue;
					columnList.add(k);
				}
			}
			
			//1차로 workbook을 생성 
			XSSFWorkbook workbook=new XSSFWorkbook();
			//2차는 sheet생성 
			XSSFSheet sheet=workbook.createSheet("시트명");
			//엑셀의 행 
			XSSFRow row=null;
			//엑셀의 셀 
			XSSFCell cell=null;
			//임의의 DB데이터 조회
			if(boardList != null && boardList.size() > 0) {
				// head
				int i=0;
		        if(columnList != null && columnList.size() > 0) {
		        	row=sheet.createRow((short)i++);
		        	int z = 0;
		        	for(String k:columnList) {
		        		cell=row.createCell(z);
		        		String head = k;
		        		switch(k) {
			        		case "bod_sn" 				: 	head = "번호"; break;
		        			case "bod_ttl" 				: 	head = "제목"; break;
		        			case "bod_readCnt" 			: 	head = "조회수"; break;
		        			
		        			case "bod_delUserId" 		: 	head = "삭제 ID"; break;
		        			case "bod_delDt" 			: 	head = "삭제 일시"; break;
		        			case "bod_delReason" 		: 	head = "삭제 사유"; break;
		        			
		        			case "dept_id" 				: 	head = "부서 ID"; break;
		        			case "dept_nm" 				: 	head = "부서명"; break;
		        			
		        			case "con_id" 				: 	head = "작성자ID"; break;
		        			case "con_nm" 				: 	head = "작성자"; break;
		        			case "con_cn" 				: 	head = "내용"; break;
		        			
		        			case "con_telno" 			: 	head = "연락처"; break;
		        			case "con_zip" 				: 	head = "우편번호"; break;
		        			case "con_addr" 			: 	head = "주소"; break;
		        			case "con_regDt" 			: 	head = "등록일시"; break;
		        			case "con_linkUrl" 			: 	head = "링크 주소"; break;
		        			
		        			//case "bgp_uid" : 	head = "제목"; break;
		        			case "bgp_nm" 				: 	head = "분류"; break;
		        			case "dept_process" 		: 	head = "처리상태"; break;
		        			case "boardFieldValueList" 	: 	head = "추가컬럼"; break;
		        			
		        			case "inf_skinType" 		: 	head = "게시판 형식"; break;
		        		}
		        		cell.setCellValue(head);
		        		z++;
					}
		        }
		        for(Map<String,Object>mapobject : boardList) {
		        	// 시트에 하나의 행을 생성한다(i 값이 0이면 첫번째 줄에 해당) 
			        row=sheet.createRow((short)i++);
			        
			        if(columnList !=null &&columnList.size() >0) {
			        	for(int j=0; j<columnList.size(); j++) {
			        		//생성된 row에 컬럼을 생성한다 
			                cell=row.createCell(j);
			                //map에 담긴 데이터를 가져와 cell에 add한다 
			                String cellValue = Util.nvl(mapobject.get(columnList.get(j)));
			                
			                String key = columnList.get(j);
			                
			                switch(key) {
				                case "con_telno":	cellValue = AESUtil.decrypt(cellValue); break;
			                	case "con_zip"	:	cellValue = AESUtil.decrypt(cellValue); break;
			                	case "con_addr"	:	cellValue = AESUtil.decrypt(cellValue); break;
			                	case "con_cn" 	: 	cellValue = (String) mapobject.get(columnList.get(j)); break;
			                	//처리단계
			                	case "dept_process" : 
		                							switch(cellValue) {
		                								case "0" : cellValue = "표시없음"; break;
		                								case "1" : cellValue = "신청"; break;
		                								case "2" : cellValue = "접수"; break;
		                								case "4" : cellValue = "담당자지정"; break;
		                								case "5" : cellValue = "완료"; break;
		                								case "6" : cellValue = "접수불가"; break;
		                							}
		                	
		                						break;
			                }
			                cell.setCellValue(cellValue);
			            }
			        }
			    }
			}
			
			String uploadDir	= EgovProperties.getProperty("Globals.FilePath");
			String fileName		= String.format("%s.xlsx", EgovFormBasedFileUtil.getTodayString());
			
			if(!srchSDate.equals("2000-01-01") || !srchEDate.equals("9999-12-31")) {
				fileName = String.format("%s_%s.xlsx", srchSDate, srchEDate);
			}
			String savePath		= String.format("%s%s/%s", uploadDir, destDir, fileName);

			fileoutputstream = new FileOutputStream(savePath);
			//파일을 쓴다
			workbook.write(fileoutputstream);
			//필수로 닫아주어야함 
			fileoutputstream.close();
			
			EgovFormBasedFileUtil.downloadFile(response, EgovProperties.getProperty("Globals.FilePath"), destDir, fileName, String.format("%s_%s", destFile, fileName));
		}catch(IOException e) {
			System.out.println("exception BoardController boardDownloadXls");
		}finally {
			if(fileoutputstream != null) fileoutputstream.close();
		}
		
		return null;
	}
	
	public static String convertClob(Object targetData) throws Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(((Clob)targetData).getCharacterStream());
		
		try {
			String dummy = "";
			while((dummy = reader.readLine()) != null){
				buffer.append(dummy);
			}
			return buffer.toString();
		}catch(Exception e) {
			System.out.println("exception BoardController convertClob");
		}finally{
			if(reader != null) reader.close();
		}
		
		return null;
	}
	
}
