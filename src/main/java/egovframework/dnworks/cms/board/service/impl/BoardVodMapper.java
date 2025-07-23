package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.service.BoardVodVo;

@Mapper("BoardVodMapper")
public interface BoardVodMapper 
{
	public int save(BoardVodVo vo);
	public int delete(int vod_sn);
	public List<BoardVodVo> selectList(int bod_sn);
}
