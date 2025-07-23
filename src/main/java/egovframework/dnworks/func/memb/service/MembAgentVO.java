package egovframework.dnworks.func.memb.service;

import egovframework.dnworks.func.cmm.vo.CommonVO;

public class MembAgentVO extends CommonVO {


    /** 이용기관 ID */
    private String useOrgId;

    /** 이용기관명 */
    private String useOrgNm;

    /** 홈페이지명 */
    private String hmpgNm;

    /** 홈페이지 URL */
    private String hmpgUrl;
    
    /** 콜백페이지 URL */
    private String callbackUrl;

    /** 기관담당자명 */
    private String orgPicNm;

    /** 기관담당자 이메일 */
    private String orgPicEmlAddr;

    /** 기관담당자 연락처 */
    private String orgPicTelno;

    /** 클라이언트 ID */
    private String clientId;

	public String getUseOrgId() {
		return useOrgId;
	}

	public void setUseOrgId(String useOrgId) {
		this.useOrgId = useOrgId;
	}

	public String getUseOrgNm() {
		return useOrgNm;
	}

	public void setUseOrgNm(String useOrgNm) {
		this.useOrgNm = useOrgNm;
	}

	public String getHmpgNm() {
		return hmpgNm;
	}

	public void setHmpgNm(String hmpgNm) {
		this.hmpgNm = hmpgNm;
	}

	public String getHmpgUrl() {
		return hmpgUrl;
	}

	public void setHmpgUrl(String hmpgUrl) {
		this.hmpgUrl = hmpgUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getOrgPicNm() {
		return orgPicNm;
	}

	public void setOrgPicNm(String orgPicNm) {
		this.orgPicNm = orgPicNm;
	}

	public String getOrgPicEmlAddr() {
		return orgPicEmlAddr;
	}

	public void setOrgPicEmlAddr(String orgPicEmlAddr) {
		this.orgPicEmlAddr = orgPicEmlAddr;
	}

	public String getOrgPicTelno() {
		return orgPicTelno;
	}

	public void setOrgPicTelno(String orgPicTelno) {
		this.orgPicTelno = orgPicTelno;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
