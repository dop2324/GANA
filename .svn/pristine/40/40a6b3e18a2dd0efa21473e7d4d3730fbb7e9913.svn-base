package egovframework.dnworks.cms.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import egovframework.dnworks.cms.blacklist.service.BlackListIPVo;

@Mapper("BlackListIPMapper")
public interface BlackListIPMapper 
{
	public int save(BlackListIPVo vo);
	
	public int delete(int bli_sn);
	
	public BlackListIPVo select(int bli_sn);
	public List<BlackListIPVo> selectList(String site_code);
	
	public int isBlackListIP(Map<String, Object> map);
	
	public List<BlackListIPVo> selectIPList(Map<String, Object> map);
}
