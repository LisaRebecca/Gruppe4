package errorhandling;

public class ButcherException extends AbstractButcherException {

	public ButcherException(Exception e, String title, String message) {
		super(e, title, message);
	}

}
