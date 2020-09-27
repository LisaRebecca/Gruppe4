package view;

import controller.Database;
import controller.Portion;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import java.util.ArrayList;

import javax.swing.*;

import Tools.MyTools;

/**
 * Die Klasse Automat dient der Anzeige des Kaufbildschirms. Der Kunde kann mit
 * Hilfe dieser Klasse sehen, welche Produkte zu welchen Mengen im Automaten
 * vorhanden sind, was diese kosten und wann sie ablaufen. Der Kunde hat mit
 * Hilfe dieser Klasse die Moeglichkeit, eines oder mehrerer Produkte
 * auszuwaehlen und zu kaufen.
 * 
 */

@SuppressWarnings("serial")
public class Automat extends JFrame {

	/**
	 * Schriftart f�r die �berschrift
	 */
	private Font headerfont = new Font("Arial", Font.BOLD, 20);

	private JPanel jp_mainPanel;
	private JPanel jp_selectionPanel;
	private JPanel jp_buyPanel;

	private JLabel jlbl_title;
	private JLabel jlbl_total;

	private JButton jb_buy;

	/**
	 * Warenkorb, enth�lt alle ausgew�hlten Produkte in Form von
	 * {@link Panel_Selection}
	 */
	public ArrayList<Panel_Selection> list_productSelection = new ArrayList<Panel_Selection>();

	/**
	 * Gesamtpreis dieses Einkaufs
	 */
	private double gesamtpreis;

	/**
	 * Tabelle der verf�gbaren Produkte
	 */
	JTable jt_obtainableProducts;

	private Container c = getContentPane();
	
	
	/**
	 * Erzeugen des Automaten-UI inklusive Ueberschrift, Tabelle, GesamtpreisLabel
	 * und KaufButton mit ActionListener.
	 * 
	 * @param products die verf�gbaren Produkte
	 */
	public Automat(JTable products) {
		this.jt_obtainableProducts = products;

		/**
		 * ------------------------------- Konfiguration JFrame -------------------------------
		 */
		/**
		 * Hier werden Titel, Sichtbarkeit, Groesse, Position und Close-Operation des
		 * Automaten-Windows festgelegt
		 */
		this.setTitle("Kühlautomat");
		this.setVisible(true);
		this.setSize(800, 400);
		this.setLocation(50, 20);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIcon();
		
		createBuyButton();
		createBuyPanel();
		
		createSelectionPanel();
		// Füllen des Auswahlpanels mit den Produkten
		loadProductsFromTable();
		
		createMainPanel();

		//Nach dem Einf�gen der Elemente wird der JFrame noch einmal aktualisiert.
		this.revalidate();
	}

	/**
	 * Button zum Kaufen wird erstellt
	 */
	public void createBuyButton() {
		jb_buy = new JButton("Kaufen");
		jb_buy.setBackground(Color.white);
		jb_buy.addActionListener(new ActionListener_Buy());
	}
	
	/**
	 * Panel zur Anzeige der Gesamtsumme und des Kaufenbuttons
	 */
	public void createBuyPanel() {
		jp_buyPanel = new JPanel(new GridLayout(2, 1));
		
		//Anzeige der Gesamtsumme
		jlbl_total = new JLabel();
		berechneGesamtpreis();
		
		jp_buyPanel.add(jlbl_total);
		jp_buyPanel.add(jb_buy);
	}
	
	/**
	 * Panel, welches die �berschrift und alle ausw�hlbaren Produkte beinhaltet
	 */
	public void createSelectionPanel() {
		jlbl_title = new JLabel("Bitte waehlen Sie Ihre Produkte aus:");
		jlbl_title.setFont(headerfont);
		jp_selectionPanel = new JPanel(new GridLayout(0, 1));
		jp_selectionPanel.add(jlbl_title);
	}
	
	/**
	 * Erzeugen des Hintergrund-Panels
	 */
	public void createMainPanel() {
		jp_mainPanel = new JPanel(new FlowLayout());
		c.add(jp_mainPanel);
		jp_mainPanel.add(jp_selectionPanel);
		jp_mainPanel.add(jp_buyPanel);
	}
	/**
	 * @return Gesamtpreise dieses Einkaufs
	 */
	public double getGesamtpreis() {
		return gesamtpreis;
	}

	/**
	 * Berechnung des Gesamtpreises dieses Einkaufs, aktualisieren der Anzeige
	 */
	public void berechneGesamtpreis() {
		gesamtpreis = 0.00;
		for (Panel_Selection selection : list_productSelection) {
			gesamtpreis += selection.getPreis();
		}
		jlbl_total.setText(MyTools.formatAsCurrency(gesamtpreis));
	}

	/**
	 * Setzen des Fenster-Icons. <br>
	 * Hinweis: Falls das Bild nicht gesetzt werden kann erscheint lediglich eine
	 * Warnung, da das Bild nicht nötig für das Funktionieren der Anwendung ist.
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

	/**
	 * Hinzufügen der Portionen, Darstellung der Eigenschaften in Labels
	 */
	private void loadProductsFromTable() {
		for (int row = 0; row < jt_obtainableProducts.getRowCount(); row++) {
			Portion portion = new Portion();
			portion.setName("" + jt_obtainableProducts.getValueAt(row, 0));
			portion.setLagermenge("" + jt_obtainableProducts.getValueAt(row, 1));
			portion.setHaltbarBis("" + jt_obtainableProducts.getValueAt(row, 2));
			portion.setKilopreis("" + jt_obtainableProducts.getValueAt(row, 3));
			portion.setPortionsgewichtKG("" + jt_obtainableProducts.getValueAt(row, 4));

			Panel_Selection productSelection = new Panel_Selection(portion, this);
			
			// Hinzuf�gen zum Warenkorb
			list_productSelection.add(productSelection);
			jp_selectionPanel.add(productSelection);
		}
	}

	/**
	 * Anzeige des Automaten-UIs inklusive der Automatenbestandstabelle
	 */
	public static void main(String[] args) {
		
		 // Konkatenieren des Strings, der das SQL-Select-Statement zum Auslesen der
		 // Produkte (und ihrer Daten), die sich im Automaten befinden, darstellt
		String select = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";
		JTable products = null;
		try {
			products = Database.get().executeDBQuery(select);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Automat(products);
	}
}
