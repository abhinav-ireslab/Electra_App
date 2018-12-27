package com.ireslab.electraapp.stellar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.stellar.sdk.Asset;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.ChangeTrustOperation;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Network;
import org.stellar.sdk.PaymentOperation;
import org.stellar.sdk.Server;
import org.stellar.sdk.Transaction;
import org.stellar.sdk.responses.AccountResponse;
import org.stellar.sdk.responses.AccountResponse.Balance;
import org.stellar.sdk.responses.SubmitTransactionResponse;
import org.stellar.sdk.responses.SubmitTransactionResponse.Extras.ResultCodes;
import org.stellar.sdk.xdr.XdrDataOutputStream;

import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;

/**
 * @author Nitin
 *
 */
@Component
public class StellarTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(StellarTransactionManager.class);

	private static final String STELLAR_TESTNET_FRIENDBOT = "/friendbot?addr=%s";

	private static String horizonUrl = "";

	@Autowired
	private StellarConfig stellarConfig;

	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("resource")
	public StellarAccountDetails createAccount() {

		StellarAccountDetails stellarAccountDetails = null;

		try {
			// create a completely new and unique pair of keys.
			// see more about KeyPair objects:
			// https://stellar.github.io/java-stellar-sdk/org/stellar/sdk/KeyPair.html

			KeyPair newAccountKeyPair = null;

			if (stellarConfig.isTestNetwork()) {

				LOG.debug("Creating user account on Stellar Test Network - " + horizonUrl);
				newAccountKeyPair = KeyPair.random();

				horizonUrl = String.format(stellarConfig.getTestNetHorizonUrl() + STELLAR_TESTNET_FRIENDBOT,
						newAccountKeyPair.getAccountId());

				URLConnection urlConnection = new URL(horizonUrl).openConnection();
				urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");

				InputStream response = urlConnection.getInputStream();
				/* InputStream response = new URL(horizonUrl).openStream(); */

				String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
				LOG.debug("New stellar account created successfully - " + body);

			} else {

				LOG.debug("Creating User Account on Stellar Live Network - " + stellarConfig.getLiveNetHorizonUrl()
						+ " with Initial Native Lumens (XLM) balance - "
						+ stellarConfig.getInitialLumensLoadQuantity());

				Network.usePublicNetwork();
				newAccountKeyPair = KeyPair.random();
			}

			if (stellarConfig.isLoadInitialLumens()) {
				try {
					LOG.debug("Loading user account with initial Lumens (XLM). . .");
					transferToken(stellarConfig.getBaseAccount().getSecretSeed(),
							new String(newAccountKeyPair.getSecretSeed()), stellarConfig.getInitialLumensLoadQuantity(),
							true);
				} catch (Exception e) {
					LOG.error("User Account cannot be loaded with initial Lumens (XLM) - "
							+ ExceptionUtils.getStackTrace(e));
					throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
							messageSource.getMessage(PropConstants.INTERNAL_SERVER_ERROR, null, Locale.getDefault()));
				}
			}

			stellarAccountDetails = new StellarAccountDetails(new String(newAccountKeyPair.getSecretSeed()),
					newAccountKeyPair.getAccountId());
			// getAccountBalance(pair.getAccountId(), false);

		} catch (Exception exp) {
			LOG.debug("Error occurred while generating stellar account - " + ExceptionUtils.getStackTrace(exp));
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_ACCOUNT_CREATION_FAILED,
					PropConstants.INTERNAL_SERVER_ERROR);
		}

		return stellarAccountDetails;
	}

	public void loadAccount() {

	}

	/**
	 * Execute Create Trustline transaction and return XDR
	 * 
	 * @param accountSecretKey
	 * @param limit
	 * @return
	 */
	public String createTrustLine(String accountSecretKey, String limit) {

		String xdrTrustlineTransaction = null;

		if (stellarConfig.isTestNetwork()) {
			Network.useTestNetwork();
			horizonUrl = stellarConfig.getTestNetHorizonUrl();

		} else {
			Network.usePublicNetwork();
			horizonUrl = stellarConfig.getLiveNetHorizonUrl();
		}

		if (limit == null) {
			limit = stellarConfig.getAssetLimit();
		}

		Server horizonServer = new Server(horizonUrl);

		// SendX Asset Token
		Asset tokenAsset = Asset.createNonNativeAsset(stellarConfig.getAssetCode(),
				KeyPair.fromSecretSeed(stellarConfig.getIssuingAccount().getSecretSeed()));

		KeyPair userAccountKeyPair = KeyPair.fromSecretSeed(accountSecretKey);

		try {
			LOG.debug("Creating and submitting trustline transaction to Stellar Network for Asset Token : "
					+ stellarConfig.getAssetCode() + " with Limit : " + limit);
			Transaction userAccTrustLineTxn = new Transaction.Builder(
					horizonServer.accounts().account(userAccountKeyPair))
							.addOperation(

									new ChangeTrustOperation.Builder(tokenAsset, limit).build())
							.build();
			userAccTrustLineTxn.sign(userAccountKeyPair);

			SubmitTransactionResponse baseTrustLineTxnResponse = horizonServer.submitTransaction(userAccTrustLineTxn);
			LOG.debug("Sequence Number - " + userAccTrustLineTxn.getSequenceNumber() + ",\nFee - "
					+ userAccTrustLineTxn.getFee() + ",\nisSuccess - " + baseTrustLineTxnResponse.isSuccess()
					+ ",\nResult XDR - " + baseTrustLineTxnResponse.getResultXdr() + ",\nLedger - "
					+ baseTrustLineTxnResponse.getLedger() + ",\nEnvelope XDR - "
					+ baseTrustLineTxnResponse.getEnvelopeXdr());

			// Transaction not success
			if (!baseTrustLineTxnResponse.isSuccess()) {

				ResultCodes resultCodes = baseTrustLineTxnResponse.getExtras().getResultCodes();
				LOG.error("Transaction Result Code - " + resultCodes.getTransactionResultCode()
						+ ",\nTransaction Operation Result Code - " + resultCodes.getOperationsResultCodes().get(0));
				throw new Exception();

			} else {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				org.stellar.sdk.xdr.Transaction.encode(new XdrDataOutputStream(byteArrayOutputStream),
						userAccTrustLineTxn.toXdr());
				xdrTrustlineTransaction = new Base64().encodeAsString(byteArrayOutputStream.toByteArray());

				LOG.debug("Account trustline transaction XDR - " + xdrTrustlineTransaction);
				return xdrTrustlineTransaction;
			}

		} catch (Exception exp) {
			LOG.error("Error occurred while creating trustline for user account having Account Id : "
					+ userAccountKeyPair.getAccountId() + "\n" + ExceptionUtils.getStackTrace(exp));
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_TRUSTLINE_CREATION_FAILED,
					PropConstants.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param userAccountPublicKey
	 * @return
	 * @throws IOException
	 */
	public String getAccountBalance(String userAccountPublicKey, boolean isNative) {

		LOG.debug("Getting account balance for Account Id - " + userAccountPublicKey);

		if (stellarConfig.isTestNetwork()) {
			Network.useTestNetwork();
			horizonUrl = stellarConfig.getTestNetHorizonUrl();
		} else {
			Network.usePublicNetwork();
			horizonUrl = stellarConfig.getLiveNetHorizonUrl();
		}

		Server horizonServer = new Server(horizonUrl);
		KeyPair keyPair = KeyPair.fromAccountId(userAccountPublicKey);

		try {

			AccountResponse accountResponse = horizonServer.accounts().account(keyPair);
			for (Balance balance : accountResponse.getBalances()) {

				if (balance.getAssetCode() != null && balance.getAssetCode().equalsIgnoreCase("SDX")) {
					LOG.debug("Asset Code - " + balance.getAssetCode() + " | Asset Balance - " + balance.getBalance()
							+ " | Limit - " + balance.getLimit());
					return balance.getBalance();

				} else if (isNative && balance.getAssetCode() == null) {
					LOG.debug("Lumens (XLM) Balance - " + balance.getBalance());
					return balance.getBalance();
				}
			}
		} catch (Exception exp) {
			LOG.error("Error while getting account balance from stellar - " + ExceptionUtils.getStackTrace(exp));
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_ACCOUNT_BALANCE_OPERATION_FAILED,
					PropConstants.INTERNAL_SERVER_ERROR);
		}

		return null;
	}

	/**
	 * @param senderSecretKey
	 * @param receiverSecretKey
	 * @param amount
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String transferToken(String senderSecretKey, String receiverSecretKey, String amount, boolean isNative)
			throws IOException {

		String transactionXDR = null;
		Asset assetToTransfer = null;
		String assetName = isNative ? "Native Lumens (XLM)" : "Tokens (" + stellarConfig.getAssetCode() + ")";

		if (stellarConfig.isTestNetwork()) {
			Network.useTestNetwork();
			horizonUrl = stellarConfig.getTestNetHorizonUrl();
		} else {
			Network.usePublicNetwork();
			horizonUrl = stellarConfig.getLiveNetHorizonUrl();
		}

		Server horizonServer = new Server(horizonUrl);

		KeyPair senderAccountKeyPair = KeyPair.fromSecretSeed(senderSecretKey);
		KeyPair receiverAccountKeyPair = KeyPair.fromSecretSeed(receiverSecretKey);

		if (isNative) {
			assetToTransfer = new AssetTypeNative();
		} else {
			assetToTransfer = Asset.createNonNativeAsset(stellarConfig.getAssetCode(),
					KeyPair.fromSecretSeed(stellarConfig.getIssuingAccount().getSecretSeed()));
		}

		LOG.debug("\nInitiating transfer of " + assetName + " from sender account to receiver account . . ."
				+ "\n\t=> Stellar Horizon URI - " + horizonUrl + ",\n\t=> Source AccountId - "
				+ senderAccountKeyPair.getAccountId() + ",\n\t=> Destination AccountId - "
				+ receiverAccountKeyPair.getAccountId() + ",\n\t=> No of " + assetName + " - " + amount);

		try {
			horizonServer.accounts().account(receiverAccountKeyPair);
		} catch (Exception exp) {
			LOG.debug("Destination account doesnot exists - " + ExceptionUtils.getStackTrace(exp));
		}

		/*
		 * From base to user account so transaction need to be built and signed
		 * by Issuing keys
		 */
		AccountResponse senderAccount = horizonServer.accounts().account(senderAccountKeyPair);
		Transaction tokenTransferToReceiverAccTxn = new Transaction.Builder(senderAccount).addOperation(

				new PaymentOperation.Builder(receiverAccountKeyPair, assetToTransfer, amount).build()).build();
		tokenTransferToReceiverAccTxn.sign(senderAccountKeyPair);

		LOG.debug("\nSubmitting " + assetName + " transfer request . . ." + "\n\t=> Sequence Number - "
				+ tokenTransferToReceiverAccTxn.getSequenceNumber() + ",\n\t=> Fee - "
				+ tokenTransferToReceiverAccTxn.getFee());

		SubmitTransactionResponse tokenTransferToReceiverTxnResponse = horizonServer
				.submitTransaction(tokenTransferToReceiverAccTxn);

		if (!tokenTransferToReceiverTxnResponse.isSuccess()) {
			ResultCodes resultCodes = tokenTransferToReceiverTxnResponse.getExtras().getResultCodes();
			LOG.error("\nError occurred while executing " + assetName + " transfer transaction on Stellar :"
					+ "\n\t=> Transaction Result Code - " + resultCodes.getTransactionResultCode()
					+ ",\n\t=> Transaction Operation Result Code - " + resultCodes.getOperationsResultCodes().get(0));

			if (resultCodes.getOperationsResultCodes().get(0).equalsIgnoreCase("op_underfunded")) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_ACCOUNT_INSUFFICIENT_BALANCE,
						messageSource.getMessage(PropConstants.INSUFFICIENT_ACCOUNT_BALANCE, null,
								Locale.getDefault()));
			}

			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_PAYMENT_OPERATION_FAILED,
					messageSource.getMessage(PropConstants.PAYMENT_TRANSACTION_FAILURE, null, Locale.getDefault()));
		}

		if (tokenTransferToReceiverTxnResponse.isSuccess()) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			org.stellar.sdk.xdr.Transaction.encode(new XdrDataOutputStream(byteArrayOutputStream),
					tokenTransferToReceiverAccTxn.toXdr());
			transactionXDR = new Base64().encodeAsString(byteArrayOutputStream.toByteArray());

			LOG.debug("\n" + assetName + " transferred successfully :" + "\n\t=> Ledger - "
					+ tokenTransferToReceiverTxnResponse.getLedger() + ",\n\t=> Result XDR - "
					+ tokenTransferToReceiverTxnResponse.getResultXdr() + ",\n\t=> Envelope XDR - "
					+ tokenTransferToReceiverTxnResponse.getEnvelopeXdr() + ",\n\t=> Transaction XDR - "
					+ transactionXDR);

			return transactionXDR;
		}

		return transactionXDR;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {

		StellarConfig stellarConfig = new StellarConfig();
		stellarConfig.setIsTestNetwork("false");
		stellarConfig.setAssetCode("SDX");
		stellarConfig.setTestnetHorizonUrl("https://horizon-testnet.stellar.org");
		stellarConfig.setLivenetHorizonUrl("https://horizon.stellar.org");

		StellarTransactionManager builder = new StellarTransactionManager();
		builder.stellarConfig = stellarConfig;

		builder.createAccount();
	}
}
