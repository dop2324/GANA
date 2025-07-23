package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.info.service.BoardInfoVo;

@Mapper("BoardInfoMapper")
public interface BoardInfoMapper 
{
	public int save(BoardInfoVo vo);
	public int delete(String mnu_code);
	
	public BoardInfoVo select(String mnu_code);
	public int checkBoard(String mnu_code);
	public List<BoardInfoVo> selectRssList(String site_code);
}
