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
public class ActivationCodeRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308004256899322626L;

	private Long mobileNumber;
	private String countryDialCode;
	private String activationCode;

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
	 * @return the countryCode
	 */
	public String getCountryDialCode() {
		return countryDialCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * @param activationCode
	 *            the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

}
