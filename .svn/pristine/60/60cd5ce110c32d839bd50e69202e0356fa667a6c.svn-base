package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.PermissionVo;

@Mapper("PermissionMapper")
public interface PermissionMapper 
{
	public int save(PermissionVo permission);
	public void saveSubPermission(PermissionVo permission);
	
	//public int update(PermissionVo permission);
	//public void updateSubPermission(PermissionVo permission);
	
	
	public int delete(Map<String, Object> map);
	public int deleteSubPermission(Map<String, Object> map);
	public int deletePrmId(String prm_id);
	
	public List<PermissionVo> selectList(String mnu_code);
	
	public Integer getPermission(Map<String, Object> params);
}
