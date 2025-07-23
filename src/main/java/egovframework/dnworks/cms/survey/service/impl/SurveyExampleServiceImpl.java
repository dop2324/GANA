package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.survey.service.SurveyExampleService;
import egovframework.dnworks.cms.survey.service.SurveyExampleVo;

@Service("SurveyExampleService")
public class SurveyExampleServiceImpl extends EgovAbstractServiceImpl implements SurveyExampleService
{
	@Autowired
	private SurveyExampleMapper mapper;

	@Override
	public int save(SurveyExampleVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int exam_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(exam_sn);
	}
	
	@Override
	public SurveyExampleVo select(int exam_sn) {
		// TODO Auto-generated method stub
		return mapper.select(exam_sn);
	}

	@Override
	public List<SurveyExampleVo> selectList(int qus_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(qus_sn);
	}
}
