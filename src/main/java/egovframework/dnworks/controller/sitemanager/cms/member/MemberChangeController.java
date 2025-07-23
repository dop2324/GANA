package egovframework.dnworks.controller.sitemanager.cms.member;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.member.service.UserService;
import egovframework.dnworks.cms.member.service.UserVo;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;

@Controller
@RequestMapping("/SiteManager/cms/member")
public class MemberChangeController extends WebDefault
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/memberChange.do", method = RequestMethod.POST)
	public String memberChange(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{	
		String usr_id 		= Util.nvl(request.getParameter("usr_id"));
		String org_id 		= Util.getSession(request.getSession(), "USR_ID");
		
		if(!Util.getSession(request.getSession(), "GRP_ID").equals("GRP_000")) {
			model.addAttribute("message", "권한이 없습니다.");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}
		
		//로그인
		Map<String, Object> map = new HashMap<>();
		map.put("mem_id"	, usr_id);
		UserVo userVo = userService.login(map);
		
		if(userVo == null) {
			model.addAttribute("message", "사용자 정보가 없습니다.");
			model.addAttribute("location", "history.back();");
			return "common/scriptAlert";
		}else {
			Map<String, Object> prmMap = new HashMap<>();
			prmMap.put("usr_id", userVo.getUsr_id() );
			prmMap.put("dpt_id", userVo.getDpt_id() );
			prmMap.put("grp_id", userVo.getGrp_id() );
			//메뉴 관리권한 목록
			List<String> prmAdmMenuList = menuService.selectPrmAdminList(prmMap);
			
			//관리 권한있는지 확인
			if(prmAdmMenuList == null || prmAdmMenuList.size() == 0) {
				model.addAttribute("message", "관리 권한이 없는 사용자 입니다.");
				model.addAttribute("location", "history.back();");
				return "common/scriptAlert";
			}else{
				
				String CHKDUP_ID = String.format("MANAGER_%s", userVo.getUsr_id());
				
				//관리자 세션 생성
				int sessionTime = Util.nvl(EgovProperties.getProperty("Globals.sessionTime"), 10);
				session.setMaxInactiveInterval(sessionTime*60);	// 30분
				
				Util.setSession(session, "USR_ID"		, userVo.getUsr_id());
				Util.setSession(session, "USR_NM"		, userVo.getUsr_nm());
				
				if(!Util.nvl(userVo.getDpt_id()).equals("")) Util.setSession(session, "DPT_ID"		, userVo.getDpt_id());
				if(!Util.nvl(userVo.getDpt_nm()).equals("")) Util.setSession(session, "DPT_NM"		, userVo.getDpt_nm());
				
				Util.setSession(session, "GRP_ID"		, userVo.getGrp_id());
				Util.setSession(session, "GRP_NM"		, userVo.getGrp_nm());
				
				Util.setSession(session, "USR_MAIL"		, userVo.getUsr_mail());
				Util.setSession(session, "USR_PARAM"	, userVo.getUsr_param());
				
				Util.setSession(session, "CHKDUP_ID"	, CHKDUP_ID);
				
				String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
				MenuVo menuVo = menuService.select(mnu_code);

				//로그인 처음 메뉴 
				Map<String, Object> menusMap = new HashMap<>();
				menusMap.put("site_code", menuVo.getSite_code() );
				LinkedHashMap<String, MenuVo> siteMenuTree = menuService.selectTree(menusMap);

				String loginMenuCode="";
				for(MenuVo m:siteMenuTree.values()) {
					if(!prmAdmMenuList.contains(m.getMnu_code()))	continue;	// 사용자 보기권한이 없으면 continue
					
					if(!m.getMnu_ty().equals(Code.MnuType.Menu.getText()) 
							&& !m.getMnu_ty().equals(Code.MnuType.Root.getText())
							&& m.getMnu_sttus() == 1)
					{
						loginMenuCode = m.getMnu_code();
						break;
					}
				}
				
				log_what = String.format("관리자 사용자 전환 성공 (%s -> %s)", org_id, userVo.getUsr_id());
				super.insertLog(request, log_what, "login");
				
				return "redirect:"+managerDir+"/page.do?mnu_code="+loginMenuCode; 
			}
		}

	}
}
