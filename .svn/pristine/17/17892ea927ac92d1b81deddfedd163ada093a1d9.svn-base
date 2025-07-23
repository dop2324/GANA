package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.menu.service.LogErrorService;
import egovframework.dnworks.cms.menu.service.LogErrorVo;

@Service("LogErrorService")
public class LogErrorServiceImpl extends EgovAbstractServiceImpl implements LogErrorService 
{
	@Autowired
	private LogErrorMapper mapper;

	@Override
	public int insert(LogErrorVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public Map<String, Object> select(int err_seq) {
		// TODO Auto-generated method stub
		return mapper.select(err_seq);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<LogErrorVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
