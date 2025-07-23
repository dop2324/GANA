package egovframework.dnworks.cms.member.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.member.service.GroupMemberVo;

@Mapper("GroupMemberMapper")
public interface GroupMemberMapper
{
	public int save(GroupMemberVo vo);
	
	public int insert(GroupMemberVo vo);
	
	public int update(GroupMemberVo vo);
	
	public int deleteUser(String mem_id);
	
	public int deleteGroup(String grp_id);
	
	public int selectExists(GroupMemberVo vo);
	
	public String selectUser(String mem_id);
	
	public List<GroupMemberVo> selectGroupList(String grp_id);
}
