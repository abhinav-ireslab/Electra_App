package com.ireslab.electraapp.model;

/**
 * @author Nitin
 *
 */
public class CountryDetails {

	private int countryId;

	private String countryDialCode;

	private String countryName;

	private String iso4217CurrencyAlphabeticCode;

	private String iso4217CurrencyName;

	/**
	 * 
	 */
	public CountryDetails() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param countryId
	 * @param countryDialCode
	 * @param countryName
	 * @param iso4217CurrencyAlphabeticCode
	 * @param iso4217CurrencyName
	 */
	public CountryDetails(int countryId, String countryDialCode, String countryName,
			String iso4217CurrencyAlphabeticCode, String iso4217CurrencyName) {
		super();
		this.countryId = countryId;
		this.countryDialCode = countryDialCode;
		this.countryName = countryName;
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
		this.iso4217CurrencyName = iso4217CurrencyName;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryDialCode
	 */
	public String getCountryDialCode() {
		return countryDialCode;
	}

	/**
	 * @param countryDialCode
	 *            the countryDialCode to set
	 */
	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the iso4217CurrencyAlphabeticCode
	 */
	public String getIso4217CurrencyAlphabeticCode() {
		return iso4217CurrencyAlphabeticCode;
	}

	/**
	 * @param iso4217CurrencyAlphabeticCode
	 *            the iso4217CurrencyAlphabeticCode to set
	 */
	public void setIso4217CurrencyAlphabeticCode(String iso4217CurrencyAlphabeticCode) {
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
	}

	/**
	 * @return the iso4217CurrencyName
	 */
	public String getIso4217CurrencyName() {
		return iso4217CurrencyName;
	}

	/**
	 * @param iso4217CurrencyName
	 *            the iso4217CurrencyName to set
	 */
	public void setIso4217CurrencyName(String iso4217CurrencyName) {
		this.iso4217CurrencyName = iso4217CurrencyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryDetails [countryId=" + countryId + ", countryDialCode=" + countryDialCode + ", countryName="
				+ countryName + ", iso4217CurrencyAlphabeticCode=" + iso4217CurrencyAlphabeticCode
				+ ", iso4217CurrencyName=" + iso4217CurrencyName + "]";
	}
}
