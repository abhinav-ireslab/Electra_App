package com.ireslab.electraapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.electraapp.entity.Country;

/**
 * @author Nitin
 *
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {
	
	public Country findCountryByCountryDialCode(String countryDialCode);

}
