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
public class LoadTokensRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5327085822741050546L;

	private String userCorrelationId;

	private String noOfTokens;

	private Long mobileNumber;

	private String countryDialCode;

	private String paymentReferenceNumber;

	private boolean isTransactionSuccess;

	private String additionalPaymentInfo;

	private String paymentPlatform;

	private String paymentType;

	/**
	 * @return the userCorrelationId
	 */
	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	/**
	 * @param userCorrelationId
	 *            the userCorrelationId to set
	 */
	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
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
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the countryDialCode
	 */
	public String getCountryDialCode() {
		return countryDialCode;
	}

	/**
	 * @param countryDialCode
	 *            the countryDialCode to set
	 */
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	/**
	 * @return the paymentReferenceNumber
	 */
	public String getPaymentReferenceNumber() {
		return paymentReferenceNumber;
	}

	/**
	 * @param paymentReferenceNumber
	 *            the paymentReferenceNumber to set
	 */
	public void setPaymentReferenceNumber(String paymentReferenceNumber) {
		this.paymentReferenceNumber = paymentReferenceNumber;
	}

	/**
	 * @return the additionalPaymentInfo
	 */
	public String getAdditionalPaymentInfo() {
		return additionalPaymentInfo;
	}

	/**
	 * @param additionalPaymentInfo
	 *            the additionalPaymentInfo to set
	 */
	public void setAdditionalPaymentInfo(String additionalPaymentInfo) {
		this.additionalPaymentInfo = additionalPaymentInfo;
	}

	/**
	 * @return the paymentPlatform
	 */
	public String getPaymentPlatform() {
		return paymentPlatform;
	}

	/**
	 * @param paymentPlatform
	 *            the paymentPlatform to set
	 */
	public void setPaymentPlatform(String paymentPlatform) {
		this.paymentPlatform = paymentPlatform;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @param isTransactionSuccess
	 *            the isTransactionSuccess to set
	 */
	public void setTransactionSuccess(boolean isTransactionSuccess) {
		this.isTransactionSuccess = isTransactionSuccess;
	}

	/**
	 * @return the isTransactionSuccess
	 */
	public boolean isTransactionSuccess() {
		return isTransactionSuccess;
	}
}
