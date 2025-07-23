package egovframework.dnworks.controller.sitemanager.cms.board.info;

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
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardGroupService;
import egovframework.dnworks.cms.board.info.service.BoardGroupVo;

@Controller
@RequestMapping("/SiteManager/cms/board/info/group")
public class BoardGroupController extends WebDefault
{
	@Autowired
	private BoardGroupService boardGroupService;
	
	/*
	 * group
	 */
	@RequestMapping("/group.do")
	public String group(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		List<BoardGroupVo> groupList = boardGroupService.selectList(parm_mnuCode);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("groupList"		, groupList);
		
		return managerDir+"/cms/board/info/group/group";
		
	}
	
	@RequestMapping(value = "/group_process.do", method = RequestMethod.POST)
	public String group_process(@ModelAttribute BoardGroupVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		
		String parm_mnuCode	= Util.nvl(request.getParameter("parm_mnuCode"));
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			vo.setMnu_code(parm_mnuCode);

			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				if(cmd == Code.Prm.PrmIns.getCode()) vo.setBgp_sn(-1);
				
				rtnVal = boardGroupService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Board Info Group : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getBgp_nm()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				BoardGroupVo delVo = boardGroupService.select(vo.getBgp_sn());
				rtnVal = boardGroupService.delete(vo.getBgp_sn());
				
				if(rtnVal == -2) {
					message = "삭제실패 : 그룹 게시물 정보 삭제 처리중 오류가 발생하였습니다!";
				}else if(rtnVal == 0) {
					message = "삭제실패 : 삭제 처리중 오류가 발생하였습니다!";
				}
				log_what = String.format("delete Board Info Group : %s\n%s"
										, delVo.getBgp_nm()
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
