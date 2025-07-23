package egovframework.dnworks.controller.webcontent.cms.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuGratifyService;
import egovframework.dnworks.cms.menu.service.MenuGratifyVo;

@Controller
@RequestMapping("/WebContent/cms/menu/gratify")
public class WebMenuGratifyController extends WebDefault
{
	@Autowired
	private MenuGratifyService menuGratifyService;
	
	@RequestMapping(value = "/gratify_process.do", method = RequestMethod.POST)
	public String gratify_process(@ModelAttribute MenuGratifyVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int gra_point 		= Util.nvl(request.getParameter("gra_point"), 0); 
		String gra_commt 	= Util.nvl(request.getParameter("gra_commt"));
		
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String location 	= String.format("location.href='%s?mnu_code=%s'", returnUrl, mnu_code);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setGra_commt(gra_commt);
			vo.setGra_regID(Util.getSession(session, "USR_ID"));
			
			vo.setGra_point5(gra_point == 5 ? 1:0);
			vo.setGra_point4(gra_point == 4 ? 1:0);
			vo.setGra_point3(gra_point == 3 ? 1:0);
			vo.setGra_point2(gra_point == 2 ? 1:0);
			vo.setGra_point1(gra_point == 1 ? 1:0);
			
			rtnVal = menuGratifyService.insert(vo);
			
			message = "만족도 저장 되었습니다";
			if(rtnVal == -1) {
				message = "실패 : 처리중 오류가 발생하였습니다!";
			}
			
			log_what = String.format("insert menuGratify\nIP : %s(%s) %s점\n%s"
					, IPUtil.getXForwardedFor(request), vo.getGra_regID()
					, gra_point
					, message
				);
			
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
		
	}
}
