package egovframework.dnworks.func.memb.service;

import java.util.List;
import java.util.Map;

public interface MembAgentService {

	public void insert(MembAgentVO vo) throws Exception;

	public int selectListCnt(Map<String, Object> map) throws Exception;

	public List<MembAgentVO> selectList(Map<String, Object> map) throws Exception;

	public MembAgentVO select(Map<String, Object> map) throws Exception;

	public void update(MembAgentVO vo) throws Exception;

	public void delete(MembAgentVO vo) throws Exception;

	public boolean existsClientId(String clientId) throws Exception;
}
