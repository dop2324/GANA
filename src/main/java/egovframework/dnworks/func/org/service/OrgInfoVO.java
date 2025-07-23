package egovframework.dnworks.func.org.service;

import egovframework.dnworks.func.cmm.vo.CommonVO;

public class OrgInfoVO extends CommonVO {

	/** 기관 고유 ID */
	private String orgUnqId;

	/** 기관명 */
	private String orgNm;

	/** 우편번호 */
	private String zip;

	/** 도로명 주소 */
	private String roadNmAddr;

	/** 도로명 상세주소 */
	private String dtlAddr;

	/** 대표전화번호 */
	private String rprsntTelno;

	/** SMS 발신번호 */
	private String smsSndngTelno;
	
	/** 기관 소개 내용 */
    private String prgrmCn;
    
    /** 기관 홈페이지 URL */
    private String hmpgUrl;
    
    /** 지도 경도(X좌표) */
    private String mapLo;
    
    /** 지도 위도(Y좌표) */
    private String mapLa;

	/** 사용유무 */
	private String useYn;

	/** 생성자ID */
	private String regId;

	/** 수정자ID */
	private String mdfcnId;
	
	/** 첨부파일 */
    private String atchFileId;
    
    /** 비고 */
    private String rmkCn;

	public String getPrgrmCn() {
		return prgrmCn;
	}

	public void setPrgrmCn(String prgrmCn) {
		this.prgrmCn = prgrmCn;
	}

	public String getHmpgUrl() {
		return hmpgUrl;
	}

	public void setHmpgUrl(String hmpgUrl) {
		this.hmpgUrl = hmpgUrl;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

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

	public String getRprsntTelno() {
		return rprsntTelno;
	}

	public void setRprsntTelno(String rprsntTelno) {
		this.rprsntTelno = rprsntTelno;
	}

	public String getSmsSndngTelno() {
		return smsSndngTelno;
	}

	public void setSmsSndngTelno(String smsSndngTelno) {
		this.smsSndngTelno = smsSndngTelno;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getMdfcnId() {
		return mdfcnId;
	}

	public void setMdfcnId(String mdfcnId) {
		this.mdfcnId = mdfcnId;
	}

	public String getRmkCn() {
		return rmkCn;
	}

	public void setRmkCn(String rmkCn) {
		this.rmkCn = rmkCn;
	}

	public String getMapLo() {
		return mapLo;
	}

	public void setMapLo(String mapLo) {
		this.mapLo = mapLo;
	}

	public String getMapLa() {
		return mapLa;
	}

	public void setMapLa(String mapLa) {
		this.mapLa = mapLa;
	}

	
} 