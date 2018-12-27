package com.ireslab.electraapp.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.electraapp.entity.AccountVerification;

/**
 * @author Nitin
 *
 */
public interface AccountVerificationRepository extends CrudRepository<AccountVerification, Integer> {

	/**
	 * @param mobileNumber
	 * @param countryId
	 * @return
	 */
	public AccountVerification findByMobileNumberAndCountryId(BigInteger mobileNumber, Integer countryId);

}
