package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.MenuGratifyVo;

@Mapper("MenuGratifyMapper")
public interface MenuGratifyMapper 
{
	public int insert(MenuGratifyVo vo);
	public List<MenuGratifyVo> selectList(Map<String, Object> map);
	public int selectCmmntListCnt(Map<String, Object> map);
	public List<MenuGratifyVo> selectCmmntList(Map<String, Object> map);
}
