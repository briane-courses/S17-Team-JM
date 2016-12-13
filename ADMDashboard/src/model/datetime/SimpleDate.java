package model.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SimpleDate {
	private int year;
	private int month;
	private int day;
	public SimpleDate(int year,int month,int day){
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
	}
	public SimpleDate(String date){
		
		String[] arrDate = date.length() >= 10 ? date.substring(0,10).split("-") : date.split("-");
		year = Integer.parseInt(arrDate[0]); 
		month = Integer.parseInt(arrDate[1]);
		day = Integer.parseInt(arrDate[2]);
	}
	
	public int getYear() {
		return year;
	}
	public void incYear(int amt){
		setYear(year + amt);
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void incMonth(int amt){
		setMonth(month + amt);
	}
	public void setMonth(int month) {
		month = normalize(month);
		if(month > 12 + 1)
			setYear(getYear() + (month/(12+1)));
		this.month = month % (12 + 1);
	}
	public void incDay(int amt){
		setDay(day + amt); 
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		day = normalize(day);
		int totalDays = new GregorianCalendar(getYear(), getMonth(), 1).getActualMaximum(GregorianCalendar.DAY_OF_MONTH) + 1;
		if(day > totalDays)
			setMonth(getMonth() + (day / totalDays));
		this.day = day % totalDays;
	}
	
	public Calendar toCalendar() {
	    Calendar date = Calendar.getInstance();
	    date.set(Calendar.YEAR, year);
	    date.set(Calendar.MONTH, month);
	    date.set(Calendar.DAY_OF_MONTH, day);

	    return date;
	}
	
	public Date toDate(){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	@Override
	public String toString(){
		return year + "-" + (month < 10 ? "0"+month : month) + "-" + (day < 10 ? "0"+day : day);
	}
	public static String toString(int year, int month, int day){
		return year + "-" + (month < 10 ? "0"+month : month) + "-" + (day < 10 ? "0"+day : day);
	}
	public boolean equals(int year, int month, int day){
		return getYear() == year && getMonth() == month && getDay() == day;
	}
	public boolean equals(SimpleDate date){
		return date.getYear() == year && date.getMonth() == month && date.getDay() == day;
	}
	
	private int normalize(int value){
		return value == 0? 1 : Math.abs(value); 
	}
}
