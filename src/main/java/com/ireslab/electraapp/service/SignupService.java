package com.ireslab.electraapp.service;

import com.ireslab.electraapp.model.AccountVerificationResponse;
import com.ireslab.electraapp.model.ActivationCodeRequest;
import com.ireslab.electraapp.model.ActivationCodeResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.SignupRequest;
import com.ireslab.electraapp.model.SignupResponse;
import com.ireslab.sendx.electra.model.ClientRegistrationResponse;

/**
 * @author Nitin
 *
 */
public interface SignupService {

	public AccountVerificationResponse verifyAccount(Long mobileNumber, String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public GenericResponse validateMobileNumber(Long mobileNumber, String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public ActivationCodeResponse requestActivationCode(Long mobileNumber, String countryDialCode, String requestType);

	/**
	 * @param activationCodeRequest
	 * @return
	 */
	public ActivationCodeResponse validateActivationCode(ActivationCodeRequest activationCodeRequest);

	/**
	 * @param signupRequest
	 * @param clientResponse 
	 * @return
	 */
	public SignupResponse registerAccount(SignupRequest signupRequest,boolean isUserRegistration, ClientRegistrationResponse clientResponse);


}
