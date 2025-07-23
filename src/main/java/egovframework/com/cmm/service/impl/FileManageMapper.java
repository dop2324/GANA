package egovframework.com.cmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.com.cmm.service.FileVO;

@Mapper("FileManageMapper")
public interface FileManageMapper {

	void insertFileMaster(FileVO fileMstrVO) throws Exception;

	void insertFileDetail(FileVO fileVO) throws Exception;

	int getMaxFileSN(FileVO fvo) throws Exception;

	List<FileVO> selectFileInfs(FileVO fvo) throws Exception;

	FileVO selectFileInf(FileVO fileVO) throws Exception;

	void deleteFileInf(FileVO fileVO) throws Exception;

	void deleteFileInfs(FileVO fileVO) throws Exception;

}
