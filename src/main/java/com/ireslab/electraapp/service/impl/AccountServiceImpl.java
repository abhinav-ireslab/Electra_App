package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.service.AccountService;

/**
 * @author iRESlab
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	
	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.AccountService#getAccount(java.math.BigInteger, java.lang.String)
	 */
	@Override
	public Account getAccount(BigInteger mobileNumber, String countryDialCode) {
		return accountRepository.findByMobileNumberAndCountry_CountryDialCode(mobileNumber, countryDialCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.sendx.service.AccountService#getAccountByUniqueCode(java.lang
	 * .String)
	 */
	@Override
	public Account getAccountByUniqueCode(String uniqueCode) {
		return accountRepository.findByUniqueCode(uniqueCode);
	}

}
