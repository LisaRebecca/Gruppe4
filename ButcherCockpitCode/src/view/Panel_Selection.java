package view;

import controller.Portion;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;

@SuppressWarnings("serial")
public class Panel_Selection extends JPanel {
	/**
	 * Automat welcher dieses Panel enth�lt.
	 */
	Automat automat;
	/**
	 * Label f�r die ausgew�hlte Anzahl an Portionen.
	 */
	JLabel jlbl_amount;
	/**
	 * Der gesamte Preis f�r die ausgew�hlte Menge des Produkts.
	 */
	JLabel jlbl_preis;
	/**
	 * Das Produkt kann als einzelne {@link Portion} ausgew�hlt werden.
	 */
	Portion portion;
	/**
	 * Buttons zum Ver�ndern der ausgew�hlten Menge des Produktes.
	 */
	JButton jb_more, jb_less;

	/**
	 * Kontruktor, welcher alle Buttons erstellt und die Labels mit Initialwerten
	 * beschriftet.
	 * 
	 * @param portion das darzustellende Produkt als einzelne {@link Portion}
	 */
	public Panel_Selection(Portion portion, Automat automat) {
		this.portion = portion;
		this.automat = automat;

		this.setLayout(new GridLayout(1, 0));
		this.setBackground(Color.LIGHT_GRAY);

		/**
		 * Darstellen der Portion, welche zur Auswahl steht.
		 */
		this.add(new JLabel(portion.getName(), SwingConstants.LEFT));
		this.add(new JLabel("" + portion.getKilopreis() + " �/kg", SwingConstants.RIGHT));
//		this.add(new JLabel("  haltbar bis " + portion.haltbar, SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " Portionen � ", SwingConstants.RIGHT));
		this.add(new JLabel("" + this.portion.getPortionsgewichtGramm() + "g auf Lager", SwingConstants.LEFT));

		/**
		 * Ausgew�hlte Menge anzeigen
		 */
		jlbl_amount = new JLabel("0", SwingConstants.CENTER);
		jlbl_amount.setBackground(Color.white);
		jlbl_amount.setSize(20, 20);
		this.add(jlbl_amount);

		/**
		 * ButtonsListener, welcher die �nderungen der Menge steuert
		 */
		ActionListener bl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jb_source = (JButton) e.getSource();
				int amount = Integer.parseInt(jlbl_amount.getText());
				if (jb_source.getText().equals("+")) {
					amount++;

				} else if (jb_source.getText().equals("-")) {
					amount--;
				}

				jlbl_amount.setText("" + amount);
				aktualisierePreise();
				automat.berechneGesamtpreis();

				if (amount <= 0) {
					jb_less.setVisible(false);
				} else if (0 < amount & amount < portion.getLagermenge()) {
					jb_less.setVisible(true);
					jb_more.setVisible(true);
				} else if (amount >= portion.getLagermenge()) {
					jb_more.setVisible(false);
				}

			}
		};

		/**
		 * Button zum Erh�hen der Menge
		 */
		jb_more = new JButton("+");
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		this.add(jb_more);

		/**
		 * Button zum Vermindern der Menge
		 */
		jb_less = new JButton("-");
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		/**
		 * Anzahl ist zu Anfang 0, der Nutzer soll die Anzahl nur erh�hen k�nnen.
		 */
		jb_less.setVisible(false);
		this.add(jb_less);

		jlbl_preis = new JLabel("0", SwingConstants.LEFT);
		this.add(jlbl_preis);
	}

	/**
	 * der gesamte Preis der ausgew�lten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		jlbl_preis.setText("" + formatter.format(portion.getPortionspreis() * Integer.parseInt(jlbl_amount.getText()))); // �
																															// anf�gen
	}

	public double getPreis() {
		return 0.00; // � abschneiden
	}
}