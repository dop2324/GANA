package egovframework.dnworks.func.lnkorg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.lnkorg.service.LnkorgUrlService;
import egovframework.dnworks.func.lnkorg.service.LnkorgUrlVO;

@Service("LnkorgUrlService")
public class LnkorgUrlServiceImpl extends EgovAbstractServiceImpl implements LnkorgUrlService {

	@Autowired
	LnkorgUrlMapper mapper;
	
	@Resource(name = "egovLnkOrgUrlIdGnrService")
    private EgovIdGnrService idgenService;

	@Override
	public void insert(Map<String, Object> param) throws Exception {
		String lnkorgUrlId = idgenService.getNextStringId();
		param.put("lnkorgUrlId", lnkorgUrlId);
		mapper.insert(param);
	}

	@Override
	public List<LnkorgUrlVO> getList(Map<String, Object> param) throws Exception {
		return mapper.getList(param);
	}

	@Override
	public void update(Map<String, Object> param) throws Exception {
		mapper.update(param);
	}

	@Override
	public void remove(Map<String, Object> param) throws Exception {
		mapper.remove(param);
	}
	
}
