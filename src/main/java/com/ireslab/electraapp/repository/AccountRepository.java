package com.ireslab.electraapp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ireslab.electraapp.entity.Account;

/**
 * @author Nitin
 *
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public Account findByMobileNumberAndCountry_CountryDialCode(BigInteger mobileNumber, String countryDialCode);

	/**
	 * @param uniqueCode
	 * @return
	 */
	public Account findByUniqueCode(String uniqueCode);

	@Query("SELECT CONCAT(p.firstName,' ',p.lastName) from Account a inner join a.country as c inner join a.profile as p "
			+ "where CONCAT(c.countryDialCode,a.mobileNumber)=:mobNumber ")
	public String findNameByMobileNumberWithCountryCode(@Param("mobNumber") String mobNumber);

	public Account findBymobileNumber(BigInteger mobileNumber);
	
	public Account findByUserCorrelationId(String userCorrelationId);
	
	
}
