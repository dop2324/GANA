package egovframework.dnworks.func.emp.mnthleave.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveService;
import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveVo;

@Service("MnthLeaveService")
public class MnthLeaveServiceImpl extends EgovAbstractServiceImpl implements MnthLeaveService 
{
	@Autowired
	private MnthLeaveMapper mapper;

	@Override
	public int save(MnthLeaveVo vo) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		
		if(rtnVal != -1) {
			if(vo.getMnth_sn() == -1) {
				vo.setMnth_sn(vo.getKey_sn());
			}else {
				mapper.deleteDt(vo.getMnth_sn());
			}
			
			if(vo.getDateList() != null && vo.getDateList().size() > 0) {
				for(LocalDate d:vo.getDateList()) {
					//LocalDate dt = d;
					
					Map<String, Object> map = new HashMap<>();
					map.put("mnth_sn", vo.getMnth_sn());
					map.put("mnth_dt", d.toString());
					
					mapper.saveDate(map);
				}
				
			}
		}
		return rtnVal;
	}

	@Override
	public int delete(int mnth_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(mnth_sn);
	}

	@Override
	public MnthLeaveVo select(int mnth_sn) {
		// TODO Auto-generated method stub
		MnthLeaveVo vo = mapper.select(mnth_sn);
		
		if(vo != null) {
			vo.setMnthDtList(mapper.selectDtList(vo.getMnth_sn()));
		}
		
		return vo;
	}
	
	@Override
	public int chckDup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.chckDup(map);
	}

	@Override
	public List<MnthLeaveVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<MnthLeaveVo> list = mapper.selectList(map);
		if(list != null && list.size() > 0) {
			for(MnthLeaveVo vo:list) {
				vo.setMnthDtList(mapper.selectDtList(vo.getMnth_sn()));
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> selectEmpList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectEmpList(map);
	}

	@Override
	public int saveDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.saveDate(map);
	}
}
