package egovframework.dnworks.cms.survey.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.survey.service.SurveyResultService;
import egovframework.dnworks.cms.survey.service.SurveyResultVo;


@Service("SurveyResultService")
public class SurveyResultServiceImpl extends EgovAbstractServiceImpl implements SurveyResultService
{
	@Autowired
	private SurveyResultMapper mapper;

	@Override
	public int save(SurveyResultVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}
}
