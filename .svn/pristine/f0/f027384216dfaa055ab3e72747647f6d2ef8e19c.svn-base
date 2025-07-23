package egovframework.dnworks.controller.sitemanager.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.session.SessionConfig;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.SiteVo;
import egovframework.dnworks.cms.stats.service.SessionService;
import egovframework.dnworks.cms.stats.service.SessionVo;

@Controller
@RequestMapping("/SiteManager")
public class ManagerLoginController extends WebDefault
{
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	@Value("${Globals.AdminPath}")
	protected String managerDir;
	
	/*
	 * 관리자 접속 IP, 세션 정보, blackListIP
	 * package egovframework.dnworks.cmm.interceptor;
	 * MngUserCheckInterceptor preHandle 처리
	 */
	@RequestMapping(value = {"/login.do"})
	public String login(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		
		if(base.get("siteVo") == null) {
			return "/error/error404";
		}
		
		return managerDir+"/login/login";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/managerLoginProcess.do")
	public String managerLoginProcess(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		//MenuVo menuVo = (MenuVo) base.get("menuVo");
		List<String> prmAdmMenuList = (List<String>) base.get("prmAdmMenuList");
		
		int LoginCnt = Util.nvl(Util.getSession(session, "LoginCnt"), 0);
		String mem_id 	= Util.nvl(request.getParameter("user_id"));
		String mem_pass	= Util.nvl(request.getParameter("user_password"));
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			Map<String, Object> bluMap = new HashMap<>();
			bluMap.put("blu_id"		, mem_id);
			bluMap.put("site_code"	, managerDir.replaceAll("\\/", ""));
			int isblackListUser = blackListUserService.isBlackListUser(bluMap);
			if(isblackListUser > 0) {
				super.insertBlockLog(request, mem_id, "차단 설정 된 아이디");
				model.addAttribute("message", "차단 설정 된 아이디 입니다\\n관리자에게 문의 바랍니다.");
				model.addAttribute("location", "location.href='/error/error.do?err_code=4000'");
				return "common/scriptAlert";
			}
			
			//로그인
			Map<String, Object> map = new HashMap<>();
			map.put("site_code"	, siteVo.getSite_code());
			map.put("mem_id"	, mem_id);
			//map.put("mem_pw"	, mem_pass);
			UserVo userVo = userService.login(map);
			
			if(Util.getSession(session, "LoginCnt").equals("")) {
				Util.setSession(session, "LoginCnt", 0);
			}
			
			if(userVo == null) {
				log_what = String.format("없는 ID 로그인 시도 (%s)", mem_id);
				super.insertLog(request, log_what);
				
				//로그인 실패 카운트 증가
				this.updateFailCnt(session, mem_id);
				
				if(LoginCnt > 4) {
					//차단 ip등록
					super.saveBlackListIP(request, siteVo.getSite_code(), String.format("(%s) 관리자 로그인 5회 실패 IP 차단", mem_id), mem_id);
					model.addAttribute("message", "로그인 5회 실패 IP 차단 되었습니다");
					model.addAttribute("location", "location.href='/'");
				}
				else
				{
					model.addAttribute("message", "아이디 및 비밀번호를 확인해주세요");
					model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
				}
	
				return "common/scriptAlert";
			}
			else
			{
				//비밀번호 비교
				//20241024 salt추가
				mem_pass = SHA256Util.getSHA256(mem_pass, userVo.getMem_salt());
				
				if(!mem_pass.equals(userVo.getUsr_pw())) 
				{
					log_what = String.format("관리자 비밀번호 오류 (%s)", mem_id);
					super.insertLog(request, log_what);
					
					//로그인 실패 카운트 증가
					this.updateFailCnt(session, mem_id);

					if(LoginCnt > 4) {
						//차단 ID등록
						super.saveBlackListUser(request, siteVo.getSite_code(), mem_id, userVo.getUsr_nm(), String.format("(%s) 로그인 5회 실패 ID 차단", mem_id));
						//회원 비활성 mem_sttus = 0
						memberService.updateDisable(mem_id);
						model.addAttribute("message", "로그인 5회 실패 ID 차단 되었습니다");
						model.addAttribute("location", "location.href='/'");
						return "common/scriptAlert"; 
					}
					
					model.addAttribute("message", "아이디 및 비밀번호를 확인해주세요");
					model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
					
					return "common/scriptAlert"; 
				}
				
				//관리자 계정 sttus 상태 확인
				if(userVo.getUsr_sttus() == 0) {
					model.addAttribute("message", "관리자 계정 사용중지 되어있습니다\\n관리자에게 문의 바랍니다.");
					model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
					
					return "common/scriptAlert"; 
				}
				
				Map<String, Object> prmMap = new HashMap<>();
				prmMap.put("usr_id", userVo.getUsr_id() );
				prmMap.put("dpt_id", userVo.getDpt_id() );
				prmMap.put("grp_id", userVo.getGrp_id() );
				//메뉴 관리권한 목록
				prmAdmMenuList = menuService.selectPrmAdminList(prmMap);
						
				//관리 권한있는지 확인
				if(prmAdmMenuList == null || prmAdmMenuList.size() == 0)
				{
					log_what = String.format("관리자 관리 권한이 없는 사용자 (%s)", mem_id);
					super.insertLog(request, log_what);
					
					//로그인 실패 카운트 증가
					this.updateFailCnt(session, mem_id);
					
					if(LoginCnt > 4) {
						//차단 ID등록
						super.saveBlackListUser(request, siteVo.getSite_code(), mem_id, userVo.getUsr_nm(), String.format("(%s) 관리 권한이 없는 사용자 ID 차단", mem_id));
						//회원 비활성 mem_sttus = 0
						memberService.updateDisable(mem_id);
						model.addAttribute("message", "로그인 5회 실패 IP 차단 되었습니다");
						model.addAttribute("location", "location.href='/'");
					}else{
						model.addAttribute("message", "관리 권한이 없는 사용자 입니다");
						model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
					}
	
					return "common/scriptAlert";
				}
				else
				{
					//중복로그인 방지
					String CHKDUP_ID = String.format("MANAGER_%s", mem_id);
					String DPCN = "";
					DPCN = SessionConfig.getSessionidCheck("CHKDUP_ID", CHKDUP_ID);
					if(!DPCN.equals("")) {
						log_what = String.format("%s 관리자 중복 로그인 삭제\n(%s)", mem_id, DPCN);
						super.insertLog(request, log_what);
					}
					
					//접속수 증가, 로그인 실패 초기화
					memberService.updateJoinCount(mem_id);
					memberService.initLoginFailCnt(mem_id);
										
					//관리자 세션 생성
					int sessionTime = Util.nvl(EgovProperties.getProperty("Globals.sessionTime"), 10);
					session.setMaxInactiveInterval(sessionTime*60);
					
					Util.setSession(session, "USR_ID"		, userVo.getUsr_id());
					Util.setSession(session, "USR_NM"		, userVo.getUsr_nm());
					
					if(!Util.nvl(userVo.getDpt_id()).equals("")) Util.setSession(session, "DPT_ID"		, userVo.getDpt_id());
					if(!Util.nvl(userVo.getDpt_nm()).equals("")) Util.setSession(session, "DPT_NM"		, userVo.getDpt_nm());
					
					Util.setSession(session, "GRP_ID"		, userVo.getGrp_id());
					Util.setSession(session, "GRP_NM"		, userVo.getGrp_nm());
					
					Util.setSession(session, "USR_MAIL"		, userVo.getUsr_mail());
					Util.setSession(session, "USR_PARAM"	, userVo.getUsr_param());
					
					Util.setSession(session, "CHKDUP_ID"	, CHKDUP_ID);
					
					//접속정보 업데이트
					SessionVo sessionVo = new SessionVo();
					sessionVo.setSes_id(session.getId());
					sessionVo.setSes_sesID(userVo.getUsr_id());
					sessionService.update(sessionVo);
					
					//비밀번호 만료일 체크
					int expirePwDt = Util.nvl(EgovProperties.getProperty("Globals.expirePwDt"), 180);
					if(expirePwDt <= userVo.getMem_chgPwDtDiff()) {
						
						Util.setSession(session, "EXPIRE_PW", "true");
						model.addAttribute("message", "비밀번호 사용기한이 만료 되었습니다");
						model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
						return "common/scriptAlert";
					}
					
					//로그인 처음 메뉴 
					String loginMenuCode="";
					LinkedHashMap<String, MenuVo> siteMenuTree = (LinkedHashMap<String, MenuVo>) base.get("siteMenuTree");
					
					for(MenuVo m:siteMenuTree.values()) {
						if(!prmAdmMenuList.contains(m.getMnu_code()))	continue;	// 사용자 보기권한이 없으면 continue
												
						if(!m.getMnu_ty().equals(Code.MnuType.Menu.getText()) 
								&& !m.getMnu_ty().equals(Code.MnuType.Root.getText())
								&& m.getMnu_sttus() == 1)
						{
							loginMenuCode = m.getMnu_code();
							break;
						}
					}

					log_what = String.format("관리자 로그인 성공 (%s)", mem_id);
					super.insertLog(request, log_what, "login");
					
					return "redirect:"+managerDir+"/page.do?mnu_code="+loginMenuCode; 
				}
			}
			
		}
		return null;
	}
	
	private void updateFailCnt(HttpSession session, String mem_id) 
	{
		//로그인 5회 시도시 창 닫기
		int LoginCnt = Util.nvl(Util.getSession(session, "LoginCnt"), 0);
		LoginCnt++;
		Util.setSession(session, "LoginCnt", LoginCnt);
		
		//로그인 실패 카운트 증가
		memberService.updateFailCount(mem_id);
	}
	
	/*
	 * 관리자 로그아웃
	 */
	@RequestMapping("/managerLogout.do")
	public String admLogout(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String usr_id = Util.getSession(session, "USR_ID");
		log_what = String.format("관리자 로그아웃 (%s)", Util.nvl(usr_id, "Session 종료"));
		super.insertLog(request, log_what, "logout");
		
		String path = request.getContextPath()+managerDir;
		
		request.getSession().invalidate();
		model.addAttribute("message", "");
		model.addAttribute("location", "location.href='"+path+"/login.do'");
		
		return "common/scriptAlert";
	}
}
