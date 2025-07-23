package egovframework.dnworks.cms.survey.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.survey.service.SurveyUserService;
import egovframework.dnworks.cms.survey.service.SurveyUserVo;


@Service("SurveyUserService")
public class SurveyUserServiceImpl extends EgovAbstractServiceImpl implements SurveyUserService
{
	@Autowired
	private SurveyUserMapper mapper;

	@Override
	public int save(SurveyUserVo vo) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		if(rtnVal != -1) {
			rtnVal = vo.getKey_sn();
		}
		return rtnVal;
	}

	@Override
	public int checkVote(SurveyUserVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.checkVote(vo);
	}
}
