package egovframework.dnworks.cms.org.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import egovframework.dnworks.cms.org.service.DeptVo;

@Mapper("DeptMapper")
public interface DeptMapper 
{
	public int save(DeptVo vo);
	public int delete(String dept_id);
	public int truncate();
	public int selectChild(String dept_id);
	public DeptVo select(String dept_id);
	public List<DeptVo> selectTree(String dept_id);
	
	public List<DeptVo> selectList(Map<String, Object> map);
}
