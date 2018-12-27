package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4192032820850153348L;

	public SignupResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignupResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	@JsonProperty(value = "isRegistered")
	private boolean isRegistered;
	
	

	// Will
	private boolean isPaymentPending;
	
	private boolean isEkycEkybApproved;
	
	

	public boolean isEkycEkybApproved() {
		return isEkycEkybApproved;
	}

	public void setEkycEkybApproved(boolean isEkycEkybApproved) {
		this.isEkycEkybApproved = isEkycEkybApproved;
	}

	/**
	 * @return the isPaymentPending
	 */
	public boolean isPaymentPending() {
		return isPaymentPending;
	}

	/**
	 * @param isPaymentPending
	 *            the isPaymentPending to set
	 */
	public void setPaymentPending(boolean isPaymentPending) {
		this.isPaymentPending = isPaymentPending;
	}

	/**
	 * @return the isRegistered
	 */
	@JsonProperty(value = "isRegistered")
	public boolean isRegistered() {
		return isRegistered;
	}

	/**
	 * @param isRegistered
	 *            the isRegistered to set
	 */
	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
}
