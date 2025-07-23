package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.MenuVo;

@Mapper("MenuMapper")
public interface MenuMapper 
{
	public int nextId(Map<String, Object> map);
	
	public int save(MenuVo vo);
	public int update(MenuVo vo);
	
	public int updateSubDeptInfo(MenuVo vo);
	public int sttusSubMenu(String menu_code);
	public int updateOrder(Map<String, Object> map);
	
	public int delete(String menu_code);
	public int deleteOrder(String menu_code);
	public int deleteRtnValue(String menu_code);
	
	public String selectMaxMnuCode(String menu_code);
	public MenuVo select(String menu_code);
	public int selectSubMenuCnt(String menu_code);
	
	public List<MenuVo> selectTree(Map<String, Object> map);
	public List<MenuVo> selectPath(Map<String, Object> map);
	public List<String> selectPrmAdminList(Map<String, Object> map);
	public List<String> selectPrmUserList(Map<String, Object> map);
	
	public int updateMdfcnDt(String menu_code);
	public List<MenuVo> selectMdfcnDtList(String site_code);
}
