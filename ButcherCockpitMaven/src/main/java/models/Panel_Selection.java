package models;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Tools.MyTools;
import view.Automat;

@SuppressWarnings("serial")
public class Panel_Selection extends JPanel {
	/**
	 * urspr�nglich sind keine Produkte ausgewählt, Menge = 0
	 */
	public static final int initialAmount = 0;
	/**
	 * Automat welcher dieses Panel enthält.
	 */
	private Automat automat;
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
	 * ButtonsListener, welcher die Aenderungen der Menge steuert
	 */
//	ActionListener bl = new ActionListener_Amount();
	
	/**
	 * Kontruktor, welcher alle Buttons erstellt und die Labels mit Initialwerten
	 * beschriftet.
	 * 
	 * @param portion das darzustellende Produkt als einzelne {@link Portion}
	 * @return 
	 */
	
	public Panel_Selection(Portion portion, Automat automat) {
		this.setPortion(portion);
		this.automat = automat;

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
		this.add(new JLabel("" + portion.getKilopreis() + " �/kg", SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " Portionen � ", SwingConstants.RIGHT));
		this.add(new JLabel("" + this.getPortion().getPortionsgewichtGramm() + "g auf Lager", SwingConstants.LEFT));
	}
	
	/**
	 * Ausgewaehlte Menge anzeigen
	 */
	public void setAmountInfo() {
		setJlbl_amount(new JLabel("" + initialAmount, SwingConstants.CENTER));
		getJlbl_amount().setBackground(Color.white);
		this.add(getJlbl_amount());
	}
	
	/**
	 * Button zum Erhoehen der Menge
	 */
	public void initializePlusButton() {
		setJb_more(new JButton("+"));
		getJb_more().setBackground(Color.white);
		getJb_more().addActionListener(e -> {
			jlbl_amount.setText(""+(getAmount()+1));
			aktualisierePreise();
			refreshButtonVisibility();
			automat.berechneGesamtpreis();
		});
		this.add(getJb_more());
	}
	
	/**
	 * Button zum Vermindern der Menge
	 */
	public void initializeMinusButton() {
		setJb_less(new JButton("-"));
		getJb_less().setBackground(Color.white);
		getJb_less().addActionListener(e -> {
			jlbl_amount.setText(""+(getAmount()-1));
			aktualisierePreise();
			refreshButtonVisibility();
			automat.berechneGesamtpreis();
		});
		//Anzahl ist zu Anfang 0, der Nutzer soll die Anzahl nur erhoehen koennen.
		getJb_less().setVisible(false);
		this.add(getJb_less());
	}
	
	public void setPreisLabel() {
		jlbl_preis = new JLabel(MyTools.formatAsCurrency(0), SwingConstants.RIGHT);
		this.add(jlbl_preis);
	}
	/**
	 * der gesamte Preis der ausgewaelten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		jlbl_preis.setText("" + MyTools.formatAsCurrency((getPortion().getPortionspreis() * getAmount())));
	}

	public Automat getAutomat() {
		return this.automat;
	}

	/**
	 * @return Preis als reine Kommazahl
	 */
	public double getPreis() {
		String preis = jlbl_preis.getText().replace(',', '.');
		int index = preis.indexOf("€");
		if (index == -1) {
		} else {
			preis = preis.substring(0, index);
		}
		return Double.parseDouble(preis);
	}

	/**
	 * @return ausgew�hlte Menge als nat�rliche Zahl
	 */
	public int getAmount() {
		return Integer.parseInt(getJlbl_amount().getText());
	}

	/**
	 * @return MengenJLabel
	 */
	public JLabel getJlbl_amount() {
		return jlbl_amount;
	}

	/**
	 * @param jlbl_amount Zahl, auf die das MengenJLabel gesetzt werden soll
	 */
	public void setJlbl_amount(JLabel jlbl_amount) {
		this.jlbl_amount = jlbl_amount;
	}

	public Portion getPortion() {
		return portion;
	}

	public void setPortion(Portion portion) {
		this.portion = portion;
	}

	public JButton getJb_less() {
		return jb_less;
	}

	public void setJb_less(JButton jb_less) {
		this.jb_less = jb_less;
	}

	public JButton getJb_more() {
		return jb_more;
	}

	public void setJb_more(JButton jb_more) {
		this.jb_more = jb_more;
	}
	
	public void refreshButtonVisibility() {
		// Ein-/Ausblenden der Buttons je nachdem ob weitere Portionen des Produktes
		// vorhanden sind. Verhindert auch die Auswahl einer negativen Anzahl.
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
}