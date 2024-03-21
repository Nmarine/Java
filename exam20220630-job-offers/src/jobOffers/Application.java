package jobOffers;

import java.util.ArrayList;
import java.util.List;

public class Application {
	private String CandidateName;
	private String Positions;
	
public Application(String candidateName, String positions) {
	
		this.CandidateName = candidateName;
		this.Positions = positions;
	}

public String getCandidateName() {
	return CandidateName;
}

public String getPositions() {
	return Positions;
}
	

}
