package controller.login;

import errorhandling.AbstractButcherException;

/**
 * Der LoginController steuert den Anmeldeprozess an der Datenbank und startet
 * dann die Anwendung.Die Methode kann von den aufgerufenen Klassen auch
 * aufgerufen werden um die Kontrolle zurück an den Controller zu geben.
 * 
 * @author I518232
 *
 */
public abstract class LoginController {

	/**
	 * Die aktuell ausgewählte Ausprägung für den LoginController.
	 */
	private static LoginController loginController = null;

	public static LoginController get() {
		return loginController;
	}

	public static void set(LoginController loginController) {
		LoginController.loginController = loginController;
	}

	/**
	 * Einzelne Klassen können nach erfolgreichem Abschluss ihrer Aufgabe die
	 * Kontrolle wieder zurück an den LoginController geben damit dieser den Prozess
	 * weiter steuert.
	 * 
	 * @throws AbstractButcherException Falls ein Fehler im Anmeldeprozess auftritt,
	 *                                  wird eine Exception geworfen.
	 */
	public abstract void giveControl() throws AbstractButcherException;
}