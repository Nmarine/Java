package it.polito.meet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Slot {
	private LocalTime start; 
	private LocalTime end;
	private String date;
	
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

public Slot(String start, String end, String date) {
		this.start = LocalTime.parse(start, TIME_FORMATTER); 
		this.end = LocalTime.parse(end,TIME_FORMATTER);
		this.date = date;
	}
public boolean overlaps (Slot other) {	
	return this.start.isBefore(other.end) || this.end.isAfter(other.start);

}
	public double findDuration() {
		return (double)ChronoUnit.HOURS.between(start, end);
	}/*This part calculates the difference between start and end in terms of minutes.
	 It uses the until method of the LocalTime class,which returns the amount of time between 
	 two LocalTime instances. The second argument,java.time.temporal.ChronoUnit.MINUTES, specifies 
	  that the difference should be measured in minutes.*/

}
   