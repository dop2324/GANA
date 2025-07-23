package egovframework.dnworks.cms.blacklist.service;

import java.util.List;
import java.util.Map;

public interface BlackListUserService 
{
	public abstract int insert(BlackListUserVo vo);
	
	public abstract int update(BlackListUserVo vo);
	
	public abstract int delete(int blu_sn);
	
	public abstract BlackListUserVo select(int blu_sn);
	public abstract List<BlackListUserVo> selectList(String site_code);
	
	public abstract int isBlackListUser(Map<String, Object> map);
	public abstract List<BlackListUserVo> selectUserList(Map<String, Object> map);
}
