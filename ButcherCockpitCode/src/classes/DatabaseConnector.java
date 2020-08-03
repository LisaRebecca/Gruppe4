package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class DatabaseConnector extends Observable{
	
	
	private DatabaseConnector() {
		Connection conn = DatabaseConnection.getDBConnection();		
	}
	
	public Result
	ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
	
	
}