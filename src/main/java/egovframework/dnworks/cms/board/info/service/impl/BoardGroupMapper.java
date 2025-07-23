package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.info.service.BoardGroupVo;

@Mapper("BoardGroupMapper")
public interface BoardGroupMapper 
{
	public int save(BoardGroupVo vo);
	public int delete(int bgp_sn);
	public BoardGroupVo select(int bgp_sn);
	public List<BoardGroupVo> selectList(String mnu_code);
}
