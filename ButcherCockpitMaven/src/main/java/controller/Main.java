package controller;

import Tools.Currency_Symbol;

import controller.login.LoginController;
import controller.login.LoginControllerFilled;
import controller.login.LoginControllerStandard;
import data.Database;
import data.RealDatabase;
import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.ExceptionHandlerDebug;
import errorhandling.ExceptionHandlerUser;
import payment.CreditCardPayment;
import payment.GiftCardPayment;
import payment.Payment;
import view.AutomatFactory;
import view.CockpitFactory;
import view.Factory;

@SuppressWarnings("unused") // wir kommentierern hier manche sachen abwechselnd aus, somit ist immer etwas
							// 'unused'
public class Main {
	public static void main(String[] args) {

		LoginController.set(new LoginControllerStandard());
//		LoginController.set(new LoginControllerFilled());

		Database.set(new RealDatabase());

//		ExceptionHandler.set(new ExceptionHandlerDebug());
		ExceptionHandler.set(new ExceptionHandlerUser());

		Payment.set(new GiftCardPayment());
// 		Payment.set(new CreditCardPayment());

//		Factory.set(new CockpitFactory());
		Factory.set(new AutomatFactory());

		try {
			LoginController.get().giveControl();
		} catch (AbstractButcherException e) {
			ExceptionHandler.get().showException(e);

		}
	}
}