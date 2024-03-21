package social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Person {

	private String socialCode;
	private String name;
	private String Surname;
	private HashSet<String> myFriends= new HashSet<>();
	private HashSet<String> participatinggroups= new HashSet<>();
	
	
	
public Person(String socialCode, String name, String surname) {
	this.socialCode=socialCode;	
	this.name = name;
	this.Surname = surname;
	}
public String getSocialCode() {
		return socialCode;
	}
public String getName() {
		return name;
	}
public String getSurname() {
		return Surname;
	}
public void setMyFriends(String newFriend) {
	myFriends.add(newFriend);
}
public Collection<String> getMyFriends() {
	return myFriends;
}
public String toString() {
	return socialCode+" "+name+" "+Surname+" ";
}
public void addGroup(String groupname) {
	participatinggroups.add(groupname); 
}
public HashSet<String> getParticipatinggroups() {
	return participatinggroups;
}

}
