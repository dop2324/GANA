package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.link.service.LinkService;
import egovframework.dnworks.cms.link.service.LinkVo;

@Service("LinkService")
public class LinkServiceImpl extends EgovAbstractServiceImpl implements LinkService 
{
	@Autowired
	private LinkMapper mapper;

	@Override
	public int save(LinkVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int lnk_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(lnk_sn);
	}

	@Override
	public LinkVo select(int lnk_sn) {
		// TODO Auto-generated method stub
		return mapper.select(lnk_sn);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<LinkVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

	@Override
	public List<LinkVo> selectDateList() {
		// TODO Auto-generated method stub
		return mapper.selectDateList();
	}

	@Override
	public List<LinkVo> selectMainList(List<Integer> item) {
		// TODO Auto-generated method stub
		return mapper.selectMainList(item);
	}

}
