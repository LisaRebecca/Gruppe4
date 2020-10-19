package controller;

import controller.login.LoginController;
import controller.login.LoginControllerFilled;
import controller.login.LoginControllerStandard;
import data.Database;
import data.RealDatabase;
import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import payment.GiftCard;
import payment.Payment;
import view.AutomatFactory;
import view.CockpitFactory;
import view.Factory;

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
// 		Factory.set(new CockpitFactory());
 		Factory.set(new AutomatFactory());
	}
}