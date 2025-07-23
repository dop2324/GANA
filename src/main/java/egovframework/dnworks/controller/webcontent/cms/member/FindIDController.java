package egovframework.dnworks.controller.webcontent.cms.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.MaskUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/WebContent/cms/member")
public class FindIDController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/findid.do")
	public String findid(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String step		= Util.nvl(request.getParameter("step"));

		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("mnu_code"			, "");
		String queryString = super.getParameters(request, udp, true, true);
		String failLocation = String.format("location.href='?%s'", queryString);
		
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("queryString"	, queryString);
		
		String pageLocation = null;
		switch(step)
		{
			case "find" :	
							if(Util.getSession(session, "USR_DI").equals("")) {
								model.addAttribute("message", "본인확인이 필요합니다.");
								model.addAttribute("location", failLocation);
								return "common/scriptAlert";
							}
							pageLocation = "resultId";
							this.find(request, session, model);
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
		if(memberVo != null) {
			memberVo.setMem_id(MaskUtil.getMaskid(memberVo.getMem_id()));
		}
		model.addAttribute("memberVo"	, memberVo);
	}
}
