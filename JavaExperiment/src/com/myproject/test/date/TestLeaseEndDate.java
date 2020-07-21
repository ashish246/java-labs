package com.myproject.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestLeaseEndDate {

	public static void main(String[] args) {

		GregorianCalendar currentCal = new GregorianCalendar();

		int currentYear = currentCal.get(Calendar.YEAR);
		int month = currentCal.get(Calendar.MONTH);
		GregorianCalendar newCalendar = new GregorianCalendar();
		
		Date todaysDate = new Date();
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date futureDate = null;
		try {
			futureDate = sdf.parse("2015-03-15");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Approach I
		if (month > Calendar.MARCH) {
			newCalendar.add(Calendar.YEAR, 1);
			newCalendar.set(Calendar.MONTH, 2);
			newCalendar.set(Calendar.DAY_OF_MONTH,
					newCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

			Date lastDayOfMonth = newCalendar.getTime();
			long daysRemainingI = (lastDayOfMonth.getTime() - todaysDate
					.getTime());
			System.out.println("Total no of days left in this financial year: "
					+ daysRemainingI / (24 * 60 * 60 * 1000));
		} else {
			newCalendar.set(Calendar.MONTH, 2);
			newCalendar.set(Calendar.DAY_OF_MONTH,
					newCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));

			Date lastDayOfMonth = newCalendar.getTime();
			long daysRemainingI = (lastDayOfMonth.getTime() - todaysDate
					.getTime());
			System.out.println("Total no of days left in this financial year: "
					+ daysRemainingI / (24 * 60 * 60 * 1000));
		}

		// Approach II
		if (month > Calendar.MARCH) {
			Calendar cal = new GregorianCalendar(currentYear + 1, 3, 0);
			Date date = cal.getTime();

			long daysRemainingII = (date.getTime() - futureDate.getTime());
			System.out.println("Total no of days left in this financial year: "
					+ daysRemainingII / (24 * 60 * 60 * 1000));
		} else {
			Calendar cal = new GregorianCalendar(currentYear, 3, 0);
			Date date = cal.getTime();

			long daysRemainingII = (date.getTime() - futureDate.getTime());
			System.out.println("Total no of days left in this financial year: "
					+ daysRemainingII / (24 * 60 * 60 * 1000));

		}

	}

}
