package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the account_verification database table.
 * 
 */
@Entity
@Table(name = "account_verification")
@NamedQuery(name = "AccountVerification.findAll", query = "SELECT a FROM AccountVerification a")
public class AccountVerification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acc_verification_record_id")
	private Integer accVerificationRecordId;

	@Column(name = "activation_code")
	private String activationCode;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "mobile_number")
	private BigInteger mobileNumber;

	@Column(name = "retry_attempts")
	private short retryAttempts;

	@Column(name = "verification_type")
	private String verificationType;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	/**
	 * @return the accVerificationRecordId
	 */
	public Integer getAccVerificationRecordId() {
		return accVerificationRecordId;
	}

	/**
	 * @param accVerificationRecordId
	 *            the accVerificationRecordId to set
	 */
	public void setAccVerificationRecordId(Integer accVerificationRecordId) {
		this.accVerificationRecordId = accVerificationRecordId;
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

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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
	 * @return the retryAttempts
	 */
	public short getRetryAttempts() {
		return retryAttempts;
	}

	/**
	 * @param retryAttempts
	 *            the retryAttempts to set
	 */
	public void setRetryAttempts(short retryAttempts) {
		this.retryAttempts = retryAttempts;
	}

	/**
	 * @return the verificationType
	 */
	public String getVerificationType() {
		return verificationType;
	}

	/**
	 * @param verificationType
	 *            the verificationType to set
	 */
	public void setVerificationType(String verificationType) {
		this.verificationType = verificationType;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountVerification [accVerificationRecordId=" + accVerificationRecordId + ", activationCode="
				+ activationCode + ", countryId=" + countryId + ", mobileNumber=" + mobileNumber + ", retryAttempts="
				+ retryAttempts + ", verificationType=" + verificationType + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}
}