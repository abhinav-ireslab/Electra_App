package com.ireslab.electraapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckoutBankDetailsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7307126450243064733L;

	private String bankName;

	private String bankCode1;

	private String bankCode2;

	private String bankInfo;

	private String bankLogoUrl;

	private Double accLimit;

	private Integer accNumberEnd;

	private Integer accNumberLength;

	private Integer accNumberStart;

	private String countryName;

	private String iso4217CurrencyName;

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankCode1
	 */
	public String getBankCode1() {
		return bankCode1;
	}

	/**
	 * @param bankCode1
	 *            the bankCode1 to set
	 */
	public void setBankCode1(String bankCode1) {
		this.bankCode1 = bankCode1;
	}

	/**
	 * @return the bankCode2
	 */
	public String getBankCode2() {
		return bankCode2;
	}

	/**
	 * @param bankCode2
	 *            the bankCode2 to set
	 */
	public void setBankCode2(String bankCode2) {
		this.bankCode2 = bankCode2;
	}

	/**
	 * @return the bankInfo
	 */
	public String getBankInfo() {
		return bankInfo;
	}

	/**
	 * @param bankInfo
	 *            the bankInfo to set
	 */
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	/**
	 * @return the bankLogoUrl
	 */
	public String getBankLogoUrl() {
		return bankLogoUrl;
	}

	/**
	 * @param bankLogoUrl
	 *            the bankLogoUrl to set
	 */
	public void setBankLogoUrl(String bankLogoUrl) {
		this.bankLogoUrl = bankLogoUrl;
	}

	/**
	 * @return the accLimit
	 */
	public Double getAccLimit() {
		return accLimit;
	}

	/**
	 * @param accLimit
	 *            the accLimit to set
	 */
	public void setAccLimit(Double accLimit) {
		this.accLimit = accLimit;
	}

	/**
	 * @return the accNumberEnd
	 */
	public Integer getAccNumberEnd() {
		return accNumberEnd;
	}

	/**
	 * @param accNumberEnd
	 *            the accNumberEnd to set
	 */
	public void setAccNumberEnd(Integer accNumberEnd) {
		this.accNumberEnd = accNumberEnd;
	}

	/**
	 * @return the accNumberLength
	 */
	public Integer getAccNumberLength() {
		return accNumberLength;
	}

	/**
	 * @param accNumberLength
	 *            the accNumberLength to set
	 */
	public void setAccNumberLength(Integer accNumberLength) {
		this.accNumberLength = accNumberLength;
	}

	/**
	 * @return the accNumberStart
	 */
	public Integer getAccNumberStart() {
		return accNumberStart;
	}

	/**
	 * @param accNumberStart
	 *            the accNumberStart to set
	 */
	public void setAccNumberStart(Integer accNumberStart) {
		this.accNumberStart = accNumberStart;
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
}
