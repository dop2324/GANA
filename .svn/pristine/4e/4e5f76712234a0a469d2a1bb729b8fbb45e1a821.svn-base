package egovframework.dnworks.func.memb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.memb.service.MembAgentService;
import egovframework.dnworks.func.memb.service.MembAgentVO;

@Service("MembAgentService")
public class MembAgentServiceImpl extends EgovAbstractServiceImpl implements MembAgentService {

	@Autowired
	MembAgentMapper mapper;
	
	@Resource(name = "egovUseOrgIdGnrService")
    private EgovIdGnrService idgenService;
	
	@Override
	public void insert(MembAgentVO vo) throws Exception {
		// 고유ID 발급
		String useOrgId = this.idgenService.getNextStringId(); 
		vo.setUseOrgId(useOrgId);
		
		mapper.insert(vo);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) throws Exception {
		return mapper.selectListCnt(map);
	}

	@Override
	public List<MembAgentVO> selectList(Map<String, Object> map) throws Exception {
		return mapper.selectList(map);
	}

	@Override
	public MembAgentVO select(Map<String, Object> map) throws Exception {
		return mapper.select(map);
	}

	@Override
	public void update(MembAgentVO vo) throws Exception {
		mapper.update(vo);
	}

	@Override
	public void delete(MembAgentVO vo) throws Exception {
		mapper.delete(vo);
	}

	@Override
	public boolean existsClientId(String clientId) throws Exception {
		int count = mapper.existsClientId(clientId);
		return count > 0;
	}

}
