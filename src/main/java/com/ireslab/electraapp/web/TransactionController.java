package com.ireslab.electraapp.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.model.CashOutRequest;
import com.ireslab.electraapp.model.CashOutResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.LoadTokensRequest;
import com.ireslab.electraapp.model.LoadTokensResponse;
import com.ireslab.electraapp.model.SendTokensRequest;
import com.ireslab.electraapp.model.SendTokensResponse;
import com.ireslab.electraapp.model.TransactionHistoryRequest;
import com.ireslab.electraapp.model.TransactionHistoryResponse;
import com.ireslab.electraapp.service.TransactionService;
import com.ireslab.electraapp.springsecurity.SpringSecurityUtil;
import com.ireslab.sendx.electra.model.TransactionPurposeResponse;

/**
 * @author iRESlab
 *
 */
@RestController
public class TransactionController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private TransactionService transactionService;

	
	/**
	 * use to load money in his account.
	 * 
	 * @param loadTokensRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/loadTokens", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoadTokensResponse loadTokens(@RequestBody LoadTokensRequest loadTokensRequest)
			throws JsonProcessingException {

		LoadTokensResponse loadTokensResponse = null;
		LOG.info("Request for load tokens");

		nameRequestThread((loadTokensRequest.getCountryDialCode() + loadTokensRequest.getMobileNumber()),
				RequestType.LOAD_TOKENS, loadTokensRequest);

		loadTokensResponse = transactionService.handleLoadTokens(loadTokensRequest);
		LOG.info("Response sent for load tokens");

		return loadTokensResponse;
	}

	
	/**
	 * use to transfer amount app user's account to others user's account.
	 * 
	 * @param sendTokensRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/transferTokens", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SendTokensResponse transferTokens(@RequestBody SendTokensRequest sendTokensRequest)
			throws JsonProcessingException {
		LOG.info("Token transfer request recieved - " + objectWriter.writeValueAsString(sendTokensRequest));
		SendTokensResponse sendTokensResponse = null;

		nameRequestThread((sendTokensRequest.getSenderCountryDialCode() + sendTokensRequest.getSenderMobileNumber()),
				RequestType.TRANSFER_TOKENS, sendTokensRequest);
		//sendTokensRequest.setBeneficiaryUniqueCode("13663");

		sendTokensResponse = transactionService.handleTokensTransfer(sendTokensRequest);
		LOG.info("Token transfer response sent - " + objectWriter.writeValueAsString(sendTokensResponse));

		return sendTokensResponse;
	}

	
	/**
	 * use to validate top up.
	 * 
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/validateUserTopUp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse validateUserTopUp(@RequestParam(value = "mobileNumber", required = false) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = false) String countryDialCode)
			throws JsonProcessingException {

		GenericResponse validateUserTopUpResponse = null;

		if (mobileNumber == null || countryDialCode == null) {
			String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
			mobileNumber = new Long(usernameToken[1]);
			countryDialCode = usernameToken[0];
		}

		LOG.info("Validate User TopUp request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		validateUserTopUpResponse = transactionService.validateUserTopUp(BigInteger.valueOf(mobileNumber),
				countryDialCode);
		LOG.info("Request Activation code response sent - "
				+ objectWriter.writeValueAsString(validateUserTopUpResponse));

		return validateUserTopUpResponse;
	}

	
	/**
	 * use to transfer amount app wallet to his bank account.
	 * 
	 * @param cashOutRequest
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/cashOut", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CashOutResponse cashOutTokens(@RequestBody CashOutRequest cashOutRequest, HttpServletRequest request) throws JsonProcessingException {
		// Getting username details from Spring Security Context
				String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
				BigInteger mobileNumber = new BigInteger(usernameToken[1]);
				String countryDialCode = usernameToken[0];
				
				//cashOutRequest.setMobileNumber(Long.parseLong(""+mobileNumber));
		CashOutResponse cashOutResponse = null;

		nameRequestThread((cashOutRequest.getCountryDialCode() + cashOutRequest.getMobileNumber()),
				RequestType.CASHOUT_TOKENS, cashOutRequest);

		cashOutResponse = transactionService.handleCashOutTokens(cashOutRequest);
		LOG.info("CashOut Tokens response sent  ");

		return cashOutResponse;
	}

	
	
	/**
	 * use to get transaction history of the user.
	 * 
	 * @param tnxHistoryRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/transactionHistory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionHistoryResponse transactionHistory(@RequestBody TransactionHistoryRequest tnxHistoryRequest)
			throws JsonProcessingException {

		TransactionHistoryResponse tnxHistoryResponse = null;
		LOG.info("Transaction History request received - " + objectWriter.writeValueAsString(tnxHistoryRequest));

		tnxHistoryResponse = transactionService.handleTransactionHistory(tnxHistoryRequest);
		LOG.info("Transaction History response sent  ");

		return tnxHistoryResponse;
	}
	
	/**
	 * use to get transaction purpose list.
	 * 
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getAllTransactionPurpose", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionPurposeResponse getAllTransactionPurpose(@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode)
			throws JsonProcessingException {

		TransactionPurposeResponse transactionPurposeResponse = null;
		LOG.info("Transaction Purpose request received :- \n mobile number - " + mobileNumber +"\n country dial code - "+countryDialCode);

		transactionPurposeResponse = transactionService.getAllTransactionPurpose(mobileNumber, countryDialCode);
		LOG.info("Transaction History response sent ");

		return transactionPurposeResponse;
	}
}
