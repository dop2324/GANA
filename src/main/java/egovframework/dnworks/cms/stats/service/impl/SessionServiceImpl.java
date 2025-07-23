package egovframework.dnworks.cms.stats.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.stats.service.SessionService;
import egovframework.dnworks.cms.stats.service.SessionVo;

@Service("SessionService")
public class SessionServiceImpl extends EgovAbstractServiceImpl  implements  SessionService
{
	@Autowired
	protected SessionMapper mapper;

	@Override
	public int insert(SessionVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int insertTrack(SessionVo vo) {
		// TODO Auto-generated method stub
		return mapper.insertTrack(vo);
	}

	@Override
	public int update(SessionVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<SessionVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
