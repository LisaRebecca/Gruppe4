package controller;

import javax.swing.JOptionPane;

import Tools.AbstractButcherException;

public class ExceptionHandlerUser extends ExceptionHandler {

	@Override
	public void showException(AbstractButcherException e) {
		JOptionPane.showMessageDialog(null, e.getErrorMessage(), 
				e.getErrorTitle(), 
				JOptionPane.INFORMATION_MESSAGE);

	}

}
