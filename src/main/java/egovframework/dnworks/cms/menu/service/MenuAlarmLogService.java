package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface MenuAlarmLogService {
	public abstract int insert(MenuAlarmLogVo vo);
	
	public abstract int selectListCnt(Map<String, Object> map);

	public abstract List<MenuAlarmLogVo> selectList(Map<String, Object> map);
}
