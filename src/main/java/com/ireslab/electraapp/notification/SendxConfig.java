package com.ireslab.electraapp.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Nitin
 *
 */
@Component
@PropertySource(value = "classpath:sendx_config.properties")
@ConfigurationProperties
public class SendxConfig {

	public boolean isTestMode;

	public Integer activationCodeValidity;

	public short activationCodeRequestRetryLimit;

	public String clientId;

	public String resourceIds;

	public String scopes;

	public String grantTypes;

	public String authorities;

	public String clientSecret;
	
	public String electraAppUrl;

	public boolean isCrossBorderTransactionsEnabled;
	
	public String notificationMobileNumber; //notification_mobile_number
	
	

	/* Commented - Only applicable in Livenet */

	// public String maxAllowedAccountBalance;
	// public short maxAllowedAccountTopup;
	// public String payFundBankTransferMessage;

	

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public int appVersionCode;

	public String appPlaystoreUrl;

	public String appForceUpgradeMessage;
	
    public String appBaseUrl;
	
	public String userProfileImageUrl;
	
	
	public String imageDirectoryRelativePath;
	



	public String getImageDirectoryRelativePath() {
		return imageDirectoryRelativePath;
	}

	public void setImageDirectoryRelativePath(String imageDirectoryRelativePath) {
		this.imageDirectoryRelativePath = imageDirectoryRelativePath;
	}

	public void setAppBaseUrl(String appBaseUrl) {
		this.appBaseUrl = appBaseUrl;
	}

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}
	
	public String getElectraAppUrl() {
		return electraAppUrl;
	}

	public void setElectraAppUrl(String electraAppUrl) {
		this.electraAppUrl = electraAppUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SendxConfig [isTestMode=" + isTestMode + ", activationCodeValidity=" + activationCodeValidity
				+ ", activationCodeRequestRetryLimit=" + activationCodeRequestRetryLimit + "]";
	}

	/**
	 * @param isTestMode
	 *            the isTestMode to set
	 */
	public void setTestMode(boolean isTestMode) {
		this.isTestMode = isTestMode;
	}

	/**
	 * @param activationCodeValidity
	 *            the activationCodeValidity to set
	 */
	public void setActivationCodeValidity(Integer activationCodeValidity) {
		this.activationCodeValidity = activationCodeValidity;
	}

	/**
	 * @param activationCodeRequestRetryLimit
	 *            the activationCodeRequestRetryLimit to set
	 */
	public void setActivationCodeRequestRetryLimit(short activationCodeRequestRetryLimit) {
		this.activationCodeRequestRetryLimit = activationCodeRequestRetryLimit;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @param resourceIds
	 *            the resourceIds to set
	 */
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	/**
	 * @param scopes
	 *            the scopes to set
	 */
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	/**
	 * @param grantTypes
	 *            the grantTypes to set
	 */
	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param clientSecret
	 *            the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	// /**
	// * @param maxAllowedAccountBalance
	// * the maxAllowedAccountBalance to set
	// */
	// public void setMaxAllowedAccountBalance(String maxAllowedAccountBalance)
	// {
	// this.maxAllowedAccountBalance = maxAllowedAccountBalance;
	// }
	//
	// /**
	// * @param maxAllowedAccountTopup
	// * the maxAllowedAccountTopup to set
	// */
	// public void setMaxAllowedAccountTopup(short maxAllowedAccountTopup) {
	// this.maxAllowedAccountTopup = maxAllowedAccountTopup;
	// }
	//
	// /**
	// * @return the payFundBankTransferMessage
	// */
	// public String getPayFundBankTransferMessage() {
	// return payFundBankTransferMessage;
	// }
	//
	// /**
	// * @param payFundBankTransferMessage
	// * the payFundBankTransferMessage to set
	// */
	// public void setPayFundBankTransferMessage(String
	// payFundBankTransferMessage) {
	// this.payFundBankTransferMessage = payFundBankTransferMessage;
	// }

	/**
	 * @return the appVersion
	 */
	public int getAppVersionCode() {
		return appVersionCode;
	}

	/**
	 * @param appVersionCode
	 *            the appVersion to set
	 */
	public void setAppVersionCode(int appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	/**
	 * @return the appPlaystoreUrl
	 */
	public String getAppPlaystoreUrl() {
		return appPlaystoreUrl;
	}

	/**
	 * @param appPlaystoreUrl
	 *            the appPlaystoreUrl to set
	 */
	public void setAppPlaystoreUrl(String appPlaystoreUrl) {
		this.appPlaystoreUrl = appPlaystoreUrl;
	}

	/**
	 * @return the appForceUpgradeMessage
	 */
	public String getAppForceUpgradeMessage() {
		return appForceUpgradeMessage;
	}

	/**
	 * @param appForceUpgradeMessage
	 *            the appForceUpgradeMessage to set
	 */
	public void setAppForceUpgradeMessage(String appForceUpgradeMessage) {
		this.appForceUpgradeMessage = appForceUpgradeMessage;
	}
	
	public boolean isCrossBorderTransactionsEnabled() {
		return isCrossBorderTransactionsEnabled;
	}

	public void setIsCrossBorderTransactionsEnabled(boolean isCrossBorderTransactionsEnabled) {
		this.isCrossBorderTransactionsEnabled = isCrossBorderTransactionsEnabled;
		//System.out.println("setting isCrossBorderTransactionsEnabled"+isCrossBorderTransactionsEnabled);
	}

	public String getNotificationMobileNumber() {
		return notificationMobileNumber;
	}

	public void setNotificationMobileNumber(String notificationMobileNumber) {
		this.notificationMobileNumber = notificationMobileNumber;
	}
}
