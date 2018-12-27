package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendTokensRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senderCorrelationId;

	private Long senderMobileNumber;

	private String senderCountryDialCode;

	private String beneficiaryCorrelationId;

	private Long beneficiaryMobileNumber;

	private String beneficiaryCountryDialCode;

	private String noOfTokens;

	private String fee;
	
	private String transactionPurpose;
	
	private String beneficiaryUniqueCode;
	
	private String senderUniqueCode;
	
	
	

	public String getBeneficiaryUniqueCode() {
		return beneficiaryUniqueCode;
	}

	public void setBeneficiaryUniqueCode(String beneficiaryUniqueCode) {
		this.beneficiaryUniqueCode = beneficiaryUniqueCode;
	}

	public String getSenderUniqueCode() {
		return senderUniqueCode;
	}

	public void setSenderUniqueCode(String senderUniqueCode) {
		this.senderUniqueCode = senderUniqueCode;
	}

	/**
	 * @return the senderCorrelationId
	 */
	public String getSenderCorrelationId() {
		return senderCorrelationId;
	}

	/**
	 * @param senderCorrelationId
	 *            the senderCorrelationId to set
	 */
	public void setSenderCorrelationId(String senderCorrelationId) {
		this.senderCorrelationId = senderCorrelationId;
	}

	/**
	 * @return the beneficiaryCorrelationId
	 */
	public String getBeneficiaryCorrelationId() {
		return beneficiaryCorrelationId;
	}

	/**
	 * @param beneficiaryCorrelationId
	 *            the beneficiaryCorrelationId to set
	 */
	public void setBeneficiaryCorrelationId(String beneficiaryCorrelationId) {
		this.beneficiaryCorrelationId = beneficiaryCorrelationId;
	}

	/**
	 * @return the senderMobileNumber
	 */
	public Long getSenderMobileNumber() {
		return senderMobileNumber;
	}

	/**
	 * @param senderMobileNumber
	 *            the senderMobileNumber to set
	 */
	public void setSenderMobileNumber(Long senderMobileNumber) {
		this.senderMobileNumber = senderMobileNumber;
	}

	/**
	 * @return the senderCountryDialCode
	 */
	public String getSenderCountryDialCode() {
		return senderCountryDialCode;
	}

	/**
	 * @param senderCountryDialCode
	 *            the senderCountryDialCode to set
	 */
	public void setSenderCountryDialCode(String senderCountryDialCode) {
		this.senderCountryDialCode = senderCountryDialCode;
	}

	/**
	 * @return the beneficiaryMobileNumber
	 */
	public Long getBeneficiaryMobileNumber() {
		return beneficiaryMobileNumber;
	}

	/**
	 * @param beneficiaryMobileNumber
	 *            the beneficiaryMobileNumber to set
	 */
	public void setBeneficiaryMobileNumber(Long beneficiaryMobileNumber) {
		this.beneficiaryMobileNumber = beneficiaryMobileNumber;
	}

	/**
	 * @return the beneficiaryCountryCode
	 */
	public String getBeneficiaryCountryDialCode() {
		return beneficiaryCountryDialCode;
	}

	/**
	 * @param beneficiaryCountryCode
	 *            the beneficiaryCountryCode to set
	 */
	public void setBeneficiaryCountryDialCode(String beneficiaryCountryDialCode) {
		this.beneficiaryCountryDialCode = beneficiaryCountryDialCode;
	}

	/**
	 * @return the noOfTokens
	 */
	public String getNoOfTokens() {
		return noOfTokens;
	}

	/**
	 * @param noOfTokens
	 *            the noOfTokens to set
	 */
	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTransactionPurpose() {
		return transactionPurpose;
	}

	public void setTransactionPurpose(String transactionPurpose) {
		this.transactionPurpose = transactionPurpose;
	}

}
