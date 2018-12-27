package com.ireslab.electraapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the stellar_account database table.
 * 
 */
@Entity
@Table(name = "stellar_account")
@NamedQuery(name = "StellarAccount.findAll", query = "SELECT s FROM StellarAccount s")
public class StellarAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stellar_account_id")
	private int stellarAccountId;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "federated_address")
	private String federatedAddress;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "public_key")
	private String publicKey;

	@Column(name = "secret_key")
	private String secretKey;

	// bi-directional one-to-one association to Account
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	public StellarAccount() {
	}

	public int getStellarAccountId() {
		return this.stellarAccountId;
	}

	public void setStellarAccountId(int stellarAccountId) {
		this.stellarAccountId = stellarAccountId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFederatedAddress() {
		return this.federatedAddress;
	}

	public void setFederatedAddress(String federatedAddress) {
		this.federatedAddress = federatedAddress;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPublicKey() {
		return this.publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}