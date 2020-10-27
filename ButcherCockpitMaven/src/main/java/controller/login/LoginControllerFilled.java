package controller.login;

import view.Factory;

import java.util.ResourceBundle;

import controller.Main;
import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import models.Credentials;

public class LoginControllerFilled extends LoginController {
	private final ResourceBundle language;
	
	public LoginControllerFilled() {
		this.language = ResourceBundle.getBundle("i18n/butcher_exception/butcher_exception_en");
	}

	@Override
	public void giveControl() throws AbstractButcherException {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				try {
					Factory.get().construct();
				} catch (AbstractButcherException e) {
					throw new ButcherException(e, this.language.getString("title"), this.language.getString("error_text"));
				}
			} else {
				Database.get().establishConnection();
			}
		} else {
			Credentials.setUsername("Standard_Nutzer");
			Credentials.setPassword("Fleischer_Passwort");
			giveControl();
		}
	}
}