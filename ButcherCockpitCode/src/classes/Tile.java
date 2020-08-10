package classes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Tile extends JPanel {
	Font font = new Font("Arial",Font.PLAIN, 18);
	String header;
    // Panel
	// DBT Name
	// title
    public Tile (String tileheader) throws SQLException {
    	this.header = tileheader;
    	
    	JTable jt = new JTable(
    			buildTableModel(DatabaseConnector.getTableByName("Produkt")));
    	
		JLabel title = new JLabel(tileheader);
    	title.setFont(font);
    	
    	this.setLayout(new BorderLayout());
    	this.setBackground(Color.WHITE);
    	
    	Border margin = new LineBorder(Color.gray,1);
    	this.setBorder(margin);
    	
    	this.add(title, BorderLayout.NORTH);
    	this.add(jt, BorderLayout.SOUTH);

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


