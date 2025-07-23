package egovframework.dnworks.func.lnkorg.service;

import egovframework.dnworks.func.cmm.vo.CommonVO;

public class LnkorgInfoVO extends CommonVO {

	 /** 연계기관 고유 ID */
    private String orgUnqId;

    /** 기관명 */
    private String orgNm;

    /** 우편번호 */
    private String zip;

    /** 도로명 주소 */
    private String roadNmAddr;

    /** 상세 주소 */
    private String dtlAddr;

    /** 담당자명 */
    private String picNm;

    /** 담당자 이메일 */
    private String picEmlAddr;

    /** 담당자 연락처 */
    private String picTelno;

    /** 유지보수 업체명 */
    private String mtncoNm;

    /** 유지보수 업체 담당자 */
    private String mtncoPicNm;

    /** 유지보수 업체 담당자 연락처 */
    private String mtncoPicTelno;

    /** 유지보수 업체 담당자 이메일 */
    private String mtncoPicEmlAddr;

    /** 홈페이지 주소 */
    private String hmpgUrl;

	public String getOrgUnqId() {
		return orgUnqId;
	}

	public void setOrgUnqId(String orgUnqId) {
		this.orgUnqId = orgUnqId;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getRoadNmAddr() {
		return roadNmAddr;
	}

	public void setRoadNmAddr(String roadNmAddr) {
		this.roadNmAddr = roadNmAddr;
	}

	public String getDtlAddr() {
		return dtlAddr;
	}

	public void setDtlAddr(String dtlAddr) {
		this.dtlAddr = dtlAddr;
	}

	public String getPicNm() {
		return picNm;
	}

	public void setPicNm(String picNm) {
		this.picNm = picNm;
	}

	public String getPicEmlAddr() {
		return picEmlAddr;
	}

	public void setPicEmlAddr(String picEmlAddr) {
		this.picEmlAddr = picEmlAddr;
	}


	public String getMtncoNm() {
		return mtncoNm;
	}

	public void setMtncoNm(String mtncoNm) {
		this.mtncoNm = mtncoNm;
	}

	public String getMtncoPicNm() {
		return mtncoPicNm;
	}

	public void setMtncoPicNm(String mtncoPicNm) {
		this.mtncoPicNm = mtncoPicNm;
	}

	public String getMtncoPicTelno() {
		return mtncoPicTelno;
	}

	public void setMtncoPicTelno(String mtncoPicTelno) {
		this.mtncoPicTelno = mtncoPicTelno;
	}

	public String getMtncoPicEmlAddr() {
		return mtncoPicEmlAddr;
	}

	public void setMtncoPicEmlAddr(String mtncoPicEmlAddr) {
		this.mtncoPicEmlAddr = mtncoPicEmlAddr;
	}

	public String getHmpgUrl() {
		return hmpgUrl;
	}

	public void setHmpgUrl(String hmpgUrl) {
		this.hmpgUrl = hmpgUrl;
	}

	public String getPicTelno() {
		return picTelno;
	}

	public void setPicTelno(String picTelno) {
		this.picTelno = picTelno;
	}

    
    
}
