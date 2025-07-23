package egovframework.dnworks.cms.survey.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cms.survey.service.SurveyExampleVo;
import egovframework.dnworks.cms.survey.service.SurveyQuestionService;
import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;
import egovframework.dnworks.cms.survey.service.SurveyService;
import egovframework.dnworks.cms.survey.service.SurveyVo;

@Service("SurveyService")
public class SurveyServiceImpl extends EgovAbstractServiceImpl implements SurveyService
{
	
	@Autowired
	private SurveyMapper mapper;
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;

	@Override
	public int save(SurveyVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int survey_sn) {
		// TODO Auto-generated method stub
		List<SurveyQuestionVo> quesList = surveyQuestionService.selectList(survey_sn);
		int rtnVal = mapper.delete(survey_sn);
		if(rtnVal != 0) {
			if(quesList != null) {
				for(SurveyQuestionVo v:quesList) {
					String uploadDir = EgovProperties.getProperty("Globals.FilePath")+v.getWebDir();
					for(SurveyExampleVo e:v.getSurveyExample()) {
						FileManager.deleteFile(String.format("%s/%s", uploadDir, e.getExam_file()));
					}
				}
			}
		}
		return rtnVal;
	}

	@Override
	public SurveyVo select(int survey_sn) {
		// TODO Auto-generated method stub
		return mapper.select(survey_sn);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<SurveyVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
