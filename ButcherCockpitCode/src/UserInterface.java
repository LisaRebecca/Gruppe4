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