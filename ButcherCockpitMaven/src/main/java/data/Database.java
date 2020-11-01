package data;

import java.sql.ResultSet;
import errorhandling.AbstractButcherException;
import errorhandling.SQLButcherException;

/**
 * Abstrakte Klasse, welche einen zentralen Zugriffspunkt auf die vor
 * Programmstart festgelegte Datenbank liefert.
 */
public abstract class Database {
	
	/**
	 * Die aktuell 
	 */
	private static Database database = new RealDatabase();
	public boolean isConnected = false;

	public static Database get() {
		return Database.database;
	}

	public static void set(Database database) {
		Database.database = database;
	}

	/**
	 * Herstellen einer Verbindung zu einer Datenbank.
	 * 
	 * @throws AbstractButcherException falls der Verbindungsaufbau fehlschlägt,
	 *                                  wird eine Exception geworfen.
	 */
	public abstract void establishConnection() throws AbstractButcherException;

	/**
	 * Eine Anfrage an die Datenbank senden.
	 * 
	 * @param stmt eine in {@link Select_Statements} definierte SQL-Anfrage
	 * @return ein <code>ResultSet</code> welches die angefragten Daten beinhaltet
	 * @throws SQLButcherException eine Exception wird geworfen, falls die Datenbank
	 *                             nicht erreichbar ist oder die Anfrage syntaktisch
	 *                             nicht korrekt ist.
	 */
	public abstract ResultSet executeDBQuery(Select_Statements stmt) throws SQLButcherException;

	/**
	 * Ein Update in der Datenbank ausführen.
	 * 
	 * @param insert_statement Eine SQL-Anweisung, welche die Datenbank erweitert
	 *                         oder aktualisiert.
	 * @throws SQLButcherException eine Exception wird geworfen, falls die Datenbank
	 *                             nicht erreichbar ist oder die Anfrage syntaktisch
	 *                             nicht korrekt ist.
	 */
	public abstract void executeDBInsert(String insert_statement) throws SQLButcherException;

}