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

	public static String dateToStringHoraMinuto(Date fecha, String formato, Integer hora, Integer minuto) {
		SimpleDateFormat df = new SimpleDateFormat(formato);

		String fechaString = df.format(fecha);
		
		String horaS = hora.toString();
		String minutoS = minuto.toString();
		
		if(horaS.length() < 2) horaS = "0" + horaS;
		if(minutoS.length() < 2) minutoS = "0" + minutoS;

		return fechaString +" " +horaS +":" +minutoS ;
	}
	
	public static Boolean isFechaAnteriorAFechaHoy(Date fecha, Date fechaHoy) {
		return fecha.before(fechaHoy);
	}

}
