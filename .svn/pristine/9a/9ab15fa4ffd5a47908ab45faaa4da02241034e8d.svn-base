package egovframework.dnworks.cms.board.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.service.BoardContentVo;

@Mapper("BoardContentMapper")
public interface BoardContentMapper 
{
	public int save(BoardContentVo vo);
	public int delete(int con_sn);
	public BoardContentVo select(int con_sn);
	public List<BoardContentVo> selectList(Map<String, Object> map);
}
