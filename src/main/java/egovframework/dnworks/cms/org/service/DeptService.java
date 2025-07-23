package egovframework.dnworks.cms.org.service;

import java.util.List;
import java.util.Map;

public interface DeptService 
{
	public abstract int save(DeptVo vo);
	public abstract int delete(String dept_id);
	public abstract int truncate();
	public abstract int selectChild(String dept_id);
	public abstract DeptVo select(String dept_id);
	public abstract List<DeptVo> selectTree(String dept_id);
	public abstract List<DeptVo> selectList(Map<String, Object> map);
}
