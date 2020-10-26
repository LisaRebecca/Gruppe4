package data;

import controller.login.LoginController;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import models.Credentials;
import view.Factory;

public class LoginControllerForTesting extends LoginController {
	@Override
	public void giveControl() throws AbstractButcherException {
		if (Credentials.getIsSet()) {
			if (!Database.get().isConnected) {
				Database.get().establishConnection();
			}
		} else {
			Credentials.setUsername("Nutzer");
			Credentials.setPassword("nutzerpasswort");
			giveControl();
		}
	}
}