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
import egovframework.dnworks.cms.blacklist.service.BlackListIPService;
import egovframework.dnworks.cms.blacklist.service.BlackListIPVo;

@Controller
@RequestMapping("/SiteManager/cms/blacklist")
public class BlackListIPController extends WebDefault
{

	@Autowired
	private BlackListIPService blackListIPService;
	
	/*
	 * BlackListIP
	 */
	@RequestMapping("/blackListIp.do")
	public String blackListIp(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{			
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		String queryString	= (String) request.getAttribute("queryString");	
		model.addAttribute("queryString"		, queryString);

		
		List<BlackListIPVo> blackIPList = blackListIPService.selectList(site_code);
		model.addAttribute("blackIPList"	, blackIPList);
		
		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);


		return managerDir+"/cms/blacklist/blackListIp";
	}
	
	/*
	 * blackListIp 처리
	 */
	@RequestMapping("/blackListIp_process.do")
	public String blackListIp_process(@ModelAttribute BlackListIPVo vo, BindingResult bindingResult
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
			vo.setBli_mdfcnID(Util.getSession(session, "USR_ID"));
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				rtnVal = blackListIPService.save(vo);
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("%s AccessIP : %s ~ %s %s useYn:%s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getBli_startIP(), !vo.getBli_endIP().equals("") ? vo.getBli_endIP():""
										, vo.getBli_desc(), vo.getBli_sttus()
										, message
									);

			}else if(cmd == Code.Prm.PrmDel.getCode() ) {
				
				BlackListIPVo delVo = blackListIPService.select(vo.getBli_sn());
				rtnVal = blackListIPService.delete(vo.getBli_sn());
				if(rtnVal == 0) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				log_what = String.format("delete AccessIP : %s ~ %s %s useYn:%s\n%s"
									, delVo.getBli_startIP(), !delVo.getBli_endIP().equals("") ? delVo.getBli_endIP():""
									, delVo.getBli_desc(), delVo.getBli_sttus()
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
