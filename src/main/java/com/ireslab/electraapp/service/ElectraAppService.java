package com.ireslab.electraapp.service;

import com.ireslab.electraapp.model.AccountVerificationResponse;
import com.ireslab.electraapp.model.SignupRequest;
import com.ireslab.electraapp.model.SignupResponse;
import com.ireslab.sendx.electra.model.ClientSubscriptionRequest;
import com.ireslab.sendx.electra.model.ClientSubscriptionResponse;
import com.ireslab.sendx.electra.model.CompanyCodeResponse;
import com.ireslab.sendx.electra.model.SubscriptionPlanResponse;


public interface ElectraAppService {

	public CompanyCodeResponse generateCompanyCode();

	public AccountVerificationResponse verifyAccountByMobileNo(Long mobileNumber, String countryDialCode,
			String companyCode);

	public SignupResponse registerClientUser(SignupRequest signupRequest);

	public ClientSubscriptionResponse clientSubscriptionPlan(ClientSubscriptionRequest clientSubscriptionRequest);

	//public SubscriptionPlanResponse getSubscriptionPlanList();

	public ClientSubscriptionResponse getClientSubscriptionPlan(ClientSubscriptionRequest clientSubscriptionRequest);

	public ClientSubscriptionResponse isClientORNot(ClientSubscriptionRequest clientSubscriptionRequest);

	public SubscriptionPlanResponse getSubscriptionPlanList(Long mobileNumber, String countryDialCode);

}
