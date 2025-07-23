package egovframework.dnworks.controller.webcontent.cms.member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/WebContent/cms/member")
public class FindPWController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/findpw.do")
	public String findpw(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String step		= Util.nvl(request.getParameter("step"));

		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("mnu_code"			, "");
		String queryString = super.getParameters(request, udp, true, true);
		String failLocation = String.format("location.href='?%s'", queryString);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("queryString"	, queryString);
		
		if(!step.equals("")) {
			if(Util.getSession(session, "USR_DI").equals("")) {
				model.addAttribute("message", "본인확인이 필요합니다.");
				model.addAttribute("location", failLocation);
				return "common/scriptAlert";
			}
		}
		String pageLocation = null;
		switch(step)
		{
			case "end"	:	
							String location = this.end(request, session, model);
							if(location != null) return location;
							
							pageLocation = "resetPwEnd";
							break;
			case "find" :	pageLocation = "resetPw";
							this.find(request, session, model);
							
							model.addAttribute("step", "end");
							break;
			
			default 	:	pageLocation = "auth";
							model.addAttribute("step", "find");
							break;
		}
		
		return String.format("%s/cms/member/find/%s", publicPath, pageLocation);
	}
	
	private void find(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mem_birth 	= Util.getSession(session, "USR_BIRTH");
		String mem_moblphone= Util.getSession(session, "USR_PHONE");
		
		Map<String, Object> map = new HashMap<>();
		map.put("mem_nm"		, Util.getSession(session, "USR_NM"));
		map.put("mem_birth"		, AESUtil.encrypt(mem_birth));
		map.put("mem_moblphone"	, AESUtil.encrypt(mem_moblphone));

		MemberVo memberVo = memberService.selectMap(map);
		model.addAttribute("memberVo"	, memberVo);
	}
	
	private String end(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mem_id 	= Util.nvl(request.getParameter("mem_id"));
		String mem_pw	= Util.nvl(request.getParameter("mem_pw"));
		String chk_pw	= Util.nvl(request.getParameter("chk_pw"));
		model.addAttribute("mem_id"	, mem_id);
		
		if (EgovDoubleSubmitHelper.checkAndSaveToken(request))	{
			if(!Pattern.matches(PasswordPattern, mem_pw)) {
				model.addAttribute("message", "비밀번호는 숫자, 영문자, 특수문자 조합 8자리 이상 사용해야 합니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			
			if(!mem_pw.equals(chk_pw)) {
				model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}
			MemberVo memberVo = new MemberVo();
			memberVo.setMem_id(mem_id);
			memberVo.setMem_pw(mem_pw);
			int rtnVal = memberService.updatePassword(memberVo);
			model.addAttribute("rtnVal"	, rtnVal);
			
			//본인확인 세션 삭제
			if(rtnVal != -1) super.removeNiceIDSession(session);
		}
		return null;
	}
}
