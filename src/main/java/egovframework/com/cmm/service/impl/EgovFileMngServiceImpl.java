package egovframework.com.cmm.service.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;

/**
 * @Class Name : EgovFileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Service("EgovFileMngService")
public class EgovFileMngServiceImpl extends EgovAbstractServiceImpl implements EgovFileMngService {

	@Autowired
	FileManageMapper mapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovFileMngService.class);
	
	@Override
	public String insertFileInfs(List<?> result) throws Exception {
		String atchFileId = "";
		
		if (result.size() != 0) {
			FileVO vo = (FileVO) result.get(0);
			atchFileId = vo.getAtchFileId();
			
			mapper.insertFileMaster(vo);
			
			Iterator<?> iter = result.iterator();
			while (iter.hasNext()) {
				vo = (FileVO) iter.next();
				mapper.insertFileDetail(vo);
			}
		}
		if(atchFileId == ""){
			atchFileId = null;
		}
		
		return atchFileId;
	}

	@Override
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return mapper.getMaxFileSN(fvo);
	}

	@Override
	public void updateFileInfs(List<FileVO> result) throws Exception {
		FileVO vo;
		Iterator<?> iter = result.iterator();
		
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();
			mapper.insertFileDetail(vo);
		}
	}

	@Override
	public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
		return mapper.selectFileInfs(fvo);
	}

	@Override
	public FileVO selectFileInf(FileVO fileVO) throws Exception {
		return mapper.selectFileInf(fileVO);
	}

	@Override
	public void deleteFileInf(FileVO fileVO) throws Exception {
		mapper.deleteFileInf(fileVO);
	}

	@Override
	public void deleteFileInfs(FileVO fileVO) throws Exception {
		mapper.deleteFileInfs(fileVO);
	}

	@Override
	public void deleteFileMng(FileVO fvo) throws Exception {
		
		// 해당 파일정보 호출
		FileVO fileVO;
		List<FileVO> resultVO = this.selectFileInfs(fvo);
		Iterator<?> iter = resultVO.iterator();
		while (iter.hasNext()) {
			fileVO = (FileVO) iter.next();
			
			// 실제 물리적 파일 삭제
			String fullPath = fileVO.getFileStreCours() + File.separator + fileVO.getStreFileNm();
			File file = new File(fullPath);
			if (file.exists()) {
		        boolean deleted = file.delete();
		        if (!deleted) {
		            LOGGER.warn("파일 삭제 실패: " + fullPath);
		        }
		    } else {
		        LOGGER.warn("삭제할 파일이 존재하지 않음: " + fullPath);
		    }
			
			// Detail 데이터 삭제
			this.deleteFileInf(fileVO);
		}
		
		// TN_FILE 데이터 삭제
		this.deleteFileInfs(fvo);
		
	}

}
