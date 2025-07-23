package egovframework.dnworks.cms.member.service;

import java.util.Date;

public class GroupVo 
{
	private String grp_id;
	private String grp_uprID;
	private String grp_nm;
	private String grp_desc;
	private int grp_sttus;
	private int grp_level;
	private int grp_sort;
	private String grp_mdfcnID;
	private Date grp_mdfcnDt;
	
	private int grp_childCnt;

	public String getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}

	public String getGrp_uprID() {
		return grp_uprID;
	}

	public void setGrp_uprID(String grp_uprID) {
		this.grp_uprID = grp_uprID;
	}

	public String getGrp_nm() {
		return grp_nm;
	}

	public void setGrp_nm(String grp_nm) {
		this.grp_nm = grp_nm;
	}

	public String getGrp_desc() {
		return grp_desc;
	}

	public void setGrp_desc(String grp_desc) {
		this.grp_desc = grp_desc;
	}

	public int getGrp_sttus() {
		return grp_sttus;
	}

	public void setGrp_sttus(int grp_sttus) {
		this.grp_sttus = grp_sttus;
	}

	public int getGrp_level() {
		return grp_level;
	}

	public void setGrp_level(int grp_level) {
		this.grp_level = grp_level;
	}

	public int getGrp_sort() {
		return grp_sort;
	}

	public void setGrp_sort(int grp_sort) {
		this.grp_sort = grp_sort;
	}

	public String getGrp_mdfcnID() {
		return grp_mdfcnID;
	}

	public void setGrp_mdfcnID(String grp_mdfcnID) {
		this.grp_mdfcnID = grp_mdfcnID;
	}

	public Date getGrp_mdfcnDt() {
		return grp_mdfcnDt;
	}

	public void setGrp_mdfcnDt(Date grp_mdfcnDt) {
		this.grp_mdfcnDt = grp_mdfcnDt;
	}

	public int getGrp_childCnt() {
		return grp_childCnt;
	}

	public void setGrp_childCnt(int grp_childCnt) {
		this.grp_childCnt = grp_childCnt;
	}
	
	
}
