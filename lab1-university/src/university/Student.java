package university;
import java.util.ArrayList;

public class Student {
	private String FirstName;
	private String LastName;
	private int ID;
	private ArrayList<Course> AttendingCourses= new ArrayList<>(); 
	private float Avg;
	private ArrayList<Integer> registeredGrades= new ArrayList<>();
	private float Score;
	
	public float getScore() {
	Score= Avg+(registeredGrades.size()/AttendingCourses.size())*10;	
	return Score;
	}

	public Student(String firstName, String lastName, int ID) {
		FirstName = firstName;
		LastName = lastName;
		this.ID=ID;
	} 

	public int getID() {
		return ID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}
	public void addAttendingCourse(Course course) {
		AttendingCourses.add(course);
	}

	public ArrayList<Course> getAttendingCourses() {
		return AttendingCourses;
	}

	public boolean isAttendingCourse(Course course) {
		for(Course c: AttendingCourses) {
			if(c==course) return true;
		}
		return false; 
	}

	public void registerGrade(int grade) {
		// TODO Auto-generated method stub
		registeredGrades.add(grade);
	}

	public float getAvg() {
		int totalgrade=0;
		int count=0;
		for(int i=0; i<registeredGrades.size(); i++) {
			totalgrade +=registeredGrades.get(i);
			count+=1;
		}
		float Avg=totalgrade/count;
		return Avg;
	}
	
}
