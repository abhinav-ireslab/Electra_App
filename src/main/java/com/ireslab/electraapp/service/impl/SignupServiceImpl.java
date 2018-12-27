package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.ireslab.electraapp.electra.Status;
import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.entity.AccountVerification;
import com.ireslab.electraapp.entity.Country;
import com.ireslab.electraapp.entity.OAuthAccessToken;
import com.ireslab.electraapp.entity.OAuthRefreshToken;
import com.ireslab.electraapp.entity.Profile;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.AccountVerificationResponse;
import com.ireslab.electraapp.model.ActivationCodeRequest;
import com.ireslab.electraapp.model.ActivationCodeResponse;
import com.ireslab.electraapp.model.AgentRequest;
import com.ireslab.electraapp.model.AgentResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.SignupRequest;
import com.ireslab.electraapp.model.SignupResponse;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.notification.MailMessage;
import com.ireslab.electraapp.notification.MailService;
import com.ireslab.electraapp.notification.MailType;
import com.ireslab.electraapp.notification.SMSService;
import com.ireslab.electraapp.notification.SendxConfig;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.repository.AccountVerificationRepository;
import com.ireslab.electraapp.repository.CountryRepository;
import com.ireslab.electraapp.repository.OAuthAccessTokenRepository;
import com.ireslab.electraapp.repository.OAuthRefreshTokenRepository;
import com.ireslab.electraapp.repository.ProfileRepository;
import com.ireslab.electraapp.service.CommonService;
import com.ireslab.electraapp.service.ProfileImageService;
import com.ireslab.electraapp.service.SignupService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.CommonUtils;
import com.ireslab.electraapp.util.Constants;
import com.ireslab.electraapp.util.PropConstants;
import com.ireslab.sendx.electra.model.ClientProfile;
import com.ireslab.sendx.electra.model.ClientRegistrationResponse;

/**
 * @author iRESlab
 *
 */
@Service
public class SignupServiceImpl implements SignupService {

	private static final Logger LOG = LoggerFactory.getLogger(SignupServiceImpl.class);

	
	@Autowired
	private TransactionalApiService transactionalApiService;

	@Autowired
	private SMSService smsSender;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendxConfig sendxConfig;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProfileRepository profileRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private AccountVerificationRepository accVerificationRepo;

	@Autowired
	private ScheduledTransactionExecutor scheduledTransactionExecutor;

	@Autowired
	private ProfileImageService profileImageService;

	@Autowired
	private OAuthAccessTokenRepository accessTokenRepo;

	@Autowired
	private OAuthRefreshTokenRepository refreshTokenRepo;

	

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.SignupService#validateMobileNumber(java.lang.Long, java.lang.String)
	 */
	@Override
	public GenericResponse validateMobileNumber(Long mobileNumber, String countryCode) {

		GenericResponse genericResponse = null;
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
				countryCode);

