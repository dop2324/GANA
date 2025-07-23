package egovframework.dnworks.func.memb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembLogService;
import egovframework.dnworks.func.memb.service.MembLogVO;

@Service("MembLogService")
public class MembLogServiceImpl extends EgovAbstractServiceImpl implements MembLogService {

	@Autowired
	MembLogMapper mapper;
	
	// 로그 보관일 전역변수 가져오기
	private static final String MEMBER_CONFPATH = EgovProperties.getPathProperty("Globals.MemberConfPath");
	private static final String LOG_DT = EgovProperties.getProperty(MEMBER_CONFPATH, "moa.login.retntnDt");
	
	private static final String TAG = "MembSignUpController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");

	@Override
	public void insert(MembLogVO insertVO) throws Exception {
		mapper.insert(insertVO);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) throws Exception {
		return mapper.selectListCnt(map);
	}

	@Override
	public List<MembInfoVO> selectList(Map<String, Object> map) throws Exception {
		return mapper.selectList(map);
	}

	@Override
	public void removeLogData() throws Exception {
		long indexActionStartTime = System.currentTimeMillis();
		
		try {
			// 전역변수에서 삭제일 가져오기
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("retntnDt", LOG_DT);
			mapper.removeLogData(param);
		}catch(Exception e) {
			LOGGER.info("["+TAG+"][ERROR] {} " , e.toString());
		}
		
		long indexActionEndTime = System.currentTimeMillis();
		LOGGER.info("["+TAG+"] 오래된 로그인 이력 삭제완료! ACTION 처리시간 : " + ((indexActionEndTime - indexActionStartTime) / 1000.0D));
	}
	
}
