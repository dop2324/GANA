package egovframework.dnworks.cms.member.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.member.service.GroupVo;

@Mapper("GroupMapper")
public interface GroupMapper 
{
	public int save(GroupVo vo);
	
	public int setOrder(Map<String, Object> map);
	
	public int disableSubGroup(GroupVo vo);
	
	public int delete(String grp_id);
	public String deleteRtnValue(String grp_id);
	public int deleteOrder(String grp_id);
	
	public GroupVo select(String grp_id);
	public List<GroupVo> selectTree(String grp_id);
	public List<GroupVo> selectExcludeTree(String grp_id);
	public List<GroupVo> selectGroupPath(String grp_id);
}
