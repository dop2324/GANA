package egovframework.dnworks.controller.webcontent.cms.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/WebContent/cms/member")
public class InfoController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/memberInfo.do")
	public String memberInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String returnUrl= Util.nvl(request.getParameter("returnUrl"));
		String step		= Util.nvl(request.getParameter("step"));
		String substep	= Util.nvl(request.getParameter("substep"));
		
		model.addAttribute("mnu_code"	, mnu_code);
		
		String location = "";
		String pageLocation = "";
		switch(step)
		{
			case "check" 	:
								location = this.check(request, session, model);
								if(location != null) return location;
								
								switch(substep) {
									case "update" : pageLocation = "update"; break;
								}
								model.addAttribute("step"	, substep);
								break;
			case "secede"	:
								location = this.info(request, session, model);
								if(location != null) return location;
								
								pageLocation = "secede"; 
								model.addAttribute("cmd", Code.Prm.PrmDel.getCode());
								break;
								
			case "update"	:	pageLocation = "check"; 
								model.addAttribute("step"	, "check");
								model.addAttribute("substep", step);
								break;
			
			default 	:	
							location = this.info(request, session, model);
							if(location != null) return location;
							
							pageLocation = "info";
							break;
		}

		return String.format("%s/cms/member/info/%s", publicPath, pageLocation);
	}
	
	private String check(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String substep	= Util.nvl(request.getParameter("substep"));
		String check_pw = Util.nvl(request.getParameter("check_pw"));
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String returnUrl= Util.nvl(request.getParameter("returnUrl"));
		String failLocation = String.format("location.href='%s?mnu_code=%s'", returnUrl, mnu_code);
		
		String location = this.info(request, session, model);
		if(location != null) return location;
		
		MemberVo memberVo = (MemberVo) model.getAttribute("memberVo");
		check_pw = SHA256Util.getSHA256(check_pw, memberVo.getMem_salt());
		
		if(!memberVo.getMem_pw().equals(check_pw)) {
			model.addAttribute("message", "비밀번호가 일치 하지 않습니다");
			model.addAttribute("location", failLocation);
			return "common/scriptAlert";
		}
		
		int cmd = Code.Prm.PrmUpd.getCode();
		switch(substep) {
			case "update" : cmd = Code.Prm.PrmUpd.getCode(); break;
		}
		
		model.addAttribute("cmd", cmd);
		
		return null;
	}
	
	private String info(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mem_id = Util.getSession(session, "USR_ID");
		MemberVo memberVo = memberService.select(mem_id);
		
		if(memberVo == null) {
			model.addAttribute("message", "사용자 정보가 없습니다");
			model.addAttribute("location", "location.href='/'");
			return "common/scriptAlert";
		}
		model.addAttribute("memberVo"		, memberVo);
		
		return null;
	}
	
	@RequestMapping(value = "/update_process.do", method = RequestMethod.POST)
	public String update_process(@ModelAttribute MemberVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		String site_code	= Util.nvl(request.getParameter("site_code"));
		String mnu_code		= Util.nvl(request.getParameter("mnu_code"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String location 	= String.format("location.href='%s?mnu_code=%s'", returnUrl, mnu_code);
		String failLocation = String.format("location.href='%s?mnu_code=%s&step=step'", returnUrl, mnu_code);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			String modelLocation = this.info(request, session, model);
			if(modelLocation != null) return modelLocation;
	
			MemberVo memberVo = (MemberVo) model.getAttribute("memberVo");		
			
			vo.setMem_sttus(memberVo.getMem_sttus());
			vo.setMem_id(Util.getSession(session, "USR_ID"));
			int changePw = Util.nvl(request.getParameter("changePw"), 0);

			if(cmd == Code.Prm.PrmUpd.getCode()) {
				rtnVal = memberService.save(vo, cmd);
				
				if(rtnVal == -1) {
					message = "수정실패 : 처리중 오류가 발생하였습니다.";
					location= failLocation;
				}else {
					if(changePw == 1) {
						memberService.updatePassword(vo);
					}
				}
				
				log_what = String.format("update Member : %s (%s) %s\n회원정보수정\n%s"
										, vo.getMem_id(), vo.getMem_nm()
										, vo.getGrp_id()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				String mem_pw = Util.nvl(request.getParameter("mem_pw"));
				String passSalt = SHA256Util.getSHA256(mem_pw, memberVo.getMem_salt());
				
				if(!memberVo.getMem_pw().equals(passSalt)) {
					model.addAttribute("message", "비밀번호가 일치 하지 않습니다");
					model.addAttribute("location", failLocation);
					return "common/scriptAlert";
				}
				
				rtnVal = memberService.delete(memberVo.getMem_id());
				
				if(rtnVal == -1) {
					message = "실패 : 처리중 오류가 발생하였습니다.";
					location = failLocation;
				}else {
					location = String.format("location.href='%s?mnu_code=logout&logoutUrl=/%s/main.do'", returnUrl, site_code);
				}
				
				log_what = String.format("delete Member : %s (%s) %s\n회원탈퇴\n%s"
										, memberVo.getMem_id(), memberVo.getMem_nm()
										, memberVo.getGrp_id()
										, message
									);
			}
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
