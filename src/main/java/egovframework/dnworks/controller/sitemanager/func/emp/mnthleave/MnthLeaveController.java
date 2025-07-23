package egovframework.dnworks.controller.sitemanager.func.emp.mnthleave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdService;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdVo;
import egovframework.dnworks.cms.org.service.EmpService;
import egovframework.dnworks.cms.org.service.EmpVo;
import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveService;
import egovframework.dnworks.func.emp.mnthleave.service.MnthLeaveVo;

@Controller
@RequestMapping("/SiteManager/func/emp/mnthleave")
public class MnthLeaveController extends WebDefault
{
	@Autowired
	private CmmnCdService cmmnCdService;
	
	@Autowired
	private MnthLeaveService mnthLeaveService;
	
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

		return managerDir+"/func/emp/mnthleave/emp";
	}
	
	@RequestMapping("/mnthleave.do")
	public String mnthleave(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location = null;
		String mnu_code = Util.nvl(request.getParameter("mnu_code"));
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);
		String dept_id	= Util.nvl(request.getParameter("dept_id"), "ROOT");
		int currYear 	= LocalDate.now().getYear();
		int srchYear 	= Util.nvl(request.getParameter("srchYear"), currYear);
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("srchKwd"		, "");
		udp.put("srchYear"		, srchYear);

		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("cmd"			, cmd);
		model.addAttribute("dept_id"		, dept_id);
		
		model.addAttribute("srchYear"	, srchYear);
		model.addAttribute("srchKwd"	, srchKwd);
		
		//공통코드 월차 종류 8 [MNTH_LEAVE]
		List<CmmnCdVo> mnthcdList = cmmnCdService.selectExcludeValTree("MNTH_LEAVE");
		model.addAttribute("mnthcdList"	, mnthcdList);
		
		switch(cmd)
		{
			case 16:
			case 2 :
						this.select(request, session, model, queryString, srchYear);
						location = managerDir+"/func/emp/mnthleave/mnthleave_view";
						break;

			default :			
						this.list(request, session, model, queryString, srchYear);			
						location = managerDir+"/func/emp/mnthleave/mnthleave_list";
						break;
		}
		
		return location;
	}
	
	/* select */
	public void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString, int srchYear) throws Exception
	{	
		String emp_id 	= Util.nvl(request.getParameter("emp_id"));
		int mnth_sn 	= Util.nvl(request.getParameter("mnth_sn"), 0);
		int cmd			= mnth_sn != 0 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();

		if(mnth_sn != 0) {
			MnthLeaveVo mnthVo = mnthLeaveService.select(mnth_sn);
			model.addAttribute("mnthVo"		, mnthVo);
			
			if(mnthVo != null && emp_id.equals("")) emp_id = mnthVo.getEmp_id();
		}
		
		EmpVo empVo = empService.select(emp_id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("emp_id"	, emp_id);
		map.put("mnth_year"	, srchYear);
		List<MnthLeaveVo> mnthList = mnthLeaveService.selectList(map);
		
		model.addAttribute("empVo"		, empVo);
		model.addAttribute("mnthList"	, mnthList);
		
		model.addAttribute("cmd"		, cmd);
	}
	
	/* list */
	public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString, int srchYear) throws Exception
	{
		String dept_id	= Util.nvl(request.getParameter("dept_id"), "ROOT");
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));

		Map<String, Object> map = new HashMap<>();
		map.put("dept_id"	, dept_id);
		map.put("srchYear"	, srchYear);
		map.put("srchKwd"	, srchKwd);
		List<Map<String, Object>> empList = mnthLeaveService.selectEmpList(map);
		
		model.addAttribute("srchMap"	, map);
		model.addAttribute("empList"	, empList);	
	}
	
	@RequestMapping("/mnthleave_process.do")
	public String mnthleave_process(@ModelAttribute MnthLeaveVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%semp_id=%s&cmd=2'", returnUrl, queryString, vo.getEmp_id());

		int rtnVal 		= -1;
		String message 	= "";
		if(!EgovDoubleSubmitHelper.checkAndSaveToken(request)) 
		{	
			model.addAttribute("message", "중복 입력할 수 없습니다.");
			model.addAttribute("location", location);
			return "common/scriptAlert";
		}
		else
		{

			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode()) 
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String mnth_startDt	= Util.nvl(request.getParameter("mnth_startDt"));
				String mnth_endDt	= Util.nvl(request.getParameter("mnth_endDt"), mnth_startDt);
				List<LocalDate> dtList = DateUtil.getDtBetween(mnth_startDt, mnth_endDt);
				
				if(dtList != null && dtList.size() == 0) {
					//하루만 선택 시
					dtList.add(LocalDate.parse(mnth_startDt, formatter));
				}else {
					//마지막날 추가
					dtList.add(LocalDate.parse(mnth_endDt, formatter));
				}
				
				int mnth_days = 0;
				if(dtList != null) mnth_days = dtList.size();
				
				//mnth_year
				vo.setMnth_year(Util.nvl(mnth_startDt.substring(0, 4), 0));
				
				vo.setDateList(dtList);
				vo.setMnth_days(mnth_days);
				vo.setMnth_regID(Util.getSession(session, "USR_ID"));
				
				if(cmd == Code.Prm.PrmIns.getCode()) {
					vo.setMnth_sn(-1);
				}
				
				//일자 중복 검사
				boolean dupFlag = true;
				for(LocalDate d:dtList) {
					Map<String, Object> m = new HashMap<>();
					m.put("emp_id", vo.getEmp_id());
					m.put("mnth_dt", d.toString());
					if(vo.getMnth_sn() != -1) {
						m.put("mnth_sn", vo.getMnth_sn());
					}
					int chckDup = mnthLeaveService.chckDup(m);
					
					if(chckDup > 0) {
						dupFlag = false;
						break;
					}
				}
				
				if(!dupFlag) {
					model.addAttribute("message", "월차 일자 중복 됩니다");
					model.addAttribute("location", location);
					return "common/scriptAlert";
				}
				
				rtnVal = mnthLeaveService.save(vo);
			
				if(rtnVal == -1) {
					message = "실패 : 처리중 오류가 발생하였습니다.";
				}
			}else if(cmd == Code.Prm.PrmDel.getCode()) {
				rtnVal = mnthLeaveService.delete(vo.getMnth_sn());
				message = "삭제 되었습니다";
				if(rtnVal == 0) {
					message = "실패 : 처리중 오류가 발생하였습니다.";
				}
			}
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
		
	}
}
