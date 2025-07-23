package egovframework.dnworks.cms.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.blacklist.service.BlackListIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListIPVo;

@Service("BlackListIPService")
public class BlackListIPServiceImpl extends EgovAbstractServiceImpl  implements BlackListIPService 
{
	@Autowired
    private BlackListIPMapper mapper;

	@Override
	public int save(BlackListIPVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int bli_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.delete(bli_sn);
	}
	
	@Override
	public BlackListIPVo select(int bli_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.select(bli_sn);
	}

	@Override
	public List<BlackListIPVo> selectList(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(site_code);
	}

	@Override
	public int isBlackListIP(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.isBlackListIP(map);
	}

	@Override
	public List<BlackListIPVo> selectIPList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectIPList(map);
	}
}
