package egovframework.dnworks.cms.stats.service;

import java.util.List;
import java.util.Map;

public interface SessionService 
{
	public abstract int insert(SessionVo vo);
	public abstract int insertTrack(SessionVo vo);
	
	public abstract int update(SessionVo vo);
	
	public abstract int selectListCnt(Map<String, Object> map);
	public abstract List<SessionVo> selectList(Map<String, Object> map);
}
