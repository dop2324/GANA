package egovframework.dnworks.controller.webcontent.cms.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.SiteVo;
import egovframework.dnworks.cms.survey.service.SurveyQuestionService;
import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;
import egovframework.dnworks.cms.survey.service.SurveyResultService;
import egovframework.dnworks.cms.survey.service.SurveyResultVo;
import egovframework.dnworks.cms.survey.service.SurveyService;
import egovframework.dnworks.cms.survey.service.SurveySubjectService;
import egovframework.dnworks.cms.survey.service.SurveySubjectVo;
import egovframework.dnworks.cms.survey.service.SurveyUserService;
import egovframework.dnworks.cms.survey.service.SurveyUserVo;
import egovframework.dnworks.cms.survey.service.SurveyVo;

@Controller
@RequestMapping("/WebContent/cms/survey")
public class WebSurveyController extends WebDefault
{
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@Autowired
	private SurveyUserService surveyUserService;
	
	@Autowired
	private SurveyResultService surveyResultService;
	
	@Autowired
	private SurveySubjectService surveySubjectService;
	
	@RequestMapping("/survey.do")
	public String survey(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception
	{
		//page.do 객체 호출
		SiteVo siteVo = (SiteVo) request.getAttribute("siteVo");
		
		String mnu_code		= Util.nvl(request.getParameter("mnu_code"));
		int survey_sn 		= Util.nvl(request.getParameter("survey_sn"), 0);
		int cmd 			= Util.nvl(request.getParameter("cmd"), 1);
		
		model.addAttribute("site_code"		, siteVo.getSite_code());
		model.addAttribute("mnu_code"		, mnu_code);
		model.addAttribute("survey_sn"		, survey_sn);
		model.addAttribute("cmd"			, cmd);
		
		HashMap<String, Object> udp = new HashMap<>();
		//udp.put("site_code"		, "");
		udp.put("mnu_code"		, "");
		udp.put("srchKwd"		, "");
		if(cmd != Code.Prm.PrmLst.getCode()) {
			udp.put("pageNo"	, "");
		}
		String queryString = super.getParameters(request, udp, true, false);
		model.addAttribute("queryString"	, queryString);
		
		String listLink = String.format("?%s", queryString);
		model.addAttribute("listLink"		, listLink);
		
		//설문 설정
		if(cmd != Code.Prm.PrmLst.getCode()) {
			SurveyVo surveyVo = surveyService.select(survey_sn);
			if(surveyVo == null) {
				model.addAttribute("message", "설문 내용이 없습니다.");
				model.addAttribute("location", String.format("location.href=\"%s\";", model.get("listLink")));
				return "common/scriptAlert";
			}
			model.addAttribute("surveyVo"		, surveyVo);
		}
		
		String location = "";
		switch(cmd)
		{
			case 2	:	
			case 4	:
						location = this.paper(request, session, model, queryString, siteVo);
						if(location != null) return location;
						
						location = "survey_paper";
						if(cmd == 2) location = "survey_result";
						break;
			default :	
						this.list(request, session, model, queryString, siteVo);
						location = "survey_list";
						break;
		}

		return String.format("%s/cms/survey/%s", publicPath, location);
	}
	

	private String paper(HttpServletRequest request, HttpSession session, ModelMap model, String queryString, SiteVo siteVo) throws Exception
	{
		int survey_sn 	= Util.nvl(request.getParameter("survey_sn"), 0);
		int cmd 		= Util.nvl(request.getParameter("cmd"), 1);
		int prmVal		= (int) request.getAttribute("prmVal");
		
		String listLink = (String) model.get("listLink");
		
		if(survey_sn != 0) {
			
			SurveyVo surveyVo = (SurveyVo) model.getAttribute("surveyVo");
			
			//설문 참여 사용자 체크
			if(cmd == Code.Prm.PrmIns.getCode()) {
				
				if(surveyVo.getSurvey_dateEnd() != 1 || surveyVo.getSurvey_sttus() != 1) {
					model.addAttribute("message", "설문 참여기간이 아닙니다.");
					model.addAttribute("location", String.format("location.href=\"%s\";", listLink));
					return "common/scriptAlert";
				}else {
					
					//본인확인(회원) 확인
					if(surveyVo.getSurvey_authYn() == 1) {
						if(!super.isAuthedGrp(session)) {
							return String.format("%s/cms/survey/survey_auth", publicPath);
						}
					}
					
					//중복 설문방지
					SurveyUserVo userVo = new SurveyUserVo();
					userVo.setSurvey_sn(survey_sn);
					userVo.setMem_id(Util.getSession(session, "USR_ID"));
					
					if(surveyUserService.checkVote(userVo) > 0) {
						model.addAttribute("message", "이미 설문참여 하셨습니다.");
						model.addAttribute("location", String.format("location.href=\"%s\";", listLink));
						return "common/scriptAlert";
					}
					
					//쿠키 확인
					if(Util.getCookie(request, "cookieSurvey"+survey_sn) != null) {
						model.addAttribute("message", "이미 설문참여 하셨습니다.");
						model.addAttribute("location", String.format("location.href=\"%s\";", listLink));
						return "common/scriptAlert";
					}
				}
			}
			
			List<SurveyQuestionVo> questionlist = surveyQuestionService.selectList(survey_sn);
			model.addAttribute("questionlist"	, questionlist);
		}
		
		return null;
	}
	
	private void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString, SiteVo siteVo) throws Exception
	{
		int pageNo 			= Util.nvl(request.getParameter("pageNo"), 1);
		int pageSize 		= Util.nvl(request.getParameter("pageSize"), 20);
		
		String srchKwd 		= Util.nvl(request.getParameter("srchKwd"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("site_code"		, siteVo.getSite_code());
		map.put("srchSttus"		, 1);
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
	
	
	@RequestMapping(value = "/survey_process.do", method = RequestMethod.POST)
	public String survey_process(@ModelAttribute SurveyVo vo, BindingResult bindingResult
									, HttpServletResponse response
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int survey_sn 		= Util.nvl(request.getParameter("survey_sn"), 0);
		int cmd 			= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%s'", returnUrl, queryString);
		String failLocation = String.format("location.href='%s?%s&step=form'", returnUrl, queryString);
		
		String user_id = Util.getSession(session, "USR_ID");
		String user_nm = Util.getSession(session, "USR_NM", "비회원");
		String user_di = Util.getSession(session, "USR_DI", session.getId());
		String user_addr = Util.nvl(request.getParameter("usr_addr"), IPUtil.getXForwardedFor(request));

		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request))
		{
			SurveyVo surveyVo = surveyService.select(survey_sn);
			
			SurveyUserVo userVo = new SurveyUserVo();
			userVo.setSurvey_sn(survey_sn);
			userVo.setMem_id(user_id);
			userVo.setUser_nm(user_nm);
			userVo.setUser_di(user_di);
			userVo.setUser_addr(user_addr);
			
			String[] arrayQusSn = request.getParameterValues("qus_sn");
			
			List<SurveyResultVo> surveyResultList = null;
			List<SurveySubjectVo> surveySubjectList = null;
			SurveyResultVo resultVo = null;
			SurveySubjectVo subjectVo = null;
			
			if(arrayQusSn != null) {
				surveyResultList = new ArrayList<>();
				surveySubjectList = new ArrayList<>();
				
				for(String qusSn:arrayQusSn) {
					int qus_sn = Util.nvl(qusSn, 0);
					String chkExamSn = "";
					
					//1. 객관식
					String[] arrayExample = request.getParameterValues("exam_vote"+qus_sn);
					if(arrayExample != null) {
						
						for(String exam_sn:arrayExample) {
							String sub_cn = Util.nvl(request.getParameter("sub_cn"+exam_sn));
							
							if(sub_cn.equals("")) {
								//객관식
								resultVo = new SurveyResultVo();
								resultVo.setQus_sn(qus_sn);
								resultVo.setExam_sn(Util.nvl(exam_sn, 0));
								surveyResultList.add(resultVo);
	
							}else {
								//주관식
								subjectVo = new SurveySubjectVo();
								subjectVo.setQus_sn(qus_sn);
								subjectVo.setExam_sn(Util.nvl(exam_sn, 0));
								subjectVo.setSub_cn(Util.nvl(sub_cn));
								surveySubjectList.add(subjectVo);
							}
						}	
					}
					
					//2. 주관식
					
					
					/*
					String[] arraySubSn = request.getParameterValues("sub_sn"+qus_sn);
					if(arraySubSn != null) {
						
						for(String exam_sn:arraySubSn) {
							String sub_cn = Util.nvl(request.getParameter("sub_cn"+exam_sn));
							if(!Util.nvl(sub_cn).equals("")) {
								subjectVo = new SurveySubjectVo();
								subjectVo.setQus_sn(qus_sn);
								subjectVo.setExam_sn(Util.nvl(exam_sn, 0));
								subjectVo.setSub_cn(Util.nvl(sub_cn));
								surveySubjectList.add(subjectVo);
							}
						}
					}
					*/
				}
			}
			
			if(cmd == Code.Prm.PrmIns.getCode()) {
				//참여자 입력
				rtnVal = surveyUserService.save(userVo);
				
				if(rtnVal == -1) {
					message 	= "실패 : 처리중 오류가 발생하였습니다!";
				}else {
					//객관식
					for(SurveyResultVo v:surveyResultList) {
						v.setUser_sn(rtnVal);
						surveyResultService.save(v);
					}
					
					//주관식
					for(SurveySubjectVo v:surveySubjectList) {
						v.setUser_sn(rtnVal);
						surveySubjectService.save(v);
					}
					
					//쿠키생성 cookieSurvey
					Util.setCookie(response, "cookieSurvey"+userVo.getSurvey_sn(), Util.nvl(userVo.getSurvey_sn()));
				}
				
				log_what = String.format("insert survey paper\n%s\n%s(%s) %s\n%s"
						, surveyVo.getSurvey_ttl()
						, user_id, user_nm, user_addr
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
