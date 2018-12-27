package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author iRESlab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendTokensResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817157167105669481L;

	public SendTokensResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendTokensResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public SendTokensResponse(Integer status, Integer code, String message, String accountBalance) {
		super(status, code, message);
		this.accountBalance = accountBalance;
	}

	private String accountBalance;

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
