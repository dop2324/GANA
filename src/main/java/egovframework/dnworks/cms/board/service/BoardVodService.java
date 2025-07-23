package egovframework.dnworks.cms.board.service;

import java.util.List;

public interface BoardVodService 
{
	public abstract int save(BoardVodVo vo);
	public abstract int delete(int vod_sn);
	public abstract List<BoardVodVo> selectList(int bod_sn);
}
