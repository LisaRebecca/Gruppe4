package classes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Die Klasse UserInterface dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberfläche für den Metzger. Darum muss die Klasse
 * UserInterface auch von JFrame erben.
 */
public class Cockpit extends JFrame {

	/**
	 * Erzeugt und füllt die ContentPane mit einem JPanel im GridLayout mit 3
	 * Zeilen und 1 Spalte. Diesem werden jeweils Kacheln hinzugefügt, deren
	 * Konstruktor ein SQL Statement übergeben wird, über das die benötigten
	 * Daten aus der Datenbank selektiert und in einer Tabelle angezeigt wird.
	 * 
	 */
	public Cockpit() {

		/**
		 * Containerinstanz wird als ContentPane gesetzt
		 */
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		c.add(tabbedPane,BorderLayout.CENTER);

		/**
		 * JPanelinstanz bekommt das GridLayout mit 3 Zeilen und 1 Spalte übergeben.
		 * Diese wird dem Container hinzugefügt.
		 */

		JPanel jp_1 = new JPanel();
		jp_1.setBackground(Color.WHITE);
		JPanel jp_2 = new JPanel();
		jp_2.setBackground(Color.WHITE);
		JPanel jp_3 = new JPanel();
		jp_3.setBackground(Color.WHITE);
		tabbedPane.add(jp_1);
		tabbedPane.add(jp_2);
		tabbedPane.add(jp_3);

		/**
		 * Tileinstanz mit dem passenden SQL Statement um den gesamten Lagerbestand aus
		 * der Datenbank abzufragen Diese wird dem JPanel hinzugefügt.
		 */
		JLabel label_stock = new JLabel("Lagerbestand Gesamt");
		jp.add(label_stock);
		Tile stock = new Tile(
				"SELECT name, produkt_id, haltbar_bis, lagerort, portionen, gewicht_portion from lagerbestand "
						+ "left join produkte on lagerbestand.produkt = produkte.produkt_id;");
		jp_1.add(stock);

		/**
		 * Tileinstanz mit dem passenden SQL Statement um das Produktportfolio, also die
		 * gesamte Produkttabelle aus der Datenbank abzufragen Diese wird dem JPanel
		 * hinzugefügt.
		 */


		JLabel label_products = new JLabel("Produktportfolio");
		jp.add(label_products);
		Tile products = new Tile("SELECT * FROM Produkte;");
		jp.add(products);

		jp_2.add(products);


		/**
		 * Tileinstanz mit dem passenden SQL Statement um den Füllstand des
		 * Kühlautomaten aus der Datenbank abzufragen. Diese wird dem JPanel
		 * hinzugefügt.
		 */

		JLabel label_automat = new JLabel("Füllstand Kühlautomat");
		jp.add(label_automat);
		Tile automat = new Tile(
				"SELECT name, portionen, haltbar_bis, kilopreis, gewicht_portion FROM lagerbestand "
						+ "LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");
		jp_3.add(automat);
		
		/** Der JTabbedPane die erstellten JPanels hinzufuegen
		 * 
		 */
		tabbedPane.add("Stock",jp_1);
		tabbedPane.add("Products",jp_2);
		tabbedPane.add("Automat",jp_3);
	}

	/**
	 * Erstellt eine Instanz des UserInterfaces, setzt Titel und Icon des Fensters,
	 * sowie die Sichtbarkeit, Position, Größe, und DefaultCloseOperation.
	 * 
	 * fängt eine IOException und eine MalformedURLException, wenn das Iconbild, 
	 * bzw. die dahinterstehende URL nicht gelesen werden konnte
	 */
	public static void main(String[] args) {

		Cockpit ui = new Cockpit();
		ui.setTitle("ButcherCockpit");
		BufferedImage image;
		try {
			image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			ui.setIconImage(image);
		} catch (MalformedURLException e) {
			System.err.println("Fehler beim Lesen der URL.");
		} catch (IOException e) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}
		ui.setVisible(true);
		ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}