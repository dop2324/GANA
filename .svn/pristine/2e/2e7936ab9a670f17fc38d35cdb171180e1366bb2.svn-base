package egovframework.dnworks.cms.member.service;

import java.util.List;
import java.util.Map;

public interface GroupService 
{
	public abstract int save(GroupVo vo);
	
	public abstract int setOrder(Map<String, Object> map);
	
	public abstract int disableSubGroup(GroupVo vo);
	
	public abstract int delete(String grp_id);
	public abstract String deleteRtnValue(String grp_id);
	public abstract int deleteOrder(String grp_id);
	
	public abstract GroupVo select(String grp_id);
	public abstract List<GroupVo> selectTree(String grp_id);
	public abstract List<GroupVo> selectExcludeTree(String grp_id);
	public abstract List<GroupVo> selectGroupPath(String grp_id);
}
