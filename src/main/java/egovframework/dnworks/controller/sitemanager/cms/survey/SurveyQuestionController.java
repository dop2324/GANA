package egovframework.dnworks.controller.sitemanager.cms.survey;

import java.util.ArrayList;
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

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.com.cmm.util.EgovFormBasedFileVo;
import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cmm.FileManager;
import egovframework.dnworks.cmm.FileUploadUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.survey.service.SurveyExampleService;
import egovframework.dnworks.cms.survey.service.SurveyExampleVo;
import egovframework.dnworks.cms.survey.service.SurveyQuestionService;
import egovframework.dnworks.cms.survey.service.SurveyQuestionVo;

@Controller
@RequestMapping("/SiteManager/cms/survey/question")
public class SurveyQuestionController extends WebDefault
{
	
	@Autowired
	private SurveyQuestionService surveyQuestionService;
	
	@Autowired
	private SurveyExampleService surveyExampleService;
	
	@RequestMapping(value = "/question_process.do", method = RequestMethod.POST)
	public String survey_process(@ModelAttribute SurveyQuestionVo vo, BindingResult bindingResult
									, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception 
	{
		int qus_sn 			= Util.nvl(request.getParameter("qus_sn"), 0);
		int cmd				= Util.nvl(request.getParameter("cmd"), 0);
		String returnUrl 	= Util.nvl(request.getParameter("returnUrl"));
		String queryString 	= Util.nvl(request.getParameter("queryString"));
		String location 	= String.format("location.href='%s?%scmd=8'", returnUrl, queryString);
		
		int rtnVal = -1;
		String message 	= "";
		
		if(EgovDoubleSubmitHelper.checkAndSaveToken(request)) {
			//upload
			String uploadDir	= EgovProperties.getProperty("Globals.FilePath")+vo.getWebDir();
			String[] BadExt 	= EgovProperties.getProperty("Globals.file.BadExt").split(",");
			int MaxUploadSize	= Util.nvl(EgovProperties.getProperty("Globals.file.MaxUploadSize"), 30);
			
			List<SurveyExampleVo> exampleList = null;
			SurveyExampleVo exampleVo = null;
			
			if(cmd != Code.Prm.PrmDel.getCode()) {
				//파일 업로드
				List<EgovFormBasedFileVo> files = FileUploadUtil.uploadFiles(request, uploadDir, MaxUploadSize, BadExt);
				
				String[] arrayExample = request.getParameterValues("exam_example");
				if(arrayExample != null) {
					exampleList = new ArrayList<>();

					for(int i=0; i<arrayExample.length; i++) 
					{
						String  exam_example = Util.nvl(request.getParameterValues("exam_example")[i]);
						if(exam_example.equals("")) continue;
						
						int exam_sn = Util.nvl(request.getParameterValues("exam_sn")[i], -1);
						int exam_ty = Util.nvl(request.getParameterValues("exam_ty")[i], 0);
						String exam_linkUrl = Util.nvl(request.getParameterValues("exam_linkUrl")[i]);
						int exam_sttus	= Util.nvl(request.getParameter("exam_sttus"+i), 0);
						
						exampleVo = new SurveyExampleVo();
						exampleVo.setExam_sn(exam_sn);
						exampleVo.setExam_ty(exam_ty);
						exampleVo.setExam_example(exam_example);
						exampleVo.setExam_linkUrl(exam_linkUrl);
						exampleVo.setExam_sttus(exam_sttus);
						
						//file
						String org_examFile = Util.nvl(request.getParameter("org_examFile"+i));
						int deleteFile = Util.nvl(request.getParameter("deleteFile"+i), 0);
						String exam_examFile = "";
						
						for(EgovFormBasedFileVo file:files) {
							if(file.getInputName().equals("exam_examFile"+i)) {
								exam_examFile = String.format("%s/%s", file.getServerSubPath(), file.getPhysicalName());
							}
						}
						exampleVo.setExam_file(Util.nvl(exam_examFile, org_examFile));

						if(!exam_examFile.equals("") && !org_examFile.equals("") && exam_examFile.equals(org_examFile)) {
							FileManager.deleteFile(String.format("%s/%s", uploadDir, org_examFile) );
						}
						if(deleteFile != 0) {
							FileManager.deleteFile(String.format("%s/%s", uploadDir, org_examFile) );
							exampleVo.setExam_file(null);
						}
						
						//add list
						exampleList.add(exampleVo);
					}
				}
				vo.setSurveyExample(exampleList);
			}
			
			if(cmd == Code.Prm.PrmIns.getCode() || cmd == Code.Prm.PrmUpd.getCode())
			{
				if(cmd == Code.Prm.PrmIns.getCode()) {
					vo.setQus_sn(-1);
				}
				
				rtnVal = surveyQuestionService.save(vo);
				
				if(rtnVal == -1) {
					message = "처리중 오류가 발생하였습니다.";
				}
				
				log_what = String.format("%s Survey Question : %s (%s), %s %s status : %s\n%s"
										, cmd == Code.Prm.PrmIns.getCode() ? "insert":"update"
										, vo.getQus_question(), vo.getSurvey_sn()
										, vo.getQus_ty() == 0 ? "단항":"다항"
										, vo.getQus_required() == 1 ? "필수":""
										, vo.getQus_sttus()
										, message
									);
			}
			else if(cmd == Code.Prm.PrmDel.getCode())
			{
				SurveyQuestionVo delVo = surveyQuestionService.select(vo.getQus_sn());
				
				rtnVal = surveyQuestionService.delete(vo.getQus_sn());
				
				if(rtnVal == 0) {
					message = "삭제실패 : 처리중 오류가 발생하였습니다.";
				}else {
					for(SurveyExampleVo e:delVo.getSurveyExample()) {
						FileManager.deleteFile(String.format("%s/%s", uploadDir, e.getExam_file() ) );
					}
				}
				
				log_what = String.format("delete Survey Question : %s (%s), %s %s status : %s\n%s"
										, delVo.getQus_question(), delVo.getSurvey_sn()
										, delVo.getQus_ty() == 0 ? "단항":"다항"
										, delVo.getQus_required() == 1 ? "필수":""
										, delVo.getQus_sttus()
										, message
									);
			}
			else if(cmd == (Code.Prm.PrmUpd.getCode()+Code.Prm.PrmDel.getCode())) 
			{
				int exam_sn = Util.nvl(request.getParameter("parm_examSn"), 0);
				SurveyExampleVo delExamVo = surveyExampleService.select(exam_sn);
				
				rtnVal = surveyExampleService.delete(exam_sn);
				
				if(rtnVal == 0) {
					message = "삭제실패 : 처리중 오류가 발생하였습니다.";
				}else {
					if(delExamVo != null && !delExamVo.getExam_file().equals("")) {
						FileManager.deleteFile(String.format("%s/%s", uploadDir, delExamVo.getExam_file() ) );
					}
				}
				
				log_what = String.format("delete Survey Question Example : %s (%s) status : %s\n%s"
										, delExamVo.getExam_example(), delExamVo.getQus_sn()
										, delExamVo.getExam_sttus()
										, message
									);
				location = String.format("location.href='%s?%squs_sn=%s&cmd=8'", returnUrl, queryString, qus_sn);
			}
			super.insertLog(request, log_what);
		}
		
		model.addAttribute("message", message);
		model.addAttribute("location", location);
		return "common/scriptAlert";
	}
}
