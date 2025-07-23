package egovframework.dnworks.func.memb.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembLogVO;

@Mapper("MembLogMapper")
public interface MembLogMapper {

	void insert(MembLogVO insertVO) throws Exception;

	int selectListCnt(Map<String, Object> map) throws Exception;

	List<MembInfoVO> selectList(Map<String, Object> map) throws Exception;

	void removeLogData(Map<String, Object> param) throws Exception;

}
