package egovframework.dnworks.cms.board.service;

import java.util.List;

public interface BoardFileService 
{
	public abstract int save(BoardFileVo vo);
	public abstract int delete(int file_sn);
	public abstract BoardFileVo select(int file_sn);
	public abstract List<BoardFileVo> selectList(int bod_sn);
}
