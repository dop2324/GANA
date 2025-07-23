package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.LogVo;

@Mapper("LogMapper")
public interface LogMapper 
{
	public int insert(LogVo vo);
	
	public int selectListCnt(Map<String, Object> map);

	public List<LogVo> selectList(Map<String, Object> map);
}
