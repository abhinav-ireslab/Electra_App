package com.ireslab.electraapp.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest extends AgentRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410246179154799396L;

	private String userCorrelationId;

	private String firstName;
	private String middleName;
	private String lastName;
	private String countryDialCode;
	private Long mobileNumber;
	private String secondaryContactNumber;
	private String contactAddress;
	private String password;
	private String kycIdTitle;
	private String kycIdFront;
	private String kycIdBack;
	private Map<String, String> kycIdDetails;
	private String deviceId;
	private String deviceType;
	private String emailAddress;
	private String mPIN;
	private Boolean isLocateAgent;
	private String companyName;
	private String companyCode;
	private String clientCorrelationId;

	/* kyc updation */

	/*
	 * private String profileImageValue; private String idProofImageValue;
	 */
	private Boolean isKycConfigure;

	private String residentialAddress;
	
	private String dob;
	
    private String gender;
	
    
	private String scanDocumentType;
	
	private String scanDocumentId;
	
	private String scanDocumentFrontPage;
	
	private String scanDocumentBackPage;
	
	private String postalCode;
	
	private String currencyType;
	
	private String uniqueCode;
	
	
	
	

	/*
	 * public String getProfileImageValue() { return profileImageValue; }
	 * 
	 * public String getIdProofImageValue() { return idProofImageValue; }
	 */

	

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	/*
	 * public void setProfileImageValue(String profileImageValue) {
	 * this.profileImageValue = profileImageValue; }
	 * 
	 * public SignupRequest setIdProofImageValue(String idProofImageValue) {
	 * this.idProofImageValue = idProofImageValue; return this; }
	 */
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	 * @return the countryCode
	 */
	public String getCountryDialCode() {
		return countryDialCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public SignupRequest setCountryDialCode(String countryCode) {
		this.countryDialCode = countryCode;
		return this;
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
	 * @return the secondaryContactNumber
	 */
	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	/**
	 * @param secondaryContactNumber
	 *            the secondaryContactNumber to set
	 */
	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
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
	 * @return the kycIdTitle
	 */
	public String getKycIdTitle() {
		return kycIdTitle;
	}

	/**
	 * @param kycIdTitle
	 *            the kycIdTitle to set
	 */
	public void setKycIdTitle(String kycIdTitle) {
		this.kycIdTitle = kycIdTitle;
	}

	/**
	 * @return the kycIdFront
	 */
	public String getKycIdFront() {
		return kycIdFront;
	}

	/**
	 * @param kycIdFront
	 *            the kycIdFront to set
	 */
	public void setKycIdFront(String kycIdFront) {
		this.kycIdFront = kycIdFront;
	}

	/**
	 * @return the kycIdBack
	 */
	public String getKycIdBack() {
		return kycIdBack;
	}

	/**
	 * @param kycIdBack
	 *            the kycIdBack to set
	 */
	public void setKycIdBack(String kycIdBack) {
		this.kycIdBack = kycIdBack;
	}

	/**
	 * @return the kycIdDetails
	 */
	public Map<String, String> getKycIdDetails() {
		return kycIdDetails;
	}

	/**
	 * @param kycIdDetails
	 *            the kycIdDetails to set
	 */
	public void setKycIdDetails(Map<String, String> kycIdDetails) {
		this.kycIdDetails = kycIdDetails;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
	 * @return the isLocateAgent
	 */
	public Boolean isLocateAgent() {
		return isLocateAgent;
	}

	/**
	 * @param isLocateAgent
	 *            the isLocateAgent to set
	 */
	public void setIsLocateAgent(Boolean isLocateAgent) {
		this.isLocateAgent = isLocateAgent;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}
	
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getScanDocumentType() {
		return scanDocumentType;
	}

	public void setScanDocumentType(String scanDocumentType) {
		this.scanDocumentType = scanDocumentType;
	}

	public String getScanDocumentId() {
		return scanDocumentId;
	}

	public void setScanDocumentId(String scanDocumentId) {
		this.scanDocumentId = scanDocumentId;
	}

	public String getScanDocumentFrontPage() {
		return scanDocumentFrontPage;
	}

	public void setScanDocumentFrontPage(String scanDocumentFrontPage) {
		this.scanDocumentFrontPage = scanDocumentFrontPage;
	}

	public String getScanDocumentBackPage() {
		return scanDocumentBackPage;
	}

	public void setScanDocumentBackPage(String scanDocumentBackPage) {
		this.scanDocumentBackPage = scanDocumentBackPage;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Boolean getIsLocateAgent() {
		return isLocateAgent;
	}

	@Override
	public String toString() {
		return "SignupRequest [userCorrelationId=" + userCorrelationId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", countryDialCode=" + countryDialCode + ", mobileNumber="
				+ mobileNumber + ", secondaryContactNumber=" + secondaryContactNumber + ", contactAddress="
				+ contactAddress + ", password=" + password + ", kycIdTitle=" + kycIdTitle + ", kycIdFront="
				+ kycIdFront + ", kycIdBack=" + kycIdBack + ", kycIdDetails=" + kycIdDetails + ", deviceId=" + deviceId
				+ ", deviceType=" + deviceType + ", emailAddress=" + emailAddress + ", mPIN=" + mPIN
				+ ", isLocateAgent=" + isLocateAgent + ", companyName=" + companyName + ", companyCode=" + companyCode
				+ ", clientCorrelationId=" + clientCorrelationId + ", residentialAddress=" + residentialAddress
				+ ", dob=" + dob + ", gender=" + gender + ", scanDocumentType=" + scanDocumentType + ", scanDocumentId="
				+ scanDocumentId + ", scanDocumentFrontPage=" + scanDocumentFrontPage + ", scanDocumentBackPage="
				+ scanDocumentBackPage + ", postalCode=" + postalCode + ", currencyType=" + currencyType + "]";
	}

	public Boolean getIsKycConfigure() {
		return isKycConfigure;
	}

	public void setIsKycConfigure(Boolean isKycConfigure) {
		this.isKycConfigure = isKycConfigure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	
}
