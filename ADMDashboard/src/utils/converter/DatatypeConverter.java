package utils.converter;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.Status;

public class DatatypeConverter {

	private DatatypeConverter(){}
	
	public static String toHex(Color color){
		return "#"+Integer.toHexString(color.getRed()) 
				+ Integer.toHexString(color.getGreen()) 
				+ Integer.toHexString(color.getBlue());
	}
	
	public static String toHex(int r, int g, int b){
		return "#"+Integer.toHexString(r) 
				+ Integer.toHexString(g) 
				+ Integer.toHexString(b);
	}
	
	public static String toString(Calendar calendar, String format){ // BUGGY DO NOT USE
		
		//calendar.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat(format);
		//System.out.println(cal.getTime());
		// Output "Wed Sep 26 14:23:28 EST 2012"

		String formatted = format1.format(calendar.getTime());
		//System.out.println(formatted);
		// Output "2012-09-26"

		//System.out.println(format1.parse(formatted));
		// Output "Wed Sep 26 00:00:00 EST 2012"
		return formatted;
	}
	
	public static String toString(Date date){
		return date.toString();
	}
	
	public static Calendar toCalendar(Date date){
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}
	
	public static Status toStatus(String value){
		return Status.getStatus(value);
	}
	
}
