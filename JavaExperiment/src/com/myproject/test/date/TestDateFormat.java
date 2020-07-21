/**
 * 
 */
package com.myproject.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class TestDateFormat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// testDate();

		Date pDate = new Date();

		Calendar tCal = Calendar.getInstance();
		tCal.add(Calendar.MINUTE, -840);
		Date tPrevExpiryDate = tCal.getTime();

		// System.out.println("Formatted Date: " + tPrevExpiryDate);

		StringBuilder logMessage = new StringBuilder();
		logMessage.append("{ ");

			logMessage.append("111");
			logMessage.append(":[");

				logMessage.append("{");
				logMessage.append("222");
				logMessage.append(":");
				logMessage.append(true);
				logMessage.append("},");

			logMessage.append("]");
			
		logMessage.append(" }");

		System.out.println("Log message: " + logMessage);
	}

	public static void testDate() {
		Date pDate = new Date();// "2014-06-03T20:09:47+1000";
		// yyyy-MM-dd'T'HH:mm:ssXXX to get the format
		// "2014-06-03T20:09:47+10:00"
		// yyyy-MM-dd'T'HH:mm:ssZ to get the format "2014-06-03T20:09:47+1000"
		String tDate = "23.12.2014";

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String formattedDateStr = dateFormat.format(pDate);

		try {
			Date test = dateFormat.parse(tDate);

			System.out.println("Formatted Date: " + test);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> tDateList = new ArrayList<>();
		tDateList.add("22.12.2014");
		tDateList.add("23.12.2014");
		tDateList.add("23.11.2014");

		Collections.sort(tDateList);

		for (String tSortedDate : tDateList) {
			System.out.println("Formatted Date: " + tSortedDate);
		}
		// System.out.println("Formatted Date: "+formattedDateStr);
	}

}
