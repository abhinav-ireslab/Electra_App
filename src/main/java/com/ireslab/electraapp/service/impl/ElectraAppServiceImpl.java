package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.entity.OAuthAccessToken;
import com.ireslab.electraapp.entity.OAuthRefreshToken;
import com.ireslab.electraapp.model.AccountVerificationResponse;
import com.ireslab.electraapp.model.SignupRequest;
import com.ireslab.electraapp.model.SignupResponse;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.repository.OAuthAccessTokenRepository;
import com.ireslab.electraapp.repository.OAuthRefreshTokenRepository;
import com.ireslab.electraapp.service.ElectraAppService;
import com.ireslab.electraapp.service.SignupService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.CommonUtils;
import com.ireslab.electraapp.util.Constants;
import com.ireslab.electraapp.util.PropConstants;
import com.ireslab.sendx.electra.model.ClientProfile;
import com.ireslab.sendx.electra.model.ClientRegistrationRequest;
import com.ireslab.sendx.electra.model.ClientRegistrationResponse;
import com.ireslab.sendx.electra.model.ClientSubscriptionRequest;
import com.ireslab.sendx.electra.model.ClientSubscriptionResponse;
import com.ireslab.sendx.electra.model.ClientSubscriptionUpdateRequest;
import com.ireslab.sendx.electra.model.ClientSubscriptionUpdateResponse;
import com.ireslab.sendx.electra.model.CompanyCodeResponse;
import com.ireslab.sendx.electra.model.SubscriptionPlanResponse;

@Service
public class ElectraAppServiceImpl implements ElectraAppService {

	private static final Logger LOG = LoggerFactory.getLogger(ElectraAppServiceImpl.class);

	@Autowired
	private TransactionalApiService transactionalApiService;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private OAuthAccessTokenRepository accessTokenRepo;

