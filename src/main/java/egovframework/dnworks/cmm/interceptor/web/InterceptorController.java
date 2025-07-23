package egovframework.dnworks.cmm.interceptor.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.blacklist.service.BlackListIPService;

@Controller
public class InterceptorController 
{
	@Autowired
	protected BlackListIPService blackListIPService;
	
	@RequestMapping(value = {"/error/error.do"})
	public String error(HttpServletRequest request, ModelMap model) throws Exception {
		String err_code = Util.nvl(request.getParameter("err_code"));
		String clientip = IPUtil.getXForwardedFor(request);
		
		model.addAttribute("errCode"	, err_code);
		model.addAttribute("clientip"	, clientip);
		
		return "error/error";
	}
	
	@RequestMapping(value = {"/error/error404.do"})
	public String error404(HttpServletRequest request, ModelMap model) throws Exception
	{
		return "error/error404";
	}
}
