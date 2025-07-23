package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface MenuGratifyService 
{
	public abstract int insert(MenuGratifyVo vo);
	public abstract List<MenuGratifyVo> selectList(Map<String, Object> map);
	public abstract int selectCmmntListCnt(Map<String, Object> map);
	public abstract List<MenuGratifyVo> selectCmmntList(Map<String, Object> map);
}
