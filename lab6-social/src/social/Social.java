package social;

import java.util.*;
import java.util.stream.Collectors;


public class Social {
	private HashMap<String, Person> subscripted= new HashMap<>();
	private HashMap<String, Group>  groups= new HashMap<>();
	
	/**
	 * Creates a new account for a person
	 * 
	 * @param code	nickname of the account
	 * @param name	first name
	 * @param surname last name
	 * @throws PersonExistsException in case of duplicate code
	 */	
	public void addPerson(String code, String name, String surname)
			throws PersonExistsException {
		if(subscripted.containsKey(code)) { 
			throw new PersonExistsException();}
		Person p1=new Person(code,name,surname);
		subscripted.put(code,p1);	
	}
	

	/**
	 * Retrieves information about the person given their account code.
	 * The info consists in name and surname of the person, in order, separated by blanks.
	 * 
	 * @param code account code
	 * @return the information of the person
	 * @throws NoSuchCodeException
	 */
	public String getPerson(String code) throws NoSuchCodeException {
		Person p= subscripted.get(code);
		if(p==null) {throw new NoSuchCodeException();}
		return p.toString();
		}

	/**
	 * Define a friendship relationship between to persons given their codes.
	 * 
	 * Friendship is bidirectional: if person A is friend of a person B, that means that person B is a friend of a person A.
	 * 
	 * @param codePerson1	first person code
	 * @param codePerson2	second person code
	 * @throws NoSuchCodeException in case either code does not exist
	 */
	public void addFriendship(String codePerson1, String codePerson2)
			throws NoSuchCodeException {
		Person p1=subscripted.get(codePerson1);
		Person p2= subscripted.get(codePerson2);
		if(p1==null || p2==null) {
			throw new NoSuchCodeException();
		}
		p1.setMyFriends(codePerson2);
		p2.setMyFriends(codePerson1);
	}

	/**
	 * Retrieve the collection of their friends given the code of a person.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return the list of person codes
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> listOfFriends(String codePerson)
			throws NoSuchCodeException {
		Person p= subscripted.get(codePerson);
		if(p==null) {throw new NoSuchCodeException();}
		if(subscripted.get(codePerson).getMyFriends().size()==0)
			return new LinkedList<>();
		else {return subscripted.get(codePerson).getMyFriends();} 
	}
	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriends(String codePerson)
			throws NoSuchCodeException {
		Person p1=subscripted.get(codePerson);
		if(p1==null) {throw new NoSuchCodeException();}
		Collection<String> friendOfFriend=p1.getMyFriends().stream()
				                .map(subscripted::get)
//By this step Each element in this stream will be a Person object corresponding to each key you had in your original Stream<String>.
				                .map(Person::getMyFriends)//Now we have a collection of friend of friend as a collection<String>
				                .flatMap(Collection::stream)
				                .filter(p -> !p.equals(codePerson))
				                .collect(Collectors.toList());
		if(friendOfFriend.size()==0) {return new LinkedList<>();}
		else {return friendOfFriend;}
}

	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * The result has no duplicates.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriendsNoRepetition(String codePerson)
			throws NoSuchCodeException {
		Person p1=subscripted.get(codePerson);
		if(p1==null) {throw new NoSuchCodeException();}
		Collection<String> friendOfFriend=p1.getMyFriends().stream()
				                .map(subscripted::get)
				                .map(Person::getMyFriends)
				                .flatMap(Collection::stream)
				                .filter(p -> !p.equals(codePerson))
				                .distinct()
				                .collect(Collectors.toList());
		if(friendOfFriend.size()==0) {return new LinkedList<>();}
		else {return friendOfFriend;}
	}

	/**
	 * Creates a new group with the given name
	 * 
	 * @param groupName name of the group
	 */
	public void addGroup(String groupName) {
		groups.put(groupName, new Group(groupName));
	}

	/**
	 * Retrieves the list of groups.
	 * 
	 * @return the collection of group names
	 */
	public Collection<String> listOfGroups() {
		if (groups.size() == 0)
			return Collections.emptyList();
		
		return groups.keySet();
	}

	/**
	 * Add a person to a group
	 * 
	 * @param codePerson person code
	 * @param groupName  name of the group
	 * @throws NoSuchCodeException in case the code or group name do not exist
	 */
	public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
			Person p= subscripted.get(codePerson);
			Group g=groups.get(groupName);
			if(p==null || g==null) { throw new NoSuchCodeException();}
			g.addmembers(codePerson);
			p.addGroup(groupName);
	}

	/**
	 * Retrieves the list of people on a group
	 * 
	 * @param groupName name of the group
	 * @return collection of person codes
	 */
	public Collection<String> listOfPeopleInGroup(String groupName) {
		if(groups.containsKey(groupName)) {
			return groups.get(groupName).getMembers();}	
		return null;
	}

	/**
	 * Retrieves the code of the person having the largest
	 * group of friends
	 * 
	 * @return the code of the person
	 */
	public String personWithLargestNumberOfFriends() {
		return subscripted.values().stream()
							.max(Comparator.comparing(p->p.getMyFriends().size()))
							.map(Person::getSocialCode).orElse("NONE");
	}

	/**
	 * Find the code of the person with largest number
	 * of second level friends
	 * 
	 * @return the code of the person
	 */
	public String personWithMostFriendsOfFriends() {
		return subscripted.values().stream()
								   .max(Comparator.comparing(p->{
									try {
										return friendsOfFriends(p.getSocialCode()).size();
									}
									 catch(NoSuchCodeException e) {  
										 throw new RuntimeException(e);}
								   }))
								   .map(Person::getSocialCode).orElse("<none>");
	}

	/**
	 * Find the name of group with the largest number of members
	 * 
	 * @return the name of the group
	 */
	public String largestGroup() {
		  return groups.values().stream()
		  		.max(Comparator.comparing(p->p.getMembers().size()))
		  		.map(Group::getGroupName).orElse("None");
	//we put this orElse because the output of of max is optional<T>,just in case the stream is empty
		}

	/**
	 * Find the code of the person that is member of
	 * the largest number of groups
	 * 
	 * @return the code of the person
	 */
	public String personInLargestNumberOfGroups() {
			return subscripted.values().stream()
					              .max(Comparator.comparing(p -> p.getParticipatinggroups().size()))
					              .map(Person::getSocialCode).orElse("<none>");

	}
}