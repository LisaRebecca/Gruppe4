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

		
		
		JTable jt = new JTable(
				buildTableModel(DatabaseConnector.getTableByName("")));
		

		Container con = getContentPane();
    con.add(jt);
//		JScrollPane jsp = new JScrollPane();
		JPanel jp = new JPanel(new GridBagLayout());
		con.add(jp);
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel jphead = new JPanel();
		JTextArea butcherheader = new JTextArea("Butcher Cockpit");
		jphead.add(butcherheader);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 0;
		jp.add(jphead, c);
		
		JPanel jpkund = new JPanel();
		JTextArea kunden = new JTextArea("Kundenstatistiken");
		jpkund.add(kunden);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		jp.add(jpkund, c);
		
		JPanel jplager = new JPanel();
		JTextArea lager = new JTextArea("Lagerbestand");
		jplager.add(lager);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		jp.add(jplager, c);
		
		JPanel jpeinkauf = new JPanel();
		JTextArea einkauf = new JTextArea("Einkauf");
		jpeinkauf.add(einkauf);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		jp.add(jpeinkauf, c);
		
		JPanel jpbestell = new JPanel();
		JTextArea bestell = new JTextArea("Bestellungen");
		jpbestell.add(bestell);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		jp.add(jpbestell, c);
		
		JPanel jpkuehl = new JPanel();
		JTextArea kuehl = new JTextArea("Kühlautomat");
		jpkuehl.add(kuehl);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		jp.add(jpkuehl, c);
		
//		Connection conn = DatabaseConnection.getDBConnection();
//		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
//		JTable jt = new JTable(
//				buildTableModel(rs));
		//c.add(new JLabel("hello"));
		//c.add(jt);

		this.setVisible(true);
		this.setSize(500, 500);		
	}
	
	public static void main(String[] args) {
		try {
			UserInterface ui = new UserInterface();
			ui.setVisible(true);
			ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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