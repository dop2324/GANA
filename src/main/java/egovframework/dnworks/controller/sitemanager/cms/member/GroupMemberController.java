package egovframework.dnworks.controller.sitemanager.cms.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.GroupService;
import egovframework.dnworks.cms.member.service.GroupVo;
import egovframework.dnworks.cms.member.service.MemberService;
import egovframework.dnworks.cms.member.service.MemberVo;

@Controller
@RequestMapping("/SiteManager/cms/member")
public class GroupMemberController extends WebDefault
{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GroupService groupService;
	
	/*
	 * 회원선택
	 */
	@RequestMapping("/groupMember.do")
	public String groupMember(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));
		String grp_id 	= Util.nvl(request.getParameter("srchGroup"));
		
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
		
		GroupVo groupVo = groupService.select(grp_id);
		model.addAttribute("groupVo", groupVo);

		model.addAttribute("mnu_code"		, mnu_code);
		
		return managerDir+"/cms/member/groupMember";
	}
	
	@RequestMapping("/groupMember_list.do")
	public String groupMember_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		
		String queryString	= (String) request.getAttribute("queryString");	
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);

		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));

		String srchGroup 	= Util.nvl(request.getParameter("srchGroup"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchGroup"		, srchGroup);
		map.put("srchSttus"		, 1);
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
		
		return managerDir+"/cms/member/groupMember_list";
	}
}
