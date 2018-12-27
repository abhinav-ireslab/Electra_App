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
 * The persistent class for the topup_transactions database table.
 * 
 */
@Entity
@Table(name = "topup_transactions")
@NamedQuery(name = "TopupTransaction.findAll", query = "SELECT t FROM TopupTransaction t")
public class TopupTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "topup_transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer topupTransactionId;

	@Column(name = "additional_payment_info")
	private String additionalPaymentInfo;

	@Column(name = "beneficiary_country_dial_code")
	private String beneficiaryCountryDialCode;

	@Column(name = "beneficiary_mobile_number")
	private BigInteger beneficiaryMobileNumber;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "no_of_tokens")
	private String noOfTokens;

	@Column(name = "payment_platform")
	private String paymentPlatform;

	@Column(name = "payment_reference_number")
	private String paymentReferenceNumber;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@Column(name = "transaction_message")
	private String transactionMessage;

	public TopupTransaction() {
	}

	public Integer getTopupTransactionId() {
		return this.topupTransactionId;
	}

	public void setTopupTransactionId(Integer topupTransactionId) {
		this.topupTransactionId = topupTransactionId;
	}

	public String getAdditionalPaymentInfo() {
		return this.additionalPaymentInfo;
	}

	public void setAdditionalPaymentInfo(String additionalPaymentInfo) {
		this.additionalPaymentInfo = additionalPaymentInfo;
	}

	public String getBeneficiaryCountryDialCode() {
		return this.beneficiaryCountryDialCode;
	}

	public void setBeneficiaryCountryDialCode(String beneficiaryCountryDialCode) {
		this.beneficiaryCountryDialCode = beneficiaryCountryDialCode;
	}

	public BigInteger getBeneficiaryMobileNumber() {
		return this.beneficiaryMobileNumber;
	}

	public void setBeneficiaryMobileNumber(BigInteger beneficiaryMobileNumber) {
		this.beneficiaryMobileNumber = beneficiaryMobileNumber;
	}

	public String getNoOfTokens() {
		return this.noOfTokens;
	}

	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	public String getPaymentPlatform() {
		return this.paymentPlatform;
	}

	public void setPaymentPlatform(String paymentPlatform) {
		this.paymentPlatform = paymentPlatform;
	}

	public String getPaymentReferenceNumber() {
		return this.paymentReferenceNumber;
	}

	public void setPaymentReferenceNumber(String paymentReferenceNumber) {
		this.paymentReferenceNumber = paymentReferenceNumber;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTransactionMessage() {
		return this.transactionMessage;
	}

	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
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
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}