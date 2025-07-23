package egovframework.dnworks.func.memb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.func.cmm.utl.MoaSessionUtil;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;

@Service("MembService")
public class MembServiceImpl extends EgovAbstractServiceImpl implements MembService {

	@Autowired
	MembMapper mapper;
	
	@Resource(name = "egovMembUnqIdGnrService")
    private EgovIdGnrService idgenService;

	@Override
	public boolean createDataMemb(HttpServletRequest request, MembInfoVO insertVO) throws Exception {
		boolean result = false;
		
		String membUnqId = this.idgenService.getNextStringId();
		insertVO.setMembUnqId(membUnqId);
		
		try {
			mapper.createDataMemb(insertVO);
			
			// 세션생성
			MoaSessionUtil.createSessionMoaMember(request, insertVO);
			
			result = true;
		} catch(Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public int isDuplicate(MembInfoVO searchVO) throws Exception {
		return mapper.isDuplicate(searchVO);
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
	public MembInfoVO select(Map<String, Object> map) throws Exception {
		return mapper.select(map);
	}

	@Override
	public void updateMembStatCd(EgovMap map) throws Exception {
		mapper.updateMembStatCd(map);
	}

	@Override
	public void updatePswd(MembInfoVO updateVO) throws Exception {
		mapper.updatePswd(updateVO);
	}

	@Override
	public void updateLgnFailCnt(Map<String, Object> map) throws Exception {
		mapper.updateLgnFailCnt(map);
	}

	@Override
	public void updateLgnSucc(Map<String, Object> map) throws Exception {
		mapper.updateLgnSucc(map);
	}

	@Override
	public void updateMembInfo(MembInfoVO updateVO) throws Exception {
		mapper.updateMembInfo(updateVO);
	}

	@Override
	public MembInfoVO selectLogin(Map<String, Object> map) throws Exception {
		return mapper.selectLogin(map);
	}

	@Override
	public List<MembInfoVO> selectLngtmList(Map<String, Object> map) throws Exception {
		return mapper.selectLngtmList(map);
	}

	@Override
	public void updateLngtmMemb(Map<String, Object> map) throws Exception {
		mapper.updateLngtmMemb(map);
	}

	@Override
	public MembInfoVO selectMembInfo(Map<String, Object> map) throws Exception {
		return mapper.selectMembInfo(map);
	}
	
}
