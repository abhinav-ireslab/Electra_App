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
public class CheckoutBanksResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2211217864066742071L;

	private List<CheckoutBankDetailsDto> checkoutBankDetailsList;

	public CheckoutBanksResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckoutBanksResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public CheckoutBanksResponse(Integer status, Integer code, String message,
			List<CheckoutBankDetailsDto> bankDetailsDtos) {
		super(status, code, message);
		this.checkoutBankDetailsList = bankDetailsDtos;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the checkoutBankDetailList
	 */
	public List<CheckoutBankDetailsDto> getCheckoutBankDetailList() {
		return checkoutBankDetailsList;
	}

	/**
	 * @param checkoutBankDetailList
	 *            the checkoutBankDetailList to set
	 */
	public void setCheckoutBankDetailList(List<CheckoutBankDetailsDto> checkoutBankDetailList) {
		this.checkoutBankDetailsList = checkoutBankDetailList;
	}
}
