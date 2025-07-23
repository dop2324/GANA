package egovframework.dnworks.cms.member.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.member.service.GroupMemberVo;
import egovframework.dnworks.cms.member.service.GroupService;
import egovframework.dnworks.cms.member.service.GroupVo;
import egovframework.dnworks.cms.menu.service.impl.PermissionMapper;

@Service("GroupService")
public class GroupServiceImpl extends EgovAbstractServiceImpl  implements GroupService
{
	@Autowired
	private GroupMapper mapper;
	
	@Autowired
	private GroupMemberMapper groupMemberMapper;
	
	@Autowired
    private PermissionMapper permissionMapper;

	@Override
	public int save(GroupVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int setOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.setOrder(map);
	}

	@Override
	public int disableSubGroup(GroupVo vo) {
		// TODO Auto-generated method stub
		return mapper.disableSubGroup(vo);
	}

	@Override
	public int delete(String grp_id) {
		// TODO Auto-generated method stub
		
		int rtnVal = 0;
		
		//1 하위그룹 확인
		GroupVo vo = mapper.select(grp_id);
		if(vo.getGrp_childCnt() > 0) return -2;
		
		//2 그룹에 회원 확인
		List<GroupMemberVo> selectGroupList = groupMemberMapper.selectGroupList(grp_id);
		if(selectGroupList != null && selectGroupList.size() > 0) return -3;
		
		//3 해당 그룹의 권한 삭제
		permissionMapper.deletePrmId(grp_id);
		
		//4 정렬 처리
		mapper.deleteOrder(grp_id);
		
		//5 삭제 처리
		rtnVal = mapper.delete(grp_id);
		
		return rtnVal;
	}

	@Override
	public String deleteRtnValue(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.deleteRtnValue(grp_id);
	}

	@Override
	public int deleteOrder(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.deleteOrder(grp_id);
	}

	@Override
	public GroupVo select(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.select(grp_id);
	}

	@Override
	public List<GroupVo> selectTree(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.selectTree(grp_id);
	}

	@Override
	public List<GroupVo> selectExcludeTree(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.selectExcludeTree(grp_id);
	}

	@Override
	public List<GroupVo> selectGroupPath(String grp_id) {
		// TODO Auto-generated method stub
		return mapper.selectGroupPath(grp_id);
	}
}
