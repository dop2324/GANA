package egovframework.dnworks.cms.accessip.service.impl;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.accessip.service.AccessIPService;
import egovframework.dnworks.cms.accessip.service.AccessIPVo;


@Service("AccessipService")
public class AccessIPServiceImpl extends EgovAbstractServiceImpl  implements AccessIPService {

    @Autowired
    private AccessIPMapper mapper;

	@Override
	public int insert(AccessIPVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(AccessIPVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int ip_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.delete(ip_sn);
	}
	
	@Override
	public AccessIPVo select(int ip_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.select(ip_sn);
	}

	@Override
	public List<AccessIPVo> selectList(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(site_code);
	}

	@Override
	public int isAccessIP(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.isAccessIP(map);
	}

}
