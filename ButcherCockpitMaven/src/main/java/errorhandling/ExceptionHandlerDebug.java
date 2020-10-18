package errorhandling;

public class ExceptionHandlerDebug extends ExceptionHandler {

	@Override
	public void showException(AbstractButcherException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
}
