package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CashOutResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountBalance;

	public CashOutResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public CashOutResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param code
	 * @param message
	 * @param accountBalance
	 */
	public CashOutResponse(Integer status, Integer code, String message, String accountBalance) {
		super(status, code, message);
		this.accountBalance = accountBalance;
	}

	/**
	 * @return the accountBalance
	 */
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance
	 *            the accountBalance to set
	 */
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
}