	@Autowired
	private OAuthRefreshTokenRepository refreshTokenRepo;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SignupService signupService;

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#generateCompanyCode()
	 */
	@Override
	public CompanyCodeResponse generateCompanyCode() {

		CompanyCodeResponse companyCodeResponse = transactionalApiService.invokeCompanyCodeAPI();
		return companyCodeResponse;
	}

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#verifyAccountByMobileNo(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public AccountVerificationResponse verifyAccountByMobileNo(Long mobileNumber, String countryDialCode,
			String companyCode) {
		AccountVerificationResponse accVerificationResponse = null;
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
				countryDialCode);

		boolean isCompanyCodeExist = false;
		ClientProfile clientByCompanyCode = transactionalApiService.invokeClientByCompanyCodeAPI(companyCode);
		if (clientByCompanyCode != null) {
			isCompanyCodeExist = true;
			if(!clientByCompanyCode.getClientStatus().equalsIgnoreCase("ACTIVE")) {
				
			LOG.info("Client  "+clientByCompanyCode.getClientStatus() +" with associated company code : " +companyCode);
			return new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
					getMessage(PropConstants.COMPANY_CODE_STATUS), true, isCompanyCodeExist);
			}
		}

		if (account != null) {

			LOG.info("Account already exists for mobileNumber - " + countryDialCode + mobileNumber
					+ ", getting the account status from electra based on userCorrelationId - "
					+ account.getUserCorrelationId());
			UserProfile userProfile =null;
			if(!account.getIsClient()) {
				 userProfile = transactionalApiService.invokeUserProfileAPI(account.getUserCorrelationId());
			}else {
				
				userProfile = transactionalApiService.invokeClientProfileAPI(account.getUserCorrelationId());
			}

			
			LOG.debug("Account status from electra - " + userProfile.getAccountStatus());

			if (userProfile.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {

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
						getMessage(PropConstants.ACCOUNT_EXISTS), true, isCompanyCodeExist);
			}
		}

		LOG.info("Account with Mobile Number : " + countryDialCode + mobileNumber + " not exists and is available");
		accVerificationResponse = new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.ACCOUNT_NOT_EXISTS), false, isCompanyCodeExist);

		return accVerificationResponse;
	}

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#registerClientUser(com.ireslab.electraapp.model.SignupRequest)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SignupResponse registerClientUser(SignupRequest signupRequest) {

		SignupResponse signupResponse = new SignupResponse();

		
		ClientProfile clientByCompanyCode = transactionalApiService
				.invokeClientByCompanyCodeAPI(signupRequest.getCompanyCode());

		// Company Code exists - Register as user
		if (clientByCompanyCode != null) {

			LOG.info("Company Code Exist, Registering as User ");
			
			
			
			ClientSubscriptionUpdateRequest clientSubscriptionUpdateRequest = new ClientSubscriptionUpdateRequest();
			clientSubscriptionUpdateRequest.setClientCorrelationId(clientByCompanyCode.getClientCorrelationId());
			clientSubscriptionUpdateRequest.setEmail(signupRequest.getEmailAddress());
			clientSubscriptionUpdateRequest.setMobileNo(signupRequest.getMobileNumber()+"");
			ClientSubscriptionUpdateResponse clientSubscriptionUpdateResponse = transactionalApiService.updateClientSubscriptionPlan(clientSubscriptionUpdateRequest);
			if(clientSubscriptionUpdateResponse.getErrors().size()>0) {
				
				signupResponse.setCode(101);
				signupResponse.setMessage("Email already exist !");
				signupResponse.setStatus(HttpStatus.OK.value());
			}
			else {
				String companyCode = clientByCompanyCode.getCompanyCode();
				String userCorrelationId = UUID.randomUUID().toString();	
				signupRequest.setCompanyCode(companyCode);
				signupRequest.setUserCorrelationId(userCorrelationId);
				signupRequest.setClientCorrelationId(clientByCompanyCode.getClientCorrelationId());
			signupResponse = signupService.registerAccount(signupRequest,true,null); // Entry in Client_User and Account,Profile Table with respect to sgt

			
			}
			
			

			// Company Code not exits - Register as Company/Client
		} else {

			LOG.info("Company Code not Exist, Registering as Client ");

			/**
			 * Registering as client
			 */
			ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();

			List<ClientProfile> clientProfileList = new ArrayList<>();
			ClientProfile clientProfile = new ClientProfile();

			String clientCorrelationId = UUID.randomUUID().toString(); // new correlation Id

			clientProfile.setClientCorrelationId(clientCorrelationId);
			clientProfile.setClientName(signupRequest.getCompanyName());
			clientProfile.setUserName(signupRequest.getFirstName() + " " + signupRequest.getLastName());
			clientProfile.setPassword(passwordEncoder.encode(signupRequest.getPassword()));//passwordEncoder.encode(signupRequest.getPassword())
			clientProfile.setEmailAddress(signupRequest.getEmailAddress());
			clientProfile.setContactNumber1(signupRequest.getMobileNumber() + "");
			clientProfile.setDescription("Electra App");
			clientProfile.setClientStatus("ACTIVE");
			clientProfile.setAccount_type(0+"");
			clientProfile.setCompanyCode(signupRequest.getCompanyCode());
			clientProfile.setCountryDialCode(signupRequest.getCountryDialCode());
			
			clientProfile.setResidentialAddress(signupRequest.getResidentialAddress());
			clientProfile.setFirstName(signupRequest.getFirstName());
			clientProfile.setLastName(signupRequest.getLastName());
			clientProfile.setDob(signupRequest.getDob());
			clientProfile.setScanDocumentType(signupRequest.getScanDocumentType());
			clientProfile.setScanDocumentId(signupRequest.getScanDocumentId());
			clientProfile.setScanDocumentFrontPage(signupRequest.getScanDocumentFrontPage());
			clientProfile.setScanDocumentBackPage(signupRequest.getScanDocumentBackPage());
			clientProfile.setGender(signupRequest.getGender());
			clientProfile.setCompanyPinCode(signupRequest.getPostalCode());
			clientProfile.setBusinessId(signupRequest.getBusinessId());
			clientProfile.setBusinessLat(signupRequest.getBusinessLat());
			clientProfile.setBusinessLong(signupRequest.getBusinessLong());
			clientProfile.setProfileImageValue(signupRequest.getProfileImageValue());
			clientProfile.setIdProofImageValue(signupRequest.getIdProofImageValue());
			String uniqueCode = String.valueOf(CommonUtils.generateUniqueCode(Constants.UNIQUE_CODE_LENGTH));
			clientProfile.setUniqueCode(uniqueCode);
			//clientProfile.setIdProofImageValue("sadDAdkadjasdjkasfkh nsfkcf;hIOSAF CNAhfasn");
			

			clientProfileList.add(clientProfile);
			clientRegistrationRequest.setClientProfile(clientProfileList);

			ClientRegistrationResponse clientResponse = (ClientRegistrationResponse) transactionalApiService
					.invokeUserClientOnboardingApi(clientRegistrationRequest); // client table
			ClientProfile resClientProfile = clientResponse.getClients().get(0);

			signupRequest.setUserCorrelationId(clientCorrelationId);
			signupRequest.setUniqueCode(uniqueCode);

			/**
			 * Registering client as user to SGT
			 */
			signupResponse = signupService.registerAccount(signupRequest,false,clientResponse);
			signupResponse.setEkycEkybApproved(resClientProfile.isEkycEkybApproved());
		}

		return signupResponse;
	}
	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#clientSubscriptionPlan(com.ireslab.sendx.electra.model.ClientSubscriptionRequest)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ClientSubscriptionResponse clientSubscriptionPlan(ClientSubscriptionRequest clientSubscriptionRequest) {
		ClientSubscriptionResponse clientSubscriptionResponse = null;
		
		
		clientSubscriptionResponse = (ClientSubscriptionResponse) transactionalApiService.invokeSubscriptionPlanApi(clientSubscriptionRequest);
		
		return clientSubscriptionResponse;
	}

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#getSubscriptionPlanList(java.lang.Long, java.lang.String)
	 */
	@Override
	public SubscriptionPlanResponse getSubscriptionPlanList(Long mobileNumber, String countryDialCode) {
		LOG.info("Request recieved for subscription plan list in service ");
		
		
		SubscriptionPlanResponse subscriptionPlanResponse = transactionalApiService.invokeSubscriptionPlanListApi(1);
		return subscriptionPlanResponse;
	}
	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#getClientSubscriptionPlan(com.ireslab.sendx.electra.model.ClientSubscriptionRequest)
	 */
	@Override
	public ClientSubscriptionResponse getClientSubscriptionPlan(ClientSubscriptionRequest clientSubscriptionRequest) {
     LOG.info("Request recieved for get client subscription plan list in service ");
		
     ClientSubscriptionResponse clientSubscriptionResponse = transactionalApiService.invokeClientSubscriptionPlanList(clientSubscriptionRequest);
		return clientSubscriptionResponse;
	}
	
	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ElectraAppService#isClientORNot(com.ireslab.sendx.electra.model.ClientSubscriptionRequest)
	 */
	@Override
	public ClientSubscriptionResponse isClientORNot(ClientSubscriptionRequest clientSubscriptionRequest) {
		LOG.info("Request recieved for check exist as client or not in service ");
		
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(new BigInteger(clientSubscriptionRequest.getClientSubscriptionDto().getMobileNumber()), clientSubscriptionRequest.getClientSubscriptionDto().getCountryDialCode());
		clientSubscriptionRequest.setUserCorrelationId(account.getUserCorrelationId());
	     ClientSubscriptionResponse clientSubscriptionResponse = transactionalApiService.isClientORNot(clientSubscriptionRequest);
			return clientSubscriptionResponse;
	}

	
	/**
	 * @param key
	 * @return
	 */
	private String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}

	

	
	
	

}
