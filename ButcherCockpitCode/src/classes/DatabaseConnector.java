package classes;
import java.sql.*;
import java.util.Observable;

public class DatabaseConnector extends Observable{
	static Connection conn = DatabaseConnection.getDBConnection();
	
	public static ResultSet getTableByName(String name) throws SQLException {
		// hier für die einzelnen Tabellen reinschreiben
		return conn.createStatement().executeQuery("SELECT * FROM "+name+";");
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
	
	public static ResultSet getProductsByLocation(String location) {
		ResultSet produkte = null;
		try {
			produkte = conn.createStatement().executeQuery(
					"select name, portionen, haltbar_bis, kilopreis from lagerbestand \r\n" + 
					"left join produkte on lagerbestand.produkt = produkte.produkt_id\r\n" + 
					"where lagerort='"+"location"+"';");
		} catch (SQLException e) {}
		return produkte;
	}
} 

