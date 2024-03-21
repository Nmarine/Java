package university;

import java.util.ArrayList;

public class Course {
	 	private String CourseName;
	 	private String Teacher;
	 	private int CourseID;
	  private ArrayList<Student> Attendees = new ArrayList<>();
	  private float coursegrades=0;
	  private int count=0;
	  private float Avg;
	  
		
public Course(String courseName, String teacher, int iD) {
			CourseName = courseName;
			Teacher = teacher;
			CourseID = iD;
		}

public String getCourseName() {
	return CourseName;
}

public String getTeacher() {
	return Teacher; 
}

public int getCourseID() {
	return CourseID;
}

public void addAttendee(Student attendee ) {
	Attendees.add(attendee);
}

public ArrayList<Student> getAttendees() {
	return Attendees;
}

public void registerGrade(int grade) {
	// TODO Auto-generated method stub
	count= count+1;
	coursegrades+=grade;
	Avg=coursegrades/count;
}

public float getAvg() {
	return Avg;
}

}
