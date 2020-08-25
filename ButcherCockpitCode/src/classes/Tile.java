package classes;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Tile extends JPanel {
	Font font = new Font("Arial",Font.PLAIN, 18);
	String header;
	String tabellenName;
    public Tile (String tileheader, String tabellenName) throws SQLException {
    	this.header = tileheader;
    	
    	JTable jt = new JTable(
    			DatabaseConnector.getTableByName(tabellenName));
    	
		JLabel title = new JLabel(tileheader);
    	title.setFont(font);
    	
    	this.setLayout(new BorderLayout());
    	this.setBackground(Color.WHITE);
    	
    	Border margin = new LineBorder(Color.gray,1);
    	this.setBorder(margin);
    	
    	this.add(title, BorderLayout.NORTH);
    	this.add(jt, BorderLayout.SOUTH);

    }
    
    

}