package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.electra.ElectraApiConfig;
import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.entity.Profile;
import com.ireslab.electraapp.entity.ScheduledTransaction;
import com.ireslab.electraapp.entity.TopupTransaction;
import com.ireslab.electraapp.entity.TransactionDetail;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.CashOutRequest;
import com.ireslab.electraapp.model.CashOutResponse;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.LoadTokensRequest;
import com.ireslab.electraapp.model.LoadTokensResponse;
import com.ireslab.electraapp.model.SendTokensRequest;
import com.ireslab.electraapp.model.SendTokensResponse;
import com.ireslab.electraapp.model.TransactionHistoryRequest;
import com.ireslab.electraapp.model.TransactionHistoryResponse;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.model.UserTransactionDetails;
import com.ireslab.electraapp.notification.SMSService;

import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.repository.CountryRepository;
import com.ireslab.electraapp.repository.ScheduledTransactionRepository;
import com.ireslab.electraapp.repository.TopupTransactionRepository;
import com.ireslab.electraapp.repository.TransactionDetailRepository;
import com.ireslab.electraapp.service.CommonService;
import com.ireslab.electraapp.service.TransactionService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.electraapp.springsecurity.SpringSecurityUtil;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.CommonUtils;
import com.ireslab.electraapp.util.Constants;
import com.ireslab.electraapp.util.PropConstants;
import com.ireslab.sendx.electra.dto.CashOutDto;
import com.ireslab.sendx.electra.model.ClientInfoRequest;
import com.ireslab.sendx.electra.model.ClientInfoResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;
import com.ireslab.sendx.electra.model.TokenTransferRequest;
import com.ireslab.sendx.electra.model.TokenTransferResponse;
import com.ireslab.sendx.electra.model.TransactionLimitResponse;
import com.ireslab.sendx.electra.model.TransactionPurposeResponse;



