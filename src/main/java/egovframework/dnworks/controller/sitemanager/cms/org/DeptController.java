package egovframework.dnworks.controller.sitemanager.cms.org;

import java.util.HashMap;
import java.util.List;

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
import egovframework.dnworks.cms.org.service.DeptService;
import egovframework.dnworks.cms.org.service.DeptVo;

@Controller
@RequestMapping("/SiteManager/cms/org/dept")
public class DeptController extends WebDefault 
{
	@Autowired
	private DeptService deptService;
	
	/*
	 * 부서관리
	 */
	@RequestMapping("/dept.do")
	public String dept(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		
		String dept_id 		= Util.nvl(request.getParameter("dept_id"));
		String upper_deptID	= Util.nvl(request.getParameter("upper_deptID"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"			, "");
		udp.put("frmTy"				, "");
		udp.put("dept_id"			, "");
		udp.put("upper_deptID"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("dept_id"		, dept_id);
		model.addAttribute("upper_deptID"	, upper_deptID);

		return managerDir+"/cms/org/dept/dept";
	}
	
	@RequestMapping("/dept_tree.do")
	public String dept_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String dept_id 	= Util.nvl(request.getParameter("dept_id"));
		List<DeptVo> deptTree = deptService.selectTree(null);
		
		model.addAttribute("deptTree"	, deptTree);
		model.addAttribute("dept_id"	, dept_id);
		
		return managerDir+"/cms/org/dept/dept_tree";
	}
	
	@RequestMapping("/dept_form.do")
	public String group_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String dept_id 		= Util.nvl(request.getParameter("dept_id"));
		String upper_deptID	= Util.nvl(request.getParameter("upper_deptID"));
		int cmd 		 	= 0;

		DeptVo vo = null;
		if(!dept_id.equals("")) {
			cmd = Code.Prm.PrmUpd.getCode();
			vo = deptService.select(dept_id);

		} else if(!upper_deptID.equals("")) {
			cmd = Code.Prm.PrmIns.getCode();
			vo = deptService.select(upper_deptID);

			vo.setDept_id(dept_id);
			vo.setUpper_deptID(upper_deptID);
			vo.setDept_nm("");
			vo.setDept_fullNm("");
		}

		model.addAttribute("vo"			, vo);
		model.addAttribute("cmd"		, cmd);
		
		return managerDir+"/cms/org/dept/dept_form";
	}
	
	@RequestMapping("/dept_process.do")
	public String dept_process(@ModelAttribute DeptVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);

		int rtnVal 		= -1;
		String message 	= "";
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				DeptVo selVo = deptService.select(vo.getDept_id());
				
				if(cmd == Code.Prm.PrmIns.getCode() && selVo != null) {
					model.addAttribute("message", "부서 ID 중복 입니다");
					model.addAttribute("location", location);
					return "common/scriptAlert";
				}
				
				rtnVal = deptService.save(vo);
				if(rtnVal == -1) {
					message 	= "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Dept : %s (%s), status : %s\n%s"
											, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
											, vo.getDept_nm()
											, vo.getDept_id()
											, vo.getDept_useYn()
											, message
										);
				
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				rtnVal = deptService.delete(vo.getDept_id());
				
				if(rtnVal == -2) {
					message = "삭제실패 : 하위 부서가 존재 합니다.";
				}else if(rtnVal == -3) {
					message = "삭제실패 : 부서 직원이 존재 합니다.";
				}else if(rtnVal == 0){
					message = "삭제실패 : 삭제 처리 중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Dept : %s (%s)\n%s"
											, vo.getDept_nm()
											, vo.getDept_id()
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
