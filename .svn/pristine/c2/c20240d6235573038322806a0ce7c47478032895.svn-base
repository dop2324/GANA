package egovframework.dnworks.controller.sitemanager.cms.blacklist;

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
import egovframework.dnworks.cms.blacklist.service.BlackListUserService;
import egovframework.dnworks.cms.blacklist.service.BlackListUserVo;

@Controller
@RequestMapping("/SiteManager/cms/blacklist")
public class BlackListUserController extends WebDefault
{
	@Autowired
	private BlackListUserService blackListUserService;
	
	/*
	 * BlackListIP
	 */
	@RequestMapping("/blackListUser.do")
	public String blackListUser(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);

		
		List<BlackListUserVo> blackUserList = blackListUserService.selectList(site_code);
		model.addAttribute("blackUserList"	, blackUserList);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);


		return managerDir+"/cms/blacklist/blackListUser";
	}
	
	/*
	 * blackListUser 처리
	 */
	@RequestMapping("/blackListUser_process.do")
	public String blackListUser_process(@ModelAttribute BlackListUserVo vo, BindingResult bindingResult
							, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setBlu_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode())
			{
				rtnVal = blackListUserService.insert(vo);
				if(rtnVal == -1) {
					message = "입력실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("insert BlackListUser : %s %s useYn:%s\n%s"
										, vo.getBlu_id()
										, vo.getBlu_desc(), vo.getBlu_sttus()
										, message
									);
			}else if(cmd == Code.Prm.PrmUpd.getCode()) {
				rtnVal = blackListUserService.update(vo);
				if(rtnVal == -1) {
					message = "수정실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("update BlackListUser : %s %s useYn:%s\n%s"
										, vo.getBlu_id()
										, vo.getBlu_desc(), vo.getBlu_sttus()
										, message
									);
			}else if(cmd == Code.Prm.PrmDel.getCode() ) {
				
				BlackListUserVo delVo = blackListUserService.select(vo.getBlu_sn());
				rtnVal = blackListUserService.delete(vo.getBlu_sn());
				if(rtnVal == 0) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("delete BlackListUser : %s %s useYn:%s\n%s"
									, delVo.getBlu_id()
									, delVo.getBlu_desc(), delVo.getBlu_sttus()
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
