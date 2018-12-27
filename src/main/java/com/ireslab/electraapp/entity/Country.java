package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id")
	private int countryId;

	@Column(name = "country_dial_code")
	private String countryDialCode;

	@Column(name = "country_name")
	private String countryName;

	@Column(name = "iso4217_currency_name")
	private String iso4217CurrencyName;

	@Column(name = "iso4217_currency_alphabetic_code")
	private String iso4217CurrencyAlphabeticCode;
	
	@Column(name = "country_timezone")
	private String countryTimeZone;
	
	@Column(name = "currency_symbol")
	private String currencySymbol;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	// bi-directional one-to-one association to Account
	@OneToMany(mappedBy = "country")
	private List<Account> account;

	// bi-directional many-to-one association to Profile
	@OneToMany(mappedBy = "country")
	private List<Profile> profiles;

	@OneToMany(mappedBy = "beneficiaryCountry")
	private List<ScheduledTransaction> scheduledTransactions;
	
	@OneToMany(mappedBy="country")
	private List<CheckoutBankDetail> checkoutBankDetails;

	public Country() {
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryDialCode() {
		return this.countryDialCode;
	}

	public void setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIso4217CurrencyAlphabeticCode() {
		return this.iso4217CurrencyAlphabeticCode;
	}

	public void setIso4217CurrencyAlphabeticCode(String iso4217CurrencyAlphabeticCode) {
		this.iso4217CurrencyAlphabeticCode = iso4217CurrencyAlphabeticCode;
	}

	public String getIso4217CurrencyName() {
		return this.iso4217CurrencyName;
	}

	public void setIso4217CurrencyName(String iso4217CurrencyName) {
		this.iso4217CurrencyName = iso4217CurrencyName;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setCountry(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setCountry(null);

		return profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryDialCode=" + countryDialCode + ", countryName="
				+ countryName + ", iso4217CurrencyAlphabeticCode=" + iso4217CurrencyAlphabeticCode
				+ ", iso4217CurrencyName=" + iso4217CurrencyName + "]";
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(List<Account> account) {
		this.account = account;
	}

	/**
	 * @return the scheduledTransactions
	 */
	public List<ScheduledTransaction> getScheduledTransactions() {
		return scheduledTransactions;
	}

	/**
	 * @param scheduledTransactions
	 *            the scheduledTransactions to set
	 */
	public void setScheduledTransactions(List<ScheduledTransaction> scheduledTransactions) {
		this.scheduledTransactions = scheduledTransactions;
	}

	/**
	 * @return the checkoutBankDetails
	 */
	public List<CheckoutBankDetail> getCheckoutBankDetails() {
		return checkoutBankDetails;
	}

	/**
	 * @param checkoutBankDetails the checkoutBankDetails to set
	 */
	public void setCheckoutBankDetails(List<CheckoutBankDetail> checkoutBankDetails) {
		this.checkoutBankDetails = checkoutBankDetails;
	}

	public String getCountryTimeZone() {
		return countryTimeZone;
	}

	public void setCountryTimeZone(String countryTimeZone) {
		this.countryTimeZone = countryTimeZone;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
}