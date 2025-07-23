package egovframework.dnworks.cms.blacklist.service;

import java.util.*;

public class BlackListUserVo 
{
	private int		blu_sn;
	private String	blu_id;
	private String	blu_nm;
	private int blu_rng;
	private String	blu_desc;
	private int	blu_sttus;
	private String	blu_mdfcnID;
	private Date	blu_mdfcnDt;
	private String	site_code;
	
	
	
	public int getBlu_rng() {
		return blu_rng;
	}
	public void setBlu_rng(int blu_rng) {
		this.blu_rng = blu_rng;
	}
	public int getBlu_sn() {
		return blu_sn;
	}
	public void setBlu_sn(int blu_sn) {
		this.blu_sn = blu_sn;
	}
	public String getBlu_id() {
		return blu_id;
	}
	public void setBlu_id(String blu_id) {
		this.blu_id = blu_id;
	}
	public String getBlu_nm() {
		return blu_nm;
	}
	public void setBlu_nm(String blu_nm) {
		this.blu_nm = blu_nm;
	}
	public String getBlu_desc() {
		return blu_desc;
	}
	public void setBlu_desc(String blu_desc) {
		this.blu_desc = blu_desc;
	}
	public int getBlu_sttus() {
		return blu_sttus;
	}
	public void setBlu_sttus(int blu_sttus) {
		this.blu_sttus = blu_sttus;
	}
	public String getBlu_mdfcnID() {
		return blu_mdfcnID;
	}
	public void setBlu_mdfcnID(String blu_mdfcnID) {
		this.blu_mdfcnID = blu_mdfcnID;
	}
	public Date getBlu_mdfcnDt() {
		return blu_mdfcnDt;
	}
	public void setBlu_mdfcnDt(Date blu_mdfcnDt) {
		this.blu_mdfcnDt = blu_mdfcnDt;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}


	
}

