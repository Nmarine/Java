package jobOffers;

import java.util.Set;
import java.util.TreeSet;

public class Candidate {

		private String name;
		Set<String> skills= new TreeSet<>();
		
		public Candidate(String name, Set<String> skills) {
			this.name=name;
			this.skills= skills; 
	}

		public String getName() {
			return name;
		}

		public Set<String> getSkills() {
			return skills;
		}
		
		
}
 