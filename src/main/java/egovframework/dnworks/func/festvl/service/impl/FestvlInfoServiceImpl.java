package egovframework.dnworks.func.festvl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.festvl.service.FestvlInfoService;
import egovframework.dnworks.func.festvl.service.FestvlVO;

@Service("FestvlInfoService")
public class FestvlInfoServiceImpl extends EgovAbstractServiceImpl implements FestvlInfoService {

	@Autowired
    private FestvlInfoMapper mapper;
	
	@Resource(name = "egovFsvUnqIdGnrService")
    private EgovIdGnrService idgenService;

	@Override
	public int selectListCnt(Map<String, Object> map) throws Exception {
		return mapper.selectListCnt(map);
	}

	@Override
	public List<FestvlVO> selectList(Map<String, Object> map) throws Exception {
		return mapper.selectList(map);
	}

	@Override
	public FestvlVO select(Map<String, Object> param) throws Exception {
		return mapper.select(param);
	}

	@Override
	public void insert(FestvlVO searchVO) throws Exception {
		searchVO.setFsvUnqId(this.idgenService.getNextStringId());
		mapper.insert(searchVO);
	}

	@Override
	public void update(FestvlVO searchVO) throws Exception {
		mapper.update(searchVO);
	}

	@Override
	public void delete(FestvlVO searchVO) throws Exception {
		mapper.delete(searchVO);
	}

}
