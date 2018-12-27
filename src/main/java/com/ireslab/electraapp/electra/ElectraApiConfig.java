package com.ireslab.electraapp.electra;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ireslab.electraapp.util.Constants;

/**
 * @author Nitin
 *
 */
@Component
@PropertySource(value = Constants.ELECTRA_API_CONFIG_FILE)
@ConfigurationProperties("electra")
public class ElectraApiConfig {

	private String apiVersion;
	private String baseUrl;
	private String clientBaseUrl;
	private String headerContentType;
	private String headerAccept;
	private String headerAuthorization;
	private String clientCorrelationId;
	private String tokenCorrelationId;
	private String clientId;
	private String clientSecret;
	private String grantType;
	private String tokenCode;
	private String authTokenEndpointUrl;
	private String userOnboardingApiEndpointUrl;
	private String userProfileApiEndpointUrl;
	private String loadTokensApiEndpointUrl;
	private String transferTokensApiEndpointUrl;
	private String cashoutTokensApiEndpointUrl;
	private String transactionHistoryApiEndpointUrl;
	private String exchangeDetailsApiEndpointUrl;
	private String userAgentApiEndpointUrl;
	private String getAgentApiEndpointUrl;
	private String userClientOnboardingApiEndpointUrl;
	private String userClientEntryOnboardingApiEndpointUrl;
	private String companyCodeApiEndpointUrl;
	private String clientApiEndpointUrl;
	private String userUpdationApiEndpointUrl;

	private String transferTokensToClientApiEndpointUrl;
	private String transactionalDetailsApiEndpointUrl;

	private String subscriptionPlanApiEndpointUrl;
	private String subscriptionPlanListApiEndpointUrl;
	private String clientSubscriptionPlanUpdationApiEndpointUrl;
	private String clientSubscriptionPlanApiEndpointUrl;
	private String isClientApiEndpointUrl;
	private String checkMailRegistrationApiEndpointUrl;
	private String clientUserBaseUrl;
	private String clientUserOnboardingApiEndpointUrl;
	private String productDetailsApiEndpointUrl;
	private String makePaymentApiEndpointUrl;
	private String clientInformationApiEndpointUrl;

	private String savePurchasedProductApiEndpointUrl;
	private String checkProductAvailabilityApiEndpointUrl;
	private String clientProfileApiEndpointUrl;

	private String allTransactionPurposeApiEndpointUrl;
	private String allowTransactionApiEndpointUrl;

	private String saveBankDetailApiEndpointUrl;

	private String transferFeeToMasterApiEndPointUrl;
	private String transactionLimitApiEndpointUrl;
	private String bankDetailsApiEndpointUrl;
	private String updateDeviceSpecificParameterApiEndpointUrl;
	private String searchUserProfileApiEndpointUrl;
	private String saveNotificationApiEndpointUrl;
	private String updateNotificationApiEndpointUrl;
	private String allNotificationApiEndpointUrl;
	private String offlinePaymentApiEndpointUrl;
	private String deleteNotificationApiEndpointUrl;
	private String exchangeRateApiEndpointUrl;
	private String updateInvoicedProductApiEndpointUrl;

	private String searchUserProfileByMobileApiEndpointUrl;
	private String settlementReportListApiEndpointUrl;
	
	

	public String getSettlementReportListApiEndpointUrl() {
		return settlementReportListApiEndpointUrl;
	}

	public void setSettlementReportListApiEndpointUrl(String settlementReportListApiEndpointUrl) {
		this.settlementReportListApiEndpointUrl = settlementReportListApiEndpointUrl;
	}

	public String getSearchUserProfileByMobileApiEndpointUrl() {
		return searchUserProfileByMobileApiEndpointUrl;
	}

	public void setSearchUserProfileByMobileApiEndpointUrl(String searchUserProfileByMobileApiEndpointUrl) {
		this.searchUserProfileByMobileApiEndpointUrl = searchUserProfileByMobileApiEndpointUrl;
	}

	public String getClientProfileApiEndpointUrl() {
		return clientProfileApiEndpointUrl;
	}

	public void setClientProfileApiEndpointUrl(String clientProfileApiEndpointUrl) {
		this.clientProfileApiEndpointUrl = clientProfileApiEndpointUrl;
	}

	public String getClientInformationApiEndpointUrl() {
		return clientInformationApiEndpointUrl;
	}

	public void setClientInformationApiEndpointUrl(String clientInformationApiEndpointUrl) {
		this.clientInformationApiEndpointUrl = clientInformationApiEndpointUrl;
	}

