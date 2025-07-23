package egovframework.dnworks.cms.blacklist.service;

import java.util.List;
import java.util.Map;

public interface BlackListIPService 
{
	public abstract int save(BlackListIPVo vo);
	
	public abstract int delete(int bli_sn);
	
	public abstract BlackListIPVo select(int bli_sn);
	public abstract List<BlackListIPVo> selectList(String site_code);
	
	public abstract int isBlackListIP(Map<String, Object> map);
	
	public abstract List<BlackListIPVo> selectIPList(Map<String, Object> map);
}
