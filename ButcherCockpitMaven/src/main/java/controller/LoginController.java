package controller;

import Tools.AbstractButcherException;

public abstract class LoginController {
	
	private static LoginController loginController;
//	private boolean isLoggedIn = false;
	
	public static LoginController get() {
		return loginController;
	}

	public static void set(LoginController loginController) {
		LoginController.loginController = loginController;
	}

	public abstract void giveControl() throws AbstractButcherException;
}