package classes;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class UserInterface extends JFrame{
	Font font = new Font("Arial",Font.PLAIN, 18);
	public UserInterface() throws SQLException {


		Container c = getContentPane();
		JPanel jp = new JPanel(new GridLayout(3,0));
		c.add(jp);
		
//		Tile kunden = new Tile("Kundenstatistiken", "Kunden");
//		jp.add(kunden);
		
		Tile lager = new Tile("Lagerbestand gesamt", "Lagerbestand", false);
		jp.add(lager);
		
		Tile produkte = new Tile("Produktportfolio", "Produkte", false);
		jp.add(produkte);
		
//		Tile bestell = new Tile("Bestellungen", "Produkte");
//		jp.add(bestell);
		
		Tile kuehl = new Tile("Füllstand Kühlautomat", "", true);
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