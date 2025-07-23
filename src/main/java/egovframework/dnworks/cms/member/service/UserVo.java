package egovframework.dnworks.cms.member.service;

public class UserVo extends MemberVo
{
	private String usr_id;
	private String usr_pw;
	private String dpt_id;
	private String grp_id;	
	private String usr_nm;
	private String usr_mail;
	
	private String dpt_nm;
	private String grp_nm;
	
	private String usr_telno;
	private String usr_moblphone;
	private String upt_dpt_id;
	private String upt_dpt_nm;
	private String usr_param;
	
	private int usr_sttus;

	private int login_cnt;
	private int fail_cnt;
	

	public int getUsr_sttus() {
		return usr_sttus;
	}

	public void setUsr_sttus(int usr_sttus) {
		this.usr_sttus = usr_sttus;
	}

	public String getUsr_pw() {
		return usr_pw;
	}

	public void setUsr_pw(String usr_pw) {
		this.usr_pw = usr_pw;
	}

	public int getFail_cnt() {
		return fail_cnt;
	}

	public void setFail_cnt(int fail_cnt) {
		this.fail_cnt = fail_cnt;
	}

	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getDpt_id() {
		return dpt_id;
	}

	public void setDpt_id(String dpt_id) {
		this.dpt_id = dpt_id;
	}

	public String getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}

	public String getUsr_nm() {
		return usr_nm;
	}

	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}

	public String getUsr_mail() {
		return usr_mail;
	}

	public void setUsr_mail(String usr_mail) {
		this.usr_mail = usr_mail;
	}

	public String getDpt_nm() {
		return dpt_nm;
	}

	public void setDpt_nm(String dpt_nm) {
		this.dpt_nm = dpt_nm;
	}

	public String getGrp_nm() {
		return grp_nm;
	}

	public void setGrp_nm(String grp_nm) {
		this.grp_nm = grp_nm;
	}

	public String getUsr_telno() {
		return usr_telno;
	}

	public void setUsr_telno(String usr_telno) {
		this.usr_telno = usr_telno;
	}

	public String getUsr_moblphone() {
		return usr_moblphone;
	}

	public void setUsr_moblphone(String usr_moblphone) {
		this.usr_moblphone = usr_moblphone;
	}

	public String getUpt_dpt_id() {
		return upt_dpt_id;
	}

	public void setUpt_dpt_id(String upt_dpt_id) {
		this.upt_dpt_id = upt_dpt_id;
	}

	public String getUpt_dpt_nm() {
		return upt_dpt_nm;
	}

	public void setUpt_dpt_nm(String upt_dpt_nm) {
		this.upt_dpt_nm = upt_dpt_nm;
	}

	public String getUsr_param() {
		return usr_param;
	}

	public void setUsr_param(String usr_param) {
		this.usr_param = usr_param;
	}

	public int getLogin_cnt() {
		return login_cnt;
	}

	public void setLogin_cnt(int login_cnt) {
		this.login_cnt = login_cnt;
	} 
	
	
}
