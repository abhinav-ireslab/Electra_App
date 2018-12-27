package com.ireslab.electraapp.util;

/**
 * @author Nitin
 *
 */
public interface AppStatusCodes {

	public static final Integer SUCCESS = 100;
	public static final Integer INTERNAL_SERVER_ERROR = 101;

	public static final Integer MOBILE_ALREADY_REGISTERED = 201;

	public static final Integer ACCOUNT_NOT_EXISTS = 202;

	public static final Integer ACCOUNT_SUSPENDED = 203;
	
	public static final Integer USER_SUSPENDED = 205;

	public static final Integer ACCOUNT_TERMINATED = 204;

	public static final Integer INVALID_REQUEST = 302;

	public static final Integer INVALID_ACTIVATION_CODE = 401;

	public static final Integer ACCOUNT_BALANCE_LIMIT_REACHED = 501;

	public static final Integer ACCOUNT_TOPUP_LIMIT_REACHED = 502;

	public static final Integer STELLAR_ACCOUNT_CREATION_FAILED = 801;

	public static final Integer STELLAR_TRUSTLINE_CREATION_FAILED = 802;

	public static final Integer STELLAR_ACCOUNT_BALANCE_OPERATION_FAILED = 803;

	public static final Integer STELLAR_PAYMENT_OPERATION_FAILED = 804;

	public static final Integer STELLAR_ACCOUNT_INSUFFICIENT_BALANCE = 805;
	
	public static final Integer MONTHLY_TRANSACTION_LIMIT_REACHED = 702;
	
	public static final Integer DAILY_TRANSACTION_LIMIT_REACHED = 701;
	
	public static final Integer LOAD_TOKEN_FAILED = 4000;
}
