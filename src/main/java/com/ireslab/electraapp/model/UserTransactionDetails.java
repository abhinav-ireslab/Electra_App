package com.ireslab.electraapp.model;

import java.util.Calendar;
import java.util.Date;

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
public class UserTransactionDetails /*implements Comparable<UserTransactionDetails>*/ {

	private String transactionUserName;

	private String transactionDate;

	private String transactionTime;

	private String noOfTokens;

	private String transactionMessage;

	private boolean isSendingTransaction;

	private String transactionFee;

	private short transactionStatus;

	private Long userMobileNumber;

	private String userCountryDialCode;

	private boolean isCashOutTransaction;
	
	private String profileImageUrl;
	
	private boolean offline;
	
	

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	@JsonIgnore(value = true)
	private Date txnDate;

	/**
	 * @return the transactionUserName
	 */
	public String getTransactionUserName() {
		return transactionUserName;
	}

	/**
	 * @param transactionUserName
	 *            the transactionUserName to set
	 */
	public void setTransactionUserName(String transactionUserName) {
		this.transactionUserName = transactionUserName;
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate
	 *            the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
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
	 * @return the isSendingTransaction
	 */
	public boolean getIsSendingTransaction() {
		return isSendingTransaction;
	}

	/**
	 * @param isSendingTransaction
	 *            the isSendingTransaction to set
	 */
	public void setIsSendingTransaction(boolean isSendingTransaction) {
		this.isSendingTransaction = isSendingTransaction;
	}

	/**
	 * @return the transactionFee
	 */
	public String getTransactionFee() {
		return transactionFee;
	}

	/**
	 * @param transactionFee
	 *            the transactionFee to set
	 */
	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
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

	enum TransactionStatus {
		PENDING(0), SUCCESS(1), FAILURE(3);

		private int status;

		private TransactionStatus(int status) {
			this.status = status;
		}

		public Integer getStatus() {
			return this.status;
		}
	}

	/**
	 * @return the userPhoneNumber
	 */
	public Long getUserMobileNumber() {
		return userMobileNumber;
	}

	/**
	 * @param userPhoneNumber
	 *            the userPhoneNumber to set
	 */
	public void setUserMobileNumber(Long userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	/**
	 * @return the userCountryDialCode
	 */
	public String getUserCountryDialCode() {
		return userCountryDialCode;
	}

	/**
	 * @param userCountryDialCode
	 *            the userCountryDialCode to set
	 */
	public void setUserCountryDialCode(String userCountryDialCode) {
		this.userCountryDialCode = userCountryDialCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	/*@Override
	@JsonIgnore(value = true)
	public int compareTo(UserTransactionDetails another) {

		Calendar anotherTxnDateCalendar = Calendar.getInstance();
		anotherTxnDateCalendar.setTime(another.getTxnDate());

		Calendar currentTxnDateCalendar = Calendar.getInstance();
		currentTxnDateCalendar.setTime(getTxnDate());

		return anotherTxnDateCalendar.compareTo(currentTxnDateCalendar);
	}*/

	/**
	 * @return the isCashOutTransaction
	 */
	public boolean getIsCashOutTransaction() {
		return isCashOutTransaction;
	}

	/**
	 * @param isCashOutTransaction
	 *            the isCashOutTransaction to set
	 */
	public void setIsCashOutTransaction(boolean isCashOutTransaction) {
		this.isCashOutTransaction = isCashOutTransaction;
	}

	/**
	 * @return the txnDate
	 */
	@JsonIgnore(value = true)
	public Date getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate
	 *            the txnDate to set
	 */
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	/**
	 * @return the transactionTime
	 */
	public String getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime
	 *            the transactionTime to set
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public boolean getOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

}
