package com.ireslab.electraapp.electra;

public class PurchaserDto {

	private String countryDialCode;
	private String mobileNumber;
	private String emailAddress;
	private String correlationId;
	private boolean isClient;
	private String uniqueCode;
	
	
	public String getCountryDialCode() {
		return countryDialCode;
	}
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isClient() {
		return isClient;
	}
	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	
	
	
	
	
}
