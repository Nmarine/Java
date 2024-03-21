package clinic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Doctor  extends Patient {
	private String firstName;
	private String lastName;
	private String SSN;
	private int doctorID;
	private String specialization;
	private List<Patient> assignedPatients= new LinkedList<>();
	
	
	public Doctor(String firstName, String lastName, String sSN, int doctorID, String specialization) {
		super(firstName,lastName,sSN);
		this.doctorID = doctorID;
		this.specialization = specialization;
	}
 
	public String getSpecialization() {
		return specialization;
	}

	public int getDoctorID() {
		return doctorID;
	}  

	public String getFirstName() {
		return firstName;
	}
	

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return super.toString() + " [" + doctorID + "] : " + specialization;
	}

	public void AddPatient(Patient patient) {	
	 assignedPatients.add(patient);  
	}

 
 public void removePatient(Patient patient) {
	assignedPatients.remove(patient); 
 }

 
 	public List<Patient> getAssignedPatients() {
 		Collections.sort(assignedPatients);
	return assignedPatients;
}

	public boolean isIdle() {
 		return assignedPatients.isEmpty();
 	}
 	
 	int numPatients() {
		return assignedPatients.size();
	}

}
