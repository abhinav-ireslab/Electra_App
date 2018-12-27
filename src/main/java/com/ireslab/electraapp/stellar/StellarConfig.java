package com.ireslab.electraapp.stellar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ireslab.electraapp.util.Constants;

/**
 * @author Nitin
 *
 */
@Component
@PropertySource(value = Constants.STELLAR_CONFIG_FILE)
@ConfigurationProperties("stellar")
public class StellarConfig {

	private boolean isTestNetwork;

	private String testnetHorizonUrl;

	private String livenetHorizonUrl;

	private String assetCode;

	private String assetXdr;

	private String assetLimit;

	private boolean isLoadInitialAsset;
	private String initialAssetLoadQuantity;

	private boolean isLoadInitialLumens;
	private String initialLumensLoadQuantity;

	private IssuingAccount issuingAccount;

	private BaseAccount baseAccount;

	public static class IssuingAccount {

		private String secretSeed;
		private String publicKey;

		/**
		 * @return the secretSeed
		 */
		public String getSecretSeed() {
			return secretSeed;
		}

		/**
		 * @param secretSeed
		 *            the secretSeed to set
		 */
		public void setSecretSeed(String secretSeed) {
			this.secretSeed = secretSeed;
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

	public static class BaseAccount {

		private String secretSeed;
		private String publicKey;

		/**
		 * @return the secretSeed
		 */
		public String getSecretSeed() {
			return secretSeed;
		}

		/**
		 * @param secretSeed
		 *            the secretSeed to set
		 */
		public void setSecretSeed(String secretSeed) {
			this.secretSeed = secretSeed;
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

	/**
	 * @return the assetCode
	 */
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * @param assetCode
	 *            the assetCode to set
	 */
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	/**
	 * @return the assetXdr
	 */
	public String getAssetXdr() {
		return assetXdr;
	}

	/**
	 * @param assetXdr
	 *            the assetXdr to set
	 */
	public void setAssetXdr(String assetXdr) {
		this.assetXdr = assetXdr;
	}

	/**
	 * @return the isTestNetwork
	 */
	public boolean isTestNetwork() {
		return isTestNetwork;
	}

	/**
	 * @param isTestNetwork
	 *            the isTestNetwork to set
	 */
	public void setIsTestNetwork(String isTestNetwork) {
		this.isTestNetwork = Boolean.valueOf(isTestNetwork);
	}

	/**
	 * @return the horizonUrl
	 */
	public String getTestNetHorizonUrl() {
		return testnetHorizonUrl;
	}

	/**
	 * @param testNetHorizonUrl
	 *            the horizonUrl to set
	 */
	public void setTestnetHorizonUrl(String testNetHorizonUrl) {
		this.testnetHorizonUrl = testNetHorizonUrl;
	}

	/**
	 * @return the issuerAccount
	 */
	public IssuingAccount getIssuingAccount() {
		return issuingAccount;
	}

	/**
	 * @param issuerAccount
	 *            the issuerAccount to set
	 */
	public void setIssuingAccount(IssuingAccount issuingAccount) {
		this.issuingAccount = issuingAccount;
	}

	/**
	 * @return the baseAccount
	 */
	public BaseAccount getBaseAccount() {
		return baseAccount;
	}

	/**
	 * @param baseAccount
	 *            the baseAccount to set
	 */
	public void setBaseAccount(BaseAccount baseAccount) {
		this.baseAccount = baseAccount;
	}

	/**
	 * @return the assetLimit
	 */
	public String getAssetLimit() {
		return assetLimit;
	}

	/**
	 * @param assetLimit
	 *            the assetLimit to set
	 */
	public void setAssetLimit(String assetLimit) {
		this.assetLimit = assetLimit;
	}

	/**
	 * @return the initialAssetLoad
	 */
	public String getInitialAssetLoadQuantity() {
		return initialAssetLoadQuantity;
	}

	/**
	 * @param initialAssetLoadQuantity
	 *            the initialAssetLoad to set
	 */
	public void setInitialAssetLoadQuantity(String initialAssetLoadQuantity) {
		this.initialAssetLoadQuantity = initialAssetLoadQuantity;
	}

	/**
	 * @return the isLoadInitialAsset
	 */
	public boolean isLoadInitialAsset() {
		return isLoadInitialAsset;
	}

	/**
	 * @param isLoadInitialAsset
	 *            the isLoadInitialAsset to set
	 */
	public void setIsLoadInitialAsset(String isLoadInitialAsset) {
		this.isLoadInitialAsset = Boolean.valueOf(isLoadInitialAsset);
	}

	/**
	 * @return the isLoadInitialLumens
	 */
	public boolean isLoadInitialLumens() {
		return isLoadInitialLumens;
	}

	/**
	 * @param isLoadInitialLumens
	 *            the isLoadInitialLumens to set
	 */
	public void setIsLoadInitialLumens(String isLoadInitialLumens) {
		this.isLoadInitialLumens = Boolean.valueOf(isLoadInitialLumens);
	}

	/**
	 * @return the initialLumensLoadQuantity
	 */
	public String getInitialLumensLoadQuantity() {
		return initialLumensLoadQuantity;
	}

	/**
	 * @param initialLumensLoadQuantity
	 *            the initialLumensLoadQuantity to set
	 */
	public void setInitialLumensLoadQuantity(String initialLumensLoadQuantity) {
		this.initialLumensLoadQuantity = initialLumensLoadQuantity;
	}

	/**
	 * @return the liveNetHorizonUrl
	 */
	public String getLiveNetHorizonUrl() {
		return livenetHorizonUrl;
	}

	/**
	 * @param liveNetHorizonUrl
	 *            the liveNetHorizonUrl to set
	 */
	public void setLivenetHorizonUrl(String liveNetHorizonUrl) {
		this.livenetHorizonUrl = liveNetHorizonUrl;
	}
}
