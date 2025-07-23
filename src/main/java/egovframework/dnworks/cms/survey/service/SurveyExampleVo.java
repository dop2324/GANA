package egovframework.dnworks.cms.survey.service;

public class SurveyExampleVo 
{
	private int exam_sn;
	private int qus_sn;
	private int exam_ty;
	private String exam_example;
	private String exam_file;
	private String exam_linkUrl;
	private int exam_voteCnt;
	private int exam_sttus;
	
	private int exam_totCnt;
	
	
	
	public int getExam_sttus() {
		return exam_sttus;
	}
	public void setExam_sttus(int exam_sttus) {
		this.exam_sttus = exam_sttus;
	}
	public int getExam_sn() {
		return exam_sn;
	}
	public void setExam_sn(int exam_sn) {
		this.exam_sn = exam_sn;
	}
	public int getQus_sn() {
		return qus_sn;
	}
	public void setQus_sn(int qus_sn) {
		this.qus_sn = qus_sn;
	}
	public int getExam_ty() {
		return exam_ty;
	}
	public void setExam_ty(int exam_ty) {
		this.exam_ty = exam_ty;
	}
	public String getExam_example() {
		return exam_example;
	}
	public void setExam_example(String exam_example) {
		this.exam_example = exam_example;
	}
	public String getExam_file() {
		return exam_file;
	}
	public void setExam_file(String exam_file) {
		this.exam_file = exam_file;
	}
	public String getExam_linkUrl() {
		return exam_linkUrl;
	}
	public void setExam_linkUrl(String exam_linkUrl) {
		this.exam_linkUrl = exam_linkUrl;
	}
	public int getExam_voteCnt() {
		return exam_voteCnt;
	}
	public void setExam_voteCnt(int exam_voteCnt) {
		this.exam_voteCnt = exam_voteCnt;
	}
	public int getExam_totCnt() {
		return exam_totCnt;
	}
	public void setExam_totCnt(int exam_totCnt) {
		this.exam_totCnt = exam_totCnt;
	}
	
	
}
