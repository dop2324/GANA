package egovframework.dnworks.controller.sitemanager.func.lnkorg;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.util.EgovStringUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.func.lnkorg.service.LnkorgUrlService;

@Controller
@RequestMapping("/SiteManager/func/lnkorgUrl")
public class LnkorgUrlController {

	@Autowired
	LnkorgUrlService lnkorgUrlService;
	
	/**
	 * 수정처리
	 * */
	@RequestMapping(value="/update_process.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap update_process(HttpServletRequest request, HttpSession session) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		try {
			String lnkorgUrlId = Util.nvl(request.getParameter("lnkorgUrlId"), "");
			String useYn = Util.nvl(request.getParameter("useYn"), "");
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("useYn", useYn);
			param.put("lnkorgUrlId", lnkorgUrlId);
			param.put("mdfcnId", Util.getSession(session, "USR_ID"));
			
			this.lnkorgUrlService.update(param);
			
			resultMap.put("result", true);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
			return resultMap;			
		}
		
		return resultMap;
	}
	
	/**
	 * 삭제처리
	 * */
	@RequestMapping(value="/remove_process.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap remove_process(HttpServletRequest request, HttpSession session) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		try {
			String lnkorgUrlId = Util.nvl(request.getParameter("lnkorgUrlId"), "");
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("lnkorgUrlId", lnkorgUrlId);
			
			this.lnkorgUrlService.remove(param);
			
			resultMap.put("result", true);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
			return resultMap;			
		}
		
		return resultMap;
	}
	
	/**
	 * 저장처리
	 * */
	@RequestMapping(value="/save_process.do", method = RequestMethod.POST)
	@ResponseBody
	public EgovMap save_process(HttpServletRequest request, HttpSession session) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		try {
			String orgUnqId = Util.nvl(request.getParameter("orgUnqId"), "");
			String dataDivCd = Util.nvl(request.getParameter("dataDivCd"), "");
			String url = Util.nvl(request.getParameter("url"), "");
			String useYn = Util.nvl(request.getParameter("useYn"), "");
			
			if(EgovStringUtil.isEmpty(orgUnqId)) {
				resultMap.put("result", false);
				resultMap.put("msg", "유효한 값이 넘어오지 않았습니다.");
				return resultMap;
			}
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orgUnqId", orgUnqId);
			param.put("dataDivCd", dataDivCd);
			param.put("url", url);
			param.put("useYn", useYn);
			param.put("regId", Util.getSession(session, "USR_ID"));
			
			lnkorgUrlService.insert(param);
			
			resultMap.put("result", true);
		}catch(Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			resultMap.put("msg", e.toString());
			return resultMap;
		}
		
		return resultMap;
	}
	
}
