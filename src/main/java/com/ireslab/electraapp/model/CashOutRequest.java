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
public class CashOutRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966957613756719787L;

	private String userCorrelationId;

	private Long mobileNumber;

	private String countryDialCode;

	private String noOfTokens;

	private String fee;

	private boolean isBankTransfer;

	private String institutionName;

	private String institutionAccountNumber;

	private String addtionalInstitutionInfo;

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

	/**
	 * @return the isBankTransfer
	 */
	public boolean isBankTransfer() {
		return isBankTransfer;
	}

	/**
	 * @param isBankTransfer
	 *            the isBankTransfer to set
	 */
	public void setIsBankTransfer(boolean isBankTransfer) {
		this.isBankTransfer = isBankTransfer;
	}

	/**
	 * @return the institutionName
	 */
	public String getInstitutionName() {
		return institutionName;
	}

	/**
	 * @param institutionName
	 *            the institutionName to set
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	/**
	 * @return the institutionAccountNumber
	 */
	public String getInstitutionAccountNumber() {
		return institutionAccountNumber;
	}

	/**
	 * @param institutionAccountNumber
	 *            the institutionAccountNumber to set
	 */
	public void setInstitutionAccountNumber(String institutionAccountNumber) {
		this.institutionAccountNumber = institutionAccountNumber;
	}

	/**
	 * @return the addtionalInstitutionInfo
	 */
	public String getAddtionalInstitutionInfo() {
		return addtionalInstitutionInfo;
	}

	/**
	 * @param addtionalInstitutionInfo
	 *            the addtionalInstitutionInfo to set
	 */
	public void setAddtionalInstitutionInfo(String addtionalInstitutionInfo) {
		this.addtionalInstitutionInfo = addtionalInstitutionInfo;
	}
}
