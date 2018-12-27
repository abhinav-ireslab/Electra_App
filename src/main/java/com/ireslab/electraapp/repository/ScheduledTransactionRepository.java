package com.ireslab.electraapp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.electraapp.entity.ScheduledTransaction;

/**
 * @author Nitin
 *
 */
public interface ScheduledTransactionRepository extends CrudRepository<ScheduledTransaction, Integer> {

	/**
	 * @param countryDialCode
	 * @param BeneficiaryMobileNumber
	 * @return
	 */
	public List<ScheduledTransaction> findByBeneficiaryMobileNumberAndBeneficiaryCountry_CountryDialCode(
			BigInteger mobileNumber, String countryDialCode);

}
