package egovframework.dnworks.func.cmm.utl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembLogService;
import egovframework.dnworks.func.memb.service.MembLogVO;
import egovframework.dnworks.func.memb.service.MembService;

@Component
public class MoaMberComponent {

	@Autowired
    private MembService membService;
	
	@Autowired
	private MembLogService moaLogService;
	
	private String MEMBER_CONFPATH = EgovProperties.getPathProperty("Globals.MemberConfPath");
		
	/**
	 * ID 중복검사
	 * @param String membId
	 * */
	public boolean isDuplicateId(String membId) throws Exception {
		MembInfoVO searchVO = new MembInfoVO();
		searchVO.setMembId(membId);
		
		int dupCnt = this.membService.isDuplicate(searchVO);
		return dupCnt > 0 ? false:true;
	}
	
	/**
	 * EMAIL 중복검사
	 * @param String emlAddr
	 * */
	public boolean isDuplicateEmail(String emlAddr) throws Exception {
		MembInfoVO searchVO = new MembInfoVO();
		searchVO.setEmlAddr(emlAddr);
		
		int dupCnt = this.membService.isDuplicate(searchVO);
		return dupCnt > 0 ? false:true;
	}
	
	/**
	 * 휴대폰번호 중복검사
	 * @param String mbtlnum
	 * */
	public boolean isDuplicateMbtlnum(String mbtlnum) throws Exception {
		MembInfoVO searchVO = new MembInfoVO();
		searchVO.setMbtlnum(mbtlnum);
		
		int dupCnt = this.membService.isDuplicate(searchVO);
		return dupCnt > 0 ? false:true;
	}
	
	/**
	 * CI 중복검사
	 * @param String ciVal
	 * */
	public boolean isDuplicateCi(String ciVal) throws Exception {
		MembInfoVO searchVO = new MembInfoVO();
		searchVO.setCiVal(ciVal);
		
		int dupCnt = this.membService.isDuplicate(searchVO);
		return dupCnt > 0 ? false:true;
	}
	
	/**
	 * DI 중복검사
	 * @param String diVal
	 * */
	public boolean isDuplicateDi(String diVal) throws Exception {
		MembInfoVO searchVO = new MembInfoVO();
		searchVO.setDiVal(diVal);
		
		int dupCnt = this.membService.isDuplicate(searchVO);
		return dupCnt > 0 ? false:true;
	}
	
	/**
	 * 회원가입 ID 유효성 체크
	 * 문자종류 제한:영문소문자, 숫자만 허용
	 * 길이제한: 너무 짤거나 긴 ID 방지(4~20자)
	 * 공백금지
	 * 금지단어 필터링
	 * 중복체크:기존 회원저장소에 존재하는 ID 사용불가
	 * */
	public boolean isVaildateId(String userId) throws Exception {
		if (userId == null) return false;
		
		boolean result = true;
		
		// 공백 체크 (전체 또는 중간 포함 여부)
		if(userId.contains(" ") || userId.trim().length() != userId.length()) {
			result = false;
		}
	    
	    // [a-z0-9]{4,20} : 소문자 또는 숫자, 4~20자
		if(!userId.matches("^[a-z0-9]{4,20}$")) {
			result =false;
		}
	    
		// 금지단어 필터링
		String banId	= EgovProperties.getProperty(MEMBER_CONFPATH, "moaid.banid");		
		if (banId != null && !banId.isEmpty()) {
			String[] bannedList = banId.toLowerCase().split(",");
			for (String banned : bannedList) {
				if (userId.toLowerCase().equals(banned.trim())) {
					result = false;
					break;
				}
			}
		}
				
		return result;
	}
	
	/**
	 * 비밀번호 유효성 검사
	 * @param String pswd
	 * */
	public boolean isVaildatePswd(String pswd) throws Exception {
		if (pswd == null) return false;
		
	    // 길이 체크
	    if (pswd.length() < 8 || pswd.length() > 15) {
	        return false;
	    }

	    // 정규식: 영문, 숫자, 특수문자 각각 하나 이상 포함
	    String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()_+`\\-={}\\[\\]:\";'<>?,./]).{8,15}$";

	    return pswd.matches(regex);
	}
	
	/**
	 * 모아회원 로그인 이력 로그
	 * **/	
	public void membLoginLog(String membUnqId, String membId, String useOrgId, String lgnSuccsYn, String lgnFailRs, String lgnIp) throws Exception {
		MembLogVO insertVO = new MembLogVO();
		
		insertVO.setMembUnqId(membUnqId);
		insertVO.setMembId(membId);
		insertVO.setUseOrgId(useOrgId);
		insertVO.setLgnSuccsYn(lgnSuccsYn);
		insertVO.setLgnFailRs(lgnFailRs);
		insertVO.setLgnIp(lgnIp);
		
		this.moaLogService.insert(insertVO);
	}
	
}
