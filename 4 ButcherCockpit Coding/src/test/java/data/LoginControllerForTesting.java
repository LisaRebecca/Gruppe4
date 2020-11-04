package data;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import models.Credentials;
import view.Factory;

public class LoginControllerForTesting extends LoginController {
	@Override
	public void giveControl() throws AbstractButcherException {
		Credentials.setUsername("DefaultUser");
		Credentials.setPassword("DefaultPassword");
		if (!Database.get().isConnected) {
			Database.get().establishConnection();
		}
	}
}