package view;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Formatter;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Tools.Currency_Symbol;

@SuppressWarnings("serial")
public class Panel_Selection extends JPanel {

	/**
	 * ResourceBundle zum Auslesen der Texte, abhängig von der festgelegten Sprache
	 */
	private final ResourceBundle language;
	/**
	 * ursprünglich sind keine Produkte ausgewählt, Menge = 0
	 */
	public static final int initialAmount = 0;

	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	/**
	 * Label fuer die ausgewählte Anzahl an Portionen.
	 */
	private JLabel jlbl_amount;
	/**
	 * Der gesamte Preis fuer die ausgewaehlte Menge des Produkts.
	 */
	private JLabel jlbl_preis;
	/**
	 * Das Produkt kann als einzelne {@link Portion} ausgewaehlt werden.
	 */
	private Portion portion;
	/**
	 * Buttons zum Veraendern der ausgewaehlten Menge des Produktes.
	 */
	private JButton jb_more, jb_less;

	/**
	 * Kontruktor, welcher alle Buttons erstellt und die Labels mit Initialwerten
	 * beschriftet.
	 * 
	 * @param portion das darzustellende Produkt als einzelne {@link Portion}
	 */

	public Panel_Selection(Portion portion) {

		this.language = ResourceBundle.getBundle("i18n/panel_selection/panel_selection_en");

		this.setPortion(portion);

		this.setLayout(new GridLayout(1, 0));
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(Color.orange, 1));

		setPortionInfo();
		setAmountInfo();

		initializePlusButton();
		initializeMinusButton();

		setPreisLabel();
	}

	/**
	 * Darstellen der Portion, welche zur Auswahl steht.
	 */
	public void setPortionInfo() {
		this.add(new JLabel(portion.getName(), SwingConstants.LEFT));
		this.add(new JLabel("" + portion.getKilopreis() + this.language.getString("unit"), SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " " + this.language.getString("portions") + " ",
				SwingConstants.RIGHT));
		this.add(new JLabel("" + this.getPortion().getPortionsgewichtGramm() + this.language.getString("grams"),
				SwingConstants.LEFT));

	}

	/**
	 * Ausgewaehlte Menge anzeigen
	 */
	public void setAmountInfo() {
		jlbl_amount = new JLabel("" + initialAmount, SwingConstants.CENTER);
		jlbl_amount.setBackground(Color.white);
		this.add(jlbl_amount);
	}

	/**
	 * Button zum Erhoehen der Menge
	 */
	void initializePlusButton() {
		jb_more = new JButton("+");
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(e -> {
			jlbl_amount.setText("" + (getAmount() + 1));
			aktualisierePreis();
			refreshButtonVisibility();
		});
		this.add(jb_more);
	}

	/**
	 * Button zum Vermindern der Menge
	 */
	void initializeMinusButton() {
		jb_less = new JButton("-");
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(e -> {
			jlbl_amount.setText("" + (getAmount() - 1));
			aktualisierePreis();
			refreshButtonVisibility();
		});
		// Anzahl ist zu Anfang 0, der Nutzer soll die Anzahl nur erhoehen koennen.
		jb_less.setVisible(false);
		this.add(jb_less);
	}

	/**
	 * Label zur Preisanzeige
	 */
	public void setPreisLabel() {
		jlbl_preis = new JLabel(Tools.Formatter.formatAsCurrency(0), SwingConstants.RIGHT);
		this.add(jlbl_preis);
	}

	/**
	 * der gesamte Preis der ausgewaelten Portionen wird aktualisiert
	 */
	void aktualisierePreis() {
		double new_price = (getPortion().getPortionspreis() * getAmount());
		double old_price = getPreis();
		jlbl_preis.setText("" + Tools.Formatter.formatAsCurrency(new_price));
		changes.firePropertyChange("preis", old_price, new_price);
	}

	/**
	 * @return Preis als reine Kommazahl
	 */
	public double getPreis() {
		String preis = jlbl_preis.getText().replace(',', '.');

		int index = preis.indexOf(Currency_Symbol.getCurrency_Symbol());

		if (index == -1) {
		} else {
			preis = preis.substring(0, index);
		}
		return Double.parseDouble(preis);
	}

	/**
	 * @return ausgewählte Menge als natürliche Zahl
	 */
	public int getAmount() {
		return Integer.parseInt(jlbl_amount.getText());
	}

	public Portion getPortion() {
		return portion;
	}

	public void setPortion(Portion portion) {
		this.portion = portion;
	}

	/**
	 * Ein-/Ausblenden der Buttons je nachdem ob weitere Portionen des Produktes
	 * vorhanden sind. Verhindert auch die Auswahl einer negativen Anzahl.
	 */
	void refreshButtonVisibility() {
		Panel_Selection ps = this;
		if (getAmount() <= 0) {
			jb_less.setVisible(false);
		} else if (0 < getAmount() & getAmount() < ps.getPortion().getLagermenge()) {
			jb_less.setVisible(true);
			jb_more.setVisible(true);
		} else if (getAmount() >= ps.getPortion().getLagermenge()) {
			jb_more.setVisible(false);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}

}