package egovframework.dnworks.controller.sitemanager.cms.org;

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
import egovframework.dnworks.cms.org.service.DeptService;
import egovframework.dnworks.cms.org.service.DeptVo;
import egovframework.dnworks.cms.org.service.EmpService;
import egovframework.dnworks.cms.org.service.EmpVo;

@Controller
@RequestMapping("/SiteManager/cms/org/emp")
public class EmpController extends WebDefault 
{
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private EmpService empService;
	
	/*
	 * 직원
	 */
	@RequestMapping("/emp.do")
	public String emp(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		
		String dept_id 		= Util.nvl(request.getParameter("dept_id"));
		String upper_deptID	= Util.nvl(request.getParameter("upper_deptID"));

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"			, "");
		udp.put("dept_id"			, "");
		udp.put("upper_deptID"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("dept_id"		, dept_id);
		model.addAttribute("upper_deptID"	, upper_deptID);

		return managerDir+"/cms/org/emp/emp";
	}
	
	@RequestMapping("/initEmp.do")
	public String initEmp(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location = null;
		String dept_id	= Util.nvl(request.getParameter("dept_id"));
		
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);
		int pageNo 		= Util.nvl(request.getParameter("pageNo"), 1);
		
		String queryString	= (String) request.getAttribute("queryString");	
		String listLink 	= String.format("?%s", queryString);
		
		model.addAttribute("dept_id"		, dept_id);
		model.addAttribute("cmd"			, cmd);
		model.addAttribute("queryString"	, queryString);
		model.addAttribute("listLink"		, listLink);
		
		switch(cmd)
		{
			case 16	:
			case 4 :
					this.select(request, session, model, queryString);			
					location = managerDir+"/cms/org/emp/emp_form";
					break;
			default :			
					this.list(request, session, model, queryString);			
					location = managerDir+"/cms/org/emp/emp_list";
					break;
		}
		return location;
	}
	
	/* list */
	public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		String dept_id	= Util.nvl(request.getParameter("dept_id"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));

		Map<String, Object> map = new HashMap<>();
		map.put("dept_id", dept_id);
		map.put("srchKwd", srchKwd);
		List<EmpVo> empList = empService.selectList(map);
		
		model.addAttribute("srchMap"	, map);
		model.addAttribute("empList"	, empList);
		
		log_what = String.format("직원 목록조회\n%s", !dept_id.equals("") ? dept_id : "전체");
		super.insertLog(request, log_what);
	}
	
	/* select */
	public void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{	
		String dept_id	= Util.nvl(request.getParameter("dept_id"));
		String emp_id 	= Util.nvl(request.getParameter("emp_id"));
		int cmd			= !emp_id.equals("") ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		DeptVo deptVo = deptService.select(dept_id);
		
		EmpVo vo = null;
		if(cmd == Code.Prm.PrmUpd.getCode())
		{
			vo = empService.select(emp_id);
			
			log_what = String.format("직원 상세 조회\n%s %s", vo.getEmp_nm(), vo.getDept_nm());
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("deptVo"		, deptVo);
		model.addAttribute("vo"			, vo);
		model.addAttribute("cmd"		, cmd);
	}
	
	@RequestMapping("/emp_process.do")
	public String emp_process(@ModelAttribute EmpVo vo, BindingResult bindingResult
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
				EmpVo selVo = empService.select(vo.getEmp_id());
				
				if(cmd == Code.Prm.PrmIns.getCode() && selVo != null) {
					model.addAttribute("message", "직원 ID 중복 입니다");
					model.addAttribute("location", location);
					return "common/scriptAlert";
				}
				
				rtnVal = empService.save(vo);
				if(rtnVal == -1) {
					message 	= "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Emp : %s (%s), status : %s\n%s"
											, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
											, vo.getEmp_nm()
											, vo.getEmp_id()
											, vo.getEmp_useYn()
											, message
										);
				
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				rtnVal = empService.delete(vo.getEmp_id());
				
				if(rtnVal == 0){
					message = "삭제실패 : 삭제 처리 중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete Emp : %s (%s)\n%s"
											, vo.getEmp_nm()
											, vo.getEmp_id()
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
