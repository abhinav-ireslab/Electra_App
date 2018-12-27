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
public class ActivationCodeResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392225273634950619L;

	private Short retryCounter;

	public ActivationCodeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivationCodeResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public ActivationCodeResponse(Integer status, Integer code, String message, Short retryCounter) {
		super(status, code, message);
		this.retryCounter = retryCounter;
	}

	/**
	 * @return the retryCounter
	 */
	public Short getRetryCounter() {
		return retryCounter;
	}

	/**
	 * @param retryCounter
	 *            the retryCounter to set
	 */
	public void setRetryCounter(Short retryCounter) {
		this.retryCounter = retryCounter;
	}

}
