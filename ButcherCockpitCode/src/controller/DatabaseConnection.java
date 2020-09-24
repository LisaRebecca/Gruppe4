package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLInvalidAuthorizationSpecException;

/**
 * Die Klasse {@link DatabaseConnection} stellt genau eine Verbindung zu einer
 * lokalen MySQL-Datenbank her.
 */
public class DatabaseConnection {
	private static Connection connection = null;
	
	private static String userName = Password_Screen.getUsername();
	private static String password = Password_Screen.getPassword();

	/**
	 * Ein privater Konstruktor stellt sicher, dass es nur genau eine Verbindung zur
	 * Datenbank gibt.
	 */
	private DatabaseConnection() {
	}

	/**
	 * Die Verbindung zur lokalen Datenbank wird aufgebaut.
	 * 
	 * @return Verbindung zur Datenbank
	 */
	public static Connection getDBConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metzgerei?user="
						+ userName + "&password=" + password);
			} catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
				System.err.println("MySQL-Driver not found.");
			}

			catch (SQLException s) {
				s.printStackTrace();
				System.err.print("SQL-Exception: Connection to Database could not be established.");

				JOptionPane.showMessageDialog(null, "Invalid username or password. Try again", "Warning",
						JOptionPane.WARNING_MESSAGE);

			}
		}
		return connection;
	}
}
