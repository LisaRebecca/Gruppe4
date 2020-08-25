package classes;
import java.sql.*;
import java.util.Observable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DatabaseConnector extends Observable{
	static Connection conn = DatabaseConnection.getDBConnection();
	
	public static DefaultTableModel getTableByName(String name) throws SQLException {
		return buildTableModel(conn.createStatement().executeQuery("SELECT * FROM "+name+";"));
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
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	    ResultSetMetaData metaData = rs.getMetaData();
	    Vector<String> columnNames = new Vector<String>();

	    for (int col = 1; col <= metaData.getColumnCount(); col++) {
	        columnNames.add(metaData.getColumnLabel(col));
	    }
	    
	    System.out.println(columnNames);

	    Vector rows = new Vector();
	    Vector singleRow;
//	    System.out.println(rs.arr);
	    while (rs.next()) {
	    	singleRow = new Vector();
	        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
	        	singleRow.add(rs.getObject(columnIndex));
	        }
	        rows.add(singleRow);
	        System.out.println(rows);
	    }
	    return new DefaultTableModel(rows, columnNames);
	}
} 