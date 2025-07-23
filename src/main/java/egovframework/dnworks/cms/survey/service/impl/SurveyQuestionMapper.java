package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;

@Mapper("SurveyQuestionMapper")
public interface SurveyQuestionMapper 
{
	public int nextVal();
	public int save(SurveyQuestionVo vo);
	public int delete(int qus_sn);
	public SurveyQuestionVo select(int qus_sn);

	public List<SurveyQuestionVo> selectList(int survey_sn);
}
