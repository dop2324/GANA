package egovframework.dnworks.cms.board.service;

import java.util.Date;
import java.util.List;

public class BoardDeptVo 
{
	public String getWebDir() { return "board/dept"; }
	
	private int dept_sn;
	private int bod_sn;
	private String dept_id;
	private String dept_nm;
	private String user_id;
	private String user_nm;
	
	private String dept_cn;
	private int dept_sttus;
	
	private int dept_useYn;
	private String dept_regIP;
	private Date dept_regDt;
	private String dept_mdfcnIP;
	private Date dept_mdfcnDt;
	
	private List<BoardDeptFileVo> boardDeptFileList;
	private String[] deleteFile;
	private int key_sn;
	
	
	public int getDept_sn() {
		return dept_sn;
	}
	public void setDept_sn(int dept_sn) {
		this.dept_sn = dept_sn;
	}
	public int getBod_sn() {
		return bod_sn;
	}
	public void setBod_sn(int bod_sn) {
		this.bod_sn = bod_sn;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getDept_cn() {
		return dept_cn;
	}
	public void setDept_cn(String dept_cn) {
		this.dept_cn = dept_cn;
	}
	public int getDept_sttus() {
		return dept_sttus;
	}
	public void setDept_sttus(int dept_sttus) {
		this.dept_sttus = dept_sttus;
	}
	public int getDept_useYn() {
		return dept_useYn;
	}
	public void setDept_useYn(int dept_useYn) {
		this.dept_useYn = dept_useYn;
	}
	public String getDept_regIP() {
		return dept_regIP;
	}
	public void setDept_regIP(String dept_regIP) {
		this.dept_regIP = dept_regIP;
	}
	public Date getDept_regDt() {
		return dept_regDt;
	}
	public void setDept_regDt(Date dept_regDt) {
		this.dept_regDt = dept_regDt;
	}
	public String getDept_mdfcnIP() {
		return dept_mdfcnIP;
	}
	public void setDept_mdfcnIP(String dept_mdfcnIP) {
		this.dept_mdfcnIP = dept_mdfcnIP;
	}
	public Date getDept_mdfcnDt() {
		return dept_mdfcnDt;
	}
	public void setDept_mdfcnDt(Date dept_mdfcnDt) {
		this.dept_mdfcnDt = dept_mdfcnDt;
	}
	public List<BoardDeptFileVo> getBoardDeptFileList() {
		return boardDeptFileList;
	}
	public void setBoardDeptFileList(List<BoardDeptFileVo> boardDeptFileList) {
		this.boardDeptFileList = boardDeptFileList;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	public int getKey_sn() {
		return key_sn;
	}
	public void setKey_sn(int key_sn) {
		this.key_sn = key_sn;
	}
	
	
	
}
