package controller.login;

import view.Factory;

import java.util.ResourceBundle;

import controller.Main;
import data.Database;
import errorhandling.AbstractButcherException;
import errorhandling.ButcherException;
import models.Credentials;

public class LoginControllerStandard extends LoginController {
	/**
	 * Steuert den Anmeldeprozess.Alle Zustände der Komponenten werden abgefragt,
	 * dann wird entschieden was als nächstes im Anmeldeprozess geschieht. Die
	 * Methode kann von den aufgerufenen Klassen auch aufgerufen werden um die
	 * Kontrolle zurück an den Controller zu geben.
	 */
	@Override
	public void giveControl() throws AbstractButcherException {
		if (Credentials.getIsSet()) {
			if (Database.get().isConnected) {
				Factory.get().construct();
			} else {
				Database.get().establishConnection();
			}
		} else {
			new Password_Screen();
		}
	}
}