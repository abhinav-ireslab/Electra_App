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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the scheduled_transactions database table.
 * 
 */
@Entity
@Table(name = "scheduled_transactions")
@NamedQuery(name = "ScheduledTransaction.findAll", query = "SELECT s FROM ScheduledTransaction s")
public class ScheduledTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "scheduled_transaction_id")
	private Integer scheduledTransactionId;

	@Column(name = "beneficiary_mobile_number")
	private BigInteger beneficiaryMobileNumber;

	@Column(name = "no_of_tokens")
	private String noOfTokens;

	@Column(name = "is_cash_out")
	private boolean isCashOut;

	@Column(name = "institution_name")
	private String institutionName;

	@Column(name = "institution_account_number")
	private String institutionAccountNumber;

	@Column(name = "additional_institution_info")
	private String additionalInstitutionInfo;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "sender_account_id")
	private Account senderAccount;

	@ManyToOne
	@JoinColumn(name = "beneficiary_country_id")
	private Country beneficiaryCountry;

	public ScheduledTransaction() {
	}

	public Integer getScheduledTransactionId() {
		return this.scheduledTransactionId;
	}

	public void setScheduledTransactionId(Integer scheduledTransactionId) {
		this.scheduledTransactionId = scheduledTransactionId;
	}

	public BigInteger getBeneficiaryMobileNumber() {
		return this.beneficiaryMobileNumber;
	}

	public void setBeneficiaryMobileNumber(BigInteger beneficiaryMobileNumber) {
		this.beneficiaryMobileNumber = beneficiaryMobileNumber;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNoOfTokens() {
		return this.noOfTokens;
	}

	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	public Account getSenderAccount() {
		return this.senderAccount;
	}

	public void setSenderAccount(Account account) {
		this.senderAccount = account;
	}

	/**
	 * @param beneficiaryCountry
	 *            the beneficiaryCountry to set
	 */
	public void setBeneficiaryCountry(Country beneficiaryCountry) {
		this.beneficiaryCountry = beneficiaryCountry;
	}

	/**
	 * @return the beneficiaryCountry
	 */
	public Country getBeneficiaryCountry() {
		return beneficiaryCountry;
	}

	/**
	 * @return the isCashOut
	 */
	public boolean isCashOut() {
		return isCashOut;
	}

	/**
	 * @param isCashOut
	 *            the isCashOut to set
	 */
	public void setCashOut(boolean isCashOut) {
		this.isCashOut = isCashOut;
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
	public String getAdditionalInstitutionInfo() {
		return additionalInstitutionInfo;
	}

	/**
	 * @param addtionalInstitutionInfo
	 *            the addtionalInstitutionInfo to set
	 */
	public void setAdditionalInstitutionInfo(String additionalInstitutionInfo) {
		this.additionalInstitutionInfo = additionalInstitutionInfo;
	}

}