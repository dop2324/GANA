package egovframework.dnworks.cms.board.service;

import java.util.List;

public interface BoardDeptService 
{
	public abstract int save(BoardDeptVo vo);
	public abstract int delete(int dept_sn);
	public abstract BoardDeptVo select(int dept_sn);
	public abstract List<BoardDeptVo> selectList(int bod_sn);
}
