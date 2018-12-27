package com.ireslab.electraapp.entity;

import java.io.Serializable;
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
 * The persistent class for the transaction_details database table.
 * 
 */
@Entity
@Table(name = "transaction_details")
@NamedQuery(name = "TransactionDetail.findAll", query = "SELECT t FROM TransactionDetail t")
public class TransactionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_detail_id")
	private Integer transactionDetailId;

	@Column(name = "no_of_tokens")
	private String noOfTokens;

	@Column(name = "receiver_currency_value")
	private String receiverCurrencyValue;

	@Column(name = "sender_currency_value")
	private String senderCurrencyValue;

	@Column(name = "transaction_message")
	private String transactionMessage;

	@Column(name = "transaction_status")
	private short transactionStatus;

	@Column(name = "transaction_status_message")
	private String transactionStatusMessage;

	@Column(name = "transaction_xdr")
	private String transactionXdr;

	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@ManyToOne
	@JoinColumn(name = "sender_account_id", referencedColumnName = "account_id")
	private Account senderAccountId;

	@ManyToOne
	@JoinColumn(name = "receiver_account_id", referencedColumnName = "account_id")
	private Account receiverAccountId;

	/**
	 * @return the transactionDetailId
	 */
	public Integer getTransactionDetailId() {
		return transactionDetailId;
	}

	/**
	 * @param transactionDetailId
	 *            the transactionDetailId to set
	 */
	public void setTransactionDetailId(Integer transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
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
	 * @return the noOfTokens
	 */
	public String getNoOfTokens() {
		return noOfTokens;
	}

	/**
	 * @param noOfTokens
	 *            the noOfTokens to set
	 */
	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	/**
	 * @return the receiverCurrencyValue
	 */
	public String getReceiverCurrencyValue() {
		return receiverCurrencyValue;
	}

	/**
	 * @param receiverCurrencyValue
	 *            the receiverCurrencyValue to set
	 */
	public void setReceiverCurrencyValue(String receiverCurrencyValue) {
		this.receiverCurrencyValue = receiverCurrencyValue;
	}

	/**
	 * @return the senderCurrencyValue
	 */
	public String getSenderCurrencyValue() {
		return senderCurrencyValue;
	}

	/**
	 * @param senderCurrencyValue
	 *            the senderCurrencyValue to set
	 */
	public void setSenderCurrencyValue(String senderCurrencyValue) {
		this.senderCurrencyValue = senderCurrencyValue;
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

	/**
	 * @return the transactionMessage
	 */
	public String getTransactionMessage() {
		return transactionMessage;
	}

	/**
	 * @param transactionMessage
	 *            the transactionMessage to set
	 */
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	/**
	 * @return the transactionStatus
	 */
	public short getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * @param transactionStatus
	 *            the transactionStatus to set
	 */
	public void setTransactionStatus(short transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * @return the transactionStatusMessage
	 */
	public String getTransactionStatusMessage() {
		return transactionStatusMessage;
	}

	/**
	 * @param transactionStatusMessage
	 *            the transactionStatusMessage to set
	 */
	public void setTransactionStatusMessage(String transactionStatusMessage) {
		this.transactionStatusMessage = transactionStatusMessage;
	}

	/**
	 * @return the transactionXdr
	 */
	public String getTransactionXdr() {
		return transactionXdr;
	}

	/**
	 * @param transactionXdr
	 *            the transactionXdr to set
	 */
	public void setTransactionXdr(String transactionXdr) {
		this.transactionXdr = transactionXdr;
	}

	/**
	 * @return the senderAccountId
	 */
	public Account getSenderAccountId() {
		return senderAccountId;
	}

	/**
	 * @param senderAccountId
	 *            the senderAccountId to set
	 */
	public void setSenderAccountId(Account senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	/**
	 * @return the receiverAccountId
	 */
	public Account getReceiverAccountId() {
		return receiverAccountId;
	}

	/**
	 * @param receiverAccountId
	 *            the receiverAccountId to set
	 */
	public void setReceiverAccountId(Account receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
}