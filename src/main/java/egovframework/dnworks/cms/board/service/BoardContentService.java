package egovframework.dnworks.cms.board.service;

import java.util.List;
import java.util.Map;


public interface BoardContentService 
{
	public abstract int save(BoardContentVo vo);
	public abstract int delete(int con_sn);
	public abstract BoardContentVo select(int con_sn);
	public abstract List<BoardContentVo> selectList(Map<String, Object> map);
}
