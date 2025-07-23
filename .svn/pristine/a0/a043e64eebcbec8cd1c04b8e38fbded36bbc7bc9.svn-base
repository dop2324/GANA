package egovframework.dnworks.cmm.interceptor;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import egovframework.dnworks.base.WebBaseService;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.accessip.service.AccessIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListIPVo;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.blacklist.service.BlackListUserVo;
import egovframework.dnworks.cms.blacklist.service.BlockLogService;
import egovframework.dnworks.cms.blacklist.service.BlockLogVo;
import egovframework.dnworks.cms.menu.service.LogService;
import egovframework.dnworks.cms.menu.service.LogVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.PermissionService;
import egovframework.dnworks.cms.menu.service.SiteService;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Service
public class WebUserCheckInterceptor implements HandlerInterceptor
{
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	protected AccessIPService accessIPService;
	
	@Autowired
	protected BlackListIPService blackListIPService;
	
	@Autowired
	protected BlackListUserService blackListUserService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private BlockLogService blockLogService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private WebBaseService webBaseService;
	
	@Resource(name = "WebUserSessionDontChecklist")
    protected List<String> WebUserSessionDontChecklist;
	
	@Value("${Globals.AdminPath}")
	protected String managerDir;
	
	@Value("${Globals.PublicPath}")
	protected String publicDir;
	
	private String log_what = "";
	
