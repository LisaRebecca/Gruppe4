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


		Container c = getContentPane();
		JPanel jp = new JPanel(new GridLayout(3,0));
		c.add(jp);
		
		Tile kunden = new Tile("Kundenstatistiken");
		jp.add(kunden);
		
		Tile lager = new Tile("Lagerbestand");
		jp.add(lager);
		
		Tile einkauf = new Tile("Einkäufe");
		jp.add(einkauf);
		
		Tile bestell = new Tile("Bestellungen");
		jp.add(bestell);
		
		Tile kuehl = new Tile("Kühlautomat");
		jp.add(kuehl);
			
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
}