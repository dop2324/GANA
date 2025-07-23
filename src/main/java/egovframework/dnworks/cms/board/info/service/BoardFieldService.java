package egovframework.dnworks.cms.board.info.service;

import java.util.List;
import java.util.Map;

public interface BoardFieldService
{
	public abstract int save(BoardFieldVo vo);
	public abstract int delete(String bfd_id);
	public abstract int afterDelete(String bfd_id);
	public abstract int setOrder(Map<String, Object> map);
	
	public abstract BoardFieldVo select(String bfd_id);
	public abstract List<BoardFieldVo> selectList(Map<String, Object> map);
}
