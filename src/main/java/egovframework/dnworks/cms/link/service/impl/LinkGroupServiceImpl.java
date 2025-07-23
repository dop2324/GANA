package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.link.service.LinkGroupService;
import egovframework.dnworks.cms.link.service.LinkGroupVo;

@Service("LinkGroupService")
public class LinkGroupServiceImpl extends EgovAbstractServiceImpl implements LinkGroupService
{
	@Autowired
	private LinkGroupMapper mapper;

	@Override
	public int save(LinkGroupVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int lgp_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.delete(lgp_sn);
	}
	
	@Override
	public LinkGroupVo select(int lgp_sn) {
		// TODO Auto-generated method stub
		return mapper.select(lgp_sn);
	}
	
	@Override
	public List<LinkGroupVo> selectList(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
