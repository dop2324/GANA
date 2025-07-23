package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.service.BoardDeptFileVo;

@Mapper("BoardDeptFileMapper")
public interface BoardDeptFileMapper 
{
	public int save(BoardDeptFileVo vo);
	public int delete(int file_sn);
	public BoardDeptFileVo select(int file_sn);
	public List<BoardDeptFileVo> selectList(int dept_sn);
}
