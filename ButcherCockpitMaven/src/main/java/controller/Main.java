package controller;

import controller.login.LoginController;
import controller.login.LoginControllerFilled;
import controller.login.LoginControllerStandard;
import data.Database;
import data.MockDatabase;
import data.RealDatabase;
import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.ExceptionHandlerDebug;
import payment.GiftCard;
import payment.Payment;
import view.CockpitFactory;
import view.Factory;

@SuppressWarnings("unused") // wir kommentierern hier manche sachen abwechselnd aus, somit ist immer etwas 'unused'
public class Main {
	public static void main(String[] args) {
<<<<<<< HEAD
 	LoginController.set(new LoginControllerStandard());
 		//LoginController.set(new LoginControllerFilled());
=======
// 		LoginController.set(new LoginControllerStandard());
 		LoginController.set(new LoginControllerFilled());
>>>>>>> branch 'master' of https://github.com/LisaRebecca/Gruppe4.git
 		
 		Database.set(new RealDatabase());
// 		Database.set(new MockDatabase());
 		
 		ExceptionHandler.set(new ExceptionHandlerDebug());
//		ExceptionHandler.set(new ExceptionHandlerUser());
 		
 		Payment.set(new GiftCard());
// 		Payment.set(new CreditCard());
 		
 		Factory.set(new CockpitFactory());
// 		Factory.set(new AutomatFactory());

 		try {
 			LoginController.get().giveControl();
 		} catch (AbstractButcherException e) {
 			ExceptionHandler.get().showException(e);
 
 		}
<<<<<<< HEAD
 		//Factory.set(new CockpitFactory());
		Factory.set(new AutomatFactory());
=======
>>>>>>> branch 'master' of https://github.com/LisaRebecca/Gruppe4.git
	}
}