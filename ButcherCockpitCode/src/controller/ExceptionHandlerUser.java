package controller;

import javax.swing.JOptionPane;

public class ExceptionHandlerUser extends ExceptionHandler {

	@Override
	public void showException(Exception e) {
		JOptionPane.showMessageDialog(null, e.errorMessage, e.errorTitle, 
				JOptionPane.INFORMATION_MESSAGE);

	}

}
