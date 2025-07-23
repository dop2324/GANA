package egovframework.dnworks.cms.survey.service;

import java.util.List;

public interface SurveySubjectService 
{
	public abstract int save(SurveySubjectVo vo);
	public abstract List<SurveySubjectVo> selectList(int qus_sn);
}
