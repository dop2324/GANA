package egovframework.dnworks.cms.stats.service;

import java.util.Date;

public class SessionVo 
{
	private String ses_id;
	private String site_code;
	private String site_nm;
	private Date ses_joinDt;
	private String ses_sesID;
	private String ses_agent;
	private String ses_clientIP;
	private String ses_serverIP;
	private String ses_referer;
	private String ses_lang;

	private int	ses_return;
	private String mnu_code;
	
	

	public String getSite_nm() {
		return site_nm;
	}

	public void setSite_nm(String site_nm) {
		this.site_nm = site_nm;
	}

	public String getMnu_code() {
		return mnu_code;
	}

	public void setMnu_code(String mnu_code) {
		this.mnu_code = mnu_code;
	}

	public String getSes_id() {
		return ses_id;
	}

	public void setSes_id(String ses_id) {
		this.ses_id = ses_id;
	}

	public String getSite_code() {
		return site_code;
	}

	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}

	public Date getSes_joinDt() {
		return ses_joinDt;
	}

	public void setSes_joinDt(Date ses_joinDt) {
		this.ses_joinDt = ses_joinDt;
	}

	public String getSes_sesID() {
		return ses_sesID;
	}

	public void setSes_sesID(String ses_sesID) {
		this.ses_sesID = ses_sesID;
	}

	public String getSes_agent() {
		return ses_agent;
	}

	public void setSes_agent(String ses_agent) {
		this.ses_agent = ses_agent;
	}

	public String getSes_clientIP() {
		return ses_clientIP;
	}

	public void setSes_clientIP(String ses_clientIP) {
		this.ses_clientIP = ses_clientIP;
	}

	public String getSes_serverIP() {
		return ses_serverIP;
	}

	public void setSes_serverIP(String ses_serverIP) {
		this.ses_serverIP = ses_serverIP;
	}

	public String getSes_referer() {
		return ses_referer;
	}

	public void setSes_referer(String ses_referer) {
		this.ses_referer = ses_referer;
	}

	public String getSes_lang() {
		return ses_lang;
	}

	public void setSes_lang(String ses_lang) {
		this.ses_lang = ses_lang;
	}

	public int getSes_return() {
		return ses_return;
	}

	public void setSes_return(int ses_return) {
		this.ses_return = ses_return;
	}
	
	
}
