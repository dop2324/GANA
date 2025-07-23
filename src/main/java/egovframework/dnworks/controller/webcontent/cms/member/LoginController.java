package egovframework.dnworks.controller.webcontent.cms.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.session.SessionConfig;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;
import egovframework.dnworks.cms.stats.service.SessionService;
import egovframework.dnworks.cms.stats.service.SessionVo;

@Controller
@RequestMapping("/WebContent/cms/member")
public class LoginController extends WebDefault
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String loginUrl = Util.nvl(request.getParameter("loginUrl"), "/");
		model.addAttribute("mnu_code"	, mnu_code);
		model.addAttribute("loginUrl"	, loginUrl);
		
		return String.format("%s/cms/member/login/login", publicPath);
	}
	
	/*
	 * 로그아웃
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		String logoutUrl = Util.nvl(request.getParameter("logoutUrl"), "/");
		log_what = String.format("로그아웃 (%s)", Util.getSession(session, "USR_ID"));
		super.insertLog(request, log_what, "logout");
		request.getSession().invalidate();
		model.addAttribute("message", "");
		model.addAttribute("location", "location.href='"+logoutUrl+"'");
		
		return "common/scriptAlert";			
	}
	
	@RequestMapping("/login_process.do")
	public String login_process(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {	
		int LoginCnt 		= Util.nvl(Util.getSession(session, "LoginCnt"), 0);
		String mem_id 		= Util.nvl(request.getParameter("mem_id"));
		String mem_pw		= Util.nvl(request.getParameter("mem_pw"));
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String loginUrl 	= Util.nvl(request.getParameter("loginUrl"));
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String failLocation = String.format("location.href='%s?mnu_code=login'", returnUrl);
		
		String nullLocation	= String.format("/%s/main.do", site_code);
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request)) {
			if(Util.getSession(session, "LoginCnt").equals("")) {
				Util.setSession(session, "LoginCnt", 0);
			}
			Map<String, Object> bluMap = new HashMap<>();
			bluMap.put("blu_id"		, mem_id);
			bluMap.put("site_code"	, site_code);
			int isblackListUser = blackListUserService.isBlackListUser(bluMap);
			if(isblackListUser > 0) {
				super.insertBlockLog(request, mem_id, "차단 설정 된 아이디");
				model.addAttribute("message", "차단 설정 된 아이디 입니다\\n관리자에게 문의 바랍니다.");
				model.addAttribute("location", failLocation);
				return "common/scriptAlert";
			}
			
			//로그인
			Map<String, Object> map = new HashMap<>();
			map.put("mem_id"	, mem_id);
			UserVo userVo = userService.login(map);
			
			if(userVo == null) {
				log_what = String.format("없는 ID 로그인 시도 (%s)", mem_id);
				super.insertLog(request, log_what);

				if(LoginCnt > 4) {
					//차단 ip등록
					super.saveBlackListIP(request, site_code, String.format("(%s) 로그인 5회 실패 IP 차단", mem_id), mem_id);
					model.addAttribute("message", "로그인 5회 실패 IP 차단 되었습니다");
					model.addAttribute("location", failLocation);
				}else{
					model.addAttribute("message", "아이디 및 비밀번호를 확인해주세요");
					model.addAttribute("location", failLocation);
				}
				return "common/scriptAlert";
			}else {
				
				//비밀번호 비교
				//20241024 salt추가
				mem_pw = SHA256Util.getSHA256(mem_pw, userVo.getMem_salt());

				if(!mem_pw.equals(userVo.getUsr_pw())) {
					super.insertLog(request, String.format("비밀번호 오류 (%s)", mem_id));
					//로그인 실패 카운트 증가
					this.updateFailCnt(session, mem_id);
					
					if(LoginCnt > 4) {
						//차단 ID등록
						super.saveBlackListUser(request, site_code, mem_id, userVo.getUsr_nm(), String.format("(%s) 로그인 5회 실패 ID 차단", mem_id));
						//회원 비활성 mem_sttus = 0
						memberService.updateDisable(mem_id);
						model.addAttribute("message", "로그인 5회 실패 아이디 차단 되었습니다");
						model.addAttribute("location", failLocation);
					}else {
						model.addAttribute("message", "아이디 및 비밀번호를 확인해주세요");
						model.addAttribute("location", failLocation);
					}
					return "common/scriptAlert"; 
				}
				
				if(userVo.getUsr_sttus() == 0) {
					model.addAttribute("message", "계정 사용중지 되어있습니다\\n관리자에게 문의 바랍니다.");
					model.addAttribute("location", failLocation);
					return "common/scriptAlert"; 
				}

				//중복로그인 방지
				String CHKDUP_ID = String.format("USER_%s", mem_id);
				String DPCN = "";
				DPCN = SessionConfig.getSessionidCheck("CHKDUP_ID", CHKDUP_ID);
				if(!DPCN.equals("")) {
					super.insertLog(request, String.format("%s 중복 로그인 삭제\n(%s)", mem_id, DPCN));
				}
				
				//접속수 증가, 로그인 실패 초기화
				memberService.updateJoinCount(mem_id);
				memberService.initLoginFailCnt(mem_id);
				
				//세션 생성
				int sessionTime = Util.nvl(EgovProperties.getProperty("Globals.sessionTime"), 10);
				session.setMaxInactiveInterval(sessionTime*60);	// 30분
				Util.setSession(session, "USR_ID"		, userVo.getUsr_id());
				Util.setSession(session, "USR_NM"		, userVo.getUsr_nm());
				Util.setSession(session, "GRP_ID"		, userVo.getGrp_id());
				Util.setSession(session, "GRP_NM"		, userVo.getGrp_nm());
				Util.setSession(session, "USR_MAIL"		, userVo.getUsr_mail());
				Util.setSession(session, "USR_DI"		, userVo.getUsr_id());
				
				//Util.setSession(session, "USR_PARAM"	, userVo.getUsr_param());
				//Util.setSession(session, "CHKDUP_ID"	, CHKDUP_ID);
				if(!Util.nvl(userVo.getDpt_id()).equals("")) Util.setSession(session, "DPT_ID"		, userVo.getDpt_id());
				if(!Util.nvl(userVo.getDpt_nm()).equals("")) Util.setSession(session, "DPT_NM"		, userVo.getDpt_nm());
				
				//접속정보 업데이트
				SessionVo sessionVo = new SessionVo();
				sessionVo.setSes_id(session.getId());
				sessionVo.setSes_sesID(userVo.getUsr_id());
				sessionService.update(sessionVo);
				super.insertLog(request, String.format("로그인 성공 (%s)", mem_id), "login");
				
				//비밀번호 만료일 체크
				int expirePwDt = Util.nvl(EgovProperties.getProperty("Globals.expirePwDt"), 180);
				if(expirePwDt <= userVo.getMem_chgPwDtDiff()) {
					
					String expirepwUrl = String.format("/%s/expirepw.do?loginUrl=%s", site_code, Util.encodeURI(loginUrl));
					Util.setSession(session, "EXPIRE_PW", "true");
					
					model.addAttribute("message", "비밀번호 사용기한이 만료 되었습니다");
					model.addAttribute("location", String.format("location.href='%s'", expirepwUrl));
					return "common/scriptAlert";
				}
				
				//리턴주소가 로그인 페이지면 메인 페이지로
				if(loginUrl.indexOf("mnu_code=login") > -1) {
					loginUrl = String.format("/%s/main.do", site_code);
				}
				
				return "redirect:"+loginUrl;
			}
		}
		
		return "redirect:"+nullLocation;
	}
	
	private void updateFailCnt(HttpSession session, String mem_id) {
		//로그인 5회 시도시 창 닫기
		int LoginCnt = Util.nvl(Util.getSession(session, "LoginCnt"), 0);
		LoginCnt++;
		Util.setSession(session, "LoginCnt", LoginCnt);
		
		//로그인 실패 카운트 증가
		memberService.updateFailCount(mem_id);
	}
}
