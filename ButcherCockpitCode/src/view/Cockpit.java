package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Die Klasse UserInterface dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberflaeche fuer den Metzger. Darum muss die Klasse
 * UserInterface auch von JFrame erben.
 */
@SuppressWarnings("serial")
public class Cockpit extends JFrame {
	
	/**
	 * Menuzeile unten im Cockpit-UI um zwischen den Tabellen/Kacheln zu wechseln
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);

	private JPanel jp_1 = new JPanel();
	private JPanel jp_2 = new JPanel();
	private JPanel jp_3 = new JPanel();
	private JPanel jp_4 = new JPanel();
	
	/**
	  Tilelabel mit passendem SQL Statement um den gesamten Lagerbestand aus
	 	der Datenbank abzufragen
	 */
	private JLabel label_stock = new JLabel("Lagerbestand Gesamt");
	public Tile stock = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, lagerort as Lagerort, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;");
	
	/**
	 * Tilelabel mit dem passenden SQL Statement um das Produktportfolio, also die
	 * gesamte Produkttabelle aus der Datenbank abzufragen
	 */
	private JLabel label_products = new JLabel("Produktportfolio");
	public Tile products = new Tile(
			"SELECT name as Produktname, produkt_id as 'Produkt-ID' , kilopreis as '[€/kg]', gewicht_portion as '[kg/Portion]' FROM Produkte;");
	
	/**
	 * Tilelabel mit dem passenden SQL Statement um den FÃ¼llstand des
	 * KÃ¼hlautomaten aus der Datenbank abzufragen
	 */
	
	private JLabel label_automat = new JLabel("Füllstand Kühlautomat");
	public Tile automat = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");
	
	/**
	 *  Tilelabel mit dem passenden SQL Statement um die gesamten
	 *  Verkäufe durch den Kühlautomaten aus der Datenbank abzufragen
	 */
	private JLabel label_verkaeufe = new JLabel("Verkäufe Kühlautomat");
	private Tile verkaeufe = new Tile ("SELECT verkauf_id as 'Verkauf-ID', datum as Datum, uhrzeit as Uhrzeit, gesamtpreis as Gesamtpreis FROM Verkaeufe;");
	/**
	 * Erzeugt und faellt die ContentPane mit einem JPanel im GridLayout mit 3
	 * Zeilen und 1 Spalte. Diesem werden jeweils Kacheln hinzugefuegt, deren
	 * Konstruktor ein SQL Statement uebergeben wird, ueber das die benoetigten
	 * Daten aus der Datenbank selektiert und in einer Tabelle angezeigt wird.
	 * 
	 */
	public Cockpit() {

		/**
		 * Containerinstanz wird als ContentPane gesetzt,
		 * Tabs werden hinzugefügt
		 */
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);

		/**
		 * den Tabs werden geweils weiße JPanels hinzugefügt
		 */
		jp_1.setBackground(Color.WHITE);
		jp_2.setBackground(Color.WHITE);
		jp_3.setBackground(Color.WHITE);
		jp_4.setBackground(Color.WHITE);
		tabbedPane.add(jp_1);
		tabbedPane.add(jp_2);
		tabbedPane.add(jp_3);
		tabbedPane.add(jp_4);

		/** Lagerbestandtabelle und Label werden dem JPanel hinzugefÃ¼gt.
		 */
		jp_1.add(label_stock);
		jp_1.add(stock);
		
		/** Produkttabelle und Label werden dem JPanel hinzugefÃ¼gt.
		 */
		jp_2.add(label_products);
		jp_2.add(products);

		/** Automatenbestandstabelle und Label werden dem JPanel hinzugefÃ¼gt.
		 */
		jp_3.add(label_automat);
		jp_3.add(automat);

		/** Verkaufstabelle und Label werden dem JPanel hinzugefÃ¼gt.
		 */
		jp_4.add(label_verkaeufe);
		jp_4.add(verkaeufe);

		/**
		 * Der JTabbedPane die erstellten JPanels hinzufuegen
		 * 
		 */
		tabbedPane.add("Lager", jp_1);
		tabbedPane.add("Produkte", jp_2);
		tabbedPane.add("Automat", jp_3);
		tabbedPane.add("Verkäufe", jp_4);
	}

	/**
	 * Erstellt eine Instanz des UserInterfaces, setzt Titel und Icon des Fensters,
	 * sowie die Sichtbarkeit, Position, Groesse, und DefaultCloseOperation.
	 * 
	 * faengt eine IOException und eine MalformedURLException, wenn das Iconbild,
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
		ui.setSize(500, 550);
		ui.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}