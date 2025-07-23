package egovframework.dnworks.controller.sitemanager.cms.board.info;

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
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardFieldService;
import egovframework.dnworks.cms.board.info.service.BoardFieldVo;

@Controller
@RequestMapping("/SiteManager/cms/board/info/field")
public class BoardFieldController extends WebDefault
{
	@Autowired
	private BoardFieldService boardFieldService;
	
	/*
	 * Field
	 */
	@RequestMapping("/field.do")
	public String field(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String parm_mnuCode = Util.nvl(request.getParameter("parm_mnuCode"));
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("parm_mnuCode"	, "");
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		Map<String, Object> map = new HashMap<>();
		map.put("mnu_code", parm_mnuCode);
		List<BoardFieldVo> fieldList = boardFieldService.selectList(map);
		model.addAttribute("parm_mnuCode"	, parm_mnuCode);
		model.addAttribute("fieldList"		, fieldList);
		
		return managerDir+"/cms/board/info/field/field";
		
	}
	
	@RequestMapping(value = "/field_process.do", method = RequestMethod.POST)
	public String field_process(@ModelAttribute BoardFieldVo vo, BindingResult bindingResult
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

				rtnVal = boardFieldService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Board Info Field : %s(%s)\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getBfd_nm(), vo.getBfd_id()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmAdm.getCode() )
			{
				String updn 	= Util.nvl(request.getParameter("updn"));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bfd_id", vo.getBfd_id());
				map.put("updn", updn);
				
				rtnVal = boardFieldService.setOrder(map);
				
				if(rtnVal == -1) {
					message = "순서조정 : 처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("sort Board Info Field : %s(%s) %s\n%s"
										, vo.getBfd_nm(), vo.getBfd_id()
										, updn
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode() )
			{
				BoardFieldVo delVo = boardFieldService.select(vo.getBfd_id());
				rtnVal = boardFieldService.delete(vo.getBfd_id());
				
				if(rtnVal == -2) {
					message = "삭제 실패 : 사용자 정의 필드 정보 갱신중 오류가 발생하였습니다!";
				}else if(rtnVal == 0) {
					message = "삭제실패 : 삭제 처리중 오류가 발생하였습니다!";
				}
				log_what = String.format("delete Board Info Field : %s(%s)\n%s"
										, delVo.getBfd_nm(), delVo.getBfd_id()
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
