package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getDBConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/metzgerei?user=root&password=sequel");
		} catch (SQLException e) {}
		return connection;
	}
}