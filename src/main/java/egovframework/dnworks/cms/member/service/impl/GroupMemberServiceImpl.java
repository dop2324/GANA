package egovframework.dnworks.cms.member.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.member.service.GroupMemberService;
import egovframework.dnworks.cms.member.service.GroupMemberVo;

@Service("GroupMemberService")
public class GroupMemberServiceImpl extends EgovAbstractServiceImpl  implements GroupMemberService
{
	@Autowired
	private GroupMemberMapper mapper;
	
	@Override
	public int save(GroupMemberVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}
	
	@Override
	public int insert(GroupMemberVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(GroupMemberVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int deleteUser(String mem_id) {
		// TODO Auto-generated method stub
		return mapper.deleteUser(mem_id);
	}

	@Override
	public int deleteGroup(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.deleteGroup(grp_id);
	}

	@Override
	public int selectExists(GroupMemberVo vo) {
		// TODO Auto-generated method stub
		return mapper.selectExists(vo);
	}

	@Override
	public String selectUser(String mem_id) {
		// TODO Auto-generated method stub
		return mapper.selectUser(mem_id);
	}

	@Override
	public List<GroupMemberVo> selectGroupList(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.selectGroupList(grp_id);
	}
}
