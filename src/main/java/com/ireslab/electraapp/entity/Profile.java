package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id")
	private Integer profileId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile_number")
	private BigInteger mobileNumber;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "secondary_contact_number")
	private BigInteger secondaryContactNumber;

	@Column(name = "contact_address")
	private String contactAddress;

	@Column(name = "kyc_id_title")
	private String kycIdTitle;

	@Column(name = "kyc_id_front")
	private String kycIdFront;

	@Column(name = "kyc_id_back")
	private String kycIdBack;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	// bi-directional one-to-one association to Account
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@Column(name = "dob")
	private String dob;

	
	
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Profile() {
	}

	/**
	 * @return the profileId
	 */
	public Integer getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId
	 *            the profileId to set
	 */
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
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
	 * @return the secondaryContactNumber
	 */
	public BigInteger getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	/**
	 * @param secondaryContactNumber
	 *            the secondaryContactNumber to set
	 */
	public void setSecondaryContactNumber(BigInteger secondaryContactNumber) {
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
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + ", secondaryContactNumber="
				+ secondaryContactNumber + ", contactAddress=" + contactAddress + "]";
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
}