package errorhandling;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
/**
 * ExceptionHandlerUser dient dazu dem Anwender die (weitergereichte) Exception 
 * mit der zuvor festgelegten errorMesssage und dem ErrorTitle in einem Pop-Up-Fenster
 * auf dem Bildschirm anzuzeigen. Zudem wird in die Log-File geschrieben, welche Exceptions
 * an welcher Stelle geworfen wurden.
 * @author a-sch
 *
 */
public class ExceptionHandlerUser extends ExceptionHandler {


	@Override
	public void showException(AbstractButcherException e) {
		JOptionPane.showMessageDialog(null, e.getErrorMessage(), 
				e.getErrorTitle(), 
				JOptionPane.INFORMATION_MESSAGE);
		logException(e);
	}
}