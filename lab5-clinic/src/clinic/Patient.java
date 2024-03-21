package clinic;

public class Patient implements Comparable<Patient>{
	private String firstName;
	private String lastName;
	private String SSN;
	private Doctor myDoctor;
	
	
	public Patient(String firstName, String lastName, String sSN) {
		this.firstName = firstName;
		this.lastName = lastName;
		SSN = sSN;
	}

	
	public String getSSN() {
		return SSN;
	}


	public String toString() {
		return lastName+" "+firstName+" "+"("+SSN+")";	
     }

	public void setDoctor(Doctor d) {	
	  if(myDoctor!=null) {
		  myDoctor.removePatient(this);
	  }
	  myDoctor=d;	
	  d.AddPatient(this); 
	} 
 

	public Doctor getMyDoctor() {
		return myDoctor;
	}

	

	@Override
	public int compareTo(Patient o) {
		int dl =  this.lastName.compareTo(o.lastName);
		if(dl!=0) return dl;
		return this.firstName.compareTo(o.firstName);

	}



}
