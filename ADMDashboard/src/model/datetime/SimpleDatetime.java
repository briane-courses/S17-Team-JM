package model.datetime;

public class SimpleDatetime {

	private SimpleDate simpleDate;
	private SimpleTime simpleTime;
	
	public SimpleDatetime(SimpleDate date, SimpleTime time){
		setDate(date);
		setTime(time);
	}
	
	public SimpleDatetime(int year,int month,int day, int hour,int minute){
		setDate(new SimpleDate(year, month, day));
		setTime(new SimpleTime(hour, minute));
	}
	
	public SimpleDatetime(int year,int month,int day, int hour,int minute,int second){
		setDate(new SimpleDate(year, month, day));
		setTime(new SimpleTime(hour, minute, second));
	}
	
	public SimpleDate getDate() {
		return simpleDate;
	}
	public void setDate(SimpleDate simpleDate) {
		this.simpleDate = simpleDate;
	}
	public SimpleTime getTime() {
		return simpleTime;
	}
	public void setTime(SimpleTime simpleTime) {
		this.simpleTime = simpleTime;
	}
	public boolean equals(SimpleDatetime datetime){
		return simpleDate.equals(datetime.getDate()) && simpleTime.equals(datetime.getTime());
	}
	
}
