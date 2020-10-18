package controller.login;

import controller.Main;
import data.Database;
import errorhandling.AbstractButcherException;
import models.Credentials;

public class LoginControllerFilled extends LoginController {

	@Override
	public void giveControl() throws AbstractButcherException {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				Main.construct();
			} else {
				Database.get().establishConnection();
			}
		} else {
			Credentials.setUsername("Nutzer");
			Credentials.setPassword("nutzerpasswort");
			giveControl();
		}
	}
}