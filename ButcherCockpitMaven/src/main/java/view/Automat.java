package view;

import errorhandling.*;
import payment.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.*;

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

	/**
	 * ResourceBundle zum Auslesen der Texte, abhängig von der festgelegten Sprache
	 */
	private final ResourceBundle language;

	private JPanel jp_mainPanel;
	private JPanel jp_selectionPanel;
	private JPanel jp_buyPanel;

	private JLabel jlbl_title;
	private JLabel jlbl_total;

	private JButton jb_buy;

	/**
	 * Warenkorb, enthält alle ausgewählten Produkte in Form von
	 * {@link Panel_Selection}
	 */
	private ArrayList<Panel_Selection> warenkorb = new ArrayList<Panel_Selection>();

	/**
	 * Gesamtpreis dieses Einkaufs
	 */
	private double gesamtpreis;

	/**
	 * Erzeugen des Automaten-UI inklusive Titel und Panels mit Produktauswahl-Tabelle, Gesamtpreisanzeige
	 * und KaufButton mit ActionListener.
	 */
	public Automat() throws AbstractButcherException {
		//internationalisierter Titel und Maße des JFrames
		super(ResourceBundle.getBundle("i18n/automat/automat_en").getString("title"), 800, 400);

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
	void createBuyButton() {
		jb_buy = new JButton(this.language.getString("buy_btn"));
		jb_buy.setBackground(Color.white);
		jb_buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				buyButtonPressed();
			}
		});
	}

	/**
	 * Panel zur Anzeige der Gesamtsumme und des Kaufenbuttons
	 */
	void createBuyPanel() {
		jp_buyPanel = new JPanel(new GridLayout(2, 1));

		// Anzeige der Gesamtsumme
		jlbl_total = new JLabel();
		berechneGesamtpreis();

		jp_buyPanel.add(jlbl_total);
		jp_buyPanel.add(jb_buy);
	}

	/**
	 * Panel, welches die Überschrift und alle auswählbaren Produkte beinhaltet
	 */
	void createSelectionPanel() {
		jlbl_title = new JLabel(this.language.getString("product_selection"));
		jlbl_title.setFont(headerfont);
		jp_selectionPanel = new JPanel(new GridLayout(0, 1));
		jp_selectionPanel.add(jlbl_title);
	}

	/**
	 * Erzeugen des Hintergrund-Panels
	 */
	void createMainPanel() {
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
	void berechneGesamtpreis() {
		gesamtpreis = 0.00;
		for (Panel_Selection selection : warenkorb) {
			gesamtpreis += selection.getPreis();
		}
		jlbl_total.setText(Tools.Formatter.formatAsCurrency(gesamtpreis));
	}

	public void addPanel(Panel_Selection ps) {
		warenkorb.add(ps);
		jp_selectionPanel.add(ps);
	}

	private void buyButtonPressed() {
		// Zuerst wird der Kunde nach Bestätigung gefragt.
		String[] options = { this.language.getString("option_yes"), this.language.getString("option_no") };
		int eingabe = JOptionPane.showOptionDialog(null, this.language.getString("buy_question"),
				this.language.getString("confirmation"), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);

		if (eingabe == 1) {
			// Der Dialog schließt sich, der Kunde kann weiter einkaufen
		}
		// Nur falls er den Vorgang abschließen will erscheint ein neuer Dialog.
		if (eingabe == 0) {
					try {
						JOptionPane.showMessageDialog(null, Payment.get().processPurchase(getGesamtpreis()),
								this.language.getString("thanks"), JOptionPane.INFORMATION_MESSAGE);
					} catch (AbstractButcherException e1) {
						ExceptionHandler.get().showException(e1);
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
