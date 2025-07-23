package egovframework.dnworks.controller.sitemanager.func.lnkorg;

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

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdService;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.lnkorg.service.LnkorgInfoService;
import egovframework.dnworks.func.lnkorg.service.LnkorgInfoVO;
import egovframework.dnworks.func.lnkorg.service.LnkorgUrlService;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.PageNavi;

@Controller
@RequestMapping("/SiteManager/func/lnkorg")
public class LnkorgInfoController extends WebDefault {

	@Resource(name = "LnkorgInfoService")
	private LnkorgInfoService lnkorgInfoService;
	
	@Resource(name = "LnkorgUrlService")
	private LnkorgUrlService lnkorgUrlService;
	
	@Autowired
	private CmmnCdService cmmnCdService;

	/**
	 * 연계기관 정보 메인 엔트리
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
		udp.put("pageNo", 1);
		udp.put("pageSize", 20);

		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);

		String listLink = String.format("?%s", queryString);
		model.addAttribute("listLink", listLink);
		model.addAttribute("mnu_code", mnu_code);

		switch (cmd) {
			case 16:
				this.select(request, session, model, queryString);
				location = managerDir+"/func/lnkorg/info/form";
			break;
			case 2:
				this.select(request, session, model, queryString);
				
				// 등록된 url 호출
				this.selectUrl(request, session, model, queryString);
				location = managerDir+"/func/lnkorg/info/view";
			break;
			case 4: // 등록/수정
				this.select(request, session, model, queryString);
				location = managerDir + "/func/lnkorg/info/form";
				break;
			default: // 목록
				this.list(request, session, model, queryString);
				location = managerDir + "/func/lnkorg/info/list";
				break;
		}

		return location;
	}

	/**
	 * 등록된 URL 목록 호출
	 * */
	private void selectUrl(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		String orgUnqId = Util.nvl(request.getParameter("orgUnqId"));
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgUnqId", orgUnqId);
		model.addAttribute("urlList", this.lnkorgUrlService.getList(param));
	}

	/**
	 * 목록 화면
	 */
	public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		
		int pageNo = Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize = Util.nvl(request.getParameter("pageSize"), 20);

		Map<String, Object> param = new HashMap<>();
		param.put("srchSe", Util.nvl(request.getParameter("srchSe")));
		param.put("srchUseYn", Util.nvl(request.getParameter("srchUseYn")));
		param.put("srchKwd", Util.nvl(request.getParameter("srchKwd")));
		param.put("offset", (pageNo - 1) * pageSize);
		param.put("pageSize", pageSize);
		param.put("pageNo"		, pageNo);

		int totalCnt = lnkorgInfoService.selectListCnt(param); // 추후 count 쿼리 필요 시 구현
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<LnkorgInfoVO> orgList = lnkorgInfoService.selectList(param);

		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", no);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("orgList", orgList);
		model.addAttribute("srchMap", param);

		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging", pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}

	/**
	 * 등록/수정 화면
	 */
	public void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
		String orgUnqId = Util.nvl(request.getParameter("orgUnqId"));
		int cmd			= !orgUnqId.equals("") ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		LnkorgInfoVO orgVo = new LnkorgInfoVO();
		
		if(cmd == Code.Prm.PrmUpd.getCode()) {
			Map<String, Object> map = new HashMap<>();
			map.put("orgUnqId", orgUnqId);
			orgVo = lnkorgInfoService.select(orgUnqId);
		}

		

		model.addAttribute("orgVo", orgVo);
		model.addAttribute("cmd"	, cmd);
	}

	/**
	 * 등록/수정/삭제 처리
	 */
	@RequestMapping("/info_process.do")
	public String info_process(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model,
							   @ModelAttribute LnkorgInfoVO vo) throws Exception {

		int cmd = Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl = Util.nvl(request.getParameter("returnUrl"), "/SiteManager/func/lnkorg/info.do");
		String queryString = Util.nvl(request.getParameter("queryString"));
		String location = String.format("%s?%s", returnUrl, queryString);

		String message = "";
		String log_what = "";

		if (EgovDoubleSubmitHelper.checkAndSaveToken(request)) {
			try {
				// 등록
				if (cmd == Code.Prm.PrmIns.getCode()) {
					vo.setRegId(Util.getSession(session, "USR_ID"));
					vo.setMdfcnId(Util.getSession(session, "USR_ID"));
					lnkorgInfoService.insert(vo);

					message = "등록되었습니다.";
					log_what = String.format("%s 연계기관: %s (%s)", "insert", vo.getOrgNm(), cmd);
				}

				// 수정
				else if (cmd == Code.Prm.PrmUpd.getCode()) {
					vo.setMdfcnId(Util.getSession(session, "USR_ID"));
					lnkorgInfoService.update(vo);

					message = "수정되었습니다.";
					log_what = String.format("%s 연계기관: %s (%s)", "update", vo.getOrgNm(), cmd);
				}

				// 삭제
				else if (cmd == Code.Prm.PrmDel.getCode()) {
					// 삭제 로직 필요시 작성
					vo.setDelId(Util.getSession(session, "USR_ID"));
					lnkorgInfoService.delete(vo);
					message = "삭제 되었습니다.";
					log_what = String.format("%s 연계기관: %s (%s)", "delete", vo.getOrgNm(), cmd);
				}

			} catch (Exception e) {
				e.printStackTrace();
				JavaScriptUtil.flushJsAlertAndHistoryBack(response, "요청 처리 중 오류가 발생했습니다.");
				return null;
			}
		} else {
			JavaScriptUtil.flushJsAlertAndHistoryBack(response, "중복 입력할 수 없습니다.");
			return null;
		}

		// 로그 저장
		super.insertLog(request, log_what);

		// 결과 메시지 및 이동 처리
		JavaScriptUtil.flushJsAlertAndLocation(response, message, location);
		return null;
	}

}
