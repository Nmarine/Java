package university;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	private String uniName;
    private String rectorName;
	private static final int INITIAL_STUDENT_ID = 10000;
	private static final int INITIAL_COURSE_ID = 10;
	private int currentStudentID = INITIAL_STUDENT_ID;
	private int currentCourseID = INITIAL_COURSE_ID;
    private ArrayList<Student> newComers = new ArrayList<>();
    private ArrayList <Course> newCourses = new ArrayList<>();
    
    
// R1
	/**
	 * Construct
	 * @param name name of the university
	 */
	public University(String name){
		// Example of logging
		// logger.info("Creating extended university object");
		//TODO: to be implemented
		this.uniName = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return uniName;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.rectorName = first + " " + last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return rectorName;
	}
	
// R2
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		//This for_loop is to check if this student is already enrolled
		for (Student s : newComers) {
		if (s.getFirstName() == first && s.getLastName() == last) 
		return -1;}

		Student s = new Student(first, last, currentStudentID++);
		newComers.add(s);
		
		logger.info("New student enrolled: " + s.getID() + ", " + s.getFirstName() + " " + s.getLastName());
		
		return s.getID();
	}

	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int ID){
		//TODO: to be implemented
		for (Student s : newComers){ 
			if (ID == s.getID()){
				return  s.getID()+ " " + s.getFirstName() + " " + s.getLastName();	
			}
		} 
	
		return null;
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		for (Course c : newCourses) {
			if (c.getCourseName() == title && c.getTeacher() == teacher) return -1;
		}
		Course c = new Course(title , teacher, currentCourseID++);
		newCourses.add(c);
		logger.info("New course activated: " + c.getCourseID() + ", " + c.getCourseName() + " " + c.getTeacher() );
		return c.getCourseID();
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		for (Course c : newCourses){
			if (code == c.getCourseID()){	
				return c.getCourseID() + "," + c.getCourseName() + "," + c.getTeacher();
			}
		}
		
		return null;
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){ 
		//TODO: to be implemented
		
		Student student = null;
		Course course = null;
		
		for ( Student s : newComers) {
			if (s.getID() == studentID) {
				student = s;
			} 
		}
		
		
		for ( Course c : newCourses) {
			if (c.getCourseID() == courseCode) {
				course = c;		
				}
		}
		
		if (course != null && student != null) { 
			course.addAttendee(student);
		    student.addAttendingCourse(course);
		}
		
		logger.info("Student " + student.getID() + " signed up for course " + course.getCourseID());
	}
	
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		String ans = "";
		
		Course course = null;
		for ( Course c : newCourses) {
			if (c.getCourseID() == courseCode) {
				course = c;	
				}
		}
		
		ArrayList<Student> attendees = null;
		if (course != null) attendees = course.getAttendees();
		
		for (Student attendee : attendees) { 
			ans = ans + student(attendee.getID()) + '\n';
		}
		
		return ans;  
	}
	
	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		String ans = "";
		
		Student student = null;
		for (Student s: newComers) {
			if(s.getID()== studentID) student = s;
		}
		
		ArrayList<Course> attendingCourses = null;
		if (student != null)  attendingCourses = student.getAttendingCourses();
	
		 
		
		for (Course c : attendingCourses) {
			ans = ans + course(c.getCourseID()) + "\n";
		}
		
		return ans; 
	}
  
// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		Student student = null;
		for (Student s: newComers) {
			if(s.getID()== studentId) student = s;
		}	
		
		Course course = null;
		for ( Course c : newCourses) {
			if (c.getCourseID() == courseID) {
				course = c;	
				}
		}
		
		if (student.isAttendingCourse(course)) {
			student.registerGrade(grade);
			course.registerGrade(grade);
		}
		
		logger.info( "Student " + student.getID() + " took an exam in course " + course.getCourseID() + " with grade " + grade);
	}
 
	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		String ans="";
		Student student = null;
		for (Student s: newComers) {
			if(s.getID()== studentId && s.getAvg()>0) student = s;
		return ans+studentId+student.getAvg();}
		{
			return "Student"+studentId+ "hasn't taken any exams";
		}
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		Course course = null;
		for ( Course c : newCourses) {
			if (c.getCourseID() == courseId) {
				course = c;	
				}
		}
		if( course.getAvg()>0) {
		return "The average for the course" +course.getCourseName()+"is :"+ course.getAvg();
		}else {
		return "No student has taken the exam in this course";
		}}
	

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
		float[] top3Scores = new float[3];
		Student[] top3Students = new Student[3];
		
		for (Student s : newComers) {
			if (s.getScore() >= top3Scores[0]) 
			{
				top3Students[2] = top3Students[1];
				top3Students[1] = top3Students[0];
				top3Students[0] = s;
				
				top3Scores[2] = top3Scores[1];
				top3Scores[1] = top3Scores[0];
				top3Scores[0] = s.getScore();	
			} 
			else if (s.getScore() >= top3Scores[1]) 
			{
				top3Students[2] = top3Students[1];
				top3Students[1] = s;
				 
				top3Scores[2] = top3Scores[1];
				top3Scores[1] = s.getScore();
			}
			else if (s.getScore() >= top3Scores[2]) 
			{
				top3Students[2] = s;
				
				top3Scores[2] = s.getScore();
			}
		}
		
		
		String ans = "";
		for (Student s : top3Students) {
			if (s != null && s.getScore() > 0) ans = ans + s.getFirstName() + " " + s.getLastName() + " : " + s.getScore() + "\n";
		}
		
		return ans;
	}

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    private final static Logger logger = Logger.getLogger("University");

}
