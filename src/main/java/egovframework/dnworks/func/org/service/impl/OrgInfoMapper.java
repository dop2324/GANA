package egovframework.dnworks.func.org.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.dnworks.func.org.service.OrgInfoVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgInfoMapper")
public interface OrgInfoMapper {

    OrgInfoVO select(String orgUnqId);

    List<OrgInfoVO> selectList(Map<String, Object> param);

    void insert(OrgInfoVO vo);

    void update(OrgInfoVO vo);

    void delete(OrgInfoVO vo);

	int selectListCnt(Map<String, Object> param);
	
	List<OrgInfoVO> selectListWithApiKeys();
} 
