package utils.converter;

import java.sql.Date;
import java.util.Calendar;

import model.Status;

public class DatatypeConverter {

	private DatatypeConverter() {
	}

	public static String toString(Date date) {
		return date.toString();
	}

	public static Calendar toCalendar(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}

	public static Status toStatus(String value) {
		return Status.getStatus(value);
	}

}
