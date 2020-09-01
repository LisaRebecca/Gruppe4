package classes;

import java.sql.*;
import java.util.Observable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnector extends Observable {
	static Connection conn = DatabaseConnection.getDBConnection();

	public static DefaultTableModel executeDBQuery(String select_statement) {
		DefaultTableModel table = null;
		try {
			table = buildTableModel(conn.createStatement().executeQuery(select_statement));
		} catch (SQLException e) {
			System.out.println("Error while executing statement: ");
			System.out.println(select_statement);
		}
		return table;
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