package com.ireslab.electraapp.model;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.electraapp.electra.Status;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountBalance;

	@JsonIgnore(value = true)
	private com.ireslab.sendx.electra.utils.Status accountStatus;

	private String firstName;

	private String lastName;

	private BigInteger mobileNumber;

	private String emailAddress;

	private String uniqueCode;

	private String password;

	private Boolean ismPINSetup;

	private String mPIN;

	private String verificationCode;

	private String contactAddress;

	private String nricPassportNumber;

	private String iso4217CurrencyAlphabeticCode;

	private String iso4217CurrencyName;

	private String countryName;

	private String countryDialCode;

	private boolean isRegistered;

	private String profileImageValue;

	private String profileImageUrl;
	
	private String electraAppUrl;
	
	private String countryCurrrency;

	private String ekyc;

	private String companyCode;
	
	private boolean isEkycEkybApproved;
	private String notificationCount;
	
	private boolean isClient;
	
	private String userCorrelationId;
	
	private String firebaseServiceKey;
	private String gcmRegisterKey;
	private String residentialAddress;
	private String currencySymbol;
	
	
	
	
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
	}

	public String getNotificationCount() {
		return notificationCount;
	}

	public void setNotificationCount(String notificationCount) {
		this.notificationCount = notificationCount;
	}

	public boolean isEkycEkybApproved() {
		return isEkycEkybApproved;
	}

	public void setEkycEkybApproved(boolean isEkycEkybApproved) {
		this.isEkycEkybApproved = isEkycEkybApproved;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfile(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public String getProfileImageValue() {
		return profileImageValue;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageValue(String profileImageValue) {
		this.profileImageValue = profileImageValue;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * @return the accountBalance
	 */
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance
	 *            the accountBalance to set
	 */
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the userAccountId
	 */
	/*
	 * public Integer getUserAccountId() { return userAccountId; }
	 * 
	 *//**
		 * @param userAccountId
		 *            the userAccountId to set
		 *//*
			 * public void setUserAccountId(Integer userAccountId) { this.userAccountId =
			 * userAccountId; }
			 */

	 
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	public com.ireslab.sendx.electra.utils.Status getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(com.ireslab.sendx.electra.utils.Status accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setIsClient(boolean isClient) {
		this.isClient = isClient;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the mobileNumber
	 */
	public BigInteger getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(BigInteger mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the uniqueCode
	 */
	public String getUniqueCode() {
		return uniqueCode;
	}

	/**
	 * @param uniqueCode
	 *            the uniqueCode to set
	 */
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the ismPINSetup
	 */
	/* @JsonIgnore(value = true) */
	public Boolean getIsmPINSetup() {
		return ismPINSetup;
	}

	/**
	 * @param ismPINSetup
	 *            the ismPINSetup to set
	 */
	public void setIsmPINSetup(Boolean ismPINSetup) {
		this.ismPINSetup = ismPINSetup;
	}

	/**
	 * @return the mPIN
	 */
	public String getmPIN() {
		return mPIN;
	}

	/**
	 * @param mPIN
	 *            the mPIN to set
	 */
	public void setmPIN(String mPIN) {
		this.mPIN = mPIN;
	}

	/**
	 * @return the verificationCode
	 */
	public String getVerificationCode() {
		return verificationCode;
	}

	/**
	 * @param verificationCode
	 *            the verificationCode to set
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress
	 *            the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the nricPassportNumber
	 */
	public String getNricPassportNumber() {
		return nricPassportNumber;
	}

	/**
	 * @param nricPassportNumber
	 *            the nricPassportNumber to set
	 */
	public void setNricPassportNumber(String nricPassportNumber) {
		this.nricPassportNumber = nricPassportNumber;
	}

	/**
	 * @return the iso4217CurrencyAlphabeticCode
	 */
	public String getIso4217CurrencyAlphabeticCode() {
		return iso4217CurrencyAlphabeticCode;
	}

	/**
	 * @param iso4217CurrencyAlphabeticCode
	 *            the iso4217CurrencyAlphabeticCode to set
	 */
	public void setIso4217CurrencyAlphabeticCode(String iso4217CurrencyAlphabeticCode) {
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
	}

	/**
	 * @return the iso4217CurrencyName
	 */
	public String getIso4217CurrencyName() {
		return iso4217CurrencyName;
	}

	/**
	 * @param iso4217CurrencyName
	 *            the iso4217CurrencyName to set
	 */
	public void setIso4217CurrencyName(String iso4217CurrencyName) {
		this.iso4217CurrencyName = iso4217CurrencyName;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	 * @return the ekyc
	 */
	public String getEkyc() {
		return ekyc;
	}

	/**
	 * @param ekyc
	 *            the ekyc to set
	 */
	public void setEkyc(String ekyc) {
		this.ekyc = ekyc;
	}

	/**
	 * @return the isRegistered
	 */
	public boolean isRegistered() {
		return isRegistered;
	}

	/**
	 * @param isRegistered
	 *            the isRegistered to set
	 */
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfile [accountBalance=" + accountBalance + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress + ", uniqueCode=" + uniqueCode
				+ ", password=" + password + ", ismPINSetup=" + ismPINSetup + ", mPIN=" + mPIN + ", verificationCode="
				+ verificationCode + ", contactAddress=" + contactAddress + ", nricPassportNumber=" + nricPassportNumber
				+ ", iso4217CurrencyAlphabeticCode=" + iso4217CurrencyAlphabeticCode + ", iso4217CurrencyName="
				+ iso4217CurrencyName + ", countryName=" + countryName + ", countryDialCode=" + countryDialCode
				+ ", isRegistered=" + isRegistered + ", ekyc=" + ekyc + "]";
	}

	public String getElectraAppUrl() {
		return electraAppUrl;
	}

	public void setElectraAppUrl(String electraAppUrl) {
		this.electraAppUrl = electraAppUrl;
	}

	public String getCountryCurrrency() {
		return countryCurrrency;
	}

	public void setCountryCurrrency(String countryCurrrency) {
		this.countryCurrrency = countryCurrrency;
	}

	public boolean isClient() {
		return isClient;
	}

	

	public String getFirebaseServiceKey() {
		return firebaseServiceKey;
	}

	public void setFirebaseServiceKey(String firebaseServiceKey) {
		this.firebaseServiceKey = firebaseServiceKey;
	}

	public String getGcmRegisterKey() {
		return gcmRegisterKey;
	}

	public void setGcmRegisterKey(String gcmRegisterKey) {
		this.gcmRegisterKey = gcmRegisterKey;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
}
