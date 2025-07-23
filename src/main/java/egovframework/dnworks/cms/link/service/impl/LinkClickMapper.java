package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.link.service.LinkClickVo;

@Mapper("LinkClickMapper")
public interface LinkClickMapper 
{
	public int insert(LinkClickVo vo);
	public int selectListCnt(Map<String, Object> map);
	public List<LinkClickVo> selectList(Map<String, Object> map);
	
	public List<LinkClickVo> selectYear(Map<String, Object> map);
	public List<LinkClickVo> selectMonth(Map<String, Object> map);
	public List<LinkClickVo> selectDay(Map<String, Object> map);
}
