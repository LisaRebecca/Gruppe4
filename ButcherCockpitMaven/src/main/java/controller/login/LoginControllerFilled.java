package controller.login;

import view.Factory;
import controller.Main;
import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import models.Credentials;

public class LoginControllerFilled extends LoginController {

	@Override
	public void giveControl() throws AbstractButcherException {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				try {
					Factory.get().construct();
				} catch (AbstractButcherException e) {
					throw new ButcherException(e, "Fehler in der Darstellung",
							"Bitte Mitarbeiter/ IT-Support kontaktieren");
				}
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