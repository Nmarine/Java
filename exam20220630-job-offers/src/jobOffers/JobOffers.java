package jobOffers; 
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.Comparator.*;
import static java.util.Comparator.comparing;


public class JobOffers  {
	TreeSet<String> skillsCollection=new TreeSet<>();
	HashMap<String, Position> Positions= new HashMap<>();
	HashMap<String,Candidate> OurCandidates=new HashMap<>();
	ArrayList<Application> AppList=new ArrayList<>();
	HashMap<String, Consultant> consultants= new HashMap<>();

//R1
	public int addSkills (String... skills) {
		for(String s: skills) 
			skillsCollection.add(s);
		return skillsCollection.size();  
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		if(Positions.containsKey(position)) { throw new JOException("THis position is already there");}
		HashMap<String,Integer>  skillsMap= new HashMap<>();
		for(String st: skillLevels) {
			String[] skillsArray= st.split(":");
			String skill=skillsArray[0];
			int level=Integer.parseInt(skillsArray[1]);
			if(!skillsCollection.contains(skill)) throw new JOException("This skill is unknown");
			if(level<4 || level>8) throw new JOException("the level of the skill not real");  
			skillsMap.put(skill, level);
		}
	Position p= new Position(position, skillsMap);
	Positions.put(position, p);
	return p.GetAvg();  
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		List<String> skillsList = new ArrayList<String> (Arrays.asList(skills));
		if(OurCandidates.containsKey(name)) throw new JOException("We already have this candidate");
	for(String s:skills) {
		if(!skillsCollection.contains(s)) throw new JOException("We don't need that skill");
	}
	Candidate c=new Candidate(name,new TreeSet<String>(skillsList));
	OurCandidates.put(name, c);
		return skillsList.size();
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		if(!OurCandidates.containsKey(candidate)) throw new JOException("We don't hav this candidate");
		Candidate c=OurCandidates.get(candidate);
		
		ArrayList<String> positionList= new ArrayList<String>(Arrays.asList(positions));
		for(String s: positionList)  
	{
			if(!Positions.containsKey(s))throw new JOException("we don't have this position");
			if (! c.skills.containsAll(Positions.get(s).SkillLevel.keySet())) throw new JOException("you don't have all the skills");
			}
	for(String p: positions) {AppList.add(new Application(candidate,p));}
	ArrayList<String> outputList= new ArrayList<>();
	for(String p:positions) {
	outputList.add(candidate+":"+p);	
	}
	Collections.sort(outputList);
	return outputList; 
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		//TreeMap<String,List<String>> output;;
		//output=AppList.stream()
					  //.sorted(comparing(Application::getCandidateName))
					  //.collect(Collectors.groupingBy(Application::getPositions),TreeMap::new,Collectors.mapping(Application::getCandidateName, Collectors.toList()))	   
	return null; 
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		
		
		return -1;
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		return -1;
	}
	
//R4
	public List<String> discardApplications() {
		return null;
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return null;
	}
	

	
}

		
