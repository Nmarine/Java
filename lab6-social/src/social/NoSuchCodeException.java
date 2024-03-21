package social;

public class NoSuchCodeException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchCodeException() {
	super("No such person is found");
	}
}
 