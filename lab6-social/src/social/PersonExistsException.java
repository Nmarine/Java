package social;

public class PersonExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	
	public PersonExistsException() {
		super("This person already heve a subscription");
	}
}
