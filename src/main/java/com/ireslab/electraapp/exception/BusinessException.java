package com.ireslab.electraapp.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom Exception for business related errors and messages
 * 
 * @author Nitin
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4295740555529242350L;

	private HttpStatus status;
	private Integer code;
	private String message;
	private String developerMessage;
	private String moreInfo;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(HttpStatus status, Integer code, String message) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
	}

	public BusinessException(HttpStatus status, Integer code, String message, Throwable cause) {
		super(cause);
		this.status = status;
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the developerMessage
	 */
	public String getDeveloperMessage() {
		return developerMessage;
	}

	/**
	 * @param developerMessage
	 *            the developerMessage to set
	 */
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	/**
	 * @return the moreInfo
	 */
	public String getMoreInfo() {
		return moreInfo;
	}

	/**
	 * @param moreInfo
	 *            the moreInfo to set
	 */
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessException [status=" + status + ", code=" + code + ", message=" + message + ", developerMessage="
				+ developerMessage + ", moreInfo=" + moreInfo + "]";
	}
	
	
}
