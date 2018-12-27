package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiscConfigDetailsResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String payFundBankTransferMessage;

	public MiscConfigDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MiscConfigDetailsResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the payFundBankTransferMessage
	 */
	public String getPayFundBankTransferMessage() {
		return payFundBankTransferMessage;
	}

	/**
	 * @param payFundBankTransferMessage
	 *            the payFundBankTransferMessage to set
	 */
	public void setPayFundBankTransferMessage(String payFundBankTransferMessage) {
		this.payFundBankTransferMessage = payFundBankTransferMessage;
	}

}
