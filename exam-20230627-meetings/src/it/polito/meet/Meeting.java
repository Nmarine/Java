package it.polito.meet;

import java.util.ArrayList;
import java.util.List;

public class Meeting {
	private String title;
	private String topic;
	private String category;
	private String ID;
	private List<Slot> meetingSlots;
	
	
public Meeting(String ID, String title, String topic, String category) {
	this.category=category;
	this.ID=ID;
	this.title=title;
	this.topic=topic; 
	meetingSlots= new ArrayList<>();
}

public String getCategory() {
	return category;
}


public String getID() {
	return ID; 
}


public String getTitle() {
	return title; 
}  
 

public String getTopic() {
	return topic;
}

public List<Slot> getMeetingSlots() {
	return meetingSlots;
}

//let's add a new slot for a meeting and checking for overlaps
public double addslot(String date, String start,String end) throws MeetException{
	Slot newslot= new Slot(start,end, date);
	for(Slot s:meetingSlots) {
		if(s.overlaps(newslot)) {
			return 0;
		}}
	meetingSlots.add(newslot);
	return newslot.findDuration();
	}
}

