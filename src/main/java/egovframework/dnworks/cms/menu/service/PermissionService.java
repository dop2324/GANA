package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface PermissionService 
{
	public abstract int save(PermissionVo vo, int lowApply);
	public abstract void saveSubPermission(PermissionVo vo);
	
	//public abstract int update(PermissionVo permission);
	//public abstract void updateSubPermission(PermissionVo permission);
	
	
	public abstract int delete(Map<String, Object> map);
	public abstract int deleteSubPermission(Map<String, Object> map);
	public abstract int deletePrmId(String prm_id);
	
	public abstract List<PermissionVo> selectList(String mnu_code);
	
	public abstract Integer getPermission(Map<String, Object> params);
}
