package egovframework.dnworks.controller.sitemanager.cms.menu;

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

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.PermissionService;
import egovframework.dnworks.cms.menu.service.PermissionVo;

@Controller
@RequestMapping("/SiteManager/cms/permission")
public class PermissionController extends WebDefault
{

	@Autowired
	private PermissionService permissionService;
	
	/*
	 * 권한관리
	 */
	@RequestMapping("/permission_list.do")
	public String permission_list(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);
		
		//MenuVo mnuVo = menuService.select(parm_mnuCode);
		List<PermissionVo> permissionList = permissionService.selectList(parm_mnuCode);
		
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("permissionList"	, permissionList);
		
		return managerDir+"/cms/menu/permission_list";
	}
	
	
	/*
	 * 권한처리
	 */
	@RequestMapping("/permission_process.do")
	public String permission_process(@ModelAttribute PermissionVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		int lowApply		= Util.nvl(request.getParameter("lowApply"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		String parm_deptEmpTy = Util.nvl(request.getParameter("parm_deptEmpTy"));
		String parm_deptEmpId = Util.nvl(request.getParameter("parm_deptEmpId"));
		String parm_deptEmpNm = Util.nvl(request.getParameter("parm_deptEmpNm"));
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(parm_mnuCode);
			vo.setPrm_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(vo.getPrm_id().equals(EgovProperties.getProperty("Site.grp.AdminID")) || vo.getPrm_id().equals(EgovProperties.getProperty("Site.mem.AdminID")))
			{
				message = "[전체 관리자 그룹], [관리자]에 대한 권한은 수정/삭제가 불가합니다!";
			}
			else
			{
				if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
				{
					if(parm_deptEmpTy.equals("emp")) {
						vo.setPrm_id(String.format("emp_%s", vo.getPrm_id()));
					}
					
					rtnVal = permissionService.save(vo, lowApply);
					
					if(rtnVal == -1) {
						message 	= "처리 중 오류가 발생하였습니다.";
					}
					
					log_what = String.format("%s Permission %s : %s\n%s"
												, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
												, vo.getMnu_code(), vo.getPrm_id()
												, message
											);
				}
				else if(cmd == Code.Prm.PrmDel.getCode() )
				{
					Map<String, Object> map = new HashMap<>();
					map.put("mnu_code"	, vo.getMnu_code());
					map.put("prm_id"	, vo.getPrm_id());
					
					rtnVal = permissionService.delete(map);
					
					if(rtnVal == 0) {
						message 	= "삭제실패 : 처리 중 오류가 발생하였습니다.";
					}
					
					log_what = String.format("delete Permission %s : %s\n%s"
											, vo.getMnu_code(), vo.getPrm_id()
											, message
										);
				}
				
				super.insertLog(request, log_what);
			}
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
		
	}
}
