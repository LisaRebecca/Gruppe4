package classes;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class UserInterface extends JFrame{
	Font font = new Font("Arial",Font.PLAIN, 18);
	public UserInterface() throws SQLException, IOException, IOException {


		Container c = getContentPane();
		JPanel jp = new JPanel(new GridLayout(3,0));
		c.add(jp);
		
		
//		Tile kunden = new Tile("Kundenstatistiken", "Kunden");
//		jp.add(kunden);
		
		Tile lager = new Tile("Lagerbestand Gesamt");
		jp.add(lager);
		
		Tile produkte = new Tile("Produktportfolio", "Produkte");
		jp.add(produkte);
		
//		Tile bestell = new Tile("Bestellungen", "Produkte");
//		jp.add(bestell);
		
		Tile kuehl = new Tile("Füllstand Kühlautomat", "", "");
		jp.add(kuehl);

	}
	
	public static void main(String[] args) throws IOException {
		try {
			UserInterface ui = new UserInterface();
			ui.setTitle("ButcherCockpit");
			BufferedImage image = ImageIO.read(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			ui.setIconImage(image);
			ui.setVisible(true);
			ui.setLocation(200,200);
			ui.setSize(700, 400);
			ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}