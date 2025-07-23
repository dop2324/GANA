package egovframework.dnworks.controller.sitemanager.cms.member;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.cipher.SHA256Util;
import egovframework.dnworks.cmm.util.StringUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.GroupMemberService;
import egovframework.dnworks.cms.member.service.GroupMemberVo;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/SiteManager/cms/member")
public class MemberController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GroupMemberService groupMemberService;
	
	private String[] NOT_alwID = {"manager","admin","master","administrator"};
	
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
	
	/*
	 * 회원 login fail cnt 초기화
	 */
	@RequestMapping(value="/initLoginFailCnt.do", method = RequestMethod.POST, produces = "text/json; charset=UTF-8")
	public @ResponseBody String initLoginFailCnt(HttpServletRequest request, HttpSession session) throws Exception
	{
		String mem_id = Util.nvl(request.getParameter("mem_id"));
		int rtnVal = memberService.initLoginFailCnt(mem_id);
		
		JSONObject json = new JSONObject();
		json.put("result", 0);
		
		if(rtnVal != -1) {
			json.put("result", 1);
		}
		
		return json.toString();
	}
	
	
	/*
	 * 회원관리
	 */
	@RequestMapping("/member.do")
	public String member(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		//page.jsp 넘긴 param
		//super.getPageImportParam(request, session, model);
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		if(defaultSiteMap != null) defaultSite = (String) defaultSiteMap.get("defaultSite");
		
		String site_code = Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("srchGroup"		, "");
		udp.put("srchKwd"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink = String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		
		return managerDir+"/cms/member/member";
	}
	
	@RequestMapping("/initMember.do")
	public String initMember(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location = null;
		String site_code= Util.nvl(request.getParameter("site_code"));
		String srchGroup= Util.nvl(request.getParameter("srchGroup"));
		
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);
		int pageNo 		= Util.nvl(request.getParameter("pageNo"), 20);
		
		String queryString	= (String) request.getAttribute("queryString");	
		String listLink 	= String.format("?%s", queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("srchGroup"		, srchGroup);
		
		model.addAttribute("cmd"			, cmd);
		model.addAttribute("pageNo"			, pageNo);
		
		model.addAttribute("queryString"	, queryString);
		model.addAttribute("listLink"		, listLink);
		
		switch(cmd)
		{
			case 2 :
					this.select(request, session, model, queryString);			
					location = managerDir+"/cms/member/member_view";
					break;
			case 16	:
			case 4 :
					if(cmd == 4 && (srchGroup.equals("") || srchGroup.equals("ROOT"))) {
						model.addAttribute("message", "그룹을 선택하여 주십시요");
						model.addAttribute("location", "history.back()");
						return "common/scriptAlert";
					}
					this.select(request, session, model, queryString);			
					location = managerDir+"/cms/member/member_form";
					break;
			default :			
					this.list(request, session, model, queryString);			
					location = managerDir+"/cms/member/member_list";
					break;
		}
		return location;
	}
	
	/* list */
	public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String srchSttus 	= Util.nvl(request.getParameter("srchSttus"));
		String srchGroup 	= Util.nvl(request.getParameter("srchGroup"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchGroup"		, srchGroup);
		map.put("srchSttus"		, srchSttus);
		map.put("srchKwd"		, srchKwd);
		
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = memberService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MemberVo> memberList = memberService.selectList(map);
		
		model.addAttribute("no"			, no);
		model.addAttribute("pageNo"		, pageNo);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("memberList"	, memberList);
		model.addAttribute("srchMap"	, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
		
		log_what = String.format("회원 목록조회\n%s", !srchGroup.equals("") ? srchGroup : "전체");
		super.insertLog(request, log_what);
	}
	
	/* select */
	public void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		String mem_id 	= Util.nvl(request.getParameter("mem_id"));
		int cmd			= !mem_id.equals("") ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		MemberVo vo = null;
		if(cmd == Code.Prm.PrmUpd.getCode())
		{
			vo = memberService.select(mem_id);
			
			log_what = String.format("회원 상세 조회\n%s %s", vo.getMem_id(), vo.getGrp_nm());
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
	}
	
	@RequestMapping(value = "/member_process.do", method = RequestMethod.POST)
	public String member_process(@ModelAttribute MemberVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		String faillocation = String.format("location.href='%s?%s&cmd=%s'", returnUrl, queryString, cmd);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			int changePw = Util.nvl(request.getParameter("changePw"), 0);
			
			//비밀번호 암호화
			if(cmd == Code.Prm.PrmIns.getCode()) {
				//비밀번호 체크
				if(!Pattern.matches(PasswordPattern, vo.getMem_pw())) {
					model.addAttribute("message", "비밀번호는 영문 숫자 특수기호 조합 ("+minPwLen+"자 이상 "+maxPwLen+"자 이하) 사용해야 합니다.");
					model.addAttribute("location", faillocation);
					return "common/scriptAlert";
				}
				//20241024 salt추가
				vo.setMem_salt(SHA256Util.getSalt());
				vo.setMem_pw(SHA256Util.getSHA256(vo.getMem_pw(), vo.getMem_salt()));
			}
			
			//그룹정보
			String grp_id = Util.nvl(request.getParameter("grp_id"), "GRP_004");
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
					location = faillocation;
				}
				
				if(Arrays.asList(NOT_alwID).contains(vo.getMem_id())) {
					insertFlag = false;
					message = "사용 불가능한 ID 입니다.";
					location = faillocation;
				}

				if(insertFlag) {
					rtnVal = memberService.save(vo, cmd);
					
					if(rtnVal == -2) {
						message = "입력한 ID가 존재 합니다.";
						location = faillocation;
					}else if(rtnVal == -3) {
						message = "입력한 이메일 주소가 존재 합니다.";
						location = faillocation;
					}else if(rtnVal == -4) {
						message = "입력한 휴대전화번호가 존재 합니다.";
						location = faillocation;
					}else if(rtnVal == -1) {
						message = "입력실패 : 처리 중 오류가 발생하였습니다.";
						location = faillocation;
					}
				}
				
				log_what = String.format("insert Member : %s (%s), %s status : %s\n%s"
										, vo.getMem_id(), vo.getMem_nm()
										, vo.getGrp_id()
										, vo.getMem_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmUpd.getCode() )
			{
				rtnVal = memberService.save(vo, cmd);
				
				if(rtnVal == -1) {
					message = "수정실패 : 처리 중 오류가 발생하였습니다.";
				}else {
					//비밀번호 업데이트
					if(changePw == 1) {
						memberService.updatePassword(vo);
					}
				}
				
				log_what = String.format("update Member : %s (%s), %s status : %s\n%s"
										, vo.getMem_id(), vo.getMem_nm()
										, vo.getGrp_id()
										, vo.getMem_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				MemberVo memVo = memberService.select(vo.getMem_id());
				rtnVal = memberService.delete(vo.getMem_id());
				
				if(rtnVal == -1) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("delete Member : %s (%s), %s(%s)\n%s"
										, memVo.getMem_id(), memVo.getMem_nm()
										, memVo.getGrp_nm(), memVo.getGrp_id()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode())
			{
				MemberVo memVo = memberService.select(vo.getMem_id());
				
				GroupMemberVo groupUserVo = new GroupMemberVo();
				groupUserVo.setGrp_id(grp_id);
				groupUserVo.setMem_id(vo.getMem_id());
				groupUserVo.setGmb_mdfcnID(Util.getSession(session, "USR_ID"));
				
				rtnVal = groupMemberService.update(groupUserVo);
				if(rtnVal == -1) {
					message 	= "회원그룹변경 : 처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("그룹변경 Member : %s (%s), %s(%s) -> %s\n%s"
										, memVo.getMem_id(), memVo.getMem_nm()
										, memVo.getGrp_nm(), memVo.getGrp_id()
										, groupUserVo.getGrp_id()
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
