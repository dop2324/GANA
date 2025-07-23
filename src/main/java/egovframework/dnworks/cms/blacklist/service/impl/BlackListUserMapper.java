package egovframework.dnworks.cms.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import egovframework.dnworks.cms.blacklist.service.BlackListUserVo;

@Mapper("BlackListUserMapper")
public interface BlackListUserMapper 
{
	public int insert(BlackListUserVo vo);
	
	public int update(BlackListUserVo vo);
	
	public int delete(int blu_sn);
	
	public BlackListUserVo select(int blu_sn);
	public List<BlackListUserVo> selectList(String site_code);
	
	public int isBlackListUser(Map<String, Object> map);
	public List<BlackListUserVo> selectUserList(Map<String, Object> map);
}
