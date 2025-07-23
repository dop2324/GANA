package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.menu.service.MenuGratifyService;
import egovframework.dnworks.cms.menu.service.MenuGratifyVo;

@Service("MenuGratifyService")
public class MenuGratifyServiceImpl extends EgovAbstractServiceImpl implements MenuGratifyService
{
	@Autowired
	private MenuGratifyMapper mapper;

	@Override
	public int insert(MenuGratifyVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public List<MenuGratifyVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

	@Override
	public int selectCmmntListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectCmmntListCnt(map);
	}

	@Override
	public List<MenuGratifyVo> selectCmmntList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectCmmntList(map);
	}
}
