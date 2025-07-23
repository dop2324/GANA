package egovframework.dnworks.controller.webcontent.cms.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.StringUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/WebContent/cms/member")
public class JoinController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/join.do")
	public String join(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code	= Util.nvl(request.getParameter("mnu_code"));
		String step		= Util.nvl(request.getParameter("step"));
		String failLocation = String.format("location.href='%s?mnu_code=%s'", super.servletPath(request), mnu_code);
		
		HashMap<String, Object> udp = new HashMap<String, Object>();
		udp.put("mnu_code"			, "");
		String queryString = super.getParameters(request, udp, true, true);
		
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("queryString"	, queryString);
		
		String pageLocation = null;
		switch(step)
		{
			case "end" :
							pageLocation = "end";
							String mem_id = Util.nvl(request.getParameter("mem_id"));
							MemberVo memberVo = memberService.select(mem_id);
							model.addAttribute("vo", memberVo);
							break;
			case "form"	:	
							if(Util.getSession(session, "USR_DI").equals("")) {
								pageLocation = "auth";
								model.addAttribute("step", "agree");
							}else{
								
								String mem_moblphone = Util.getSession(session, "USR_PHONE");
								
								if(!mem_moblphone.equals("")) {
									mem_moblphone = AESUtil.encrypt(mem_moblphone);
									//휴대전화번호 확인
									int existMoblCnt = memberService.existMobl(mem_moblphone);
									
									if(existMoblCnt > 0) {
										model.addAttribute("message", "이미 가입되어 있는 휴대전화번호 입니다");
										model.addAttribute("location", failLocation);
										return "common/scriptAlert";
									}
								}
								
								pageLocation = "form";
								model.addAttribute("cmd"	, Code.Prm.PrmIns.getCode());
								model.addAttribute("step"	, "end");
							}
							break;
			case "agree" :	pageLocation = "agree";
							model.addAttribute("step", "form");
							break;
			
			default 	:	pageLocation = "auth";
							model.addAttribute("step", "agree");
							break;
		}
		
		return String.format("%s/cms/member/join/%s", publicPath, pageLocation);
	}
	
	/*
	 * 회원 ID 중복 확인
	 */
	@RequestMapping(value="/checkExistID.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String checkExistID(HttpServletRequest request, HttpSession session) throws Exception
	{
		String mem_id = Util.nvl(request.getParameter("mem_id"));
		mem_id = Util.replaceRegex(mem_id,"[+]","");
		mem_id = Util.replaceRegex(mem_id,"[')(]","");
		
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		if(!mem_id.equals("")) {
			int existCnt = memberService.existID(mem_id);
			
			if(existCnt > 0) json.put("result", 1);
		}
		
		return json.toString();
	}
	
	@RequestMapping(value = "/join_process.do", method = RequestMethod.POST)
	public String join_process(@ModelAttribute MemberVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String step			= Util.nvl(request.getParameter("step"));
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		String failLocation = String.format("location.href='%s?%s&step=form'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMem_sttus(1);
			
			int changePw = Util.nvl(request.getParameter("changePw"), 0);
			
			//비밀번호 암호화
			if(cmd == Code.Prm.PrmIns.getCode()) {

				//비밀번호 체크
				if(!Pattern.matches(PasswordPattern, vo.getMem_pw())) {
					model.addAttribute("message", "비밀번호는 영문 숫자 특수기호 조합 (8자 이상 20자 이하) 사용해야 합니다.");
					model.addAttribute("location", failLocation);
					return "common/scriptAlert";
				}

				//20241024 salt추가
				vo.setMem_salt(SHA256Util.getSalt());
				vo.setMem_pw(SHA256Util.getSHA256(vo.getMem_pw(), vo.getMem_salt()));
			}
			
			//그룹정보 (일반회원)
			String grp_id = Util.nvl(request.getParameter("grp_id"), "GRP_004_01");
			vo.GroupMember().setGrp_id(grp_id);
			vo.GroupMember().setMem_id(vo.getMem_id());
			vo.GroupMember().setGmb_mdfcnID(StringUtil.substringVarU3(Util.getSession(session, "USR_ID"), 64));
			
			if(cmd == Code.Prm.PrmIns.getCode())
			{
				boolean insertFlag = true;
				
				//문자열에 공백 혹은 특수문자가 입력된 경우
				if(!Pattern.matches(IDpattern, vo.getMem_id())) {
				  	insertFlag = false;
				  	message = "ID는 특수문자 또는 공백을 사용할수 없습니다";
					location = "history.back();";
				}
				
				if(Arrays.asList(NOT_alwID).contains(vo.getMem_id())) {
					insertFlag = false;
					message = "사용 불가능한 ID 입니다.";
					location = "history.back();";
				}

				if(insertFlag) {
					location 	= String.format("location.href='%s?%smem_id=%s&step=%s'", returnUrl, queryString, vo.getMem_id(), step);
					
					rtnVal = memberService.save(vo, cmd);
					
					if(rtnVal == -2) {
						message = "입력한 ID가 존재 합니다.";
						location= failLocation;
					}else if(rtnVal == -3) {
						message = "입력한 이메일 주소가 존재 합니다.";
						location= failLocation;
					}else if(rtnVal == -4) {
						message = "입력한 휴대전화번호가 존재 합니다.";
						location= failLocation;
					}else if(rtnVal == -1) {
						message = "입력실패 : 처리중 오류가 발생하였습니다.";
						location= failLocation;
					}
					
					if(rtnVal != -1) {
						//세션 삭제
						request.getSession().invalidate();
					}
					
				}
				
				log_what = String.format("insert Member : %s (%s) %s\n회원가입\n%s"
										, vo.getMem_id(), vo.getMem_nm()
										, vo.getGrp_id()
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
