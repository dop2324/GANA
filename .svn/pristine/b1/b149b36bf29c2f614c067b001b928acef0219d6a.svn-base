package egovframework.dnworks.cms.menu.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MenuService 
{
	public abstract int save(MenuVo vo, int cmd, int lowApply);
	public abstract int update(MenuVo vo);
	
	public abstract int sttusSubMenu(String menu_code);
	public abstract int updateOrder(Map<String, Object> map);
	
	public abstract int delete(String menu_code);
	public abstract int deleteOrder(String menu_code);
	public abstract int deleteRtnValue(String menu_code);
	
	public abstract String selectMaxMnuCode(String menu_code);
	public abstract MenuVo select(String menu_code);
	
	public abstract LinkedHashMap<String, MenuVo> selectTree(Map<String, Object> map);
	//public abstract List<MenuVo> selectTree(Map<String, Object> map);
	public abstract List<MenuVo> selectPath(Map<String, Object> map);
	public abstract List<String> selectPrmAdminList(Map<String, Object> map);
	public abstract List<String> selectPrmUserList(Map<String, Object> map);
	
	public abstract int updateMdfcnDt(String menu_code);
	public abstract List<MenuVo> selectMdfcnDtList(String site_code);
}
