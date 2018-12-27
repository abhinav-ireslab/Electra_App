package com.ireslab.electraapp.stellar;

public class StellarAccountDetails {

	private String secretKey;
	private String publicKey;

	public StellarAccountDetails() {
		// TODO Auto-generated constructor stub
	}

	public StellarAccountDetails(String secretKey, String publicKey) {
		super();
		this.secretKey = secretKey;
		this.publicKey = publicKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey
	 *            the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey
	 *            the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
