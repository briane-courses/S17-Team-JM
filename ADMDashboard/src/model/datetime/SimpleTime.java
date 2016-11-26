package model.datetime;

public class SimpleTime {
	private int hourOverflow;
	private int hour;
	private int minute;
	private int second;
	public SimpleTime(int hour,int minute){
		setHour(hour);
		setMinute(minute);
		setSecond(0);
		setHourOverflow(0);
	}
	public SimpleTime(int hour,int minute,int second){
		setHour(hour);
		setMinute(minute);
		setSecond(second);
		setHourOverflow(0);
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		if(hour > 24)
			setHourOverflow(getHourOverflow() + hour - 24); 
		this.hour %= 24;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		if(minute > 60)
			setHour(getHour() + 1);
		this.minute %= 60;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		if(second > 60)
			setMinute(getMinute() + 1);
		this.second %= 60;
	}
	public int getHourOverflow() {
		return hourOverflow;
	}
	public void setHourOverflow(int hourOverflow) {
		this.hourOverflow = hourOverflow;
	}
	public String toString(){
		return hour + ":" + minute + ":" + second;
	}
	
	public boolean equals(int hour, int minute, int second){
		return hour == getHour() && minute == getMinute() && second == getSecond();
		
	}
	public boolean equals(SimpleTime time){
		return hour == time.getHour() && minute == time.getMinute() && second == time.getSecond();
	}
}
