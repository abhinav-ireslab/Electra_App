package com.ireslab.electraapp.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.UserProfile;

/**
 * @author Nitin
 *
 */
public interface ProfileService {

	/**
	 * @param userProfile
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	//public UserProfile editUserProfile(UserProfile userProfile);
	public UserProfile editUserProfile(UserProfile userProfile, HttpServletRequest request);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public UserProfile getUserProfile(BigInteger mobileNumber, String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public UserProfile getUserProfileByUniqueCode(String uniqueCode);

	/**
	 * @param userProfile
	 * @return
	 */
	public GenericResponse updatePasswordOrMpin(UserProfile userProfile);

}
