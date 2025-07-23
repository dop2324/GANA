package egovframework.dnworks.func.org.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.org.service.OrgInfoService;
import egovframework.dnworks.func.org.service.OrgInfoVO;

@Service("orgInfoService")
public class OrgInfoServiceImpl extends EgovAbstractServiceImpl implements OrgInfoService {

	@Autowired
    private OrgInfoMapper orgInfoMapper;

	@Resource(name = "egovOrgIdGnrService")
    private EgovIdGnrService idgenService;

    @Override
    public OrgInfoVO select(String orgUnqId) throws Exception {
        return orgInfoMapper.select(orgUnqId);
    }

    @Override
    public List<OrgInfoVO> selectList(Map<String, Object> param) throws Exception {
        return orgInfoMapper.selectList(param);
    }
    
    @Override
    public int selectListCnt(Map<String, Object> param) throws Exception {
        return orgInfoMapper.selectListCnt(param);
    }

    @Override
    public void insert(OrgInfoVO vo) throws Exception {
    	// 고유ID 발급
		String orgId = this.idgenService.getNextStringId(); 
		vo.setOrgUnqId(orgId);
        orgInfoMapper.insert(vo);
    }

    @Override
    public void update(OrgInfoVO vo) throws Exception {
        orgInfoMapper.update(vo);
    }

    @Override
	public void delete(OrgInfoVO vo) throws Exception {
    	orgInfoMapper.delete(vo);
	}
    
    @Override
    public List<OrgInfoVO> selectListWithApiKeys() throws Exception {
        return orgInfoMapper.selectListWithApiKeys();
    }
} 
