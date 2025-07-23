package egovframework.dnworks.cms.accessip.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.accessip.service.AccessIPVo;

@Mapper("AccessIPMapper")
public interface AccessIPMapper 
{
	public int insert(AccessIPVo vo);
	
	public int update(AccessIPVo vo);
	
	public int delete(int ip_sn);
	
	public AccessIPVo select(int ip_sn);
	
	public List<AccessIPVo> selectList(String site_code);
	
	public int isAccessIP(Map<String, Object> map);
}
