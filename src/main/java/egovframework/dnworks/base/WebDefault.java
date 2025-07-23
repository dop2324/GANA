package egovframework.dnworks.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListIPVo;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.blacklist.service.BlackListUserVo;
import egovframework.dnworks.cms.blacklist.service.BlockLogService;
import egovframework.dnworks.cms.blacklist.service.BlockLogVo;
import egovframework.dnworks.cms.menu.service.LogService;
import egovframework.dnworks.cms.menu.service.LogVo;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogService;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.PermissionService;
import egovframework.dnworks.cms.menu.service.SiteService;
import egovframework.dnworks.cms.menu.service.SiteVo;


public class WebDefault 
{
	@Autowired
	protected WebBaseService webBaseService;
	
	@Autowired
    private SiteService siteService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private MenuAlarmLogService menuAlarmLogService;
	
	@Autowired
	private BlackListIPService blackListIPService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	@Autowired
	private BlockLogService blockLogService;
	
	
	
	@Value("${Globals.PublicPath}")
	protected String publicPath;
	
	@Value("${Globals.AdminPath}")
	protected String managerDir;
	
	@Value("${site.html.meta.charset}")
	protected String DefaultCharSet;
	
	@Value("${Site.mem.GuestID}")
	protected String guestID;

	protected String log_what = "";
	
	protected String[] NOT_alwID = {"manager","admin","master","administrator"};
	
	protected String IDpattern = "^[a-zA-Z0-9]*$";
	
	//비밀번호 최소 자리수
	protected int minPwLen = Util.nvl(EgovProperties.getProperty("Globals.minPwLen"), 0);
	//비밀번호 최대 자리수
	protected int maxPwLen = Util.nvl(EgovProperties.getProperty("Globals.maxPwLen"), 0);
	
