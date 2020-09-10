package view;
import controller.Portion;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Panel_Selection extends JPanel {
	/**
	 * Referenz auf den Automat welcher dieses Panel enthält.
	 */
	Automat automat;
	/**
	 * Label für die ausgewählte Anzahl an Portionen.
	 */
	JLabel jlbl_amount;
	/**
	 * Der gesamte Preis für die ausgewählte Menge des Produkt.
	 */
	JLabel jlbl_preis;
	/**
	 * Das Produkt, welches in dieser Produktauswahl dargestellt wird ist eine
	 * Portion.
	 */
	Portion portion;
	/**
	 * Instanziieren der Plus- und Minus-Buttons zum Auswählen der Menge eines
	 * Produkts.
	 */
	JButton jb_more, jb_less;

	/**
	 * Kontruktor, welcher alle Buttons erstellt und die Beschriftungen mit
	 * Anfangswerten beschriftet.
	 * 
	 * @param portion die zugehörige Instanz der Klasse Portion trägt die
	 *                Eigenschaften des Produktes in sich.
	 */
	public Panel_Selection(Portion portion, Automat automat) {
		this.portion = portion;
		this.automat = automat;
		Font arial = new Font("Arial", Font.PLAIN, 18);

		this.setLayout(new GridLayout(1, 0));

		/**
		 * Darstellen der Portion, welche zur Auswahl steht.
		 */
		this.add(new JLabel(portion.getName(), SwingConstants.LEFT));
		this.add(new JLabel("" + portion.getKilopreis() + " €/kg", SwingConstants.RIGHT));
//		this.add(new JLabel("  haltbar bis " + portion.haltbar, SwingConstants.RIGHT));
		this.add(new JLabel("" + portion.getLagermenge() + " mal auf Lager", SwingConstants.RIGHT));

		// Ausgewählte Menge anzeigen
		jlbl_amount = new JLabel("0", SwingConstants.CENTER);
		jlbl_amount.setFont(arial);
		jlbl_amount.setBackground(Color.white);
		jlbl_amount.setSize(20, 20);
		this.add(jlbl_amount);

		/**
		 * ButtonsListener, welcher die Änderungen der Menge steuert
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
		 * Button zum Erhöhen der Menge
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
		jb_less.setVisible(false);
		this.add(jb_less);

		jlbl_preis = new JLabel("0", SwingConstants.LEFT);
		this.add(jlbl_preis);
		this.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * der gesamte Preis der ausgewälten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2);
		jlbl_preis.setText("" + formatter.format(portion.getPortionspreis() * Integer.parseInt(jlbl_amount.getText())));
	}
}