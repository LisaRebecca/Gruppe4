package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse {@link DatabaseConnection} stellt genau eine Verbindung zu einer
 * lokalen MySQL-Datenbank her.
 * 
 * @author Lisa Schmidt
 *
 */
public class DatabaseConnection {
	private static Connection connection = null;

	private DatabaseConnection() {
	}

	public static Connection getDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metzgerei?user=root&password=sequel");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			System.err.println("MySQL-Driver not found.");
		} catch (SQLException s) {
			s.printStackTrace();
			System.err.print("SQL-Exception: Connection to Database could not be established.");
		}
		return connection;
	}
}