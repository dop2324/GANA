package egovframework.dnworks.cms.blacklist.service;

import java.util.*;

import egovframework.dnworks.cmm.util.Util;

public class BlackListIPVo {
	
	private int bli_sn;
	private String bli_startIP;
	private String bli_endIP;
	private int bli_rng;
	private String bli_desc;
	private int bli_sttus;
	private String bli_mdfcnID;
	private Date bli_mdfcnDt;
	private String site_code;
	
	
	
	public int getBli_rng() {
		return bli_rng;
	}
	public void setBli_rng(int bli_rng) {
		this.bli_rng = bli_rng;
	}
	public int getBli_sn() {
		return bli_sn;
	}
	public void setBli_sn(int bli_sn) {
		this.bli_sn = bli_sn;
	}
	public String getBli_startIP() {
		return bli_startIP;
	}
	public void setBli_startIP(String bli_startIP) {
		this.bli_startIP = Util.getFormatIP(bli_startIP);
	}
	public String getBli_endIP() {
		return bli_endIP;
	}
	public void setBli_endIP(String bli_endIP) {
		this.bli_endIP = Util.getFormatIP(bli_endIP);
	}
	public String getBli_desc() {
		return bli_desc;
	}
	public void setBli_desc(String bli_desc) {
		this.bli_desc = bli_desc;
	}
	public int getBli_sttus() {
		return bli_sttus;
	}
	public void setBli_sttus(int bli_sttus) {
		this.bli_sttus = bli_sttus;
	}
	public String getBli_mdfcnID() {
		return bli_mdfcnID;
	}
	public void setBli_mdfcnID(String bli_mdfcnID) {
		this.bli_mdfcnID = bli_mdfcnID;
	}
	public Date getBli_mdfcnDt() {
		return bli_mdfcnDt;
	}
	public void setBli_mdfcnDt(Date bli_mdfcnDt) {
		this.bli_mdfcnDt = bli_mdfcnDt;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	
	
}
