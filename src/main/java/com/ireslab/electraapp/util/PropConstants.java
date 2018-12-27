package com.ireslab.electraapp.util;

/**
 * @author Nitin
 *
 */
public interface PropConstants {

	public static final String SUCCESS = "success";
	public static final String MOBILE_ALREADY_REGISTERED = "mobile.already.registered";
	public static final String MOBILE_AVAILABLE = "mobile.available";
	public static final String INVALID_REQUEST = "invalid.request";
	public static final String INTERNAL_SERVER_ERROR = "internal.server.error";
	public static final String INVALID_ACTIVATION_CODE = "invalid.activation.code";
	public static final String ACTIVATION_CODE_REQUEST_LIMIT_REACHED = "activation.code.request.limit.reached";
	public static final String ACTIVATION_CODE_SENT = "activation.code.sent";

	public static final String PAYMENT_TRANSACTION_SUCCESS = "tokens.transfer.success";
	public static final String PAYMENT_TRANSACTION_FAILURE = "tokens.transfer.failure";
	public static final String PAYMENT_TRANSACTION_NOT_ALLOWED = "tokens.transfer.not.allowed";

	public static final String PAYMENT_TRANSACTION_REQUEST_SCHEDULED = "tokens.transfer.request.scheduled";

	public static final String CASHOUT_TRANSACTION_SUCCESS = "tokens.cashout.success";

	public static final String PASSWORD_UPDATE_SUCCESS = "password.update.success";

	public static final String MPIN_UPDATE_SUCCESS = "mpin.update.success";

	public static final String MPIN_SETUP_REQUIRED = "mpin.setup.required";

	public static final String ACCOUNT_NOT_EXISTS = "account.not.exists";

	public static final String ACCOUNT_EXISTS = "account.exists";
	
	public static final String COMPANY_CODE_STATUS = "company.code.status";

	public static final String INSUFFICIENT_ACCOUNT_BALANCE = "insufficient.account.balance";

	public static final String ACCOUNT_SUSPENDED = "user.account.suspended";

	public static final String ACCOUNT_TERMINATED = "user.account.terminated";
	
	public static final String USER_SUSPENDED = "user.suspended";
	
	public static final String LOAD_TOKEN_FAILED = "load.token.failed";
	
	

	// public static final String AGENT_SUCCESS = "account.exists";

	/* Commented - Only applicable in Livenet */
	// public static final String ACCOUNT_BALANCE_LIMIT_SURPASSES =
	// "beneficiary.account.balance.limit.surpasses";
	// public static final String ACCOUNT_BALANCE_LIMIT_REACHED =
	// "beneficiary.account.balance.limit.reached";
	//
	// public static final String TOPUP_DAYLIMIT_REACHED =
	// "topup.daylimit.reached";
	// public static final String TOPUP_FAILED_ACCOUNT_BALANCE_LIMIT_REACHED =
	// "topup.failed.account.balance.reached";
}
