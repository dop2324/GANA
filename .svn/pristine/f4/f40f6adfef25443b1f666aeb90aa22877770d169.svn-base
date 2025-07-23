package egovframework.dnworks.func.cmm.utl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.dnworks.cms.member.service.GroupVo;
import egovframework.dnworks.func.cmm.service.MoaFuncService;
import egovframework.dnworks.func.memb.service.MembAgentVO;

public class MoaFuncUtil {
	
	/**
     * 공통 Service 획득 메서드 (전역 변수 없이 요청당 한 번만 초기화됨)
     */
    private static MoaFuncService getService(HttpServletRequest request) {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        return (MoaFuncService) context.getBean("MoaFuncService");
    }
        
	public static String getCodeNm(HttpServletRequest request, String codeUprSn, String codeVal) throws Exception {
		MoaFuncService service = getService(request);
        
        EgovMap map = new EgovMap();
        map.put("codeUprSn", codeUprSn);
        map.put("codeVal", codeVal);
        
        String codeNm = service.getCodeNm(map);
        
		return codeNm;
	}
	
	public static List<Map<String, String>> getCodeList(HttpServletRequest request, String codeVal) throws Exception {
		MoaFuncService service = getService(request);
		
		EgovMap map = new EgovMap();
		map.put("codeVal", codeVal);
		
		List<Map<String, String>> result = service.getCodeList(map);
		
		return result;
	}
		
	/**
	 * MOA 회원 로그인 체크
	 * */
	public static boolean isLogin(HttpServletRequest request) throws Exception {
		return MoaSessionUtil.isLogin(request);
	}
	
	/**
	 * 관리자 그룹ID 로 해당 그룹정보 가져오기
	 * @param String grpId
	 * **/
	public static GroupVo getGroupInfo(HttpServletRequest request, String grp_id) throws Exception {
		MoaFuncService service = getService(request);
		GroupVo result = new GroupVo();
		result.setGrp_id(grp_id);
		
		result = service.getGroupInfo(result);
		
		return result;
	}
	
	/**
	 * 만 14세 미만 확인
	 * */
	public static boolean isAge14Under(String birthday) throws Exception {
		
		boolean result = true;
		if (birthday == null || birthday.length() != 8) {
			return false;
		}
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate birthDate;
		
		try {
			birthDate = LocalDate.parse(birthday, fmt);
		}catch(Exception e) {
			return false;
		}
		
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
		int age = Period.between(birthDate, today).getYears();
		if (age >= 14) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * MOA 이용기관 목록 조회
	 * */
	public static List<MembAgentVO> getUseOrgList(HttpServletRequest request) throws Exception {
		MoaFuncService service = getService(request);
		
		List<MembAgentVO> result = new ArrayList<MembAgentVO>();
		result = service.getUseOrgList();
		
		return result;
	}
	
	public static String getUseOrgNm(HttpServletRequest request, String useOrgId) throws Exception {
		MoaFuncService service = getService(request);
		
		MembAgentVO searchVO = new MembAgentVO();
		searchVO.setUseOrgId(useOrgId);
		
		return service.getUseOrgNm(searchVO);
	}
	
	/**
	 * 아이디의 앞 두 글자를 제외한 나머지를 '*' 로 마스킹한다.
	 * 예) "paroso" -> "pa****", "userid" -> "us****"
	 */
	public static String maskId(String maskId) {
	    if (maskId == null || maskId.length() <= 2) {
	        // 2글자 이하인 경우 그대로 반환
	        return maskId;
	    }

	    int maskLength = maskId.length() - 2;   // * 로 바꿀 길이
	    StringBuilder masked = new StringBuilder(maskId.substring(0, 2));

	    // Java 8용: 반복문으로 '*' 채우기
	    for (int i = 0; i < maskLength; i++) {
	        masked.append('*');
	    }

	    return masked.toString();
	}

}
