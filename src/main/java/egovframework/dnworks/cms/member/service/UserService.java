package egovframework.dnworks.cms.member.service;

import java.util.Map;

public interface UserService 
{
	public abstract UserVo login(Map<String, Object> map);
	
	public abstract UserVo loginSuMember( String usr_id );
}
