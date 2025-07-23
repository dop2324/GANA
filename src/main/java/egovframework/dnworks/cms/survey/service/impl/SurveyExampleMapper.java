package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.survey.service.SurveyExampleVo;

@Mapper("SurveyExampleMapper")
public interface SurveyExampleMapper 
{
	public int save(SurveyExampleVo vo);
	public int delete(int exam_sn);
	
	public SurveyExampleVo select(int exam_sn);
	public List<SurveyExampleVo> selectList(int qus_sn);
}
