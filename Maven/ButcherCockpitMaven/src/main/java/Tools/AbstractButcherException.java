package Tools;

public abstract class AbstractButcherException extends Exception {
	final private String errorMessage;
	final private String errorTitle;
	
	public AbstractButcherException(Exception e, String title, String message) {
		super(e);
		errorMessage = message;
		errorTitle = title;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

}
