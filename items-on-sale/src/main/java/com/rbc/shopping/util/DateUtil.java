package com.rbc.shopping.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Anmoldeep Singh Kang
 * This class has utility methods for parsing the date, which is needed in caching.
 *
 */
public class DateUtil {
	
	public static Date getDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			System.out.println("Problem in parsing Date");
		}
		return date;
	}
	
	public static Date getCurrentDate() {
		return getDate(new Date());
	}
}
