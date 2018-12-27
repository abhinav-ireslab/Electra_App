package com.ireslab.electraapp.model;

import java.util.List;

public class CountryListResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CountryDetails> countryDetails;

	private int appVersionCode;

	private String appPlaystoreUrl;

	private String appForceUpgradeMessage;

	/**
	 * @return the countryDetails
	 */
	public List<CountryDetails> getCountryDetails() {
		return countryDetails;
	}

	/**
	 * @param countryDetails
	 *            the countryDetails to set
	 */
	public void setCountryDetails(List<CountryDetails> countryDetails) {
		this.countryDetails = countryDetails;
	}

	public CountryListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryListResponse(Integer status, Integer code, String message) {
		super(status, code, message);
		// TODO Auto-generated constructor stub
	}

	public CountryListResponse(Integer status, Integer code, String message, List<CountryDetails> countryDetailsList,
			int appVersionCode, String appPlaystoreUrl, String appForceUpgradeMessage) {
		super(status, code, message);
		this.countryDetails = countryDetailsList;
		this.appVersionCode = appVersionCode;
		this.appPlaystoreUrl = appPlaystoreUrl;
		this.appForceUpgradeMessage = appForceUpgradeMessage;
	}

	/**
	 * @return the appVersionCode
	 */
	public int getAppVersionCode() {
		return appVersionCode;
	}

	/**
	 * @param appVersionCode
	 *            the appVersionCode to set
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
}
