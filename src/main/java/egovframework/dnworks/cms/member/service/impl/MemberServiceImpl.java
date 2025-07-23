package egovframework.dnworks.cms.member.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;
import egovframework.dnworks.cms.menu.service.impl.PermissionMapper;

@Service("MemberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl  implements MemberService
{
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private GroupMemberMapper groupMemberMapper;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	private MemberVo encrypt(MemberVo vo)
	{
		if(!Util.nvl(vo.getMem_mail()).equals("")) 		vo.setMem_mail(			AESUtil.encrypt(vo.getMem_mail())		);
		if(!Util.nvl(vo.getMem_birth()).equals("")) 	vo.setMem_birth(		AESUtil.encrypt(vo.getMem_birth())		);
		if(!Util.nvl(vo.getMem_telno()).equals("")) 	vo.setMem_telno(		AESUtil.encrypt(vo.getMem_telno())		);
		if(!Util.nvl(vo.getMem_moblphone()).equals("")) vo.setMem_moblphone(	AESUtil.encrypt(vo.getMem_moblphone())	);
		if(!Util.nvl(vo.getMem_zip()).equals("")) 		vo.setMem_zip(			AESUtil.encrypt(vo.getMem_zip())		);
		if(!Util.nvl(vo.getMem_addr()).equals("")) 		vo.setMem_addr(			AESUtil.encrypt(vo.getMem_addr())		);
		if(!Util.nvl(vo.getMem_addrDtl()).equals("")) 	vo.setMem_addrDtl(		AESUtil.encrypt(vo.getMem_addrDtl())	);
		
		return vo;
	}
	
	private MemberVo decrypt(MemberVo vo)
	{
		if(!Util.nvl(vo.getMem_mail()).equals("")) 		vo.setMem_mail(			AESUtil.decrypt(vo.getMem_mail())		);
		if(!Util.nvl(vo.getMem_birth()).equals("")) 	vo.setMem_birth(		AESUtil.decrypt(vo.getMem_birth())		);
		if(!Util.nvl(vo.getMem_telno()).equals("")) 	vo.setMem_telno(		AESUtil.decrypt(vo.getMem_telno())		);
		if(!Util.nvl(vo.getMem_moblphone()).equals("")) vo.setMem_moblphone(	AESUtil.decrypt(vo.getMem_moblphone())	);
		if(!Util.nvl(vo.getMem_zip()).equals("")) 		vo.setMem_zip(			AESUtil.decrypt(vo.getMem_zip())		);
		if(!Util.nvl(vo.getMem_addr()).equals("")) 		vo.setMem_addr(			AESUtil.decrypt(vo.getMem_addr())		);
		if(!Util.nvl(vo.getMem_addrDtl()).equals("")) 	vo.setMem_addrDtl(		AESUtil.decrypt(vo.getMem_addrDtl())	);
		
		return vo;
	}
	
	@Override
	public int save(MemberVo vo, int cmd) {
		// TODO Auto-generated method stub
		int rtnVal = -1;
		vo = this.encrypt(vo);
		
		if(cmd == Code.Prm.PrmIns.getCode()) {
			if(this.existID(vo.getMem_id()) > 0) {
				return -2;
			}
			if(!Util.nvl(vo.getMem_mail()).equals("") && this.existMail(vo.getMem_mail()) > 0) {
				return -3;
			}
			if(!Util.nvl(vo.getMem_moblphone()).equals("") && this.existMobl(vo.getMem_moblphone()) > 0) {
				return -4;
			}
		}
		
		rtnVal  = mapper.save(vo);
		
		if(rtnVal != -1) {
			if(vo.getGroupMemberVo() != null) {
				groupMemberMapper.save(vo.getGroupMemberVo());
			}
		}
		
		return rtnVal;
	}
	
	@Override
	public int updatePassword(MemberVo vo) {
		// TODO Auto-generated method stub
		MemberVo memVo = mapper.select(vo.getMem_id());
		vo.setMem_pw(SHA256Util.getSHA256(vo.getMem_pw(), memVo.getMem_salt()));
		return mapper.updatePassword(vo);
	}
	
	@Override
	public void updateJoinCount(String mem_id) {
		// TODO Auto-generated method stub
		mapper.updateJoinCount(mem_id);
	}

	@Override
	public void updateFailCount(String mem_id) {
		// TODO Auto-generated method stub
		mapper.updateFailCount(mem_id);
	}

	@Override
	public int initLoginFailCnt(String mem_id) {
		// TODO Auto-generated method stub
		return mapper.initLoginFailCnt(mem_id);
	}
	
	@Override
	public void updateDisable(String mem_id) {
		// TODO Auto-generated method stub
		mapper.updateDisable(mem_id);
	}

	@Override
	public int delete(String mem_id) {
		// TODO Auto-generated method stub
		int rtnVal = 0;
		//권한 삭제
		permissionMapper.deletePrmId(mem_id);
		
		//회원그룹 삭제
		groupMemberMapper.deleteUser(mem_id);
		
		rtnVal = mapper.delete(mem_id);
		return rtnVal;
	}

	@Override
	public MemberVo select(String mem_id) {
		// TODO Auto-generated method stub
		MemberVo vo = mapper.select(mem_id);
		if(vo != null) vo = this.decrypt(vo);
		return vo;
	}
	@Override
	public MemberVo selectMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		MemberVo vo = mapper.selectMap(map);
		if(vo != null) vo = this.decrypt(vo);
		return vo;
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<MemberVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<MemberVo> returnList = null;
		List<MemberVo> list = mapper.selectList(map);
		if(list != null && list.size() > 0) {
			returnList = new ArrayList<>();
			for(MemberVo v:list) {
				v = this.decrypt(v);
				returnList.add(v);
			}
		}
		return returnList;
	}

	@Override
	public int existID(String mem_id) {
		// TODO Auto-generated method stub
		return mapper.existID(mem_id);
	}

	@Override
	public int existMail(String mem_mail) {
		// TODO Auto-generated method stub
		return mapper.existMail(mem_mail);
	}

	@Override
	public int existMobl(String mem_moblphone) {
		// TODO Auto-generated method stub
		return mapper.existMobl(mem_moblphone);
	}
}
