package egovframework.dnworks.controller.sitemanager.cms.cmmncd;

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
import egovframework.dnworks.cms.cmmncd.service.CmmnCdService;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdVo;

@Controller
@RequestMapping("/SiteManager/cms/cmmncd")
public class CmmncdController extends WebDefault
{
	@Autowired
	private CmmnCdService cmmnCdService;
	
	/*
	 * 공통코드 관리
	 */
	@RequestMapping("/cmmncd.do")
	public String cmmncd(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String code_sn 		= Util.nvl(request.getParameter("code_sn"));
		String code_uprSn	= Util.nvl(request.getParameter("code_uprSn"));
		String frmTy 		= Util.nvl(request.getParameter("frmTy"), "update");

		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"			, "");
		udp.put("frmTy"				, "");
		udp.put("code_sn"			, "");
		udp.put("code_uprSn"		, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("code_sn"		, code_sn);
		model.addAttribute("code_uprSn"		, code_uprSn);
		model.addAttribute("frmTy"			, frmTy);

		return managerDir+"/cms/cmmncd/cmmncd";
	}
	
	@RequestMapping("/cmmncd_tree.do")
	public String dept_tree(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String code_uprSn 	= Util.nvl(request.getParameter("code_uprSn"));
		String code_sn 		= Util.nvl(request.getParameter("code_sn"), code_uprSn);
		
		List<CmmnCdVo> cmmncdTree = cmmnCdService.selectTree(0);
		
		model.addAttribute("cmmncdTree"	, cmmncdTree);
		model.addAttribute("code_sn"	, code_sn);
		
		return managerDir+"/cms/cmmncd/cmmncd_tree";
	}
	
	@RequestMapping("/cmmncd_form.do")
	public String cmmncd_form(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		int code_sn 	= Util.nvl(request.getParameter("code_sn"), -1);
		int code_uprSn	= Util.nvl(request.getParameter("code_uprSn"), 0);
		int cmd			= code_sn != -1 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();

		CmmnCdVo vo = null;
		if(code_sn != -1) {
			cmd = Code.Prm.PrmUpd.getCode();
			vo = cmmnCdService.select(code_sn);

		} else {
			cmd = Code.Prm.PrmIns.getCode();
			vo = cmmnCdService.select(code_uprSn);

			vo.setCode_sn(code_sn);
			vo.setCode_uprSn(code_uprSn);
			vo.setCode_nm("");
			vo.setCode_val("");
		}

		model.addAttribute("vo"			, vo);
		model.addAttribute("cmd"		, cmd);
		
		return managerDir+"/cms/cmmncd/cmmncd_form";
	}
	
	@RequestMapping("/cmmncd_process.do")
	public String cmmncd_process(@ModelAttribute CmmnCdVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String updn 		= Util.nvl(request.getParameter("updn"));
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);

		int rtnVal 		= -1;
		String message 	= "";
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setCode_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				if(cmd == Code.Prm.PrmIns.getCode()) vo.setCode_sn(-1);
				
				rtnVal = cmmnCdService.save(vo);
				if(rtnVal == -1) {
					message 	= "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s cmmncd : %s (%s), status : %s\n%s"
											, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
											, vo.getCode_nm()
											, vo.getCode_sn()
											, vo.getCode_sttus()
											, message
										);
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				rtnVal = cmmnCdService.delete(vo.getCode_sn());
				
				if(rtnVal == -2) {
					message 	= "삭제 실패 : 등록된 하위코드가 있습니다. 하위코드를 먼저 삭제하세요!";
				}else if(rtnVal == -3) {
					message 	= "삭제 실패 : 공통코드 삭제 순서변경 오류가 발생하였습니다!";
				}else if(rtnVal == 0) {
					message 	= "삭제 실패 : 공통코드 삭제 처리중 오류가 발생하였습니다!";
				}
				
				log_what = String.format("delete cmmncd : %s (%s)\n%s : %s"
										, vo.getCode_nm(), vo.getCode_sn()
										, rtnVal, message
									);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode() )
			{
				Map<String, Object> map = new HashMap<>();
				map.put("code_sn"	, vo.getCode_sn());
				map.put("UPDN"		, updn);
				rtnVal = cmmnCdService.setOrder(map);
				
				if(rtnVal == -1) {
					message 	= "순서조정 : 처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("setOrder Menu : %s (%s)\nupdn : %s\n%s : %s"
										, vo.getCode_nm(), vo.getCode_sn()
										, updn
										, rtnVal, message
									);
			}
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
