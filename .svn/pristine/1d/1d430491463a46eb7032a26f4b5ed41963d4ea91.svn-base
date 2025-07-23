package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.link.service.LinkClickService;
import egovframework.dnworks.cms.link.service.LinkClickVo;

@Service("LinkClickService")
public class LinkClickServiceImpl extends EgovAbstractServiceImpl implements LinkClickService
{
	@Autowired
	private LinkClickMapper mapper;

	@Override
	public int insert(LinkClickVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<LinkClickVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

	@Override
	public List<LinkClickVo> selectYear(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectYear(map);
	}

	@Override
	public List<LinkClickVo> selectMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectMonth(map);
	}

	@Override
	public List<LinkClickVo> selectDay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectDay(map);
	}
}
