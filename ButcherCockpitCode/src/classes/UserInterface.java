package classes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class UserInterface extends JFrame {
	public UserInterface() throws SQLException, IOException, IOException {

		Container c = getContentPane();
		JPanel jp = new JPanel(new GridLayout(3, 0));
		c.add(jp);

		Tile lager = new Tile(	"Lagerbestand Gesamt",
								"SELECT name, produkt_id, haltbar_bis, lagerort, portionen, gewicht_portion from lagerbestand "
							  + "left join produkte on lagerbestand.produkt = produkte.produkt_id;");
		jp.add(lager);

		Tile produkte = new Tile(	"Produktportfolio", 
									"SELECT * FROM Produkte;");
		jp.add(produkte);

		Tile kuehl = new Tile(		"Füllstand Kühlautomat",
									"SELECT name, portionen, haltbar_bis, kilopreis, gewicht_portion FROM lagerbestand "
								+   "LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");
		jp.add(kuehl);

	}

	public static void main(String[] args) throws IOException {
		try {
			UserInterface ui = new UserInterface();
			ui.setTitle("ButcherCockpit");
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			ui.setIconImage(image);
			ui.setVisible(true);
			ui.setLocation(200, 200);
			ui.setSize(700, 400);
			ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}