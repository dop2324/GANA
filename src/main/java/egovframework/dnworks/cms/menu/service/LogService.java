package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface LogService 
{
	public abstract int insert(LogVo vo);
	
	public abstract int selectListCnt(Map<String, Object> map);

	public abstract List<LogVo> selectList(Map<String, Object> map);
}
