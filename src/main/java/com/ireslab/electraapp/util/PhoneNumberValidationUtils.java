package com.ireslab.electraapp.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * @author Nitin
 *
 */
public class PhoneNumberValidationUtils {

	private final static Logger LOGGER = Logger.getLogger(PhoneNumberValidationUtils.class);

	private static final String DEFAULT_REGION = "SG";
	private static final String COUNTRY_CODE_PREFIX = "+";

	/**
	 * @param numberToParse
	 * @param countryCallingCode
	 * @return
	 */
	public static boolean mobileNumberValidation(String numberToParse, int countryCallingCode) {
		LOGGER.debug("Verifying number- " + numberToParse + "with country code- " + countryCallingCode);
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		boolean isValid = false;
		try {
			PhoneNumber phoneNumber = phoneUtil.parse(numberToParse,
					phoneUtil.getRegionCodeForCountryCode(countryCallingCode));
			isValid = phoneUtil.isValidNumber(phoneNumber);
		} catch (Exception exception) {
			LOGGER.error("Error description" + ExceptionUtils.getStackTrace(exception));
		}
		return isValid;
	}

	/**
	 * @param numberToParse
	 * @param countryCallingCode
	 * @return
	 */
	public static String parseInternationalValidNumber(String numberToParse, String countryCallingCode) {
		LOGGER.debug("Verifying number- " + numberToParse + " with country code- " + countryCallingCode);
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		boolean isValid = false;
		PhoneNumber phoneNumber = null;
		try {
			phoneNumber = phoneUtil.parse(numberToParse,
					phoneUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCallingCode)));
			isValid = phoneUtil.isValidNumber(phoneNumber);
		} catch (Exception exception) {
			LOGGER.error("Error description" + ExceptionUtils.getStackTrace(exception));
		}
		return isValid ? String.valueOf(phoneNumber.getCountryCode()) + phoneNumber.getNationalNumber() : null;
	}

	/**
	 * @param numberToParse
	 * @param countryCallingCode
	 * @return
	 */
	public static String parseInternationalValidNumberWithoutCountryCode(String numberToParse,
			String countryCallingCode) {
		LOGGER.debug("Verifying number- " + numberToParse + " with country code- " + countryCallingCode);
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		boolean isValid = false;
		PhoneNumber phoneNumber = null;
		try {
			phoneNumber = phoneUtil.parse(numberToParse,
					phoneUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCallingCode)));
			isValid = phoneUtil.isValidNumber(phoneNumber);
		} catch (Exception exception) {
			LOGGER.error("Error description" + ExceptionUtils.getStackTrace(exception));
		}
		return isValid ? String.valueOf(phoneNumber.getNationalNumber()) : null;
	}

	/**
	 * @param phNumber
	 * @param countryAbbv
	 * @return
	 */
	public static String[] validateMobNoWithCountryAbbv(String phNumber, String region) {

		boolean isNumberValid = false;
		String[] mobileNumber = null;

		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumber = phoneUtil.parse(phNumber, region);

			isNumberValid = phoneUtil.isValidNumber(phoneNumber);

			if (!isNumberValid) {
				phNumber = COUNTRY_CODE_PREFIX + phNumber;
				phoneNumber = phoneUtil.parse(phNumber, region);
				isNumberValid = phoneUtil.isValidNumber(phoneNumber);
			}

			if (isNumberValid) {
				mobileNumber = new String[2];
				mobileNumber[0] = COUNTRY_CODE_PREFIX + phoneNumber.getCountryCode();
				mobileNumber[1] = String.valueOf(phoneNumber.getNationalNumber());
				return mobileNumber;
			}
		} catch (Exception exception) {
			// LOGGER.error("mobileNumber = " + mobileNumber + ", Error
			// description" + exception.getMessage());
			return mobileNumber;
		}
		return mobileNumber;
	}

	public static void main(String[] args) {

		String region = PhoneNumberUtil.getInstance()
				.getRegionCodeForCountryCode(Integer.parseInt("+91".replaceAll("[+]", "")));
		System.out.println("Region - " + region);

		/*String arr[] = validateMobNoWithCountryAbbv("92353702",
				PhoneNumberUtil.getInstance().getRegionCodeForCountryCode(91));
		System.out.println(arr[0] + " - " + arr[1]);*/
		
		String arr[] = validateMobNoWithCountryAbbv("9015293893",
				PhoneNumberUtil.getInstance().getRegionCodeForCountryCode(91));
		System.out.println(arr[0] + " - " + arr[1]);
	}
}
