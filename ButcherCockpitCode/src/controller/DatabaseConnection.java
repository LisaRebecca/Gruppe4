package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse {@link DatabaseConnection} stellt genau eine Verbindung zu einer
 * lokalen MySQL-Datenbank her.
 */
public class DatabaseConnection {
	private static Connection connection = null;
	
	/**
	 * Ein privater Konstruktor stellt sicher, dass es nur genau eine Verbindung zur Datenbank gibt.
	 */
	private DatabaseConnection() {
	}

	/**
	 * Die Verbindung zur lokalen Datenbank wird aufgebaut.
	 * @return Verbindung zur Datenbank
	 */
	public static Connection getDBConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=root&password=pups");
			} catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
				System.err.println("MySQL-Driver not found.");
			} catch (SQLException s) {
				s.printStackTrace();
				System.err.print("SQL-Exception: Connection to Database could not be established.");
			}
		}
		return connection;
	}
}