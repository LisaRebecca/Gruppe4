import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.DatabaseConnection;
import classes.DatabaseConnector;

public class UserInterface extends JFrame{
	public UserInterface() throws SQLException {
		Container c = getContentPane();
		
		JTable jt = new JTable(
				buildTableModel(DatabaseConnector.getTableByName("")));
		c.add(jt);
		this.setVisible(true);
		this.setSize(500, 500);		
	}
	public static void main(String[] args) {
		try {
			UserInterface ui = new UserInterface();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
//
//	    ResultSetMetaData metadata = rs.getMetaData();
//	    
//	    int columnCount = metadata.getColumnCount();
//
//	    Vector<String> attributes = new Vector<String>();
//	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
//	    DefaultTableModel tableModel = new DefaultTableModel();
//	    
//	    
//	    for (int column = 1; column <= columnCount; column++) {
//	    	attributes.add(metadata.getColumnName(column));
//	    }
//
//	    
//	    while (rs.next()) {
//	        Vector<Object> vector = new Vector<Object>();
//	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//	            vector.add(rs.getObject(columnIndex));
//	        }
//	        data.add(vector);
//	    }
//
//	    return tableModel;
	    ResultSetMetaData metaData = rs.getMetaData();

	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
}