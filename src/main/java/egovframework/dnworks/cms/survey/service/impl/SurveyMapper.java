package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.survey.service.SurveyVo;

@Mapper("SurveyMapper")
public interface SurveyMapper 
{
	public int save(SurveyVo vo);
	public int delete(int survey_sn);
	
	public SurveyVo select(int survey_sn);
	
	public int selectListCnt(Map<String, Object> map);
	public List<SurveyVo> selectList(Map<String, Object> map);
}
