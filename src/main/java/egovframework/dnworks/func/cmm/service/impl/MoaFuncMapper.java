package egovframework.dnworks.func.cmm.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import egovframework.dnworks.cms.member.service.GroupVo;
import egovframework.dnworks.func.memb.service.MembAgentVO;

@Mapper("MoaFuncMapper")
public interface MoaFuncMapper {

	String getcodeNm(EgovMap map) throws Exception;

	List<Map<String, String>> getCodeList(EgovMap map) throws Exception;

	GroupVo getGroupInfo(GroupVo result) throws Exception;

	List<MembAgentVO> getUseOrgList() throws Exception;

	String getUseOrgNm(MembAgentVO searchVO) throws Exception;

}
