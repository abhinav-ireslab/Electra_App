package com.ireslab.electraapp.service;

import java.math.BigInteger;

import com.ireslab.electraapp.entity.Account;

/**
 * @author Nitin
 *
 */
public interface AccountService {

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public Account getAccount(BigInteger mobileNumber, String countryDialCode);

	/**
	 * @param uniqueCode
	 * @return
	 */
	public Account getAccountByUniqueCode(String uniqueCode);
}
