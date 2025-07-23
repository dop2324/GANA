package egovframework.dnworks.cmm.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.cmm.utl.MoaMberComponent;
import egovframework.dnworks.func.cmm.utl.MoaSessionUtil;
import egovframework.dnworks.func.memb.service.MembService;

@Service
public class MoaUserCheckInterceptor implements HandlerInterceptor {

	@Autowired
	MembService membService;
	
	@Autowired
	MoaMberComponent moaMberComponent;
	
	private static final String MEMBER_CONFPATH = EgovProperties.getPathProperty("Globals.MemberConfPath");
	private static final String LOGIN_PAGE = EgovProperties.getProperty(MEMBER_CONFPATH, "moa.login.page");
	private static final String LOGIN_MNU_CODE = EgovProperties.getProperty(MEMBER_CONFPATH, "moa.login.mnuCode");
	
	private static final String IGNORE_MNU_CODES = EgovProperties.getProperty(MEMBER_CONFPATH, "moa.loginchk.mnuCode");
	private static final Set<String> IGNORE_CODES;
	static {
        IGNORE_CODES = Collections.unmodifiableSet(
                Arrays.stream(IGNORE_MNU_CODES.split(","))
                      .map(String::trim)        // 공백 제거
                      .filter(s -> !s.isEmpty()) // 빈 값 제거
                      .collect(Collectors.toSet())
        );
    }
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String returnUrl = request.getContextPath()+LOGIN_PAGE+"?mnu_code="+LOGIN_MNU_CODE;
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		
		// 로그인 검사가 필요한 mnu_code
		if (IGNORE_CODES.contains(mnu_code)) {
			
			// 모아회원 로그인 체크
			if(!MoaSessionUtil.isLogin(request)) {
				JavaScriptUtil.flushJsAlertAndLocation(response, "", returnUrl);
			}
        }
		
		return true;
	}
}
