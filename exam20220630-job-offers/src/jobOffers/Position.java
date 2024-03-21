package jobOffers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Position {
	private String name;
	HashMap<String,Integer> SkillLevel=null;
	private List<String> AppliedCandidates= new ArrayList<>();
	
	public Position(String name, HashMap<String,Integer> skillLevel) {
		this.name = name;
		this.SkillLevel = skillLevel;
	}
	public int GetAvg() {
		return SkillLevel.values().stream()
						   .reduce(0,(a,b)->a+b)/SkillLevel.size();}
	public String getName() {
		return name;
	}
	public HashMap<String, Integer> getSkillLevel() {
		return SkillLevel;
	}

	
}
 