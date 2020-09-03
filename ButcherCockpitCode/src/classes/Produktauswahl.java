package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Produktauswahl extends JPanel {
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
	 * Kontruktor, welcher alle Buttons erstellt und die Beschriftungen mit
	 * Anfangswerten beschriftet.
	 * 
	 * @param portion die zugehörige Instanz der Klasse Portion trägt die
	 *                Eigenschaften des Produktes in sich.
	 */
	public Produktauswahl(Portion portion) {
		JButton jb_more, jb_less;
		this.portion = portion;
		Font arial = new Font("Arial", Font.PLAIN, 18);

		this.setLayout(new GridLayout(1, 0));

		/**
		 * Darstellen der Portion, welche zur Auswahl steht.
		 */
		this.add(new JLabel(portion.name));
		this.add(new JLabel("" + portion.portionspreis + "€/Portion"));
		this.add(new JLabel("haltbar bis " + portion.haltbar));
		this.add(new JLabel(""+ portion.lagermenge+" mal auf Lager", SwingConstants.CENTER));

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
			}
		};

		/**
		 * Button zum Erhöhen der Menge
		 */
		jb_more = new JButton("+");
		jb_more.setSize(20, 20);
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		this.add(jb_more);

		/**
		 * Button zum Vermindern der Menge
		 */
		jb_less = new JButton("-");
		jb_less.setSize(20, 20);
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		this.add(jb_less);

		jlbl_preis = new JLabel("", SwingConstants.CENTER);
		this.add(jlbl_preis);
		this.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * der gesamte Preis der ausgewälten Portionen wird aktualisiert
	 */
	public void aktualisierePreise() {
		// Hier fehlt noch der Fall, dass jlbl_amount leer ist (tritt ein, Falls anzahl
		// == 0)

		jlbl_preis.setText("" + portion.portionspreis * Integer.parseInt(jlbl_amount.getText()) + " €");
	}
}