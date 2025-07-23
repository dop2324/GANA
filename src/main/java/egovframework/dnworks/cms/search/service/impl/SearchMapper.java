package egovframework.dnworks.cms.search.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SearchMapper")
public interface SearchMapper {
	
	public Integer pageSelectListCnt(Map<String, Object> map);
	public List<Map<String, Object>> pageSelectList(Map<String, Object> map);
	
	public Integer boardSelectListCnt(Map<String, Object> map);
	public List<Map<String, Object>> boardSelectList(Map<String, Object> map);
	
	public List<Map<String, Object>> orgSelectList(Map<String, Object> map);
}
