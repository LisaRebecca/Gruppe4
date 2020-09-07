package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/** Die Klasse Automat erbt von JFrame und dient der Anzeige des Kaufbildschirms. 
 * Der Kunde kann mit Hilfe dieser Klasse also sehen, welche Produkte noch zu welchen Mengen im Automaten vorhanden sind,
 * was diese kosten und wann sie ablaufen. Der Kunde hat mit Hilfe dieser Klasse die M�glichkeit, eine bestimmte Menge eines
 * oder mehrerer Produkte auszuw�hlen und schlie�lich zu kaufen.
 * 
 */

public class Automat extends JFrame {

	/** Instanz der Klasse Font um die Titelschriftart auf Arial, Fett und Schriftgr��e 20 zu setzen
	 */
	Font headerfont = new Font("Arial", Font.BOLD, 20);
	
	/** Instanziieren dreier JPanels, um die Automatenbestandteile voneinander abzugrenzen
	 */
	JPanel jp_mainPanel, jp_selectionPanel, jp_barPanel;
	
	/** Instanziieren zweier JLabels f�r die UI-Beschreibung und der Berechnung des Gesamtbetrages der Bestellung
	 */
	JLabel jlbl_desc, jlbl_sum;
	
	/** Instanziieren eines Kaufbuttons
	 */
	JButton jb_buy;
	
	/** Rahmeneinstellungen f�r die sp�tere ProductSelection-Instanz/den Warenkorb
	 */
	Border border = new LineBorder(Color.orange,1);
	
	/** Instanziieren der ArrayList zum Abspeichern des Warenkorbs
	 */
	ArrayList<Produktauswahl> list_productSelection = new ArrayList<Produktauswahl>();

	/** Erzeugt das Automaten-UI inklusive �berschrift, entsprechender Tabelle, GesamtpreisLabel und KaufButton 
	 * mit entsprechendem ActionListener. 
	 */
	public Automat() {

		/** Try-Catch-Block zum Einlesen der Icon-URL 
		 * F�ngt eine IOException wenn das Iconbild, bzw. die dahinterstehende URL nicht gelesen werden konnte
		 */
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e1) {
			System.err.println("Icon des Automaten konnte nicht geladen werden.");
		}
		
		/** Hier werden Titel, Sichtbarkeit, Gr��e, Position und Close-Operation des Automaten-Windows festgelegt
		 */
		this.setTitle("K�hlautomat");
		this.setVisible(true);
		this.setSize(1400, 400);
		this.setLocation(50, 20);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		/** Containerinstanz wird als ContentPane gesetzt 
		 */
		Container c = getContentPane();
		
		/** Das MainPanel wird mit dem FlowLayout erstellt und dem Container hinzugef�gt
		 */
		jp_mainPanel = new JPanel(new FlowLayout());
		c.add(jp_mainPanel);

		/** Das Beschreibungslabel wird mit dem zugeh�rigen String erstellt und ihre Schriftart auf die headerfont gesetzt
		 */
		jlbl_desc = new JLabel("Bitte w�hlen Sie Ihre Produkte aus:");
		jlbl_desc.setFont(headerfont);

		/** Das SelectionPanel wird mit dem GridLayout mit unbegrenzten Zeilen und 1 Spalte erstellt.
		 * Diesem wird die das Beschreibungslabel hinzugef�gt
		 */
		jp_selectionPanel = new JPanel(new GridLayout(0, 1));
		jp_selectionPanel.add(jlbl_desc);
		
		/** Das SelectionPanel wird dem MainPanel hinzugef�gt
		 */
		jp_mainPanel.add(jp_selectionPanel);

		/** Konkatenieren des Strings, der das SQL-Select-Statement zum Auslesen der Produkte (und ihrer Daten), die sich 
		 * im Automaten befinden, darstellt
		 */
		String cus_sql = "select name, portionen, haltbar_bis, kilopreis, gewicht_portion from lagerbestand "
				+ "left join produkte on lagerbestand.produkt = produkte.produkt_id " + "WHERE lagerort='automat1';";
		
		/** Erzeugen der JTable-Instanz inklusive Datenbankabfrage �ber die Klasse DatabaseConnector
		 */
		JTable jt_obtainableProducts = DatabaseConnector.executeDBQuery(cus_sql);

		/** 
		 */
		for (int row = 0; row < jt_obtainableProducts.getRowCount(); row++) {
			Portion portion = new Portion("" + jt_obtainableProducts.getValueAt(row, 0),
					"" + jt_obtainableProducts.getValueAt(row, 1), "" + jt_obtainableProducts.getValueAt(row, 2),
					"" + jt_obtainableProducts.getValueAt(row, 3), "" + jt_obtainableProducts.getValueAt(row, 4));
			Produktauswahl productSelection = new Produktauswahl(portion);
			productSelection.setBorder(border);
			list_productSelection.add(productSelection); // Warenkorb
			jp_selectionPanel.add(productSelection);
		}

		/** Das BarPanel wird mit dem GridLayout mit 2 Zeilen und 1 Spalte erstellt.
		 */
		jp_barPanel = new JPanel(new GridLayout(2, 1));

		/** Das Gesamtsummenlabel, bekommt den entsprechenden String zugewiesen und wird dem BarPanel hinzugef�gt.
		 */
		jlbl_sum = new JLabel("Gesamtpreis: ____�");
		jp_barPanel.add(jlbl_sum);

		/** Der Kaufen-Button bekommt einen entsprechenden String zugewiesen und seine Farbe wird auf wei� gesetzt.
		 */
		jb_buy = new JButton("Kaufen");
		jb_buy.setBackground(Color.white);
		
		/** Erstellen eines ActionListeners f�r den Kauf-Button
		 */
		ActionListener listener_buy_btn = new ActionListener() {

			/** Wenn der Button gedr�ckt wird, sollen eine OptionPane angezeigt werden, mit zwei Buttons
			 * die jeweils die Texte "Ja, bezahlen" oder "Nein, zur�ck" enthalten. Sowie dar�ber die Frage:
			 * "M�chten Sie den Kaufvorgang abschlie�en und bezahlen".
			 * Wird der Ja-Button gedr�ckt, soll der Benutzer eine Best�tigung �ber die Bestellung erhalten.
			 * Wird der Nein-Button gedr�ckt, soll das Fenster geschlossen werden. 
			 * 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Ja, bezahlen", "Nein, zur�ck" };
				int eingabe = JOptionPane.showOptionDialog(null,
						"M�chten Sie den Kaufvorgang abschlie�en und bezahlen?", "Best�tigung",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(eingabe);
				if (eingabe == 0) {
					JOptionPane.showMessageDialog(null,
							"Danke f�r Ihren Einkauf, der Kassenbetrag wurde von ihrer Gutscheinkarte abgezogen.",
							"Danke!", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}

			}
		};
		
		/** Dem Kauf-Button wird der ActionListener hinzugef�gt
		 */
		jb_buy.addActionListener(listener_buy_btn);
		
		/** Der Kauf-Button wird dem Bar-Panel hinzugef�gt und das Bar-Panel wiederrum dem Main-Panel.
		 */
		jp_barPanel.add(jb_buy);
		jp_mainPanel.add(jp_barPanel);

		/** Eine Funktion zur korrekten Anzeige des Automaten-UIs. 
		 */
		this.revalidate();
	}

	/**
	 * In der Main Methode wird eine Instanz der Automaten-Klasse erzeugt. 
	 */
	public static void main(String[] args) {
		new Automat();
	}
}