package view;

import data.Database;
import errorhandling.AbstractButcherException;
import payment.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.sound.sampled.Port;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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
<<<<<<< Upstream, based on master
class Automat extends DefaultFrame implements PropertyChangeListener{
=======
public class Automat extends DefaultFrame {
	
	private final ResourceBundle language;
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.

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
	public ArrayList<Panel_Selection> warenkorb = new ArrayList<Panel_Selection>();

	/**
	 * Gesamtpreis dieses Einkaufs
	 */
	private double gesamtpreis;


	/**
	 * Erzeugen des Automaten-UI inklusive Ueberschrift, Tabelle, GesamtpreisLabel
	 * und KaufButton mit ActionListener.
	 * 
	 * @param resultSet die verf�gbaren Produkte
	 * @throws SQLException 
	 */
<<<<<<< Upstream, based on master
	public Automat() throws SQLException {
		super("Kühlautomat", 800, 400);
=======
	public Automat() {
		
		this.language = ResourceBundle.getBundle("i18n/automat/automat_de");
		
		super(this.language.getString("title"), 800, 400);
		this.jt_obtainableProducts = this.readProductsFormDB();
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.

		createBuyButton();
		createBuyPanel();
		createSelectionPanel();
		createMainPanel();

		// Nach dem Einf�gen der Elemente wird der JFrame noch einmal aktualisiert.
		this.revalidate();
	}

	/**
	 * Button zum Kaufen wird erstellt
	 */
	public void createBuyButton() {
		jb_buy = new JButton(this.language.getString("buy_btn"));
		jb_buy.setBackground(Color.white);
		jb_buy.addActionListener(new ActionListener() {

<<<<<<< Upstream, based on master
			@Override
			public void actionPerformed(ActionEvent e) {
				buyButtonPressed();
=======
			// Zuerst wird der Kunde nach Bestätigung gefragt.
			String[] options = { this.language.getString("option_yes"), this.language.getString("option_no") };
			int eingabe = JOptionPane.showOptionDialog(null, this.language.getString("buy_question"),
					this.language.getString("confirmation"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			// Der Dialog schließt sich, der Kunde kann weiter einkaufen
			if (eingabe == 1) {
			}
			// Nur falls er den Vorgang abschließen will erscheint ein neuer Dialog.
			if (eingabe == 0) {
				try {
					JOptionPane.showMessageDialog(null,
							Payment.get().processPurchase(),
							this.language.getString("thanks"), JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// generieren eines Universally Unique Identifiers für jeden Einkauf
				String uuid = UUID.randomUUID().toString();
				uuid = uuid.replace("-", "");

				// Speichern des aktuellen Zeitstempels
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String sql_date = simpleDateFormat.format(date);

				Date time = new Date();
				SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
				String sql_time = simpleTimeFormat.format(time);

				// Der Einkauf wird als Statistik in der Datenbank hinterlegt.
				
					try {
						Database.get().executeDBInsert(
								"INSERT INTO Verkaeufe( verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( UNHEX('" + uuid
										+ "'), '" + sql_date + "', '" + sql_time + "', " + gesamtpreis + ");");
					} catch (AbstractButcherException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				

				// Der Automat wird geschlossen, der Einkauf ist beendet
				System.exit(0);
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.
			}
		});
	}

	/**
	 * Panel zur Anzeige der Gesamtsumme und des Kaufenbuttons
	 */
	public void createBuyPanel() {
		jp_buyPanel = new JPanel(new GridLayout(2, 1));

		// Anzeige der Gesamtsumme
		jlbl_total = new JLabel();
		berechneGesamtpreis();

		jp_buyPanel.add(jlbl_total);
		jp_buyPanel.add(jb_buy);
	}

	/**
	 * Panel, welches die �berschrift und alle ausw�hlbaren Produkte beinhaltet
	 */
	public void createSelectionPanel() {
		jlbl_title = new JLabel(this.language.getString("product_selection"));
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
		for (Panel_Selection selection : warenkorb) {
			gesamtpreis += selection.getPreis();
		}
		jlbl_total.setText(MyTools.formatAsCurrency(gesamtpreis));
	}

<<<<<<< Upstream, based on master
	public void addPanel(Panel_Selection ps) {
		warenkorb.add(ps);
		jp_selectionPanel.add(ps);
=======
	@Override
	protected void setIcon() {
		try {
			BufferedImage image = ImageIO.read(new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQuzBtedlLeHnfd8uGFz57BYsRIej7Op8mJLA&usqp=CAU"));
			this.setIconImage(image);
		} catch (IOException e) {
			System.err.println(this.language.getString("icon_error"));
		}
>>>>>>> daf20bb Resource Language Pakete zu allen Klassen in denen Strings beschrieben werden, die später im Frontend zu sehen sind, sowie entsprechende Anpassungen in den Klassen.
	}
	private void buyButtonPressed() {
		// Zuerst wird der Kunde nach Bestätigung gefragt.
		String[] options = { "Ja, bezahlen", "Nein, zurueck" };
		int eingabe = JOptionPane.showOptionDialog(null, "Moechten Sie den Kaufvorgang abschliessen und bezahlen?",
				"Bestätigung", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		// Der Dialog schließt sich, der Kunde kann weiter einkaufen
		if (eingabe == 1) {
		}
		// Nur falls er den Vorgang abschließen will erscheint ein neuer Dialog.
		if (eingabe == 0) {
			try {
				JOptionPane.showMessageDialog(null, Payment.get().processPurchase(), "Danke!",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (HeadlessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// generieren eines Universally Unique Identifiers für jeden Einkauf
			String uuid = UUID.randomUUID().toString();
			uuid = uuid.replace("-", "");

			// Speichern des aktuellen Zeitstempels
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sql_date = simpleDateFormat.format(date);

			Date time = new Date();
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
			String sql_time = simpleTimeFormat.format(time);

			// Der Einkauf wird als Statistik in der Datenbank hinterlegt.

			try {
				Database.get().executeDBInsert(
						"INSERT INTO Verkaeufe( verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( UNHEX('" + uuid
								+ "'), '" + sql_date + "', '" + sql_time + "', " + gesamtpreis + ");");
			} catch (AbstractButcherException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Der Automat wird geschlossen, der Einkauf ist beendet
			System.exit(0);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		berechneGesamtpreis();		
	}
}