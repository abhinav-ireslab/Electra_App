package com.ireslab.electraapp.service;

import java.util.List;

import com.ireslab.electraapp.model.AgentRequest;
import com.ireslab.electraapp.model.AgentRequestBody;
import com.ireslab.electraapp.model.AgentResponse;
import com.ireslab.electraapp.model.CashOutRequest;
import com.ireslab.electraapp.model.LoadTokensRequest;
import com.ireslab.electraapp.model.SendTokensRequest;
import com.ireslab.electraapp.model.SignupRequest;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.sendx.electra.model.BankDetailResponse;
import com.ireslab.sendx.electra.model.BankDetailsRequest;
import com.ireslab.sendx.electra.model.ClientInfoRequest;
import com.ireslab.sendx.electra.model.ClientInfoResponse;
import com.ireslab.sendx.electra.model.ClientProfile;
import com.ireslab.sendx.electra.model.ClientRegistrationRequest;
import com.ireslab.sendx.electra.model.ClientRegistrationResponse;
import com.ireslab.sendx.electra.model.ClientSubscriptionRequest;
import com.ireslab.sendx.electra.model.ClientSubscriptionResponse;
import com.ireslab.sendx.electra.model.ClientSubscriptionUpdateRequest;
import com.ireslab.sendx.electra.model.ClientSubscriptionUpdateResponse;
import com.ireslab.sendx.electra.model.CompanyCodeResponse;
import com.ireslab.sendx.electra.model.ExchangeRequest;
import com.ireslab.sendx.electra.model.ExchangeResponse;
import com.ireslab.sendx.electra.model.NotificationRequest;
import com.ireslab.sendx.electra.model.NotificationResponse;
import com.ireslab.sendx.electra.model.PaymentRequest;
import com.ireslab.sendx.electra.model.PaymentResponse;
import com.ireslab.sendx.electra.model.ProductAvailabilityRequest;
import com.ireslab.sendx.electra.model.ProductAvailabilityResponse;
import com.ireslab.sendx.electra.model.ProductRequest;
import com.ireslab.sendx.electra.model.ProductResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;
import com.ireslab.sendx.electra.model.SubscriptionPlanResponse;
import com.ireslab.sendx.electra.model.TokenTransferRequest;
import com.ireslab.sendx.electra.model.TokenTransferResponse;
import com.ireslab.sendx.electra.model.TransactionLimitResponse;
import com.ireslab.sendx.electra.model.TransactionPurposeResponse;
import com.ireslab.sendx.electra.model.UserRegistrationResponse;


/**
 * @author Nitin
 *
 */
public interface TransactionalApiService {

	/**
	 * @param signupRequest
	 * @return
	 */
	public List<com.ireslab.sendx.electra.model.UserProfile> invokeUserOnboardingApi(SignupRequest signupRequest);

	/**
	 * @param loadTokensRequest
	 * @return
	 */
	public String invokeLoadTokensAPI(LoadTokensRequest loadTokensRequest);

	/**
	 * 
	 * @param userCorrelationId
	 * @return
	 */
	public UserProfile invokeUserProfileAPI(String userCorrelationId);

	/**
	 * @param sendTokensRequest
	 * @return
	 */
	public String invokeTransferTokensAPI(SendTokensRequest sendTokensRequest);

	/**
	 * @param cashOutTokensRequest
	 * @return
	 */
	public String invokeCashoutTokensAPI(CashOutRequest cashOutTokensRequest);

	/**
	 * @param userCorrelationId
	 * @return
	 */
	public ExchangeResponse getAllExchangeDetails(String userCorrelationId);

	/**
	 * @param agentRequest
	 * @param correlationId
	 * @return
	 */
	public AgentResponse invokeAgentOnboardingApi(AgentRequest agentRequest, String correlationId);

	/**
	 * @param agentRequestBody
	 * @return
	 */
	public AgentResponse invokeGetAgentAPI(AgentRequestBody agentRequestBody);

	public ClientRegistrationResponse invokeUserClientOnboardingApi(ClientRegistrationRequest clientRegistrationRequest);

	public void invokeUserClientEntryOnboardingApi(SignupRequest signupRequest);

	public CompanyCodeResponse invokeCompanyCodeAPI();

	public ClientProfile invokeClientByCompanyCodeAPI(String companyCode);

	public String invokeTransferTokensAPIToClient(TokenTransferRequest tokenTransferRequest);

	public UserRegistrationResponse updateUser(UserProfile userProfile, String correlationId);

	public ClientSubscriptionResponse invokeSubscriptionPlanApi(ClientSubscriptionRequest clientSubscriptionRequest);

	public SubscriptionPlanResponse invokeSubscriptionPlanListApi(int countryId);

	public ClientSubscriptionUpdateResponse updateClientSubscriptionPlan(ClientSubscriptionUpdateRequest clientSubscriptionUpdateRequest);

	public ClientSubscriptionResponse invokeClientSubscriptionPlanList(
			ClientSubscriptionRequest clientSubscriptionRequest);

	public ClientSubscriptionResponse isClientORNot(ClientSubscriptionRequest clientSubscriptionRequest);

	public ClientSubscriptionUpdateResponse updateCheckmailRegistration(
			ClientSubscriptionUpdateRequest clientSubscriptionUpdateRequest);
	
	public ProductResponse getProductList(String url,ProductRequest productRequest);

	public PaymentResponse makePayment(String makePaymentEndPointUrl, PaymentRequest paymentRequest);

	public ClientInfoResponse clientInformation(ClientInfoRequest clientInfoRequest);

	public PaymentResponse savePurchasedProduct(PaymentRequest paymentRequest);

	public ProductAvailabilityResponse checkProductAvailability(ProductAvailabilityRequest productAvailabilityRequest);

	public UserProfile invokeClientProfileAPI(String userCorrelationId);

	public TransactionPurposeResponse getAllTransactionPurpose(String clientCorrelationId);

	public TokenTransferResponse transactionLimitsForAllowTransfer(TokenTransferRequest tokenTransferRequestForcheck);

	public BankDetailResponse saveBankDetail(BankDetailsRequest bankDetailsRequest);

	public String invokeTransferFeeToMaster(TokenTransferRequest tokenTransferRequest);

	public TransactionLimitResponse getTransactionLimit();

	public BankDetailResponse getBankDetailByClientEmail(BankDetailsRequest bankDetailsRequest);

	public SendxElectraResponse updateDeviceSpecificParameter(SendxElectraRequest sendxElectraRequest);

	public UserProfile searchUserProfileByUniqueCode(String uniqueCode);

	public NotificationResponse saveNotificationData(NotificationRequest notificationRequest);

	public NotificationResponse findByNotificationId(Integer notificationId);

	public SendxElectraResponse updateNotificationApi(SendxElectraRequest sendxElectraRequest);

	public SendxElectraResponse getAllNotification(String correlationId);

	public PaymentResponse makeOfflinePayment(TokenTransferRequest tokenTransferRequest);

	public NotificationResponse deleteNotificationData(NotificationRequest notificationRequest);

	public ExchangeResponse getExchangeRate(ExchangeRequest exchangeRequest);

	public PaymentResponse updateInvoicedProductQty(PaymentRequest paymentRequest);

	public UserProfile searchUserProfileByDialCodeAndMobile(String beneficiaryCountryDialCode,
			Long beneficiaryMobileNumber);

	public SendxElectraResponse getAllTransactionalDetails(SendxElectraRequest requestTransactionDetailList);

	

	SendxElectraResponse getAllSettlementReports(String correllationId);

	
}