/**
 * @author iRESlab
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private SMSService smsSender;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private CommonService commonService;

	@Autowired
	private TopupTransactionRepository topupTransactionRepository;

	@Autowired
	private TransactionDetailRepository txnDetailRepo;

	@Autowired
	private ScheduledTransactionRepository scheduledTxnRepo;

	@Autowired
	private TransactionalApiService transactionalApiService;
	
	@Autowired
	private ElectraApiConfig ElectraApiConfig;
	
	
	
	@Autowired
	private ObjectWriter objectWriter;
	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#handleTokensTransfer(com.ireslab.electraapp.model.SendTokensRequest)
	 */
	@Override
	@Transactional
	public SendTokensResponse handleTokensTransfer(SendTokensRequest sendTokensRequest) {

		SendTokensResponse sendTokensResponse = null;
		
		try {
			LOG.info("Transfer Token Request Recieved in Service :- "+ objectWriter.writeValueAsString(sendTokensRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		TransactionLimitResponse transactionLimitResponse = transactionalApiService.getTransactionLimit();
		
		
        double numberOfToken = Double.parseDouble(sendTokensRequest.getNoOfTokens());
		
		if(numberOfToken>Double.parseDouble(transactionLimitResponse.getDailyLimit()) && numberOfToken<= Double.parseDouble(transactionLimitResponse.getMonthlyLimit())) {
			
			 sendTokensResponse = new SendTokensResponse();
	            sendTokensResponse.setStatus(HttpStatus.OK.value());
	            sendTokensResponse.setCode(101);
	           sendTokensResponse.setMessage("You are exceeding your daily transaction limit.");
	           return sendTokensResponse;
		}
		
		if(numberOfToken> Double.parseDouble(transactionLimitResponse.getMonthlyLimit())) {
			
			 sendTokensResponse = new SendTokensResponse();
	            sendTokensResponse.setStatus(HttpStatus.OK.value());
	            sendTokensResponse.setCode(101);
	           sendTokensResponse.setMessage("You are exceeding your monthly transaction limit.");
	           return sendTokensResponse;
		}

		BigInteger senderMobileNumber = BigInteger.valueOf(sendTokensRequest.getSenderMobileNumber());
		String senderCountryDialCode = sendTokensRequest.getSenderCountryDialCode();

		// Get account details of sender (from spring context if null)
		if (senderMobileNumber == null) {
			String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
			senderMobileNumber = new BigInteger(usernameToken[1]);
			senderCountryDialCode = usernameToken[0];
		}

		LOG.info("Getting Sender account details from database. . . ");

		// Get account details of beneficiary
		Account senderAccountDetails = accountRepo.findByMobileNumberAndCountry_CountryDialCode(senderMobileNumber,
				senderCountryDialCode);

		if (senderAccountDetails == null) {
			LOG.error("Sender Account doesn't exists");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.ACCOUNT_NOT_EXISTS);
		}
		TokenTransferRequest tokenTransferRequestForcheck =new TokenTransferRequest();
		tokenTransferRequestForcheck.setNoOfToken(sendTokensRequest.getNoOfTokens());
		tokenTransferRequestForcheck.setSenderCorrelationId(senderAccountDetails.getUserCorrelationId());

		TokenTransferResponse tokenTransferResponse = transactionalApiService.transactionLimitsForAllowTransfer(tokenTransferRequestForcheck);
		if(tokenTransferResponse.getCode().intValue()==AppStatusCodes.DAILY_TRANSACTION_LIMIT_REACHED.intValue()) {
			
            sendTokensResponse = new SendTokensResponse();
            sendTokensResponse.setStatus(HttpStatus.OK.value());//HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage
            sendTokensResponse.setCode(101);
           // messageSource.getMessage(PropConstants., null, Locale.getDefault())
           sendTokensResponse.setMessage("You have reached your allowed transaction daily limit.");
            
			
		}
		else if(tokenTransferResponse.getCode().intValue()==AppStatusCodes.MONTHLY_TRANSACTION_LIMIT_REACHED.intValue()) {
			
            sendTokensResponse = new SendTokensResponse();
            sendTokensResponse.setStatus(HttpStatus.OK.value());//HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage
            sendTokensResponse.setCode(101);
           // messageSource.getMessage(PropConstants., null, Locale.getDefault())
           sendTokensResponse.setMessage("You have reached your allowed transaction monthly limit.");
            
			
		}
		else {

		Profile senderProfile = senderAccountDetails.getProfile();
		LOG.info("Sender Details: \n\tMobile Number - " + senderCountryDialCode + senderMobileNumber + ",\n\tName - "
				+ senderProfile.getFirstName() + " " + senderProfile.getLastName() + ",\n\tEmail Address - "
				+ senderProfile.getEmailAddress());

		BigInteger beneficiaryMobileNumber = BigInteger.valueOf(sendTokensRequest.getBeneficiaryMobileNumber());
		String beneficiaryCountryDialCode = sendTokensRequest.getBeneficiaryCountryDialCode();

		LOG.info("Getting Beneficiary account details from database. . . ");

		
		
		
		
		
	UserProfile userProfileModel = null;
		
		if(sendTokensRequest.getBeneficiaryUniqueCode() != null && sendTokensRequest.getBeneficiaryUniqueCode().length()>0) {
			userProfileModel = commonService.searchUserByuniqueCodeInElectra(sendTokensRequest.getBeneficiaryUniqueCode());
		}else {
			
			
			
			Account beneficiaryAccountDetails = accountRepo
					.findByMobileNumberAndCountry_CountryDialCode(beneficiaryMobileNumber, beneficiaryCountryDialCode);
			
			if(beneficiaryAccountDetails  != null) {
				Profile profile = beneficiaryAccountDetails.getProfile();
				userProfileModel = new UserProfile();
				userProfileModel.setAccountStatus(Enum.valueOf(com.ireslab.sendx.electra.utils.Status.class, beneficiaryAccountDetails.getStatus()));
				userProfileModel.setFirstName(profile.getFirstName());
				userProfileModel.setLastName(profile.getLastName());
				userProfileModel.setEmailAddress(profile.getEmailAddress());
				userProfileModel.setUserCorrelationId(beneficiaryAccountDetails.getUserCorrelationId());
				userProfileModel.setIsClient(beneficiaryAccountDetails.getIsClient());
				UserProfile profileForStatus = null;
				if(beneficiaryAccountDetails.getIsClient()) {
					profileForStatus = transactionalApiService.invokeClientProfileAPI(userProfileModel.getUserCorrelationId());	
				}
				else {
					profileForStatus = transactionalApiService.invokeUserProfileAPI(userProfileModel.getUserCorrelationId());
				}
				
				if(profileForStatus.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {
					userProfileModel = commonService.searchUserByDialCodeAndMobileInElectra(sendTokensRequest.getBeneficiaryCountryDialCode(),sendTokensRequest.getBeneficiaryMobileNumber());
				}
			}else {
				
				userProfileModel = commonService.searchUserByDialCodeAndMobileInElectra(sendTokensRequest.getBeneficiaryCountryDialCode(),sendTokensRequest.getBeneficiaryMobileNumber());
				
				
			}
			
			
			
		}

		
		UserProfile profile=null;
		
		if(userProfileModel != null) {
			if (!userProfileModel.isClient()) {
				
				profile = transactionalApiService.invokeUserProfileAPI(userProfileModel.getUserCorrelationId());
				try {
					LOG.debug("invokeUserProfileAPI :"+objectWriter.writeValueAsString(profile));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				
				profile = transactionalApiService.invokeClientProfileAPI(userProfileModel.getUserCorrelationId());
				try {
					LOG.debug("invokeClientProfileAPI :"+objectWriter.writeValueAsString(profile));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			if(profile.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.SUSPENDED)) {
				 sendTokensResponse = new SendTokensResponse();
		            sendTokensResponse.setStatus(HttpStatus.OK.value());
		            sendTokensResponse.setCode(101);
		           
		           sendTokensResponse.setMessage("This account has been suspended.");
		           return sendTokensResponse;
			}
		}
			
		
		// check if the beneficiary user is registered user
		if (userProfileModel == null || profile.getAccountStatus().equals(com.ireslab.sendx.electra.utils.Status.TERMINATED)) {

			LOG.info("Beneficiary - " + beneficiaryCountryDialCode + beneficiaryMobileNumber
					+ " is not registered. Scheduling payment and sending SMS message. . . .");

			ScheduledTransaction scheduledTransaction = new ScheduledTransaction();
			scheduledTransaction.setSenderAccount(senderAccountDetails);
			scheduledTransaction.setBeneficiaryMobileNumber(beneficiaryMobileNumber);
			scheduledTransaction.setBeneficiaryCountry(
					countryRepo.findOne(commonService.getCountryDetails(beneficiaryCountryDialCode).getCountryId()));
			scheduledTransaction.setNoOfTokens(sendTokensRequest.getNoOfTokens());
			scheduledTransaction.setCreatedDate(new Date());
			scheduledTransaction.setModifiedDate(new Date());

			handleUnregisteredBeneficiaryTransfer(scheduledTransaction);
			
			String successMessage = String.format(
					messageSource.getMessage(PropConstants.PAYMENT_TRANSACTION_REQUEST_SCHEDULED, null,
							Locale.getDefault()),
					sendTokensRequest.getNoOfTokens(),senderProfile.getCountry().getIso4217CurrencyAlphabeticCode(), (beneficiaryCountryDialCode + beneficiaryMobileNumber));

			sendTokensResponse = new SendTokensResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage);

		} else {
			String transactionXDR = null;

			
			String beneficiaryName = userProfileModel.getFirstName() + " " + userProfileModel.getLastName();

			LOG.info("Beneficiary Details: \n\tMobile Number - " + beneficiaryCountryDialCode + beneficiaryMobileNumber
					+ ",\n\tName - " + beneficiaryName + ",\n\tEmail Address - "
					+ userProfileModel.getEmailAddress());

			LOG.info("Initiating transfer of '" + sendTokensRequest.getNoOfTokens() + "' tokens");

			// Save transaction details in database
			TransactionDetail transactionDetail = new TransactionDetail();
			transactionDetail.setNoOfTokens(sendTokensRequest.getNoOfTokens());
			transactionDetail.setSenderAccountId(senderAccountDetails);
			
			transactionDetail.setModifiedDate(new Date());
			transactionDetail.setTransactionDate(new Date());

			String accountBalance = null;

			// transfer tokens
			try {
				sendTokensRequest.setSenderCorrelationId(senderAccountDetails.getUserCorrelationId());
				sendTokensRequest.setBeneficiaryCorrelationId(userProfileModel.getUserCorrelationId());
				
				TokenTransferRequest tokenTransferRequest =new TokenTransferRequest();
				
				tokenTransferRequest.setClientId(ElectraApiConfig.getClientCorrelationId());
				
				tokenTransferRequest.setNoOfToken(sendTokensRequest.getFee());
				tokenTransferRequest.setReceiverCorrelationId(ElectraApiConfig.getClientCorrelationId());
				
				tokenTransferRequest.setSenderCorrelationId(sendTokensRequest.getSenderCorrelationId());
				transactionalApiService.invokeTransferFeeToMaster(tokenTransferRequest);
				
				if ((accountBalance = transactionalApiService.invokeTransferTokensAPI(sendTokensRequest)) == null) {
					throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
							PropConstants.INTERNAL_SERVER_ERROR);
				}
				
				

			} catch (BusinessException exp) {
				transactionDetail.setTransactionStatus((short) 2);
				transactionDetail.setTransactionStatusMessage("Failed");

				txnDetailRepo.save(transactionDetail);
				throw new BusinessException(exp);

			} catch (Exception exp) {

				transactionDetail.setTransactionStatus((short) 2);
				transactionDetail.setTransactionStatusMessage("Failed");

				txnDetailRepo.save(transactionDetail);

				LOG.error("Token transfer operation failed due to error - " + ExceptionUtils.getStackTrace(exp));
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_PAYMENT_OPERATION_FAILED,
						PropConstants.PAYMENT_TRANSACTION_FAILURE, exp);
			}

			LOG.info("Tokens transferred successfully");

			transactionDetail.setTransactionStatus((short) 1);
			transactionDetail.setTransactionXdr(transactionXDR);
			transactionDetail.setTransactionStatusMessage("Success");

			txnDetailRepo.save(transactionDetail);

			String successMessage = String.format(
					messageSource.getMessage(PropConstants.PAYMENT_TRANSACTION_SUCCESS, null, Locale.getDefault()),
					sendTokensRequest.getNoOfTokens(),senderProfile.getCountry().getIso4217CurrencyAlphabeticCode(), beneficiaryName);

			sendTokensResponse = new SendTokensResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage);
			sendTokensResponse.setAccountBalance(accountBalance);
		}
		}
		return sendTokensResponse;
	}

	/**
	 * use to save un-registered mobile number in the database for scheduled transaction.
	 * 
	 * @param scheduledTransaction
	 */
	
	@Transactional(rollbackOn = Exception.class)
	private void handleUnregisteredBeneficiaryTransfer(ScheduledTransaction scheduledTransaction) {

		String userMobileNumber = scheduledTransaction.getBeneficiaryCountry().getCountryDialCode().replace("+", "")
				+ String.valueOf(scheduledTransaction.getBeneficiaryMobileNumber());

		try {
			LOG.info("Scheduling transaction in database . . . ");
			scheduledTxnRepo.save(scheduledTransaction);

		} catch (Exception exp) {
			LOG.error("Error occurred while scheduling transaction in database. . . . ");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_PAYMENT_OPERATION_FAILED,
					PropConstants.PAYMENT_TRANSACTION_FAILURE);
		}

		String downloadAppMessage = String.format(smsSender.getDownloadAppMessage(), "");

		if (!smsSender.sendMessage(userMobileNumber, downloadAppMessage, scheduledTransaction.getBeneficiaryCountry()
				.getCountryDialCode().equalsIgnoreCase(Constants.US_COUNTRY_DIAL_CODE))) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_PAYMENT_OPERATION_FAILED,
					PropConstants.PAYMENT_TRANSACTION_FAILURE);
		}
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#handleCashOutTokens(com.ireslab.electraapp.model.CashOutRequest)
	 */
	@Override
	public CashOutResponse handleCashOutTokens(CashOutRequest cashOutTokensRequest) {

		CashOutResponse cashOutResponse = new CashOutResponse();

		if (cashOutTokensRequest.getMobileNumber() == null) {
			String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
			cashOutTokensRequest.setMobileNumber(Long.valueOf(usernameToken[1]));
			cashOutTokensRequest.setCountryDialCode(usernameToken[0]);
		}

		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(
				BigInteger.valueOf(cashOutTokensRequest.getMobileNumber()), cashOutTokensRequest.getCountryDialCode());

		if (account == null) {
			LOG.error("Account not exists !!");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_NOT_EXISTS,
					PropConstants.ACCOUNT_NOT_EXISTS);
		}
		
		

		cashOutTokensRequest.setUserCorrelationId(account.getUserCorrelationId());

		LOG.debug("Calling Electra Cashout tokens API");
		String accountBalance = transactionalApiService.invokeCashoutTokensAPI(cashOutTokensRequest);

		if (accountBalance == null) {

			LOG.error("Electra API failed");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.STELLAR_PAYMENT_OPERATION_FAILED,
					PropConstants.PAYMENT_TRANSACTION_FAILURE);
		}

		LOG.info("Saving cash out transaction as scheduled transaction in database . . . ");

		ScheduledTransaction scheduledTransaction = new ScheduledTransaction();
		scheduledTransaction.setSenderAccount(account);
		scheduledTransaction.setNoOfTokens(cashOutTokensRequest.getNoOfTokens());
		scheduledTransaction.setCashOut(true);
		scheduledTransaction.setInstitutionName(cashOutTokensRequest.getInstitutionName());
		scheduledTransaction.setInstitutionAccountNumber(cashOutTokensRequest.getInstitutionAccountNumber());
		scheduledTransaction.setAdditionalInstitutionInfo(cashOutTokensRequest.getAddtionalInstitutionInfo());
		scheduledTransaction.setCreatedDate(new Date());
		scheduledTransaction.setModifiedDate(new Date());
		scheduledTxnRepo.save(scheduledTransaction);

		

		String successMessage = String.format(
				messageSource.getMessage(PropConstants.CASHOUT_TRANSACTION_SUCCESS, null, Locale.getDefault()),
				cashOutTokensRequest.getNoOfTokens(),account.getProfile().getCountry().getIso4217CurrencyAlphabeticCode(),cashOutTokensRequest.getInstitutionAccountNumber());

		cashOutResponse = new CashOutResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, successMessage,
				accountBalance);
		
		return cashOutResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#handleTransactionHistory(com.ireslab.electraapp.model.TransactionHistoryRequest)
	 */
	@Override
	public TransactionHistoryResponse handleTransactionHistory(TransactionHistoryRequest txnHistoryRequest) {

		TransactionHistoryResponse txnHistoryResponse = null;

		String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(mobileNumber, countryDialCode);
		if (account == null) {
			// TODO: throw exception
		}

		List<UserTransactionDetails> userTransactionDetailsList = new ArrayList<>();
		
		
		
		//-- code to fatch data from electra 
		SendxElectraRequest requestTransactionDetailList =new SendxElectraRequest();
		requestTransactionDetailList.setUserCorrelationId(account.getUserCorrelationId());
		requestTransactionDetailList.setAllLedger(txnHistoryRequest.getAllLedger());
		requestTransactionDetailList.setOfflineLedger(txnHistoryRequest.getOfflineLedger());
		List<com.ireslab.sendx.electra.dto.TransactionDetailsDto> transactionDetailsDtos = transactionalApiService.getAllTransactionalDetails(requestTransactionDetailList).getTransactionDetailsDtos();
			LOG.debug("Ledger list size "+transactionDetailsDtos.size());
		
		for (com.ireslab.sendx.electra.dto.TransactionDetailsDto userTransactionDetails : transactionDetailsDtos) {
			UserTransactionDetails userTxnDetails = new UserTransactionDetails();
			userTxnDetails.setUserMobileNumber(account.getMobileNumber().longValue());
			userTxnDetails.setUserCountryDialCode(account.getCountry().getCountryDialCode());
			
			
			
			userTxnDetails.setIsSendingTransaction(userTransactionDetails.isSendingTransaction());
			
			if(userTransactionDetails.isSendingTransaction()) {
				userTxnDetails.setTransactionUserName(userTransactionDetails.getRecieverFirstName());
			}else {
				userTxnDetails.setTransactionUserName(userTransactionDetails.getSenderFirstName());
			}
			
			
			userTxnDetails.setTransactionDate(userTransactionDetails.getTransactionDate());
			
			
			userTxnDetails.setTransactionTime(CommonUtils.transactionTime(userTransactionDetails.getTxnDate()));
			
		
			
			userTxnDetails.setTxnDate(userTransactionDetails.getTxnDate());
			userTxnDetails.setTransactionMessage("message");
			userTxnDetails.setTransactionStatus(userTransactionDetails.getTransactionStatus());
			userTxnDetails.setNoOfTokens(userTransactionDetails.getNoOfTokens());
			userTxnDetails.setOffline(userTransactionDetails.getOffline());
			userTransactionDetailsList.add(userTxnDetails);
			
			
		}
		
			
			
			SendxElectraResponse allSettlementReports = transactionalApiService.getAllSettlementReports(account.getUserCorrelationId());
			
			
			if(allSettlementReports.getSettlementReportList()!=null && !allSettlementReports.getSettlementReportList().isEmpty()) {
				
				
				
				List<CashOutDto> settlementReportList = allSettlementReports.getSettlementReportList();
				
				settlementReportList.forEach((cashOutDto) -> {

					UserTransactionDetails userTxnDetails = new UserTransactionDetails();

						userTxnDetails.setTransactionUserName(cashOutDto.getInstitutionName());
						userTxnDetails.setIsCashOutTransaction(true);
						userTxnDetails.setIsSendingTransaction(true);
						userTxnDetails.setNoOfTokens(cashOutDto.getNoOfTokens());
						if(cashOutDto.getStatus().equals("NEW")) {
							userTxnDetails.setTransactionStatus((short) 0);
						}else if(cashOutDto.getStatus().equals("TRANSFERRED")) {
							userTxnDetails.setTransactionStatus((short) 1);
						}else if(cashOutDto.getStatus().equals("REJECTED")) {
							userTxnDetails.setTransactionStatus((short) 3);
						}
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						
						userTxnDetails.setTransactionDate(cashOutDto.getCreatedDate());
						try {
							userTxnDetails.setTransactionDate(CommonUtils.transactionDate(new Date(sdf.parse(cashOutDto.getCreatedDate()).getTime())));
							userTxnDetails.setTransactionTime(CommonUtils.transactionTime(new Date(sdf.parse(cashOutDto.getCreatedDate()).getTime())));
						
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						userTransactionDetailsList.add(userTxnDetails);
					
				});
				
			
			
			
			
			
			
		}
		

		txnHistoryResponse = new TransactionHistoryResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				PropConstants.SUCCESS, userTransactionDetailsList);
		

		return txnHistoryResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#handleLoadTokens(com.ireslab.electraapp.model.LoadTokensRequest)
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public LoadTokensResponse handleLoadTokens(LoadTokensRequest loadtokensRequest) {

		LoadTokensResponse loadTokensResponse = null;
		Date currentDate = new Date();

		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(
				BigInteger.valueOf(loadtokensRequest.getMobileNumber()), loadtokensRequest.getCountryDialCode());

		if (account == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.ACCOUNT_NOT_EXISTS);
		}

		loadtokensRequest.setUserCorrelationId(account.getUserCorrelationId());

		// Invoke Electra Load Tokens API
		String accountBalance = transactionalApiService.invokeLoadTokensAPI(loadtokensRequest);
		if (accountBalance == null) {

			// TODO: throw proper exception
			LOG.error("Error occurred while invoking Electra load tokens API");
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.INTERNAL_SERVER_ERROR);
		}
		if (accountBalance.equalsIgnoreCase("op_line_full")) {

			// TODO: throw proper exception
			LOG.error("Error occurred while invoking Electra load tokens API");
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.LOAD_TOKEN_FAILED,
					PropConstants.LOAD_TOKEN_FAILED);
		}

		TopupTransaction topupTransaction = new TopupTransaction();
		topupTransaction.setBeneficiaryMobileNumber(BigInteger.valueOf(loadtokensRequest.getMobileNumber()));
		topupTransaction.setBeneficiaryCountryDialCode(loadtokensRequest.getCountryDialCode());
		topupTransaction.setNoOfTokens(loadtokensRequest.getNoOfTokens());
		topupTransaction.setPaymentReferenceNumber(loadtokensRequest.getPaymentReferenceNumber());
		topupTransaction.setPaymentPlatform(loadtokensRequest.getPaymentPlatform());
		topupTransaction.setPaymentType(loadtokensRequest.getPaymentType());
		topupTransaction.setTransactionDate(currentDate);
		topupTransaction.setModifiedDate(currentDate);
		topupTransactionRepository.save(topupTransaction);

		loadTokensResponse = new LoadTokensResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				"Amount loaded successfully", accountBalance);
		return loadTokensResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#validateUserTopUp(java.math.BigInteger, java.lang.String)
	 */
	@Override
	public GenericResponse validateUserTopUp(BigInteger mobileNumber, String countryDialCode) {

		GenericResponse validateUserTopUpResponse = null;


		Account userAccount = accountRepo.findByMobileNumberAndCountry_CountryDialCode(mobileNumber, countryDialCode);
		if (userAccount != null) {
	
		}

		validateUserTopUpResponse = new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				PropConstants.SUCCESS);

		return validateUserTopUpResponse;
	}

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.TransactionService#getAllTransactionPurpose(java.lang.Long, java.lang.String)
	 */
	@Override
	public TransactionPurposeResponse getAllTransactionPurpose(Long mobileNumber, String countryDialCode) {
		TransactionPurposeResponse transactionPurposeResponse = new TransactionPurposeResponse();
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(new BigInteger(mobileNumber+""), countryDialCode);
		
		if(account != null) {
			
			if(account.getIsClient()) {
				LOG.info("Account exist as client with associate mobile no and country dial code. \n mobile no - "+mobileNumber+"\n country dail code - "+countryDialCode);
				
				
				ClientInfoRequest clientInfoRequest = new ClientInfoRequest();
				clientInfoRequest.setEmail(account.getProfile().getEmailAddress());
				
				ClientInfoResponse clientInfoResponse = transactionalApiService.clientInformation(clientInfoRequest);
				transactionPurposeResponse = transactionalApiService.getAllTransactionPurpose(clientInfoResponse.getClientCorrelationId());
				
			}
			else {
				LOG.info("Account exist as agent with associate mobile no and country dial code. \n mobile no - "+mobileNumber+"\n country dail code - "+countryDialCode);
				transactionPurposeResponse = transactionalApiService.getAllTransactionPurpose(account.getClientCorrelationId());
			}
		}
		else {
			transactionPurposeResponse.setCode(101);
			transactionPurposeResponse.setMessage("Account not exist");
			LOG.info("Account not exist with associate mobile no and country dial code. \n mobile no - "+mobileNumber+"\n country dail code - "+countryDialCode);
		}
		
		return transactionPurposeResponse;
	}
}
