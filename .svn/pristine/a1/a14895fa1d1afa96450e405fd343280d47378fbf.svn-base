package egovframework.dnworks.controller.sitemanager.cms.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.GroupService;
import egovframework.dnworks.cms.member.service.GroupVo;

@Controller
@RequestMapping("/SiteManager/cms/member/group")
public class GroupController extends WebDefault
{
	@Autowired
	private GroupService groupService;
	
	/*
	 * 그룹관리
	 */
	@RequestMapping("/group.do")
	public String group(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		
		String parm_grpId 	= Util.nvl(request.getParameter("parm_grpId"));
		String parm_grpUprId= Util.nvl(request.getParameter("parm_grpUprId"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"			, "");
		udp.put("frmTy"				, "");
		udp.put("parm_grpId"		, "");
		udp.put("parm_uprGrpId"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("parm_grpId"		, parm_grpId);
		model.addAttribute("parm_grpUprId"	, parm_grpUprId);

		return managerDir+"/cms/member/group/group";
		
	}
	
	@RequestMapping("/groupFrame.do")
	public String groupFrame(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		return managerDir+"/cms/member/group/groupFrame";	
	}
	
	@RequestMapping("/group_tree.do")
	public String group_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String parm_grpId 	= Util.nvl(request.getParameter("parm_grpId"));
		List<GroupVo> groupTree = groupService.selectTree(null);
		
		model.addAttribute("groupTree"	, groupTree);
		model.addAttribute("parm_grpId"	, parm_grpId);
		
		return managerDir+"/cms/member/group/group_tree";
		
	}
	
	@RequestMapping("/group_form.do")
	public String group_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String grp_id 		= Util.nvl(request.getParameter("parm_grpId"));
		String grp_uprId	= Util.nvl(request.getParameter("parm_uprGrpId"));
		int cmd 		 	= 0;

		GroupVo vo = null;
		if(!grp_id.equals("")) {
			cmd = Code.Prm.PrmUpd.getCode();
			vo = groupService.select(grp_id);

		} else if(!grp_uprId.equals("")) {
			cmd = Code.Prm.PrmIns.getCode();
			vo = groupService.select(grp_uprId);

			vo.setGrp_id(grp_id);
			vo.setGrp_uprID(grp_uprId);
			vo.setGrp_nm("");
			vo.setGrp_desc("");
		}

		model.addAttribute("vo"			, vo);
		model.addAttribute("cmd"		, cmd);
		
		return managerDir+"/cms/member/group/group_form";
	}
	

	@RequestMapping("/group_process.do")
	public String group_process(@ModelAttribute GroupVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String updn 		= Util.nvl(request.getParameter("updn"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);

		vo.setGrp_mdfcnID(Util.getSession(session, "USR_ID"));

		int rtnVal 		= -1;
		String message 	= "";
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				//중복확인
				GroupVo selVo = groupService.select(vo.getGrp_id());
				if(cmd == Code.Prm.PrmIns.getCode() && selVo != null) {
					model.addAttribute("message", "그룹 ID 중복 입니다");
					model.addAttribute("location", location);
					return "common/scriptAlert";
				}
				
				rtnVal = groupService.save(vo);
				if(rtnVal == -1) {
					message 	= "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Group : %s (%s), status : %s\n%s"
											, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
											, vo.getGrp_nm()
											, vo.getGrp_id()
											, vo.getGrp_sttus()
											, message
										);
				
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				rtnVal = groupService.delete(vo.getGrp_id());
				
				if(rtnVal == -2) {
					message = "삭제실패 : 하위 그룹이 존재 합니다.";
				}else if(rtnVal == -3) {
					message = "삭제실패 : 그룹에 회원이 존재 합니다.";
				}else if(rtnVal == 0){
					message = "삭제실패 : 삭제 처리 중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Group : %s (%s)\n%s"
											, vo.getGrp_nm()
											, vo.getGrp_id()
											, message
										);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode())
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("grp_id"	, vo.getGrp_id());
				map.put("UPDN"		, updn);
				rtnVal = groupService.setOrder(map);
				if(rtnVal == -1) {
					message 	= "순서조정 : 처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("setOrder Group : %s (%s) %s\n%s"
											, vo.getGrp_nm()
											, vo.getGrp_id()
											, updn
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
