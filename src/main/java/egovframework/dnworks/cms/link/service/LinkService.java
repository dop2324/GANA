package egovframework.dnworks.cms.link.service;

import java.util.List;
import java.util.Map;

public interface LinkService 
{
	public abstract int save(LinkVo vo);
	public abstract int delete(int lnk_sn);
	
	public abstract LinkVo select(int lnk_sn);
	public abstract int selectListCnt(Map<String, Object> map);
	public abstract List<LinkVo> selectList(Map<String, Object> map);

	public abstract List<LinkVo> selectDateList();
	public abstract List<LinkVo> selectMainList(List<Integer> item);
}
