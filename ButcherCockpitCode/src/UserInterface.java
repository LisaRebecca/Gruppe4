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
	Font font = new Font("Arial",Font.PLAIN, 18);
	public UserInterface() throws SQLException {

//		JTable jt = new JTable(
//				buildTableModel(DatabaseConnector.getTableByName("")));
		

		Container c = getContentPane();
//    con.add(jt);
//		JScrollPane jsp = new JScrollPane();
		JPanel jp = new JPanel(new GridBagLayout());
		c.add(jp);
		GridBagConstraints gbc = new GridBagConstraints();
		
//		JPanel jphead = new JPanel();
////		JTextArea butcherheader = new JTextArea("Butcher Cockpit");
//		JButton butcherheader = new JButton("Butcher Cockpit");
//		jphead.add(butcherheader);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
////		gbc.ipady = 40;
//		//Anzahl der Spalten/Zeilen die das Feld breit/hoch sein soll
//		gbc.gridwidth = 3;
////		gbc.gridheight =1;
//		//Positionen
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		//Freiraum zwischen diesem Feld und anderen
////		gbc.weightx=1.0;
////		gbc.weighty=0.5;
//		jp.add(jphead, gbc);
		
		JButton kunden = new JButton("Kundenstatistiken");
		kunden.setPreferredSize(new Dimension(200,100));
		kunden.setBackground(Color.WHITE);
		kunden.setFont(font);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jp.add(kunden, gbc);
		
		JButton lager = new JButton("Lagerbestand");
		lager.setPreferredSize(new Dimension(200,100));
		lager.setBackground(Color.WHITE);
		lager.setFont(font);
		gbc.gridx = 1;
		gbc.gridy = 1;
		jp.add(lager, gbc);
		
		JButton einkauf = new JButton("Einkauf");
		einkauf.setPreferredSize(new Dimension(200,100));
		einkauf.setBackground(Color.WHITE);
		einkauf.setFont(font);
		gbc.gridx = 0;
		gbc.gridy = 2;
		jp.add(einkauf, gbc);
		
		JButton bestell = new JButton("Bestellungen");
		bestell.setPreferredSize(new Dimension(200,100));
		bestell.setBackground(Color.WHITE);
		bestell.setFont(font);
		gbc.gridx = 1;
		gbc.gridy = 2;
		jp.add(bestell, gbc);
		
		JButton kuehl = new JButton("Kühlautomat");
		kuehl.setPreferredSize(new Dimension(200,100));
		kuehl.setBackground(Color.WHITE);
		kuehl.setFont(font);
		gbc.gridx = 2;
		gbc.gridy = 2;
		jp.add(kuehl, gbc);
		
//		Tile tiletest = new Tile("tiletest");
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		jp.add(tiletest, gbc);
//		
//		Tile tiletest1 = new Tile("tiletest1");
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		jp.add(tiletest1, gbc);
//		
//		Tile tiletest2 = new Tile("tiletest2");
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		jp.add(tiletest2, gbc);
		
//		Connection conn = DatabaseConnection.getDBConnection();
//		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
//		JTable jt = new JTable(
//				buildTableModel(rs));
//		c.add(new JLabel("hello"));
//		c.add(jt);

		this.setVisible(true);
		this.setSize(500, 500);		
	}
	
	public static void main(String[] args) {
		try {
			UserInterface ui = new UserInterface();
			ui.setTitle("ButcherCockpit");
			ui.setVisible(true);
			ui.setLocation(200,200);
			ui.setSize(700, 300);
			ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
//	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
//	    ResultSetMetaData metaData = rs.getMetaData();
//	    Vector<String> columnNames = new Vector<String>();
//
//	    for (int col = 1; col <= metaData.getColumnCount(); col++) {
//	        columnNames.add(metaData.getColumnLabel(col));
//	    }
//	    
//	    System.out.println(columnNames);
//
//	    Vector rows = new Vector();
//	    Vector singleRow;
////	    System.out.println(rs.arr);
//	    while (rs.next()) {
//	    	singleRow = new Vector();
//	        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
//	        	singleRow.add(rs.getObject(columnIndex));
//	        }
//	        rows.add(singleRow);
//	        System.out.println(rows);
//	    }
//	    return new DefaultTableModel(rows, columnNames);
//	}
}