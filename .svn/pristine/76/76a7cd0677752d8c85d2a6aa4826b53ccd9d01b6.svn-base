package egovframework.dnworks.func.cmm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.member.service.GroupVo;
import egovframework.dnworks.func.cmm.service.MoaFuncService;
import egovframework.dnworks.func.memb.service.MembAgentVO;

@Service("MoaFuncService")
public class MoaFuncServiceImpl extends EgovAbstractServiceImpl implements MoaFuncService {

	@Autowired
	MoaFuncMapper mapper;

	@Override
	public String getCodeNm(EgovMap map) throws Exception {
		return mapper.getcodeNm(map);
	}

	@Override
	public List<Map<String, String>> getCodeList(EgovMap map) throws Exception {
		return mapper.getCodeList(map);
	}

	@Override
	public GroupVo getGroupInfo(GroupVo result) throws Exception {
		return mapper.getGroupInfo(result);
	}

	@Override
	public List<MembAgentVO> getUseOrgList() throws Exception {
		return mapper.getUseOrgList();
	}

	@Override
	public String getUseOrgNm(MembAgentVO searchVO) throws Exception {
		return mapper.getUseOrgNm(searchVO);
	}
}
