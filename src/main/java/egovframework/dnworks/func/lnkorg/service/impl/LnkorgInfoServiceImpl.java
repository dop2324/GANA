package egovframework.dnworks.func.lnkorg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.lnkorg.service.LnkorgInfoService;
import egovframework.dnworks.func.lnkorg.service.LnkorgInfoVO;

@Service("LnkorgInfoService")
public class LnkorgInfoServiceImpl extends EgovAbstractServiceImpl implements LnkorgInfoService {

	@Autowired
    private LnkorgInfoMapper mapper;
	
	@Resource(name = "egovLnkOrgIdGnrService")
    private EgovIdGnrService idgenService;

    @Override
    public LnkorgInfoVO select(String orgUnqId) throws Exception {
        return mapper.select(orgUnqId);
    }

    @Override
    public List<LnkorgInfoVO> selectList(Map<String, Object> param) throws Exception {
        return mapper.selectList(param);
    } 
    
    @Override
    public int selectListCnt(Map<String, Object> param) throws Exception {
        return mapper.selectListCnt(param);
    }

    @Override
    public void insert(LnkorgInfoVO vo) throws Exception {
    	
    	// 고유ID 발급
		String OrgId = this.idgenService.getNextStringId(); 
		vo.setOrgUnqId(OrgId);
		
		mapper.insert(vo);
    }

    @Override
    public void update(LnkorgInfoVO vo) throws Exception {
    	mapper.update(vo);
    }
    
    @Override
	public void delete(LnkorgInfoVO vo) throws Exception {
    	mapper.delete(vo);
	}
}
