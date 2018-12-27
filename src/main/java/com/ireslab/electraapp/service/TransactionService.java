package com.ireslab.electraapp.service;

import java.math.BigInteger;

import com.ireslab.electraapp.model.CashOutRequest;
import com.ireslab.electraapp.model.CashOutResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.LoadTokensRequest;
import com.ireslab.electraapp.model.LoadTokensResponse;
import com.ireslab.electraapp.model.SendTokensRequest;
import com.ireslab.electraapp.model.SendTokensResponse;
import com.ireslab.electraapp.model.TransactionHistoryRequest;
import com.ireslab.electraapp.model.TransactionHistoryResponse;
import com.ireslab.sendx.electra.model.TransactionPurposeResponse;

/**
 * @author Nitin
 *
 */
public interface TransactionService {

	/**
	 * @param sendTokensRequest
	 * @return
	 */
	public SendTokensResponse handleTokensTransfer(SendTokensRequest sendTokensRequest);

	/**
	 * @param cashOutRequest
	 * @return
	 */
	public CashOutResponse handleCashOutTokens(CashOutRequest cashOutRequest);

	/**
	 * @param txnHistoryRequest
	 * @return
	 */
	public TransactionHistoryResponse handleTransactionHistory(TransactionHistoryRequest txnHistoryRequest);
	
	/**
	 * @param loadtokensRequest
	 * @return
	 */
	public LoadTokensResponse handleLoadTokens(LoadTokensRequest loadtokensRequest);
	
	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public GenericResponse validateUserTopUp(BigInteger mobileNumber, String countryDialCode);

	public TransactionPurposeResponse getAllTransactionPurpose(Long mobileNumber, String countryDialCode);
}
