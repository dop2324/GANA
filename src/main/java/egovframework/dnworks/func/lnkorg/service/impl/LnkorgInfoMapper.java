package egovframework.dnworks.func.lnkorg.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.dnworks.func.lnkorg.service.LnkorgInfoVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("LnkorgInfoMapper")
public interface LnkorgInfoMapper {
	
    LnkorgInfoVO select(String orgUnqId);
    
    List<LnkorgInfoVO> selectList(Map<String, Object> param);
    
    void insert(LnkorgInfoVO vo);
    
    void update(LnkorgInfoVO vo);
    
	void delete(LnkorgInfoVO vo);
	
	int selectListCnt(Map<String, Object> param);
}
