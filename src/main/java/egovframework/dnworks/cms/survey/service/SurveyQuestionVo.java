package egovframework.dnworks.cms.survey.service;

import java.util.List;

public class SurveyQuestionVo 
{
	public String getWebDir() { return "survey"; }
	
	private int qus_sn;
	private int survey_sn;
	private String qus_question;
	private String qus_cn;
	private int qus_ty;
	private int qus_required;
	private int qus_sttus;
	
	private List<SurveyExampleVo> surveyExample;
	private List<SurveySubjectVo> surveySubject;
	
	private int qus_totCnt;
	
	private int key_sn;
	
	
	
	
	public int getQus_totCnt() {
		return qus_totCnt;
	}
	public void setQus_totCnt(int qus_totCnt) {
		this.qus_totCnt = qus_totCnt;
	}
	public int getKey_sn() {
		return key_sn;
	}
	public void setKey_sn(int key_sn) {
		this.key_sn = key_sn;
	}
	public int getQus_sttus() {
		return qus_sttus;
	}
	public void setQus_sttus(int qus_sttus) {
		this.qus_sttus = qus_sttus;
	}
	public int getQus_sn() {
		return qus_sn;
	}
	public void setQus_sn(int qus_sn) {
		this.qus_sn = qus_sn;
	}
	public int getSurvey_sn() {
		return survey_sn;
	}
	public void setSurvey_sn(int survey_sn) {
		this.survey_sn = survey_sn;
	}
	public String getQus_question() {
		return qus_question;
	}
	public void setQus_question(String qus_question) {
		this.qus_question = qus_question;
	}
	public String getQus_cn() {
		return qus_cn;
	}
	public void setQus_cn(String qus_cn) {
		this.qus_cn = qus_cn;
	}
	public int getQus_ty() {
		return qus_ty;
	}
	public void setQus_ty(int qus_ty) {
		this.qus_ty = qus_ty;
	}
	public int getQus_required() {
		return qus_required;
	}
	public void setQus_required(int qus_required) {
		this.qus_required = qus_required;
	}
	public List<SurveyExampleVo> getSurveyExample() {
		return surveyExample;
	}
	public void setSurveyExample(List<SurveyExampleVo> surveyExample) {
		this.surveyExample = surveyExample;
	}
	public List<SurveySubjectVo> getSurveySubject() {
		return surveySubject;
	}
	public void setSurveySubject(List<SurveySubjectVo> surveySubject) {
		this.surveySubject = surveySubject;
	}
	
	
}