	public String getSearchUserProfileApiEndpointUrl() {
		return searchUserProfileApiEndpointUrl;
	}

	public void setSearchUserProfileApiEndpointUrl(String searchUserProfileApiEndpointUrl) {
		this.searchUserProfileApiEndpointUrl = searchUserProfileApiEndpointUrl;
	}

	public String getSaveNotificationApiEndpointUrl() {
		return saveNotificationApiEndpointUrl;
	}

	public void setSaveNotificationApiEndpointUrl(String saveNotificationApiEndpointUrl) {
		this.saveNotificationApiEndpointUrl = saveNotificationApiEndpointUrl;
	}

	public String getMakePaymentApiEndpointUrl() {
		return makePaymentApiEndpointUrl;
	}

	public void setMakePaymentApiEndpointUrl(String makePaymentApiEndpointUrl) {
		this.makePaymentApiEndpointUrl = makePaymentApiEndpointUrl;
	}

	public String getProductDetailsApiEndpointUrl() {
		return productDetailsApiEndpointUrl;
	}

	public void setProductDetailsApiEndpointUrl(String productDetailsApiEndpointUrl) {
		this.productDetailsApiEndpointUrl = productDetailsApiEndpointUrl;
	}

	public String getClientUserOnboardingApiEndpointUrl() {
		return clientUserOnboardingApiEndpointUrl;
	}

	public void setClientUserOnboardingApiEndpointUrl(String clientUserOnboardingApiEndpointUrl) {
		this.clientUserOnboardingApiEndpointUrl = clientUserOnboardingApiEndpointUrl;
	}

	public String getClientUserBaseUrl() {
		return clientUserBaseUrl;
	}

	public void setClientUserBaseUrl(String clientUserBaseUrl) {
		this.clientUserBaseUrl = clientUserBaseUrl;
	}

	public String getTransactionalDetailsApiEndpointUrl() {
		return transactionalDetailsApiEndpointUrl;
	}

	public void setTransactionalDetailsApiEndpointUrl(String transactionalDetailsApiEndpointUrl) {
		this.transactionalDetailsApiEndpointUrl = transactionalDetailsApiEndpointUrl;
	}

	public String getTransferTokensToClientApiEndpointUrl() {
		return transferTokensToClientApiEndpointUrl;
	}

	public void setTransferTokensToClientApiEndpointUrl(String transferTokensToClientApiEndpointUrl) {
		this.transferTokensToClientApiEndpointUrl = transferTokensToClientApiEndpointUrl;
	}

	/**
	 * @return the getAgentApiEndpointUrl
	 */
	public String getGetAgentApiEndpointUrl() {
		return getAgentApiEndpointUrl;
	}

	/**
	 * @param getAgentApiEndpointUrl
	 *            the getAgentApiEndpointUrl to set
	 */
	public void setGetAgentApiEndpointUrl(String getAgentApiEndpointUrl) {
		this.getAgentApiEndpointUrl = getAgentApiEndpointUrl;
	}

	public String getClientBaseUrl() {
		return clientBaseUrl;
	}

	public void setClientBaseUrl(String clientBaseUrl) {
		this.clientBaseUrl = clientBaseUrl;
	}

	public String getExchangeDetailsApiEndpointUrl() {
		return exchangeDetailsApiEndpointUrl;
	}

	public void setExchangeDetailsApiEndpointUrl(String exchangeDetailsApiEndpointUrl) {
		this.exchangeDetailsApiEndpointUrl = exchangeDetailsApiEndpointUrl;
	}

	/**
	 * @return the apiVersion
	 */
	public String getApiVersion() {
		return apiVersion;
	}

	/**
	 * @param apiVersion
	 *            the apiVersion to set
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the headerContentType
	 */
	public String getHeaderContentType() {
		return headerContentType;
	}

	/**
	 * @param headerContentType
	 *            the headerContentType to set
	 */
	public void setHeaderContentType(String headerContentType) {
		this.headerContentType = headerContentType;
	}

	/**
	 * @return the headerAccept
	 */
	public String getHeaderAccept() {
		return headerAccept;
	}

	/**
	 * @param headerAccept
	 *            the headerAccept to set
	 */
	public void setHeaderAccept(String headerAccept) {
		this.headerAccept = headerAccept;
	}

	/**
	 * @return the headerAuthorizationKey
	 */
	public String getHeaderAuthorization() {
		return headerAuthorization;
	}

