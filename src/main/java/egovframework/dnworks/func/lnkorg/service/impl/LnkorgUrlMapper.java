package egovframework.dnworks.func.lnkorg.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.func.lnkorg.service.LnkorgUrlVO;

@Mapper("LnkorgUrlMapper")
public interface LnkorgUrlMapper {

	void insert(Map<String, Object> param) throws Exception;

	List<LnkorgUrlVO> getList(Map<String, Object> param) throws Exception;

	void update(Map<String, Object> param) throws Exception;

	void remove(Map<String, Object> param) throws Exception;

}
