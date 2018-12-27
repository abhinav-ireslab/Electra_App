package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "client_correlation_id")
	private String clientCorrelationId;
	
	@Column(name = "correlation_id")
	private String userCorrelationId;
	
	@Column(name = "is_client")
	private boolean isClient;

	@Column(name = "mobile_number")
	private BigInteger mobileNumber;

	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "device_token")
	private String deviceToken;

	@Column(name = "device_type")
	private String deviceType;

	private String password;

	@Column(name = "mpin", nullable = false)
	private String mpin;

	@Column(name = "unique_code", nullable = false)
	private String uniqueCode;

	@Column(name = "status")
	private String status;

	@Column(name = "wallet_pin")
	private String walletPin;

	// bi-directional one-to-one association to Profile
	@OneToOne(mappedBy = "account")
	private Profile profile;

	// bi-directional one-to-one association to Country
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;

	// bi-directional one-to-one association to StellarAccount
	@OneToOne(mappedBy = "account")
	private StellarAccount stellarAccount;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@OneToMany(mappedBy = "senderAccountId")
	private List<TransactionDetail> senderTransactionDetails;

	@OneToMany(mappedBy = "receiverAccountId")
	private List<TransactionDetail> receiverTransactionDetails;

	@OneToMany(mappedBy = "senderAccount")
	private List<ScheduledTransaction> scheduledTransactions;

	/* kyc updation */
	@Column(name = "profile_image_url")
	private String profileImageUrl;

	@Column(name = "id_proof_image_url")
	private String idProofImageUrl;

	@Column(name = "residential_address")
	private String residentialAddress;
	
	@Column(name = "gcm_register_key")
	private String gcmRegisterKey;

	/*
	 * @Column(name = "profile_image_name") private String profileImageName;
	 * 
	 * @Column(name = "id_proof_image_name") private String idProofImageName;
	 */

	/*
	 * public String getProfileImageName() { return profileImageName; }
	 * 
	 * public String getIdProofImageName() { return idProofImageName; }
	 * 
	 * public void setProfileImageName(String profileImageName) {
	 * this.profileImageName = profileImageName; }
	 * 
	 * public void setIdProofImageName(String idProofImageName) {
	 * this.idProofImageName = idProofImageName; }
	 */
	
	
	

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public boolean getIsClient() {
		return isClient;
	}

	public void setIsClient(boolean isClient) {
		this.isClient = isClient;
	}

	public String getGcmRegisterKey() {
		return gcmRegisterKey;
	}

	public void setGcmRegisterKey(String gcmRegisterKey) {
		this.gcmRegisterKey = gcmRegisterKey;
	}

	public String getIdProofImageUrl() {
		return idProofImageUrl;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setIdProofImageUrl(String idProofImageUrl) {
		this.idProofImageUrl = idProofImageUrl;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public Account() {
	}

	/**
	 * @return the accountId
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * @param deviceToken
	 *            the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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
	 * @return the mpin
	 */
	public String getMpin() {
		return mpin;
	}

	/**
	 * @param mpin
	 *            the mpin to set
	 */
	public void setMpin(String mpin) {
		this.mpin = mpin;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the walletPin
	 */
	public String getWalletPin() {
		return walletPin;
	}

	/**
	 * @param walletPin
	 *            the walletPin to set
	 */
	public void setWalletPin(String walletPin) {
		this.walletPin = walletPin;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the stellarAccount
	 */
	public StellarAccount getStellarAccount() {
		return stellarAccount;
	}

	/**
	 * @param stellarAccount
	 *            the stellarAccount to set
	 */
	public void setStellarAccount(StellarAccount stellarAccount) {
		this.stellarAccount = stellarAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", mobileNumber=" + mobileNumber + ", deviceId=" + deviceId
				+ ", deviceToken=" + deviceToken + ", deviceType=" + deviceType + ", password=" + password + ", status="
				+ status + ", walletPin=" + walletPin + "]";
	}

	/**
	 * @return the senderTransactionDetails
	 */
	public List<TransactionDetail> getSenderTransactionDetails() {
		return senderTransactionDetails;
	}

	/**
	 * @param senderTransactionDetails
	 *            the senderTransactionDetails to set
	 */
	public void setSenderTransactionDetails(List<TransactionDetail> senderTransactionDetails) {
		this.senderTransactionDetails = senderTransactionDetails;
	}

	/**
	 * @return the receiverTransactionDetails
	 */
	public List<TransactionDetail> getReceiverTransactionDetails() {
		return receiverTransactionDetails;
	}

	/**
	 * @param receiverTransactionDetails
	 *            the receiverTransactionDetails to set
	 */
	public void setReceiverTransactionDetails(List<TransactionDetail> receiverTransactionDetails) {
		this.receiverTransactionDetails = receiverTransactionDetails;
	}

	/**
	 * @return the scheduledTransactions
	 */
	public List<ScheduledTransaction> getScheduledTransactions() {
		return scheduledTransactions;
	}

	/**
	 * @param scheduledTransactions
	 *            the scheduledTransactions to set
	 */
	public void setScheduledTransactions(List<ScheduledTransaction> scheduledTransactions) {
		this.scheduledTransactions = scheduledTransactions;
	}

	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	
}