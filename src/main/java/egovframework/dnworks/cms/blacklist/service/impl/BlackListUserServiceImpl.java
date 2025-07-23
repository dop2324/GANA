package egovframework.dnworks.cms.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.blacklist.service.BlackListUserVo;

@Service("BlackListUserService")
public class BlackListUserServiceImpl extends EgovAbstractServiceImpl  implements BlackListUserService
{
	@Autowired
	private BlackListUserMapper mapper;

	@Override
	public int insert(BlackListUserVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(BlackListUserVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int blu_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.delete(blu_sn);
	}
	
	@Override
	public BlackListUserVo select(int blu_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.select(blu_sn);
	}

	@Override
	public List<BlackListUserVo> selectList(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(site_code);
	}

	@Override
	public int isBlackListUser(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.isBlackListUser(map);
	}

	@Override
	public List<BlackListUserVo> selectUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectUserList(map);
	}
}
