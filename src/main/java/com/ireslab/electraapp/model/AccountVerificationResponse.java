package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AccountVerificationResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "isAccountExists")
	private boolean isAccountExists;
	
	@JsonProperty(value = "isCompanyCodeExists")
	private boolean isCompanyCodeExists;

	private UserProfile profile;

	public AccountVerificationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isAccountExists
	 * @param profile
	 */
	public AccountVerificationResponse(boolean isAccountExists, UserProfile profile) {
		super();
		this.isAccountExists = isAccountExists;
		this.profile = profile;
	}

	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public AccountVerificationResponse(Integer status, Integer code, String message, boolean isAccountExists) {
		super(status, code, message);
		this.isAccountExists = isAccountExists;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public AccountVerificationResponse(Integer status, Integer code, String message, boolean isAccountExists, boolean isCompanyCodeExists) {
		super(status, code, message);
		this.isAccountExists = isAccountExists;
		this.isCompanyCodeExists = isCompanyCodeExists;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param code
	 * @param message
	 */
	public AccountVerificationResponse(Integer status, Integer code, String message, boolean isAccountExists,
			UserProfile profile) {
		super(status, code, message);
		this.isAccountExists = isAccountExists;
		this.profile = profile;
		// TODO Auto-generated constructor stub
	}

	public AccountVerificationResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the isAccountExists
	 */
	@JsonIgnore(value = true)
	public boolean isAccountExists() {
		return isAccountExists;
	}

	/**
	 * @param isAccountExists
	 *            the isAccountExists to set
	 */
	public void setAccountExists(boolean isAccountExists) {
		this.isAccountExists = isAccountExists;
	}

	@JsonIgnore(value = true)
	public boolean isCompanyCodeExists() {
		return isCompanyCodeExists;
	}

	public void setCompanyCodeExists(boolean isCompanyCodeExists) {
		this.isCompanyCodeExists = isCompanyCodeExists;
	}

	/**
	 * @return the profile
	 */
	public UserProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

}
