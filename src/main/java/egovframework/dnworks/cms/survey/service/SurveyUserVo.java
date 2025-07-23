package egovframework.dnworks.cms.survey.service;

public class SurveyUserVo 
{
	private int user_sn;
	private int survey_sn;
	private String user_nm;
	private String mem_id;
	private String user_di;
	private String user_addr;
	private String user_regDt;
	
	private int key_sn;
	
	public int getKey_sn() {
		return key_sn;
	}
	public void setKey_sn(int key_sn) {
		this.key_sn = key_sn;
	}
	public int getUser_sn() {
		return user_sn;
	}
	public void setUser_sn(int user_sn) {
		this.user_sn = user_sn;
	}
	public int getSurvey_sn() {
		return survey_sn;
	}
	public void setSurvey_sn(int survey_sn) {
		this.survey_sn = survey_sn;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getUser_di() {
		return user_di;
	}
	public void setUser_di(String user_di) {
		this.user_di = user_di;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_regDt() {
		return user_regDt;
	}
	public void setUser_regDt(String user_regDt) {
		this.user_regDt = user_regDt;
	}
	
	
}
