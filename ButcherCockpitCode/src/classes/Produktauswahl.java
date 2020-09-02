package classes;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Produktauswahl extends JPanel {
	JLabel jlbl_amount, jlbl_preis;
	JButton jb_more, jb_less;
	Portion portion;

	public Produktauswahl(Portion portion) {
		this.portion = portion;
		Font arial = new Font("Arial", Font.PLAIN, 18);

		this.setLayout(new GridLayout(1, 0));

		// Produkt darstellen
		this.add(new JLabel(portion.name));
		this.add(new JLabel("" + portion.portionspreis + "€/Portion"));
		this.add(new JLabel("haltbar bis " + portion.haltbar));

		// Ausgewählte Menge anzeigen
		jlbl_amount = new JLabel("0", SwingConstants.CENTER);
		jlbl_amount.setFont(arial);
		jlbl_amount.setBackground(Color.white);
		jlbl_amount.setSize(20, 20);
		this.add(jlbl_amount);

		// ButtonsListener, welcher die Änderungen der Menge steuert
		ActionListener bl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jb_source = (JButton) e.getSource();
				int amount = Integer.parseInt(jlbl_amount.getText());
				if (jb_source.getText().equals("+")) {
					amount++;
				} else if (jb_source.getText().equals("-")) {
					amount--;
				}
				if(amount == 0) {
					jlbl_amount.setText("");
				}else {
					jlbl_amount.setText("" + amount);
				}
				aktualisierePreise();
			}
		};

		// Button zum Erhöhen der Menge
		jb_more = new JButton("+");
		jb_more.setSize(20, 20);
		jb_more.setBackground(Color.white);
		jb_more.addActionListener(bl);
		this.add(jb_more);

		// Button zum Vermindern der Menge
		jb_less = new JButton("-");
		jb_less.setSize(20, 20);
		jb_less.setBackground(Color.white);
		jb_less.addActionListener(bl);
		this.add(jb_less);

		jlbl_preis = new JLabel("", SwingConstants.CENTER);
		this.add(jlbl_preis);
		this.setBackground(Color.LIGHT_GRAY);
	}

	public void aktualisierePreise() {
		// Hier fehlt noch der Fall, dass jlbl_amount leer ist (tritt ein, Falls anzahl == 0)
		jlbl_preis.setText("" + portion.portionspreis * Integer.parseInt(jlbl_amount.getText()) + " €");
	}
}