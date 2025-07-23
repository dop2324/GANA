package egovframework.dnworks.controller.sitemanager.cms.survey;

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
import egovframework.dnworks.cmm.PageNavi;
import egovframework.dnworks.cmm.util.DateUtil;
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.SiteVo;
import egovframework.dnworks.cms.survey.service.SurveyQuestionService;
import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;
import egovframework.dnworks.cms.survey.service.SurveyService;
import egovframework.dnworks.cms.survey.service.SurveyVo;

@Controller
@RequestMapping("/SiteManager/cms/survey")
public class SurveyController extends WebDefault
{
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	/*
	 * survey
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/survey.do")
	public String survey(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		Map<String, Object> defaultSiteMap = super.sitePrmList(session);
		model.addAttribute("defaultSiteMap"	, defaultSiteMap);
		
		String defaultSite = "";
		List<SiteVo> siteList = null;
		if(defaultSiteMap != null) {
			defaultSite = (String) defaultSiteMap.get("defaultSite");
			siteList = (List<SiteVo>) defaultSiteMap.get("sitePrmList");
		}
		model.addAttribute("siteList"		, siteList);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"), defaultSite);
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);
		
		HashMap<String, Object> udp = new HashMap<>();
		udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("srchKwd"		, "");
		if(cmd != Code.Prm.PrmLst.getCode()) 
		{
			udp.put("survey_sn"	, "");
			udp.put("pageNo"	, "");
		}
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink 	= String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);

		return managerDir+"/cms/survey/survey";
	}
	
	@RequestMapping("/initSurvey.do")
	public String initSurvey(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		String location = null;
		String site_code 	= (String) request.getAttribute("site_code");	//Util.nvl(request.getParameter("site_code"));
		String mnu_code 	= Util.nvl(request.getParameter("mnu_code"));
		int survey_sn 	= Util.nvl(request.getParameter("survey_sn"), 0);
		
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);

		String queryString	= (String) request.getAttribute("queryString");	
		String listLink 	= String.format("?%s", queryString);

		model.addAttribute("site_code"		, site_code);
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("survey_sn"		, survey_sn);
		model.addAttribute("cmd"			, cmd);
			
		model.addAttribute("queryString"	, queryString);
		model.addAttribute("listLink"		, listLink);
		
		switch(cmd)
		{
			//설문결과
			case 64	:
						this.question(request, session, model, queryString);
						location = managerDir+"/cms/survey/survey_result";
						break;
			//문항등록
			case 8	:
						this.select(request, session, model, queryString);
						this.question(request, session, model, queryString);
						location = managerDir+"/cms/survey/survey_question";
						break;
			//view
			case 2 :
						this.select(request, session, model, queryString);			
						location = managerDir+"/cms/survey/survey_view";
						break;
			case 16	:
			case 4 :
						this.select(request, session, model, queryString);			
						location = managerDir+"/cms/survey/survey_form";
						break;
			default :			
						this.list(request, session, model, queryString);			
						location = managerDir+"/cms/survey/survey_list";
						break;
		}
		
		return location;
	}
	
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String site_code 	= Util.nvl(request.getParameter("site_code"));
		String srchSttus 	= Util.nvl(request.getParameter("srchSttus"));
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, site_code);
		map.put("srchSttus"		, srchSttus);
		map.put("srchKwd"		, srchKwd);
		map.put("pageNo"		, pageNo);
		map.put("pageSize"		, pageSize);
		map.put("offset"		, (pageNo - 1) * pageSize);

		int totalCnt = surveyService.selectListCnt(map);
		int no = totalCnt - ((pageNo - 1) * pageSize);
		List<SurveyVo> surveyList = surveyService.selectList(map);
		
		model.addAttribute("no"			, no);
		model.addAttribute("pageNo"		, pageNo);
		model.addAttribute("totalCnt"	, totalCnt);
		model.addAttribute("surveyList"	, surveyList);
		model.addAttribute("srchMap"	, map);
		
		PageNavi pageNavi = new PageNavi();
		model.addAttribute("paging"		, pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
	}
	
	/* select */
	private void select(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int survey_sn 	= Util.nvl(request.getParameter("survey_sn"), 0);
		int cmd		= survey_sn!= 0 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		SurveyVo vo = null;
		if(cmd == Code.Prm.PrmUpd.getCode())
		{
			vo = surveyService.select(survey_sn);
		}
		
		model.addAttribute("vo"		, vo);
		model.addAttribute("cmd"	, cmd);
	}
	
	private void question(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception
	{
		int survey_sn 	= Util.nvl(request.getParameter("survey_sn"), 0);
		int qus_sn 		= Util.nvl(request.getParameter("qus_sn"), 0);
		int cmd			= qus_sn != 0 ? Code.Prm.PrmUpd.getCode():Code.Prm.PrmIns.getCode();
		
		SurveyQuestionVo quesVo = null;
		if(qus_sn != 0) {
			quesVo = surveyQuestionService.select(qus_sn);
		}
		
		List<SurveyQuestionVo> queslist = surveyQuestionService.selectList(survey_sn);
		
		model.addAttribute("survey_sn"	, survey_sn);
		model.addAttribute("quesVo"		, quesVo);
		model.addAttribute("queslist"	, queslist);
		model.addAttribute("cmd"		, cmd);
	}
	
	
	@RequestMapping(value = "/survey_process.do", method = RequestMethod.POST)
	public String survey_process(@ModelAttribute SurveyVo vo, BindingResult bindingResult
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
			vo.setSurvey_regID(Util.getSession(session, "USR_ID"));
			vo.setSurvey_regIP(IPUtil.getXForwardedFor(request));
			
			String survey_startDt 	= Util.nvl(request.getParameter("survey_startDt"), "2000-01-01");
			String survey_startDtHH = Util.nvl(request.getParameter("survey_startDtHH"), "00");
			String startDt = String.format("%s %s:00:00", survey_startDt, survey_startDtHH);
			
			String survey_endDt 	= Util.nvl(request.getParameter("survey_endDt"), "2999-12-31");
			String survey_endDtHH = Util.nvl(request.getParameter("survey_endDtHH"), "00");
			String endDt = String.format("%s %s:00:00", survey_endDt, survey_endDtHH);
			if(survey_endDtHH.equals("00")) {
				endDt = String.format("%s 23:59:59", survey_endDt);
			}
			
			vo.setSurvey_startDt(DateUtil.getDateTimeFromString(startDt));
			vo.setSurvey_endDt(DateUtil.getDateTimeFromString(endDt));
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode()) 
			{
				rtnVal = surveyService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Survey : %s (%s), %s~%s status : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getSurvey_ttl(), vo.getSite_code()
										, vo.getSurvey_startDt(), vo.getSurvey_endDt()
										, vo.getSurvey_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode()) 
			{
				SurveyVo delVo = surveyService.select(vo.getSurvey_sn());
				
				
				rtnVal = surveyService.delete(vo.getSurvey_sn());
				
				if(rtnVal == 0) {
					message = "삭제실패 : 처리 중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("delete Survey : %s (%s), %s~%s status : %s\n%s"
										, delVo.getSurvey_ttl(), delVo.getSite_code()
										, delVo.getSurvey_startDt(), delVo.getSurvey_endDt()
										, delVo.getSurvey_sttus()
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
