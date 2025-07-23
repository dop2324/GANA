package egovframework.dnworks.cms.menu.service;

import java.util.List;
import java.util.Map;

public interface SiteService 
{
	public abstract int save(SiteVo vo, int cmd);

	public abstract int delete(String site_code);
	
	//deleteSiteCheck
	public abstract int deleteUseYn(String site_code);
	
	public abstract int deleteSiteUseYn(String site_code);
	
	public abstract int existNm(String site_nm);
	public abstract int existDir(String site_directory);
	public abstract int existMngYn();
	
	public abstract SiteVo selectDir(String site_directory);
	public abstract SiteVo select(Map<String, Object> map);
	
	
	//selectAllUserSiteByAuth
	public abstract List<SiteVo> selectPrmList(Map<String, Object> map);
	public abstract List<SiteVo> selectList(Map<String, Object> map);
}
