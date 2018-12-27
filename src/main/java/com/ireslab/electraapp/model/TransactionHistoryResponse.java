package com.ireslab.electraapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionHistoryResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7735452179083040070L;

	private List<UserTransactionDetails> transactionDetails;

	/**
	 * @return the transactionDetails
	 */
	public List<UserTransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}

	/**
	 * @param transactionDetails
	 *            the transactionDetails to set
	 */
	public void setTransactionDetails(List<UserTransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public TransactionHistoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionHistoryResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public TransactionHistoryResponse(Integer status, Integer code, String message,
			List<UserTransactionDetails> transactionDetails) {
		super(status, code, message);
		this.transactionDetails = transactionDetails;
		// TODO Auto-generated constructor stub
	}
}
