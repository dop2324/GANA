package egovframework.dnworks.cms.board.info.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.info.service.BoardHeadColumnVo;

@Mapper("BoardHeadColumnMapper")
public interface BoardHeadColumnMapper 
{
	public int save(BoardHeadColumnVo vo);
	public int delete(String mnu_code);
	public BoardHeadColumnVo select(String mnu_code);
}
