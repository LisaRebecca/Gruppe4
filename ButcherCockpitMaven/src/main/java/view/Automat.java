package view;

import data.Database;

import errorhandling.AbstractButcherException;
import errorhandling.ExceptionHandler;
import errorhandling.PaymentButcherException;
import errorhandling.SQLButcherException;
import payment.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
class Automat extends DefaultFrame implements PropertyChangeListener {

	private final ResourceBundle language;

	private JPanel jp_mainPanel;
	private JPanel jp_selectionPanel;
	private JPanel jp_buyPanel;

	private JLabel jlbl_title;
	private JLabel jlbl_total;

	private JButton jb_buy;

	/**
	 * Warenkorb, enth lt alle ausgew hlten Produkte in Form von
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
	 * @param resultSet die verf gbaren Produkte
	 * @throws SQLException
	 */

	public Automat() throws AbstractButcherException {
		super("K¸hlautomat", 800, 400);

		this.language = ResourceBundle.getBundle("i18n/automat/automat_en");


		createBuyButton();
		createBuyPanel();
		createSelectionPanel();
		createMainPanel();

		// Nach dem Einf gen der Elemente wird der JFrame noch einmal aktualisiert.
		this.revalidate();
	}

	/**
	 * Button zum Kaufen wird erstellt
	 */
	public void createBuyButton() {
		jb_buy = new JButton(this.language.getString("buy_btn"));
		jb_buy.setBackground(Color.white);
		jb_buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae){
				try {
					buyButtonPressed();
				} catch (AbstractButcherException e) {
					ExceptionHandler.get().showException(e);
				}
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
	 * Panel, welches die  berschrift und alle ausw hlbaren Produkte beinhaltet
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

	public void addPanel(Panel_Selection ps) {
		warenkorb.add(ps);
		jp_selectionPanel.add(ps);
	}

	private void buyButtonPressed() throws AbstractButcherException{
		// Zuerst wird der Kunde nach Best‰tigung gefragt.
		String[] options = { this.language.getString("option_yes"), this.language.getString("option_no") };
		int eingabe = JOptionPane.showOptionDialog(null, this.language.getString("buy_question"),
				this.language.getString("confirmation"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);


		// Der Dialog schlie√üt sich, der Kunde kann weiter einkaufen
		if (eingabe == 1) {
		}
		// Nur falls er den Vorgang abschlie√üen will erscheint ein neuer Dialog.
		if (eingabe == 0) {
			try {
				JOptionPane.showMessageDialog(null, Payment.get().processPurchase(), this.language.getString("thanks"),
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e2) {
				throw new PaymentButcherException(e2);
			
			}

			// generieren eines Universally Unique Identifiers f√ºr jeden Einkauf
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

			Database.get().executeDBInsert(
					"INSERT INTO Verkaeufe( verkauf_id, datum, uhrzeit, gesamtpreis) VALUES ( UNHEX('" + uuid
							+ "'), '" + sql_date + "', '" + sql_time + "', " + gesamtpreis + ");");
			
			

			// Der Automat wird geschlossen, der Einkauf ist beendet
			System.exit(0);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		berechneGesamtpreis();
	}
}

