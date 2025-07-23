package egovframework.dnworks.cms.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.blacklist.service.BlockLogService;
import egovframework.dnworks.cms.blacklist.service.BlockLogVo;

@Service("BlockLogService")
public class BlockLogServiceImpl extends EgovAbstractServiceImpl implements BlockLogService
{
	@Autowired
	private BlockLogMapper mapper;

	@Override
	public boolean insert(BlockLogVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<BlockLogVo> selectList(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
