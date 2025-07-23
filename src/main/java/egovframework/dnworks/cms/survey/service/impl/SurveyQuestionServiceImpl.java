package egovframework.dnworks.cms.survey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.survey.service.SurveyExampleService;
import egovframework.dnworks.cms.survey.service.SurveyExampleVo;
import egovframework.dnworks.cms.survey.service.SurveyQuestionService;
import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;
import egovframework.dnworks.cms.survey.service.SurveySubjectService;

@Service("PollQuestionService")
public class SurveyQuestionServiceImpl extends EgovAbstractServiceImpl implements SurveyQuestionService
{
	@Autowired
	private SurveyQuestionMapper mapper;
	
	@Autowired
	private SurveyExampleService surveyExampleService;
	
	@Autowired
	private SurveySubjectService surveySubjectService;

	@Override
	public int nextVal() {
		// TODO Auto-generated method stub
		return mapper.nextVal();
	}

	@Override
	public int save(SurveyQuestionVo vo) {
		// TODO Auto-generated method stub
		int rtnVal = -1;
		rtnVal = mapper.save(vo);
		if(rtnVal != -1) {
			if(vo.getQus_sn() == -1) vo.setQus_sn(vo.getKey_sn());

			if(vo.getSurveyExample() != null) {
				for(SurveyExampleVo v:vo.getSurveyExample()) {
					v.setQus_sn(vo.getQus_sn());
					surveyExampleService.save(v);
				}
			}
		}
		return rtnVal;
	}

	@Override
	public int delete(int qus_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(qus_sn);
	}

	@Override
	public SurveyQuestionVo select(int qus_sn) {
		// TODO Auto-generated method stub
		SurveyQuestionVo vo = mapper.select(qus_sn);
		vo.setSurveyExample(surveyExampleService.selectList(qus_sn));
		return vo;
	}

	@Override
	public List<SurveyQuestionVo> selectList(int survey_sn) {
		// TODO Auto-generated method stub
		List<SurveyQuestionVo> rtnList = new ArrayList<>();
		List<SurveyQuestionVo> list = mapper.selectList(survey_sn);
		if(list != null && list.size() > 0) {
			for(SurveyQuestionVo vo:list) {
				vo.setSurveyExample(surveyExampleService.selectList(vo.getQus_sn()));
				vo.setSurveySubject(surveySubjectService.selectList(vo.getQus_sn()));
				rtnList.add(vo);
			}
		}
		
		return rtnList;
	}

}
