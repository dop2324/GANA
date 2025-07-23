package egovframework.dnworks.func.memb.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.dnworks.func.memb.service.MembInfoVO;

@Mapper("MembMapper")
public interface MembMapper {

	void createDataMemb(MembInfoVO insertVO) throws Exception;

	int isDuplicate(MembInfoVO searchVO) throws Exception;

	int selectListCnt(Map<String, Object> map) throws Exception;

	List<MembInfoVO> selectList(Map<String, Object> map) throws Exception;

	MembInfoVO select(Map<String, Object> map) throws Exception;

	void updateMembStatCd(EgovMap map) throws Exception;

	void updatePswd(MembInfoVO updateVO) throws Exception;

	void updateLgnFailCnt(Map<String, Object> map) throws Exception;

	void updateLgnSucc(Map<String, Object> map) throws Exception;

	void updateMembInfo(MembInfoVO updateVO) throws Exception;

	MembInfoVO selectLogin(Map<String, Object> map) throws Exception;

	List<MembInfoVO> selectLngtmList(Map<String, Object> map) throws Exception;

	void updateLngtmMemb(Map<String, Object> map) throws Exception;

	MembInfoVO selectMembInfo(Map<String, Object> map) throws Exception;

}
