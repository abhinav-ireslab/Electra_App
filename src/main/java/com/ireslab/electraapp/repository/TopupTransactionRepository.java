package com.ireslab.electraapp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ireslab.electraapp.entity.TopupTransaction;

/**
 * @author Nitin
 *
 */
public interface TopupTransactionRepository extends CrudRepository<TopupTransaction, Integer> {

	/**
	 * @param beneficiaryMobileNumber
	 * @param beneficiaryCountryDialCode
	 * @return
	 */
	public List<TopupTransaction> findByBeneficiaryMobileNumberAndBeneficiaryCountryDialCode(
			BigInteger beneficiaryMobileNumber, String beneficiaryCountryDialCode);

	@Query("SELECT t FROM TopupTransaction t WHERE t.beneficiaryMobileNumber=:beneficiaryMobileNumber AND t.beneficiaryCountryDialCode=:beneficiaryCountryDialCode AND t.transactionDate >= CURDATE()")
	public List<TopupTransaction> findByBeneficiaryMobileNumberAndBeneficiaryCountryDialCodeByDay(
			@Param("beneficiaryMobileNumber") BigInteger beneficiaryMobileNumber,
			@Param("beneficiaryCountryDialCode") String beneficiaryCountryDialCode);

}
