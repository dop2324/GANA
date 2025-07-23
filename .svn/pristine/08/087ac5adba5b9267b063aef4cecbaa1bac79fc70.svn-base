package egovframework.dnworks.cms.cmmncd.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.cmmncd.service.CmmnCdService;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdVo;

@Service("CmmnCdService")
public class CmmnCdServiceImpl extends EgovAbstractServiceImpl implements CmmnCdService
{
	@Autowired
	private CmmnCdMapper mapper;

	@Override
	public int save(CmmnCdVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int updateSttus(CmmnCdVo vo) {
		// TODO Auto-generated method stub
		return mapper.updateSttus(vo);
	}

	@Override
	public int delete(int cd_sn) {
		// TODO Auto-generated method stub
		int rtnVal = 0;
		
		List<CmmnCdVo> codeTree = mapper.selectExcludeTree(cd_sn);
		if(codeTree != null && codeTree.size() > 0) {
			return -2;
		}
		//순서조정
		if(mapper.deleteOrder(cd_sn) == -1) {
			return -3;
		}
		rtnVal = mapper.delete(cd_sn);	

		return rtnVal;
	}

	@Override
	public int deleteOrder(int cd_sn) {
		// TODO Auto-generated method stub
		return mapper.deleteOrder(cd_sn);
	}

	@Override
	public int deleteRtnValue(int cd_sn) {
		// TODO Auto-generated method stub
		return mapper.deleteRtnValue(cd_sn);
	}

	@Override
	public int setOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.setOrder(map);
	}

	@Override
	public List<CmmnCdVo> selectTree(int code_sn) {
		// TODO Auto-generated method stub
		return mapper.selectTree(code_sn);
	}

	@Override
	public List<CmmnCdVo> selectExcludeTree(int cd_sn) {
		// TODO Auto-generated method stub
		return mapper.selectExcludeTree(cd_sn);
	}

	@Override
	public CmmnCdVo select(int code_sn) {
		// TODO Auto-generated method stub
		return mapper.select(code_sn);
	}

	@Override
	public List<CmmnCdVo> selectValTree(String cd_val) {
		// TODO Auto-generated method stub
		return mapper.selectValTree(cd_val);
	}

	@Override
	public List<CmmnCdVo> selectExcludeValTree(String cd_val) {
		// TODO Auto-generated method stub
		return mapper.selectExcludeValTree(cd_val);
	}

	@Override
	public List<CmmnCdVo> selectValList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectValList(map);
	}
	
	/*
	@Override
	public CmmnCdVo selectValue(String cd_val) {
		// TODO Auto-generated method stub
		return mapper.selectValue(cd_val);
	}
	*/
}
