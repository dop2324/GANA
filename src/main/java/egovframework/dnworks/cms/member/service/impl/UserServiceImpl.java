package egovframework.dnworks.cms.member.service.impl;

import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.member.service.MemberVo;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;

@Service("UserService")
public class UserServiceImpl extends EgovAbstractServiceImpl  implements UserService
{
	@Autowired
	private  MemberMapper memberMapper;

	@Override
	public UserVo login(Map<String, Object> map) 
	{
		// TODO Auto-generated method stub
		UserVo userVo = null;
		
		MemberVo memberVo = memberMapper.login(map);
		
		if(memberVo != null) {
			userVo = new UserVo();
			userVo.setUsr_id(memberVo.getMem_id());
			userVo.setUsr_pw(memberVo.getMem_pw());
			userVo.setMem_salt(memberVo.getMem_salt());
			//userVo.setDpt_id(null);
			
			
			userVo.setGrp_id(memberVo.getGrp_id());
			userVo.setGrp_nm(memberVo.getGrp_nm());
			userVo.setUsr_nm(memberVo.getMem_nm());
			userVo.setUsr_mail(memberVo.getMem_mail());
			userVo.setUsr_telno(memberVo.getMem_telno());
			userVo.setUsr_moblphone(memberVo.getMem_moblphone());
			userVo.setUsr_param(memberVo.getMem_param());
			userVo.setLogin_cnt(memberVo.getMem_joinCnt());
			
			userVo.setUsr_sttus(memberVo.getMem_sttus());
			
			userVo.setMem_chgPwDtDiff(memberVo.getMem_chgPwDtDiff());
		}
		
		return userVo;
	}

	@Override
	public UserVo loginSuMember(String usr_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
