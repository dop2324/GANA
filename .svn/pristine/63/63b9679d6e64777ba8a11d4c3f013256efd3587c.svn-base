package egovframework.dnworks.func.emp.mnthleave.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveDtVo;
import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveVo;

@Mapper("MnthLeaveMapper")
public interface MnthLeaveMapper 
{
	public int save(MnthLeaveVo vo);
	public int delete(int mnth_sn);
	public MnthLeaveVo select(int mnth_sn);
	
	public int chckDup(Map<String, Object> map);
	public List<MnthLeaveVo> selectList(Map<String, Object> map);
	
	public List<Map<String, Object>> selectEmpList(Map<String, Object> map);
	
	public int saveDate(Map<String, Object> map);
	
	public List<MnthLeaveDtVo> selectDtList(int mnth_sn);
	public int deleteDt(int mnth_sn);
}
