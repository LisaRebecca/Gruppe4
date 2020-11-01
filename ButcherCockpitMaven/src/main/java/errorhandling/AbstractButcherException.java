package errorhandling;

@SuppressWarnings("serial")
/**
 * AbstractButcherException erbt von Exception und dient als abstrakte Oberklasse für alle
 * von uns enger definierten Exceptions die geworfen werden sollen, nachdem eine
 * Exception wie beispielsweise eine SQLException gefangen wurde.
 * 
 * Die AbstractButcherExceptions besitzen eine errorMessage als auch errorTitle,
 * die beim Werfen einer von AbstractButcherException erbenden Klasse übergeben werden.
 * Dadurch wird festgelegt was später dem Anwender in einem Pop-Up angezeigt wird. 
 * @author a-sch
 *
 */
public abstract class AbstractButcherException extends Exception {
	final private String errorMessage;
	final private String errorTitle;
	
	/**
	 * 
	 * @param Exception e
	 * @param String title
	 * @param String message
	 */
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
