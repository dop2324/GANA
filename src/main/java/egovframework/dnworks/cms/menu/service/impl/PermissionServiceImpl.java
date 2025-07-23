package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.menu.service.PermissionService;
import egovframework.dnworks.cms.menu.service.PermissionVo;

@Service("PermissionService")
public class PermissionServiceImpl extends EgovAbstractServiceImpl  implements  PermissionService{
	
	@Autowired
    private PermissionMapper mapper;

	@Override
	public int save(PermissionVo vo, int lowApply) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		
		if(rtnVal != -1) {
			if(lowApply == 1) {
				mapper.saveSubPermission(vo);
			}
		}
		return rtnVal;
	}

	@Override
	public void saveSubPermission(PermissionVo vo) {
		// TODO Auto-generated method stub
		mapper.saveSubPermission(vo);
	}

	/*
	@Override
	public int update(PermissionVo permission) {
		// TODO Auto-generated method stub
		return mapper.update(permission);
	}

	@Override
	public void updateSubPermission(PermissionVo permission) {
		// TODO Auto-generated method stub
		mapper.updateSubPermission(permission);
	}
	*/

	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.delete(map);
		if(rtnVal > 0) {
			mapper.deleteSubPermission(map);
		}
		return rtnVal;
	}

	@Override
	public int deleteSubPermission(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.deleteSubPermission(map);
	}

	@Override
	public int deletePrmId(String prm_id) {
		// TODO Auto-generated method stub
		return mapper.deletePrmId(prm_id);
	}

	@Override
	public List<PermissionVo> selectList(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectList(mnu_code);
	}

	@Override
	public Integer getPermission(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.getPermission(params);
	} 

}
