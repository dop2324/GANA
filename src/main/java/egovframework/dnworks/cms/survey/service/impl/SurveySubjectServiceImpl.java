package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.survey.service.SurveySubjectService;
import egovframework.dnworks.cms.survey.service.SurveySubjectVo;


@Service("SurveySubjectService")
public class SurveySubjectServiceImpl extends EgovAbstractServiceImpl implements SurveySubjectService
{
	@Autowired
	private SurveySubjectMapper mapper;
	
	@Override
	public int save(SurveySubjectVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public List<SurveySubjectVo> selectList(int qus_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(qus_sn);
	}
}
