package egovframework.dnworks.func.cal.service;

import java.io.Serializable;
import java.util.Date;



public class CalInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String calId;
    private String orgUnqId;
    private String mid;
    private String orderId;
    private String method;
    private long amount;
    private Long fee;
    private Long interestFee;
    private Long supplyAmount;
    private Long vat;
    private Long payoutAmount;
    private String approvedAt;
    private String soldDate;
    private String payoutDate;
    private Date createdAt;
    
	/** 기관명 */
	private String orgNm;
	
    /** 조회조건용 **/
    private String startDate; // YYYY-MM-DD
    private String dateType;    // soldDate(매출일), paidOutDate(정산일)
    private String status;    // payment or refund
    
    private int totalPayment; // 기관별 결제 총합
    private int totalRefund; // 기관별 환불 총합
    
	public String getCalId() {
		return calId;
	}
	public void setCalId(String calId) {
		this.calId = calId;
	}
	public String getOrgUnqId() {
		return orgUnqId;
	}
	public void setOrgUnqId(String orgUnqId) {
		this.orgUnqId = orgUnqId;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long string) {
		this.amount = string;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getInterestFee() {
		return interestFee;
	}
	public void setInterestFee(Long interestFee) {
		this.interestFee = interestFee;
	}
	public Long getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(Long supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public Long getVat() {
		return vat;
	}
	public void setVat(Long vat) {
		this.vat = vat;
	}
	public Long getPayoutAmount() {
		return payoutAmount;
	}
	public void setPayoutAmount(Long payoutAmount) {
		this.payoutAmount = payoutAmount;
	}
	
	public String getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}
	public String getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(String approvedAt) {
		this.approvedAt = approvedAt;
	}
	public String getPayoutDate() {
		return payoutDate;
	}
	public void setPayoutDate(String payoutDate) {
		this.payoutDate = payoutDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	public int getTotalRefund() {
		return totalRefund;
	}
	public void setTotalRefund(int totalRefund) {
		this.totalRefund = totalRefund;
	}

    
    

}