	private void insertLog(HttpServletRequest request, String mnu_code, String log_what) throws Exception
	{
		int cmd 	= Util.nvl(request.getParameter("cmd"), 1);
		MenuVo menuVo = menuService.select(mnu_code);
		
		LogVo vo = new LogVo();
		vo.setMnu_code("mng");
		//vo.setLog_when(); SYSDATE()
		vo.setLog_who(String.format("%s (%s)", Util.getSession(request.getSession(), "USR_ID"), Util.getSession(request.getSession(), "USR_NM")));
		vo.setLog_where(menuVo != null ?menuVo.getMnu_nm():mnu_code);
		vo.setLog_what(log_what);
		vo.setLog_how(Util.nvl(cmd));
		vo.setLog_privacy("N");
		vo.setLog_ip(IPUtil.getXForwardedFor(request));
		logService.insert(vo);
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		URL url 			= new URL(Util.nvl(request.getRequestURL()));
		String servletPath 	= Util.nvl(request.getAttribute("javax.servlet.forward.servlet_path"));
		
		String Path 		= String.format("%s%s%s"
											, Util.nvl(servletPath, url.getPath())
											, request.getQueryString() != null ? "?":""
											, request.getQueryString() != null ? request.getQueryString():""
											);
		String site_code 	= "";
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		SiteVo siteVo 		= null;
		MenuVo menuVo 		= menuService.select(mnu_code);
		
		if(mnu_code.equals("")) {
			siteVo = webBaseService.selectSiteVo(request);
			if(siteVo != null) {
				site_code = siteVo.getSite_code();
			}
		}else {
			//menuVo null
			if(menuVo == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/error/error404.do");
	            rd.forward(request, response);
				return false;
			}
			site_code = menuVo.getSite_code();
			Map<String, Object> siteMap = new HashMap<>();
			siteMap.put("site_code"	, site_code);
			siteVo = siteService.select(siteMap);
		}
		
		/*
		System.out.println("/// WebUserCheckInterceptor /////////////////////////");
		System.out.println("path : "+Path);
		System.out.println("servletPath : "+servletPath);
		System.out.println("uri : "+request.getAttribute("javax.servlet.include.request_uri"));		
		System.out.println("mnu_code : "+mnu_code+":"+site_code);
		*/
		
		//사이트 코드 없음
		if(siteVo == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/error/error404.do");
            rd.forward(request, response);
			return false;
		}
		
		//1 access ip
        String accessip = Util.getFormatIP(IPUtil.getXForwardedFor(request));
		Map<String, Object> map = new HashMap<>();
		map.put("site_code"	, site_code);
		map.put("accessip"	, accessip);

		int accessipCnt = accessIPService.isAccessIP(map);
		
		if(siteVo != null && siteVo.getSite_accessIpYn() == 1) {
			//접속불가 페이지 이동
			if(accessipCnt == 0) {		
				log_what = String.format("사용자 AccessIP : %s 접근 차단", accessip);
				this.insertLog(request, "WebUserCheckInterceptor", log_what);
				
				RequestDispatcher rd = request.getRequestDispatcher("/error/error.do?err_code=1000");
	            rd.forward(request, response);
				return false;
			}
		}

		//2 BlackListIP
		map.remove("site_code");
		List<BlackListIPVo> blackListIPList = blackListIPService.selectIPList(map);
		
		if(blackListIPList != null && blackListIPList.size() > 0) {
			boolean blockLogFlag = false;
			
			for(BlackListIPVo v:blackListIPList) {
				//범위 전체 
				if(v.getBli_rng() == 1) {
					blockLogFlag = true; break;
				}
				
				if(v.getSite_code().equals(site_code)) {
					blockLogFlag = true;
					break;
				}
			}
			
			if(blockLogFlag) {
				//WEB_BlockLog 입력
				BlockLogVo logVo = new BlockLogVo();
				logVo.setLog_id(accessip);
				logVo.setLog_nm("BlackListIP 등록 IP");
				logVo.setLog_ip(accessip);
				logVo.setLog_url(Path);
				logVo.setLog_se(null);
				logVo.setSite_code(site_code);
				blockLogService.insert(logVo);
				
				//접속불가 페이지 이동
				RequestDispatcher rd = request.getRequestDispatcher("/error/error.do?err_code=2000");
	            rd.forward(request, response);
				return false;
			}
		}

		//3 check session
		//세션 체크 하지 않는 주소이면 context-sessionlist.xml (포함되지 않을때 진행)
		if (!Path.equals("")) {
			boolean sessionCheck = true;
			for(String checkList: WebUserSessionDontChecklist) {
				if(Path.indexOf(checkList) != -1) {
					sessionCheck = false;
					break;
				}
			}

			if(sessionCheck) {

				//4 관리자일경우 세션 체크
				if(siteVo.getSite_mngYn() == 1) {
					if( Util.getSession(request.getSession(), "USR_ID").equals("guest") || Util.getSession(request.getSession(), "USR_ID").equals("") ) {
						//세션 종료 페이지
			        	log_what = String.format("관리자 세션 종료 : %s", accessip);
			        	this.insertLog(request, "WebUserCheckInterceptor", log_what);
			        	
			        	RequestDispatcher rd = request.getRequestDispatcher("/error/error.do?err_code=3000");
			            rd.forward(request, response);
						return false;
					}
				}
				
				String expriePw = Util.getSession(request.getSession(), "EXPIRE_PW");
				if(expriePw.equals("true")) {
					String redirectLink = String.format("/%s/expirepw.do", site_code);
					RequestDispatcher rd = request.getRequestDispatcher(redirectLink);
		            rd.forward(request, response);
		            return false;
				}

		        //5 권한 체크
				String parmMnuCode	= Util.nvl(request.getParameter("parmMnuCode"));
				int cmd				= Util.nvl(request.getParameter("cmd"), 1);
				
				//참조메뉴코드
				if(!parmMnuCode.equals("")) mnu_code = parmMnuCode;
				
				//메뉴 코드가 있을때만
				if(!mnu_code.equals("")) {
					Map<String, Object> prmMap = new HashMap<>();
					prmMap.put("mnu_code"	, mnu_code );
					prmMap.put("usr_id"		, Util.getSession(request.getSession(), "USR_ID", "guest") );
					prmMap.put("dpt_id"		, Util.getSession(request.getSession(), "DPT_ID", "") );
					prmMap.put("grp_id"		, Util.getSession(request.getSession(), "GRP_ID", "GRP_003") );
					int prmVal = Util.nvl(permissionService.getPermission(prmMap), 0);
					
					if((prmVal & cmd) == 0) {
						log_what = String.format("사용자 권한 없음 : %s, %s, %s"
												, Util.getSession(request.getSession(), "USR_ID")
												, Util.getSession(request.getSession(), "DPT_ID")
												, Util.getSession(request.getSession(), "GRP_ID")
												);
						this.insertLog(request, "WebUserCheckInterceptor", log_what);
						
						RequestDispatcher rd = request.getRequestDispatcher("/error/error.do?err_code=5000");
			            rd.forward(request, response);
						return false;
					}
				}
			}
		}

    	//4 BlackListUser
    	String blu_id = Util.getSession(request.getSession(), "USR_ID");
    	map.put("blu_id", blu_id);
    	List<BlackListUserVo> bluIDList = blackListUserService.selectUserList(map);
    	map.put("blu_id", Util.getSession(request.getSession(), "USR_DI"));
    	List<BlackListUserVo> bluDIList = blackListUserService.selectUserList(map);

    	List<BlackListUserVo> margeBluList = new ArrayList<>();
    	margeBluList.addAll(bluIDList);
    	margeBluList.addAll(bluDIList);

    	if(margeBluList != null && margeBluList.size() > 0) {
    		boolean blockLogFlag = false;
    		
    		for(BlackListUserVo v:margeBluList) {
    			if(v.getBlu_rng() == 1) {
    				blockLogFlag = true; break;
    			}
    			
    			if(v.getSite_code().equals(site_code)) {
					blockLogFlag = true;
					break;
				}
    		}
    		
    		if(blockLogFlag) {
	    		//WEB_BlockLog 입력
				BlockLogVo logVo = new BlockLogVo();
				logVo.setLog_id(blu_id);
				logVo.setLog_nm("BlackListUser 등록 ID");
				logVo.setLog_ip(accessip);
				logVo.setLog_url(Path);
				logVo.setLog_se(null);
				logVo.setSite_code(site_code);
				blockLogService.insert(logVo);
				
				//접속불가 페이지 이동
				RequestDispatcher rd = request.getRequestDispatcher("/error/error.do?err_code=4000");
	            rd.forward(request, response);
				return false;
    		}
    	}

        return true;
    }
	
	/*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modeAndView) throws Exception {
        System.out.println("postHandle >>>  Controller 실행 후 실행");
        

    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException{
        System.out.println("afterCompletion >>>  preHandle 메소드 return값이 true일 때 실행");
        
    }
     */
}
