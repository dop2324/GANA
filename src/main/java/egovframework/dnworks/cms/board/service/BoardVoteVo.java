package egovframework.dnworks.cms.board.service;

import java.util.Date;

public class BoardVoteVo 
{
	private int con_sn;
	private String vote_id;
	private int vote_ty;
	private Date vote_regDt;
	
	private int vote_yesCnt;
	private int vote_noCnt;
	
	
	
	public int getVote_yesCnt() {
		return vote_yesCnt;
	}
	public void setVote_yesCnt(int vote_yesCnt) {
		this.vote_yesCnt = vote_yesCnt;
	}
	public int getVote_noCnt() {
		return vote_noCnt;
	}
	public void setVote_noCnt(int vote_noCnt) {
		this.vote_noCnt = vote_noCnt;
	}
	public int getCon_sn() {
		return con_sn;
	}
	public void setCon_sn(int con_sn) {
		this.con_sn = con_sn;
	}
	public String getVote_id() {
		return vote_id;
	}
	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}
	public int getVote_ty() {
		return vote_ty;
	}
	public void setVote_ty(int vote_ty) {
		this.vote_ty = vote_ty;
	}
	public Date getVote_regDt() {
		return vote_regDt;
	}
	public void setVote_regDt(Date vote_regDt) {
		this.vote_regDt = vote_regDt;
	}
	
	
}
