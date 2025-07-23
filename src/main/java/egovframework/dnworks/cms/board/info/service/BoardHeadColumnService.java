package egovframework.dnworks.cms.board.info.service;

public interface BoardHeadColumnService 
{
	public abstract int save(BoardHeadColumnVo vo);
	public abstract int delete(String mnu_code);
	public abstract BoardHeadColumnVo select(String mnu_code);
}