		if (account != null) {
			LOG.info("Mobile Number : " + countryCode + mobileNumber + " is already registered");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.MOBILE_ALREADY_REGISTERED,
					PropConstants.MOBILE_ALREADY_REGISTERED);
		}

		LOG.info("Mobile Number : " + countryCode + mobileNumber + " is available");
		genericResponse = new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.MOBILE_AVAILABLE));

		return genericResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.SignupService#verifyAccount(java.lang.Long, java.lang.String)
	 */
	@Override
	public AccountVerificationResponse verifyAccount(Long mobileNumber, String countryDialCode) {

		AccountVerificationResponse accVerificationResponse = null;
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
				countryDialCode);

		

		if (account != null) {

			LOG.info("Account already exists for mobileNumber - " + countryDialCode + mobileNumber
					+ ", getting the account status from electra based on userCorrelationId - "
					+ account.getUserCorrelationId());
			UserProfile userProfile =null;
			if(account.getClientCorrelationId()!=null) {
				userProfile = transactionalApiService.invokeUserProfileAPI(account.getUserCorrelationId());
				LOG.debug("Account status from electra - " + userProfile.getAccountStatus());
			}else {
				
				userProfile = transactionalApiService.invokeClientProfileAPI(account.getUserCorrelationId());
				LOG.debug("Account status from electra - " + userProfile.getAccountStatus());
			}
			
			

			if (userProfile.getAccountStatus().equals(Status.SUSPENDED)) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_SUSPENDED,
						PropConstants.ACCOUNT_SUSPENDED);

			} 
			
			if (userProfile.getAccountStatus().equals(Status.TERMINATED)) {

				String username = countryDialCode + "_" + mobileNumber;

				LOG.info("Account for mobileNumber - " + username
						+ " is Terminated and is available for re-registration");

				OAuthAccessToken authAccessToken = accessTokenRepo.findByUserName(username);

				if (authAccessToken != null) {
					OAuthRefreshToken authRefreshToken = refreshTokenRepo
							.findByTokenId(authAccessToken.getRefresh_token());

					if (authRefreshToken != null) {
						LOG.debug("Deleting refresh token based on refresh token - "
								+ authAccessToken.getRefresh_token());
						refreshTokenRepo.delete(authRefreshToken);
					}
					LOG.debug("Deleting access token for username - " + authAccessToken.getUserName());
					accessTokenRepo.delete(authAccessToken);
				}

			} else {
				LOG.info("Account with Mobile Number : " + countryDialCode + mobileNumber + " already exists");
				return new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
						getMessage(PropConstants.ACCOUNT_EXISTS), true);
			}
		}

		LOG.info("Account with Mobile Number : " + countryDialCode + mobileNumber + " not exists and is available");
		accVerificationResponse = new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.ACCOUNT_NOT_EXISTS), false);

		return accVerificationResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.SignupService#requestActivationCode(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActivationCodeResponse requestActivationCode(Long mobileNumber, String countryCode, String requestType) {

		short requestRetryAttempts = 0;
		String activationCode = null;
		ActivationCodeResponse activationCodeResponse = null;
		Date currentDate = null;

		Integer countryId = commonService.getCountryDetails(countryCode).getCountryId();

		AccountVerification accountVerification = accVerificationRepo
				.findByMobileNumberAndCountryId(BigInteger.valueOf(mobileNumber), countryId);

		if (requestType != null && !requestType.equalsIgnoreCase("signup")) {

			if (accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
					countryCode) == null) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_NOT_EXISTS,
						PropConstants.ACCOUNT_NOT_EXISTS);
			}
		}

		if (accountVerification != null) {
			requestRetryAttempts = accountVerification.getRetryAttempts();

			// Retry limit reached
			if (requestRetryAttempts <= 0) {
				LOG.error("Retry Limit for Requesting Activation Code reached - " + requestRetryAttempts);
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST,
						PropConstants.ACTIVATION_CODE_REQUEST_LIMIT_REACHED);
			}
			requestRetryAttempts -= 1;

		} else {
			currentDate = new Date();
			requestRetryAttempts = sendxConfig.activationCodeRequestRetryLimit;
			accountVerification = new AccountVerification();
			accountVerification.setMobileNumber(BigInteger.valueOf(mobileNumber));
			accountVerification.setCountryId(countryId);
			accountVerification.setCreatedDate(currentDate);
			accountVerification.setModifiedDate(currentDate);
			accountVerification.setVerificationType(Constants.VERIFICATION_TYPE_SIGNUP);
		}

		// Check Test Mode &
		if (sendxConfig.isTestMode) {
			LOG.debug("Test Mode is enabled, Activation code configured is - " + Constants.ACTIVATION_CODE);
			activationCode = String.valueOf(Constants.ACTIVATION_CODE);
		} else {

			// Generate Activation Code
			activationCode = String.valueOf(CommonUtils.generateUniqueCode(Constants.ACTIVATION_CODE_LENGTH));
		}

		LOG.info("Activation code: \n\tRequest Retry Attempts - " + requestRetryAttempts + ", \n\tCode - "
				+ activationCode);

		// Save in database
		currentDate = new Date();
		accountVerification.setCreatedDate(currentDate);
		accountVerification.setModifiedDate(currentDate);
		accountVerification.setActivationCode(activationCode);
		accountVerification.setRetryAttempts(requestRetryAttempts);
		accVerificationRepo.save(accountVerification);

		String userMobileNumber = countryCode.replace("+", "") + String.valueOf(mobileNumber);

		// send Activation Code via SMS
		if (!sendxConfig.isTestMode) {

			String activationCodeMsg = null;
			boolean useShortCodeApi = false;

			if (countryCode.equalsIgnoreCase(Constants.US_COUNTRY_DIAL_CODE)) {
				useShortCodeApi = true;
				activationCodeMsg = String.format(smsSender.getShortCodeApiActivationCodeMessage(), activationCode);

			} else {
				activationCodeMsg = String.format(smsSender.getActivationCodeMessage(), activationCode);
			}

			if (!smsSender.sendMessage(userMobileNumber, activationCodeMsg, useShortCodeApi)) {

				LOG.error("Error occurred while sending Activation Code SMS message . . . .");
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
						PropConstants.INTERNAL_SERVER_ERROR);
			}
		}

		activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.ACTIVATION_CODE_SENT), requestRetryAttempts);

		return activationCodeResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.SignupService#validateActivationCode(com.ireslab.electraapp.model.ActivationCodeRequest)
	 */
	@Override
	public ActivationCodeResponse validateActivationCode(@RequestBody ActivationCodeRequest activationCodeRequest) {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.info("Getting account verification details based on mobile number and country code . . .");

		AccountVerification accountVerification = accVerificationRepo.findByMobileNumberAndCountryId(
				BigInteger.valueOf(activationCodeRequest.getMobileNumber()),
				commonService.getCountryDetails(activationCodeRequest.getCountryDialCode()).getCountryId());

		// No activation code send yet
		if (accountVerification == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}

		/*
		 * Comparing activation code and checking activation code validity
		 */
		//long tempMinCheck=179000;
		if (!activationCodeRequest.getActivationCode().equalsIgnoreCase(accountVerification.getActivationCode())
				|| CommonUtils.calculateTimeDiffInMin(accountVerification.getModifiedDate(),
						new Date()) > sendxConfig.activationCodeValidity) {
			
			

			LOG.error("Activation code invalid or expired !!");

			activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(),
					AppStatusCodes.INVALID_ACTIVATION_CODE, getMessage(PropConstants.INVALID_ACTIVATION_CODE));
			activationCodeResponse.setRetryCounter(accountVerification.getRetryAttempts());
			return activationCodeResponse;
		}
		
		

		LOG.info("Activation code validated successfully . . . ");
		activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				PropConstants.SUCCESS);
		return activationCodeResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.SignupService#registerAccount(com.ireslab.electraapp.model.SignupRequest, boolean, com.ireslab.sendx.electra.model.ClientRegistrationResponse)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SignupResponse registerAccount(SignupRequest signupRequest,boolean isUserRegistration,ClientRegistrationResponse clientResponse) {

		String userCorrelationId = null;
		SignupResponse signupResponse = null;
		Date currentDate = new Date();

		/*******************************
		 * USER ACCOUNT CREATION
		 *******************************/

		AccountVerification accountVerification = accVerificationRepo.findByMobileNumberAndCountryId(
				BigInteger.valueOf(signupRequest.getMobileNumber()),
				commonService.getCountryDetails(signupRequest.getCountryDialCode()).getCountryId());

		if (accountVerification == null) {
			LOG.error("Unauthorized request - Activation code validation is required . . . ");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}

		Country country = countryRepo
				.findOne(commonService.getCountryDetails(signupRequest.getCountryDialCode()).getCountryId());

		LOG.info("Initiating User Account creation on Electra Platform ");

		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(
				BigInteger.valueOf(signupRequest.getMobileNumber()), signupRequest.getCountryDialCode());

		if (account == null) {
			account = new Account();
			if(signupRequest.getUserCorrelationId()!=null) {
			userCorrelationId = signupRequest.getUserCorrelationId();
			}else {
				userCorrelationId = UUID.randomUUID().toString();
			}
		} else if (account != null) {
			userCorrelationId = account.getUserCorrelationId();
			LOG.info("Updating existing account with new signup details having accountId - " + account.getAccountId());
		}
		
		// migrating uniqueCode to electra server side.
		String uniqueCode =null;
		if(signupRequest.getUniqueCode()==null) {
			uniqueCode = String.valueOf(CommonUtils.generateUniqueCode(Constants.UNIQUE_CODE_LENGTH));
		}else {
			
			uniqueCode = signupRequest.getUniqueCode();
		}
		
		
		signupRequest.setUniqueCode(uniqueCode);
		signupRequest.setUserCorrelationId(userCorrelationId);

		LOG.info("Initiating User Account creation on Electra Platform ");
		
		/* *******
		 * Getting client details with company code to set it in signupRequest 
		 * to perform logic to invokeUserOnboardingApi
		 * *******/
		ClientProfile clientProfile = transactionalApiService.invokeClientByCompanyCodeAPI(signupRequest.getCompanyCode());
		signupRequest.setCorrelationId(clientProfile.getClientCorrelationId());
		//**logic ends**
		
		List<com.ireslab.sendx.electra.model.UserProfile> userProfiles=null;

		if (isUserRegistration) {
			userProfiles = transactionalApiService.invokeUserOnboardingApi(signupRequest);
			// Creating User Account on Electra platform
			if (userProfiles == null || !userProfiles.get(0).isRegistered()) {

				LOG.error("Account Creation on Electra Platform failed");
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
						PropConstants.INTERNAL_SERVER_ERROR);
			} 
		}else {
			
			userCorrelationId=clientProfile.getClientCorrelationId();
		}
		String profileImageUrl = null;
		LOG.debug("Saving Profile Image on server");
		if(signupRequest.getProfileImageValue() != null && signupRequest.getProfileImageValue() != "") {
			
			profileImageUrl = profileImageService.saveImage("profile", signupRequest.getMobileNumber().toString(),
				signupRequest.getProfileImageValue());
		}

		String idProofUrl = null;
		LOG.debug("Saving ID proof Image on server");
		if(signupRequest.getIdProofImageValue() != null && signupRequest.getIdProofImageValue() != "") {
			idProofUrl = profileImageService.saveImage("idproof", signupRequest.getMobileNumber().toString(),
				signupRequest.getIdProofImageValue());
		}

		account.setMobileNumber(BigInteger.valueOf(signupRequest.getMobileNumber()));
		account.setUserCorrelationId(userCorrelationId);
		account.setCountry(country);
		account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		account.setMpin(passwordEncoder.encode(signupRequest.getmPIN()));
		account.setUniqueCode(uniqueCode);
		account.setStatus(Status.ACTIVE.name());
		account.setDeviceId(signupRequest.getDeviceId());
		account.setDeviceType(signupRequest.getDeviceType());
		account.setCreatedDate(currentDate);
		account.setModifiedDate(currentDate);
		account.setProfileImageUrl(profileImageUrl);
		account.setIdProofImageUrl(idProofUrl);
		account.setResidentialAddress(signupRequest.getResidentialAddress());
		account.setClientCorrelationId(signupRequest.getClientCorrelationId());
		
		if(isUserRegistration) {
			account.setIsClient(false);
		}else {
			account.setIsClient(true);
		}
		
		account = accountRepo.save(account);

		LOG.debug("User Account saved successfully in database");

		Profile userProfile = account.getProfile();
		if (userProfile == null) {
			userProfile = new Profile();
		} else if (userProfile != null) {
			LOG.info("Updating existing profile with new profile information having accountId  ");
		}

		modelMapper.map(signupRequest, userProfile);
		userProfile.setAccount(account);
		userProfile.setCreatedDate(currentDate);
		userProfile.setModifiedDate(currentDate);
		userProfile.setCountry(country);
		
		userProfile = profileRepo.save(userProfile);
		LOG.debug("User Profile saved successfully in database");

		/*******************************
		 * AGENT ACCOUNT CREATION
		 *******************************/

		// Save the agent details on Electra
		if (signupRequest.isLocateAgent()) {
			signupRequest = (SignupRequest) ((AgentRequest) signupRequest)
					.setAgentMobNo(signupRequest.getMobileNumber())
					.setCountryDialCode(signupRequest.getCountryDialCode()).setCorrelationId(userCorrelationId);
			// .setIdProofImageValue(signupRequest.getIdProofImageValue());

			LOG.info("Signup request contains Agent Registration data");
			AgentResponse agentResponses = transactionalApiService.invokeAgentOnboardingApi(signupRequest,
					account.getUserCorrelationId());

			if (agentResponses == null) {
				LOG.error("Agent account creation on Electra Platform failed");
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
						PropConstants.INTERNAL_SERVER_ERROR);
			}
			LOG.info("User successfully registered as Agent - ");
		}

		scheduledTransactionExecutor.executeScheduledTransactions(signupRequest.getMobileNumber(),
				signupRequest.getCountryDialCode());

		if (accountVerification != null) {
			accVerificationRepo.delete(accountVerification);
		}

		/*
		 * Welcome email to users
		 */
		MailMessage mailMessage = new MailMessage();
		mailMessage.setToEmailAddresses(new String[] { userProfile.getEmailAddress() });
		mailMessage.setMailType(MailType.WELCOME_EMAIL);

		Map<String, Object> msgParams = new HashMap<>();
		msgParams.put("firstName", userProfile.getFirstName());
		msgParams.put("lastName", userProfile.getLastName());
		mailMessage.setModel(msgParams);

		mailService.sendEmail(mailMessage);

		signupResponse = new SignupResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
		signupResponse.setIsRegistered(true);
		if(userProfiles!=null) {
			
			if(userProfiles.get(0)!=null) {
				signupResponse.setEkycEkybApproved(userProfiles.get(0).isEkycEkybApproved());
				}
		}else {
			
			//TODO write logic to get ekyc approvel status  from client table.
			signupResponse.setEkycEkybApproved(false);
		}
		
		
		

		return signupResponse;
	}

	/**
	 * @param key
	 * @return
	 */
	private String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}

}
