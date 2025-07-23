package egovframework.dnworks.cms.search.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.search.service.SearchService;

@Service("SearchService")
public class SearchServiceImpl extends EgovAbstractServiceImpl  implements  SearchService
{
	@Autowired
	protected SearchMapper mapper;

	@Override
	public Integer pageSelectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageSelectListCnt(map);
	}

	@Override
	public List<Map<String, Object>> pageSelectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.pageSelectList(map);
	}

	@Override
	public Integer boardSelectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.boardSelectListCnt(map);
	}

	@Override
	public List<Map<String, Object>> boardSelectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.boardSelectList(map);
	}

	@Override
	public List<Map<String, Object>> orgSelectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.orgSelectList(map);
	}
}
