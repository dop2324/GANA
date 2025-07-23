package egovframework.dnworks.cms.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.org.service.EmpService;
import egovframework.dnworks.cms.org.service.EmpVo;

@Service("EmpService")
public class EmpServiceImpl extends EgovAbstractServiceImpl  implements EmpService
{
	@Autowired
	private EmpMapper mapper;
	
	private EmpVo encrypt(EmpVo vo)
	{
		if(!Util.nvl(vo.getEmp_eml()).equals("")) 		vo.setEmp_eml(			AESUtil.encrypt(vo.getEmp_eml())		);
		if(!Util.nvl(vo.getEmp_moblphon()).equals("")) 	vo.setEmp_moblphon(		AESUtil.encrypt(vo.getEmp_moblphon())	);
		return vo;
	}
	
	private EmpVo decrypt(EmpVo vo)
	{
		if(!Util.nvl(vo.getEmp_eml()).equals("")) 		vo.setEmp_eml(			AESUtil.decrypt(vo.getEmp_eml())		);
		if(!Util.nvl(vo.getEmp_moblphon()).equals("")) 	vo.setEmp_moblphon(		AESUtil.decrypt(vo.getEmp_moblphon())	);
		return vo;
	}
	
	@Override
	public int save(EmpVo vo) {
		// TODO Auto-generated method stub
		vo = this.encrypt(vo);
		
		return mapper.save(vo);
	}

	@Override
	public int delete(String emp_id) {
		// TODO Auto-generated method stub
		return mapper.delete(emp_id);
	}

	@Override
	public int truncate() {
		// TODO Auto-generated method stub
		return mapper.truncate();
	}

	@Override
	public EmpVo select(String emp_id) {
		// TODO Auto-generated method stub
		EmpVo vo = mapper.select(emp_id);
		if(vo != null) vo = this.decrypt(vo);
		return vo; 
	}

	@Override
	public List<EmpVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<EmpVo> returnList = null;
		List<EmpVo> list = mapper.selectList(map);
		if(list != null && list.size() > 0) {
			returnList = new ArrayList<>();
			for(EmpVo v:list) {
				v = this.decrypt(v);
				returnList.add(v);
			}
		}
		return returnList;
	}
}
