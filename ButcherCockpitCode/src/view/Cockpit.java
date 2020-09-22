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
 * internen Benutzeroberflaeche fuer den Metzger.
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
	 * Anzeige des Lagerbestands
	 */
	private JLabel label_stock = new JLabel("Lagerbestand Gesamt");
	public Tile stock = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, lagerort as Lagerort, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;");

	/**
	 * Anzeige des Produktportfolios
	 */
	private JLabel label_products = new JLabel("Produktportfolio");
	public Tile products = new Tile(
			"SELECT name as Produktname, produkt_id as 'Produkt-ID' , kilopreis as '[�/kg]', gewicht_portion as '[kg/Portion]' FROM Produkte;");

	/**
	 * Anzeige des F�llstandes des K�hlautomaten
	 */

	private JLabel label_automat = new JLabel("F�llstand K�hlautomat");
	public Tile automat = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");

	/**
	 * Anzeige der Verkaufsstatistiken f�r den K�hlautomat
	 */
	private JLabel label_verkaeufe = new JLabel("Verk�ufe K�hlautomat");
	private Tile verkaeufe = new Tile(
			"SELECT verkauf_id as 'Verkauf-ID', datum as Datum, uhrzeit as Uhrzeit, gesamtpreis as Gesamtpreis FROM Verkaeufe;");

	
	
	/**
	 * Konstruktor erzeugt alle anzuzeigenden Objekte
	 */
	public Cockpit() {

		/**
		 * Containerinstanz wird als ContentPane gesetzt, Tabs werden hinzugef�gt
		 */
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);

		this.setTitle("ButcherCockpit");
		this.setVisible(true);
		this.setSize(500, 550);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setIcon();

		createStockPane();
		createProductPane();
		createAutomatPane();
		createSalesPane();
	}
	

	/** Hinzufuegen eines wei�en Panels zum Tab, dem wiederum Lagertabelle und Label
	 * hinzugef�gt werden
	 */
	public void createStockPane() {
		jp_1.setBackground(Color.WHITE);
		tabbedPane.add(jp_1);
		jp_1.add(label_stock);
		jp_1.add(stock);
		tabbedPane.add("Lager", jp_1);
	}
	/** Hinzufuegen eines wei�en Panels zum Tab, dem wiederum Produkttabelle und Label
	 * hinzugef�gt werden
	 */
	public void createProductPane() {
		jp_2.setBackground(Color.WHITE);
		tabbedPane.add(jp_2);
		jp_2.add(label_products);
		jp_2.add(products);
		tabbedPane.add("Produkte", jp_2);
	}
	
	/** Hinzufuegen eines wei�en Panels zum Tab, dem wiederum Automatenbestandstabelle und Label
	 * hinzugef�gt werden
	 */
	public void createAutomatPane() {
		jp_3.setBackground(Color.WHITE);
		tabbedPane.add(jp_3);
		jp_3.add(label_automat);
		jp_3.add(automat);
		tabbedPane.add("Automat", jp_3);
	}
	
	/** Hinzufuegen eines wei�en Panels zum Tab, dem wiederum Verkaufstabelle und Label
	 * hinzugef�gt werden
	 */
	public void createSalesPane() {
		jp_4.setBackground(Color.WHITE);
		tabbedPane.add(jp_4);
		jp_4.add(label_verkaeufe);
		jp_4.add(verkaeufe);
		tabbedPane.add("Verk�ufe", jp_4);
	}

	/**
	 * Setzen des Fenster-Icons. <br>
	 * Hinweis: Falls das Bild nicht gesetzt werden kann erscheint lediglich eine
	 * Warnung, da das Bild nicht n�tig f�r das Funktionieren der Anwendung ist.
	 */
	private void setIcon() {
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}
	}

	public static void main(String[] args) {
		Cockpit ui = new Cockpit();
	}
}