	/**
	 * @param headerAuthorizationKey
	 *            the headerAuthorizationKey to set
	 */
	public void setHeaderAuthorization(String headerAuthorization) {
		this.headerAuthorization = headerAuthorization;
	}

	/**
	 * @return the clientCorrelationId
	 */
	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	/**
	 * @param clientCorrelationId
	 *            the clientCorrelationId to set
	 */
	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	/**
	 * @return the tokenCorrelationId
	 */
	public String getTokenCorrelationId() {
		return tokenCorrelationId;
	}

	/**
	 * @param tokenCorrelationId
	 *            the tokenCorrelationId to set
	 */
	public void setTokenCorrelationId(String tokenCorrelationId) {
		this.tokenCorrelationId = tokenCorrelationId;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret
	 *            the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the grantType
	 */
	public String getGrantType() {
		return grantType;
	}

	/**
	 * @param grantType
	 *            the grantType to set
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	/**
	 * @return the tokenCode
	 */
	public String getTokenCode() {
		return tokenCode;
	}

	/**
	 * @param tokenCode
	 *            the tokenCode to set
	 */
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	/**
	 * @return the authTokenEndpointUrl
	 */
	public String getAuthTokenEndpointUrl() {
		return authTokenEndpointUrl;
	}

	/**
	 * @param authTokenEndpointUrl
	 *            the authTokenEndpointUrl to set
	 */
	public void setAuthTokenEndpointUrl(String authTokenEndpointUrl) {
		this.authTokenEndpointUrl = authTokenEndpointUrl;
	}

	/**
	 * @return the userOnboardingApiEndpointUrl
	 */
	public String getUserOnboardingApiEndpointUrl() {
		return userOnboardingApiEndpointUrl;
	}

	/**
	 * @param userOnboardingApiEndpointUrl
	 *            the userOnboardingApiEndpointUrl to set
	 */
	public void setUserOnboardingApiEndpointUrl(String userOnboardingApiEndpointUrl) {
		this.userOnboardingApiEndpointUrl = userOnboardingApiEndpointUrl;
	}

	/**
	 * @return the userProfileApiEndpointUrl
	 */
	public String getUserProfileApiEndpointUrl() {
		return userProfileApiEndpointUrl;
	}

	/**
	 * @param userProfileApiEndpointUrl
	 *            the userProfileApiEndpointUrl to set
	 */
	public void setUserProfileApiEndpointUrl(String userProfileApiEndpointUrl) {
		this.userProfileApiEndpointUrl = userProfileApiEndpointUrl;
	}

	/**
	 * @return the loadTokensApiEndpointUrl
	 */
	public String getLoadTokensApiEndpointUrl() {
		return loadTokensApiEndpointUrl;
	}

	/**
	 * @param loadTokensApiEndpointUrl
	 *            the loadTokensApiEndpointUrl to set
	 */
	public void setLoadTokensApiEndpointUrl(String loadTokensApiEndpointUrl) {
		this.loadTokensApiEndpointUrl = loadTokensApiEndpointUrl;
	}

	/**
	 * @return the transferTokensApiEndpointUrl
	 */
	public String getTransferTokensApiEndpointUrl() {
		return transferTokensApiEndpointUrl;
	}

	/**
	 * @param transferTokensApiEndpointUrl
	 *            the transferTokensApiEndpointUrl to set
	 */
	public void setTransferTokensApiEndpointUrl(String transferTokensApiEndpointUrl) {
		this.transferTokensApiEndpointUrl = transferTokensApiEndpointUrl;
	}

	/**
	 * @return the cashoutTokensApiEndpointUrl
	 */
	public String getCashoutTokensApiEndpointUrl() {
		return cashoutTokensApiEndpointUrl;
	}

	/**
	 * @param cashoutTokensApiEndpointUrl
	 *            the cashoutTokensApiEndpointUrl to set
	 */
	public void setCashoutTokensApiEndpointUrl(String cashoutTokensApiEndpointUrl) {
		this.cashoutTokensApiEndpointUrl = cashoutTokensApiEndpointUrl;
	}

	/**
	 * @return the transactionHistoryApiEndpointUrl
	 */
	public String getTransactionHistoryApiEndpointUrl() {
		return transactionHistoryApiEndpointUrl;
	}

	/**
	 * @param transactionHistoryApiEndpointUrl
	 *            the transactionHistoryApiEndpointUrl to set
	 */
	public void setTransactionHistoryApiEndpointUrl(String transactionHistoryApiEndpointUrl) {
		this.transactionHistoryApiEndpointUrl = transactionHistoryApiEndpointUrl;
	}

	public String getUserAgentApiEndpointUrl() {
		return userAgentApiEndpointUrl;
	}

	public void setUserAgentApiEndpointUrl(String userAgentApiEndpointUrl) {
		this.userAgentApiEndpointUrl = userAgentApiEndpointUrl;
	}

	public String getUserClientOnboardingApiEndpointUrl() {
		return userClientOnboardingApiEndpointUrl;
	}

	public void setUserClientOnboardingApiEndpointUrl(String userClientOnboardingApiEndpointUrl) {
		this.userClientOnboardingApiEndpointUrl = userClientOnboardingApiEndpointUrl;
	}

	public String getUserClientEntryOnboardingApiEndpointUrl() {
		return userClientEntryOnboardingApiEndpointUrl;
	}

	public void setUserClientEntryOnboardingApiEndpointUrl(String userClientEntryOnboardingApiEndpointUrl) {
		this.userClientEntryOnboardingApiEndpointUrl = userClientEntryOnboardingApiEndpointUrl;
	}

	public String getCompanyCodeApiEndpointUrl() {
		return companyCodeApiEndpointUrl;
	}

	public void setCompanyCodeApiEndpointUrl(String companyCodeApiEndpointUrl) {
		this.companyCodeApiEndpointUrl = companyCodeApiEndpointUrl;
	}

	public String getClientApiEndpointUrl() {
		return clientApiEndpointUrl;
	}

	public void setClientApiEndpointUrl(String clientApiEndpointUrl) {
		this.clientApiEndpointUrl = clientApiEndpointUrl;
	}

	public String getUserUpdationApiEndpointUrl() {
		return userUpdationApiEndpointUrl;
	}

	public void setUserUpdationApiEndpointUrl(String userUpdationApiEndpointUrl) {
		this.userUpdationApiEndpointUrl = userUpdationApiEndpointUrl;
	}

	public String getSubscriptionPlanApiEndpointUrl() {
		return subscriptionPlanApiEndpointUrl;
	}

	public void setSubscriptionPlanApiEndpointUrl(String subscriptionPlanApiEndpointUrl) {
		this.subscriptionPlanApiEndpointUrl = subscriptionPlanApiEndpointUrl;
	}

	public String getSubscriptionPlanListApiEndpointUrl() {
		return subscriptionPlanListApiEndpointUrl;
	}

	public void setSubscriptionPlanListApiEndpointUrl(String subscriptionPlanListApiEndpointUrl) {
		this.subscriptionPlanListApiEndpointUrl = subscriptionPlanListApiEndpointUrl;
	}

	public String getClientSubscriptionPlanUpdationApiEndpointUrl() {
		return clientSubscriptionPlanUpdationApiEndpointUrl;
	}

	public void setClientSubscriptionPlanUpdationApiEndpointUrl(String clientSubscriptionPlanUpdationApiEndpointUrl) {
		this.clientSubscriptionPlanUpdationApiEndpointUrl = clientSubscriptionPlanUpdationApiEndpointUrl;
	}

	public String getClientSubscriptionPlanApiEndpointUrl() {
		return clientSubscriptionPlanApiEndpointUrl;
	}

	public void setClientSubscriptionPlanApiEndpointUrl(String clientSubscriptionPlanApiEndpointUrl) {
		this.clientSubscriptionPlanApiEndpointUrl = clientSubscriptionPlanApiEndpointUrl;
	}

	public String getIsClientApiEndpointUrl() {
		return isClientApiEndpointUrl;
	}

	public void setIsClientApiEndpointUrl(String isClientApiEndpointUrl) {
		this.isClientApiEndpointUrl = isClientApiEndpointUrl;
	}

	public String getCheckMailRegistrationApiEndpointUrl() {
		return checkMailRegistrationApiEndpointUrl;
	}

	public void setCheckMailRegistrationApiEndpointUrl(String checkMailRegistrationApiEndpointUrl) {
		this.checkMailRegistrationApiEndpointUrl = checkMailRegistrationApiEndpointUrl;
	}

	public String getSavePurchasedProductApiEndpointUrl() {
		return savePurchasedProductApiEndpointUrl;
	}

	public void setSavePurchasedProductApiEndpointUrl(String savePurchasedProductApiEndpointUrl) {
		this.savePurchasedProductApiEndpointUrl = savePurchasedProductApiEndpointUrl;
	}

	public String getCheckProductAvailabilityApiEndpointUrl() {
		return checkProductAvailabilityApiEndpointUrl;
	}

	public void setCheckProductAvailabilityApiEndpointUrl(String checkProductAvailabilityApiEndpointUrl) {
		this.checkProductAvailabilityApiEndpointUrl = checkProductAvailabilityApiEndpointUrl;
	}

	public String getAllTransactionPurposeApiEndpointUrl() {
		return allTransactionPurposeApiEndpointUrl;
	}

	public void setAllTransactionPurposeApiEndpointUrl(String allTransactionPurposeApiEndpointUrl) {
		this.allTransactionPurposeApiEndpointUrl = allTransactionPurposeApiEndpointUrl;
	}

	public String getAllowTransactionApiEndpointUrl() {
		return allowTransactionApiEndpointUrl;
	}

	public void setAllowTransactionApiEndpointUrl(String allowTransactionApiEndpointUrl) {
		this.allowTransactionApiEndpointUrl = allowTransactionApiEndpointUrl;
	}

	public String getSaveBankDetailApiEndpointUrl() {
		return saveBankDetailApiEndpointUrl;
	}

	public void setSaveBankDetailApiEndpointUrl(String saveBankDetailApiEndpointUrl) {
		this.saveBankDetailApiEndpointUrl = saveBankDetailApiEndpointUrl;
	}

	public String getTransferFeeToMasterApiEndPointUrl() {
		return transferFeeToMasterApiEndPointUrl;
	}

	public void setTransferFeeToMasterApiEndPointUrl(String transferFeeToMasterApiEndPointUrl) {
		this.transferFeeToMasterApiEndPointUrl = transferFeeToMasterApiEndPointUrl;
	}

	public String getTransactionLimitApiEndpointUrl() {
		return transactionLimitApiEndpointUrl;
	}

	public void setTransactionLimitApiEndpointUrl(String transactionLimitApiEndpointUrl) {
		this.transactionLimitApiEndpointUrl = transactionLimitApiEndpointUrl;
	}

	public String getBankDetailsApiEndpointUrl() {
		return bankDetailsApiEndpointUrl;
	}

	public void setBankDetailsApiEndpointUrl(String bankDetailsApiEndpointUrl) {
		this.bankDetailsApiEndpointUrl = bankDetailsApiEndpointUrl;
	}

	public String getUpdateDeviceSpecificParameterApiEndpointUrl() {
		return updateDeviceSpecificParameterApiEndpointUrl;
	}

	public void setUpdateDeviceSpecificParameterApiEndpointUrl(String updateDeviceSpecificParameterApiEndpointUrl) {
		this.updateDeviceSpecificParameterApiEndpointUrl = updateDeviceSpecificParameterApiEndpointUrl;
	}

	public String getUpdateNotificationApiEndpointUrl() {
		return updateNotificationApiEndpointUrl;
	}

	public void setUpdateNotificationApiEndpointUrl(String updateNotificationApiEndpointUrl) {
		this.updateNotificationApiEndpointUrl = updateNotificationApiEndpointUrl;
	}

	public String getAllNotificationApiEndpointUrl() {
		return allNotificationApiEndpointUrl;
	}

	public void setAllNotificationApiEndpointUrl(String allNotificationApiEndpointUrl) {
		this.allNotificationApiEndpointUrl = allNotificationApiEndpointUrl;
	}

	public String getOfflinePaymentApiEndpointUrl() {
		return offlinePaymentApiEndpointUrl;
	}

	public void setOfflinePaymentApiEndpointUrl(String offlinePaymentApiEndpointUrl) {
		this.offlinePaymentApiEndpointUrl = offlinePaymentApiEndpointUrl;
	}

	public String getDeleteNotificationApiEndpointUrl() {
		return deleteNotificationApiEndpointUrl;
	}

	public void setDeleteNotificationApiEndpointUrl(String deleteNotificationApiEndpointUrl) {
		this.deleteNotificationApiEndpointUrl = deleteNotificationApiEndpointUrl;
	}

	public String getExchangeRateApiEndpointUrl() {
		return exchangeRateApiEndpointUrl;
	}

	public void setExchangeRateApiEndpointUrl(String exchangeRateApiEndpointUrl) {
		this.exchangeRateApiEndpointUrl = exchangeRateApiEndpointUrl;
	}

	public String getUpdateInvoicedProductApiEndpointUrl() {
		return updateInvoicedProductApiEndpointUrl;
	}

	public void setUpdateInvoicedProductApiEndpointUrl(String updateInvoicedProductApiEndpointUrl) {
		this.updateInvoicedProductApiEndpointUrl = updateInvoicedProductApiEndpointUrl;
	}

}
