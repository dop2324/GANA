package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.info.service.BoardFieldVo;

@Mapper("BoardFieldMapper")
public interface BoardFieldMapper 
{
	public int save(BoardFieldVo vo);
	public int delete(String bfd_id);
	public int afterDelete(String bfd_id);
	public int setOrder(Map<String, Object> map);
	
	public BoardFieldVo select(String bfd_id);
	public List<BoardFieldVo> selectList(Map<String, Object> map);
}
