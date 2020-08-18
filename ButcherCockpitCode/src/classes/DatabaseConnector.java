package classes;
import java.sql.*;
import java.util.Observable;

public class DatabaseConnector extends Observable{
	static Connection conn = DatabaseConnection.getDBConnection();
	
	public static ResultSet getTableByName(String name) throws SQLException {
		// 1. Alternative: mit einem ActionListener zu arbeiten, 2. mit Switch (Konsoleneingabe) arbeiten
		return conn.createStatement().executeQuery("SELECT * FROM" +Produkt+";");
		return conn.createStatement().executeQuery("SELECT * FROM" +Auftragsposten+";");
		return conn.createStatement().executeQuery("SELECT * FROM" +Bestellungen+";");
		return conn.createStatement().executeQuery("SELECT * FROM" +Kunden+";");
		return conn.createStatement().executeQuery("SELECT * FROM" +Mitarbeiter+";");
	}	

//	public static ResultSet getFieldByName(String tableName, )
//	
	public static void updateTable(String tableName, String keyAttrName, String keyAttrVal, String attrName, String attrVal) {
		try {
			conn.createStatement().execute(
					"UPDATE "+tableName+
					" SET "+attrName+" = "+attrVal+
					" WHERE "+keyAttrName+" = "+keyAttrVal+";"				
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
