package ar.uba.fi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date stringToDate(String fecha, String formato) {
		Date date = null;
		try {
			date = new SimpleDateFormat(formato).parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;

	}

	public static String addNMinutesToTime(Calendar date, String minutes) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				int minutesToAdd = Integer.valueOf(minutes);
				System.out.println("Initial Time: " + df.format(date.getTime()));
				Calendar startTime = date;
				startTime.add(date.MINUTE, minutesToAdd);
				String dateStr = df.format(startTime.getTime());
				System.out.println("After Time : " + dateStr + "\n");
				return dateStr;
			}

}
