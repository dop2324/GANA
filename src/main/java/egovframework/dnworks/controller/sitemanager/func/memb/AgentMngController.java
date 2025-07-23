package egovframework.dnworks.controller.sitemanager.func.memb;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.memb.service.MembAgentService;
import egovframework.dnworks.func.memb.service.MembAgentVO;

@Controller
@RequestMapping("/SiteManager/func/memb/agent")
public class AgentMngController  extends WebDefault {
	
	@Resource(name = "egovMessageSource")
    private EgovMessageSource egovMessageSource;
	
	@Autowired
	private MembAgentService membAgentService;

	/*
	 * 울산모아 이용기관관리 list
	 * @param request  HttpServletRequest 객체
	 * @param response ServletResponse 객체
	 * @param model    ModelMap 객체
	 * @return 화면 경로
	 */
	@RequestMapping("/info.do")
	public String info(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		
		String location 	= null;
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("mnu_code"		, "");
		udp.put("srchSe"		, "");
		udp.put("srchUseYn"		, "");

		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		model.addAttribute("mnu_code"		, mnu_code);
		
		switch(cmd) {
			case 16:
				this.select(request, session, model, queryString);
				location = managerDir+"/func/memb/agent/form";
			break;
			case 4:
				this.select(request, session, model, queryString);
				location = managerDir+"/func/memb/agent/form";
			break;
			default :
				this.list(request, session, model, queryString);
				location = managerDir+"/func/memb/agent/list";
			break;
		}
		
		return location;
	}
	
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {

		String srchSe 		= Util.nvl(request.getParameter("srchSe"));
		String srchUseYn 	= Util.nvl(request.getParameter("srchUseYn"));
		String srchKwd 	= Util.nvl(request.getParameter("srchKwd"));
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("srchSe"	, srchSe);
		map.put("srchUseYn"	, srchUseYn);
		map.put("srchKwd", srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);
		
		int totalCnt = membAgentService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<MembAgentVO> resultList = membAgentService.selectList(map);
		
		model.addAttribute("pageNo"			, pageNo);
		model.addAttribute("no"				, no);
		model.addAttribute("totalCnt"		, totalCnt);
		model.addAttribute("resultList"		, resultList);
		model.addAttribute("srchMap"		, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"			, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));

	}

	private void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		
		String useOrgId		= Util.nvl(request.getParameter("useOrgId"));
		int cmd			= !useOrgId.equals("") ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		MembAgentVO vo = new MembAgentVO();
		if(cmd == Code.Prm.PrmUpd.getCode()) {
			Map<String, Object> map = new HashMap<>();
			map.put("useOrgId", useOrgId);
			vo = membAgentService.select(map);
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
	}

	@RequestMapping("/process.do")
	public String process(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model
			, @ModelAttribute MembAgentVO vo) throws Exception {
		
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl	= request.getContextPath()+"/SiteManager/page.do";
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("%s?%s", returnUrl, queryString);
		
		String message 	= "";
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request)) {
			
			try {
				// Insert
				if(cmd == Code.Prm.PrmIns.getCode()) {
					// Client ID 생성
					String clientId;
					do {
						clientId = ClientIdGenerator();
					} while (membAgentService.existsClientId(clientId));
					
					vo.setClientId(clientId);
					vo.setRegId(Util.getSession(session, "USR_ID"));
					membAgentService.insert(vo);
					
					message = egovMessageSource.getMessage("success.common.insert");
					log_what = String.format("%s cmmncd : %s (%s)\n%s"
							, "insert"
							, "이용기관관리"
							, cmd
							, message
						);
				}
				
				// Update
				if(cmd == Code.Prm.PrmUpd.getCode()) {
					vo.setMdfcnId(Util.getSession(session, "USR_ID"));
					membAgentService.update(vo);
					
					message = egovMessageSource.getMessage("success.common.update");
					log_what = String.format("%s cmmncd : %s (%s)\n%s"
							, "update"
							, "이용기관관리"
							, cmd
							, message
						);
				}
				
				// Delete
				if(cmd == Code.Prm.PrmDel.getCode()) {
					vo.setDelId(Util.getSession(session, "USR_ID"));
					membAgentService.delete(vo);
					
					message = egovMessageSource.getMessage("success.common.delete");
					log_what = String.format("%s cmmncd : %s (%s)\n%s"
							, "delete"
							, "이용기관관리"
							, cmd
							, message
						);
				}
			}catch(Exception e) {
				e.printStackTrace();
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, egovMessageSource.getMessage("fail.request.msg"));
			}
		}
		
		super.insertLog(request, log_what);

		JavaScriptUtil.flushJsAlertAndLocation(response, message, location);
		return null;
	}
	
	/** 클라이언트ID 생성 */
	public String ClientIdGenerator() {
		SecureRandom random = new SecureRandom();
		int BLOCK_COUNT = 4;
		int BLOCK_LENGTH = 5;
		String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < BLOCK_COUNT; i++) {
            if (i > 0) sb.append("-");
            for (int j = 0; j < BLOCK_LENGTH; j++) {
                int index = random.nextInt(CHARSET.length());
                sb.append(CHARSET.charAt(index));
            }
        }
		return sb.toString();
	}

}
