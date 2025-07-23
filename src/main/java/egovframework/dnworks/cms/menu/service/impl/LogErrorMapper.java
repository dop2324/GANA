package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.LogErrorVo;

@Mapper("LogErrorMapper")
public interface LogErrorMapper 
{
	public int insert(LogErrorVo vo);
	public Map<String, Object> select(int err_seq);
	
 	public int selectListCnt(Map<String, Object> map);
 	public List<LogErrorVo> selectList(Map<String, Object> map);
}
