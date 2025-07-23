package egovframework.dnworks.cms.member.service;

import java.util.List;
import java.util.Map;

public interface MemberService 
{
	public abstract int save(MemberVo vo, int cmd);
	public abstract int updatePassword(MemberVo vo);
	
	public abstract void updateJoinCount(String mem_id);
	public abstract void updateFailCount(String mem_id);
	public abstract int initLoginFailCnt(String mem_id);
	public abstract void updateDisable(String mem_id);
	
	public abstract int delete(String mem_id);
	
	public abstract MemberVo select(String mem_id);
	public abstract MemberVo selectMap(Map<String, Object> map);
	
	public abstract int selectListCnt(Map<String, Object> map);
	public abstract List<MemberVo> selectList(Map<String, Object> map);
	
	public abstract int existID(String mem_id);
	public abstract int existMail(String mem_mail);
	public abstract int existMobl(String mem_moblphone);
}
