package egovframework.com.cmm.service;

import java.util.List;

public interface EgovFileMngService {

	/**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
	public String insertFileInfs(List<?> result) throws Exception;

	public int getMaxFileSN(FileVO fvo) throws Exception;

	public void updateFileInfs(List<FileVO> result) throws Exception;

	public List<FileVO> selectFileInfs(FileVO fvo) throws Exception;

	/**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
	public FileVO selectFileInf(FileVO fileVO) throws Exception;

	public void deleteFileInf(FileVO fileVO) throws Exception;
	
	public void deleteFileInfs(FileVO fileVO) throws Exception;

	/**
	 * 파일 서비스상에서 모두 삭제한다.
	 * */
	public void deleteFileMng(FileVO fvo) throws Exception;

}
