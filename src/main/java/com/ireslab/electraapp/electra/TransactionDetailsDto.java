package com.ireslab.electraapp.electra;

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
public class TransactionDetailsDto implements Comparable<TransactionDetailsDto> {

	private String transactionDate;

	private String transactionTime;

	private String noOfTokens;

	private String senderFirstName;

	private String senderLastName;

	private String recieverFirstName;

	private String recieverLastName;

	private short transactionStatus;
	
	private boolean isSendingTransaction;

	private String correlationId;
	
	private boolean offline;
	
	
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	private Date txnDate;

	private String tokenActivity;

	enum TransactionStatus {
		PENDING(0), SUCCESS(1), FAILURE(2);

		private int status;

		private TransactionStatus(int status) {
			this.status = status;
		}

		public Integer getStatus() {
			return this.status;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	@JsonIgnore(value = true)
	public int compareTo(TransactionDetailsDto another) {

		Calendar anotherTxnDateCalendar = Calendar.getInstance();
		anotherTxnDateCalendar.setTime(another.getTxnDate());

		Calendar currentTxnDateCalendar = Calendar.getInstance();
		currentTxnDateCalendar.setTime(getTxnDate());

		return anotherTxnDateCalendar.compareTo(currentTxnDateCalendar);
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getNoOfTokens() {
		return noOfTokens;
	}

	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}

	public String getSenderFirstName() {
		return senderFirstName;
	}

	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}

	public String getSenderLastName() {
		return senderLastName;
	}

	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}

	public String getRecieverFirstName() {
		return recieverFirstName;
	}

	public void setRecieverFirstName(String recieverFirstName) {
		this.recieverFirstName = recieverFirstName;
	}

	public String getRecieverLastName() {
		return recieverLastName;
	}

	public void setRecieverLastName(String recieverLastName) {
		this.recieverLastName = recieverLastName;
	}

	public short getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(short transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public void setTokenActivity(String tokenActivity) {
		this.tokenActivity = tokenActivity;
	}

	public String getTokenActivity() {
		return tokenActivity;
	}

	public boolean isSendingTransaction() {
		return isSendingTransaction;
	}

	public void setSendingTransaction(boolean isSendingTransaction) {
		this.isSendingTransaction = isSendingTransaction;
	}

	public boolean getOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

}
