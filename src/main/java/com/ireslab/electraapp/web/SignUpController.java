package com.ireslab.electraapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.AccountVerificationResponse;
import com.ireslab.electraapp.model.ActivationCodeRequest;
import com.ireslab.electraapp.model.ActivationCodeResponse;
import com.ireslab.electraapp.service.SignupService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;

/**
 * @author iRESlab
 *
 */
@RestController
public class SignUpController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private SignupService signupService;
	
	

	
	/**
	 * use to check mobile number registered or not.
	 * 
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/checkMobileNumberRegistration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AccountVerificationResponse verifyAccount(
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode)
			throws JsonProcessingException {

		AccountVerificationResponse accVerificationResponse = null;
		LOG.info("Account verification request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		if (mobileNumber == null || countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		accVerificationResponse = signupService.verifyAccount(mobileNumber, countryDialCode);
		LOG.info("Account verification response sent - " + objectWriter.writeValueAsString(accVerificationResponse));

		return accVerificationResponse;
	}

	
	/**
	 * use to send activation code SMS.
	 * 
	 * @param mobileNumber
	 * @param countryDialCode
	 * @param requestType
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/requestActivationCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivationCodeResponse requestActivationCode(
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode,
			@RequestParam(value = "requestType", required = false) String requestType) throws JsonProcessingException {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.info("Request Activation code request received - \n\t mobileNumber : " + mobileNumber
				+ ",\n\t countryCode : " + countryDialCode);

		activationCodeResponse = signupService.requestActivationCode(mobileNumber, countryDialCode, requestType);
		LOG.info("Request Activation code response sent - " + objectWriter.writeValueAsString(activationCodeResponse));

		return activationCodeResponse;
	}

	
	/**
	 * use to validate activation code with mobile number.
	 * 
	 * @param activationCodeRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/validateActivationCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivationCodeResponse validateActivationCode(@RequestBody ActivationCodeRequest activationCodeRequest)
			throws JsonProcessingException {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.info("Validate Activation code request received - "
				+ objectWriter.writeValueAsString(activationCodeRequest));

		activationCodeResponse = signupService.validateActivationCode(activationCodeRequest);
		LOG.info(
				"Validate Activation code response sent - " + objectWriter.writeValueAsString(activationCodeResponse));

		return activationCodeResponse;
	}

	
	

}
