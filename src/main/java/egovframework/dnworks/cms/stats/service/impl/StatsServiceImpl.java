package egovframework.dnworks.cms.stats.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.stats.service.StatsService;
import egovframework.dnworks.cms.stats.service.StatsVo;

@Service("StatsService")
public class StatsServiceImpl extends EgovAbstractServiceImpl implements StatsService
{
	@Autowired
	private StatsMapper mapper;

	@Override
	public List<StatsVo> selectSite(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectSite(map);
	}

	@Override
	public List<StatsVo> selectYear(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectYear(map);
	}

	@Override
	public List<StatsVo> selectMonth(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectMonth(map);
	}

	@Override
	public List<StatsVo> selectDay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectDay(map);
	}

	@Override
	public List<StatsVo> selectTime(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectTime(map);
	}

	@Override
	public List<StatsVo> selectMenu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectMenu(map);
	}	
}
