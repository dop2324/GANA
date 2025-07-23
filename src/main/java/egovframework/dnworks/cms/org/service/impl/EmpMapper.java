package egovframework.dnworks.cms.org.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.org.service.EmpVo;

@Mapper("EmpMapper")
public interface EmpMapper 
{
	public int save(EmpVo vo);
	public int delete(String emp_id);
	public int truncate();
	public EmpVo select(String emp_id);
	public List<EmpVo> selectList(Map<String, Object> map);
}
