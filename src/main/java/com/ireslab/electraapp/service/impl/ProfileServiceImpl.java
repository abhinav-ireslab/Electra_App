package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.entity.AccountVerification;
import com.ireslab.electraapp.entity.Profile;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.AgentRequestBody;
import com.ireslab.electraapp.model.AgentResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.notification.SendxConfig;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.repository.AccountVerificationRepository;
import com.ireslab.electraapp.service.AccountService;
import com.ireslab.electraapp.service.CommonService;
import com.ireslab.electraapp.service.ProfileImageService;
import com.ireslab.electraapp.service.ProfileService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

/**
 * @author iRESlab
 *
 */
@Service
public class ProfileServiceImpl implements ProfileService {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileServiceImpl.class);
	
	

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountService accountService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountVerificationRepository accVerificationRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TransactionalApiService transactionalApiService;

	@Autowired
	private ProfileImageService profileImageService;

	@Autowired
	private ObjectWriter objectWriter;
	
	@Autowired
	private SendxConfig sendxConfig;
	
	

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileService#editUserProfile(com.ireslab.electraapp.model.UserProfile, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UserProfile editUserProfile(UserProfile userProfile, HttpServletRequest request) {

		Account account = accountService.getAccount(userProfile.getMobileNumber(), userProfile.getCountryDialCode());

		Profile profile = account.getProfile();
		try {
		profile.setFirstName(userProfile.getFirstName());
		profile.setLastName(userProfile.getLastName());
		profile.setEmailAddress(userProfile.getEmailAddress());
		profile.setContactAddress(userProfile.getContactAddress());
		

		if (userProfile.getProfileImageValue() != null) {
			userProfile = profileImageService.saveProfileImage(userProfile, request);
			account.setProfileImageUrl(userProfile.getProfileImageUrl());
		}

		account.setProfile(profile);
		
		
		
		
		accountRepo.save(account);
		}catch (Exception e) {
			//System.out.println(e);
			e.printStackTrace();
		}
		userProfile.setStatus(HttpStatus.OK.value());
		userProfile.setCode(AppStatusCodes.SUCCESS);
		userProfile.setMessage(PropConstants.SUCCESS);

		LOG.debug("Getting account balance for user from Electra");
		UserProfile electraUserProfile =null;
		if(account.getClientCorrelationId()!=null) {
		
		electraUserProfile = transactionalApiService.invokeUserProfileAPI(account.getUserCorrelationId());
		}else {
			
			electraUserProfile =transactionalApiService.invokeClientProfileAPI(account.getUserCorrelationId());
		}
		if (electraUserProfile == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
					messageSource.getMessage(PropConstants.INTERNAL_SERVER_ERROR, null, Locale.getDefault()));
		}

		
		userProfile.setAccountBalance(electraUserProfile.getAccountBalance());
		return userProfile;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileService#getUserProfile(java.math.BigInteger, java.lang.String)
	 */
	@Override
	public UserProfile getUserProfile(BigInteger mobileNumber, String countryDialCode) {

		/*
		 * Retrieves the User's profile as well as Agent specific data
		 * 
		 */
		/***********************************
		 * USER PROFILE DATA
		 ***********************************/

		UserProfile userProfile = new UserProfile(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
		Account account = accountService.getAccount(mobileNumber, countryDialCode);
		boolean ismPINSetup = (StringUtils.isEmpty(account.getMpin())) ? false : true;

		if (!ismPINSetup) {
			userProfile = new UserProfile(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
					messageSource.getMessage(PropConstants.MPIN_SETUP_REQUIRED, null, Locale.getDefault()));
		}

		modelMapper.map(account.getProfile(), userProfile);
		userProfile.setIsmPINSetup(ismPINSetup);
		userProfile.setUniqueCode(account.getUniqueCode());

		UserProfile profile=null;
	
		if (!account.getIsClient()) {
			
			profile = transactionalApiService.invokeUserProfileAPI(account.getUserCorrelationId());
			try {
				LOG.debug("invokeUserProfileAPI :"+objectWriter.writeValueAsString(profile));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			profile = transactionalApiService.invokeClientProfileAPI(account.getUserCorrelationId());
			try {
				LOG.debug("invokeClientProfileAPI :"+objectWriter.writeValueAsString(profile));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (profile == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.INTERNAL_SERVER_ERROR);
		}

		
		com.ireslab.sendx.electra.utils.Status accountStatus = profile.getAccountStatus();

		LOG.debug("Updating account status in database - " + accountStatus);
		account.setStatus(accountStatus.name());
		accountRepo.save(account);

		if (accountStatus.equals(com.ireslab.sendx.electra.utils.Status.SUSPENDED)) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_SUSPENDED,
					PropConstants.ACCOUNT_SUSPENDED);

		} else if (accountStatus.equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_TERMINATED,
					PropConstants.ACCOUNT_TERMINATED);
		}

		/***********************************
		 * AGENT PROFILE & BUSINESS DATA
		 ***********************************/

		AgentRequestBody agentRequestBody = new AgentRequestBody();
		agentRequestBody.setMobileNumber(mobileNumber);
		agentRequestBody.setCountryDialCode(countryDialCode);
		agentRequestBody.setUserCorreletionId(account.getUserCorrelationId());

		try {
			LOG.debug("Getting agent account data from Electra" + objectWriter.writeValueAsString(agentRequestBody));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		AgentResponse agentResponses = transactionalApiService.invokeGetAgentAPI(agentRequestBody);
		if (agentResponses != null) {
			if (null != agentResponses.getEkyc()) {
				userProfile.setEkyc(agentResponses.getEkyc());
			}
		}
		userProfile.setIsClient(account.getIsClient());
		userProfile.setAccountBalance(profile.getAccountBalance());
		userProfile.setProfileImageUrl(account.getProfileImageUrl());
		userProfile.setElectraAppUrl(sendxConfig.getElectraAppUrl());
		userProfile.setCountryCurrrency(account.getCountry().getIso4217CurrencyAlphabeticCode());
		userProfile.setEkycEkybApproved(profile.isEkycEkybApproved());
		userProfile.setCurrencySymbol(profile.getCurrencySymbol());
		// get Notification count for mobile no.
		SendxElectraResponse sendxElectraResponse = commonService.getAllNotification(""+mobileNumber);
		List<com.ireslab.sendx.electra.dto.Notification> notificationList = sendxElectraResponse.getNotificationList();
		
		if(notificationList!=null) {
			userProfile.setNotificationCount(""+notificationList.size());
		}else {
			userProfile.setNotificationCount("0");
		}
		
		
		
		return userProfile;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileService#getUserProfileByUniqueCode(java.lang.String)
	 */
	@Override
	public UserProfile getUserProfileByUniqueCode(String uniqueCode) {
		
		UserProfile userProfile = new UserProfile(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
		Account account = accountService.getAccountByUniqueCode(uniqueCode);

		if (account == null) {
			
			UserProfile userProfileModel = commonService.searchUserByuniqueCodeInElectra(uniqueCode);
			
			
			if(userProfileModel== null) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
			}else {
				try {
					LOG.debug("profile json :"+objectWriter.writeValueAsString(userProfileModel));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (userProfileModel.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.SUSPENDED)) {
					throw new BusinessException(HttpStatus.OK, AppStatusCodes.USER_SUSPENDED,
							PropConstants.USER_SUSPENDED);

				} else if (userProfileModel.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {
					throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST,
							PropConstants.INVALID_REQUEST);
				}
				
				userProfile.setFirstName(userProfileModel.getFirstName());
				userProfile.setLastName(userProfileModel.getLastName());
				userProfile.setMobileNumber(userProfileModel.getMobileNumber());
				userProfile.setEmailAddress(userProfileModel.getEmailAddress());
				userProfile.setCountryDialCode(userProfileModel.getCountryDialCode());
				userProfile.setUniqueCode(userProfileModel.getUniqueCode());
				
				userProfile.setCountryName(userProfileModel.getCountryName());
				userProfile.setIso4217CurrencyAlphabeticCode(userProfileModel.getIso4217CurrencyAlphabeticCode());
				userProfile.setCountryCurrrency(userProfileModel.getIso4217CurrencyAlphabeticCode());
				
			}
			
		}
		if(account!=null) {
			UserProfile userModel = commonService.searchUserByuniqueCodeInElectra(uniqueCode);
			if (userModel.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.SUSPENDED)) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.USER_SUSPENDED,
						PropConstants.USER_SUSPENDED);

			} else if (userModel.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST,
						PropConstants.INVALID_REQUEST);
			}
			
		modelMapper.map(account.getProfile(), userProfile);
		userProfile.setIsClient(account.getIsClient());
		userProfile.setUniqueCode(account.getUniqueCode());
		userProfile.setElectraAppUrl(sendxConfig.getElectraAppUrl());
		userProfile.setCountryCurrrency(account.getCountry().getIso4217CurrencyAlphabeticCode());
		userProfile.setCountryName(account.getCountry().getCountryName());
		}
		
		
		try {
			LOG.debug("profile json :"+objectWriter.writeValueAsString(userProfile));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			LOG.debug("userProfile --------------->: "+objectWriter.writeValueAsString(userProfile));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userProfile;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileService#updatePasswordOrMpin(com.ireslab.electraapp.model.UserProfile)
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public GenericResponse updatePasswordOrMpin(UserProfile userProfile) {

		GenericResponse genericResponse = null;
		boolean isPasswordUpdate = false;

		Account account = accountService.getAccount(userProfile.getMobileNumber(), userProfile.getCountryDialCode());
		if (account == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}

		Integer countryId = commonService.getCountryDetails(userProfile.getCountryDialCode()).getCountryId();
		AccountVerification accountVerification = accVerificationRepo
				.findByMobileNumberAndCountryId(userProfile.getMobileNumber(), countryId);

		String verificationCode = userProfile.getVerificationCode();
		if (verificationCode == null || !verificationCode.equalsIgnoreCase(accountVerification.getActivationCode())) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}
		

		if (userProfile.getPassword() != null) {
			// update password
			isPasswordUpdate = true;
			account.setPassword(passwordEncoder.encode(userProfile.getPassword()));
		} else if (userProfile.getmPIN() != null) {
			// update mpin
			account.setMpin(passwordEncoder.encode(userProfile.getmPIN()));
		}

		account.setCreatedDate(new Date());
		account.setModifiedDate(new Date());
		accountRepo.save(account);

		if (accountVerification != null) {
			accVerificationRepo.delete(accountVerification);
		}

		String successMessage = messageSource.getMessage(
				(isPasswordUpdate ? PropConstants.PASSWORD_UPDATE_SUCCESS : PropConstants.MPIN_UPDATE_SUCCESS), null,
				Locale.getDefault());

		genericResponse = new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage);
		return genericResponse;
	}
}
