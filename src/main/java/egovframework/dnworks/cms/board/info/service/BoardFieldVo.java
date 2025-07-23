package egovframework.dnworks.cms.board.info.service;

public class BoardFieldVo 
{
	private String bfd_id;
	private String mnu_code;
    private String bfd_nm;
    private int bfd_width;
    private int bfd_maxLen;
    private int bfd_isMandatory;
    private int bfd_encYn;
    private int bfd_sort;
    private int bfd_sttus;
    
    
	public String getBfd_id() {
		return bfd_id;
	}
	public void setBfd_id(String bfd_id) {
		this.bfd_id = bfd_id;
	}
	public String getMnu_code() {
		return mnu_code;
	}
	public void setMnu_code(String mnu_code) {
		this.mnu_code = mnu_code;
	}
	public String getBfd_nm() {
		return bfd_nm;
	}
	public void setBfd_nm(String bfd_nm) {
		this.bfd_nm = bfd_nm;
	}
	public int getBfd_width() {
		return bfd_width;
	}
	public void setBfd_width(int bfd_width) {
		this.bfd_width = bfd_width;
	}
	public int getBfd_maxLen() {
		return bfd_maxLen;
	}
	public void setBfd_maxLen(int bfd_maxLen) {
		this.bfd_maxLen = bfd_maxLen;
	}
	public int getBfd_isMandatory() {
		return bfd_isMandatory;
	}
	public void setBfd_isMandatory(int bfd_isMandatory) {
		this.bfd_isMandatory = bfd_isMandatory;
	}
	public int getBfd_encYn() {
		return bfd_encYn;
	}
	public void setBfd_encYn(int bfd_encYn) {
		this.bfd_encYn = bfd_encYn;
	}
	public int getBfd_sort() {
		return bfd_sort;
	}
	public void setBfd_sort(int bfd_sort) {
		this.bfd_sort = bfd_sort;
	}
	public int getBfd_sttus() {
		return bfd_sttus;
	}
	public void setBfd_sttus(int bfd_sttus) {
		this.bfd_sttus = bfd_sttus;
	}
}
