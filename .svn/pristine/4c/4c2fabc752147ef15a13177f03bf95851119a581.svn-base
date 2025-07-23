package egovframework.dnworks.cms.stats.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.stats.service.SessionVo;

@Mapper("SessionMapper")
public interface SessionMapper 
{
	public int insert(SessionVo vo);
	public int insertTrack(SessionVo vo);
	
	public int update(SessionVo vo);
	
	public int selectListCnt(Map<String, Object> map);
	public List<SessionVo> selectList(Map<String, Object> map);
}
