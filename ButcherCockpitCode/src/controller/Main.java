package controller;

import view.Cockpit;
import view.Automat;

public class Main {
	public static void main(String[] args) {
		LoginController.set(new LoginControllerStandard());
//		LoginController.set(new LoginControllerFilled());
		
		Database.set(new RealDatabase());
//		Database.set(new MockDatabase());
		
		Payment.set(new GiftCard());
//		Payment.set(new CreditCard());
		
		LoginController.get().giveControl();
	}
	
	public static void construct() {
		new Cockpit();
		new Automat();		
	}
}
