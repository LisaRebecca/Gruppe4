package controller;

import view.Cockpit;
import controller.login.LoginController;
import controller.login.LoginControllerStandard;
import data.Database;
import data.RealDatabase;
import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import payment.GiftCard;
import payment.Payment;
import view.Automat;

public class Main {
	public static void main(String[] args) {
 		LoginController.set(new LoginControllerStandard());
// 		LoginController.set(new LoginControllerFilled());
 		
 		Database.set(new RealDatabase());
// 		Database.set(new MockDatabase());
 		
 		Payment.set(new GiftCard());
// 		Payment.set(new CreditCard());
 		
 		try {
 			LoginController.get().giveControl();
 		} catch (AbstractButcherException e) {
 			ExceptionHandler.get().showException(e);
 
 		}
	}
	
	public static void construct() {
		System.out.println("construct");
 		try {
 			new Cockpit();
 			new Automat();		
 		}
 		catch (AbstractButcherException e) {
 			ExceptionHandler.get().showException(e);
 		}		
	}
}
