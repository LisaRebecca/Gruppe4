package errorhandling;
/**
 * Klasse, die durch die implementierte Methode dem Programmierer die Exception 
 * auf der Konsole ausgibt und in die Log-File schreibt.
 * @author a-sch
 *
 */
public class ExceptionHandlerDebug extends ExceptionHandler {

	@Override
	public void showException(AbstractButcherException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
		logException(e);
	}
}