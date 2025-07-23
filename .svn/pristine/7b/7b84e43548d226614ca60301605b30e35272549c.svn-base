package egovframework.dnworks.cms.board.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("BoardMapper")
public interface BoardMapper 
{
	public Integer save(Map<String, Object> map);

	public int updateSttus(int bod_sn);
	public int deleteSttus(Map<String, Object> map);
	
	public Integer delete(int bod_sn);
	
	public int updateReadCnt(int bod_sn);
	public int updateCommentCnt(Map<String, Object> map);
	
	public Map<String, Object> select(int bod_sn);
	
	public Integer selectListCnt(Map<String, Object> map);
	public List<Map<String, Object>> selectList(Map<String, Object> map);
	
	public Map<String, Object> selectPreNxt(Map<String, Object> map);
}
