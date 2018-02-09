package sistemaGestao;

public class Date {
	private int month;
	private int day;
	private int year;
	private int hour;
	private int minute;
	
	
	private static final int[] daysPerMoth = {0, 31, 28, 31 ,30 ,31, 31,30,31,30,31};
	
	public Date(int day, int month, int year, int hour, int minute) {
		if(month <= 0 || month > 12)
			System.out.println("Month (" + month + ") must be 1-12");
		
		if(day <= 0 || (day > daysPerMoth[month] && !(month == 2 && day == 29)))
			System.out.println("Out-of-range for the specified month and year");
		
		if(month == 2 && day == 29 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))
			System.out.println("Out-of-range for the specified month and year");
		
		if(hour > 23 || hour < 0)
			System.out.println("Hour must be 0-23");
		
		if(minute >= 60 || minute < 0)
			System.out.println("Out-of-range for the specified minute");
		
		this.month = month;
		this.day = day;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}
	
	public String toString(){
		return String.format("%d/%d/%d %02d:%02d",day,month,year,hour,minute);
	}
}
