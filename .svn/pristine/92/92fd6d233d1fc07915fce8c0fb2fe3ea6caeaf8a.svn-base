package egovframework.dnworks.controller.sitemanager.cms.org;

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
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.org.service.DeptService;
import egovframework.dnworks.cms.org.service.DeptVo;
import egovframework.dnworks.cms.org.service.EmpService;
import egovframework.dnworks.cms.org.service.EmpVo;

@Controller
@RequestMapping("/SiteManager/cms/org")
public class DeptEmpController extends WebDefault
{
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private EmpService empService;
	
	/*
	 * 부서 직원선택
	 */
	@RequestMapping("/deptEmp.do")
	public String deptEmp(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));
		String dept_id 	= Util.nvl(request.getParameter("dept_id"));
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("dept_id"		, "");
		udp.put("pageNo"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink = String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		DeptVo deptVo = deptService.select(dept_id);
		model.addAttribute("deptVo", deptVo);

		model.addAttribute("mnu_code"		, mnu_code);
		
		return managerDir+"/cms/org/deptEmp";
	}
	
	@RequestMapping("/deptEmp_list.do")
	public String deptEmp_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String dept_id	= Util.nvl(request.getParameter("dept_id"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("dept_id"	, dept_id);
		map.put("srchKwd"	, srchKwd);
		map.put("srchSttus"	, 1);
		List<EmpVo> empList = empService.selectList(map);
		
		model.addAttribute("srchMap"	, map);
		model.addAttribute("empList"	, empList);
		
		return managerDir+"/cms/org/deptEmp_list";
	}
}
