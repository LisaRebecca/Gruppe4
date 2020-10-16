package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Select_Statements;
import errorhandling.AbstractButcherException;

/**
 * Die Klasse UserInterface dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberflaeche fuer den Metzger.
 */
@SuppressWarnings("serial")
<<<<<<< Upstream, based on master
class Cockpit extends DefaultFrame {
=======
public class Cockpit extends DefaultFrame {
	
	private final ResourceBundle language;
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.

	/**
	 * Menuzeile unten im Cockpit-UI um zwischen den Tabellen/Kacheln zu wechseln
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);

	public Cockpit(){
		super("ButcherCockpit", 500, 550);

<<<<<<< Upstream, based on master
=======
	/**
	 * Anzeige des Lagerbestands
	 */
	private JLabel label_stock = new JLabel(this.text.getString("stock_label"));
	public Tile stock = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, lagerort as Lagerort, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id;");

	/**
	 * Anzeige des Produktportfolios
	 */
	private JLabel label_products = new JLabel(this.text.getString("product_label"));
	public Tile products = new Tile(

			"SELECT name as Produktname, produkt_id as 'Produkt-ID' , kilopreis as '[â‚¬/kg]', gewicht_portion as '[kg/Portion]' FROM Produkte;");

	/**
	 * Anzeige des Füllstandes des Kühlautomaten
	 */

	private JLabel label_automat = new JLabel(this.text.getString("automat_label"));

	public Tile automat = new Tile(
			"SELECT name as Produktname, haltbar_bis as Haltbarkeit, portionen as Vorraetig from lagerbestand LEFT JOIN produkte ON lagerbestand.produkt = produkte.produkt_id WHERE lagerort='automat1';");

	/**

	 * Anzeige der Verkaufsstatistiken für den Kühlautomat
	 */
	private JLabel label_verkaeufe = new JLabel(this.text.getString("verkaufe_label"));

	private Tile verkaeufe = new Tile(
			"SELECT HEX(verkauf_id) as 'Verkauf-ID', datum as Datum, uhrzeit as Uhrzeit, gesamtpreis as Gesamtpreis FROM Verkaeufe;");

	
	
	/**
	 * Konstruktor erzeugt alle anzuzeigenden Objekte
	 * @throws AbstractButcherException 
	 */
	public Cockpit() throws AbstractButcherException {
		
		this.language = ResourceBundle.getBundle("i18n/cockpit/cockpit_de");
		
		super(this.text.getString("title"), 500,550);

		/**
		 *  Tabs werden hinzugefügt
		 */
		
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.
		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);
	}
	
<<<<<<< Upstream, based on master
	public void addTile(Tile tile) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.add(new JLabel(""+tile.getTilename()));
		panel.add(tile);
		tabbedPane.add(panel);
		tabbedPane.add(""+tile.getTilename(), panel);
=======

	/** Hinzufuegen eines weißen Panels zum Tab, dem wiederum Lagertabelle und Label
	 * hinzugefügt werden
	 */
	public void createStockPane() {
		jp_1.setBackground(Color.WHITE);
		tabbedPane.add(jp_1);
		jp_1.add(label_stock);
		jp_1.add(stock);
		tabbedPane.add(this.text.getString("stock_tab"), jp_1);
	}
	/** Hinzufuegen eines weißen Panels zum Tab, dem wiederum Produkttabelle und Label
	 * hinzugefügt werden
	 */
	public void createProductPane() {
		jp_2.setBackground(Color.WHITE);
		tabbedPane.add(jp_2);
		jp_2.add(label_products);
		jp_2.add(products);
		tabbedPane.add(this.text.getString("product_tab"), jp_2);
	}
	
	/** Hinzufuegen eines weißen Panels zum Tab, dem wiederum Automatenbestandstabelle und Label
	 * hinzugefügt werden
	 */
	public void createAutomatPane() {
		jp_3.setBackground(Color.WHITE);
		tabbedPane.add(jp_3);
		jp_3.add(label_automat);
		jp_3.add(automat);
		tabbedPane.add(this.text.getString("automat_tab"), jp_3);
	}
	
	/** Hinzufuegen eines weißen Panels zum Tab, dem wiederum Verkaufstabelle und Label
	 * hinzugefügt werden
	 */
	public void createSalesPane() {
		jp_4.setBackground(Color.WHITE);
		tabbedPane.add(jp_4);
		jp_4.add(label_verkaeufe);
		jp_4.add(verkaeufe);
		tabbedPane.add(this.text.getString("sales_tab"), jp_4);
	}

	/**
	 * Setzen des Fenster-Icons. <br>
	 * Hinweis: Falls das Bild nicht gesetzt werden kann erscheint lediglich eine
	 * Warnung, da das Bild nicht nötig für das Funktionieren der Anwendung ist.
	 */
	@Override
	protected void setIcon() {
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			System.err.println(this.text.getString("icon_exception"));
		}
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.
	}
}