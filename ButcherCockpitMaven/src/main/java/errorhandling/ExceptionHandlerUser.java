package errorhandling;

import javax.swing.JOptionPane;

public class ExceptionHandlerUser extends ExceptionHandler {

	// Log-File?
	@Override
	public void showException(AbstractButcherException e) {
		JOptionPane.showMessageDialog(null, e.getErrorMessage(), 
				e.getErrorTitle(), 
				JOptionPane.INFORMATION_MESSAGE);

	}

}
