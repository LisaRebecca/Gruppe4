package classes;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Tile extends JScrollPane {
	Font font = new Font("Arial",Font.PLAIN, 18);
	String header;
	String tabellenName;
	JTable jt;
    public Tile (String tileheader, String select_statement) {
    	this.header = tileheader;
    	
    	jt = new JTable(
    				DatabaseConnector.executeDBQuery(select_statement)); 
    	jt.setGridColor(Color.orange);
    	
		JLabel title = new JLabel(tileheader);
    	title.setFont(font);
    	
    	JViewport titlepanel = new JViewport();
    	titlepanel.add(title);

    	JViewport tablepanel = new JViewport();
    	tablepanel.add(jt);
    	
    	this.setBackground(Color.WHITE);
    	Border margin = new LineBorder(Color.gray,1);
    	this.setBorder(margin);

    	this.setColumnHeader(titlepanel);
    	this.setViewportView(tablepanel);
    }
}