package social;

import java.util.HashSet;

public class Group {

	private String groupName;
	private HashSet<String> members= new HashSet<>();
	
	
	public Group(String groupName) {
		this.groupName=groupName;
	}


	public String getGroupName() {
		return groupName;
	}
	
	public void addmembers(String code) {
		members.add(code);
	}


	public HashSet<String> getMembers() {
		return members;
	}
	
}

