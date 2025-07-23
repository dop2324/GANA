package egovframework.dnworks.cms.member.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.member.service.MemberVo;

@Mapper("MemberMapper")
public interface MemberMapper 
{
	public int save(MemberVo vo);
	
	public int updatePassword(MemberVo vo);
	
	public void updateJoinCount(String mem_id);
	public void updateFailCount(String mem_id);
	public int initLoginFailCnt(String mem_id);
	
	public void updateDisable(String mem_id);
	
	public int delete(String mem_id);
	
	public MemberVo select(String mem_id);
	public MemberVo selectMap(Map<String, Object> map);
	
	public int selectListCnt(Map<String, Object> map);
	public List<MemberVo> selectList(Map<String, Object> map);
	
	public MemberVo login(Map<String, Object> map);
	
	public int existID(String mem_id);
	
	public int existMail(String mem_mail);
	public int existMobl(String mem_moblphone);
}
