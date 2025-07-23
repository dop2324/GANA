package egovframework.dnworks.func.memb.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.func.memb.service.MembAgentVO;

@Mapper("MembAgentMapper")
public interface MembAgentMapper {

	void insert(MembAgentVO vo) throws Exception;

	int selectListCnt(Map<String, Object> map) throws Exception;

	List<MembAgentVO> selectList(Map<String, Object> map) throws Exception;

	MembAgentVO select(Map<String, Object> map) throws Exception;

	void update(MembAgentVO vo) throws Exception;

	void delete(MembAgentVO vo) throws Exception;

	int existsClientId(String clientId) throws Exception;

}
