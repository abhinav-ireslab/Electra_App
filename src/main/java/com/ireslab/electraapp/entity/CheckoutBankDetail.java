package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the checkout_bank_details database table.
 * 
 */
@Entity
@Table(name = "checkout_bank_details")
@NamedQuery(name = "CheckoutBankDetail.findAll", query = "SELECT c FROM CheckoutBankDetail c")
public class CheckoutBankDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "checkout_bank_id")
	private Integer checkoutBankId;

	@Column(name = "acc_limit")
	private Double accLimit;

	@Column(name = "acc_number_end")
	private Integer accNumberEnd;

	@Column(name = "acc_number_length")
	private Integer accNumberLength;

	@Column(name = "acc_number_start")
	private Integer accNumberStart;

	@Column(name = "bank_code1")
	private String bankCode1;

	@Column(name = "bank_code2")
	private String bankCode2;

	@Column(name = "bank_info")
	private String bankInfo;

	@Column(name = "bank_logo_url")
	private String bankLogoUrl;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public CheckoutBankDetail() {
	}

	public Integer getCheckoutBankId() {
		return this.checkoutBankId;
	}

	public void setCheckoutBankId(Integer checkoutBankId) {
		this.checkoutBankId = checkoutBankId;
	}

	public Double getAccLimit() {
		return this.accLimit;
	}

	public void setAccLimit(Double accLimit) {
		this.accLimit = accLimit;
	}

	public Integer getAccNumberEnd() {
		return this.accNumberEnd;
	}

	public void setAccNumberEnd(Integer accNumberEnd) {
		this.accNumberEnd = accNumberEnd;
	}

	public Integer getAccNumberLength() {
		return this.accNumberLength;
	}

	public void setAccNumberLength(Integer accNumberLength) {
		this.accNumberLength = accNumberLength;
	}

	public Integer getAccNumberStart() {
		return this.accNumberStart;
	}

	public void setAccNumberStart(Integer accNumberStart) {
		this.accNumberStart = accNumberStart;
	}

	public String getBankCode1() {
		return this.bankCode1;
	}

	public void setBankCode1(String bankCode1) {
		this.bankCode1 = bankCode1;
	}

	public String getBankCode2() {
		return this.bankCode2;
	}

	public void setBankCode2(String bankCode2) {
		this.bankCode2 = bankCode2;
	}

	public String getBankInfo() {
		return this.bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	public String getBankLogoUrl() {
		return this.bankLogoUrl;
	}

	public void setBankLogoUrl(String bankLogoUrl) {
		this.bankLogoUrl = bankLogoUrl;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}