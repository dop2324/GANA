package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.service.BoardFieldValueVo;

@Mapper("BoardFieldValueMapper")
public interface BoardFieldValueMapper 
{
	public int save(BoardFieldValueVo vo);
	public int delete(int bod_sn);
	public List<BoardFieldValueVo> selectList(int bod_sn);
}
