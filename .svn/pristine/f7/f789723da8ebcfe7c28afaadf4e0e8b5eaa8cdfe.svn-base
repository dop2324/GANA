package egovframework.dnworks.cms.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService 
{
	public abstract Integer save(BoardVo vo);

	public abstract int updateSttus(int bod_sn);
	public abstract int deleteSttus(Map<String, Object> map);
	
	public abstract Integer delete(int bod_sn);
	
	public abstract int updateReadCnt(int bod_sn);
	public abstract int updateCommentCnt(Map<String, Object> map);

	public abstract Map<String, Object> select(int bod_sn);
	
	public abstract Integer selectListCnt(Map<String, Object> map);
	public abstract List<Map<String, Object>> selectList(Map<String, Object> map);
	
	public abstract Map<String, Object> selectPreNxt(Map<String, Object> map);
}
