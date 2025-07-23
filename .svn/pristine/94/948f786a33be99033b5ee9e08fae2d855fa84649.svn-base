package egovframework.dnworks.controller.sitemanager.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Controller
@RequestMapping("/SiteManager")
public class ManagerExpirePwController extends WebDefault
{
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	@Value("${Globals.AdminPath}")
	protected String managerDir;
	
	/*
	 * 비밀번호 만료
	 */
	@RequestMapping(value = {"/expirepw.do"})
	public String expirepw(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		
		if(base.get("siteVo") == null) {
			return "/error/error404";
		}
		
		return managerDir+"/login/expirepw";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/expirepw_process.do")
	public String expirepw_process(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> base = webBaseService.webBase(request, session);
		SiteVo siteVo = (SiteVo) base.get("siteVo");
		List<String> prmAdmMenuList = (List<String>) base.get("prmAdmMenuList");
		
		String mem_id 	= Util.getSession(session, "USR_ID");
		String mem_pass	= Util.nvl(request.getParameter("user_password"));
		
		String chg_pw	= Util.nvl(request.getParameter("chg_pw"));
		String chk_pw	= Util.nvl(request.getParameter("chk_pw"));
		int chg_next	= Util.nvl(request.getParameter("chg_next"), 0);
		
		if(chg_next != 1) {
			if(chg_pw.equals("")) {
				model.addAttribute("message", "비밀번호를 입력하여 주십시요");
				model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
				return "common/scriptAlert";
			}
			
			if(!chg_pw.equals(chk_pw)) {
				model.addAttribute("message", "변경 비밀번호가 일치하지 않습니다");
				model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
				return "common/scriptAlert";
			}
			
			//비밀번호 체크
			if(!Pattern.matches(PasswordPattern, chg_pw)) {
				model.addAttribute("message", "비밀번호는 영문 숫자 특수기호 조합 (8자 이상 20자 이하) 사용해야 합니다.");
				model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
				return "common/scriptAlert";
			}
		}
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			Map<String, Object> bluMap = new HashMap<>();
			bluMap.put("blu_id"		, mem_id);
			bluMap.put("site_code"	, managerDir);
			int isblackListUser = blackListUserService.isBlackListUser(bluMap);
			if(isblackListUser > 0) {
				super.insertBlockLog(request, mem_id, "차단 설정 된 아이디");
				model.addAttribute("message", "차단 설정 된 아이디 입니다\\n관리자에게 문의 바랍니다.");
				model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
				return "common/scriptAlert";
			}
			
			//로그인
			Map<String, Object> map = new HashMap<>();
			map.put("site_code"	, siteVo.getSite_code());
			map.put("mem_id"	, mem_id);

			UserVo userVo = userService.login(map);
			
			if(userVo == null) {
				log_what = String.format("없는 ID 비밀번호 만료 변경 시도 (%s)", mem_id);
				super.insertLog(request, log_what);
				
				model.addAttribute("message", "존재하지 않는 아이디 입니다");
				model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
				return "common/scriptAlert";
			}
			else
			{			
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
					log_what = String.format("관리자 비밀번호 만료 변경 관리 권한이 없는 사용자 (%s)", mem_id);
					super.insertLog(request, log_what);
					
					model.addAttribute("message", "관리 권한이 없는 사용자 입니다");
					model.addAttribute("location", String.format("location.href='%s/login.do'", managerDir));
	
					return "common/scriptAlert";
				}
				else
				{
					//다음에 변경
					if(chg_next != 1) {
						
						//비밀번호 비교
						//20241024 salt추가
						mem_pass = SHA256Util.getSHA256(mem_pass, userVo.getMem_salt());
						
						if(!mem_pass.equals(userVo.getUsr_pw())) {
							log_what = String.format("관리자 비밀번호 만료 변경 비밀번호 오류 (%s)", mem_id);
							super.insertLog(request, log_what);
							
							model.addAttribute("message", "현재 비밀번호가 일치하지 않습니다.");
							model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
							
							return "common/scriptAlert"; 
						}
						
						MemberVo memberVo = new MemberVo();
						memberVo.setMem_id(mem_id);
						memberVo.setMem_pw(SHA256Util.getSHA256(chg_pw));
						int rtnVal = memberService.updatePassword(memberVo);
						
						model.addAttribute("message", "비밀번호 변경 되었습니다.");
						model.addAttribute("location", String.format("location.href='%s/managerLogout.do'", managerDir));
						
						if(rtnVal == -1) {
							model.addAttribute("message", "비밀번호 변경 중 오류가 발생되었습니다");
							model.addAttribute("location", String.format("location.href='%s/expirepw.do'", managerDir));
						}else {
							//비밀번호 만료 다음에 변경 만료 세션 삭제
							session.removeAttribute("EXPIRE_PW");
						}
						
						return "common/scriptAlert";
						
					}else {
						
						//비밀번호 만료 다음에 변경 만료 세션 삭제
						session.removeAttribute("EXPIRE_PW");
						
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
						
						return "redirect:"+managerDir+"/page.do?mnu_code="+loginMenuCode; 
					}

				}
			}
			
		}
		return null;
	}
}
