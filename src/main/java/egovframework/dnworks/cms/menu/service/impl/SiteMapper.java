package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.SiteVo;

@Mapper("SiteMapper")
public interface SiteMapper 
{
	public int save(SiteVo vo);
	public int delete(String site_code);
	
	//deleteSiteCheck
	public int deleteUseYn(String site_code);
	
	public int deleteSiteUseYn(String site_code);
	
	public int existNm(String site_nm);
	public int existDir(String site_directory);
	public int existMngYn();
	
	public SiteVo selectDir(String site_directory);
	public SiteVo select(Map<String, Object> map);
	
	
	//selectAllUserSiteByAuth
	public List<SiteVo> selectPrmList(Map<String, Object> map);
	public List<SiteVo> selectList(Map<String, Object> map);
	
	public int memberDisable(String site_code);
}
