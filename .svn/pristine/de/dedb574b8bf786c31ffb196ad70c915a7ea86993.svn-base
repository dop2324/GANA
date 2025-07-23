package egovframework.dnworks.cms.stats.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.dao.DataAccessException;

import egovframework.dnworks.cms.stats.service.StatsVo;

@Mapper("StatsMapper")
public interface StatsMapper 
{
	public List<StatsVo> selectSite(Map<String, Object> map) throws DataAccessException;
	public List<StatsVo> selectYear(Map<String, Object> map) throws DataAccessException;
	public List<StatsVo> selectMonth(Map<String, Object> map) throws DataAccessException;
	public List<StatsVo> selectDay(Map<String, Object> map) throws DataAccessException;
	public List<StatsVo> selectTime(Map<String, Object> map) throws DataAccessException;
	public List<StatsVo> selectMenu(Map<String, Object> map) throws DataAccessException;
}
