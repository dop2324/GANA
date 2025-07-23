package egovframework.dnworks.cms.cmmncd.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.cmmncd.service.CmmnCdVo;

@Mapper("CmmnCdMapper")
public interface CmmnCdMapper 
{
	public int save(CmmnCdVo vo);
	public int updateSttus(CmmnCdVo vo);
	public int delete(int code_uid);
	public int deleteOrder(int cd_sn);
	public int deleteRtnValue(int cd_sn);
	public int setOrder(Map<String, Object> map);
	
	public List<CmmnCdVo> selectTree(int code_sn);
	public List<CmmnCdVo> selectExcludeTree(int cd_sn);
	public CmmnCdVo select(int code_sn);
	
	public List<CmmnCdVo> selectValTree(String cd_val);
	public List<CmmnCdVo> selectExcludeValTree(String cd_val);
	public List<CmmnCdVo> selectValList(Map<String, Object> map);
	//public CmmnCdVo selectValue(String cd_val);
}
