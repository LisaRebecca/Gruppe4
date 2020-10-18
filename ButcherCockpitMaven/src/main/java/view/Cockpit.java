package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Select_Statements;
import errorhandling.AbstractButcherException;
import models.Tile;

/**
 * Die Klasse UserInterface dient der Darstellung des ButcherCockpits, also der
 * internen Benutzeroberflaeche fuer den Metzger.
 */
@SuppressWarnings("serial")
class Cockpit extends DefaultFrame {

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
	public Tile stock = new Tile(Select_Statements.select_full_stock);

	/**
	 * Anzeige des Produktportfolios
	 */
	private JLabel label_products = new JLabel("Produktportfolio");
	public Tile products = new Tile(Select_Statements.select_products);

	/**
	 * Anzeige des Füllstandes des Kühlautomaten
	 */

	private JLabel label_automat = new JLabel("Füllstand Kühlautomat");

	public Tile automat = new Tile(Select_Statements.select_automat);

	/**
	 * 
	 * Anzeige der Verkaufsstatistiken für den Kühlautomat
	 */
	private JLabel label_verkaeufe = new JLabel("VerkÃ¤ufe Kühlautomat");

	private Tile verkaeufe = new Tile(Select_Statements.select_purchases);

	/**
	 * Konstruktor erzeugt alle anzuzeigenden Objekte
	 * 
	 * @throws AbstractButcherException
	 */
	public Cockpit(){
		super("ButcherCockpit", 500, 550);

		c.setLayout(new BorderLayout());
		c.add(tabbedPane, BorderLayout.CENTER);

		createStockPane();
		createProductPane();
		createAutomatPane();
		createSalesPane();
	}

	/**
	 * Hinzufuegen eines weißen Panels zum Tab, dem wiederum Lagertabelle und Label
	 * hinzugefügt werden
	 */
	public void createStockPane() {
		jp_1.setBackground(Color.WHITE);
		tabbedPane.add(jp_1);
		jp_1.add(label_stock);
		jp_1.add(stock);
		tabbedPane.add("Lager", jp_1);
	}

	/**
	 * Hinzufuegen eines weißen Panels zum Tab, dem wiederum Produkttabelle und
	 * Label hinzugefügt werden
	 */
	public void createProductPane() {
		jp_2.setBackground(Color.WHITE);
		tabbedPane.add(jp_2);
		jp_2.add(label_products);
		jp_2.add(products);
		tabbedPane.add("Produkte", jp_2);
	}

	/**
	 * Hinzufuegen eines weißen Panels zum Tab, dem wiederum
	 * Automatenbestandstabelle und Label hinzugefügt werden
	 */
	public void createAutomatPane() {
		jp_3.setBackground(Color.WHITE);
		tabbedPane.add(jp_3);
		jp_3.add(label_automat);
		jp_3.add(automat);
		tabbedPane.add("Automat", jp_3);
	}

	/**
	 * Hinzufuegen eines weißen Panels zum Tab, dem wiederum Verkaufstabelle und
	 * Label hinzugefügt werden
	 */
	public void createSalesPane() {
		jp_4.setBackground(Color.WHITE);
		tabbedPane.add(jp_4);
		jp_4.add(label_verkaeufe);
		jp_4.add(verkaeufe);
		tabbedPane.add("VerkÃ¤ufe", jp_4);
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
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}
	}

	@Override
	protected void setExceptionMessage(Exception e) {
		// TODO Auto-generated method stub

	}
}