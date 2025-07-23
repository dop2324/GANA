package egovframework.dnworks.cms.board.info.service;

import java.util.List;

public interface BoardGroupService 
{
	public abstract int save(BoardGroupVo vo);
	public abstract int delete(int bgp_sn);
	public abstract BoardGroupVo select(int bgp_sn);
	public abstract List<BoardGroupVo> selectList(String mnu_code);
}
