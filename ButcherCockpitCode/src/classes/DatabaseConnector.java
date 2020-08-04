package classes;
import java.sql.*;
import java.util.Observable;

public class DatabaseConnector extends Observable{
	static Connection conn = DatabaseConnection.getDBConnection();
	
	public static ResultSet getTableByName(String name) throws SQLException {
		// hier für die einzelnen Tabellen reinschreiben
		return conn.createStatement().executeQuery("SELECT * FROM Produkt;");
	}	
}
