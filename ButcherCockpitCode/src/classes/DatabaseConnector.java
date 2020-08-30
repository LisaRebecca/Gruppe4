package classes;

import java.sql.*;
import java.util.Observable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnector extends Observable {
	static Connection conn = DatabaseConnection.getDBConnection();

	public static DefaultTableModel executeDBQuery(String full_select_statement) {
		DefaultTableModel table = null;
		try {
			table = buildTableModel(conn.createStatement().executeQuery(full_select_statement));
		} catch (SQLException e) {
			System.out.println("Error while executing statement: ");
			System.out.println(full_select_statement);
		}
		return table;
	}

	public static DefaultTableModel getTableByName(String name) {
		return executeDBQuery("SELECT * FROM " + name + ";");
	}

	public static void updateTable(String tableName, String keyAttrName, String keyAttrVal, String attrName,
			String attrVal) {
		try {
			conn.createStatement().execute("UPDATE " + tableName + " SET " + attrName + " = " + attrVal + " WHERE "
					+ keyAttrName + " = " + keyAttrVal + ";");
		} catch (SQLException e) {
		}
	}

	public static DefaultTableModel getProductsByLocation(String location) {
		return executeDBQuery("select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");
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
		}
		return new DefaultTableModel(rows, columnNames);
	}
}