	protected String PasswordPattern = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{"+minPwLen+","+maxPwLen+"})$";
	
	/*
	 * 관리자 Log_Manager 기본설정
	 */
	protected void insertLog(HttpServletRequest request, String log_what) throws Exception {
		int cmd 	= Util.nvl(request.getParameter("cmd"), 1);
		this.insertLog(request, log_what, Util.nvl(cmd));
	}
	protected void insertLog(HttpServletRequest request, String log_what, String log_how) throws Exception {
		String mnu_code = Util.nvl(request.getParameter("mnu_code"), "mng");
		MenuVo menuVo = menuService.select(mnu_code);
		
		String usr_id = Util.getSession(request.getSession(), "USR_ID");
		String usr_nm = Util.getSession(request.getSession(), "USR_NM");
		String log_who = "Session 종료";
		
		if(!usr_id.equals("")) {
			log_who = String.format("%s (%s)", usr_id, usr_nm);
		}
		
		LogVo vo = new LogVo();
		vo.setMnu_code(mnu_code);
		vo.setLog_who(log_who);
		vo.setLog_where(menuVo.getMnu_nm());
		vo.setLog_what(log_what);
		vo.setLog_how(log_how);
		vo.setLog_privacy(menuVo.getMnu_privacy());
		vo.setLog_ip(IPUtil.getXForwardedFor(request));
		logService.insert(vo);
	}
	/*
	 * insertBlockLog
	 */
	protected void insertBlockLog(HttpServletRequest request, String log_id, String log_msg) throws Exception {
		String mnu_code = Util.nvl(request.getParameter("mnu_code"), "mng");
		MenuVo menuVo = menuService.select(mnu_code);
		log_msg = String.format("%s %s %s", log_msg, menuVo.getSite_nm(), menuVo.getMnu_nm());
		
		BlockLogVo logVo = new BlockLogVo();
		logVo.setLog_id(log_id);
		logVo.setLog_nm(log_msg);
		logVo.setLog_ip(IPUtil.getXForwardedFor(request));
		logVo.setLog_url(this.servletPath(request));
		logVo.setLog_se(null);
		logVo.setSite_code(menuVo.getSite_code());
		blockLogService.insert(logVo);
	}
	
	/*
	 * saveBlackListIP
	 */
	protected void saveBlackListIP(HttpServletRequest request, String site_code, String bli_desc, String mem_id) throws Exception {
		BlackListIPVo vo = new BlackListIPVo();
		vo.setBli_startIP(Util.getFormatIP(IPUtil.getXForwardedFor(request)));
		vo.setBli_endIP(Util.getFormatIP(IPUtil.getXForwardedFor(request)));
		vo.setBli_desc(bli_desc);
		vo.setBli_sttus(1);
		vo.setBli_mdfcnID(mem_id);
		vo.setSite_code(site_code);
		blackListIPService.save(vo);		
	}
	
	/*
	 * saveBlackListUser
	 */
	protected void saveBlackListUser(HttpServletRequest request, String site_code, String blu_id, String blu_nm, String blu_desc) throws Exception {
		BlackListUserVo vo = new BlackListUserVo();
		vo.setBlu_id(blu_id);
		vo.setBlu_nm(blu_nm);
		vo.setBlu_rng(0);
		vo.setBlu_desc(blu_desc);
		vo.setBlu_sttus(1);
		vo.setBlu_mdfcnID(blu_id);
		vo.setSite_code(site_code);
		blackListUserService.insert(vo);		
	}
	
	/*
	 * insert MenuAlarmLog
	 */
	protected void insertAlarmLog(String mnu_code
			, String mnu_alarmMethod
			, String mnu_alarmSe
			, String mnu_alarmTrgt
			, String mnu_alarmMsg) throws Exception {
		MenuAlarmLogVo vo = new MenuAlarmLogVo();
		vo.setMnu_code(mnu_code);
		vo.setMnu_alarmMethod(mnu_alarmMethod);
		vo.setMnu_alarmSe(mnu_alarmSe);
		vo.setMnu_alarmTrgt(mnu_alarmTrgt);
		vo.setMnu_alarmMsg(mnu_alarmMsg);
		this.insertAlarmLog(vo);
	}
	protected void insertAlarmLog(MenuAlarmLogVo vo) throws Exception {
		menuAlarmLogService.insert(vo);
	}
	
	
	protected int getPermission(HttpServletRequest request, HttpSession session) throws Exception {
		// 보안 : 권한 조회
		int rtnVal 			= 0;
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String parmMnuCode= Util.nvl(request.getParameter("parmMnuCode"));

		if(!parmMnuCode.equals("")) mnu_code = parmMnuCode;
		
		Map<String, Object> prmMap = new HashMap<>();
		prmMap.put("mnu_code"	, mnu_code );
		prmMap.put("usr_id"		, Util.getSession(session, "USR_ID") );
		prmMap.put("dpt_id"		, Util.getSession(session, "DPT_ID") );
		prmMap.put("grp_id"		, Util.getSession(session, "GRP_ID") );
		rtnVal = Util.nvl(permissionService.getPermission(prmMap), 0);
		return rtnVal;
	}
	
	public boolean isAuthed(HttpSession session) {
		return !Util.getSession(session, "USR_ID").equals(EgovProperties.getProperty("Site.mem.GuestID"));
	}
	public boolean isAuthedGrp(HttpSession session) {
		return !Util.getSession(session, "GRP_ID").equals(EgovProperties.getProperty("Site.grp.GuestID"));
	}
	
	protected void removeNiceIDSession(HttpSession session) {
		//본인확인 세션 삭제
		session.removeAttribute("AuthName");
		session.removeAttribute("USR_NM");
		session.removeAttribute("USR_CI");
		session.removeAttribute("USR_DI");
		session.removeAttribute("USR_BIRTH");
		session.removeAttribute("USR_HP");
	}
	
	protected void getPageImportParam(HttpServletRequest request, HttpSession session, ModelMap model) {
		SiteVo siteVo = (SiteVo) request.getAttribute("siteVo");
		MenuVo tmnuVo = (MenuVo) request.getAttribute("tmnuVo");
		MenuVo pmnuVo = (MenuVo) request.getAttribute("pmnuVo");
		MenuVo menuVo = (MenuVo) request.getAttribute("menuVo");
		int prmVal = (int) request.getAttribute("prmVal");
		
		model.addAttribute("siteVo"	, siteVo);
		model.addAttribute("tmnuVo"	, tmnuVo);
		model.addAttribute("pmnuVo"	, pmnuVo);
		model.addAttribute("menuVo"	, menuVo);
		model.addAttribute("prmVal"	, prmVal);
	}
	
	protected Map<String, Object> sitePrmList(HttpSession session) throws Exception {
		Map<String, Object> rtnMap = null;

		Map<String, Object> map = new HashMap<>();
		map.put("site_deleteYn"	, 1);
		map.put("mnu_ty"		, Code.MnuType.All);
		map.put("usr_id"		, Util.getSession(session, "USR_ID"));
		map.put("dpt_id"		, Util.getSession(session, "DPT_ID"));
		map.put("grp_id"		, Util.getSession(session, "GRP_ID"));
		List<SiteVo> sitePrmList = siteService.selectPrmList(map);
		
		if(sitePrmList != null && sitePrmList.size() > 0) {
			rtnMap = new HashMap<>();
			rtnMap.put("sitePrmList", sitePrmList);
			//관리자 제외한 사이트
			String defaultSite = "";
			
			for(SiteVo v:sitePrmList) {
				defaultSite = v.getSite_code();
				if(v.getSite_mngYn() == 0) {
					break;
				}
			}
			rtnMap.put("defaultSite", defaultSite);
		}
		return rtnMap;
	}
	
	protected String servletPath(HttpServletRequest request) throws Exception {
		return Util.nvl(request.getAttribute("javax.servlet.forward.servlet_path"));
	}
	
	protected String menuPathDir(String mnu_code) {
		String rtnVal = "";
		Map<String, Object> pathMap = new HashMap<>();
		pathMap.put("mnu_code"	, mnu_code);
		pathMap.put("mnu_sttus"	, 1);
		List<MenuVo> menuPath = menuService.selectPath(pathMap);
		for(MenuVo m:menuPath) {
			if(m.getMnu_level() == 1) {
				rtnVal = String.format("/%s/%s", m.getMnu_uprCode(), m.getMnu_code());
			}else {
				rtnVal = String.format("%s/%s", rtnVal, m.getMnu_code());
			}
		}
		return rtnVal;
	}
	
	protected Map<String, Object> refineMenusFactory(HttpSession session
												, LinkedHashMap<String, MenuVo> lhmMenuTree
												, int mnuLevel, String naviType
												, boolean isAdminMode, List<String> prmSelMenuList, List<String> prmAdmMenuList
												)
	{
		Map<String, Object> returnMap = new HashMap<>();
		List<MenuVo> topMenuList = new ArrayList<>();
		Map<String, List<MenuVo>> subMenuListMap = new LinkedHashMap<>();
		
		// 2. 메뉴를 레벨별로 따로 담는다.
		Stack<String> stackMnuCode = new Stack<>();										// 1차레벨 임시 저장 스택
		ArrayList<MenuVo> arraySubMenus = null;											// ArrayList<2차 메뉴 Menu>
		
		for(MenuVo m:lhmMenuTree.values()) {
			// 출력 제외 대상 처리
			if(!(m.getMnu_level() == mnuLevel || m.getMnu_level() 	== (mnuLevel + 1)))			continue;
			//인증사용자만 보기
			if(!this.isAuthed(session) 	&& m.getMnu_visibleAuth() 	== 1) 						continue;
			if(naviType.equals("gnb") 	&& m.getMnu_visibleGnb() 	== 0)						continue;
			if(naviType.equals("snb") 	&& m.getMnu_visibleSnb() 	== 0)						continue;
			if(naviType.equals("tab") 	&& m.getMnu_visibleTab() 	== 0)						continue;
			if(m.getMnu_sttus() == 0)															continue;
			
			if(isAdminMode) {
				if(prmAdmMenuList != null && !prmAdmMenuList.contains(m.getMnu_code()))			continue;	// 관리권한이 없으면 continue
			}else {
				if(prmSelMenuList != null && !prmSelMenuList.contains(m.getMnu_code()))			continue;	// 사용자 보기권한이 없으면 continue
			}
			
			if(m.getMnu_level() == mnuLevel) {
				if(!stackMnuCode.isEmpty()) {
					if((arraySubMenus != null) && (!arraySubMenus.isEmpty())) {
						lhmMenuTree.get(stackMnuCode.peek()).setMnu_childCnt(arraySubMenus.size());
					}
					topMenuList.add(lhmMenuTree.get(stackMnuCode.peek()));
					subMenuListMap.put(stackMnuCode.pop(), arraySubMenus);
				}
				
				// ArrayList<2차 메뉴 Menu>를 초기화 한다.
				// 임시 저장 스택에 1차 메뉴 mnu_uid를 담는다.
				arraySubMenus = new ArrayList<MenuVo>();
				stackMnuCode.push(m.getMnu_code());
			}
			else if(m.getMnu_level() == (mnuLevel + 1) && !stackMnuCode.isEmpty() && stackMnuCode.peek().equals(m.getMnu_uprCode()))
			{
				// ArrayList<2차 메뉴 Menu>담는다.
				arraySubMenus.add(m);
			}
		}
		
		if(!stackMnuCode.isEmpty()) {
			if(arraySubMenus != null && !arraySubMenus.isEmpty()) {
				lhmMenuTree.get(stackMnuCode.peek()).setMnu_childCnt(arraySubMenus.size());
			}
			topMenuList.add(lhmMenuTree.get(stackMnuCode.peek()));
			subMenuListMap.put(stackMnuCode.pop(), arraySubMenus);
		}
		
		stackMnuCode.clear();

		returnMap.put("topMenuList"		, topMenuList);
		returnMap.put("subMenuListMap"	, subMenuListMap);
		
		return returnMap;
	}
	
    // 사용자 정의 파라메타 만들기 
 	// 사용자가 정의한 HashMap(parmName, parmValue)를 인자로 받아 이를 기준으로
 	// 1.	넘어온 사용자 정의 파라메타가 empty라면 jsp page로 넘어온 Raw Parameter를 그대로 만든다.
 	// 2.	jsp page로 넘어온 Raw Parameter중 parmName과 일치하는 파라메타값은 그대로 사용하고 
 	// 		없다면 인자로 넘긴  parmValue를 지정한다.
 	// 3.	그리고 넘어온 사용자 정의 파라메타중 Raw Parameter에 없는 parmName도 파라메타로 만든다.
	@SuppressWarnings("unchecked")
	protected String getParameters(HttpServletRequest request, HashMap<String, Object> userDefineParms, boolean flagUDPOnly, boolean flagEncoding) {	
 		HashMap<String, Object> userParms = (HashMap<String, Object>) userDefineParms.clone();
 		HashMap<String, String> tempParms = new HashMap<String, String>();
 		
 		StringBuffer sb = new StringBuffer();
 		String parmName = "";
 		String parmValue = "";
 		Enumeration<?> enumParms = request.getParameterNames();
 		
 		while(enumParms.hasMoreElements()) {
 			parmName = enumParms.nextElement().toString();
 			parmValue = request.getParameter(parmName);

 			if(userParms.containsKey(parmName)) {
 				String tempParamsVal = Util.nvl(parmValue).equals("") ? userParms.get(parmName).toString() : parmValue;
 				if(Util.nvl(tempParamsVal).equals("")) {
 					userParms.remove(parmName); 
 					continue;
 				}
 				// 넘어온 파라메타 값 == 없다 ? 사용자 정의 파라메타 값 : 넘어온 파라메타 값
 				tempParms.put(parmName, tempParamsVal);
 				userParms.remove(parmName);
 			}else{
 				// 사용자 정의 파라메타이외의 넘어온 파라메타를 입력 하되
 				// 사용자 정의 파마메타로만 구성하겠다면 처리 skip
 				if(flagUDPOnly) continue; 
 				// 똑같은 파라메타 값이 갱신되지 않게
 				if(!tempParms.containsKey(parmName)) {
 					tempParms.put(parmName, parmValue);
 				}
 			}
 		}
 		
 		for(Object o:userParms.keySet().toArray()) {
 			tempParms.put(o.toString(), userParms.get(o).toString());
 		}
 		
 		// 누적된 파라메타를 쿼리스트링으로 만들어 반환한다.
 		for(Object o:tempParms.keySet().toArray()) {
 			try {
 				parmName = o.toString();
 				parmValue = flagEncoding ? URLEncoder.encode(tempParms.get(o), DefaultCharSet) : tempParms.get(o);
 				
 				// 원본 사용자 정의 파라메타 갱신(page에서 참조하기 위해서)
 				userDefineParms.put(parmName, parmValue);
 			} catch (UnsupportedEncodingException e) {
 				System.out.println("Util.java getParameters Error ");
 			}
 			
 			//parmValue 값이 있을때만
 			if(!parmValue.equals("")) {
 				sb.append(String.format("%s=%s&", parmName, parmValue));
 			}
 		}
 		return sb.toString();
 	}
	
	/**
	 * 기관 담당자 체크 및 담당 기관 정보 가져온다.
	 * @param session("GRP_ID")
	 * */
	protected String mngrOrgUnqId(HttpSession session) throws Exception {
		String mngrOrgUnqId = "";
		
		if(Util.getSession(session, "GRP_ID").matches("^ORG_\\d{18}$")) {
			mngrOrgUnqId = Util.getSession(session, "GRP_ID");
		}
		
		return mngrOrgUnqId;
	}
}
