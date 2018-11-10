package ar.uba.fi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
