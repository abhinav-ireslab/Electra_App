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
public class ContactDetails {

	private String contactName;
	private String mobileNumber;
	private String countryDialCode;
	private boolean isRegistered;
	private boolean isNumberValid;
	private String iso4217CurrencyAlphabeticCode;
	
	
	
	public String getIso4217CurrencyAlphabeticCode() {
		return iso4217CurrencyAlphabeticCode;
	}

	public void setIso4217CurrencyAlphabeticCode(String iso4217CurrencyAlphabeticCode) {
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
	}

	public boolean isAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	private boolean accountStatus;
	
	private String profileImageUrl;
	
	

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(boolean registered) {
		this.isRegistered = registered;
	}

	public boolean isNumberValid() {
		return isNumberValid;
	}

	public void setIsNumberValid(boolean validNumber) {
		this.isNumberValid = validNumber;
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

	@Override
	public String toString() {
		return "name: " + contactName + ", countryDialCode: " + countryDialCode + ", phoneNumber: " + mobileNumber
				+ ", registered: " + isRegistered + ", validNumber:" + isNumberValid;
	}
}
