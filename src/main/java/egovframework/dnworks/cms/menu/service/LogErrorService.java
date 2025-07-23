package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface LogErrorService 
{
	public abstract int insert(LogErrorVo vo);
	public abstract Map<String, Object> select(int err_seq);
	
 	public abstract int selectListCnt(Map<String, Object> map);
 	public abstract List<LogErrorVo> selectList(Map<String, Object> map);
}
