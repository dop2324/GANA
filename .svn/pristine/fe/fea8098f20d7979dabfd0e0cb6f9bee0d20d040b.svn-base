package egovframework.dnworks.controller.webcontent.cms.member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;

@Controller
public class ExpirePwController extends WebDefault
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BlackListUserService blackListUserService;
	
	@RequestMapping("/{path}/expirepw.do")
	public String expirepw(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		
		String queryString 	= (String) request.getAttribute("queryString");
		String loginUrl 	= Util.nvl(request.getParameter("loginUrl"));
		
		model.addAttribute("path"		, path);
		model.addAttribute("site_code"	, path);
		model.addAttribute("loginUrl"	, loginUrl);
		model.addAttribute("queryString", queryString);
		return String.format("%s/cms/member/login/expirepw", publicPath);
	}
	
	
	@RequestMapping("/{path}/expirepw_process.do")
	public String expirepw_process(@PathVariable("path") String path, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {	
		
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String loginUrl 	= Util.nvl(request.getParameter("loginUrl"));
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String failLocation = String.format("location.href='%s?mnu_code=%s'", returnUrl, mnu_code);
		String nullLocation	= String.format("/%s/main.do", path);
		
		String mem_id 		= Util.getSession(session, "USR_ID");
		String mem_pw		= Util.nvl(request.getParameter("mem_pw"));
		String chg_pw		= Util.nvl(request.getParameter("chg_pw"));
		String chk_pw		= Util.nvl(request.getParameter("chk_pw"));
		int chg_next		= Util.nvl(request.getParameter("chg_next"), 0);
		
		if(chg_next != 1) {
			if(chg_pw.equals("")) {
				model.addAttribute("message", "비밀번호를 입력하여 주십시요");
				model.addAttribute("location", failLocation);
				return "common/scriptAlert";
			}
			
			if(!chg_pw.equals(chk_pw)) {
				model.addAttribute("message", "변경 비밀번호가 일치하지 않습니다");
				model.addAttribute("location", failLocation);
				return "common/scriptAlert";
			}
			
			//비밀번호 체크
			if(!Pattern.matches(PasswordPattern, chg_pw)) {
				model.addAttribute("message", "비밀번호는 영문 숫자 특수기호 조합 ("+minPwLen+"자 이상 "+maxPwLen+"자 이하) 사용해야 합니다.");
				model.addAttribute("location", failLocation);
				return "common/scriptAlert";
			}
		}
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request)) {

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

				model.addAttribute("message", "존재하지 않는 아이디 입니다");
				model.addAttribute("location", failLocation);

				return "common/scriptAlert";
				
			}else {
				
				//다음에 변경
				if(chg_next != 1) {
					//비밀번호 비교
					//20241024 salt추가
					mem_pw = SHA256Util.getSHA256(mem_pw, userVo.getMem_salt());
					
					if(!mem_pw.equals(userVo.getUsr_pw())) {
						super.insertLog(request, String.format("비밀번호 오류 (%s)", mem_id));
	
						model.addAttribute("message", "비밀번호가 일치하지 않습니다");
						model.addAttribute("location", failLocation);
						return "common/scriptAlert"; 
					}

					MemberVo memberVo = new MemberVo();
					memberVo.setMem_id(mem_id);
					memberVo.setMem_pw(chg_pw);
					int rtnVal = memberService.updatePassword(memberVo);
					
					model.addAttribute("message", "비밀번호 변경 되었습니다.");
					model.addAttribute("location", String.format("location.href='/%s/page.do?mnu_code=login'", path));
					
					if(rtnVal == -1) {
						model.addAttribute("message", "비밀번호 변경 중 오류가 발생되었습니다");
						model.addAttribute("location", failLocation);
					}else {
						//비밀번호 만료 다음에 변경 만료 세션 삭제
						session.removeAttribute("EXPIRE_PW");
					}
					
					return "common/scriptAlert";
					
				}else {
					
					//비밀번호 만료 다음에 변경 만료 세션 삭제
					session.removeAttribute("EXPIRE_PW");
					
					returnUrl = String.format("/%s/main.do", path);
					if(!loginUrl.equals("")) {
						returnUrl = loginUrl;
					}
					return "redirect:"+returnUrl;
				}
				
				
			}
		}
		
		return "redirect:"+nullLocation;
	}
	
}
