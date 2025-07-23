package egovframework.dnworks.cms.cmmncd.service;

import java.util.List;
import java.util.Map;

public interface CmmnCdService 
{
	public abstract int save(CmmnCdVo vo);
	public abstract int updateSttus(CmmnCdVo vo);
	public abstract int delete(int code_sn);
	public abstract int deleteOrder(int code_sn);
	public abstract int deleteRtnValue(int code_sn);
	public abstract int setOrder(Map<String, Object> map);
	
	public abstract List<CmmnCdVo> selectTree(int code_sn);
	public abstract List<CmmnCdVo> selectExcludeTree(int cd_sn);
	public abstract CmmnCdVo select(int code_sn);
	
	public abstract List<CmmnCdVo> selectValTree(String cd_val);
	public abstract List<CmmnCdVo> selectExcludeValTree(String cd_val);
	public abstract List<CmmnCdVo> selectValList(Map<String, Object> map);
	//public abstract CmmnCdVo selectValue(String cd_val);
}
