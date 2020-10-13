package controller;

import view.Cockpit;
import Tools.AbstractButcherException;
import view.Automat;

public class Main {
	public static void main(String[] args) {
		LoginController.set(new LoginControllerStandard());
//		LoginController.set(new LoginControllerFilled());
		
		Database.set(new RealDatabase());
//		Database.set(new MockDatabase());
		
//		ExceptionHandler.set(new ExceptionHandlerDebug());
		ExceptionHandler.set(new ExceptionHandlerUser());
		
		try {
			LoginController.get().giveControl();
		} catch (AbstractButcherException e) {
			ExceptionHandler.get().showException(e);

		}
	}
	
	public static void construct() {
		try {
			new Cockpit();
			new Automat();		
		}
		catch (AbstractButcherException e) {
			ExceptionHandler.get().showException(e);
		}
	}
}
