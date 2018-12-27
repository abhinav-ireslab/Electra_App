package com.ireslab.electraapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.electraapp.entity.CheckoutBankDetail;

/**
 * @author Nitin
 *
 */
public interface CheckoutBankDetailsRepository extends CrudRepository<CheckoutBankDetail, Integer> {

	/**
	 * @param countryDialCode
	 * @return
	 */
	public List<CheckoutBankDetail> findByCountry_CountryDialCode(String countryDialCode);

}
