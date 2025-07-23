package egovframework.dnworks.cms.org.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.org.service.DeptService;
import egovframework.dnworks.cms.org.service.DeptVo;
import egovframework.dnworks.cms.org.service.EmpVo;

@Service("DeptService")
public class DeptServiceImpl extends EgovAbstractServiceImpl  implements DeptService
{
	@Autowired
	private DeptMapper mapper;
	
	@Autowired
	private EmpMapper empMapper;

	@Override
	public int save(DeptVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(String dept_id) {
		// TODO Auto-generated method stub
		int rtnVal = 0;
		
		//1 하위 부서 확인
		int childCnt = mapper.selectChild(dept_id);
		if(childCnt > 0) {
			return -2;
		}
		
		//직원 존재 확인
		Map<String, Object> map = new HashMap<>();
		map.put("dept_id", dept_id);
		List<EmpVo> empList = empMapper.selectList(map);
		if(empList != null && empList.size() > 0) {
			return -3;
		}
		
		rtnVal = mapper.delete(dept_id);
		
		return rtnVal;
	}

	@Override
	public int truncate() {
		// TODO Auto-generated method stub
		return mapper.truncate();
	}

	@Override
	public int selectChild(String dept_id) {
		// TODO Auto-generated method stub
		return mapper.selectChild(dept_id);
	}

	@Override
	public DeptVo select(String dept_id) {
		// TODO Auto-generated method stub
		return mapper.select(dept_id);
	}

	@Override
	public List<DeptVo> selectTree(String dept_id) {
		// TODO Auto-generated method stub
		return mapper.selectTree(dept_id);
	}

	@Override
	public List<DeptVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
