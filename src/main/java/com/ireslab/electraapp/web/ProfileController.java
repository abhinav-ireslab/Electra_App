package com.ireslab.electraapp.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.GenericResponse;
import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.service.ProfileService;
import com.ireslab.electraapp.springsecurity.SpringSecurityUtil;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;

/**
 * @author iRESlab
 *
 */
@RestController
public class ProfileController {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileService profileService;

	

	
	/**
	 * use to update profile of users.
	 * 
	 * @param userProfile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile updateProfile(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		LOG.info("Request received for profile updation - " + userProfile.toString());

		// Getting username details from Spring Security Context
		String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		userProfile.setMobileNumber(mobileNumber);
		userProfile.setCountryDialCode(countryDialCode);

		return profileService.editUserProfile(userProfile, request);
	}

	
	/**
	 *  use to get profile of users.
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getProfile() throws JsonProcessingException {

		// Getting username details from Spring Security Context
		String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		UserProfile userProfile = null;
		LOG.info("Get user profile request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		if (mobileNumber == null || countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		userProfile = profileService.getUserProfile(mobileNumber, countryDialCode);
		LOG.info("Get user profile response sent ");

		return userProfile;
	}

	
	/**
	 *  use to get profile by unique code.
	 * 
	 * @param uniqueCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getProfile/{uniqueCode}", method = RequestMethod.GET)
	public UserProfile getProfileByUniqueCode(@PathVariable("uniqueCode") String uniqueCode)
			throws JsonProcessingException {

		UserProfile userProfile = null;
		LOG.info("Get user profile request received for unique code - " + uniqueCode);

		userProfile = profileService.getUserProfileByUniqueCode(uniqueCode);
		LOG.info("Get user profile response sent " );
		return userProfile;
	}

	
	/**
	 * use to update password of users.
	 * 
	 * @param userProfile
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updatePassword(@RequestBody UserProfile userProfile) throws JsonProcessingException {

		GenericResponse genericResponse = null;
		String mobileNumber = userProfile.getCountryDialCode() + userProfile.getMobileNumber();
		LOG.info("Password update request received for Mobile Number - " + mobileNumber);

		genericResponse = profileService.updatePasswordOrMpin(userProfile);
		LOG.info("Password successfully updated for Mobile Number - " + mobileNumber);
		return genericResponse;
	}

	
	/**
	 * use to update mpin to login.
	 * 
	 * @param userProfile
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/updateMpin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updateMpin(@RequestBody UserProfile userProfile) throws JsonProcessingException {

		GenericResponse genericResponse = null;
		String mobileNumber = userProfile.getCountryDialCode() + userProfile.getMobileNumber();
		LOG.debug("mPIN update request received for Mobile Number - " + mobileNumber);

		genericResponse = profileService.updatePasswordOrMpin(userProfile);
		LOG.debug("mPIN successfully updated for Mobile Number - " + mobileNumber);
		return genericResponse;
	}

	// @GetMapping(value = "/getProfileImage", produces =
	// MediaType.IMAGE_JPEG_VALUE)
	// public @ResponseBody byte[] getImageWithMediaType(HttpServletRequest request,
	// @RequestParam("imageName") String imageName) throws IOException {
	// LOG.debug("Request recived to fetching image[" + imageName + "]");
	// return profileImageService.getImageDataAsInputStream(imageName);
	// }
}
