package com.ireslab.electraapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Nitin
 *
 */
public class CommonUtils {

	private static final String TRANSACTION_DATE_FORMAT = "MMM dd, yyyy";
	//private static final String TRANSACTION_TIME_FORMAT = "HH:mm a";
	private static final String TRANSACTION_TIME_FORMAT = "HH:mm:ss";

	private static final SimpleDateFormat transactionDateFormat = new SimpleDateFormat(TRANSACTION_DATE_FORMAT);
	private static final SimpleDateFormat transactionTimeFormat = new SimpleDateFormat(TRANSACTION_TIME_FORMAT);

	private static final String TRANSACTION_TIME_TIMEZONE = "GMT+8";
	private static final TimeZone gmt8TimeZone = TimeZone.getTimeZone(TRANSACTION_TIME_TIMEZONE);
	private static final SimpleDateFormat transactionTimeZoneFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
	

	public static String transactionDate(Date date) {

	//	transactionDateFormat.setTimeZone(gmt8TimeZone);
		return transactionDateFormat.format(date);
	}

	public static String transactionTime(Date date) {

	//transactionTimeFormat.setTimeZone(gmt8TimeZone);
		return transactionTimeFormat.format(date);
	}

	/**
	 * @return
	 */
	public static Integer generateUniqueCode(int size) {
		Integer uniqueId = 0;

		uniqueId = (int) (new Random().nextDouble() * Long.parseLong(StringUtils.leftPad("1", size, "0")));

		while (uniqueId.toString().length() < size) {
			uniqueId = Integer.parseInt(String.valueOf(uniqueId) + new Random().nextInt(9));
		}

		return uniqueId;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long calculateTimeDiffInMin(Date startDate, Date endDate) {

		long duration = endDate.getTime() - startDate.getTime();
		//long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		//System.out.println("Time Duration : : : : : ----"+duration);

		return duration;
	}

	/**
	 * @param dateString
	 * @return
	 */
	public static Calendar getCalendarFromString(String dateString) {

		Calendar dateCalendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		try {
			dateCalendar.setTime(sdf.parse(dateString));
		} catch (ParseException pExp) {
			return null;
		}
		return dateCalendar;
	}

	/**
	 * @param timezone
	 * @param date
	 * @return
	 */
	public static String convertDateToSpecificTimeZone(String timezone, Date date) {

		TimeZone gmt8Time = gmt8TimeZone;

		if (date == null) {
			date = new Date();
		}

		if (timezone == null) {
			gmt8Time = TimeZone.getTimeZone(TRANSACTION_TIME_TIMEZONE);
		}

		transactionTimeZoneFormat.setTimeZone(gmt8Time);

		return transactionTimeZoneFormat.format(date);
	}
	
	
	public static String formatDate(Date date, String format) {
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);  
		String     strDate = formatter.format(date);  
        return strDate;
	}
	
	
	
	public static String setDateParsing(String date) throws ParseException {

	    // This is the format date we want
	    DateFormat mSDF = new SimpleDateFormat("hh:mm a");

	    // This format date is actually present
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	    return mSDF.format(formatter.parse(date));
	}
	
	

	public static void main(String[] args) {

		/*
		 * System.out.println(transactionDate(new Date()));
		 * 
		 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		 * "dd-MM-yyyy HH:mm:ss a"); try { System.out.println( new
		 * SimpleDateFormat("MMM dd, yyyy").format(simpleDateFormat.parse(
		 * "07-09-2017 20:10:47 PM"))); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*System.out.println(generateUniqueCode(7));

		Date date = new Date();
		System.out.println("ss"+date.getTime());
		System.out.println("Current Time: " + date);
		System.out.println("GMT Time: " + convertDateToSpecificTimeZone(null, date));
        System.out.println("Transaction Date - " + transactionDate(date));
		System.out.println("Transaction Time - " + transactionTime(date));*/
		
		System.out.println(formatDate(new Date(), "dd/MM/yyyy"));
		
		
	}
}
