package view;

import controller.DatabaseConnector;
import controller.Portion;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Tools.MyTools;

/**
 * Die Klasse Automat erbt von JFrame und dient der Anzeige des Kaufbildschirms.
 * Der Kunde kann mit Hilfe dieser Klasse also sehen, welche Produkte noch zu
 * welchen Mengen im Automaten vorhanden sind, was diese kosten und wann sie
 * ablaufen. Der Kunde hat mit Hilfe dieser Klasse die Moeglichkeit, eine
 * bestimmte Menge eines oder mehrerer Produkte auszuwaehlen und schliesslich zu
 * kaufen.
 * 
 */

@SuppressWarnings("serial")
public class Automat extends JFrame {

	/**
	 * Instanz der Klasse Font um die ‹berschrift durch die Schriftart hervorzuheben
	 */
	private Font headerfont = new Font("Arial", Font.BOLD, 20);

	/**
	 * Instanziieren dreier JPanels, um die Automatenbestandteile voneinander
	 * abzugrenzen
	 */
	private JPanel jp_mainPanel, jp_selectionPanel, jp_barPanel;

	/**
	 * Instanziieren zweier JLabels f√uer die UI-Beschreibung und der Berechnung des
	 * Gesamtbetrages der Bestellung
	 */
	private JLabel jlbl_desc;
	public static JLabel jlbl_sum;

	/**
	 * Instanziieren eines Kaufbuttons
	 */
	private JButton jb_buy;

	/**
	 * Instanziieren der ArrayList zum Abspeichern des Warenkorbs
	 * 
	 */
	public ArrayList<Panel_Selection> list_productSelection = new ArrayList<Panel_Selection>();

	/**
	 * speichert die Summe der Preise der ausgew‰hlten Produkte
	 */
	private double gesamtpreis;
	
	/**
	 * Erzeugen der JTable-Instanz inklusive Datenbankabfrage ueber die Klasse
	 * DatabaseConnector
	 */
	JTable jt_obtainableProducts;

	/**
	 * Erzeugt das Automaten-UI inklusive √úberschrift, entsprechender Tabelle,
	 * GesamtpreisLabel und KaufButton mit entsprechendem ActionListener.
	 */
	public Automat(JTable products) {
		this.jt_obtainableProducts = products;
		/**
		 * Try-Catch-Block zum Einlesen der Icon-URL F√§ngt eine IOException wenn das
		 * Iconbild, bzw. die dahinterstehende URL nicht gelesen werden konnte
		 */
		try {
			final BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e1) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}

		/**
		 * Hier werden Titel, Sichtbarkeit, Groesse, Position und Close-Operation des
		 * Automaten-Windows festgelegt
		 * 
		 */
		this.setTitle("K¸hlautomat");
		this.setVisible(true);
		this.setSize(800, 400);
		this.setLocation(50, 20);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		/**
		 * Containerinstanz wird als ContentPane gesetzt
		 */
		Container c = getContentPane();

		/**
		 * Das MainPanel wird mit dem FlowLayout erstellt und dem Container hinzugef√ºgt
		 */
		jp_mainPanel = new JPanel(new FlowLayout());
		c.add(jp_mainPanel);

		/**
		 * Das Beschreibungslabel wird mit dem zugeh√∂rigen String erstellt und ihre
		 * Schriftart auf die headerfont gesetzt
		 */
		jlbl_desc = new JLabel("Bitte waehlen Sie Ihre Produkte aus:");
		jlbl_desc.setFont(headerfont);

		/**
		 * Das SelectionPanel wird mit dem GridLayout mit unbegrenzten Zeilen und 1
		 * Spalte erstellt. Diesem wird die das Beschreibungslabel hinzugef√ºgt
		 */
		jp_selectionPanel = new JPanel(new GridLayout(0, 1));
		jp_selectionPanel.add(jlbl_desc);

		/**
		 * Das SelectionPanel wird dem MainPanel hinzugef√ºgt
		 */
		jp_mainPanel.add(jp_selectionPanel);

		/**
		 * Hinzuf¸gen der Portionen, Darstellung der Eigenschaften in Labels
		 */
		for (int row = 0; row < jt_obtainableProducts.getRowCount(); row++) {
			Portion portion = new Portion("" + jt_obtainableProducts.getValueAt(row, 0), // name
					"" + jt_obtainableProducts.getValueAt(row, 1), // portionen
					"" + jt_obtainableProducts.getValueAt(row, 2), // haltbar_bis
					"" + jt_obtainableProducts.getValueAt(row, 3), // kilopreis
					"" + jt_obtainableProducts.getValueAt(row, 4) // gewicht_portion
			);
			Panel_Selection productSelection = new Panel_Selection(portion, this);
			list_productSelection.add(productSelection); // Warenkorb
			jp_selectionPanel.add(productSelection);
		}

		/**
		 * Das BarPanel wird mit dem GridLayout mit 2 Zeilen und 1 Spalte erstellt.
		 */
		jp_barPanel = new JPanel(new GridLayout(2, 1));
		/**
		 * Anzeige der Gesamtsumme
		 */
		jlbl_sum = new JLabel("0.00Ä");
		jp_barPanel.add(jlbl_sum);

		/**
		 * Button zum Simulieren des Kaufs
		 */
		jb_buy = new JButton("Kaufen");
		jb_buy.setBackground(Color.white);

		/**
		 * Erstellen eines ActionListeners f√ºr den Kauf-Button
		 * 
		 */

		/**
		 * Dem Kauf-Button wird der ActionListener hinzugef√ºgt
		 */
		jb_buy.addActionListener(new ActionListener_Buy());

		/**
		 * Der Kauf-Button wird dem Bar-Panel hinzugef√ºgt und das Bar-Panel wiederrum
		 * dem Main-Panel.
		 */
		jp_barPanel.add(jb_buy);
		jp_mainPanel.add(jp_barPanel);

		/**
		 * Eine Funktion zur korrekten Anzeige des Automaten-UIs.
		 */
		this.revalidate();
	}

	/**
	 * @return gibt die Summe der Preise zurueck
	 */
	public double getGesamtpreis() {
		return gesamtpreis;
	}
	
	/**
	 * rechnet die einzelnen Preise der Produkte multipliziert mit der ausgew‰hlten Menge aus
	 */
	public void berechneGesamtpreis() {
		gesamtpreis = 0.00;
		for (Panel_Selection selection : list_productSelection) {
			double preis = selection.getPreis();
			gesamtpreis += preis;

			jlbl_sum.setText(MyTools.formatAsCurrency(gesamtpreis));
		}
	}
	
	/**
	 * Anzeige des Automaten-UIs inklusive der Automatenbestandstabelle
	 */
	public static void main(String[] args) {
			/**
			 * Konkatenieren des Strings, der das SQL-Select-Statement zum Auslesen der
			 * Produkte (und ihrer Daten), die sich im Automaten befinden, darstellt
			 */
		String cus_sql = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
					+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";

		new Automat(DatabaseConnector.executeDBQuery(cus_sql));
		}
	}
