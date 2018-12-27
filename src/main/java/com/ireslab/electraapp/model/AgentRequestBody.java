package com.ireslab.electraapp.model;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentRequestBody extends GenericRequest {

	private static final long serialVersionUID = -3308004256899322626L;

	private BigInteger mobileNumber;
	private String countryDialCode;
	private String userCorreletionId;
	
	
	
	public String getUserCorreletionId() {
		return userCorreletionId;
	}
	public void setUserCorreletionId(String userCorreletionId) {
		this.userCorreletionId = userCorreletionId;
	}
	public BigInteger getMobileNumber() {
		return mobileNumber;
	}
	public String getCountryDialCode() {
		return countryDialCode;
	}
	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}
	
	
	
}
